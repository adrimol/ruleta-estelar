package co.adrimol.ruleta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class RuletaApp extends WebMvcConfigurerAdapter {

  public static void main(String[] args) {
    SpringApplication.run(RuletaApp.class, args);
  }
}
