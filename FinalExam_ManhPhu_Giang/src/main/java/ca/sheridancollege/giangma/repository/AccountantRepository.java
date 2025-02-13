package ca.sheridancollege.giangma.repository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.giangma.beans.Accountant;
@Repository
public class AccountantRepository {
	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	
	
	
	public void addAccountant( Accountant accountant) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "INSERT INTO accountantTable (name, salary, yearsExperience) VALUES " +
		"(:na, :salary, :yEx)";                         
		parameters.addValue("na", accountant.getName());
		parameters.addValue("salary", accountant.getSalary());
		parameters.addValue("yEx", accountant.getYearsExperience());

		jdbc.update(query, parameters);
	}
	
	
	public ArrayList<Accountant> getAccountant(){
		
		
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM accountantTable";
		
		
		ArrayList<Accountant> accountants =
		
		(ArrayList<Accountant>) jdbc.query(query, parameters,
				new BeanPropertyRowMapper<Accountant>(Accountant.class));
				return accountants;
}
	
	
	
	public void deleteAccountantById(int id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource ();
		String query = " DELETE FROM accountantTable WHERE id =:accountantID";
		parameters.addValue("accountantID", id);
		jdbc.update(query, parameters);
	}
	
	
	public Accountant getAccountantById(int id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM accountantTable WHERE id=:accountantId";
		parameters.addValue("accountantId", id);
		ArrayList<Accountant> accountants =
				(ArrayList<Accountant>)jdbc.query(query, parameters,
				new BeanPropertyRowMapper<Accountant>(Accountant.class));
		
				if (accountants.size()>0)
					return accountants.get(0);
				else
					return null;
	}
	

}
