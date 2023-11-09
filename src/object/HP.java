package object;

import entity.Entity;
import main.GamePanel;

public class HP extends Entity {

	public HP(GamePanel gp) {
		super(gp);
		name = "HP";
		image = setup("/objects/HP1");
		image2 = setup("/objects/HP2");	
	}
}
