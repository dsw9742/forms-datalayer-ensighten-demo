package com.douglaswhitehead.model;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * DON'T ACTUALLY NEED THIS CLASS -- REMOVE IT. JUST NEED A JOIN-TABLE BETWEEN CART_ID AND PRODUCT_ID.
 * 
 * @author Douglas.Whitehead
 *
 */
public class CartProduct extends Product {

	private static final long serialVersionUID = 5720195375011996118L;
	
	private UUID cartId;
	
	public CartProduct(final UUID cartId, final long id, final String name, final String description, final String productUrl, final String imageUrl,
			final String thumbnailUrl, final String manufacturer, final String sku, final String color, final BigDecimal price, final String size,
			final String category) {
		super(id, name, description, productUrl, imageUrl, thumbnailUrl, manufacturer, sku, color, price, size, category);
		this.cartId = cartId;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cartId == null) ? 0 : cartId.hashCode());
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
		if (!(obj instanceof CartProduct))
			return false;
		CartProduct other = (CartProduct) obj;
		if (cartId == null) {
			if (other.cartId != null)
				return false;
		} else if (!cartId.equals(other.cartId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CartProduct [cartId=");
		builder.append(cartId);
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
