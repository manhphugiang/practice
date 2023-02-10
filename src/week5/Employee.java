class Employee {
	private String firstName;
	private String lastName;
	private String socialSecurityNumber;
	
	public Employee(String f, String l, String ssn){
		this.firstName = f;
		this.lastName = l;
		this.socialSecurityNumber = ssn;
	}
	
	public String getFirstName(){
		return this.firstName;
	}
	
	public String getLastName(){
		return this.lastName;
	}
	
	public String getSocialSecurityNumber(){
		return this.socialSecurityNumber;
	}
	
	public String toString(){
		return String.format("%s: %s %s %n %s: %s",
			"employee", getFirstName(), getLastName(),
			"social security number", getSocialSecurityNumber());
	}
}
