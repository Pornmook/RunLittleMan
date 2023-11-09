package object;

import entity.Entity;
import main.GamePanel;

public class CellDoor extends Entity{
	
	public CellDoor(GamePanel gp) {
		super(gp);

		name = "CellDoor";
		down1 = setup("/objects/CellDoor");
		collision = true;
		
		Hitbox.x = 0;
		Hitbox.y = 0;
		Hitbox.width = 48;
		Hitbox.height = 32;
		HitboxDefaultX = Hitbox.x;
		HitboxDefaultY = Hitbox.y;
			
	}
}
