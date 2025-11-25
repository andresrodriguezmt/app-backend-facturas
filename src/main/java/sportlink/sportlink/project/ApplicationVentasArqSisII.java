package sportlink.sportlink.project;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Log4j2
@EnableScheduling
public class ApplicationVentasArqSisII {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationVentasArqSisII.class, args);
	}

}

