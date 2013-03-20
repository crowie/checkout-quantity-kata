package com.crow.checkout.pricing;

/**
 * Simple productAPricer to return a total productPrice
 * 
 * @author Phil Crow
 *
 */
public class DefaultPricingStrategy implements PricingStrategy {

	private int productPrice;
	private int quantity;
	
	public DefaultPricingStrategy(int productPrice, int quantity) {
		this.productPrice = productPrice;
		this.quantity = quantity;
	}
	
	@Override
	public int getTotalPrice() {
		return this.productPrice * this.quantity;
	}

}
