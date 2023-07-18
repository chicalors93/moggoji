package kr.moggoji.api.application.configuration.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;

import java.io.IOException;
import java.io.InputStream;

public class DecodedHttpInputMessage implements HttpInputMessage {
  private final HttpHeaders headers;
  private final InputStream body;

  public DecodedHttpInputMessage(HttpHeaders headers, InputStream body) {
    super();
    this.headers = headers;
    this.body = body;
  }

  @Override
  public InputStream getBody() throws IOException {
    return this.body;
  }

  @Override
  public HttpHeaders getHeaders() {
    return this.headers;
  }
}
