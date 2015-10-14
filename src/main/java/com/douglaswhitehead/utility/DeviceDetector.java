package com.douglaswhitehead.utility;

import org.springframework.mobile.device.Device;

public interface DeviceDetector {
	
	public String detect(Device device);

}
