package com.douglaswhitehead.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EnsightenManageConfigProperties {
	
	private String accountId;
	private String publishPath;

	@Autowired
	public EnsightenManageConfigProperties(@Value("${ensighten.manage.account-id}") String accountId, @Value("${ensighten.manage.publish-path}") String publishPath) {
		this.accountId = accountId;
		this.publishPath = publishPath;
	}
	
	public String getAccountId() {
		return accountId;
	}

	public String getPublishPath() {
		return publishPath;
	}
	
}
