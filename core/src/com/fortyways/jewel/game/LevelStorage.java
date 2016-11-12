package com.fortyways.jewel.game;

import com.badlogic.gdx.utils.Array;
import com.fortyways.jewel.game.Cell.CellType;

public class LevelStorage {
	private Array<Level> Levels;
	public LevelStorage() {
		Levels=new Array<Level>();
		CellType[] Allowed=new CellType[5];
		Allowed[0]=CellType.CRYSTAL1;
		
		Allowed[1]=CellType.CRYSTAL2;
		Allowed[2]=CellType.CRYSTAL4;
		Allowed[3]=CellType.CRYSTAL3;
		Allowed[4]=CellType.CRYSTAL5;
		
		CellType[] Required=new CellType[1];
		Required[0]=CellType.CRYSTAL1;
		int[] Amount=new int[1];
		Amount[0]=10;
		int Moves=30;
		int Number=0;
		Level level=new Level(Allowed, Required, Amount,Moves,Number);
		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{24,0,7,8,9,15,16,14,23,31,32,39,40,47,48,55,56,62,63,64,57,71,70,69,68,67,66,65,1,2,3,4,5,6,7});
		Levels.add(level);
		 Allowed=new CellType[5];
		Allowed[0]=CellType.CRYSTAL1;
		Allowed[1]=CellType.CRYSTAL2;
		Allowed[2]=CellType.CRYSTAL4;
		Allowed[3]=CellType.CRYSTAL3;
		Allowed[4]=CellType.CRYSTAL5;
		Required=new CellType[2];
		Required[0]=CellType.CRYSTAL2;
		Required[1]=CellType.CRYSTAL4;
		Amount =new int[2];
		Amount[0]=8;
		Amount[1]=8;
		Moves=35;
		Number=1;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{24,0,7,8,9,15,16,14,23,31,32,39,40,47,48,55,56,62,63,64,57,71,70,69,68,67,66,65,1,2,3,4,5,6,7});
		Levels.add(level);
		Allowed=new CellType[6];
		Allowed[0]=CellType.CRYSTAL1;
	
		Allowed[1]=CellType.CRYSTAL2;
		Allowed[2]=CellType.CRYSTAL4;
		Allowed[3]=CellType.CRYSTAL3;
		Allowed[4]=CellType.CRYSTAL5;
		Allowed[5]=CellType.CRYSTAL6;
		Required=new CellType[3];
		Required[0]=CellType.CRYSTAL5;
		Required[1]=CellType.CRYSTAL3;
		Required[2]=CellType.CRYSTAL2;
		Amount =new int[3];
		Amount[0]=5;
		Amount[1]=5;
		Amount[2]=5;
		Moves=30;
		Number=2;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{24,0,7,8,9,15,16,14,23,31,32,33,34,35,36,37,38,39,40,47,48,55,56,62,63,64,57,71});
		
		Levels.add(level);
		 Allowed=new CellType[5];
	
		Allowed[0]=CellType.CRYSTAL1;
		Allowed[1]=CellType.CRYSTAL7;
		Allowed[2]=CellType.CRYSTAL2;
		
		Allowed[3]=CellType.CRYSTAL3;
		Allowed[4]=CellType.CRYSTAL5;
		Required=new CellType[2];
		Required[0]=CellType.CRYSTAL3;
		Required[1]=CellType.CRYSTAL7;
		Amount =new int[2];
		Amount[0]=8;
		Amount[1]=8;
		Moves=30;
		Number=3;
		level=new Level(Allowed, Required, Amount,Moves,Number);

		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{24,0,7,8,9,15,16,14,23,31,32,33,34,35,36,37,38,39,40,47,48,55,56,62,63,64,57,71,70,69,66,65,1,2,5,6,7});
		
		Levels.add(level);
		Allowed=new CellType[6];
		
		Allowed[0]=CellType.CRYSTAL1;
		
		Allowed[1]=CellType.CRYSTAL2;
		Allowed[2]=CellType.CRYSTAL4;
		Allowed[3]=CellType.CRYSTAL3;
		Allowed[4]=CellType.CRYSTAL5;
		Allowed[5]=CellType.CRYSTAL6;
		Required=new CellType[1];
		Required[0]=CellType.CRYSTAL6;
		Amount =new int[1];
		Amount[0]=10;
		
		Moves=30;
		Number=4;
		level=new Level(Allowed, Required, Amount,Moves,Number);

		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{24,0,7,8,15,16,23,31,32,33,34,35,36,37,38,39,40,47,48,55,56,63,64,71,1,2,3,4,5,6,70,69,68,67,66,65});
		
		Levels.add(level);
		Allowed=new CellType[5];
		Allowed[0]=CellType.CRYSTAL1;
		Allowed[1]=CellType.CRYSTAL7;
		Allowed[2]=CellType.CRYSTAL2;
		Allowed[3]=CellType.CRYSTAL5;
		Allowed[4]=CellType.CRYSTAL3;
		
		Required=new CellType[2];
		Required[0]=CellType.CRYSTAL2;
		Required[1]=CellType.CRYSTAL7;
		Amount =new int[2];
		Amount[0]=10;
		Amount[1]=10;
		
		Moves=45;
		Number=5;
		level=new Level(Allowed, Required, Amount,Moves,Number);

		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{24,0,7,8,15,16,23,31,32,33,34,35,36,37,38,39,40,47,48,55,56,63,64,71,57,58,59,60,61,62,9,10,11,12,13,14});
		
		Levels.add(level);
		Allowed=new CellType[6];
		Allowed[0]=CellType.CRYSTAL1;
		Allowed[1]=CellType.CRYSTAL7;
		Allowed[2]=CellType.CRYSTAL2;
		
		Allowed[3]=CellType.CRYSTAL3;
		Allowed[4]=CellType.CRYSTAL5;
		Allowed[5]=CellType.CRYSTAL6;
		Required=new CellType[1];
		Required[0]=CellType.CRYSTAL6;
		
		Amount =new int[1];
		Amount[0]=15;
		Moves=30;
		Number=6;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{24,0,7,8,15,16,23,31,32,39,40,47,48,55,56,63,64,71,70,69,68,67,66,65,1,2,3,4,5,6});
		
		Levels.add(level);
		Allowed=new CellType[6];
		Allowed[0]=CellType.CRYSTAL1;
		Allowed[1]=CellType.CRYSTAL7;
		Allowed[2]=CellType.CRYSTAL2;
		
		Allowed[3]=CellType.CRYSTAL3;
		Allowed[4]=CellType.CRYSTAL5;
		Allowed[5]=CellType.CRYSTAL6;
		
		Required=new CellType[4];
		Required[0]=CellType.CRYSTAL5;
		Required[1]=CellType.CRYSTAL7;
		Required[2]=CellType.CRYSTAL1;
		Required[3]=CellType.CRYSTAL2;
		
		Amount =new int[4];
		Amount[0]=10;
		Amount[1]=10;
		Amount[2]=10;
		Amount[3]=10;
		
		Moves=40;
		Number=7;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{24,0,7,8,15,16,23,31,32,33,34,35,36,37,38,39,40,47,48,55,56,63,64,71,70,69,68,67,66,65,1,2,3,4,5,6});
		Levels.add(level);
		Allowed=new CellType[6];
		Allowed[0]=CellType.CRYSTAL1;
		
		Allowed[1]=CellType.CRYSTAL2;
		Allowed[2]=CellType.CRYSTAL4;
		Allowed[3]=CellType.CRYSTAL3;
		Allowed[4]=CellType.CRYSTAL5;
		Allowed[5]=CellType.CRYSTAL6;
		Required=new CellType[3];
	
		Required[0]=CellType.CRYSTAL5;
		Required[1]=CellType.CRYSTAL3;
		Required[2]=CellType.CRYSTAL4;
		Amount =new int[3];
		Amount[0]=10;
		Amount[1]=10;
		Amount[2]=10;
		
		Moves=40;
		Number=8;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{32,33,34,37,38,35,36,39,3,4,11,12,19,20,27,28,43,44,51,52,59,60,67,68});
		
		Levels.add(level);
		Allowed=new CellType[5];
		Allowed[0]=CellType.CRYSTAL1;
		Allowed[1]=CellType.CRYSTAL2;
		Allowed[2]=CellType.CRYSTAL4;
		Allowed[3]=CellType.CRYSTAL3;
		Allowed[4]=CellType.CRYSTAL5;
		Required=new CellType[3];
		Required[0]=CellType.CRYSTAL2;
		Required[1]=CellType.CRYSTAL3;
		Required[2]=CellType.CRYSTAL5;

		Amount =new int[3];
		Amount[0]=10;
		Amount[1]=10;
		Amount[2]=10;

		Moves=40;
		Number=9;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{0,1,2,3,4,5,6,7,71,70,69,68,67,66,65,64,32,33,34,35,36,37,38,39});
		
		Levels.add(level);
		Allowed=new CellType[5];
		Allowed[0]=CellType.CRYSTAL1;
		Allowed[1]=CellType.CRYSTAL5;
		Allowed[2]=CellType.CRYSTAL2;
		Allowed[3]=CellType.CRYSTAL4;
		Allowed[4]=CellType.CRYSTAL3;
	
		Required=new CellType[0];
	
		

		Amount =new int[1];
		Amount[0]=6;
		
		Moves=15;
		Number=10;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		level.setMode(2);
		level.setPlatespos(new int[]{19,20,27,28,35,36});

		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{24,0,7,8,15,16,23,31,32,39,40,47,48,55,56,63,64,71,70,69,68,67,66,65,1,2,3,4,5,6});
		Levels.add(level);
		Allowed=new CellType[6];
		Allowed[0]=CellType.CRYSTAL1;
		
		Allowed[1]=CellType.CRYSTAL2;
		Allowed[2]=CellType.CRYSTAL4;
		Allowed[3]=CellType.CRYSTAL3;
		Allowed[4]=CellType.CRYSTAL5;
		Allowed[5]=CellType.CRYSTAL6;
		Required=new CellType[0];
	
		
		Amount =new int[1];
		Amount[0]=10;
		Moves=25;
		Number=11;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		level.setMode(2);
		level.setPlatespos(new int[]{42,43,45,44,34,35,36,37,28,27});

		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{24,0,7,8,9,15,16,14,23,31,32,39,40,47,48,55,56,62,63,64,57,71,70,69,68,67,66,65,1,2,3,4,5,6,7});
		
		Levels.add(level);
		Allowed=new CellType[6];
		Allowed[0]=CellType.CRYSTAL1;
		
		Allowed[1]=CellType.CRYSTAL2;
		Allowed[2]=CellType.CRYSTAL4;
		Allowed[3]=CellType.CRYSTAL3;
		Allowed[4]=CellType.CRYSTAL5;
		Allowed[5]=CellType.CRYSTAL6;
		Required=new CellType[0];
	
		//Required[0]=CellType.BERYL;

		Amount =new int[1];
		Amount[0]=8;
		Moves=25;
		Number=12;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		level.setMode(2);
		level.setPlatespos(new int[]{18,19,20,21,26,27,28,29});

		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{24,0,7,8,15,16,23,31,32,47,48,55,56,62,63,64,57,71,70,69,68,67,66,65,1,2,3,4,5,6,7});
		
		Levels.add(level);
		Allowed=new CellType[7];
		Allowed[0]=CellType.CRYSTAL1;
		Allowed[1]=CellType.BCRYSTAL7;
		Allowed[2]=CellType.CRYSTAL2;
		Allowed[3]=CellType.CRYSTAL4;
		Allowed[4]=CellType.CRYSTAL3;
		Allowed[5]=CellType.CRYSTAL5;
		Allowed[6]=CellType.CRYSTAL6;
		Required=new CellType[1];
	
		Required[0]=CellType.BCRYSTAL7;
		Amount =new int[1];
		Amount[0]=10;
		Moves=20;
		Number=13;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		level.setMode(1);
		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{24,0,7,8,9,14,15,16,23,31,32,33,34,35,36,37,38,39,40,47,48,55,56,62,63,64,57,71,70,69,68,67,66,65,1,2,3,4,5,6,7});
		
		Levels.add(level);
		Allowed=new CellType[5];
		Allowed[0]=CellType.CRYSTAL1;
		
		Allowed[1]=CellType.CRYSTAL2;
		Allowed[2]=CellType.CRYSTAL4;
		Allowed[3]=CellType.CRYSTAL3;
		Allowed[4]=CellType.CRYSTAL5;
		Required=new CellType[0];
		Amount =new int[1];
		Amount[0]=10;
		Moves=25;
		Number=14;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		level.setMode(2);
		level.setPlatespos(new int[]{42,50,58,34,26,53,45,37,29,61});

		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{24,0,7,8,15,16,23,31,32,39,40,47,48,55,56,63,64,71,63});
		
		Levels.add(level);
		Allowed=new CellType[6];
		Allowed[0]=CellType.CRYSTAL1;
		Allowed[1]=CellType.CRYSTAL2;
		Allowed[2]=CellType.CRYSTAL4;
		Allowed[3]=CellType.CRYSTAL3;
		Allowed[4]=CellType.CRYSTAL5;
		Allowed[5]=CellType.CRYSTAL6;
		Required=new CellType[0];
		Amount =new int[1];
		Amount[0]=10;
		Moves=25;
		Number=15;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		level.setMode(2);
		level.setPlatespos(new int[]{42,43,44,34,26,53,45,37,29,61});
		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{0,1,2,3,4,5,6,7,8,15,11,12,19,20});
		
		Levels.add(level);
		Allowed=new CellType[5];
		Allowed[0]=CellType.CRYSTAL1;
		Allowed[1]=CellType.CRYSTAL2;
		Allowed[2]=CellType.CRYSTAL7;
		Allowed[3]=CellType.CRYSTAL3;
		Allowed[4]=CellType.CRYSTAL5;
		
		Required=new CellType[1];
		Required[0]=CellType.CRYSTAL2;
		Amount =new int[1];
		Amount[0]=20;
		Moves=25;
		Number=16;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{0,1,2,3,4,5,6,7,8,15,11,12,19,20,71,70,69,68,67,66,65,64,63,56,60,59});
		Levels.add(level);
		Allowed=new CellType[6];
		Allowed[0]=CellType.CRYSTAL1;
		Allowed[1]=CellType.CRYSTAL2;
		Allowed[2]=CellType.CRYSTAL7;
		Allowed[3]=CellType.CRYSTAL3;
		Allowed[4]=CellType.CRYSTAL5;
		Allowed[5]=CellType.CRYSTAL6;
		
		Required=new CellType[4];
		Required[0]=CellType.CRYSTAL6;
		Required[1]=CellType.CRYSTAL7;
		Required[2]=CellType.CRYSTAL5;
		Required[3]=CellType.CRYSTAL3;
		
		Amount =new int[4];
		Amount[0]=10;
		Amount[1]=10;
		Amount[2]=10;
		Amount[3]=10;
		Moves=40;
		Number=17;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{0,1,2,3,4,5,6,7,71,70,69,68,67,66,65,64});
		
		Levels.add(level);
		Allowed=new CellType[6];
		Allowed[0]=CellType.CRYSTAL1;
		Allowed[1]=CellType.CRYSTAL2;
		Allowed[2]=CellType.BCRYSTAL5;
		Allowed[3]=CellType.CRYSTAL3;
		Allowed[4]=CellType.CRYSTAL4;
		Allowed[5]=CellType.CRYSTAL6;
		Required=new CellType[1];
		Required[0]=CellType.BCRYSTAL5;	
		Amount =new int[1];
		Amount[0]=15;
		Moves=25;
		Number=18;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		level.setMode(1);
		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{0,1,2,3,4,5,6,7,71,32,33,34,35,36,37,38,39,70,69,68,67,66,65,64});

		Levels.add(level);
		Allowed=new CellType[6];
		Allowed[0]=CellType.CRYSTAL1;
		Allowed[1]=CellType.CRYSTAL2;
		Allowed[2]=CellType.CRYSTAL4;
		Allowed[3]=CellType.CRYSTAL3;
		Allowed[4]=CellType.CRYSTAL5;
		Allowed[5]=CellType.CRYSTAL6;
		Required=new CellType[1];
		Required[0]=CellType.CRYSTAL1;	
		Amount =new int[1];
		Amount[0]=2;
		Moves=40;
		Number=19;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{0,1,2,3,4,5,6,7,71,70,69,68,67,66,65,64,8,16,24,32,40,48,56,7,15,23,31,39,47,55,63});

		Levels.add(level);
		Allowed=new CellType[6];
		Allowed[0]=CellType.REDCRYSTAL5;
		Allowed[1]=CellType.REDCRYSTAL2;
		Allowed[2]=CellType.REDCRYSTAL4;
		
		Allowed[3]=CellType.REDCRYSTAL3;
		Allowed[4]=CellType.REDCRYSTAL1;
		Allowed[5]=CellType.REDCRYSTAL6;
		
		
		
		
		Required=new CellType[1];
		Required[0]=CellType.REDCRYSTAL5;	
		Amount =new int[1];
		Amount[0]=10;
		Moves=20;
		Number=20;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		Levels.add(level);
		Required=new CellType[1];
		Required[0]=CellType.REDCRYSTAL5;	
		Amount =new int[1];
		Amount[0]=10;
		Moves=20;
		Number=21;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		level.setHasForbiddenCells(true);
		
		level.setForbpos(new int[]{0,1,2,3,4,5,6,8,9,14,16,24,32,40,48,56,64,7,15,23,31,39,47,55,63,71,70,69,68,67,66,65,62,57});
		Levels.add(level);
		Required=new CellType[1];
		Required[0]=CellType.REDCRYSTAL2;	
		Amount =new int[1];
		Amount[0]=20;
		Moves=30;
		Number=22;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		level.setHasForbiddenCells(true);
		
		level.setForbpos(new int[]{0,8,16,24,32,40,48,56,64,7,15,23,31,39,47,55,63,71,70,69,68,67,66,65,62,57});
		Levels.add(level);
		Required=new CellType[2];
		Required[0]=CellType.REDCRYSTAL1;	
		Required[1]=CellType.REDCRYSTAL4;	
		Amount =new int[2];
		Amount[0]=20;
		Amount[1]=20;
		Moves=40;
		Number=23;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{0,8,16,24,32,33,34,35,36,37,38,40,48,56,64,7,15,23,31,39,47,55,63,71});
		Levels.add(level);
		Required=new CellType[2];
		Required[0]=CellType.REDCRYSTAL3;	
		Required[1]=CellType.REDCRYSTAL1;	
		Amount =new int[2];
		Amount[0]=20;
		Amount[1]=20;
		Moves=40;
		Number=24;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{24,25,26,27,28,29,30,31,48,49,50,51,52,53,54,55});
		Levels.add(level);
		Required=new CellType[3];
		Required[0]=CellType.REDCRYSTAL5;	
		Required[1]=CellType.REDCRYSTAL4;
		Required[2]=CellType.REDCRYSTAL5;
		Amount =new int[3];
		Amount[0]=20;
		Amount[1]=20;
		Amount[2]=20;
		Moves=40;
		Number=25;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{24,33,34,35,36,37,38,0,7,8,9,15,16,14,23,31,32,39,40,47,48,55,56,62,63,64,57,71,70,69,68,67,66,65,1,2,3,4,5,6,7});
		Levels.add(level);
		Required=new CellType[0];
	
		Amount =new int[1];
		Amount[0]=18;
		
		Moves=50;
		Number=26;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{24,0,7,8,9,15,16,14,23,31,32,39,40,47,48,55,56,62,63,64,57,71,70,69,68,67,66,65,1,2,3,4,5,6,7});
		level.setMode(2);
		level.setPlatespos(new int[]{13,10,11,12,58,59,60,61,25,33,41,49,17,30,38,46,54,22});
		Levels.add(level);
		Required=new CellType[0];
		Amount =new int[1];
		Amount[0]=10;
		Moves=25;
		Number=27;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{0,1,2,3,4,5,6,7,71,70,69,68,67,66,65,64});
		level.setMode(2);
		level.setPlatespos(new int[]{9,10,14,13,17,18,21,22,20,19});
		Levels.add(level);

		Required=new CellType[2];
		Required[0]=CellType.REDCRYSTAL6;	
		Required[1]=CellType.REDCRYSTAL2;	
		Amount =new int[2];
		Amount[0]=15;
		Amount[1]=15;
		Moves=35;
		Number=28;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{0,1,2,3,4,5,6,7,11,12,19,20,71,70,69,68,67,66,65,64});
		Levels.add(level);
		Required=new CellType[1];
		Required[0]=CellType.REDCRYSTAL3;	
		//Required[1]=CellType.EMERALD;	
		Amount =new int[1];
		Amount[0]=15;
		//Amount[1]=15;
		Moves=35;
		Number=29;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{0,1,2,3,4,5,6,7,11,12,19,20,71,70,69,68,67,66,65,64,63,56});
		Levels.add(level);
		Required=new CellType[1];
		Required[0]=CellType.REDCRYSTAL1;	
		//Required[1]=CellType.EMERALD;	
		Amount =new int[1];
		Amount[0]=20;
		//Amount[1]=15;
		Moves=35;
		Number=30;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{0,8,16,24,32,40,48,56,64,71,63,55,47,39,31,23,15,7});
		Levels.add(level);
		Required=new CellType[0];
		Amount =new int[1];
		Amount[0]=14;
		Moves=40;
		Number=31;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		
		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{0,1,2,3,4,5,6,7,71,70,69,68,67,66,65,64,11,12,19,20,27,28,35,36,43,44,51,52,59,60});
		level.setMode(2);
		level.setPlatespos(new int[]{10,18,26,34,42,50,58,13,21,29,37,45,53,61});
		Levels.add(level);
		Required=new CellType[0];
		Amount =new int[1];
		Amount[0]=12;
		Moves=40;
		Number=32;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{0,1,2,3,4,5,6,7,71,70,69,68,67,66,65,64,8,16,24,32,40,48,56,15,23,31,39,47,55,63});
		level.setMode(2);
		level.setPlatespos(new int[]{9,10,11,12,13,14,57,58,59,60,61,62});
		Levels.add(level);
		Required=new CellType[3];
		Required[0]=CellType.REDCRYSTAL5;	
		Required[1]=CellType.REDCRYSTAL2;
		Required[2]=CellType.REDCRYSTAL1;
		Amount =new int[3];
		Amount[0]=15;
		Amount[1]=15;
		Amount[2]=15;
		Moves=35;
		Number=33;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{0,7,8,15,16,23,24,31,32,39,40,47,48,55,56,63,64,71,65,70,1,6});
		Levels.add(level);
		Required=new CellType[2];
		Required[0]=CellType.REDCRYSTAL3;	
		Required[1]=CellType.REDCRYSTAL6;
		//Required[2]=CellType.TOPAZ;
		Amount =new int[2];
		Amount[0]=20;
		Amount[1]=20;
		//Amount[2]=15;
		Moves=35;
		Number=34;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{0,7,8,15,16,23,24,31,32,39,40,47,48,55,56,63,64,71,65,70,1,6,66,69,2,3,4,5,6,11,12,19,20});
		Levels.add(level);

		Allowed=new CellType[5];
		Allowed[0]=CellType.REDCRYSTAL5;
		Allowed[1]=CellType.REDCRYSTAL2;
		Allowed[2]=CellType.REDCRYSTAL4;
		
		Allowed[3]=CellType.REDCRYSTAL3;
		Allowed[4]=CellType.REDCRYSTAL1;
		Required=new CellType[0];
		Amount =new int[1];
		Amount[0]=28;

		Moves=60;
		Number=35;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{0,1,2,3,4,5,6,7,71,70,69,68,67,66,65,64});
		level.setMode(2);
		level.setPlatespos(new int[]{8,10,12,14,17,19,21,23,24,26,28,30,33,35,37,39,40,42,44,46,49,51,53,55,56,58,60,62});
		Levels.add(level);
		Required=new CellType[0];
		Amount =new int[1];
		Amount[0]=16;
		Moves=35;
		Number=36;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{0,1,2,3,4,5,6,7,15,23,31,39,47,55,63,71,8,16,24,32,40,48,56,64});
		level.setMode(2);
		level.setPlatespos(new int[]{9,17,25,33,41,49,57,65,14,22,30,38,46,54,62,70});
		Levels.add(level);
		Allowed=new CellType[5];
		Allowed[0]=CellType.REDCRYSTAL5;
		Allowed[1]=CellType.REDCRYSTAL2;
		Allowed[2]=CellType.REDCRYSTAL4;	
		Allowed[3]=CellType.REDCRYSTAL3;
		Allowed[4]=CellType.REDCRYSTAL6;

		Required=new CellType[2];
		Required[0]=CellType.REDCRYSTAL5;	
		Required[1]=CellType.REDCRYSTAL4;
		Amount =new int[2];
		Amount[0]=15;
		Amount[1]=20;
		Moves=35;
		Number=37;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{0,8,16,24,32,40,48,56,64,71,63,55,47,39,31,23,15,7});
		Levels.add(level);

		Allowed=new CellType[6];
		Allowed[0]=CellType.REDCRYSTAL5;
		Allowed[1]=CellType.REDCRYSTAL2;
		Allowed[2]=CellType.REDCRYSTAL4;	
		Allowed[3]=CellType.REDCRYSTAL3;
		Allowed[4]=CellType.REDCRYSTAL6;
		Allowed[5]=CellType.REDCRYSTAL1;
		Required=new CellType[2];
		Required[0]=CellType.REDCRYSTAL6;	
		Required[1]=CellType.REDCRYSTAL1;
		Amount =new int[2];
		Amount[0]=25;
		Amount[1]=25;
		Moves=40;
		Number=38;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{0,8,16,24,32,40,48,56,64,71,63,55,47,39,31,23,15,7,1,2,3,4,5,6,70,69,68,67,66,65});
		Levels.add(level);
		Required=new CellType[2];
		Required[0]=CellType.REDCRYSTAL4;	
		Required[1]=CellType.REDCRYSTAL3;
		Amount =new int[2];
		Amount[0]=25;
		Amount[1]=25;
		Moves=30;
		Number=39;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{0,8,16,24,32,40,48,56,64,71,63,55,47,39,31,23,15,7,1,2,3,4,5,6,70,69,68,67,66,65});
		Levels.add(level);
		Allowed=new CellType[5];
		Allowed[0]=CellType.GCRYSTAL5;
		Allowed[1]=CellType.GCRYSTAL2;
		Allowed[2]=CellType.GCRYSTAL4;	
		Allowed[3]=CellType.GCRYSTAL3;
		Allowed[4]=CellType.GCRYSTAL1;

		Required=new CellType[1];
		Required[0]=CellType.GCRYSTAL1;	
		Amount =new int[1];
		Amount[0]=25;
		Moves=20;
		Number=40;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{0,8,16,24,32,40,48,56,64,71,63,55,47,39,31,23,15,7,1,2,3,4,5,6,70,69,68,67,66,65});
		Levels.add(level);
		Required=new CellType[1];
		Required[0]=CellType.GCRYSTAL2;	
		Amount =new int[1];
		Amount[0]=20;
		Moves=20;
		Number=41;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{0,8,16,24,32,40,48,56,64,71,63,55,47,39,31,23,15,7,1,2,3,4,5,6,11,12});
		Levels.add(level);
		Required=new CellType[2];
		Required[0]=CellType.GCRYSTAL3;	
		Required[1]=CellType.GCRYSTAL4;	
		Amount =new int[2];
		Amount[0]=15;
		Amount[1]=15;
		Moves=40;
		Number=42;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{0,8,7,1,2,3,4,5,6,11,12,15});
		Levels.add(level);

		Required=new CellType[1];
		Required[0]=CellType.CRATE1;	
		
		Amount =new int[1];
		Amount[0]=4;
		
		Moves=30;
		Number=43;
		level=new Level(Allowed, Required, Amount,Moves,Number);
		level.setMode(3);
		level.setColpos(new int[]{70,68,67,65});
		level.setHasForbiddenCells(true);
		level.setForbpos(new int[]{0,8,16,24,32,40,48,56,64,7,15,23,31,39,47,55,63,71});
		Levels.add(level);
		

		level=new Level(Allowed, Required, Amount,Moves,Number);
	
	}
	public Level getLevel(int i){
		return Levels.get(i);
	}
	public int getLevelAmount(){
		return Levels.size;
	}
	public boolean hasLevel(int i){
		return i<Levels.size;
	}
}
