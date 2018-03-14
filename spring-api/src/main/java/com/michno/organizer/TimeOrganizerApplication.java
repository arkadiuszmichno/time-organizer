package com.michno.organizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.sql.Time;
import java.util.TimeZone;


@SpringBootApplication
@EnableJpaRepositories
@EntityScan(basePackageClasses = {
		TimeOrganizerApplication.class,
		Jsr310JpaConverters.class
})
public class TimeOrganizerApplication {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UT"));
	}

	public static void main(String[] args) {
		SpringApplication.run(TimeOrganizerApplication.class, args);
	}
}
