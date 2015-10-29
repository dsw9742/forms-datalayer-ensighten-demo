package com.douglaswhitehead.adapter;

import org.springframework.stereotype.Component;

import com.douglaswhitehead.model.digitaldata.common.AddressImpl;
import com.douglaswhitehead.model.digitaldata.common.AttributesImpl;
import com.douglaswhitehead.model.digitaldata.user.ProfileImpl;
import com.douglaswhitehead.model.digitaldata.user.ProfileInfoImpl;
import com.douglaswhitehead.model.digitaldata.user.SegmentImpl;
import com.douglaswhitehead.model.digitaldata.user.SocialImpl;
import com.douglaswhitehead.model.digitaldata.user.User;
import com.douglaswhitehead.model.digitaldata.user.UserImpl;

/**
 * Example adapter class that adapts a demo retail store's users
 * into a CEDDL Users array.
 * 
 * @author Douglas.Whitehead
 *
 */
@Component
public class UsersToUsersAdapterImpl implements UsersToUsersAdapter {

	@Override
	public User[] adapt(final com.douglaswhitehead.model.User[] users) {

		// if users is null, be sure to return an empty CEDDL users array
		if (users == null || users.length == 0) {
			return new UserImpl[0];
		}

		// create CEDDL users array
		User[] ceddlUsers = new UserImpl[users.length];
		
		// for each user
		for (int p=0;p<users.length;p++) {
			com.douglaswhitehead.model.User user = users[p];
			User ceddlUser = null;
			if (user == null) {
				return new UserImpl[0]; // if user is null, return empty CEDDL user array
			} else {
				ceddlUser = new UserImpl.Builder()
					.profile(new ProfileImpl[]{
						new ProfileImpl.Builder() // in our demo app, each user can only have one profile, so build just one profile here
								.profileInfo(new ProfileInfoImpl.Builder()
										.profileID(user.getId().toString())
										.userName(user.getUsername())
										//.email() // empty, we opt not to use this in our demo retail app
										//.language() // empty, we opt not to use this in our demo retail app
										//.returningStatus() // empty, we opt not to use this in our demo retail app
										//.type() // empty, we opt not to use this in our demo retail app
									.build())
								.address(new AddressImpl.Builder().build()) // empty address object, we do not require this of nor 
																			// persist this from our demo retail app users 
								.shippingAddress(new AddressImpl.Builder().build()) // empty address object, we do not persist this 
																					// from our demo retail app users
								.social(new SocialImpl.Builder().build()) // empty social object, we opt not to use this in our demo
																		  // retail app
								.attributes(new AttributesImpl.Builder().build()) // empty attributes object, we opt not to use this
																				  // in our demo retail app
							.build()
					})
					.segment(new SegmentImpl.Builder().build()) // empty segment object, we opt not to use this in our demo retail app
				.build();
			}
			ceddlUsers[p] = ceddlUser; // assign CEDDL user to array
		}
		
		// return array
		return ceddlUsers;
	}

}
