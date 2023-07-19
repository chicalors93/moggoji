package kr.moggoji.api.application.core.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorResponseDTO {
  private final String code;
  private final String message;
}
