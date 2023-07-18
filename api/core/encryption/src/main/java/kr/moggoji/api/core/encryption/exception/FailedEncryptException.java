package kr.moggoji.api.core.encryption.exception;

import kr.moggoji.api.core.context.exception.SystemException;

public class FailedEncryptException extends SystemException {
  public FailedEncryptException(Throwable cause) {
    super("암호화 할 수 없습니다.", cause);
  }
}
