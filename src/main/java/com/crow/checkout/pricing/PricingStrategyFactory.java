package com.crow.checkout.pricing;

import com.crow.checkout.domain.OrderLine;

public class PricingStrategyFactory {

	private static final PricingThreshold prodAThreshold = new PricingThreshold(3, 130);
	private static final PricingThreshold prodBThreshold = new PricingThreshold(2, 45);
	
	
	/**
	 * The project is configured to use a quantity based pricing strategy based on
	 * the product.
	 * 
	 * Requires a fully formed order line containing a product with a price, and the
	 * quantity or products.
	 */
	public static PricingStrategy getProductPricingStrategy(OrderLine orderLine) {
		
		if (orderLine.getProduct().getSku() == "A") {
			return new QuantityBasedPricingStrategy(prodAThreshold, 
					orderLine.getProduct().getPrice(), orderLine.getQuantity());
		} else if (orderLine.getProduct().getSku() == "B") {
			return new QuantityBasedPricingStrategy(prodBThreshold, 
					orderLine.getProduct().getPrice(), orderLine.getQuantity());
		} else {
			return new DefaultPricingStrategy(
					orderLine.getProduct().getPrice(), orderLine.getQuantity());
		}
		
	}
}
