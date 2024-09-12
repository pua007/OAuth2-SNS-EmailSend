package com.housing.back.provider;


import ch.qos.logback.core.net.SyslogOutputStream;
import jakarta.mail.Multipart;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class EmailProvider {

    private final JavaMailSender javaMailSender;
    private static final Logger logger = LoggerFactory.getLogger(EmailProvider.class);

    private static final String SUBJECT = "[회원가입 인증번호] 인증메일";

    public boolean sendCertificationMail(String email, String certificationNumber) {

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

            String htmlContent = getCertificationMessage(certificationNumber);

            messageHelper.setTo(email);
            messageHelper.setSubject(SUBJECT);
            //html: true => Html을 적용할거냐?

            messageHelper.setText(htmlContent, true);

            System.out.println(htmlContent);

            logger.info("메일 전송 준비 완료: {}", email);
            javaMailSender.send(message);
            logger.info("메일 전송 성공: {}", email);

        } catch (Exception exception) {
            logger.error("메일 전송 실패: {}", exception);
            return false;
        }

        return true;
    }

    private String getCertificationMessage(String certificationNumber) {
        String certificationMessage = "";
        certificationMessage += "<h1 style='text-align: center;'>[회원가입 인증번호] 인증번호</h1>";
        certificationMessage += "<h3 style='text-align: center;'>인증코드: <strong style='font-size: 32px; letter-spacing: 8px;'>" + certificationNumber + "</strong></h3>";
        return certificationMessage;
    }
}
