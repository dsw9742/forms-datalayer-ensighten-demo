package com.douglaswhitehead.adapter;

import org.springframework.stereotype.Component;

import com.douglaswhitehead.model.Order;
import com.douglaswhitehead.model.ShoppingCartItem;
import com.douglaswhitehead.model.digitaldata.transaction.TotalImpl;
import com.douglaswhitehead.model.digitaldata.transaction.TransactionImpl;
import com.douglaswhitehead.model.digitaldata.user.ProfileImpl;
import com.douglaswhitehead.model.digitaldata.user.ProfileInfoImpl;
import com.douglaswhitehead.model.digitaldata.cart.PriceImpl;
import com.douglaswhitehead.model.digitaldata.common.AttributesImpl;
import com.douglaswhitehead.model.digitaldata.common.CategoryImpl;
import com.douglaswhitehead.model.digitaldata.common.Item;
import com.douglaswhitehead.model.digitaldata.common.ItemImpl;
import com.douglaswhitehead.model.digitaldata.product.ProductImpl;
import com.douglaswhitehead.model.digitaldata.product.ProductInfoImpl;
import com.douglaswhitehead.model.digitaldata.transaction.Transaction;

/**
 * Example adapter class that adapts a demo retail store's order
 * into a CEDDL Transaction object.
 * 
 * @author Douglas.Whitehead
 *
 */
@Component
public class OrderToTransactionAdapterImpl implements OrderToTransactionAdapter {

	@Override
	public Transaction adapt(Order order) {

		// if order is null, be sure to return an empty CEDDL transaction object
		if (order == null) {
			return new TransactionImpl.Builder().build();
		}
		
		// build CEDDL items for CEDDL transaction
		Item[] items = new ItemImpl[order.getOrderItems().size()];
		for (int i=0;i<order.getOrderItems().size(); i++) {
			ShoppingCartItem shoppingTransactionItem = order.getOrderItems().get(i);
			Item item = new ItemImpl.Builder()
					.productInfo(new ProductInfoImpl.Builder()
							.productID(String.valueOf(shoppingTransactionItem.getId()))
							.productName(shoppingTransactionItem.getName())
							.description(shoppingTransactionItem.getDescription())
							.productURL(shoppingTransactionItem.getProductUrl())
							.productImage(shoppingTransactionItem.getImageUrl())
							.productThumbnail(shoppingTransactionItem.getThumbnailUrl())
							.manufacturer(shoppingTransactionItem.getManufacturer())
							.sku(shoppingTransactionItem.getSku())
							.color(shoppingTransactionItem.getColor())
							.size(shoppingTransactionItem.getSize())
						.build())
					.category(new CategoryImpl.Builder()
							.primaryCategory(shoppingTransactionItem.getCategory())
						.build())
					.quantity(shoppingTransactionItem.getQuantity())
					.price(new PriceImpl.Builder()
							.basePrice(shoppingTransactionItem.getPrice())
							//.voucherCode() // not applicable, no voucher code at individual item level in our demo retail store
							//.voucherDiscount() // not applicable, no voucher code at individual item level in our demo retail store
							//.currency() // not applicable, currency used should be same for all items in order
							.taxRate(order.getTaxRate()) // using same tax rate as from order assumes no items require separate tax rate (e.g. alcohol) 
							//.shipping() // calculated at time of transaction
							//.shippingMethod() // calculated at time of transaction
							.priceWithTax(shoppingTransactionItem.getPrice() // base price
									.add(shoppingTransactionItem.getPrice().multiply(order.getTaxRate()))) // plus taxes
							//.orderTotal() // not applicable to price of individual items
						.build())
					.linkedProduct(new ProductImpl[0]) // not applicable, no linked products in our demo retail store, but we want to return an 
													   // empty product[] array
					.attributes(new AttributesImpl.Builder().build()) // empty attributes object
				.build();
			items[i] = item;
		}
		
		// build and return CEDDL order
		return new TransactionImpl.Builder()
			.transactionID(order.getId().toString())
			.profile(new ProfileImpl.Builder()
					/*.profileInfo(new ProfileInfoImpl.Builder()
							.profileID()
							.userName()
							.email()
							.language()
							.returningStatus()
							.type()
						.build())
					.address()
					.shippingAddress()
					.social()
					.attributes()*/
				.build())
			.attributes(new AttributesImpl.Builder().build()) // empty attributes object
			.item(items) // get items built above
			.total(new TotalImpl.Builder()
					.basePrice(order.getBasePrice())
					.voucherCode(order.getVoucherCode())
					.voucherDiscount(order.getVoucherDiscount())
					.currency(order.getCurrency())
					.taxRate(order.getTaxRate())
					.shipping(order.getShipping())
					.shippingMethod(order.getShippingMethod())
					.priceWithTax(order.getPriceWithTax())
					.transactionTotal(order.getOrderTotal())
				.build())
		.build();
	}

}
