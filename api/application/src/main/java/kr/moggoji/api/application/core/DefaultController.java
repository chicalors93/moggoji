package kr.moggoji.api.application.core;

import kr.moggoji.api.application.core.dto.ErrorResponseDTO;
import kr.moggoji.api.application.core.http.response.error.ErrorCode;

public abstract class DefaultController {
  protected ErrorResponseDTO error(ErrorCode errorCode) {
    return new ErrorResponseDTO(errorCode.code(), errorCode.message());
  }
}
