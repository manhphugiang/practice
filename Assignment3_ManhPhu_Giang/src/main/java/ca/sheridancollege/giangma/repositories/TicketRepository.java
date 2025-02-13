package ca.sheridancollege.giangma.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.giangma.beans.Ticket;


@Repository
public class TicketRepository {
	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	
	
	
	public void addGuestTicket(Ticket ticket) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "INSERT INTO ticket (name, price, creditCardNumber, ticketType, email, cvv) VALUES " +
		"(:na, :p, :ccn, :tt, :email, :cvv)";                         
		parameters.addValue("na", ticket.getName());
		parameters.addValue("p", ticket.getPrice());
		parameters.addValue("ccn", ticket.getCreditCardNumber());
		parameters.addValue("tt", ticket.getTicketType());
		parameters.addValue("email", ticket.getEmail());
		parameters.addValue("cvv", ticket.getCvv());
		jdbc.update(query, parameters);
	}
	
	
	
	
	
	public ArrayList<Ticket>  viewAllTicket() {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM ticket order by name";

		ArrayList<Ticket> tickets =
				
		(ArrayList<Ticket>) jdbc.query(query, parameters,
				new BeanPropertyRowMapper<Ticket>(Ticket.class));
				return tickets;
		
				
	
				}
	public ArrayList<Ticket>  viewGuestTicket(String name) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM ticket where name = :name";
		parameters.addValue("name", name);

		ArrayList<Ticket> tickets =
				
		(ArrayList<Ticket>) jdbc.query(query, parameters,
				new BeanPropertyRowMapper<Ticket>(Ticket.class));
				return tickets;
				}
	
	public Double getSubtotal(String name) {
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    String query = "SELECT ROUND(SUM(price), 2) AS subtotal FROM ticket where name = :name";
	    parameters.addValue("name", name);

	    Double subtotal = jdbc.queryForObject(query, parameters, Double.class);

	    return subtotal;
	}

	public Double getTaxes(String name) {
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    String query = "SELECT ROUND(SUM(price * 0.13), 2) AS taxes FROM ticket where name = :name";
	    parameters.addValue("name", name);

	    Double taxes = jdbc.queryForObject(query, parameters, Double.class);

	    return taxes;
	}

	public Double getTotal(String name) {
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    String query = "SELECT (ROUND(SUM(price * 0.13), 2)+ROUND(SUM(price), 2))  AS taxes FROM ticket where name = :name";
	    parameters.addValue("name", name);

	    Double total = jdbc.queryForObject(query, parameters, Double.class);

	    return total;
	}
	
	
	
	public void deleteTicketById(int id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource ();
		String query = " DELETE FROM ticket WHERE id =:ticketID";
		parameters.addValue("ticketID", id);
		jdbc.update(query, parameters);
		
		
	}
	
	
	
	public Ticket getTicketById(int id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM ticket WHERE id=:ticketID";
		parameters.addValue("ticketID", id);
		ArrayList<Ticket> tickets =
				(ArrayList<Ticket>)jdbc.query(query, parameters,
				new BeanPropertyRowMapper<Ticket>(Ticket.class));
		
				if (tickets.size()>0)
					return tickets.get(0);
				else
					return null;
		
	}
	
	
	
	
	
    public List<Ticket> getTicketsByUsername(String username) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT * FROM ticket WHERE name = :username";
        parameters.addValue("username", username);

        return jdbc.query(query, parameters, new BeanPropertyRowMapper<>(Ticket.class));
    }
	
	
	public void editTicket(Ticket ticket) {
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    String query = "UPDATE ticket SET " +
	            "name = :na, price = :p, creditCardNumber = :ccn, ticketType = :tt, " +
	            "email = :email, cvv = :cvv WHERE id = :ticketID";

	    parameters.addValue("ticketID", ticket.getId());
	    parameters.addValue("na", ticket.getName());
	    parameters.addValue("p", ticket.getPrice());
	    parameters.addValue("ccn", ticket.getCreditCardNumber());
	    parameters.addValue("tt", ticket.getTicketType());
	    parameters.addValue("email", ticket.getEmail());
	    parameters.addValue("cvv", ticket.getCvv());

	    jdbc.update(query, parameters);
	}
	
	
    public List<String> getAllGuestUsernames() {

	    MapSqlParameterSource parameters = new MapSqlParameterSource();

    	String query = "SELECT DISTINCT SEC_User.userName "
    			+ "FROM SEC_User "
    			+ "JOIN USER_ROLE ur ON SEC_User.userId = ur.userId "
    			+ "JOIN SEC_ROLE r ON ur.roleId = r.roleId "
    			+ "WHERE r.roleName = 'ROLE_GUEST' "
    			+ "ORDER BY SEC_User.userName ; ";
    	
        return jdbc.queryForList(query, parameters, String.class);

    }
    
 
	
	}
	
	

