package object;

import entity.Entity;
import main.GamePanel;

public class Crowbar extends Entity{

	public Crowbar(GamePanel gp) {
		super(gp);
		name = "Crowbar";
		down1 = setup("/objects/Crowbar");
	}
}
