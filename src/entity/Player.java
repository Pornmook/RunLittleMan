package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;
import main.Keyboard;

public class Player extends Entity{
	Keyboard Key;
	public final int screenX = 0;
	public final int screenY = 0;
	
	//PLAYER GOT ITEMS
	public int hasLockpick = 0;
	public int hasCrowbar = 0;
	
	public Player(GamePanel gp, Keyboard Key) {
		
		super(gp);
		this.Key = Key;
		
		Hitbox = new Rectangle();
		Hitbox.x = 8;
		Hitbox.y = 16;
		Hitbox.width = 26;
		Hitbox.height = 26;
		
		HitboxDefaultX = Hitbox.x;
		HitboxDefaultY = Hitbox.y;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	//private void Super(GamePanel gp2) {}

	public void setDefaultValues() {
		x = 100;
		y = 100;
		speed = 4;
		direction = "down";
		
		//PLAYER LIFE
		maxLife = 5;
		life = maxLife;
	}
	
	public void getPlayerImage() {
		//MOVE UP
		up1 = setup("/player/MoveForward1");
		up2 = setup("/player/MoveForward2");
		up3 = setup("/player/MoveForward3");
		
		//MOVE DOWN
		down1 = setup("/player/MoveBack1");
		down2 = setup("/player/MoveBack2");
		down3 = setup("/player/MoveBack3");
		
		//MOVE LEFT
		left1 = setup("/player/MoveLeft1");
		left2 = setup("/player/MoveLeft2");
		left3 = setup("/player/MoveLeft3");
		
		//MOVE RIGHT
		right1 = setup("/player/MoveRight1");
		right2 = setup("/player/MoveRight2");
		right3 = setup("/player/MoveRight3");
	}
	
	public void update() {
		if(Key.upPressed == true || Key.downPressed == true || Key.leftPressed == true || Key.rightPressed == true) {
			if(Key.upPressed == true) {
				direction = "up";
			}
			else if(Key.downPressed == true) {
				direction = "down";
			}
			else if(Key.leftPressed == true) {
				direction = "left";
			}
			else if(Key.rightPressed == true) {
				direction = "right";
			}
			
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			int objIndex = gp.cChecker.checkObject(this, true);
			pickupObject(objIndex);
			
			int catIndex = gp.cChecker.checkEntity(this, gp.cat);
			InteractCat(catIndex);
			
			int zombieIndex = gp.cChecker.checkEntity(this, gp.zombie);
			zombieAttack(zombieIndex);
			
			if(collisionOn == false) {
				switch(direction) {
				case "up": y -= speed; break;
				case "down": y += speed; break;
				case "left": x -= speed; break;
				case "right": x += speed; break;
				}
			}
			
			spriteCounter++;
			if(spriteCounter > 6) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 3;
				}
				else if(spriteNum == 3) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
		
		if(invincible == true) {
			invincibleCounter++;
			if(invincibleCounter > 60) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
		if(life <= 0) {
			gp.gameState = gp.gameoverState;
			hasLockpick = 0;
			hasCrowbar = 0;
		}
	}
	
	//---------------------------------------- PICK UP ITEMS ----------------------------------------
	public void pickupObject(int i) {
		
		if(i != 999) {
			String objectName = gp.obj[i].name;
			
			switch(objectName) {
			case "Lockpick":
				if(hasLockpick==0 && hasCrowbar==0) {
					hasLockpick++;
					gp.obj[i] = null;
					gp.ui.showNoti("I got lockpick.");
				}else {
					gp.ui.showNoti("My bag is full.");
				}
				break;
			case "CellDoor":	
				if(Key.enterPressed == true) {
					if(hasLockpick > 0) {
						gp.obj[i] = null;
						hasLockpick--;
						
						gp.ui.showNoti("Using lockpick.");
					}else {
						gp.ui.showNoti("I need lockpick!");
					}
				}
				break;
			case "Crowbar":
				hasCrowbar++;
				gp.obj[i] = null;
				gp.ui.showNoti("I got Crowbar.");
				
				break;
			case "MainDoor":
				if(Key.enterPressed == true) {
					if(hasCrowbar > 0) {
						gp.obj[i] = null;
						hasCrowbar--;
						gp.ui.showNoti("Using Crowbar.");
						
						gp.gameState = gp.gamefinishedState;
						//gp.ui.gameFinished = true;
						
					}else {
						gp.ui.showNoti("I need Crowbar!");
					}
				}
				break;
			case "MainDoorLock":
				if(Key.enterPressed == true) {
					if(hasCrowbar > 0) {
						gp.obj[i] = null;
						hasCrowbar--;
						gp.ui.showNoti("Using Crowbar.");
						
						gp.gameState = gp.gamefinishedState;
						//gp.ui.gameFinished = true;
						
					}else {
						gp.ui.showNoti("I need Crowbar!");
					}
				}
				break;
			case "BedsideFront":	
				if(Key.enterPressed == true) {
					if(hasLockpick <= 0) {
						if(i == 8) {
							gp.obj[i] = null;
							gp.ui.showNoti("Nothing here");
						}
						if(i == 9) {
							if(hasLockpick == 0) {
								gp.obj[i] = null;
								hasLockpick++;
								gp.ui.showNoti("I got lockpick");
							}else {
								gp.ui.showNoti("My bag is full");
							}
						}
						if(i == 10) {
							gp.obj[i] = null;
							gp.ui.showNoti("Nothing here");
						}
					}else {
						gp.ui.showNoti("Bag is full");
					}
				}
				break;
			case "BedsideBack":	
				if(Key.enterPressed == true) {
					if(hasLockpick <= 0) {
						if(i == 11) {
							gp.obj[i] = null;
							gp.ui.showNoti("Nothing here");
						}
						if(i == 12){
							gp.obj[i] = null;
							hasCrowbar++;	
							gp.ui.showNoti("I got crowbar");
						}
						if(i == 13) {
							gp.obj[i] = null;
							hasLockpick++;
							gp.ui.showNoti("I got lockpick");
						}
					}else {
						gp.ui.showNoti("Bag is full");
					}
				}
				break;
			case "Body":	
				if(Key.enterPressed == true) {
					if(hasLockpick <= 0) {
						gp.obj[i] = null;
						hasLockpick++;
						gp.ui.showNoti("I got lockpick");
					}else {
						gp.ui.showNoti("Bag is full");
					}
				}
				break;
			}
		}
	}
	
	public void InteractCat(int i) {}
	
	public void zombieAttack(int i) {
		if(i != 999) {
			if(invincible == false) {
				life -= 1;
				gp.ui.showNoti("Ugh!");
				invincible = true;
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			if(spriteNum == 3) {
				image = up3;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			if(spriteNum == 3) {
				image = down3;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			if(spriteNum == 3) {
				image = left3;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			if(spriteNum == 3) {
				image = right3;
			}
			break;
		}
		g2.drawImage(image, x, y, null);
	}

}
