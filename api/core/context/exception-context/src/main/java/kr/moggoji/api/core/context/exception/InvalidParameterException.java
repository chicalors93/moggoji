package kr.moggoji.api.core.context.exception;

public class InvalidParameterException extends SystemException {
  public InvalidParameterException() {
    super("유효하지 않은 파라미터 입니다.");
  }
}
