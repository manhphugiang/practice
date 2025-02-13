
package ca.sheridancollege.giangma.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.giangma.beans.Drink;
import ca.sheridancollege.giangma.beans.User;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class DrinkRepository {
	
	private NamedParameterJdbcTemplate jdbc;
	
	
	
	public void addDrink() {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "INSERT INTO easy_drinks VALUES ('Meow meow', 'cat', 5, 'red', 2, 'Scratch and Sniff')";
		jdbc.update(query, parameters);
	}
	
	public void addDrink(Drink drink) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "INSERT INTO easy_drinks(drink_name, main1, amount1, main2, amount2, directions) VALUES " +
		"(:na, :m1, :a1, :m2, :a2, :dir)";                         // instead of typing for all the value, we make a parameter to make the query add the value, and the below will be the advalue into the paramemters 
		parameters.addValue("na", drink.getDrinkName());
		parameters.addValue("m1", drink.getMain1());
		parameters.addValue("a1", drink.getAmount1());
		parameters.addValue("m2", drink.getMain2());
		parameters.addValue("a2", drink.getAmount2());
		parameters.addValue("dir", drink.getDirections());
		jdbc.update(query, parameters);
	}
/*	
	public ArrayList<Drink> getDrinks(){
	 ArrayList<Drink> drinks = new ArrayList<Drink>();
	
	MapSqlParameterSource parameters = new MapSqlParameterSource();
	String query = "SELECT * FROM easy_drinks";
	
	List<Map<String, Object>> rows =
	jdbc.queryForList(query, parameters);
	
	
	
	for (Map<String, Object> row: rows) {
		Drink d = new Drink();
		d.setDrinkName((String)row.get("drink_name"));
		d.setMain1((String)row.get("main1"));
		d.setAmount1((double)row.get("amount1"));
		d.setMain2((String)row.get("main2"));
		d.setAmount2((double)row.get("amount2"));
		d.setDirections((String)row.get("diretions"));
		drinks.add(d);
	}
	return drinks;
}
	
*/	
	
	public ArrayList<Drink> getDrinks2(){
		
		
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM easy_drinks";
		
		
		ArrayList<Drink> drinks =
		
		(ArrayList<Drink>) jdbc.query(query, parameters,
				new BeanPropertyRowMapper<Drink>(Drink.class));
				return drinks;
		
				
	
				}
		
		
	public Drink getDrinkById(int id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM easy_drinks WHERE id=:meow";
		parameters.addValue("meow", id);
		ArrayList<Drink> drinks =
				(ArrayList<Drink>)jdbc.query(query, parameters,
				new BeanPropertyRowMapper<Drink>(Drink.class));
		
				if (drinks.size()>0)
					return drinks.get(0);
				else
					return null;
		
		
	}
	public void editDrink(Drink drink) {
		MapSqlParameterSource parameters = new MapSqlParameterSource ();
		String query = " Update easy_drinks SET " 
				+ "drink_name= :na, main1=:m1, amount1=:a1, main2=:m2, "
				+ "amount2=:a2, directions=:dir WHERE id=:meow";
		parameters.addValue("meow", drink.getId());
		parameters.addValue("na", drink.getDrinkName());
		parameters.addValue("m1", drink.getMain1());
		parameters.addValue("a1", drink.getAmount1());
		parameters.addValue("m2", drink.getMain2());
		parameters.addValue("a2", drink.getAmount2());
		parameters.addValue("dir", drink.getDirections());
		jdbc.update(query, parameters);
	}

	public void deleteDrinkById(int id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource ();
		String query = " DELETE FROM easy_drinks WHERE id =:meow";
		parameters.addValue("meow", id);
		jdbc.update(query, parameters);
		
		
	}
	
	
	
	
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
