package dev.tindersamurai.blinckserver;

import dev.tindersamurai.blinckserver.configuration.MainConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@EnableDiscoveryClient
@Import({MainConfiguration.class})
public class BlinckServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlinckServerApplication.class, args);
	}
}
