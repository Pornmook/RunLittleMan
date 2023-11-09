package object;

import entity.Entity;
import main.GamePanel;

public class MainDoor extends Entity{
	
	public MainDoor(GamePanel gp) {
		super(gp);
		name = "MainDoor";
		down1 = setup("/objects/MainDoor");
		collision = true;
	}
}
