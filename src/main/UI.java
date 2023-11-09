package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import object.HP;

public class UI {
	
	GamePanel gp;
	Graphics2D g2;
	Font arial_20, TitleMenu;
	BufferedImage Hfull, Hblank;
	
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	public int commandNum = 0;
	public int HTPScreen = 0;
	public BufferedImage bg1,bgtest, howtoplay , inv, Cinv, Linv;
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		arial_20 = new Font("Airal", Font.BOLD, 20);
		TitleMenu = new Font("Airal", Font.BOLD, 40);
		
		HP hp = new HP(gp);
		Hfull = hp.image;
		Hblank = hp.image2;
	}
	
	public void showNoti(String text) {
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		
		g2.setFont(arial_20);
		g2.setColor(Color.yellow);
		
		//TITLE STATE
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
			getBackgroundImage();
		}
		
		if(gp.gameState == gp.htpState) {
			drawHTPscreen();
		}
		
		//PLAY STATE
		if(gp.gameState == gp.playState) {
			drawPlayerLife();
			getInventoryImage();
			
			g2.drawImage(inv, 670, 0, gp.tileSize, gp.tileSize, null);
			g2.setFont(arial_20);
			g2.setColor(Color.white);
			
			//SHOW ITEMS IN INVENTORY
			if(gp.player.hasLockpick > 0) {
				g2.drawImage(Linv, 670, 0, gp.tileSize, gp.tileSize, null);
				//g2.drawString("Lockpick: "+gp.player.hasLockpick , 600,35);
			}
			if(gp.player.hasCrowbar > 0) {
				g2.drawImage(Cinv, 670, 0, gp.tileSize, gp.tileSize, null);
				//g2.drawString("Crowbar: "+gp.player.hasCrowbar , 600,35);
			}
			
			//SHOW NOTI MESSAGE
			if(messageOn == true) {
				g2.setFont(g2.getFont().deriveFont(30));
				g2.drawString(message, 300, 555);
				
				messageCounter++;
				if(messageCounter > 120) {
					messageCounter = 0;
					messageOn = false;
				}
			}
		}
		
		//PAUSE STATE
		if(gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
		
		//GAME OVER STATE
		if(gp.gameState == gp.gameoverState) {
			drawGameOverScreen();
		}
		
		//GAME FINISHED STATE
		if(gp.gameState == gp.gamefinishedState) {
			drawGameFinishedScreen();
			//gp.gameThread = null;
		}
		
	}
	
	public void drawPlayerLife() {
		int x = gp.tileSize/2;
		int y = 2;
		int i = 0;
		
		while(i < gp.player.maxLife) {
			g2.drawImage(Hblank, x, y,null);
			i++;
			x += gp.tileSize;
		}
		 x = gp.tileSize/2;
		 y = 2;
		 i = 0;
		 
		 while(i < gp.player.life) {
			 g2.drawImage(Hfull, x, y, null);
			 i++;
			 x += gp.tileSize;
		 }
	}
	
	public void drawTitleScreen() {
		//TITLE NAME
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
		String text = "RUN LITTLE MAN";
		int x;
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		x = gp.screenWidth/2 - length/2;
		int y = gp.tileSize*3;
		
		//TITLE BACKGROUND IMAGE
		x = gp.screenWidth/2 - (gp.tileSize*2)/2;
		y += gp.tileSize*2;
		/*g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);*/
		g2.drawImage(bg1, 0, 0, gp.worldWidth, gp.worldHeight, null);
		
		//MENU
		g2.setFont(TitleMenu);
		g2.setColor(Color.ORANGE);
		
		text = "PLAY";
		length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		x = gp.screenWidth/2 - length/2;
		y += gp.tileSize*4;
		g2.drawString(text, 100, y);
		if(commandNum == 0) {
			g2.drawString(">", 100-gp.tileSize, y);
		}
		
		text = "QUIT";
		length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		x = gp.screenWidth/2 - length/2;
		y += gp.tileSize;
		g2.drawString(text, 100, y);
		if(commandNum == 1) {
			g2.drawString(">", 100-gp.tileSize, y);
		}
	}
	
	public void drawHTPscreen() {
		int x, y=0;
		x = gp.screenWidth/2 - (gp.tileSize*2)/2;
		y += gp.tileSize*2;
		g2.drawImage(howtoplay, 0, 0, gp.worldWidth, gp.worldHeight, null);
	}
	
	public void getBackgroundImage() {
		BufferedImage image = null;
		try {
			//768*576 pixels
			bgtest = ImageIO.read(getClass().getResourceAsStream("/BackgroundImage/bgtest.png"));
			bg1 = ImageIO.read(getClass().getResourceAsStream("/BackgroundImage/bg1.png"));
			howtoplay = ImageIO.read(getClass().getResourceAsStream("/BackgroundImage/howtoplay.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getInventoryImage() {
		BufferedImage image = null;
		try {
			inv = ImageIO.read(getClass().getResourceAsStream("/Inv/Inv.png"));
			Cinv = ImageIO.read(getClass().getResourceAsStream("/Inv/CrowbarInv.png"));
			Linv = ImageIO.read(getClass().getResourceAsStream("/Inv/LockpickInv.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void drawGameOverScreen() {
		g2.setColor(new Color(0,0,0,150));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		int x, y;
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));
		text = "YOU DIED";
		g2.setColor(Color.black);
		
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		x = gp.screenWidth/2 - length/2;
		y = gp.tileSize*4;
		g2.drawString(text, x+5, y+5);
		g2.setColor(Color.red);
		g2.drawString(text, x, y);
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(45f));
		text = "Restart";
		length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		x = gp.screenWidth/2 - length/2;
		y += gp.tileSize*4;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x-40, y);
		}
		
		text = "Give up";
		length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		x = gp.screenWidth/2 - length/2;
		y += 70;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString(">", x-40, y);
		}
	}
	
	public void drawGameFinishedScreen() {
		g2.setColor(new Color(0,0,0,150));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		int x, y;
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80));
		text = "You are survive";
		g2.setColor(Color.black);
		
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		x = gp.screenWidth/2 - length/2;
		y = gp.tileSize*4;
		g2.drawString(text, x+5, y+5);
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(45f));
		text = "Restart";
		length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		x = gp.screenWidth/2 - length/2;
		y += gp.tileSize*4;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x-40, y);
		}
		
		text = "Menu";
		length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		x = gp.screenWidth/2 - length/2;
		y += 70;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString(">", x-40, y);
		}
	}
	
	public void drawPauseScreen() {
		g2.setColor(new Color(0,0,0,150));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		g2.setColor(Color.yellow);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
		String text = "PAUSED";
		int x, y;
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		x = gp.screenWidth/2 - length/2;
		y = gp.screenHeight/2;
		g2.drawString(text, x, y);
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(20f));
		text = "[ENTER] Main menu";
		g2.drawString(text, 290, 320);
	}

}
