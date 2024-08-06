package br.com.fiap.lanchonete;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class ProducaoServiceFase4Application {

	public static void main(String[] args) {
		SpringApplication.run(ProducaoServiceFase4Application.class, args);
	}

}
