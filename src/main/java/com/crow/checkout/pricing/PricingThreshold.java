package com.crow.checkout.pricing;

public class PricingThreshold {

	private int threshold;
	private int thresholdPrice;
	
	protected PricingThreshold(int threshold, int thresholdPrice) {
		this.threshold = threshold;
		this.thresholdPrice = thresholdPrice;
	}
	
	public int getThreshold() {
		return threshold;
	}
	
	public int getThresholdPrice() {
		return thresholdPrice;
	}
	
}
