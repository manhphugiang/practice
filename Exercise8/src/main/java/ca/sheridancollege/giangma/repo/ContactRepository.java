package ca.sheridancollege.giangma.repo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.giangma.beans.Contacts;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class ContactRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbc;

public void addContact(Contacts contact) {
	MapSqlParameterSource parameters = new MapSqlParameterSource();
	String query = "INSERT INTO contactsData(name, address, phoneNumber, email, role) VALUES " +
	"(:name, :address, :phoneNumber, :email, :role)";
	parameters.addValue("name", contact.getName());
	parameters.addValue("address", contact.getAddress());
	parameters.addValue("phoneNumber", contact.getPhoneNumber());
	parameters.addValue("email", contact.getEmail());
	parameters.addValue("role", contact.getRole());
	
	jdbc.update(query, parameters);
}

public ArrayList<Contacts> getContact(){
	MapSqlParameterSource parameters = new MapSqlParameterSource();
	String query = "SELECT * FROM contactsData";
	
	ArrayList<Contacts> contacts =
	
	(ArrayList<Contacts>) jdbc.query(query, parameters,
			new BeanPropertyRowMapper<Contacts>(Contacts.class));
			return contacts;
}

			

public Contacts getContactById(int id) {
	MapSqlParameterSource parameters = new MapSqlParameterSource();
	String query = "SELECT * FROM ContactsData WHERE id=:meow";
	parameters.addValue("meow", id);
	ArrayList<Contacts> contacts =
			(ArrayList<Contacts>)jdbc.query(query, parameters,
			new BeanPropertyRowMapper<Contacts>(Contacts.class));
	
			if (contacts.size()>0)
				return contacts.get(0);
			else
				return null;
	
	
}
public void editContact(Contacts contact) {
	MapSqlParameterSource parameters = new MapSqlParameterSource ();
	String query = " Update contactsData SET " 
			+ "name= :na, address=:ad, phoneNumber=:p, email=:e WHERE id=:meow";
	parameters.addValue("meow", contact.getId());
	parameters.addValue("na", contact.getName());
	parameters.addValue("ad", contact.getAddress());
	parameters.addValue("p", contact.getPhoneNumber());
	parameters.addValue("e", contact.getEmail());
	jdbc.update(query, parameters);
	
	
	
	
}

public void deleteContactById(int id) {
	MapSqlParameterSource parameters = new MapSqlParameterSource ();
	String query = " DELETE FROM contactsData WHERE id =:meow";
	parameters.addValue("meow", id);
	jdbc.update(query, parameters);
	
	

}

public ArrayList<Contacts> getGuestContacts(){
	MapSqlParameterSource parameters = new MapSqlParameterSource ();
	String query = "SELECT * FROM CONTACTSDATA  acts where role = 'guest'";
	
	ArrayList<Contacts> contacts =
			
	(ArrayList<Contacts>) jdbc.query(query, parameters,
			new BeanPropertyRowMapper<Contacts>(Contacts.class));
			return contacts;
	
	
}
	public ArrayList<Contacts> getMembersContacts(){
		MapSqlParameterSource parameters = new MapSqlParameterSource ();
	String query = "SELECT * FROM CONTACTSDATA  where role = 'member'";
	
	
	ArrayList<Contacts> contacts =
			
	(ArrayList<Contacts>) jdbc.query(query, parameters,
			new BeanPropertyRowMapper<Contacts>(Contacts.class));
			return contacts;
		}
	
	
	
	
	
	public ArrayList<Contacts> getAdminContacts(){
		MapSqlParameterSource parameters = new MapSqlParameterSource ();
	String query = "SELECT * FROM CONTACTSDATA  where role = 'admin'";
	
	ArrayList<Contacts> contacts =
			
	(ArrayList<Contacts>) jdbc.query(query, parameters,
			new BeanPropertyRowMapper<Contacts>(Contacts.class));
			return contacts;
			}



}
