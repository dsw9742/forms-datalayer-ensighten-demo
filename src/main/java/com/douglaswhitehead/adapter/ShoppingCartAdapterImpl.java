package com.douglaswhitehead.adapter;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.douglaswhitehead.model.ShoppingCart;
import com.douglaswhitehead.model.ShoppingCartItem;
import com.douglaswhitehead.model.digitaldata.cart.Cart;
import com.douglaswhitehead.model.digitaldata.cart.PriceImpl;
import com.douglaswhitehead.model.digitaldata.cart.CartImpl;
import com.douglaswhitehead.model.digitaldata.common.AttributesImpl;
import com.douglaswhitehead.model.digitaldata.common.CategoryImpl;
import com.douglaswhitehead.model.digitaldata.common.Item;
import com.douglaswhitehead.model.digitaldata.common.ItemImpl;
import com.douglaswhitehead.model.digitaldata.product.ProductImpl;
import com.douglaswhitehead.model.digitaldata.product.ProductInfoImpl;

/**
 * Example adapter class that adapts a demo retail store's shopping cart
 * into a CEDDL Cart object.
 * 
 * @author Douglas.Whitehead
 *
 */
@Component
public class ShoppingCartAdapterImpl implements ShoppingCartAdapter {

	@Override
	public Cart adapt(ShoppingCart cart) {
		// if shopping cart is null, be sure to return an empty CEDDL cart object
		if (cart == null) {
			return new CartImpl.Builder().build();
		}
		
		// build CEDDL items for CEDDL cart
		Item[] items = new ItemImpl[cart.getCartItems().size()];
		for (int i=0;i<cart.getCartItems().size(); i++) {
			ShoppingCartItem shoppingCartItem = cart.getCartItems().get(i);
			Item item = new ItemImpl.Builder()
					.productInfo(new ProductInfoImpl.Builder()
							.productID(String.valueOf(shoppingCartItem.getId()))
							.productName(shoppingCartItem.getName())
							.description(shoppingCartItem.getDescription())
							.productURL(shoppingCartItem.getProductUrl())
							.productImage(shoppingCartItem.getImageUrl())
							.productThumbnail(shoppingCartItem.getThumbnailUrl())
							.manufacturer(shoppingCartItem.getManufacturer())
							.sku(shoppingCartItem.getSku())
							.color(shoppingCartItem.getColor())
							.size(shoppingCartItem.getSize())
						.build())
					.category(new CategoryImpl.Builder()
							.primaryCategory(shoppingCartItem.getCategory())
						.build())
					.quantity(shoppingCartItem.getQuantity())
					.price(new PriceImpl.Builder()
							.basePrice(shoppingCartItem.getPrice())
							//.voucherCode() // not applicable, no voucher code at individual item level in our demo retail store
							//.voucherDiscount() // not applicable, no voucher code at individual item level in our demo retail store
							//.currency() // not applicable, currency used should be same for all items in cart
							.taxRate(cart.getTaxRate()) // using same tax rate as from cart assumes no items require separate tax rate (e.g. alcohol) 
							//.shipping() // calculated at time of transaction
							//.shippingMethod() // calculated at time of transaction
							.priceWithTax(shoppingCartItem.getPrice() // base price
									.add(shoppingCartItem.getPrice().multiply(cart.getTaxRate()))) // plus taxes
							//.cartTotal() // not applicable to price of individual items
						.build())
					.linkedProduct(new ProductImpl[0]) // not applicable, no linked products in our demo retail store, but we want to return an 
													   // empty product[] array
					.attributes(new AttributesImpl.Builder().build()) // empty attributes object
				.build();
			items[i] = item;
		}
		
		// build CEDDL cart
		return new CartImpl.Builder()
			.cartID(cart.getId().toString())
			.attributes(new AttributesImpl.Builder().build()) // empty attributes object
			.item(items) // get items built above
			.price(new PriceImpl.Builder()
					.basePrice(cart.getBasePrice())
					.voucherCode(cart.getVoucherCode())
					.voucherDiscount(cart.getVoucherDiscount())
					.currency(cart.getCurrency())
					.taxRate(cart.getTaxRate())
					.shipping(cart.getShipping())
					.shippingMethod(cart.getShippingMethod())
					.priceWithTax(cart.getPriceWithTax())
					.cartTotal(cart.getCartTotal())
				.build())
		.build();
	}

}