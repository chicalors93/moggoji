package kr.moggoji.api.core.context.exception;

public abstract class SystemException extends RuntimeException {
  protected SystemException() {
    super();
  }

  protected SystemException(String message) {
    super(message);
  }

  protected SystemException(Throwable cause) {
    super(cause);
  }

  protected SystemException(String message, Throwable cause) {
    super(message, cause);
  }
}
