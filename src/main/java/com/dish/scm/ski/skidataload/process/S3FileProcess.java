package com.dish.scm.ski.skidataload.process;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.dish.scm.ski.skidataload.config.EmailConfig;
import com.dish.scm.ski.skidataload.config.S3Config;
import com.dish.scm.ski.skidataload.util.SendEmailUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.dish.scm.ski.skidataload.util.FileUtil.deleteAllFiles;
import static com.dish.scm.ski.skidataload.util.FileUtil.getFiles;

@Component
@Slf4j
public class S3FileProcess {
    private String file_process_folder;
    private final SendEmailUtil sendEmailUtil;
    private final EmailConfig emailConfig;
    private final S3Config s3Config;

    @Autowired
    public S3FileProcess(SendEmailUtil sendEmailUtil,EmailConfig emailConfig,S3Config s3Config) {
        this.sendEmailUtil = sendEmailUtil;
        this.emailConfig = emailConfig;
        this.s3Config = s3Config;
    }

    public boolean downloadFile(String proc) {
        log.info("downloadFile");
        if (System.getProperty("os.name").startsWith("Window")) {
            file_process_folder = this.s3Config.getWin_location().getProcess();
            deleteAllFiles(this.s3Config.getWin_location().getOutput(),0);
        }
        else {
            file_process_folder = this.s3Config.getLinux_location().getProcess();
            deleteAllFiles(this.s3Config.getLinux_location().getOutput(),0);
        }
        deleteAllFiles(file_process_folder,0);
        boolean status = downloadFromS3(file_process_folder,1);
        if (StringUtils.isEmpty(proc) && !status) {
            if (!StringUtils.isEmpty(emailConfig.getTo()))
                sendEmailUtil.sendEmail(emailConfig.getFrom(), emailConfig.getTo(), emailConfig.getTo(), "Unable to download file:", ".Hi\n\n Unable to download file from Aws s3, please delete the file from S3 and upload again.", null, null);
        }
        return status;
    }


    private boolean downloadFromS3(String file_process_folder,int cnt) {
        log.info("download files From S3 bucket insert:{} folder:{}",this.s3Config.getS3().getBucketname(),this.s3Config.getS3().getInsert_folder());
        cnt = cnt +1;
        log.info("Creating client");
        AmazonS3 s3 = getAwsClient();
        log.info("ok");
        try {

            try {
                ObjectListing obj = s3.listObjects(new ListObjectsRequest().withBucketName(this.s3Config.getS3().getBucketname())
                        .withPrefix(this.s3Config.getS3().getInsert_folder()));
                obj.getObjectSummaries().forEach(x -> {

                    if (x.getKey().toLowerCase().endsWith(".xlsx")) {

                        S3Object sobj = s3.getObject(new GetObjectRequest(this.s3Config.getS3().getBucketname(), x.getKey()));
                        S3ObjectInputStream inputStream = sobj.getObjectContent();

                        String fname = file_process_folder + x.getKey().substring(x.getKey().lastIndexOf("/") + 1);

                        try {
                            FileUtils.copyInputStreamToFile(inputStream, new File(fname));
                            log.info("Downloaded file:{} in {}", x.getKey(), fname);

                        } catch (IOException e) {
                            sendEmailUtil.sendEmail(emailConfig.getFrom(), emailConfig.getTo(), emailConfig.getTo(), "Unable to download insert file:" + x.getKey(), ".Hi\n\n Unable to download file from Aws s3", null, null);
                            e.printStackTrace();
                        }
                    }
                });
            }
            catch (SdkClientException sdk) {
                log.error("S3 connection Error:{}", sdk.getMessage() + sdk.getLocalizedMessage());
                if (cnt<4) {
                    s3.shutdown();
                    Thread.sleep(120000);
                    downloadFromS3(file_process_folder, cnt);
                }
                else {
                    return false;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return false;
        }
        finally {
            if (s3!=null)
                s3.shutdown();
        }
        return true;
    }


    public final void deleteFilesFromS3(int cnt) {
        cnt = cnt +1;
        log.info("deleting files From S3 bucket insert:{} folder:{}",this.s3Config.getS3().getBucketname(),this.s3Config.getS3().getInsert_folder());
        AmazonS3 s3 = getAwsClient();
        try {
            ObjectListing obj = s3.listObjects(new ListObjectsRequest().withBucketName(this.s3Config.getS3().getBucketname())
                    .withPrefix(this.s3Config.getS3().getInsert_folder()));
            obj.getObjectSummaries().forEach(x -> {

                if (x.getKey().toLowerCase().endsWith(".xlsx")) {
                    s3.deleteObject(this.s3Config.getS3().getBucketname(), x.getKey());
                    log.info("Deleted file in s3 {}", x.getKey());
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            if (cnt<4) {
                s3.shutdown();
                deleteFilesFromS3(cnt);
            }
        }
    }

    public List<String> getS3Files() {
        return getFiles(file_process_folder);
    }


    public final void uploadFileToError(String outFile,int cnt) {
        log.info("uploadFileToError()");
        AmazonS3 s3 = getAwsClient();
        try {
            s3.putObject(this.s3Config.getS3().getBucketname(), this.s3Config.getS3().getError_folder() + new File(outFile).getName(), new File(outFile));
            log.info("uploaded File To s3 error bucket:{} ,folder:{} ", this.s3Config.getS3().getBucketname(), this.s3Config.getS3().getError_folder());
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            if (cnt<4) {
                s3.shutdown();
                uploadFileToError(outFile, cnt);
            }
        }
    }

    public void uploadFileToArchive(String outFile,int cnt) {
        cnt = cnt+1;
        log.info("uploadFileToArchive()");
        AmazonS3 s3 = getAwsClient();
        try {
            s3.putObject(this.s3Config.getS3().getBucketname(), this.s3Config.getS3().getArchive_folder() + new File(outFile).getName(), new File(outFile));
            log.info("uploaded File To s3 archive bucket:{} ,folder:{} ", this.s3Config.getS3().getBucketname(), this.s3Config.getS3().getArchive_folder());
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            if (cnt<4) {
                s3.shutdown();
                uploadFileToArchive(outFile, cnt);
            }
        }
    }


    private AmazonS3 getAwsClient()  {
        return AmazonS3ClientBuilder.defaultClient();
        //.withCredentials(new ProfileCredentialsProvider()).build();
        //return AmazonS3ClientBuilder.defaultClient();
        //ClientConfiguration client = new ClientConfiguration();

        //return AmazonS3ClientBuilder.standard()
        //.withCredentials(new AWSStaticCredentialsProvider(new EC2ContainerCredentialsProviderWrapper().getCredentials()))
        //.withClientConfiguration(client)
        //.withRegion(this.s3Config.getS3().getRegion())
        //		  .build();
    }

}
