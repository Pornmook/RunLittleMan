package main;

import entity.Entity;

public class Collision {
	GamePanel gp;
	
	public Collision(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) {
		int entityLeftWorldX = entity.x + entity.Hitbox.x;
		int entityRightWorldX = entity.x + entity.Hitbox.x + entity.Hitbox.width;
		int entityTopWorldY = entity.y + entity.Hitbox.y;
		int entityBottomWorldY = entity.y + entity.Hitbox.y + entity.Hitbox.height;
		
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		int tileNum1, tileNum2;
				
		switch(entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collison == true || gp.tileM.tile[tileNum2].collison == true) {
				entity.collisionOn = true;
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collison == true || gp.tileM.tile[tileNum2].collison == true) {
				entity.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collison == true || gp.tileM.tile[tileNum2].collison == true) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collison == true || gp.tileM.tile[tileNum2].collison == true) {
				entity.collisionOn = true;
			}
			break;
		}
		
		
	}
	
	public int checkObject(Entity entity, boolean player) {
		int index = 999;
		
		for(int i=0; i<gp.obj.length; i++) {
			if(gp.obj[i] != null) {
				entity.Hitbox.x = entity.x + entity.Hitbox.x;
				entity.Hitbox.y = entity.y + entity.Hitbox.y;
				
				gp.obj[i].Hitbox.x = gp.obj[i].x + gp.obj[i].Hitbox.x;
				gp.obj[i].Hitbox.y = gp.obj[i].y + gp.obj[i].Hitbox.y;
				
				switch(entity.direction) {
				case "up":entity.Hitbox.y -= entity.speed;break;
				case "down":entity.Hitbox.y += entity.speed;break;
				case "left":entity.Hitbox.x -= entity.speed;break;
				case "right":entity.Hitbox.x += entity.speed;break;
				}
				if(entity.Hitbox.intersects(gp.obj[i].Hitbox)) {
					if(gp.obj[i].collision == true) {
						entity.collisionOn = true;
					}
					if(player == true) {
						index = i;
					}
				}
				
				entity.Hitbox.x = entity.HitboxDefaultX;
				entity.Hitbox.y = entity.HitboxDefaultY;
				gp.obj[i].Hitbox.x = gp.obj[i].HitboxDefaultX;
				gp.obj[i].Hitbox.y = gp.obj[i].HitboxDefaultY;
			}
		}
		return index;
	}
	
	public int checkEntity(Entity entity, Entity[] target) {
int index = 999;
		
		for(int i=0; i<target.length; i++) {
			if(target[i] != null) {
				entity.Hitbox.x = entity.x + entity.Hitbox.x;
				entity.Hitbox.y = entity.y + entity.Hitbox.y;
				
				target[i].Hitbox.x = target[i].x + target[i].Hitbox.x;
				target[i].Hitbox.y = target[i].y + target[i].Hitbox.y;
				
				switch(entity.direction) {
				case "up":entity.Hitbox.y -= entity.speed;break;
				case "down":entity.Hitbox.y += entity.speed;break;
				case "left":entity.Hitbox.x -= entity.speed;break;
				case "right":entity.Hitbox.x += entity.speed;break;
				}
				if(entity.Hitbox.intersects(target[i].Hitbox)) {
					if(target[i] != entity) {
						entity.collisionOn = true;
						index = i;
					}
				}
				
				entity.Hitbox.x = entity.HitboxDefaultX;
				entity.Hitbox.y = entity.HitboxDefaultY;
				target[i].Hitbox.x = target[i].HitboxDefaultX;
				target[i].Hitbox.y = target[i].HitboxDefaultY;
			}
		}
		return index;
	}
	
	public boolean checkPlayer(Entity entity) {
		
		boolean zombieAttack = false;
		
		entity.Hitbox.x = entity.x + entity.Hitbox.x;
		entity.Hitbox.y = entity.y + entity.Hitbox.y;
		
		gp.player.Hitbox.x = gp.player.x + gp.player.Hitbox.x;
		gp.player.Hitbox.y = gp.player.y + gp.player.Hitbox.y;
		
		switch(entity.direction) {
		case "up":entity.Hitbox.y -= entity.speed;break;
		case "down":entity.Hitbox.y += entity.speed;break;
		case "left":entity.Hitbox.x -= entity.speed;break;
		case "right":entity.Hitbox.x += entity.speed;break;
		}
		if(entity.Hitbox.intersects(gp.player.Hitbox)) {
			entity.collisionOn = true;
			zombieAttack = true;
		}
		
		entity.Hitbox.x = entity.HitboxDefaultX;
		entity.Hitbox.y = entity.HitboxDefaultY;
		gp.player.Hitbox.x = gp.player.HitboxDefaultX;
		gp.player.Hitbox.y = gp.player.HitboxDefaultY;
		
		return zombieAttack;
	}
}
