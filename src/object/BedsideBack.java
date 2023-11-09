package object;

import entity.Entity;
import main.GamePanel;

public class BedsideBack extends Entity{
	
	public BedsideBack(GamePanel gp) {
		super(gp);
		name = "BedsideBack";
		down1 = setup("/objects/BedsideBack");
		collision = true;	
	}	
}
