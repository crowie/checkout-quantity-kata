package com.crow.checkout.pricing;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class DefaultPricingStrategyTest {

	DefaultPricingStrategy pricer;
	
	@Test
	public void standardPricerReturnsSimpleTotal() {
		int productPrice = 10;
		int orderLineQuantity = 5;
		pricer = new DefaultPricingStrategy(productPrice, orderLineQuantity);
		
		// quantity * price
		assertThat(pricer.getTotalPrice(), equalTo(50));
	}
	
}
