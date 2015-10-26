package com.douglaswhitehead.adapter;

import org.springframework.stereotype.Component;

import com.douglaswhitehead.model.digitaldata.privacy.AccessCategory;
import com.douglaswhitehead.model.digitaldata.privacy.AccessCategoryImpl;
import com.douglaswhitehead.model.digitaldata.privacy.Privacy;
import com.douglaswhitehead.model.digitaldata.privacy.PrivacyImpl;

@Component
public class PrivacyAdapterImpl implements PrivacyAdapter {

	@Override
	public Privacy defaultPrivacy() {
		return new PrivacyImpl.Builder().accessCategories(new AccessCategory[]{
				new AccessCategoryImpl.Builder()
						.categoryName("Default")
						.domains(new String[]{"*"})
					.build()})
		.build();
	}

}
