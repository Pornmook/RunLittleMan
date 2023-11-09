package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JPanel;
import ai.PathFinder;
import entity.Entity;
import entity.Player;
import tile.TileManager;


public class GamePanel extends JPanel implements Runnable{
	final int originalTileSize = 16;
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
	public final int maxWorldCol = 16;
	public final int maxWorldRow = 12;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	public TileManager tileM = new TileManager(this);
	public Collision cChecker = new Collision(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	Keyboard Key = new Keyboard(this, ui);
	public PathFinder pFinder = new PathFinder(this);
	Thread gameThread;
	
	public Player player = new Player(this, Key);
	public Entity obj[] = new Entity[30];
	public Entity cat[] = new Entity[2];
	public Entity zombie[] = new Entity[10];
	ArrayList<Entity> entityList = new ArrayList<>();
	
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int gameoverState = 3;
	public final int gamefinishedState = 4;
	public final int htpState = 5;
	
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.white);
		this.setDoubleBuffered(true);
		this.addKeyListener(Key);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		aSetter.setCat();
		aSetter.setZombie();
		aSetter.setObject();
		gameState = titleState;
	}
	
	public void restart() {
		player.setDefaultValues();
		aSetter.setCat();
		aSetter.setZombie();
		aSetter.setObject();
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		
		double drawInterval = 1000000000/60;
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while(gameThread != null) {
			
			update();
			
			repaint();
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long) remainingTime);
				nextDrawTime += drawInterval;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void update() {
		if(gameState == playState) {
			player.update();
			
			for(int i=0; i< cat.length; i++) {
				if(cat[i] != null) {
					cat[i].update();
				}
			}
			for(int i=0; i<zombie.length; i++) {
				if(zombie[i] != null) {
					zombie[i].update();
				}
			}
		}	
		if(gameState == pauseState) {
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		//TITLE SCREEN
		if(gameState == titleState) {
			ui.draw(g2);
		}else {
			//TILE
			tileM.draw(g2);
			
			entityList.add(player);
			
			for(int i=0; i<obj.length; i++) {
				if(obj[i] != null) {
					entityList.add(obj[i]);
				}
			}
			
			for(int i=0; i<cat.length; i++) {
				if(cat[i] != null) {
					entityList.add(cat[i]);
				}
			}
			
			for(int i=0; i<zombie.length; i++) {
				if(zombie[i] != null) {
					entityList.add(zombie[i]);
				}
			}
			
			Collections.sort(entityList, new Comparator<Entity>() {
				@Override
				public int compare(Entity e1, Entity e2) {
					int result = Integer.compare(e1.y, e2.y);
					
					return result;
				}
				
			});
			
			//DRAW ENTITIES
			for(int i=0; i< entityList.size(); i++) {
				entityList.get(i).draw(g2);
			}
			entityList.clear();
			
			//UI
			ui.draw(g2);
			
		}
		
		g2.dispose();
	}

}
