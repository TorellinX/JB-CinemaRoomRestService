package cinema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CinemaRoomRestServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaRoomRestServiceApplication.class, args);
	}

	@Bean
	Integer totalRows() {
		return 9;
	}

	@Bean
	Integer totalColumns() {
		return 9;
	}

}
