package com.crow.checkout.pricing;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class QuantityBasedStrategyTest {

	QuantityBasedPricingStrategy productAPricer;
	PricingThreshold threshold;
	
	@Before
	public void setUp() {
		threshold = new PricingThreshold(3, 130);
	}
	
	@Test
	public void detailsOfOrderLineAndThresholdGivesBasicThresholdBasedPrice() {
		int productPrice = 50;
		int orderLineQuantity = 3;
		
		productAPricer = new QuantityBasedPricingStrategy(threshold, productPrice, 
				orderLineQuantity);
		
		assertThat(productAPricer.getTotalPrice(), equalTo(130));
	}
	
	@Test
	public void orderLineGreaterQuantityThanThresholdUsesThreshold() {
		int productPrice = 50;
		int orderLineQuantity = 5;
		
		productAPricer = new QuantityBasedPricingStrategy(threshold, productPrice,
				orderLineQuantity);
		
		assertThat(productAPricer.getTotalPrice(), equalTo(230));
	}
	
	@Test
	public void orderLineQuantityDoubleThresholdUsesThreshold() {
		int productPrice = 50;
		int orderLineQuantity = 6;
		productAPricer = new QuantityBasedPricingStrategy(threshold, 
				productPrice, orderLineQuantity);
		
		assertThat(productAPricer.getTotalPrice(), equalTo(260));
	}
	
	@Test
	public void orderLineQuantityLessThanThresholdGivesCorrectValue() {
		int productPrice = 50;
		int orderLineQuantity = 2;
		productAPricer = new QuantityBasedPricingStrategy(threshold, 
				productPrice, orderLineQuantity);
		
		assertThat(productAPricer.getTotalPrice(), equalTo(100));
	}
}
