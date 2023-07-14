package kr.moggoji.api.domain.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.moggoji.api.domain.user.column.Password;
import kr.moggoji.api.domain.user.column.Username;
import kr.moggoji.api.domain.user.entity.UserEntity;
import kr.moggoji.api.domain.user.enumerate.UserTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


import static kr.moggoji.api.domain.user.entity.QUserEntity.userEntity;

@Repository
@RequiredArgsConstructor
public class UserDefaultRepository {
  private final JPAQueryFactory factory;

  public UserEntity insert(Username username, Password password, UserTypeEnum type) {
    factory.insert(userEntity)
            .columns(userEntity.username, userEntity.password, userEntity.type)
            .values(username.get(), password.get(), type)
            .execute();


    return factory.select(userEntity)
            .from(userEntity)
            .where(userEntity.username.eq(username.get()))
            .fetchOne();
  }
}
