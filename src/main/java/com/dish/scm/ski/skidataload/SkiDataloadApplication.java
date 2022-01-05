package com.dish.scm.ski.skidataload;

import com.dish.scm.ski.skidataload.process.DataLoadProcess;
import com.dish.scm.ski.skidataload.process.S3FileProcess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
@Slf4j
public class SkiDataloadApplication implements CommandLineRunner {
    final ApplicationContext applicationContext;

    public SkiDataloadApplication(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
    public static void main(String[] args) {
        SpringApplication.run(SkiDataloadApplication.class, args);
    }

    @Override
    public void run(String... args)  {

        try {
            DataLoadProcess dl = (DataLoadProcess) applicationContext.getBean("dataLoadProcess");

            /*
            S3FileProcess dwnFile = (S3FileProcess) applicationContext.getBean("s3FileProcess");
            boolean dwnstatus = dwnFile.downloadFile("");
            if (dwnstatus) {

                List<String> lstFile = dwnFile.getS3Files();
                if (lstFile != null && lstFile.size() > 0) {
                    for (String fn : lstFile) {
                        log.info("Processing s3 insert files {}", fn);
                        String status = dl.processFile(fn);
                        if (status.equalsIgnoreCase("success")) {
                            dwnFile.uploadFileToArchive(fn,1);
                        } else {
                            dwnFile.uploadFileToError(fn,1);
                        }
                    }
                    dwnFile.deleteFilesFromS3(1);
                }
            }
            else
                log.info("No files to load");
            */

            dl.processFile("C:\\temp1\\insert\\as.xlsx");
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }

    }

}
