package kr.moggoji.api.domain.user.repository.condition;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserCondition {
  private String username;
}
