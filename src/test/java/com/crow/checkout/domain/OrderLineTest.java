package com.crow.checkout.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class OrderLineTest {

	@Test
	public void orangeConfiguresOrderLineToUseQuantityPricing() {
		Product product = new Product("A", 10);
		OrderLine orderLine = new OrderLine(product, 5);
		
		// Default strategy = price & quantity
		assertThat(orderLine.getTotalPrice(), equalTo(50));
	}
}
