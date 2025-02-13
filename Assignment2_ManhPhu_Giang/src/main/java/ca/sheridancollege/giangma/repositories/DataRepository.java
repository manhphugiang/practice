package ca.sheridancollege.giangma.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.giangma.beans.Contacts;
import ca.sheridancollege.giangma.beans.Drink;
import ca.sheridancollege.giangma.product.beans.Product;
import ca.sheridancollege.giangma.store.beans.Store;
@Repository
public class DataRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	public void addStore(Store store) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		
		String query = "INSERT INTO store (storeName, location, numberEmployee, storeManager) VALUES " +
		"(:storeName, :location, :numberEmployee, :storeManager)"; 
		
	    parameters.addValue("storeName", store.getStoreName()); 
		parameters.addValue("location", store.getLocation());
		parameters.addValue("numberEmployee", store.getNumberEmployee());
		parameters.addValue("storeManager", store.getStoreManager());

		jdbc.update(query, parameters);
	}
	
	public void addProduct(Product product) {
	    MapSqlParameterSource parameters = new MapSqlParameterSource();


	    String insertProductQuery = "INSERT INTO product (productName, storeName, productNumber, price) " +
	    "VALUES (:productName, :storeName, :productNumber, :price)";

	    parameters.addValue("productName", product.getProductName());
	    parameters.addValue("storeName", product.getStoreName()); 
	    parameters.addValue("productNumber", product.getProductNumber());
	    parameters.addValue("price", product.getPrice());

	    jdbc.update(insertProductQuery, parameters);
	}

	
	public void editProduct(Product product) {
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    String query = "UPDATE product SET productName = :productName, storeName = :storeName, productNumber = :productNumber, price = :price WHERE id = :productid";
	    parameters.addValue("productid", product.getId());
	    parameters.addValue("productName", product.getProductName());
	    parameters.addValue("storeName", product.getStoreName());
	    parameters.addValue("productNumber", product.getProductNumber());
	    parameters.addValue("price", product.getPrice());
	    jdbc.update(query, parameters);
	}

	
	public List<String>  getAllStoreNames() {
			String query = "SELECT storeName FROM store";
			
	        List<String> storeNames = jdbc.queryForList(query, new MapSqlParameterSource(), String.class);
			return storeNames;
			}	
	
	public Product getProductById(int id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM product WHERE id=:productid";
		parameters.addValue("productid", id);
		ArrayList<Product> products =
				(ArrayList<Product>)jdbc.query(query, parameters,
				new BeanPropertyRowMapper<Product>(Product.class));
		
				if (products.size()>0)
					return products.get(0);
				else
					return null;
	}
	
	public Store getDataForStore(String selectedStoreName) {
	    MapSqlParameterSource parameters = new MapSqlParameterSource();

	    String query = "SELECT * "
	            + "FROM store "
	            + "WHERE storeName = :selectedStoreName";
	    parameters.addValue("selectedStoreName", selectedStoreName);

	    ArrayList<Store> stores = (ArrayList<Store>) jdbc.query(query, parameters,
	            new BeanPropertyRowMapper<Store>(Store.class));

	    if (stores.size() > 0)
	        return stores.get(0);
	    else
	        return null;
	}

	public List<Product> getDataForProduct(String selectedStoreName) {
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    
	    String query = "SELECT * FROM product WHERE storeName = :selectedStoreName";
	    parameters.addValue("selectedStoreName", selectedStoreName);
	    
	    List<Product> products = jdbc.query(query, parameters, new BeanPropertyRowMapper<>(Product.class));
	    
	    return products;
	}

	public void deleteProductById(int id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource ();
		String query = " DELETE FROM product WHERE id =:productid";
		parameters.addValue("productid", id);
		jdbc.update(query, parameters);
		
	}

}
