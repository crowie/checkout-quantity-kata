package com.crow.checkout.domain;

import java.util.HashMap;
import java.util.Map;

import com.crow.checkout.pricing.PricingStrategy;
import com.crow.checkout.pricing.PricingStrategyFactory;

/**
 * ShoppingBasket class is responsible for adding and removing from basket and 
 * totalling costs.
 * 
 * @author Admin
 */
public class ShoppingBasket {
	
	private Map<String, OrderLine> basketItems = new HashMap<String, OrderLine>();

	public void addItem(OrderLine orderLineItem) {
		String lineItemSku = orderLineItem.getProduct().getSku();
		
		if (basketItems.containsKey(lineItemSku)) {
			OrderLine existingOrderLine = basketItems.get(lineItemSku);
			existingOrderLine.setQuantity(existingOrderLine.getQuantity() + 
					orderLineItem.getQuantity());
			basketItems.put(lineItemSku, existingOrderLine);
		} else {
			basketItems.put(orderLineItem.getProduct().getSku(), orderLineItem);
		}
	}

	public void removeItem(String sku, int quantity) {
		if (basketItems.containsKey(sku)) {
			OrderLine existingOrderLine = basketItems.get(sku);
			int existingQuantity = existingOrderLine.getQuantity();
			
			int newQuantity = (existingQuantity > quantity) ?
					existingQuantity - quantity : 0;
			
			if (newQuantity == 0) {
				basketItems.remove(sku);
			} else {
				existingOrderLine.setQuantity(newQuantity);
			}
		}
	}
	
	public int calculateBasketTotal() {
		int basketPrice = 0;
		for (OrderLine orderLine : basketItems.values()) {
			PricingStrategy strategy = PricingStrategyFactory
										.getProductPricingStrategy(orderLine);
			orderLine.setPricingStrategy(strategy);
			
			basketPrice += orderLine.getTotalPrice();
		}
		
		return basketPrice;
	}
	
	public int getNumberOfItems() {
		int numberOfItems = 0;
		
		for (OrderLine orderLine : basketItems.values()) {
			numberOfItems += orderLine.getQuantity();
		}
		return numberOfItems;
	}
	
}
