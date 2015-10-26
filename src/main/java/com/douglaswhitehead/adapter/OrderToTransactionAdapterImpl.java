package com.douglaswhitehead.adapter;

import org.springframework.stereotype.Component;

import com.douglaswhitehead.model.Order;
import com.douglaswhitehead.model.digitaldata.transaction.Transaction;

@Component
public class OrderToTransactionAdapterImpl implements OrderToTransactionAdapter {

	@Override
	public Transaction adapt(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

}
