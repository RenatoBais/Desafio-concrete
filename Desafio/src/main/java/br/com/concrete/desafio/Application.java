package br.com.concrete.desafio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(value="classpath:hsql_cfg.xml")
public class Application {

	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
