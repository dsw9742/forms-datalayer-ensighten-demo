package com.douglaswhitehead.adapter;

import com.douglaswhitehead.model.Order;
import com.douglaswhitehead.model.digitaldata.transaction.Transaction;

public interface OrderToTransactionAdapter {

	public Transaction adapt(Order order);
	
}
