package com.douglaswhitehead.adapter;

import org.springframework.stereotype.Component;

import com.douglaswhitehead.model.digitaldata.privacy.Privacy;
import com.douglaswhitehead.model.digitaldata.privacy.PrivacyImpl;

/**
 * Example class with no adapt() method -- there is nothing to adapt in
 * our demo retail app -- but with a defaultPrivacy() method instead,
 * which builds the PrivacyImpl object that includes the CEDDL-required
 * "Default" Access Category
 * 
 * @author douglas whitehead
 *
 */
@Component
public class PrivacyAdapterImpl implements PrivacyAdapter {

	@Override
	public Privacy defaultPrivacy() {
		return new PrivacyImpl.Builder().build();
	}

}
