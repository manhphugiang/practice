package week2_2Principle;

public class MyGame {

	public static void main(String[] args) {
		Player p1 = new Player(new Pistol());
		p1.setWeapon1(new Pistol());
		p1.setWeapon2(new Shotgun());
	}

}
