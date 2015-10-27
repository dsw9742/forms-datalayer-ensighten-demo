package com.douglaswhitehead.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Order implements Serializable {

	private static final long serialVersionUID = -2390370772181138879L;
	
	private UUID id;
	private BigDecimal basePrice;
	private String voucherCode;
	private BigDecimal voucherDiscount;
	private String currency;
	private BigDecimal taxRate;
	private BigDecimal shipping;
	private String shippingMethod;
	private BigDecimal priceWithTax;
	private BigDecimal orderTotal;
	private List<ShoppingCartItem> orderItems;
	private Address shippingAddress;
	
	public Order(final UUID id, final BigDecimal basePrice, final String voucherCode, final BigDecimal voucherDiscount,
			final String currency, final BigDecimal taxRate, final BigDecimal shipping, final String shippingMethod,
			final BigDecimal priceWithTax, final BigDecimal orderTotal, final List<ShoppingCartItem> orderItems, final
			Address shippingAddress) {
		this.id = id;
		this.basePrice = basePrice;
		this.voucherCode = voucherCode;
		this.voucherDiscount = voucherDiscount;
		this.currency = currency;
		this.taxRate = taxRate;
		this.shipping = shipping;
		this.shippingMethod = shippingMethod;
		this.priceWithTax = priceWithTax;
		this.orderTotal = orderTotal;
		this.orderItems = orderItems;
		this.shippingAddress = shippingAddress;
	}

	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(UUID id) {
		this.id = id;
	}

	/**
	 * @return the basePrice
	 */
	public BigDecimal getBasePrice() {
		return basePrice;
	}

	/**
	 * @param basePrice the basePrice to set
	 */
	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	/**
	 * @return the voucherCode
	 */
	public String getVoucherCode() {
		return voucherCode;
	}

	/**
	 * @param voucherCode the voucherCode to set
	 */
	public void setVoucherCode(String voucherCode) {
		this.voucherCode = voucherCode;
	}

	/**
	 * @return the voucherDiscount
	 */
	public BigDecimal getVoucherDiscount() {
		return voucherDiscount;
	}

	/**
	 * @param voucherDiscount the voucherDiscount to set
	 */
	public void setVoucherDiscount(BigDecimal voucherDiscount) {
		this.voucherDiscount = voucherDiscount;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return the taxRate
	 */
	public BigDecimal getTaxRate() {
		return taxRate;
	}

	/**
	 * @param taxRate the taxRate to set
	 */
	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	/**
	 * @return the shipping
	 */
	public BigDecimal getShipping() {
		return shipping;
	}

	/**
	 * @param shipping the shipping to set
	 */
	public void setShipping(BigDecimal shipping) {
		this.shipping = shipping;
	}

	/**
	 * @return the shippingMethod
	 */
	public String getShippingMethod() {
		return shippingMethod;
	}

	/**
	 * @param shippingMethod the shippingMethod to set
	 */
	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	/**
	 * @return the priceWithTax
	 */
	public BigDecimal getPriceWithTax() {
		return priceWithTax;
	}

	/**
	 * @param priceWithTax the priceWithTax to set
	 */
	public void setPriceWithTax(BigDecimal priceWithTax) {
		this.priceWithTax = priceWithTax;
	}

	/**
	 * @return the orderTotal
	 */
	public BigDecimal getOrderTotal() {
		return orderTotal;
	}

	/**
	 * @param orderTotal the orderTotal to set
	 */
	public void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal = orderTotal;
	}

	/**
	 * @return the orderItems
	 */
	public List<ShoppingCartItem> getOrderItems() {
		return orderItems;
	}

	/**
	 * @param orderItems the orderItems to set
	 */
	public void setOrderItems(List<ShoppingCartItem> orderItems) {
		this.orderItems = orderItems;
	}

	/**
	 * @return the shippingAddress
	 */
	public Address getShippingAddress() {
		return shippingAddress;
	}

	/**
	 * @param shippingAddress the shippingAddress to set
	 */
	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((basePrice == null) ? 0 : basePrice.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((orderItems == null) ? 0 : orderItems.hashCode());
		result = prime * result + ((orderTotal == null) ? 0 : orderTotal.hashCode());
		result = prime * result + ((priceWithTax == null) ? 0 : priceWithTax.hashCode());
		result = prime * result + ((shipping == null) ? 0 : shipping.hashCode());
		result = prime * result + ((shippingAddress == null) ? 0 : shippingAddress.hashCode());
		result = prime * result + ((shippingMethod == null) ? 0 : shippingMethod.hashCode());
		result = prime * result + ((taxRate == null) ? 0 : taxRate.hashCode());
		result = prime * result + ((voucherCode == null) ? 0 : voucherCode.hashCode());
		result = prime * result + ((voucherDiscount == null) ? 0 : voucherDiscount.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Order))
			return false;
		Order other = (Order) obj;
		if (basePrice == null) {
			if (other.basePrice != null)
				return false;
		} else if (!basePrice.equals(other.basePrice))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (orderItems == null) {
			if (other.orderItems != null)
				return false;
		} else if (!orderItems.equals(other.orderItems))
			return false;
		if (orderTotal == null) {
			if (other.orderTotal != null)
				return false;
		} else if (!orderTotal.equals(other.orderTotal))
			return false;
		if (priceWithTax == null) {
			if (other.priceWithTax != null)
				return false;
		} else if (!priceWithTax.equals(other.priceWithTax))
			return false;
		if (shipping == null) {
			if (other.shipping != null)
				return false;
		} else if (!shipping.equals(other.shipping))
			return false;
		if (shippingAddress == null) {
			if (other.shippingAddress != null)
				return false;
		} else if (!shippingAddress.equals(other.shippingAddress))
			return false;
		if (shippingMethod == null) {
			if (other.shippingMethod != null)
				return false;
		} else if (!shippingMethod.equals(other.shippingMethod))
			return false;
		if (taxRate == null) {
			if (other.taxRate != null)
				return false;
		} else if (!taxRate.equals(other.taxRate))
			return false;
		if (voucherCode == null) {
			if (other.voucherCode != null)
				return false;
		} else if (!voucherCode.equals(other.voucherCode))
			return false;
		if (voucherDiscount == null) {
			if (other.voucherDiscount != null)
				return false;
		} else if (!voucherDiscount.equals(other.voucherDiscount))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Order [id=");
		builder.append(id);
		builder.append(", basePrice=");
		builder.append(basePrice);
		builder.append(", voucherCode=");
		builder.append(voucherCode);
		builder.append(", voucherDiscount=");
		builder.append(voucherDiscount);
		builder.append(", currency=");
		builder.append(currency);
		builder.append(", taxRate=");
		builder.append(taxRate);
		builder.append(", shipping=");
		builder.append(shipping);
		builder.append(", shippingMethod=");
		builder.append(shippingMethod);
		builder.append(", priceWithTax=");
		builder.append(priceWithTax);
		builder.append(", orderTotal=");
		builder.append(orderTotal);
		builder.append(", orderItems=");
		builder.append(orderItems);
		builder.append(", shippingAddress=");
		builder.append(shippingAddress);
		builder.append("]");
		return builder.toString();
	}

}
