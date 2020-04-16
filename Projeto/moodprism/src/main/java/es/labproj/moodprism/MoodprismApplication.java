package es.labproj.moodprism;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@EnableScheduling
@SpringBootApplication
@ComponentScan({"es.labproj.moodprism"})
@EntityScan({"es.labproj.moodprism"})
public class MoodprismApplication
{
	public static void main(String[] args) {SpringApplication.run(MoodprismApplication.class, args);}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {return builder.build();}
}
