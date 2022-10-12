package sem3.its.ReReddit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })

public class ReRedditApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReRedditApplication.class, args);
	}

}
