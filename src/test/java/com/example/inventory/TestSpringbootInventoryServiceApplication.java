package com.example.inventory;

import org.springframework.boot.SpringApplication;

public class TestSpringbootInventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(SpringbootInventoryServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
