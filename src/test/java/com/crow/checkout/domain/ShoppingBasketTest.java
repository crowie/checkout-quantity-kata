package com.crow.checkout.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ShoppingBasketTest {

	@Test
	public void simpleOrderLineAddsItemToBasket() {
		ShoppingBasket basket = new ShoppingBasket();
		Product productA = new Product("A", 10);
		
		OrderLine orderLineItem = new OrderLine(productA, 3);
		basket.addItem(orderLineItem);
		
		assertThat(basket.getNumberOfItems(), equalTo(3));
	}
	
	@Test
	public void givenTwoOfSameProductIncreasesQuantityOfThatProduct() {
		ShoppingBasket basket = new ShoppingBasket();
		Product productA = new Product("A", 10);
		
		OrderLine existingOrderLineItem = new OrderLine(productA, 3);
		OrderLine additionalOrderLineItem = new OrderLine(productA, 1);
		
		basket.addItem(existingOrderLineItem);
		basket.addItem(additionalOrderLineItem);
		
		assertThat(basket.getNumberOfItems(), equalTo(4));
	}
	
	@Test
	public void removeItemReducesItemsInBasket() {
		ShoppingBasket basket = new ShoppingBasket();
		Product productA = new Product("A", 10);
		
		OrderLine orderLineItem = new OrderLine(productA, 3);
		basket.addItem(orderLineItem);
		
		basket.removeItem(productA.getSku(), 2);
		
		assertThat(basket.getNumberOfItems(), equalTo(1));
	}
	
	@Test
	public void requestToRemoveMoreProductThanExistsResultsInEmptyBasket() {
		ShoppingBasket basket = new ShoppingBasket();
		Product productA = new Product("A", 10);
		
		OrderLine orderLineItem = new OrderLine(productA, 3);
		basket.addItem(orderLineItem);
		
		basket.removeItem(productA.getSku(), 15);
		
		assertThat(basket.getNumberOfItems(), equalTo(0));
	}
	
	@Test
	public void requestToRemoveAllOfAProductEmptiesBasket() {
		ShoppingBasket basket = new ShoppingBasket();
		Product productA = new Product("A", 10);
		
		OrderLine orderLineItem = new OrderLine(productA, 3);
		basket.addItem(orderLineItem);
		
		basket.removeItem(productA.getSku(), 3);
		
		assertThat(basket.getNumberOfItems(), equalTo(0));
	}
	
	/**
	 * This test tests real objects working together. It tests
	 * the combination of basket, orderLine, pricingStrategy factory
	 * and the pricing strategy
	 */
	@Test
	public void factoryForPricingReturnsTotalPriceUsingDiscount() {
		ShoppingBasket basket = new ShoppingBasket();
		Product productA = new Product("A", 50);
		
		OrderLine orderLineItem = new OrderLine(productA, 3);
		basket.addItem(orderLineItem);
		
		
		assertThat(basket.calculateBasketTotal(), equalTo(130));
	}
	
	/**
	 * Similar testing real objects above
	 */
	@Test
	public void factoryForPricingDetectsProductBReturnsTotalPriceUsingDiscount() {
		ShoppingBasket basket = new ShoppingBasket();
		Product productB = new Product("B", 30);
		
		OrderLine orderLineItem = new OrderLine(productB, 2);
		basket.addItem(orderLineItem);
		
		
		assertThat(basket.calculateBasketTotal(), equalTo(45));
	}
	
	@Test
	public void factoryForPricingDetectsStandardProductReturnsRegularTotalPrice() {
		ShoppingBasket basket = new ShoppingBasket();
		Product productC = new Product("C", 20);
		
		OrderLine orderLineItem = new OrderLine(productC, 2);
		basket.addItem(orderLineItem);
		
		
		assertThat(basket.calculateBasketTotal(), equalTo(40));
	}

}
