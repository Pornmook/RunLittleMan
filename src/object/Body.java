package object;

import entity.Entity;
import main.GamePanel;

public class Body extends Entity{
	public Body(GamePanel gp) {
		super(gp);
		name = "Body";
		down1 = setup("/objects/Body");
		collision = true;		
	}
}
