package com.douglaswhitehead.model;

import java.io.Serializable;

public class Payment implements Serializable {

	private static final long serialVersionUID = -3961010514438051666L;
	
	private String cardName;
	private long cardNumber;
	private int expiryMonth;
	private int expiryYear;
	private int cardCvv;
	
	public Payment(final String cardName, final long cardNumber, final int expiryMonth, final int expiryYear, final int cardCvv) {
		this.cardName = cardName;
		this.cardNumber = cardNumber;
		this.expiryMonth = expiryMonth;
		this.expiryYear = expiryYear;
		this.cardCvv = cardCvv;
	}

	/**
	 * @return the cardName
	 */
	public String getCardName() {
		return cardName;
	}

	/**
	 * @return the cardNumber
	 */
	public long getCardNumber() {
		return cardNumber;
	}

	/**
	 * @return the expiryMonth
	 */
	public int getExpiryMonth() {
		return expiryMonth;
	}
	
	/**
	 * @return the expiryYear
	 */
	public int getExpiryYear() {
		return expiryYear;
	}

	/**
	 * @return the cardCvv
	 */
	public int getCardCvv() {
		return cardCvv;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cardCvv;
		result = prime * result + ((cardName == null) ? 0 : cardName.hashCode());
		result = prime * result + (int) (cardNumber ^ (cardNumber >>> 32));
		result = prime * result + expiryMonth;
		result = prime * result + expiryYear;
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
		if (!(obj instanceof Payment))
			return false;
		Payment other = (Payment) obj;
		if (cardCvv != other.cardCvv)
			return false;
		if (cardName == null) {
			if (other.cardName != null)
				return false;
		} else if (!cardName.equals(other.cardName))
			return false;
		if (cardNumber != other.cardNumber)
			return false;
		if (expiryMonth != other.expiryMonth)
			return false;
		if (expiryYear != other.expiryYear)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Payment [cardName=");
		builder.append("[PROTECTED]");
		builder.append(", cardNumber=");
		builder.append("[PROTECTED]");
		builder.append(", expiryMonth=");
		builder.append("[PROTECTED]");
		builder.append(", expiryYear=");
		builder.append("[PROTECTED]");
		builder.append(", cardCvv=");
		builder.append("[PROTECTED]");
		builder.append("]");
		return builder.toString();
	}

}
