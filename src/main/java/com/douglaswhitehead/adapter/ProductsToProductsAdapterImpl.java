package com.douglaswhitehead.adapter;

import org.springframework.stereotype.Component;

import com.douglaswhitehead.model.digitaldata.common.AttributesImpl;
import com.douglaswhitehead.model.digitaldata.common.CategoryImpl;
import com.douglaswhitehead.model.digitaldata.product.Product;
import com.douglaswhitehead.model.digitaldata.product.ProductImpl;
import com.douglaswhitehead.model.digitaldata.product.ProductInfoImpl;

/**
 * Example adapter class that adapts a demo retail store's products
 * into a CEDDL Products array.
 * 
 * @author Douglas.Whitehead
 *
 */
@Component
public class ProductsToProductsAdapterImpl implements ProductsToProductsAdapter {

	@Override
	public Product[] adapt(final com.douglaswhitehead.model.Product[] products) {
		
		// if products is null, be sure to return an empty CEDDL products array
		if (products == null) {
			return new ProductImpl[0];
		}
		
		// create CEDDL products array
		Product[] ceddlProducts = new Product[products.length];
		
		// for each product
		for (int p=0;p<products.length;p++) {
			com.douglaswhitehead.model.Product product = products[p];
			Product ceddlProduct = new ProductImpl.Builder() // create a new CEDDL product
					.productInfo(new ProductInfoImpl.Builder()
							.productID(String.valueOf(product.getId()))
							.productName(product.getName())
							.description(product.getDescription())
							.productURL(product.getProductUrl())
							.productImage(product.getImageUrl())
							.productThumbnail(product.getThumbnailUrl())
							.manufacturer(product.getManufacturer())
							.sku(product.getSku())
							.color(product.getColor())
							.size(product.getSize())
						.build())
					.category(new CategoryImpl.Builder()
							.primaryCategory(product.getCategory())
						.build())
					.linkedProduct(new ProductImpl[0]) // not applicable, no linked products in our demo retail store, but we want to return an 
													   // empty product[] array
					.attributes(new AttributesImpl.Builder().build()) // empty attributes object
				.build();
			
			ceddlProducts[p] = ceddlProduct; // assign CEDDL product to array
		}
		
		// return array
		return ceddlProducts;
	}

}
