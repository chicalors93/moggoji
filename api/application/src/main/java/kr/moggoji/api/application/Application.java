package kr.moggoji.api.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "kr.moggoji.api")
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
