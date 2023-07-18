package kr.moggoji.api.core.encryption.test;

import kr.moggoji.api.core.encryption.HttpBodyEncryptor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class EncryptionTest {
  String httpBodyKey = "pzcUbFaEaWJBGR8EwCUCaPjkCytYUrcb";
  String bodyMessage = "BODY_TEST";

  @Test
  void HttpBody_암호화() {
    HttpBodyEncryptor encryptor = new HttpBodyEncryptor(httpBodyKey);
    String hash = encryptor.encrypt(bodyMessage);

    System.out.println("hash: " + hash);
  }

  @Test
  void HttpBody_복호화() {
    HttpBodyEncryptor encryptor = new HttpBodyEncryptor(httpBodyKey);
    String hash = "JcaJlcl0DCMxineylGLNpUyGr/TdICvvy2HhqtE4KhPUoEOKCATh7RE=";
    String plaintext = encryptor.decrypt(hash);

    System.out.println("plaintext: " + plaintext);

    assertEquals(plaintext, bodyMessage);
  }
}
