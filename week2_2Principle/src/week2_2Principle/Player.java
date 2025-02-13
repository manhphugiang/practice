package week2_2Principle;

public class Player {
	private Weapon primaryWeapon;
	// private Shotgun shotgun;
	
	
	private Weapon weapon1;
	private Weapon weapon2;
	
	public Player(Weapon weapon) { // dependency injection with the instructor
	//	primaryWeapon = new Pistol(); //dependency 
		
		primaryWeapon = weapon;
		
	}
	
	
	public void onLeftClick() {
		primaryWeapon.shoot();
	}
	
	public void onRightClick() {
		if (primaryWeapon instanceof SecondaryOption) {
		((SecondaryOption)primaryWeapon).secondaryFire();
}
	}
	public void setWeapon1(Weapon weapon) {
		this.weapon1 = weapon;
		}
	public void setWeapon2(Weapon weapon) {
		this.weapon2 = weapon;
		}
	public void num1Press() {
		this.primaryWeapon = weapon1;
		}
	public void num2Press() {
		this.primaryWeapon = weapon2; }
	
	}
	
	
}
