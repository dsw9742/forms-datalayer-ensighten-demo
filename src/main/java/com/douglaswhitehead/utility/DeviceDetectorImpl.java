package com.douglaswhitehead.utility;

import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Component;

@Component
public class DeviceDetectorImpl implements DeviceDetector {
	
	public String detect(final Device device) {
		if (device.isMobile()) {
			return "mobile";
		}
		if (device.isTablet()) {
			return "tablet";
		}
		return "desktop";
	}
	
}
