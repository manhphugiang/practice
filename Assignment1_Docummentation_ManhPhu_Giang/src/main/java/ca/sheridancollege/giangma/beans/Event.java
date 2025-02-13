package ca.sheridancollege.giangma.beans;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor 
@AllArgsConstructor 

@Data
public class Event {
private String firstname;
private String lastname;
private String nameHolder;
private int cardNumber;
private int cvv;
private boolean sms;
private boolean mail;
private String phoneNumber;
private String email;
private String ticketType;

public String getTicketType() {
    return ticketType;
}

public void setTicketType(String ticketType) {
    this.ticketType = ticketType;
}

}

