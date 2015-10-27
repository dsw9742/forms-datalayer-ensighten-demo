package com.douglaswhitehead.adapter;

import org.springframework.stereotype.Component;

import com.douglaswhitehead.model.digitaldata.common.AttributesImpl;
import com.douglaswhitehead.model.digitaldata.user.ProfileImpl;
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
		if (users == null) {
			return new UserImpl[0];
		}
		
		// create CEDDL users array
		User[] ceddlUsers = new User[users.length];
		
		// for each user
		for (int p=0;p<users.length;p++) {
			com.douglaswhitehead.model.User user = users[p];
			User ceddlUser = new UserImpl.Builder()
					.profile(new ProfileImpl[]{
						new ProfileImpl.Builder() // in our demo app, each user can only have one profile, so build just one profile here
								/*.profileInfo()
								.address()
								.shippingAddress()
								.social(new SocialImpl.Builder().build()) // empty social object
								.attributes(new AttributesImpl.Builder().build()) // empty attributes object*/
							.build()
					})
					.segment(new SegmentImpl.Builder().build()) // empty segment object
				.build();
			ceddlUsers[p] = ceddlUser; // assign CEDDL user to array
		}
		
		// return array
		return ceddlUsers;
	}

}
