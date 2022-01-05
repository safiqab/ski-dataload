package com.dish.scm.ski.skidataload.process;

import com.dish.scm.ski.skidataload.config.EmailConfig;
import com.dish.scm.ski.skidataload.config.FileTabNameConfig;
import com.dish.scm.ski.skidataload.export.SkuDataExport;
import com.dish.scm.ski.skidataload.model.file.ModelNumVM;
import com.dish.scm.ski.skidataload.model.file.SkuAttributeDefinitionVM;
import com.dish.scm.ski.skidataload.model.file.SkuAttributeReferenceVM;
import com.dish.scm.ski.skidataload.readfile.EntityDataWS;
import com.dish.scm.ski.skidataload.readfile.SkuDataImport;
import com.dish.scm.ski.skidataload.util.SendEmailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
@Slf4j
public class DataLoadProcess {

    private final DataValidation dataValidation;
    private final SkuDataExport skuDataExport;
    private final SkuDataImport skuDataImport;
    private final EntityDataWS entityDataWS;

    //private final EntityWorkFlow entityWorkFlow;
    private final SendEmailUtil sendEmailUtil;

    private final SkiReferenceProcess skiReferenceProcess;
    private final InsertSkuEntity insertSkuEntity;
    private final FileTabNameConfig fileTabNameConfig;
    private final EmailConfig emailConfig;

    @Autowired
    public DataLoadProcess(SkiReferenceProcess skiReferenceProcess, InsertSkuEntity insertSkuEntity,
                           DataValidation dataValidation, EmailConfig emailConfig, SendEmailUtil sendEmailUtil, SkuDataExport skuDataExport, SkuDataImport skuDataImport,
                           EntityDataWS entityDataWS, FileTabNameConfig fileTabNameConfig,
                           EmailConfig emailConfig1) {

        this.skiReferenceProcess = skiReferenceProcess;
        this.insertSkuEntity = insertSkuEntity;

        this.dataValidation = dataValidation;
        this.skuDataExport = skuDataExport;
        this.skuDataImport = skuDataImport;
        this.entityDataWS = entityDataWS;

        this.sendEmailUtil = sendEmailUtil;
        this.fileTabNameConfig = fileTabNameConfig;

        this.emailConfig = emailConfig1;
    }

    public String processFile(String fileName) {

        log.info("Process file:{}",fileName);
        SkuAttributeReferenceVM skuAttributeReferenceVM = new SkuAttributeReferenceVM();
        SkuAttributeDefinitionVM skuAttributeDefinitionVM = new SkuAttributeDefinitionVM();
        skuDataImport.readData(fileName,skuAttributeReferenceVM,skuAttributeDefinitionVM);

        //List<ModelNumVM> lstModelNumberDelete = entityDataWS.readData(fileName,fileTabNameConfig.getDelete());
        //List<ModelNumVM> lstModelNumberSA = entityDataWS.readData(fileName,fileTabNameConfig.getSubmit_approval());
        //List<ModelNumVM> lstModelNumberApprove = entityDataWS.readData(fileName,fileTabNameConfig.getApprove());
        //List<ModelNumVM> lstModelNumberPurge = entityDataWS.readData(fileName,fileTabNameConfig.getPurge());
        //List<ModelNumVM> lstModelNumberStage = entityDataWS.readData(fileName,fileTabNameConfig.getStage());

        boolean isFactDataExist = dataValidation.isFactDataExist(skuAttributeReferenceVM);
        boolean isModelDataExist = dataValidation.isModelDataExist(skuAttributeDefinitionVM);
        //boolean isEntityDataExist = dataValidation.isEntityDataExist(skuAttributeDefinitionVM);

        if (isFactDataExist)
            this.skiReferenceProcess.insertReferenceData(skuAttributeReferenceVM);

        /*
        if (isEntityDataExist)
            this.insertSkuEntity.importDefinitionData(skuAttributeDefinitionVM);


        if (isFactDataExist || isEntityDataExist || (!StringUtils.isEmpty(lstModelNumberPurge) && lstModelNumberPurge.size()>0)
                || (!StringUtils.isEmpty(lstModelNumberSA) && lstModelNumberSA.size()>0) ||
                (!StringUtils.isEmpty(lstModelNumberApprove) && lstModelNumberApprove.size()>0) ||
                (!StringUtils.isEmpty(lstModelNumberStage) && lstModelNumberStage.size()>0)) {

            boolean isError = dataValidation.isErrorDataExist(skuAttributeReferenceVM,skuAttributeDefinitionVM,lstModelNumberPurge,
                    lstModelNumberSA,lstModelNumberApprove,lstModelNumberStage);

            sendReport(new File(fileName).getName(),isError,skuAttributeReferenceVM,skuAttributeDefinitionVM,
                    lstModelNumberDelete,lstModelNumberPurge,lstModelNumberSA,lstModelNumberApprove,lstModelNumberStage);
            return isError?"Failure":"Success";
        }
        else {
            String content ="Hi,\n\n" + " There is no data in the file(" + new File(fileName).getName() +")." +
                    "Please refer attached file.";
            String subject = "No data to load - " + new File(fileName).getName();
            SendEmail(subject,content,fileName,fileName);
            return "Failure";
        }
        */
        return "dummy";
    }


    private void sendReport(String inputFileName, boolean isError,
                            SkuAttributeReferenceVM skuAttributeReferenceVM,
                            SkuAttributeDefinitionVM skuAttributeDefinitionVM,
                            List<ModelNumVM> lstModelNameDelete, List<ModelNumVM> lstModelNamePurge,
                            List<ModelNumVM> lstModelNameSA,
                            List<ModelNumVM> lstModelNameApprove,
                            List<ModelNumVM> lstModelNameStage) {
        String content;
        String subject;

        String outputfile =skuDataExport.exportData(inputFileName,skuAttributeReferenceVM,skuAttributeDefinitionVM,
                lstModelNameDelete,lstModelNamePurge,lstModelNameSA,lstModelNameApprove,lstModelNameStage);
        if (isError) {
            content = "Hi,\n\n" + "Unable to load data from file(" + inputFileName + ")." +
                    "Please refer attached file for detailed error message";
            subject = "Data Load Failed - " + inputFileName;
        }
        else {

            content = "Hi,\n\n" + "Data loaded successfully(" + inputFileName + ")." +
                    "Please refer attached file for more information";
            subject = "Data Loaded successfully - " + inputFileName;
        }
        if (!StringUtils.isEmpty(outputfile)) {
            log.info("Send output file {} in Email",outputfile);
            SendEmail(subject, content, outputfile,inputFileName);
        }
    }


    private void SendEmail(String subject,String emailContent,String outputfile,String inputFileName) {

        if (!StringUtils.isEmpty(emailConfig.getTo())) {
            sendEmailUtil.sendEmail(emailConfig.getFrom() ,emailConfig.getTo(),emailConfig.getCc(),
                    subject,emailContent,outputfile,inputFileName);
        }
    }

}
