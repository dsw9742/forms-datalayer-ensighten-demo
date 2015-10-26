package com.douglaswhitehead.datalayer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.douglaswhitehead.model.ShoppingCart;
import com.douglaswhitehead.model.User;
import com.douglaswhitehead.model.digitaldata.DigitalData;
import com.douglaswhitehead.model.digitaldata.DigitalDataImpl;
import com.douglaswhitehead.model.digitaldata.common.AttributesImpl;
import com.douglaswhitehead.model.digitaldata.common.CategoryImpl;
import com.douglaswhitehead.model.digitaldata.component.ComponentImpl;
import com.douglaswhitehead.model.digitaldata.event.EventImpl;
import com.douglaswhitehead.model.digitaldata.page.Page;
import com.douglaswhitehead.model.digitaldata.page.PageImpl;
import com.douglaswhitehead.model.digitaldata.page.PageInfoImpl;

@Component
public class IndexDataLayerImpl extends AbstractDataLayer implements IndexDataLayer {

	@Override
	public DigitalData index(final HttpServletRequest request, final HttpServletResponse response, 
			final Device device, final Model model, final ShoppingCart cart, final User user) {
		return new DigitalDataImpl.Builder()
			.pageInstanceID("index-prod")
			.page(indexPageAdapter(request, device))
			.product(productsAdapter.adapt(null))
			.cart(cartAdapter.adapt(cart))
			.transaction(orderAdapter.adapt(null))
			.event(new EventImpl[0])
			.component(new ComponentImpl[0])
			.user(usersAdapter.adapt(new User[]{user}))
			.privacy(privacyAdapter.defaultPrivacy())
			.version(VERSION)
		.build();
	}
	
	private Page indexPageAdapter(final HttpServletRequest request, final Device device) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyy");
		Date issueDate = null;
		Date effectiveDate = null;
		Date expiryDate = null;
		
		try {
			issueDate = simpleDateFormat.parse("12/28/2001");
			effectiveDate = simpleDateFormat.parse("12/29/2001");
			expiryDate = simpleDateFormat.parse("12/30/2001");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return new PageImpl.Builder()
			.pageInfo(new PageInfoImpl.Builder()
					.pageID("index")
					.pageName("index")
					.destinationURL("http://localhost/") // doesn't work well server-side, plan to overwrite this value from client side
					.referringURL(request.getHeader("referer")) // doesn't work well server-side, plan to overwrite this value from client side
					.sysEnv(detector.detect(device))
					.variant("1")
					.version("1.0")
					.breadcrumbs(new String[]{}) // doesn't work well server-side, plan to overwrite this value from client side
					.author("Test McGee").security(new String[]{"Analytics"})
					.issueDate(issueDate)
					.effectiveDate(effectiveDate)
					.expiryDate(expiryDate)
					.language("en-US")
					.geoRegion("US")
					.industryCodes("")
					.publisher("Shirley J Tester").security(new String[]{"Analytics"})
				.build())
			.category(new CategoryImpl.Builder()
					.primaryCategory("index")
				.build())
			.attributes(new AttributesImpl.Builder()
				.build())
		.build();
	}
	
}