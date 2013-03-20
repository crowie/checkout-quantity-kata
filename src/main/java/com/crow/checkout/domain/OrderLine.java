package com.crow.checkout.domain;

import com.crow.checkout.pricing.DefaultPricingStrategy;
import com.crow.checkout.pricing.PricingStrategy;

public class OrderLine {

	private int quantity;
	private Product product;
	private PricingStrategy pricingStrategy;

	public OrderLine(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public int getTotalPrice() {
		
		if (pricingStrategy == null) {
			setPricingStrategy(new DefaultPricingStrategy(product.getPrice(), quantity));
		}
		
		return pricingStrategy.getTotalPrice();
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setPricingStrategy(PricingStrategy pricingStrategy) {
		this.pricingStrategy = pricingStrategy;
	}
	
}
