package com.douglaswhitehead.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public class ShoppingCartItem extends Product implements Serializable {

	private static final long serialVersionUID = 2760226980413838674L;
	
	private UUID cartId;
	private int quantity;

	public ShoppingCartItem(final long id, final String name, final String description, final String productUrl, final String imageUrl,
			final String thumbnailUrl, final String manufacturer, final String sku, final String color, final BigDecimal price, final String size,
			final String category, final int quantity, final UUID cartId) {
		super(id, name, description, productUrl, imageUrl, thumbnailUrl, manufacturer, sku, color, price, size, category);
		this.cartId = cartId;
		this.quantity = quantity;
	}

	/**
	 * @return the cartId
	 */
	public UUID getCartId() {
		return cartId;
	}

	/**
	 * @param cartId the cartId to set
	 */
	public void setCartId(UUID cartId) {
		this.cartId = cartId;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cartId == null) ? 0 : cartId.hashCode());
		result = prime * result + quantity;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof ShoppingCartItem))
			return false;
		ShoppingCartItem other = (ShoppingCartItem) obj;
		if (cartId == null) {
			if (other.cartId != null)
				return false;
		} else if (!cartId.equals(other.cartId))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ShoppingCartItem [cartId=");
		builder.append(cartId);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append(", getId()=");
		builder.append(getId());
		builder.append(", getName()=");
		builder.append(getName());
		builder.append(", getDescription()=");
		builder.append(getDescription());
		builder.append(", getProductUrl()=");
		builder.append(getProductUrl());
		builder.append(", getImageUrl()=");
		builder.append(getImageUrl());
		builder.append(", getThumbnailUrl()=");
		builder.append(getThumbnailUrl());
		builder.append(", getManufacturer()=");
		builder.append(getManufacturer());
		builder.append(", getSku()=");
		builder.append(getSku());
		builder.append(", getColor()=");
		builder.append(getColor());
		builder.append(", getSize()=");
		builder.append(getSize());
		builder.append(", getPrice()=");
		builder.append(getPrice());
		builder.append(", getCategory()=");
		builder.append(getCategory());
		builder.append("]");
		return builder.toString();
	}

}
