package com.postcode.postcode_distance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.postcode.postcode_distance.repository.PostcodeRepository;
import com.postcode.postcode_distance.repository.PostcodeRepositoryImpl;
import com.postcode.postcode_distance.util.DistanceCalculator;

@SpringBootApplication
@EnableAspectJAutoProxy
public class PostcodeDistanceApplication {

	// Main application entry point
	public static void main(String[] args) {
		SpringApplication.run(PostcodeDistanceApplication.class, args);
	}
	
	@Bean
	// Creates postcode repository bean
	public PostcodeRepository postcodeRepository() {
		return new PostcodeRepositoryImpl();
	}
	
	@Bean
	// Creates distance calculator bean
	public DistanceCalculator distanceCalculator() {
		return new DistanceCalculator();
	}
}
