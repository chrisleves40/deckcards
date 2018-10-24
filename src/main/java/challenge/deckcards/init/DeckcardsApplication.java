package challenge.deckcards.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("challenge.deckcards")
@SpringBootApplication
public class DeckcardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeckcardsApplication.class, args);
	}
}
