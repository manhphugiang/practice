package ca.sheridancollege.giangma.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.giangma.beans.User;


@Repository

public class SecurityRepository {
	
	@Autowired

	private NamedParameterJdbcTemplate jdbc;
	public User findUserByUserName(String username) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "Select * FROM SEC_USER WHERE userName=:un";
		parameters.addValue("un", username);
		
		ArrayList<User> users = 
				(ArrayList<User>) jdbc.query(query, parameters,
						new BeanPropertyRowMapper<User>(User.class));
		
		
		if (users.size() > 0)
			return users.get(0);
		return null;
	}
	
	public List<String> getRolesById(long userId){
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		
		
		String query = "SELECT user_role.userId, sec_role.roleName "
		+ "FROM user_role, sec_role WHERE "
		+ "user_role.roleId=sec_role.roleId and userId=:id";
		
		
		parameters.addValue("id", userId);
		
		
		ArrayList<String> roles = new ArrayList<String>();
		
		
		List<Map<String,Object>> rows= jdbc.queryForList(query, parameters);
		
		
		for (Map<String,Object> row : rows) {
		roles.add((String)row.get("roleName"));
		}
		return roles;
		}

	
	public void register(String username, String password) {
		
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String encodedPassword = encoder.encode(password);
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			String userPass = "INSERT INTO SEC_User(userName, encryptedPassword, ENABLED) "
					+ "VALUES " +
			"(:username, :password, 1)";                         // instead of typing for all the value, we make a parameter to make the query add the value, and the below will be the advalue into the paramemters 
			parameters.addValue("username", username);
			parameters.addValue("password", encodedPassword);

			jdbc.update(userPass, parameters);

	}
	
	public void assignRoles(long userId, long roleID ) {
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();

	
	String query = "INSERT INTO USER_ROLE(userId, roleID) "
			+ "VALUES " +
	"(:uid, :rid)";                         // instead of typing for all the value, we make a parameter to make the query add the value, and the below will be the advalue into the paramemters 
	parameters.addValue("uid", userId);
	parameters.addValue("rid", roleID);

	jdbc.update(query, parameters);

	
	}
		}

