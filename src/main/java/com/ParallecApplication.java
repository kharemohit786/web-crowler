package com;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.parallec.ParallecService;

@SpringBootApplication
public class ParallecApplication implements CommandLineRunner {

	@Value("${filePath}")
	private String filePath;
	
	
	public static void main(String[] args) {
		SpringApplication.run(ParallecApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ParallecService ps = new ParallecService();
		ps.getResponse(filePath);
		
	}

}
