package com.fortyways.jewel.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.fortyways.animation.Animation;
import com.fortyways.animation.DestroyAnim;
import com.fortyways.animation.GoalAnim;
import com.fortyways.animation.LightningAnim;
import com.fortyways.animation.NoMovesAnim;
import com.fortyways.animation.PUAnim;
import com.fortyways.animation.PlusNumAnim;
import com.fortyways.jewel.JR;
import com.fortyways.jewel.game.Cell.CellType;



public class Grid {
	//Cell[][] cells;
	Array<Cell>gr;
	 int numRows=5;
	int numCols=5;
	private int cellSize;
	float boardOffset;
	public boolean created=false;
	private Level level;
	private int[] collected;
	private int score=0;
	private int CellWorth=10;
	private float[] multiplier=new float[3];
	private int lastlen=0;
	private CellType lasttype;
	private boolean processing=false;
	private boolean clickable=true;
	private boolean moved;
	private float inputTimer=4f;
	Array<Animation>Anims=new Array<Animation>();
	//Sound sound = Gdx.audio.newSound(Gdx.files.internal("Pickup_Coin2.wav"));
	private GoalAnim GA;
	Cell LastMoved;
	public Grid(int numRows,int numCols, Level level) {
		
		this.level=level;
	//	GA=new GoalAnim(level);
		//Anims.add(GA);
		
		gr=new Array<Cell>();
		collected=new int[level.getAmount().length];
		for(int i=0;i<collected.length;i++){
		collected[i]=0;
		}
		this.numCols=numCols;
		this.numRows=numRows;
		
		cellSize=JR.WIDTH/numCols;
		System.out.println(cellSize);
		boardOffset=(JR.HEIGHT - (cellSize*numRows))/2-10;
		for(int row=0;row<numRows;row++){
			for(int col=0;col<numCols;col++){
				Cell c=new Cell(
						col*cellSize+cellSize/2,
						row*cellSize+boardOffset+cellSize/2,
						cellSize,
						cellSize,level.getAllowedCells());
			
				//c.setTipShowing(true);
				gr.add(c);
							
			}
		}
		//gr.get(62).setPowerUpType(3);
		//gr.get(70).setPowerUpType(1);
		if(level.getMode()==2){
		for(int i=0;i<level.getPlatespos().length;i++){
			gr.get(level.getPlatespos()[i]).setPlateState(1);
		}}
		if(level.HasForbiddenCells()){
			for(int i=0;i<level.getForbpos().length;i++){
				gr.get(level.getForbpos()[i]).setForbidden(true);
			}}
		if(level.getMode()==3){
			for(int i=0;i<level.getColpos().length;i++){
				///TODO different items for collect mode;
				gr.get(level.getColpos()[i]).setType(level.getRequiredCells()[0]);
			}
		}
		for(int i=0;i<multiplier.length;i++){
			multiplier[i]=1;
		}
		//getCell(3, 3).setPowerUpType(3);
		setForbTextures();
		
	}
	public void setForbTextures(){
		int row=0,col=0;
		if(level.HasForbiddenCells())
		for(int i=0;i<level.getForbpos().length;i++){
			row=level.getForbpos()[i]/numCols;
			col=level.getForbpos()[i]%numCols;
			if(row==numRows-1){
				if((col==0&&getCell(row-1,col).getForbidden()&&getCell(row-1,col+1).getForbidden()&&getCell(row,col+1).getForbidden())
						||(col==numCols-1&&getCell(row-1,col).getForbidden()&&getCell(row-1,col-1).getForbidden()&&getCell(row,col-1).getForbidden())
						||(col!=0&&col!=numCols-1&&getCell(row-1,col).getForbidden()&&getCell(row-1,col-1).getForbidden()&&getCell(row-1,col+1).getForbidden()&&getCell(row,col+1).getForbidden()&&getCell(row,col-1).getForbidden())){
					if(level.getNumber()>=20&&level.getNumber()<40){
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("RCrystalWallFull"));
					}
					else
					if(level.getNumber()>=40){
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("GCrystalWallFull"));
					}
					else
					getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("CrystalWallFull"));
					
				}else
					if(col!=numCols-1&&getCell(row,col+1).getForbidden()&&getCell(row-1,col).getForbidden()&&!getCell(row-1,col+1).getForbidden()){
						if(level.getNumber()>=20&&level.getNumber()<40){
							getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("RCrystalWallUpLeft"));
						}
						else
							if(level.getNumber()>=40){
								getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("GCrystalWallUpLeft"));
								
							}
						else
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("CrystalWallUpLeft"));
					}
					else if((col>0)&&!getCell(row-1,col-1).getForbidden()&&getCell(row,col-1).getForbidden()&&getCell(row-1,col).getForbidden())
					{
						if(level.getNumber()>=20&&level.getNumber()<40){
							getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("RCrystalWallUpRight"));
						}
						else
						if(level.getNumber()>=40){
							getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("GCrystalWallUpRight"));
						}
						else
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("CrystalWallUpRight"));
						
					}
					else if(col!=numCols-1
							&&!getCell(row-1,col).getForbidden()
							&&!getCell(row,col+1).getForbidden()){
						if(level.getNumber()>=20&&level.getNumber()<40){
							getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("RCrystalWallCornerTopLeft"));
						}
						else
						if(level.getNumber()>=40){
							getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("GCrystalWallCornerTopLeft"));
							
						}
						else
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("CrystalWallCornerTopLeft"));
					}
					else if(col!=0
							&&!getCell(row-1,col).getForbidden()
							&&!getCell(row,col-1).getForbidden()){
						if(level.getNumber()>=20&&level.getNumber()<40){
							getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("RCrystalWallCornerTopRight"));
						}
						else
						if(level.getNumber()>=40){
							getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("GCrystalWallCornerTopRight"));
								
						}
						else
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("CrystalWallCornerTopRight"));
					}
					else if((col!=0&&getCell(row,col-1).getForbidden()&&!getCell(row-1,col).getForbidden())
							||((col!=numCols-1)&&getCell(row,col+1).getForbidden()&&!getCell(row-1,col).getForbidden())){
						if(level.getNumber()>=20&&level.getNumber()<40){
							getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("RCrystalWallUp"));
						}
						else
						if(level.getNumber()>=40){
							getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("GCrystalWallUp"));
								
						}
						else
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("CrystalWallUp"));
						
					}
					
					else if(col!=0&&!getCell(row,col-1).getForbidden()){
						if(level.getNumber()>=20&&level.getNumber()<40){
							getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("RCrystalWallRight"));
						}
						else
						if(level.getNumber()>=40){
							getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("GCrystalWallRight"));
						}
						else
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("CrystalWallRight"));
						
					}
					else{
						if(level.getNumber()>=20&&level.getNumber()<40){
							getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("RCrystalWallLeft"));
						}
						else
						if(level.getNumber()>=40){
							getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("GCrystalWallLeft"));
						}
						else
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("CrystalWallLeft"));
					}
			}
			/////////////////////////////////////
			else if(row==0){

				if((col==0&&getCell(row+1,col).getForbidden()&&getCell(row+1,col+1).getForbidden()&&getCell(row,col+1).getForbidden())
						||(col==numCols-1&&getCell(row+1,col).getForbidden()&&getCell(row+1,col-1).getForbidden()&&getCell(row,col-1).getForbidden())
						||(col!=0&&col!=numCols-1&&getCell(row+1,col).getForbidden()&&getCell(row+1,col-1).getForbidden()&&getCell(row+1,col+1).getForbidden()&&getCell(row,col+1).getForbidden()&&getCell(row,col-1).getForbidden())){
					if(level.getNumber()>=20&&level.getNumber()<40){
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("RCrystalWallFull"));
					}
					else
					if(level.getNumber()>=40){
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("GCrystalWallFull"));
							
					}
					else
					getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("CrystalWallFull"));
					
				}else
				if(col!=numCols-1&&getCell(row,col+1).getForbidden()&&getCell(row+1,col).getForbidden()&&!getCell(row+1,col+1).getForbidden()){
					if(level.getNumber()>=20&&level.getNumber()<40){
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("RCrystalWallDownLeft"));
					}
					else
					if(level.getNumber()>=40){
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("GCrystalWallDownLeft"));
					}
					else
					getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("CrystalWallDownLeft"));
				}
				else if((col>0)&&!getCell(row+1,col-1).getForbidden()&&getCell(row,col-1).getForbidden()&&getCell(row+1,col).getForbidden())
				{
					if(level.getNumber()>=20&&level.getNumber()<40){
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("RCrystalWallDownRight"));
					}
					else
					if(level.getNumber()>=40){
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("GCrystalWallDownRight"));
								
					}
					else
					getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("CrystalWallDownRight"));
					
				}
				else if(col!=0
						&&!getCell(row+1,col).getForbidden()
						&&!getCell(row,col-1).getForbidden()){
					if(level.getNumber()>=20&&level.getNumber()<40){
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("RCrystalWallCornerDownRight"));
					}
					else
					if(level.getNumber()>=40){
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("GCrystalWallCornerDownRight"));
							
					}
					else
					getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("CrystalWallCornerDownRight"));
				}
				else if(col!=numCols-1
						&&!getCell(row+1,col).getForbidden()
						&&!getCell(row,col+1).getForbidden()){
					if(level.getNumber()>=20&&level.getNumber()<40){
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("RCrystalWallCornerDownLeft"));
					}
					else
					if(level.getNumber()>=40){
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("GCrystalWallCornerDownLeft"));
							
					}
					else
					getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("CrystalWallCornerDownLeft"));
				}
				else if(((col!=0)&&getCell(row,col-1).getForbidden()&&!getCell(row+1,col).getForbidden())
						||(col==0)&&getCell(row,col+1).getForbidden()&&!getCell(row+1,col).getForbidden()){
					if(level.getNumber()>=20&&level.getNumber()<40){
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("RCrystalWallDown"));
					}
					else
					if(level.getNumber()>=40){
							getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("GCrystalWallDown"));
							
					}
					else
					getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("CrystalWallDown"));
					
				}
				
				else if(col!=0&&!getCell(row,col-1).getForbidden()){
					if(level.getNumber()>=20&&level.getNumber()<40){
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("RCrystalWallRight"));
					}
					else
					if(level.getNumber()>=40){
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("GCrystalWallRight"));
							
					}
					else
					getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("CrystalWallRight"));
					
				}
				else{
					if(level.getNumber()>=20&&level.getNumber()<40){
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("RCrystalWallLeft"));
					}
					else
					if(level.getNumber()>=40){
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("GCrystalWallLeft"));
							
					}
					else
					getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("CrystalWallLeft"));
				}
			}
			//////////////////////////////
			else{
				if((col!=numCols-1)
						&&getCell(row,col+1).getForbidden()
						&&getCell(row-1,col).getForbidden()
						&&getCell(row+1,col).getForbidden()
						&&!getCell(row+1,col+1).getForbidden()
						&&!getCell(row-1,col+1).getForbidden()){
					if(level.getNumber()>=20&&level.getNumber()<40){
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("RCrystalWallHorL"));
					}
					else
						if(level.getNumber()>=40){
							getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("GCrystalWallHorL"));
							
						}
					else
					getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("CrystalWallHorL"));
				}else
				if((col!=0)
						&&getCell(row,col-1).getForbidden()
						&&getCell(row-1,col).getForbidden()
						&&getCell(row+1,col).getForbidden()
						&&!getCell(row+1,col-1).getForbidden()
						&&!getCell(row-1,col-1).getForbidden()){
					if(level.getNumber()>=20&&level.getNumber()<40){
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("RCrystalWallHorR"));
					}
					else
						if(level.getNumber()>=40){
							getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("GCrystalWallHorR"));
								
						}
					else
					getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("CrystalWallHorR"));
				}
				else if(col!=0
						&&!getCell(row+1,col).getForbidden()
						&&!getCell(row-1,col).getForbidden()
						&&!getCell(row,col-1).getForbidden()){
					if(level.getNumber()>=20&&level.getNumber()<40){
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("RCrystalWallHorLeft"));
					}
					else
						if(level.getNumber()>=40){
							getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("GCrystalWallHorLeft"));
							
						}
					else
					getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("CrystalWallHorLeft"));
				}
				else if(col!=numCols-1
						&&!getCell(row+1,col).getForbidden()
						&&!getCell(row-1,col).getForbidden()
						&&!getCell(row,col+1).getForbidden()){
					if(level.getNumber()>=20&&level.getNumber()<40){
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("RCrystalWallHorRight"));
					}
					else
						if(level.getNumber()>=40){
							getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("GCrystalWallHorRight"));
							
						}
					else
					getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("CrystalWallHorRight"));
				}
				else if(!getCell(row+1,col).getForbidden()&&!getCell(row-1,col).getForbidden()){
					if(level.getNumber()>=20&&level.getNumber()<40){
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("RCrystalWallHor"));
					}
					else
						if(level.getNumber()>=40){
							getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("GCrystalWallHor"));
								
						}
					else
					getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("CrystalWallHor"));
				}
				
				else if(col!=0
						&&col!=numCols-1
						&&!getCell(row,col+1).getForbidden()
						&&!getCell(row,col-1).getForbidden()
						&&getCell(row+1,col).getForbidden()
						&&getCell(row-1,col).getForbidden()){
					if(level.getNumber()>=20&&level.getNumber()<40){
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("RCrystalWallVer"));
					}
					else
						if(level.getNumber()>=40){
							getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("GCrystalWallVer"));
							
						}
					else
					getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("CrystalWallVer"));
						}
				else
				if(col!=numCols-1&&getCell(row,col+1).getForbidden()&&getCell(row-1,col).getForbidden()&&!getCell(row-1,col+1).getForbidden()){
					if(level.getNumber()>=20&&level.getNumber()<40){
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("RCrystalWallUpLeft"));
					}
					else
						if(level.getNumber()>=40){
							getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("GCrystalWallUpLeft"));
							
						}
					else
					getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("CrystalWallUpLeft"));
				}
				else if((col>0)&&!getCell(row-1,col-1).getForbidden()&&getCell(row,col-1).getForbidden()&&getCell(row-1,col).getForbidden())
				{
					if(level.getNumber()>=20&&level.getNumber()<40){
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("RCrystalWallUpRight"));
					}
					else
						if(level.getNumber()>=40){
							getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("GCrystalWallUpRight"));
							
						}
					else
					getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("CrystalWallUpRight"));
				}else
				if(col!=numCols-1&&getCell(row,col+1).getForbidden()&&getCell(row+1,col).getForbidden()&&!getCell(row+1,col+1).getForbidden()){
					if(level.getNumber()>=20&&level.getNumber()<40){
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("RCrystalWallDownLeft"));
					}
					else
						if(level.getNumber()>=40){
							getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("GCrystalWallDownLeft"));
							
						}
					else
					getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("CrystalWallDownLeft"));
				}
				else if((col>0)&&!getCell(row+1,col-1).getForbidden()&&getCell(row,col-1).getForbidden()&&getCell(row+1,col).getForbidden())
				{
					if(level.getNumber()>=20&&level.getNumber()<40){
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("RCrystalWallDownRight"));
					}
					else
						if(level.getNumber()>=40){
							getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("GCrystalWallDownRight"));
							
						}
					else
					getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("CrystalWallDownRight"));
					
				}
				else if(col!=numCols-1
						&&!getCell(row-1,col).getForbidden()
						&&!getCell(row,col+1).getForbidden()){
					if(level.getNumber()>=20&&level.getNumber()<40){
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("RCrystalWallCornerTopLeft"));
					}
					else
						if(level.getNumber()>=40){
							getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("GCrystalWallCornerTopLeft"));
							
						}
					else
					getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("CrystalWallCornerTopLeft"));
				}
				else if(col!=0
						&&!getCell(row-1,col).getForbidden()
						&&!getCell(row,col-1).getForbidden()){
					if(level.getNumber()>=20&&level.getNumber()<40){
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("RCrystalWallCornerTopRight"));
					}
					else
						if(level.getNumber()>=40){
							getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("GCrystalWallCornerTopRight"));
							
						}
					else
					getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("CrystalWallCornerTopRight"));
				}
				else if(col!=0
						&&!getCell(row+1,col).getForbidden()
						&&!getCell(row,col-1).getForbidden()){
					if(level.getNumber()>=20&&level.getNumber()<40){
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("RCrystalWallCornerDownRight"));
					}
					else
						if(level.getNumber()>=40){
							getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("GCrystalWallCornerDownRight"));
							
						}
					else
					getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("CrystalWallCornerDownRight"));
				}
				else if(col!=numCols-1
						&&!getCell(row+1,col).getForbidden()
						&&!getCell(row,col+1).getForbidden()){
					if(level.getNumber()>=20&&level.getNumber()<40){
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("RCrystalWallCornerDownLeft"));
					}
					else
						if(level.getNumber()>=40){
							getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("GCrystalWallCornerDownLeft"));
							
						}
					else
					getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("CrystalWallCornerDownLeft"));
				}
				else
				if(col!=numCols-1&&!getCell(row,col+1).getForbidden()){
					if(level.getNumber()>=20&&level.getNumber()<40){
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("RCrystalWallLeft"));
					}
					else
						if(level.getNumber()>=40){
							getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("GCrystalWallLeft"));
							
						}
					else
					getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("CrystalWallLeft"));
					
				}
				else if(col!=0&&!getCell(row,col-1).getForbidden()){
					if(level.getNumber()>=20&&level.getNumber()<40){
						getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("RCrystalWallRight"));
					}
					else
						if(level.getNumber()>=40){
							getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("GCrystalWallRight"));
							
						}
					else
					getCell(row,col).setForb(JR.res.getAtlas("pack").findRegion("CrystalWallRight"));
					
				}
			}
		}
	}
	
	////////////////////////////////GRID CHANGING///////////////
	boolean NoMoves=false;
	Array<Cell> ar;
	public void CheckForNoMoves(){
	//inputTimer=4f;
	ar=new Array<Cell>();
		
		if(!NoMoves){
		boolean check=true;
		for(int row=0;row<numRows;row++){
			for(int col=0;col<numCols-1;col++){
				if(MakesMatch(getCell(row, col), row, col+1,true)||
						MakesMatch(getCell(row, col+1), row, col,true)){ 
					if(!getCell(row, col).getForbidden()&&!getCell(row, col+1).getForbidden() ){
					check=false;
					
					break;
					
					}
					
					
				}
				if(!check){
					break;
				}
			}
			if(!check){
				break;
			}
		}
		if(check)
			for(int col=0;col<numCols;col++){
				for(int row=0;row<numRows-1;row++){
				if(MakesMatch(getCell(row, col), row+1, col,true)||
						MakesMatch(getCell(row+1, col), row, col,true)){
					if(!getCell(row, col).getForbidden()&&!getCell(row+1, col).getForbidden() ){
					//System.out.println("Match at(2) "+row+" "+col);
					check=false;
					break;
					}
					
				}
				if(!check){
					break;
				}
			}
				if(!check){
					break;
				}
		}
		if(
				check&&!HasUnprocessedMatches()&&!processing&&!isFilling
				)
		{
			NoMoves=true;
			Anims.add(new NoMovesAnim());
Timer.schedule(new Timer.Task() {
				
				@Override
				public void run() {
			Timer.schedule(new Timer.Task() {
				
				@Override
				public void run() {
					if(level.getMode()==1){
						Anims.add(new PlusNumAnim(JR.WIDTH/2, JR.HEIGHT-80, -1));
						
					}
					for(Cell c:gr){
						if(!c.getForbidden()){
							c.UpdateTextures();
							//c.setMaxTime(1f);
							

							c.setMaxTime(0.5f);
							c.setTimer(1f);
							c.setMoveState(6);
							c.setMaxTime(0.15f);
							c.setDestroyed(false);
							Timer.schedule(new Timer.Task() {
								@Override
								public void run() {
								NoMoves=false;
								Timer.schedule( new Timer.Task() {
									
									@Override
									public void run() {
								CheckForNoMoves();}
									},2.5f);//TODO Previously was 5f;
								}},1f);
							
						}
					}
				}
			}, //getCell(0,0).getMaxTime());//
				1f);
			
			Array<CellType>CurrTypes=new Array<Cell.CellType>();
		
			for(Cell c:gr){
				if(!c.getForbidden()){
					c.setMaxTime(2f);
					c.setTimer(c.getMaxTime());
					c.setDestroyed(true);
					c.setMoveState(5);
					CurrTypes.add(c.getType());
				}
			}
			for(Cell c:gr){
				if(!c.getForbidden()){
					CellType ct=CurrTypes.random();
					c.setType(ct);
					CurrTypes.removeValue(ct, true);
				}
			}
			}},1f);
		}
		else{
			Timer.schedule(new Timer.Task() {
				
				@Override
				public void run() {
					
				if(ar.size!=0&&inputTimer<=0){
				for(Cell c:ar){
					c.setTipShowing(true);
				}
				inputTimer=4f;
			}
				;
					
				}
			}, 1f);
			
			
		}
		}
	
		
		
	}
	
	public void CheckBeforeStart(){
		for(int row=0;row<numRows;row++){
			for(int col=0;col<numCols-2;col++){
				if(getCell(row, col).getType()==getCell(row, col+1).getType()
						&&getCell(row, col+1).getType()==getCell(row, col+1).getType()&&
						!JR.getColCells().contains(getCell(row, col).getType(),false)){
					float rand=MathUtils.random(0, 10);
					if(rand>6){
						getCell(row, col).GenerateOtherThan(getCell(row, col).getType(),level.getAllowedCells());
					}
					else if(rand<3){
						getCell(row, col+1).GenerateOtherThan(getCell(row, col+1).getType(),level.getAllowedCells());
					}
					else{
						getCell(row, col+2).GenerateOtherThan(getCell(row, col+2).getType(),level.getAllowedCells());
					}
					getCell(row, col).UpdateTextures();
					
					
				}
				
				
				
			}
			
		}
		for(int col=0;col<numCols;col++){
			for(int row=0;row<numRows-2;row++){
				if(getCell(row, col).getType()==getCell(row+1, col).getType()
						&&getCell(row+1, col).getType()==getCell(row+2, col).getType()&&
						!JR.getColCells().contains(getCell(row, col).getType(),false)){
					float rand=MathUtils.random(0, 10);
					if(rand>6){
						getCell(row, col).GenerateOtherThan(getCell(row, col).getType(),level.getAllowedCells());
					}
					else if(rand<3){
						getCell(row+1, col).GenerateOtherThan(getCell(row+1, col).getType(),level.getAllowedCells());
					}
					else{
						getCell(row+2, col).GenerateOtherThan(getCell(row+2, col).getType(),level.getAllowedCells());
					}
					getCell(row, col).UpdateTextures();
				}
				}
			}
		for(Cell c:gr){
			c.UpdateTextures();
			c.setMaxTime(0.5f);
			c.setTimer(1f);
			c.setMoveState(6);
			c.setMaxTime(0.15f);
		}
		created=true;
		Timer.schedule(new Timer.Task() {
			
			@Override
			public void run() {
				
				CheckForNoMoves();
				
			}
		}, 2f);
		
	}
	boolean CheckNeeded=false;
	public void CheckMatch(){
/////////////ROW///////////////
	CheckNeeded=true;
	Array<int[]>r=new Array<int[]>();
	Array<int[]>c=new Array<int[]>();
	if(level.getMode()==3){
		for(int col=0;col<numCols;col++){
			if(!getCell(0, col).getForbidden()&&JR.getColCells().contains(getCell(0, col).getType(),false)){
			c.add(new int[]{0,col,1});	
			}
		}
	}
	for(int row=numRows-1;row>=0;row--){
		for(int col=0;col<numCols-2;col++){
			if(getCell(row, col).getType()==getCell(row, col+1).getType()
					&&getCell(row, col+1).getType()==getCell(row, col+2).getType()
					&&!getCell(row, col).getDestroyed()
					&&!getCell(row, col+1).getDestroyed()
					&&!getCell(row, col+2).getDestroyed()
					&&!getCell(row, col).getForbidden()
					&&!getCell(row, col+1).getForbidden()
					&&!getCell(row, col+2).getForbidden()
					//&&!JR.getColCells().contains(getCell(row, col).getType(),false)
					){
				
				int j=col;
				int len=3;
				int k=j+len;
				clickable=false;
				while(k<numCols&&getCell(row, k-1).getType()==getCell(row,k).getType()&&!getCell(row,k).getDestroyed()&&!getCell(row,k).getForbidden()){
					
					len++;
					k++;
				}
				if(!JR.getColCells().contains(getCell(row, col).getType(),false)){
				for(int i=0;i<len;i++){
					r.add(new int[]{row,col+i,1});
				}
				}
				break;
			
			
		}
		
		}
		}
	/////////////////COL/////////////////
	for(int col=numCols-1;col>=0;col--){
		for(int row=0;row<numRows-2;row++){
			if(getCell(row, col).getType()==getCell(row+1, col).getType()
					&&getCell(row+1, col).getType()==getCell(row+2, col).getType()
					&&!getCell(row, col).getDestroyed()
					&&!getCell(row+1, col).getDestroyed()
					&&!getCell(row+2, col).getDestroyed()
					&&!getCell(row, col).getForbidden()
					&&!getCell(row+1, col).getForbidden()
					&&!getCell(row+2, col).getForbidden()
				//	&&!JR.getColCells().contains(getCell(row, col).getType(),false)
					){
				clickable=false;
				int j=row;
				int len=3;
				int k=j+len;
				while(k<numRows&&getCell(k-1, col).getType()==getCell(k,col).getType()&&!getCell(k,col).getDestroyed()&&!getCell(k,col).getForbidden()){
					len++;
					k++;
				}
				if(!JR.getColCells().contains(getCell(row, col).getType(),false)){
				c.add(new int[]{row,col,len});}
			break;
			}
		}
		
		
	}
	
	ProcessMatches(r, c);
	
	timer=1f;
	}
	public void ColMatch(int row, int col,int len){
		
		boolean pr=false,pc=false,px=false;
		int pur = 0;
		int puc = 0;
		for(int i=0;i<len;i++){
		if(row+i<numRows&&getCell(row+i, col).getPowerUpType()!=0){
			Anims.add(new PUAnim(getCell(row+i, col)));
			if(getCell(row+i, col).getPowerUpType()==1){
				pr=true;
				pur=row+i;
				puc=col;
				getCell(row+i, col).setPowerUpType(0);
			}
			else if(getCell(row+i, col).getPowerUpType()==2){
				pc=true;
				pur=row+i;
				puc=col;
				getCell(row+i, col).setPowerUpType(0);
			}
			else if(getCell(row+i, col).getPowerUpType()==3){
				px=true;
				pur=row+i;
				puc=col;
				getCell(row+i, col).setPowerUpType(0);
			}
		if(level.getMode()==2){
			for(int i1=0;i1<len;i1++){
				getCell(row+i1, col).BreakPlate();
			}
		}
		}
		}
		if(len>=4){
			//int rr=0,rc=0;
		/*	rr=MathUtils.random(0, numRows-1);
			rc=MathUtils.random(0, numCols-1);
			while(getCell(rr,rc).getDestroyed()||getCell(rr, rc).getForbidden()||(rc==col&&rr>=row&&rr<row+len)){
				rr=MathUtils.random(0, numRows-1);
				rc=MathUtils.random(0, numCols-1);
			}
			if(!getCell(rr, rc).getDestroyed())
			getCell(rr, rc).setPowerUpType(MathUtils.random(1, 3));	
			System.out.println(rr+" "+rc);*/
			boolean chk4type=true;
			for(int i=0;i<len-1;i++){
				if(getCell(row+i, col).getType()!=getCell(row+i+1, col).getType()){
					chk4type=false;
					break;
				}
			}
			if(chk4type){
			getCell(row+len-1, col).setPowerUpType(2);
			//len=1;
			//len=len-1;
			}
			}
		final int rowf=row,colf=col,lenf=len;
		////////////////MULTIPLIER////////
		if(lastlen!=0){
			if(len>3){
				multiplier[0]=2;
			}
			else{
			
				if(multiplier[0]>1){
					multiplier[0]=1;
				}
				
			}
		}
		if(moved){
			setMoved(false);
			multiplier[2]=1;
		}
		else{
			if(LastMatch[0]!=row)
			multiplier[2]+=1;
		}
		if(lasttype!=null){
			if(getCell(row, col).getType()==lasttype&&len>2){
				multiplier[1]+=1;
				
			}
			else{
				multiplier[1]=1;
			}	
		}
		lasttype=getCell(row, col).getType();
		lastlen=len;
		for(int r=row;r<row+len;r++){
			AddScore(getCell(r, colf));
		}
		
		
		/////////////////////////////////
	
		if(pr||pc||px){
			if(pr){
				for(int i=0;i<numCols;i++){
					final int iff=i;
					final int purf=pur;
					if(iff!=colf && !getCell(purf,iff).getDestroyed()&&!getCell(purf,iff).getForbidden()){
					Timer.schedule(new Timer.Task() {
						@Override
						public void run() {
							Anims.add(new PlusNumAnim(getCell(purf,iff).getX(), getCell(purf,iff).getY(),CellWorth ));
							score+=CellWorth;
							ShiftColumn(purf, iff, 1);
							
							
						}},getCell(rowf, colf).getMaxTime());
					}
					else {
						ShiftColumn(rowf, iff, len);
					}
				}
			}
			else if(pc){
				final int pucf=puc;
				Timer.schedule(new Timer.Task() {
					
					@Override
					public void run() {
						for(int i=0;i<numRows;i++){
							if(i<rowf||i>=rowf+lenf&&!getCell(i,pucf).getForbidden()){
							Anims.add(new PlusNumAnim(getCell(i,pucf).getX(), getCell(i,pucf).getY(),CellWorth));
							score+=CellWorth;
							}
							
						}
						ShiftColumn(0, pucf,numRows );
						
					
					}},getCell(rowf, colf).getMaxTime());
				
			}
			else if(px){
				final int pucf=puc;
				for(int i=0;i<numRows;i++){
					if(i<row||i>=row+len&&!getCell(i,pucf).getForbidden()){
						
				Anims.add(new PlusNumAnim(getCell(i,pucf).getX(), getCell(i,pucf).getY(),CellWorth));
				score+=CellWorth;}
					}
				ShiftColumn(0, pucf,numRows );
				for(int i=0;i<numCols;i++){
					final int iff=i;
					final int purf=pur;
					if(i!=col && !getCell(purf,iff).getDestroyed()&&!getCell(purf,iff).getForbidden()){
						Timer.schedule(new Timer.Task() {
							@Override
							public void run() {
								if(iff!=pucf){
									
								Anims.add(new PlusNumAnim(getCell(purf,iff).getX(), getCell(purf,iff).getY(),CellWorth));
								score+=CellWorth;}
								ShiftColumn(purf, iff, 1);}},getCell(rowf, colf).getMaxTime());
					
					}
					
					}
			}}
			else{
				if(!getCell(rowf,colf).getDestroyed())
				ShiftColumn(rowf, colf, lenf);
			
			}
	}
	public boolean MakesMatch(Cell c,int row,int col,boolean tip){
	if(tip&&ar.size>0){
		ar=new Array<Cell>();
	}
				if(row+2<=numRows-1){
				if(c.getType()==getCell(row+1, col).getType()
						&&getCell(row+1, col).getType()==getCell(row+2, col).getType()
						&&c!=getCell(row+1, col)&&
						c!=getCell(row+2, col)
						&&!c.getForbidden()
						&&!getCell(row+1, col).getForbidden()
						&&!getCell(row+2, col).getForbidden()
						&&!JR.getColCells().contains(c.getType(),false)
						){
					if(tip){
					ar.add(c);
					ar.add(getCell(row+1,col));
					ar.add(getCell(row+2,col));
					//System.out.println("here");
					}
					return true;
					
				}}
				if(row-2>=0){
					if(c.getType()==getCell(row-1, col).getType()
							&&getCell(row-1, col).getType()==getCell(row-2, col).getType()
							&&c!=getCell(row-1, col)&&
							c!=getCell(row-2, col)
							&&!c.getForbidden()
							&&!getCell(row-1, col).getForbidden()
							&&!getCell(row-2, col).getForbidden()
							&&!JR.getColCells().contains(c.getType(),false)){
						//System.out.println("there");
						if(tip){
						ar.add(c);
						ar.add(getCell(row-1,col));
						ar.add(getCell(row-2,col));}
						return true;
					}
				}
				if(row>0&&row<numRows-1){
					if(c.getType()==getCell(row-1, col).getType()
							&&getCell(row-1, col).getType()==getCell(row+1, col).getType()
							&&c!=getCell(row+1, col)&&
							c!=getCell(row-1, col)
							&&!c.getForbidden()
							&&!getCell(row+1, col).getForbidden()
							&&!getCell(row-1, col).getForbidden()
							&&!JR.getColCells().contains(c.getType(),false)){
						if(tip){
						ar.add(c);
						ar.add(getCell(row+1,col));
						ar.add(getCell(row-1,col));}
						return true;
					}
				}
				if(col>0&&col<numCols-1){
				if(c.getType()==getCell(row, col-1).getType()
						&&getCell(row, col-1).getType()==getCell(row, col+1).getType()
						&&c!=getCell(row, col+1)&&
						c!=getCell(row, col-1)
						&&!c.getForbidden()
						&&!getCell(row, col+1).getForbidden()
						&&!getCell(row, col-1).getForbidden()
						&&!JR.getColCells().contains(c.getType(),false)){
					if(tip){
					ar.add(c);
					ar.add(getCell(row,col+1));
					ar.add(getCell(row,col-1));}
					return true;
				}
				}
				if(col-2>=0){
					if(c.getType()==getCell(row, col-1).getType()
							&&getCell(row, col-1).getType()==getCell(row, col-2).getType()
							&&c!=getCell(row, col-2)&&
							c!=getCell(row, col-1)
							&&!c.getForbidden()
							&&!getCell(row, col-2).getForbidden()
							&&!getCell(row, col-1).getForbidden()
							&&!JR.getColCells().contains(c.getType(),false)
							){
						if(tip){
						ar.add(c);
						ar.add(getCell(row,col-1));
						ar.add(getCell(row,col-2));}
						return true;
					}}
				if(col+2<=numCols-1){
					if(c.getType()==getCell(row, col+1).getType()
								&&getCell(row, col+1).getType()==getCell(row, col+2).getType()
								&&c!=getCell(row, col+1)&&
								c!=getCell(row, col+2)
								&&!c.getForbidden()
								&&!getCell(row, col+1).getForbidden()
								&&!getCell(row, col+2).getForbidden()
								&&!JR.getColCells().contains(c.getType(),false)){
						if(tip){
						ar.add(c);
						ar.add(getCell(row,col+1));
						ar.add(getCell(row,col+2));}
						return true;
					}
				}
					
				
				
			//}
		//}
	
		return false;
		
	}
	int emp=0;
	public void ShiftColumn(final int row,final int col,final int len ) {
			processing=true;
			inputTimer=4f;
			for(int y=numRows-1;y>=row;y--){
			final int yf=y;
			if(y<len+row && y>=row){
				Timer.schedule( new Timer.Task() {
					@Override
					public void run() {
						if(yf>=row&&yf<row+len){
							Timer.schedule( new Timer.Task() {
								@Override
								public void run() {
									getCell(yf, col).setDestroyed(true);
								}},0.1f);
						}
						else
						
				getCell(yf, col).setDestroyed(true);
					}},getCell(0, 0).getMaxTime());
				getCell(yf, col).setTipShowing(false);
			}
		}
			
		
		
		Timer.schedule( new Timer.Task() {
			@Override
			public void run() {
				if(level.getMode()==2){
					for(int i=0;i<len;i++){
						if(getCell(row+i, col).getPlateState()!=0){
						
						}
						getCell(row+i, col).BreakPlate();
						
						if(getCell(row+i, col).getBroken()){
							
							collected[0]+=1;
							getCell(row+i, col).setBroken(false);
						}
						
					}}
				for(int i=row;i<row+len;i++){
					for(int j=0;j<level.getRequiredCells().length;j++){
						if(getCell(i, col).getType()==level.getRequiredCells()[j]){
							collected[j]++;
						}
					}
					
				}
				
				
				
				
					
					
				
			for(int i=0;i<len;i++){
				float time;
				if(i>0){
					time=getCell(row, col).getMaxTime()*i;
				}
				else{
					time=getCell(row, col).getMaxTime()*i+0.01f;
				}
				
				
			final int iff=i;if(iff==0){
						for(int y=numRows-1;y>=row;y--){
							final int yf=y;
							if(y>row+len){
							Timer.schedule( new Timer.Task() {
								@Override
								public void run() {
								
								getCell(yf, col).setDestroyed(true);
								}},0.05f);}
							else{
								Timer.schedule( new Timer.Task() {
									@Override
									public void run() {
								getCell(yf, col).setDestroyed(true);
									}},0.05f);
									
							}
						}}
				Timer.schedule( new Timer.Task() {
					
					@Override
					public void run() {
						
						for(int k=row+len;k<numRows;k++){
							if(!getCell(k,col).getForbidden()||(k<numRows-1&&getCell(k,col).getForbidden()&&!getCell(k+1, col).getForbidden())
								
									){
							if(k!=row+len){
								
								getCell(k-iff, col).setMoveState(0);
								
							
								getCell(k-iff, col).setMoveState(2);
								
							
							}
							getCell(k-iff, col).setMoveState(0);
							
								getCell(k-iff, col).setMoveState(2);
								
							
							
								
					}		final int kf=k;
					
							Timer.schedule( new Timer.Task() {
								
								@Override
								public void run() {
									if(kf-iff<=numRows-len){
									if(!getCell(kf,col).getForbidden()){
										if(iff==len-1){
											if(kf+iff<numRows){
												if(!getCell(kf+iff-1,col).getForbidden()||!getCell(kf+iff-2,col).getForbidden())
												{
													getCell(kf-iff-1, col).setDestroyed(false);
												}
												
											}
											else{
												getCell(kf-iff-1, col).setDestroyed(false);
											}
										}
											
									}
									else if((kf<numRows-1 &&getCell(kf,col).getForbidden()&&!getCell(kf+1, col).getForbidden())){
									getCell(kf-iff-1, col).setDestroyed(false);
									
									}
									else{
										if(!getCell(kf,col).getForbidden())
										getCell(kf-iff-1, col).setDestroyed(false);
									}
									}
								}},(float) (getCell(0,0).getMaxTime())-0.04f
									);
						}
						
						
						if(iff==0||iff!=0){
							for(int r=row;r<numRows-1;r++){
								if(getCell(r,col).getForbidden()){
									getCell(r, col).setType(getCell(r+1, col).getType());
									getCell(r,col).UpdateTextures();
								}
								if(!getCell(r+1, col).getForbidden()){
							getCell(r, col).setType(getCell(r+1, col).getType());
							}
								else if(r+2<numRows){
									getCell(r, col).setType(getCell(r+2, col).getType());
								}
								
							
						}}
					}
					},time);
				
				
				
			}
			
			Timer.schedule( new Timer.Task() {
							@Override
							public void run() {
								
								FillColumn(col,len);
							}
						},getCell(0,0).getMaxTime()
								);
			}
		}, getCell(0, 0).getMaxTime()*2);
		
		///////////
		Timer.schedule( new Timer.Task() {
		
		@Override
		public void run() {
			for(int i=0;i<len;i++){
				if(row+i<numRows&&col<numCols && !getCell(row+i,col).getForbidden()){
		getCell(row+i, col).setMoveState(5);
			Anims.add(new DestroyAnim(0.6f, 
					getCell(row+i, col).getX(),getCell(row+i, col).getY()
					, CellType.getTexture(getCell(row+i, col).getType())));
			}
			}
		}}
	,getCell(0, 0).getMaxTime());
	
	}	
	boolean shiftin=false;
	int[]LastMatch=new int[]{0,0,0};
	public void ProcessMatches(Array<int[]>r,Array<int[]>c){
		///0=row
		///1=col
		///2=len
		if(r.size!=0&&c.size!=0){
		shiftin=true;
		}
		for(int i=0;i<r.size;i++){
			int[] a=r.get(i);
			////////UNITING ROWS
			for(int j=0;j<r.size;j++){
				int[]b=r.get(j);
				if(a[1]==b[1]&&Math.abs(a[0]-b[0])==1){
					if(a[0]<b[0]){
						a[2]=a[2]+b[2];
						r.set(i, a);
						r.removeIndex(j);
					}
					else{
						b[2]=a[2]+b[2];
						r.set(j, b);
						r.removeIndex(i);
					}
					
				}
				
			}
			////////////////
			///////UNITING ROWS+COLS
			for(int j=0;j<c.size;j++){
				int[] b= c.get(j);
				if(a[1]==b[1]&&a[0]>=b[0]&&a[0]<=b[0]+b[2]){
					if(b[0]+b[2]<a[0]+a[2]){
						b[2]=a[0]+a[2]-b[0];
						c.set(j, b);
						r.removeIndex(i);
					}
					else{
						if(r.contains(a, true))
						r.removeIndex(r.indexOf(a, true));
					}
				}
				else if(a[1]==b[1]&&(b[0]-a[0]==1||a[0]-b[0]-b[2]==1)){
					if(b[0]-a[0]==1){
						a[2]+=b[2];
						if(i<r.size){
						r.set(i,a);
						c.removeIndex(j);}
					}
					else
					if(a[0]-b[0]-b[2]==1){
							b[2]+=1;
							c.set(j,b);
							r.removeIndex(i);
							
					}
				}
			}
			//////////////
			
		}
		///////UNITING COLS
		if(c.size!=1)
		for(int i=0;i<c.size;i++){
			int[]a=c.get(i);
			for(int j=0;j<c.size;j++){
				int[] b= c.get(j);
				if(a[1]==b[1]&&a[0]>b[0]&&a[0]==b[0]+b[2]){
						b[2]=a[2]+b[2];
						c.set(j, b);
						c.removeIndex(i);
				}
				else if(a[1]==b[1]&&b[0]>a[0]&&b[0]==a[0]+a[2]){
					a[2]=a[2]+b[2];
					c.set(i, a);
					c.removeIndex(j);
				}
				
			}
		}
		////////////////////////
		
		////CHECK FOR SAME MATCHES
		for(int i=0;i<r.size;i++){
			int[] a=r.get(i);
			for(int j=0;j<c.size;j++){
				int[] b= c.get(j);
				if(a==b){

				
					c.removeIndex(j);
				}
			}
		}
		/////////
		
		/////////
		final Array<int[]>All=c;
		All.addAll(r);
		//SORTING
		boolean onerow=true;
		for(int j=0;j<All.size;j++){
		int max=All.get(j)[0];
		int maxnum=j;
			for(int i=j;i<All.size;i++){
			int[] a=All.get(i);
			if(max!=a[0]&&a[1]==All.get(maxnum)[1]){
					onerow=false;
				}
			if(a[0]>=max){
				
				max=a[0];
				maxnum=i;
			}
			
		}
			All.swap(j,maxnum );
		}
		for(int i=0;i<All.size;i++){
			int [] a=All.get(i);
			for(int j=0;j<All.size;j++){
				int [] b=All.get(j);
				if(a[0]==b[0]&&a[1]==b[1]&&a[2]==b[2] && i!=j){
				
					All.removeIndex(i);
				}
			}
		}
		
		///////////
		for(int i=0;i<All.size;i++){
			int [] a=All.get(i);
		
			if(getCell(a[0], a[1]).getPowerUpType()!=0)
			{
				if(getCell(a[0], a[1]).getPowerUpType()==1){
					for(int j=0;j<All.size;j++){
						int[]b=All.get(j);
						if(b[0]==a[0]&&a!=b){
							//All.removeIndex(j);
							//j--;//TODO justcheckin
						}
					}
					
				}
				else if(getCell(a[0], a[1]).getPowerUpType()==3){
					for(int j=0;j<All.size;j++){
						int[]b=All.get(j);
						if(b[0]==a[0]&&a!=b){
							All.removeIndex(j);
							j--;
						}
					}
				}
			}
		}
	int curlen=0;
			int row=-1;
			int col=-1;
		if(!onerow){
			
			int prevrow=All.get(0)[0];
			for(int i=0;i<All.size;i++){
	
			 int[] a=All.get(i);	
			if(a[0]==prevrow&&(i==0||a!=All.get(i-1))){
				
				if(checkTimer>0||a!=LastMatch){
					ColMatch(a[0], a[1], a[2]);
				}
				LastMatch=a;
			
				prevrow=a[0];
				All.removeIndex(i);
				
				i--;
				
			}
			else{
				
				break;
			}
			Timer.schedule(new Timer.Task() {
				
				@Override
				public void run() {
					shiftin=false;
				}
			}, getCell(0,0).getMaxTime()*All.size*a[2]);
		}}
		else{
			for(int i=0;i<All.size;i++){
				
				int [] a=All.get(i);
				
				curlen++;
				if(row==-1&&col==-1){
				row=a[0];
				col=a[1];
				}
		
				Timer.schedule(new Timer.Task() {
					
					@Override
					public void run() {
						shiftin=false;
					}
				}, getCell(0,0).getMaxTime()*All.size*a[2]);
			 if(curlen<=3){
				if(checkTimer>0||a!=LastMatch){
					boolean pu3check=true;
					if(i==3){
					if((getCell(a[0],a[1]).getType()!=getCell(All.get(i-1)[0],All.get(i-1)[1]).getType()
							||
							getCell(a[0],a[1]).getType()!=getCell(All.get(i-2)[0],All.get(i-2)[1]).getType())){
						pu3check=false;
					}
					if(a[2]>2&&getCell(a[0]+a[2]-1,a[1]).getType()!=getCell(a[0],a[1]).getType()){
						pu3check=false;
					}
					else if(All.get(i-1)[2]>2&&getCell((All.get(i-1)[0])+All.get(i-1)[2]-1,All.get(i-1)[1]).getType()!=getCell(All.get(i-1)[0],All.get(i-1)[1]).getType())
					{
						pu3check=false;
					}
					else if(All.get(i-2)[2]>2&&getCell(All.get(i-2)[0]+All.get(i-1)[2]-1,All.get(i-2)[1]).getType()!=getCell(All.get(i-2)[0],All.get(i-2)[1]).getType())
					{
						pu3check=false;
					}
					}
					ColMatch(a[0], a[1], a[2]);
					if(curlen==3){
						if((a[2]>1||All.get(i-2)[2]>2||All.get(i-1)[2]>2)&&
								a[0]==All.get(i-1)[0]&&a[0]==All.get(i-2)[0]&&pu3check
								){
							if(a[2]>2)
							getCell(a[0], a[1]).setPowerUpType(3);
							else if(All.get(i-2)[2]>2)
							getCell(a[0], All.get(i-2)[1]).setPowerUpType(3);
							else if(All.get(i-1)[2]>2){
							getCell(a[0], All.get(i-1)[1]).setPowerUpType(3);
							}
							//curlen=1;
							}
					}
				}
				
			 }
			 else
			 {
				if(i==a.length){
					
					if(curlen>3&&All.size>1){//TODO
						int rr=a[0],rc=a[1];
						
						ColMatch(a[0], a[1], a[2]);
					
						if(lasttype==getCell(a[0],a[1] ).getType()&&rr==All.get(i-1)[0]&&rr==All.get(i-2)[0]&&rr==All.get(i-3)[0]){
						
							if(a[2]>1||All.get(i-2)[2]>1||All.get(i-1)[2]>1||All.get(i-3)[2]>1){
								getCell(rr, rc).setPowerUpType(3);
							}
							else{
								getCell(rr, rc).setPowerUpType(1);
							}
							//curlen=1;
									}
					//	curlen=-1;
					
					}
					
				}
			}
			 LastMatch=a;
			}
		}
		
	}
	boolean isFilling=false;
	public void FillColumn(final int col,int len){
		CheckNeeded=false;
		
		{
			int ForbA=0;
			if(level.HasForbiddenCells()){
			
			for(int i=numRows-1;i>=numRows-len-1;i--){
				if(i>=0&&i<numRows){
				if(getCell(i, col).getForbidden()){
					ForbA++;
					}
				}
			}
			}
			final int ForbAm =ForbA;
			final int lenf=len;
		isFilling=true;
		
		Timer.schedule( new Timer.Task() {
			
			@Override
			public void run() {
		//for(int row=numRows-1;row>=numRows-lenf-ForbAm;row--)
				for(int row=numRows-lenf-ForbAm;row<numRows;row++)
		{
			final int rowf=row;
			
			
			Timer.schedule( new Timer.Task() {
				
				@Override
				public void run() {
					if(rowf<numRows&&col<numCols&&col>=0&&rowf>=0)
					if(getCell(rowf, col).getDestroyed()){
						
						getCell(rowf, col).setDestroyed(false);
						getCell(rowf,col).setMoveState(6);
						getCell(rowf,col).GenerateRandom(level.getAllowedCells());
						
						Timer.schedule( new Timer.Task() {
							@Override
							public void run() {
						if(rowf==0||!getCell(rowf-1, col).getDestroyed()){
							if(rowf==0){
								//System.out.println("wtf");
							}
							Timer.schedule( new Timer.Task() {
								
								@Override
								public void run() {
							CheckForNoMoves();}
								},5f);
								Timer.schedule( new Timer.Task() {
								
								@Override
								public void run() {	isFilling=false;}},0.1f);
							processing=false;
						
							}
							}},
								//getCell(0, 0).getMaxTime()*(numRows-rowf)*lenf-0.1f
								0);
							
						
						
					}
				}
			},getCell(0, 0).getMaxTime()*(numRows-row)+0.1f);
		}
	
			}},1.5f/(numRows+1-len));
		}
	}
	public void SwapCells(final Cell chosencell,int moveState1, final Cell cell,int moveState2) {
		chosencell.setTipShowing(false);cell.setTipShowing(false);
		CellType buf=cell.getType();
		CellType buf1=chosencell.getType();
		
		cell.setType(buf1);
		cell.setMoveState(moveState1);
		
		chosencell.setType(buf);
			
		
		chosencell.setMoveState(moveState2);
		LastMoved=cell;
			
	}
	public void CheckCols(){
		for(int i=0;i<numCols;i++){
			FillColumn(i, 0);
		}
	}

	//////////////////////////////////////////////////
	float checkTimer=0.2f;
	float timer=0.3f;
	int temp=0;
	public void update(float dt){
		if(inputTimer>0){
			inputTimer-=4f/300;
		}
		else{
			//inputTimer=4f;
			CheckForNoMoves();
		}
		if(CheckWin()){
		}
		
		if(timer>0){
			timer-=dt;
			
		}
		
		else{
			if(created
					&&!processing&&!isFilling&&!NoMoves&&HasUnprocessedMatches()
				
					){
			
				timer=0.3f;
				Timer.schedule(new Timer.Task() {
					
					@Override
					public void run() {	
						CheckMatch();
					}},0.3f);
			
		
			}
			
		}
		for(Animation da:Anims){
			if(!da.isFinished()){
				da.update(dt);
			}
			else{
				Anims.removeValue(da, true);
			}
		}
		if(checkTimer>=0&&CheckNeeded){
			checkTimer-=dt;
		}
		else{
			checkTimer=0.2f;
		}
		for(Cell c:gr){
			c.update(dt);
		}
		
	
}
	public boolean ShowingMessage(){
		if(!Anims.contains(GA, true)){
			return false;
		}
		else return true;
	}
	
	private boolean CheckWin() {
		boolean check=true;
		if(level.getMode()!=1){
		for(int i=0;i<collected.length;i++){
			if(collected[i]!=level.getAmount()[i]){
				check=false;
			}
		}
		
		}
		else{
			check=false;
		}
		return (check==true);
	}


	public void render(SpriteBatch sb){
		for(Cell c:gr){
			c.renderGrid(sb);
		}
		if(level.getMode()==2){
	for(int i: level.getPlatespos()){
		gr.get(i).renderPlates(sb);
	}}
		
		for(Cell c:gr){
			c.renderPU(sb);
		}
		
		
	for(Cell c:gr){
		//System.out.println(gr.indexOf(c, true));
		c.render(sb);
	}
	if(level.HasForbiddenCells()){
			for(int i:level.getForbpos()){
				//System.out.println(i);
				gr.get(i).renderForb(sb);
			}
		}
	for(Animation da:Anims){
		if(!da.isFinished()){
			da.render(sb);;
		}}
	}
	public boolean HasUnprocessedMatches(){
		
		if(level.getMode()==3){
			for(int i=0;i<numCols;i++){
				if(!getCell(0,i).getForbidden()&&JR.getColCells().contains(getCell(0,i).getType(), false)){
					return true;
				}
			}
		}
		for(int row=numRows-1;row>=0;row--){
			for(int col=0;col<numCols-2;col++){
				if(getCell(row, col).getType()==getCell(row, col+1).getType()
						&&getCell(row, col+1).getType()==getCell(row, col+2).getType()
						&&!getCell(row, col).getDestroyed()
						&&!getCell(row, col+1).getDestroyed()
						&&!getCell(row, col+2).getDestroyed()
						&&!getCell(row, col).getForbidden()
						&&!getCell(row, col+1).getForbidden()
						&&!getCell(row, col+2).getForbidden()
						){
					return true;
					
				
			}
			
			}
		
			}
		/////////////////COL/////////////////
		for(int col=numCols-1;col>=0;col--){
			for(int row=0;row<numRows-2;row++){
				if(getCell(row, col).getType()==getCell(row+1, col).getType()
						&&getCell(row+1, col).getType()==getCell(row+2, col).getType()
						&&!getCell(row, col).getDestroyed()
						&&!getCell(row+1, col).getDestroyed()
						&&!getCell(row+2, col).getDestroyed()
						&&!getCell(row, col).getForbidden()
						&&!getCell(row+1, col).getForbidden()
						&&!getCell(row+2, col).getForbidden()
						){

					return true;
				}
			}
			
			
		}
		return false;
	}
	
	public void AddScore(Cell c){
		int temp=CellWorth;
		if(level.getMode()==1){
			for(int i=0;i<level.getRequiredCells().length;i++){
				if(level.getRequiredCells()[i]==c.getType()){
					temp*=-1;
					break;
				}
			}
			}
			for(float m : multiplier){
						temp*=m;
					}
			if(c!= null)
					Anims.add(new PlusNumAnim(c.getX(), c.getY(),temp));
					score+=temp;
		
	}
	
	/////////////////////GETTERS///////////////////
	public Cell getCell(int row,int col){
		if(row>=0 && row<numRows&&col>=0&&col<numCols){
			return gr.get(row*numCols+col);
		}
		return null;
		
	}


	public Array<Cell> getGr() {
	return gr;
	}
	public float getBoardOffset() {
		return boardOffset;
	}
	public int getCellSize() {
		return cellSize;
	}
	public int getNumRows() {
		return numRows;
	}
	public int getNumCols() {
		return numCols;
	}

	public int getColOf(Cell chosencell) {
		return gr.indexOf(chosencell, true)%numCols;
		
	}
	public int getRowOf(Cell chosencell) {
		return gr.indexOf(chosencell, true)/numCols;
		
	}
	public void setMoved(boolean moved) {
		this.moved = moved;
	}
	public boolean getMoved(){
		return moved;
	}
	public int[] getCollected() {
		return collected;
	}
	public int getScore() {
		return score;
	}
	public boolean getClickable(){
		return clickable;
	}
	public boolean getProcessing(){
		return processing;
	}
	public int getCellWorth() {
		return CellWorth;
	}
	public boolean isFilling(){
		return isFilling;
	}
	public boolean getNoMoves(){
		return NoMoves;
	}
	public void setInputTimer(float inputTimer) {
		this.inputTimer = inputTimer;
	}
}
