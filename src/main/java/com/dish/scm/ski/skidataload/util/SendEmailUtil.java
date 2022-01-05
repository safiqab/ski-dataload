package com.dish.scm.ski.skidataload.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
@Slf4j
public class SendEmailUtil {
    private final JavaMailSender javaMailSender;

    @Autowired
    public SendEmailUtil(JavaMailSender javaMailSender) {

        this.javaMailSender = javaMailSender;
    }

    public  void sendEmail(String emailFrom,String emailTo,String emailCC,String subject,
                           String body,String attachFile,String fname_temp)  {
        if (!StringUtils.isEmpty(emailTo)) {
            try {
                if (!StringUtils.isEmpty(attachFile)) {
                    MimeMessage mie = javaMailSender.createMimeMessage();
                    MimeMessageHelper mhelp = new MimeMessageHelper(mie,true);
                    mhelp.setTo(emailTo.split(","));
                    if (!StringUtils.isEmpty(emailCC))
                        mhelp.setCc(emailCC.split(","));
                    mhelp.setFrom(emailFrom);
                    mhelp.setSubject(subject);
                    mhelp.setText(body);
                    FileSystemResource file = new FileSystemResource(new File(attachFile));
                    mhelp.addAttachment(fname_temp,file);
                    javaMailSender.send(mie);
                }
                else {
                    SimpleMailMessage smm = new SimpleMailMessage();
                    smm.setTo(emailTo.split(","));
                    if (!StringUtils.isEmpty(emailCC))
                        smm.setCc(emailCC.split(","));
                    smm.setFrom(emailFrom);
                    smm.setSubject(subject);
                    smm.setText(body);
                    javaMailSender.send(smm);
                }
            }
            catch (MailException me) {
                log.error("Unable to send email Mail Exception due to :{}",me.getMessage());
                me.printStackTrace();
            }
            catch (Exception e) {
                log.error("unable to send email due to :{}",e.getMessage());
            }
        }
    }
}
