package kr.moggoji.api.application.configuration.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import kr.moggoji.api.core.encryption.HttpBodyEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.IOException;
import java.io.OutputStream;

@RestControllerAdvice
public class EncodeResponseBodyAdvice implements ResponseBodyAdvice<Object> {
  private final HttpBodyEncryptor httpBodyEncryptor;

  public EncodeResponseBodyAdvice(@Value("${system.configuration.encryption.http.body.key}") String key) {
    this.httpBodyEncryptor = new HttpBodyEncryptor(key);
  }
  @Override
  public boolean supports(MethodParameter returnType, Class converterType) {
    return true;
  }

  @Override
  public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
    ObjectMapper objectMapper = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    try (OutputStream stream = response.getBody()) {
      stream.write(httpBodyEncryptor.encrypt(objectMapper.writeValueAsString(body)).getBytes());
      stream.flush();
    } catch (IOException e) {
      // todo log 수집
      return ResponseEntity.internalServerError();
    }

    return null;
  }
}
