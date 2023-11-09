package zombie;

import java.util.Random;
import entity.Entity;
import main.GamePanel;

public class Zombie extends Entity{

	public Zombie(GamePanel gp) {
		super(gp);
		
		type = 2;
		name = "Zombie";
		speed = 1;
		maxLife = 4;
		life = maxLife;
		
		Hitbox.x = 8;
		Hitbox.y = 16;
		Hitbox.width = 26;
		Hitbox.height = 26;
		HitboxDefaultX = Hitbox.x;
		HitboxDefaultY = Hitbox.y;
		
		getImage();

	}
	
	public void getImage() {
		up1 = setup("/zombie/ZomUp1");
		up2 = setup("/zombie/ZomUp2");
		up3 = setup("/zombie/ZomUp3");
		
		down1 = setup("/zombie/ZomDown1");
		down2 = setup("/zombie/ZomDown2");
		down3 = setup("/zombie/ZomDown3");
		
		left1 = setup("/zombie/ZomLeft1");
		left2 = setup("/zombie/ZomLeft2");
		left3 = setup("/zombie/ZomLeft3");
		
		right1 = setup("/zombie/ZomRight1");
		right2 = setup("/zombie/ZomRight2");
		right3 = setup("/zombie/ZomRight3");
	}
	
	public void update() {
		super.update();
		int xDistance = Math.abs(x - gp.player.x);
		int yDistance = Math.abs(y - gp.player.y);
		int tileDistance = (xDistance + yDistance)/gp.tileSize;
		
		if(onPath == false && tileDistance < 5) {
			int i = new Random().nextInt(100)+1;
			if(i > 50) {
				onPath = true;
			}
		}
		if(onPath == true && tileDistance > 5) {
			onPath = false;
		}
	}
	
	public void setAction() {
		if(onPath == true) {
			int goalCol = (gp.player.x + gp.player.Hitbox.x)/gp.tileSize;
			int goalRow = (gp.player.y + gp.player.Hitbox.y)/gp.tileSize;
			
			searchPath(goalCol, goalRow);
			
		}else {
			actionLockCounter++;
			if(actionLockCounter == 60) {
				Random random = new Random();
				int i = random.nextInt(100)+1;
				
				if(i <= 25) {
					direction = "up";
				}
				if(i > 25 && i <= 50) {
					direction = "down";
				}
				if(i > 50 && i <= 75) {
					direction = "left";
				}
				if(i > 75 && i <= 100) {
					direction = "right";
				}
				actionLockCounter = 0;
			}
		}
	}
}

