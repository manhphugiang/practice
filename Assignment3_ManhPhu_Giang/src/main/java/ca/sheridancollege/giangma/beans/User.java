package ca.sheridancollege.giangma.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
	private Long userID;
	private String userName;
	private String encryptedPassword;
}
