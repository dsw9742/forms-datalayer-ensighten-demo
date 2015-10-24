package com.douglaswhitehead.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.douglaswhitehead.model.User;

@Repository
public class UserRepositoryImpl implements UserRepository {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Override
	public UserDetails loadUserByUsername(final String username) {
		List<UserDetails> users = loadUsersByUsername(username);
		if (users.isEmpty()) {
			return null;
		}
		User user = (User)users.get(0);
		
		return new User(user.getId(), user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, getAuthorities(user.getId()));
	}
	
	protected List<UserDetails> loadUsersByUsername(String username) {
		String sql1 = "SELECT id, username, password, enabled " +
	            "FROM USERS WHERE USERNAME = ?";
		
        return jdbcTemplate.query(sql1, new String[] {username}, new RowMapper<UserDetails>() {
        	
            public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
            	UUID id = (UUID)rs.getObject(1);
                String username = rs.getString(2);
                String password = rs.getString(3);
                boolean enabled = rs.getBoolean(4);
                
                return new User(id, username, password, enabled, true, true, true, AuthorityUtils.NO_AUTHORITIES);
            }

        });
        
    }
	
	protected List<SimpleGrantedAuthority> getAuthorities(UUID id) {
		String sql1 = "SELECT r.id, r.name FROM ROLES AS r "
				+ "JOIN USERS_ROLES AS ur ON ur.role_id = r.id "
				+ "WHERE ur.user_id = ?";
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql1, new Object[]{id.toString()});
		for (Map<String, Object> row : rows) {
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority((String)row.get("NAME"));
			authorities.add(authority);			
		}
		
		return authorities;
	}

}
