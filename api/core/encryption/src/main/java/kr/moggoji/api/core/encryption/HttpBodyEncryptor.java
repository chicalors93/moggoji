package kr.moggoji.api.core.encryption;

import kr.moggoji.api.core.encryption.exception.FailedDecryptException;
import kr.moggoji.api.core.encryption.exception.FailedEncryptException;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Base64Utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

@RequiredArgsConstructor
public class HttpBodyEncryptor {
  private static final String ALGORITHM = "AES/GCM/NoPadding";
  private static final int GCM_IV_LENGTH = 16;
  private static final int GCM_TAG_LENGTH = 128;

  private final String KEY;

  public String encrypt(String plaintext) {
    try {
      SecureRandom random = new SecureRandom();
      byte[] bytesIV = new byte[GCM_IV_LENGTH];

      random.nextBytes(bytesIV);
      GCMParameterSpec iv = new GCMParameterSpec(GCM_TAG_LENGTH, bytesIV);

      Cipher cipher = Cipher.getInstance(ALGORITHM);
      cipher.init(Cipher.ENCRYPT_MODE, createKeySpec(), iv);

      byte[] encryptData = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
      byte[] result = Arrays.copyOf(iv.getIV(), iv.getIV().length + encryptData.length);
      System.arraycopy(encryptData, 0, result, iv.getIV().length, encryptData.length);

      return Base64Utils.encodeToString(result);
    } catch (InvalidAlgorithmParameterException | NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | BadPaddingException | InvalidKeyException e) {
      throw new FailedEncryptException(e);
    }
  }

  public String decrypt(String hash) {
    try {
      byte[] dataBytes = Base64Utils.decodeFromString(hash);
      byte[] ivByte = Arrays.copyOfRange(dataBytes, 0, GCM_IV_LENGTH);
      byte[] cipherText = Arrays.copyOfRange(dataBytes, GCM_IV_LENGTH, dataBytes.length);

      GCMParameterSpec iv = new GCMParameterSpec(GCM_TAG_LENGTH, ivByte);

      Cipher cipher = Cipher.getInstance(ALGORITHM);
      cipher.init(Cipher.DECRYPT_MODE, createKeySpec(), iv);

      byte[] original = cipher.doFinal(cipherText);

      return new String(original, StandardCharsets.UTF_8);
    } catch (InvalidAlgorithmParameterException | NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | BadPaddingException | InvalidKeyException e) {
      throw new FailedDecryptException(e);
    }
  }

  private SecretKeySpec createKeySpec() {
    return new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), "AES");
  }
}
