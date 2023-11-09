package object;

import entity.Entity;
import main.GamePanel;

public class BedsideFront extends Entity{
	
	public BedsideFront(GamePanel gp) {
		super(gp);
		name = "BedsideFront";
		down1 = setup("/objects/BedsideFront");
		collision = true;	
	}
}
