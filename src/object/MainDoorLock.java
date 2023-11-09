package object;

import entity.Entity;
import main.GamePanel;

public class MainDoorLock extends Entity{

	public MainDoorLock(GamePanel gp) {
		super(gp);
		name = "MainDoorLock";
		down1 = setup("/objects/MainDoorLock");
		collision = true;
	}
}
