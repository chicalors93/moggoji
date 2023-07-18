package kr.moggoji.api.application.configuration.controller;

import kr.moggoji.api.core.encryption.HttpBodyEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

@RestControllerAdvice
public class EncodedRequestBodyAdvice implements RequestBodyAdvice {
  private final HttpBodyEncryptor httpBodyEncryptor;

  public EncodedRequestBodyAdvice(@Value("${system.configuration.encryption.http.body.key}") String key) {
    this.httpBodyEncryptor = new HttpBodyEncryptor(key);
  }

  @Override
  public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
    return true;
  }

  @Override
  public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
    try (InputStream inputStream = inputMessage.getBody()) {
      byte[] body = StreamUtils.copyToByteArray(inputStream);

      String plaintext = httpBodyEncryptor.decrypt(new String(body, StandardCharsets.UTF_8));

      return new DecodedHttpInputMessage(inputMessage.getHeaders(), new ByteArrayInputStream(plaintext.getBytes()));
    }
  }

  @Override
  public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
    return body;
  }

  @Override
  public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
    return null;
  }
}
