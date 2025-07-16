package Esport_Website;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class EsportWebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsportWebsiteApplication.class, args);
	}

}
