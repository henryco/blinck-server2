package dev.tindersamurai.blinckserver;

import dev.tindersamurai.blinckserver.configuration.MainConfiguration;
import lombok.val;
import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@Import({MainConfiguration.class})
public class BlinckServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlinckServerApplication.class, args);
	}


	@Bean
	public Server h2Server() {
		val server = new Server();
		try {
			server.runTool("-tcpAllowOthers");
			server.runTool("-tcp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return server;
	}
}
