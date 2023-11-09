package entity;

import java.util.Random;
import main.GamePanel;

public class Cat extends Entity{
	public Cat(GamePanel gp) {
		super(gp);
		direction = "left";
		speed = 1;
		
		getImage();
	}
	
	public void getImage() {
		//MOVE UP
		up1 = setup("/cat/CatUp1");
		up2 = setup("/cat/CatUp2");
		up3 = setup("/cat/CatUp3");
		
		//MOVE DOWN
		down1 = setup("/cat/CatDown1");
		down2 = setup("/cat/CatDown2");
		down3 = setup("/cat/CatDown3");
		
		//MOVE LEFT
		left1 = setup("/cat/CatLeft1");
		left2 = setup("/cat/CatLeft2");
		left3 = setup("/cat/CatLeft3");
		
		//MOVE RIGHT
		right1 = setup("/cat/CatRight1");
		right2 = setup("/cat/CatRight2");
		right3 = setup("/cat/CatRight3");
	}
	
	public void setAction() {
		actionLockCounter++;
		if(actionLockCounter == 120) {
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
