package ca.sheridancollege.giangma.repositories;

import java.util.ArrayList;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.giangma.beans.Cupcake;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class CupcakeRepository {
	private NamedParameterJdbcTemplate jdbc;

	public void addCupcake(Cupcake cupcake) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "INSERT INTO cupcake(cupcake_name, price, quantity) VALUES " +
		"(:name, :price, :quantity)";                        
		parameters.addValue("name", cupcake.getCupcakeName());
		parameters.addValue("price", cupcake.getPrice());
		parameters.addValue("quantity", cupcake.getQuantity());

		jdbc.update(query, parameters);
	
	}
	public ArrayList<Cupcake> getCupcake(){
		
		
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM cupcake";
		
		
		ArrayList<Cupcake> cupcakes =
		
		(ArrayList<Cupcake>) jdbc.query(query, parameters,
				new BeanPropertyRowMapper<Cupcake>(Cupcake.class));
				return cupcakes;
}
	
	
	public void deleteCupcakeById(int id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource ();
		String query = " DELETE FROM cupcake WHERE id =:cupcakeID";
		parameters.addValue("cupcakeID", id);
		jdbc.update(query, parameters);
	}
	
	public void purchaseCupcakeById(int id) {
		
		MapSqlParameterSource parameters = new MapSqlParameterSource ();
		String query = " Update cupcake"
				+ " SET quantity = quantity -1 WHERE id =:cupcakeID";
		parameters.addValue("cupcakeID", id);
		jdbc.update(query, parameters);
	}
	
}
