package br.com.entrega_mais.transportadora;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableCaching
@SpringBootApplication
public class TransportadoraApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransportadoraApplication.class, args);
	}

}
