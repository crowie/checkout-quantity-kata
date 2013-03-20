package com.crow.checkout.pricing;

public class QuantityBasedPricingStrategy implements PricingStrategy {

	private int quantity;
	private int productPrice;
	private PricingThreshold threshold;
	
	@Override
	public int getTotalPrice() {
		
		int totalPrice = 0;
		int remainingQuantity = quantity;
		
		while (remainingQuantity >= threshold.getThreshold() 
				&& remainingQuantity > 0) {
			totalPrice += threshold.getThresholdPrice();
			remainingQuantity -= threshold.getThreshold();
		}
		
		totalPrice = (remainingQuantity > 0) ? 
				totalPrice + (remainingQuantity * productPrice) : totalPrice;
		
		return totalPrice;
	}
	
	public QuantityBasedPricingStrategy(PricingThreshold threshold, int productPrice, int quantity) {
		this.threshold = threshold;
		this.productPrice = productPrice;
		this.quantity = quantity;
	}
}
