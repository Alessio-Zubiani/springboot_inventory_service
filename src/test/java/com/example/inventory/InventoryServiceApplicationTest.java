package com.example.inventory;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryServiceApplicationTest {
	
	@ServiceConnection
	static MySQLContainer mySQLContainer = (MySQLContainer) new MySQLContainer("mysql:latest").withReuse(true);
	
	@LocalServerPort
	private Integer port;
	
	
	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = this.port;
	}
	
	static {
		mySQLContainer.start();
	}

	@Test
	void isInStockTest() {
		
		boolean response = RestAssured.given()
			.when()
			.get("/api/inventory?skuCode=iphone_15&quantity=10")
			.then()
			.log().all()
			.statusCode(200)
			.extract().response().as(Boolean.class);
		
		assertThat(response).isTrue();
	}
	
	@Test
	void isNotInStockTest() {
		
		boolean response = RestAssured.given()
			.when()
			.get("/api/inventory?skuCode=iphone_15&quantity=150")
			.then()
			.log().all()
			.statusCode(200)
			.extract().response().as(Boolean.class);
		
		assertThat(response).isFalse();
	}

}
