package ca.sheridancollege.giangma.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.giangma.beans.User;


@Repository

public class SecurityRepository {
	
	@Autowired

	private NamedParameterJdbcTemplate jdbc;
	// we need to import the user from the beans package not the spring stuff framework
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
	
	
}