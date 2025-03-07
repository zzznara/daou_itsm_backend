package kr.co.dysnt.framework.jasypt;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({ "local" })
@Slf4j
public class JasyptConfigTest {

    @Autowired
    private StringEncryptor jasyptStringEncryptor;

    @Test
    public void TestJasyptEncrypt() {

        String plainTextToEncrypt = "DBOTMS";

        String encryptedText = jasyptStringEncryptor.encrypt(plainTextToEncrypt);

        String decryptedText = jasyptStringEncryptor.decrypt(encryptedText);

        log.info("plainTextToEncrypt    : " + plainTextToEncrypt);
        log.info("encryptedText         : " + encryptedText);
        log.info("decryptedText         : " + decryptedText);

        Assert.assertEquals(plainTextToEncrypt, decryptedText);
    }

}
