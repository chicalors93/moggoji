package kr.moggoji.api.domain.user.column;

import kr.moggoji.api.core.context.database.Column;
import kr.moggoji.api.core.context.exception.InvalidParameterException;
import org.springframework.util.ObjectUtils;

public class Username extends Column<String> {
  public Username(String value) {
    if (ObjectUtils.isEmpty(value)) {
      throw new InvalidParameterException();
    }

    this.value = value;
  }
}
