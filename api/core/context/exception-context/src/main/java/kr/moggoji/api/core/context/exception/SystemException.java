package kr.moggoji.api.core.context.exception;

public abstract class SystemException extends RuntimeException {
  public SystemException() {
    super();
  }

  public SystemException(String message) {
    super(message);
  }
}
