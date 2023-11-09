package main;

import entity.Cat;
import object.BedsideBack;
import object.BedsideFront;
import object.Body;
import object.CellDoor;
import object.Lockpick;
import object.MainDoor;
import object.MainDoorLock;
import zombie.Zombie;

public class AssetSetter {
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		//---------------------------------------- CELL DOOR ----------------------------------------
		gp.obj[0] = new CellDoor(gp);
		gp.obj[0].x = 10 * gp.tileSize;
		gp.obj[0].y = 7 * gp.tileSize;
		
		gp.obj[1] = new CellDoor(gp);
		gp.obj[1].x = 2 * gp.tileSize;
		gp.obj[1].y = 4 * gp.tileSize;
		
		gp.obj[2] = new CellDoor(gp);
		gp.obj[2].x = 6 * gp.tileSize;
		gp.obj[2].y = 4 * gp.tileSize;
		
		gp.obj[3] = new CellDoor(gp);
		gp.obj[3].x = 10 * gp.tileSize;
		gp.obj[3].y = 4 * gp.tileSize;
		
		gp.obj[4] = new CellDoor(gp);
		gp.obj[4].x = 2 * gp.tileSize;
		gp.obj[4].y = 7 * gp.tileSize;
		
		gp.obj[5] = new CellDoor(gp);
		gp.obj[5].x = 6 * gp.tileSize;
		gp.obj[5].y = 7 * gp.tileSize;
		
		//---------------------------------------- MAIN DOOR ----------------------------------------
		gp.obj[6] = new MainDoor(gp);
		gp.obj[6].x = 15 * gp.tileSize;
		gp.obj[6].y = 6 * gp.tileSize;
		
		gp.obj[7] = new MainDoorLock(gp);
		gp.obj[7].x = 15 * gp.tileSize;
		gp.obj[7].y = 5 * gp.tileSize;
		
		//---------------------------------------- BED SIDE ----------------------------------------	
		gp.obj[8] = new BedsideFront(gp);
		gp.obj[8].x = 1 * gp.tileSize;
		gp.obj[8].y = 1 * gp.tileSize;
		
		gp.obj[9] = new BedsideFront(gp);
		gp.obj[9].x = 5 * gp.tileSize;
		gp.obj[9].y = 1 * gp.tileSize;
		
		gp.obj[10] = new BedsideFront(gp);
		gp.obj[10].x = 9 * gp.tileSize;
		gp.obj[10].y = 1 * gp.tileSize;
		
		gp.obj[11] = new BedsideBack(gp);
		gp.obj[11].x = 1 * gp.tileSize;
		gp.obj[11].y = 10 * gp.tileSize;
		
		gp.obj[12] = new BedsideBack(gp);
		gp.obj[12].x = 5 * gp.tileSize;
		gp.obj[12].y = 10 * gp.tileSize;
		
		gp.obj[13] = new BedsideBack(gp);
		gp.obj[13].x = 9 * gp.tileSize;
		gp.obj[13].y = 10 * gp.tileSize;
		
		//---------------------------------------- BODY ----------------------------------------
		gp.obj[14] = new Body(gp);
		gp.obj[14].x = 1 * gp.tileSize;
		gp.obj[14].y = 6 * gp.tileSize;
		
		//---------------------------------------- LOCKPICK ----------------------------------------
		gp.obj[15] = new Lockpick(gp);
		gp.obj[15].x = 1 * gp.tileSize;
		gp.obj[15].y = 2 * gp.tileSize;
		
		gp.obj[16] = new Lockpick(gp);
		gp.obj[16].x = 9 * gp.tileSize;
		gp.obj[16].y = 3 * gp.tileSize;
		
		gp.obj[17] = new Lockpick(gp);
		gp.obj[17].x = 2 * gp.tileSize;
		gp.obj[17].y = 10 * gp.tileSize;
		
		gp.obj[18] = new Lockpick(gp);
		gp.obj[18].x = 14 * gp.tileSize;
		gp.obj[18].y = 1 * gp.tileSize;
	}
	//---------------------------------------- CAT ----------------------------------------
	public void setCat() {
		gp.cat[0] = new Cat(gp);
		gp.cat[0].x = 10 * gp.tileSize;
		gp.cat[0].y = 6 * gp.tileSize;
		
	}
	//---------------------------------------- ZOMBIE ----------------------------------------
	public void setZombie() {
		gp.zombie[0] = new Zombie(gp);
		gp.zombie[0].x = 13 * gp.tileSize;
		gp.zombie[0].y = 5 * gp.tileSize;
		
		gp.zombie[1] = new Zombie(gp);
		gp.zombie[1].x = 5 * gp.tileSize;
		gp.zombie[1].y = 2 * gp.tileSize;
		
		gp.zombie[2] = new Zombie(gp);
		gp.zombie[2].x = 11 * gp.tileSize;
		gp.zombie[2].y = 1 * gp.tileSize;
		
		gp.zombie[3] = new Zombie(gp);
		gp.zombie[3].x = 5 * gp.tileSize;
		gp.zombie[3].y = 8 * gp.tileSize;
	}
}
