package object;

import entity.Entity;
import main.GamePanel;

public class Lockpick extends Entity{

	public Lockpick(GamePanel gp) {
		super(gp);
		name = "Lockpick";
		down1 = setup("/objects/Lockpick");	
	}
}
