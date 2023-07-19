package kr.moggoji.api.core.context.exception.database;

import kr.moggoji.api.core.context.exception.SystemException;

public class EntityExistsException extends SystemException {
  public EntityExistsException() {
    super("이미 존재하는 객체 입니다.");
  }
}
