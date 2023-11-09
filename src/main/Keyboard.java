package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{
	GamePanel gp;
	UI ui;
	
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, dropPressed;
	
	public Keyboard(GamePanel gp, UI ui) {
		this.gp = gp;
		this.ui = ui;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		//------------------------------ TITLE MENU ------------------------------
		int maxCommand = 1;

		if(gp.gameState == gp.titleState) {
			if(code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = maxCommand;
				}
			}
			if(code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > maxCommand) {
					gp.ui.commandNum = 0;
				}
			}
			if(code == KeyEvent.VK_UP) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = maxCommand;
				}
			}
			if(code == KeyEvent.VK_DOWN) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > maxCommand) {
					gp.ui.commandNum = 0;
				}
			}
			if(code == KeyEvent.VK_ENTER) {
				if(gp.ui.commandNum == 0) {
					gp.gameState = gp.htpState;
				}
				if(gp.ui.commandNum == 1) {
					System.exit(0);
				}
			}
		}
		
		//------------------------------ CHEATS ------------------------------
		if(gp.gameState == gp.playState) {
			if(gp.player.hasLockpick == 0 && gp.player.hasCrowbar == 0) {
				if(code == KeyEvent.VK_1) {
					gp.player.hasLockpick = 1;
				}
				if(code == KeyEvent.VK_2) {
					gp.player.hasCrowbar = 1;
				}
			}else {
				if(code == KeyEvent.VK_1) {
					gp.player.hasLockpick = 0;
				}
				if(code == KeyEvent.VK_2) {
					gp.player.hasCrowbar = 0;
				}
			}
			if(code == KeyEvent.VK_3) {
				gp.player.life = gp.player.maxLife;
			}
		}
		
		//------------------------------ GAME OVER MENU ------------------------------
		if(gp.gameState == gp.gameoverState) {
			if(code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = maxCommand;
				}
			}
			if(code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > maxCommand) {
					gp.ui.commandNum = 0;
				}
			}
			if(code == KeyEvent.VK_UP) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = maxCommand;
				}
			}
			if(code == KeyEvent.VK_DOWN) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > maxCommand) {
					gp.ui.commandNum = 0;
				}
			}
			if(code == KeyEvent.VK_ENTER) {
				if(gp.ui.commandNum == 0) {
					gp.gameState = gp.playState;
					gp.restart();
				}
				if(gp.ui.commandNum == 1) {
					gp.gameState = gp.titleState;
				}
			}
		}

		//------------------------------ GAME FINISHED STATE ------------------------------
		if(gp.gameState == gp.gamefinishedState) {
			if(code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = maxCommand;
				}
			}
			if(code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > maxCommand) {
					gp.ui.commandNum = 0;
				}
			}
			if(code == KeyEvent.VK_UP) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = maxCommand;
				}
			}
			if(code == KeyEvent.VK_DOWN) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > maxCommand) {
					gp.ui.commandNum = 0;
				}
			}
			if(code == KeyEvent.VK_ENTER) {
				if(gp.ui.commandNum == 0) {
					gp.gameState = gp.playState;
					gp.restart();
				}
				if(gp.ui.commandNum == 1) {
					gp.gameState = gp.titleState;
				}
			}
		}
		
		//------------------------------ USE ITEMS ------------------------------
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		if(code == KeyEvent.VK_SHIFT) {
			dropPressed = true;
		}
		
		//------------------------------ MOVE CHARECTER ------------------------------
		if(code == KeyEvent.VK_W) {
			upPressed = true;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = true;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = true;
		}
		
		//------------------------------ PAUSE GAME ------------------------------
		if(code == KeyEvent.VK_ESCAPE) {
			if(gp.gameState == gp.playState) {
				gp.gameState = gp.pauseState;
			}
			else if(gp.gameState == gp.pauseState) {
				if(code == KeyEvent.VK_ESCAPE) {
					gp.gameState = gp.playState;
				}
			}
		}
		if(gp.gameState == gp.pauseState) {
			if(code == KeyEvent.VK_ENTER) {
				gp.gameState = gp.titleState;
			}
		}
		
		//------------------------------ HOW TO PLAY ------------------------------
		if(gp.gameState == gp.htpState) {
			if(code == KeyEvent.VK_SPACE) {
				gp.gameState = gp.playState;
			}
			if(code == KeyEvent.VK_ESCAPE) {
				gp.gameState = gp.titleState;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = false;
		}
		if(code == KeyEvent.VK_SHIFT) {
			dropPressed = false;
		}
	}	
}
