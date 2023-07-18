package kr.moggoji.api.core.encryption.exception;

import kr.moggoji.api.core.context.exception.SystemException;

public class FailedDecryptException extends SystemException {
  public FailedDecryptException(Throwable cause) {
    super("복호화 할 수 없습니다.", cause);
  }
}
