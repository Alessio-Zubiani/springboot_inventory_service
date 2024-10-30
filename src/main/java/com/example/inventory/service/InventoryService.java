package com.example.inventory.service;

public interface InventoryService {

	public boolean isInStock(String skuCode, Integer quantity);
	
}
