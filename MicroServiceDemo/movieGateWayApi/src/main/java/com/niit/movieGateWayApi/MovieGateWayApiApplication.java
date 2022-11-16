package com.niit.movieGateWayApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
@EnableEurekaClient
public class MovieGateWayApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieGateWayApiApplication.class, args);
	}
	@Bean
	public RouteLocator route(RouteLocatorBuilder builder){
		return builder.routes()
				.route(p->p.path("/user/**").uri("lb://user-authentication-service"))
				.route(p->p.path("/usermovie/**").uri("lb://MOVIE-SERVICE"))
				.build();
	}

}
