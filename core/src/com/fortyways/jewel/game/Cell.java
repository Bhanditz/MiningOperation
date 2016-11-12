package com.fortyways.jewel.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.fortyways.jewel.JR;
import com.fortyways.util.Box;

public class Cell extends Box{
	private TextureRegion img;
	private TextureRegion sel;
	private TextureRegion PR;
	private TextureRegion PC;
	private TextureRegion PX;
	private TextureRegion Plate;
	private TextureRegion Forb;
	private TextureRegion CurPU;
	private int PlateState=0;
	private boolean Broken=false;
	boolean IsDestroying=false;
	boolean Forbidden=false;
	/*
	 * 0-none
	 * 1-????
	 * 2
	 * 3
	 * 
	 */
	private TextureRegion Grid;
	private CellType type;
	private int PowerUpType=0;
	private int PUTypeMove=0;
	/*
	 * 0-null
	 * 1-row
	 * 2-col
	 * 3-plus(row+col)
	 * 
	 */
	private boolean Selected=false;
	private boolean destroyed;
	private boolean TipShowing=false;
	private float mtiptime=0.6f,tiptimer=0;
	private boolean DoneAnimating=true;
	float maxTime=0.15f;
	float timer=maxTime;
	private int moveState=0;
	float scale=1;
	
	/*
	 * 1-up
	 * 2-down
	 * 3-left
	 * 4-right
	 * 5-destroy
	 * 6-create
	 * 7-null
	 */
	
	public Cell(float x,float y,float width,float height,CellType[] Allowed) {
		super(x, y, width, height);
		GenerateRandom(Allowed);
		Plate=JR.res.getAtlas("pack").findRegion("Plate1");
		Forb=JR.res.getAtlas("pack").findRegion("Rock");
		Grid=JR.res.getAtlas("pack").findRegion("Grid");
		
	}
	public Cell(Cell c){
		super(c.getX(),c.getY(),c.getWidth(),c.getHeight());
		this.setType(c.getType());
		this.sel=c.sel;
	}
	
	
	public Cell() {
		// TODO Auto-generated constructor stub
	}


	public enum CellType{
		CRYSTAL4,REDCRYSTAL2,REDCRYSTAL3,REDCRYSTAL1,REDCRYSTAL4,REDCRYSTAL5,REDCRYSTAL6, CRYSTAL7,CRYSTAL3,CRYSTAL5,CRYSTAL2,CRYSTAL1,CRYSTAL6,BCRYSTAL7,BCRYSTAL5,BRUBY
		,GCRYSTAL1,GCRYSTAL2,GCRYSTAL3,GCRYSTAL4,GCRYSTAL5,CRATE1;
		public static CellType getRandomType(){
			CellType type=null;
			int rand=MathUtils.random(0, 17);
			if(rand==0){
				type=CellType.REDCRYSTAL2;
			}
			else if(rand==1){
				type=CellType.CRYSTAL4;
			}
			else if(rand==2){
				type=CellType.REDCRYSTAL3;
			}
			else if(rand==3){
				type=CellType.REDCRYSTAL1;
			}
			else if(rand==4){
				type=CellType.REDCRYSTAL4;
		
			}
			else if(rand==5){
				type=CellType.REDCRYSTAL5;
			}
			else if(rand==6){
				type=CellType.REDCRYSTAL6;	
			}
			else if(rand==7){
				type=CellType.CRYSTAL7;
			}
			else if(rand==8){
				type=CellType.CRYSTAL1;
			}
			else if(rand==9){
				type=CellType.CRYSTAL6;
			}
			else if(rand==10){
				type=CellType.CRYSTAL3;
			}
			else if(rand==11){
				type=CellType.CRYSTAL2;
			}
			else if(rand==12){
				type=CellType.CRYSTAL5;
			}
			else if(rand==13){
				type=CellType.GCRYSTAL1;
			}
			else if(rand==14){
				type=CellType.GCRYSTAL2;
			}
			else if(rand==15){
				type=CellType.GCRYSTAL3;
			}
			else if(rand==16){
				type=CellType.GCRYSTAL4;
			}
			else if(rand==17){
				type=CellType.GCRYSTAL5;
			}
			return type;
		}
		public static TextureRegion getTexture(CellType type){
			if(type==CellType.REDCRYSTAL1){
				return JR.res.getAtlas("pack").findRegion("RedCrystal1");
			}
			else
			if(type==CellType.REDCRYSTAL2){
				return JR.res.getAtlas("pack").findRegion("RedCrystal2");
			}
			else
			if(type==CellType.CRYSTAL4){
				return JR.res.getAtlas("pack").findRegion("Crystal4");
			}
			else
			if(type==CellType.REDCRYSTAL3){
				return JR.res.getAtlas("pack").findRegion("RedCrystal3");
			}
			else
			if(type==CellType.REDCRYSTAL4){
				return JR.res.getAtlas("pack").findRegion("RedCrystal4");
			}
			else
			if(type==CellType.REDCRYSTAL5){
				return JR.res.getAtlas("pack").findRegion("RedCrystal5");
			}
			else
				if(type==CellType.REDCRYSTAL6){
					return JR.res.getAtlas("pack").findRegion("RedCrystal6");
				}
			else
			if(type==CellType.CRYSTAL7){
				return JR.res.getAtlas("pack").findRegion("Crystal7");
			}
			else
			if(type==CellType.CRYSTAL2){
				return JR.res.getAtlas("pack").findRegion("Crystal2");
			}
			else
			if(type==CellType.CRYSTAL1){
				return JR.res.getAtlas("pack").findRegion("Crystal1");
			}
			else
			if(type==CellType.CRYSTAL3){
				return JR.res.getAtlas("pack").findRegion("Crystal3");
			}
			else
			if(type==CellType.CRYSTAL5){
				return JR.res.getAtlas("pack").findRegion("Crystal5");
			}else
			if(type==CellType.CRYSTAL6){
				return JR.res.getAtlas("pack").findRegion("Crystal6");
			}else
			if(type==CellType.BCRYSTAL7){
				return JR.res.getAtlas("pack").findRegion("BCrystal7");
			}
			else
			if(type==CellType.BRUBY){
				return JR.res.getAtlas("pack").findRegion("RubyB");
			}
			else
			if(type==CellType.BCRYSTAL5){
				return JR.res.getAtlas("pack").findRegion("BCrystal5");
			}
			else
			if(type==CellType.GCRYSTAL1){
				return JR.res.getAtlas("pack").findRegion("GCrystal1");
			}
			else
			if(type==CellType.GCRYSTAL2){
				return JR.res.getAtlas("pack").findRegion("GCrystal2");
			}
			else
			if(type==CellType.GCRYSTAL3){
				return JR.res.getAtlas("pack").findRegion("GCrystal3");
			}
			else
			if(type==CellType.GCRYSTAL4){
				return JR.res.getAtlas("pack").findRegion("GCrystal4");
			}
			else
			if(type==CellType.GCRYSTAL5){
				return JR.res.getAtlas("pack").findRegion("GCrystal5");
			}
			else
				if(type==CellType.CRATE1){
					return JR.res.getAtlas("pack").findRegion("Crate");
				}
			return null;
		}
	}
	public void GenerateRandom(CellType[] Allowed){
		//float rand=MathUtils.random(0, 100);
	
		sel=JR.res.getAtlas("pack").findRegion("Selected");
		int rand=MathUtils.random(0,Allowed.length-1);
		this.setType(Allowed[rand]);
		this.img=CellType.getTexture(this.getType());
			}

	public void GenerateOtherThan(CellType type,CellType[] Allowed){
		while(this.getType()==type){
			int rand=MathUtils.random(0,Allowed.length-1);
			this.setType(Allowed[rand]);
		}
	}
	public void renderGrid(SpriteBatch sb){
		if(!Forbidden){
			sb.setColor(1, 1, 1, 0.3f);
			sb.draw(Grid,getX()-getWidth()/2,getY()-getHeight()/2,getWidth(),getHeight());	
			sb.setColor(1, 1, 1, 1);
		}
	
	}
	public void render(SpriteBatch sb){
		if(destroyed&&!Forbidden){
			if(DestroyedTimer>0){
			//	sb.draw(img,getX()-getWidth()/2,getY()-getHeight()/2,getWidth(),getHeight());	
				
			}
				 if(moveState==2){
					sb.draw(img,getX()-getWidth()/2,getY()-getHeight()/2-getHeight()*(maxTime-timer)*(1/maxTime),scale*getWidth(),scale*getHeight());
					
				 }
				 else if(moveState==5 ){
						sb.draw(img,getX()-getWidth()/2+getWidth()*(maxTime-timer)*(1/maxTime),getY()-getHeight()/2,getWidth()-getWidth()*(maxTime-timer)*(1/maxTime),getHeight()-getHeight()*(maxTime-timer)*(1/maxTime));
						//sb.draw(img,getX()-getWidth()/2,getY()-getHeight()/2-(getY())*(maxTime-timer/maxTime),getWidth(),getHeight());
						
					}
				
		}
		
		if(!destroyed&&!Forbidden//||destroyed
				){
		if(moveState==0){
			
			sb.draw(img,getX()-getWidth()/2,getY()-getHeight()/2,scale*getWidth(),scale*getHeight());	
		}
		else if(moveState==1){
		
			sb.draw(img,getX()-getWidth()/2,getY()-getHeight()/2+getHeight()*(maxTime-timer)*(1/maxTime),scale*getWidth(),scale*getHeight());
		}
		else if(moveState==2){
		
		sb.draw(img,getX()-getWidth()/2,getY()-getHeight()/2-getHeight()*(maxTime-timer)*(1/maxTime),scale*getWidth(),scale*getHeight());
		}
		else if(moveState==3){
		
		sb.draw(img,getX()-getWidth()/2+getWidth()*(maxTime-timer)*(1/maxTime),getY()-getHeight()/2,scale*getWidth(),scale*getHeight());
		}
		else if(moveState==4&&img!=null){
		
		sb.draw(img,getX()-getWidth()/2-getWidth()*(maxTime-timer)*(1/maxTime),getY()-getHeight()/2,scale*getWidth(),scale*getHeight());			
		}
		else if(moveState==5 ){
			sb.draw(img,getX()-getWidth()/2+getWidth()*(maxTime-timer)*(1/maxTime),getY()-getHeight()/2,getWidth()-getWidth()*(maxTime-timer)*(1/maxTime),getHeight()-getHeight()*(maxTime-timer)*(1/maxTime));
			//sb.draw(img,getX()-getWidth()/2,getY()-getHeight()/2-(getY())*(maxTime-timer/maxTime),getWidth(),getHeight());
			
		}
		else if(moveState==6 ){
			sb.draw(img,getX()-getWidth()/2,getY()-getHeight()/2+(JR.HEIGHT-700)*(timer/maxTime),scale*getWidth(),scale*getHeight());
		}
		
		else{
			
		}
		if(Selected){
			sb.draw(sel,getX()-getWidth()/2,getY()-getHeight()/2,getWidth(),getHeight());
			
		}
	
		}
		
	}
	public void renderPU(SpriteBatch sb){
		if(PowerUpType==1&&PC!=null){
			sb.setColor(1, 1, 1, 0.9f);
			sb.draw(PR,getX()-getWidth()/2,getY()-getHeight()/2,getWidth(),getHeight());	
			sb.setColor(1, 1, 1, 1);
		}
		else if(PowerUpType==2&& PR!=null){
			sb.setColor(1, 1, 1, 0.9f);
			sb.draw(PC,getX()-getWidth()/2,getY()-getHeight()/2,getWidth(),getHeight());	
			sb.setColor(1, 1, 1, 1);
		}
		else if(PowerUpType==3&& PX!=null){
			sb.setColor(1, 1, 1, 0.9f);
			sb.draw(PX,getX()-getWidth()/2,getY()-getHeight()/2,getWidth(),getHeight());	
			sb.setColor(1, 1, 1, 1);
		}
	}
	public void renderPlates(SpriteBatch sb){
		if(PlateState!=0){
			sb.draw(Plate,getX()-getWidth()/2,getY()-getHeight()/2,getWidth(),getHeight());	
		}
	}
	public void renderForb(SpriteBatch sb){
		if(Forbidden){
			if(moveState==2){
				sb.draw(img,getX()-getWidth()/2,getY()-getHeight()/2-getHeight()*(maxTime-timer)*(1/maxTime),getWidth(),getHeight());
			}
			//System.out.println("ggggggg");
			sb.draw(Forb,getX()-getWidth()/2,getY()-getHeight()/2,getWidth(),getHeight());
		}
	}
	
	
	float DestroyedTimer=0.09f;
	public void update(float dt){
		if(TipShowing){
			if(tiptimer<mtiptime){
				tiptimer+=mtiptime/100;
				if(tiptimer<mtiptime/2){
					scale+=mtiptime/100;
				}
				else{
					scale-=mtiptime/100;
				}
			}
			else{
				tiptimer=0;
				scale=1;
				TipShowing=false;
			}
		}
		
		
		if(moveState!=0){
			//UpdateTextures();
			DoneAnimating=false;
			if(timer>0){
				timer-=maxTime/10;
			}
			else{
				DoneAnimating=true;
				if(moveState==8){
					PowerUpType=0;
					PUTypeMove=0;
				}
				moveState=0;
				timer=maxTime;
				if(PUTypeMove!=0){
					//PowerUpType=0;
					//PUTypeMove=0;
				}
				UpdateTextures();
			}
		}
		
		else{
			UpdateTextures();
		}
	
	
		
	}
	
	public void UpdateTextures(){
		img=CellType.getTexture(type);
		}
public void UpdatePUTextures(){
	if(getType()==CellType.REDCRYSTAL1){
		PR=JR.res.getAtlas("pack").findRegion("PR2");
		PC=JR.res.getAtlas("pack").findRegion("PC2");
		PX=JR.res.getAtlas("pack").findRegion("PX2");
	}
	else
	if(getType()==CellType.REDCRYSTAL2){
		PR=JR.res.getAtlas("pack").findRegion("PR2");
		PC=JR.res.getAtlas("pack").findRegion("PC2");
		PX=JR.res.getAtlas("pack").findRegion("PX2");
	}
	else
	if(getType()==CellType.CRYSTAL4){
		PR=JR.res.getAtlas("pack").findRegion("PR1");
		PC=JR.res.getAtlas("pack").findRegion("PC1");
		PX=JR.res.getAtlas("pack").findRegion("PX1");
	}
	else
	if(getType()==CellType.REDCRYSTAL3){
		PR=JR.res.getAtlas("pack").findRegion("PR2");
		PC=JR.res.getAtlas("pack").findRegion("PC2");
		PX=JR.res.getAtlas("pack").findRegion("PX2");
	}
	else
	if(getType()==CellType.REDCRYSTAL4){
		PR=JR.res.getAtlas("pack").findRegion("PR2");
		PC=JR.res.getAtlas("pack").findRegion("PC2");
		PX=JR.res.getAtlas("pack").findRegion("PX2");
	}
	else
	if(getType()==CellType.REDCRYSTAL5){
		PR=JR.res.getAtlas("pack").findRegion("PR2");
		PC=JR.res.getAtlas("pack").findRegion("PC2");
		PX=JR.res.getAtlas("pack").findRegion("PX2");
	}
	else
	if(getType()==CellType.CRYSTAL7){
		PR=JR.res.getAtlas("pack").findRegion("PR1");
		PC=JR.res.getAtlas("pack").findRegion("PC1");
		PX=JR.res.getAtlas("pack").findRegion("PX1");
	}
	else
	if(getType()==CellType.CRYSTAL1){
	PR=JR.res.getAtlas("pack").findRegion("PR1");
	PC=JR.res.getAtlas("pack").findRegion("PC1");
	PX=JR.res.getAtlas("pack").findRegion("PX1");
	}
	else
	if(getType()==CellType.CRYSTAL3){
	PR=JR.res.getAtlas("pack").findRegion("PR1");
	PC=JR.res.getAtlas("pack").findRegion("PC1");
	PX=JR.res.getAtlas("pack").findRegion("PX1");
	}
	else
	if(getType()==CellType.CRYSTAL2){
		PR=JR.res.getAtlas("pack").findRegion("PR1");
		PC=JR.res.getAtlas("pack").findRegion("PC1");
		PX=JR.res.getAtlas("pack").findRegion("PX1");
	}
	else
	if(getType()==CellType.CRYSTAL5){
		PR=JR.res.getAtlas("pack").findRegion("PR1");
		PC=JR.res.getAtlas("pack").findRegion("PC1");
		PX=JR.res.getAtlas("pack").findRegion("PX1");
	}
	else
	if(getType()==CellType.CRYSTAL6){
		PR=JR.res.getAtlas("pack").findRegion("PR1");
		PC=JR.res.getAtlas("pack").findRegion("PC1");
		PX=JR.res.getAtlas("pack").findRegion("PX1");
	}
	else
		if(getType()==CellType.BCRYSTAL7){
			PR=JR.res.getAtlas("pack").findRegion("PR1");
			PC=JR.res.getAtlas("pack").findRegion("PC1");
			PX=JR.res.getAtlas("pack").findRegion("PX1");
		}
		else
	if(getType()==CellType.GCRYSTAL1){
	PR=JR.res.getAtlas("pack").findRegion("PR3");
	PC=JR.res.getAtlas("pack").findRegion("PC3");
	PX=JR.res.getAtlas("pack").findRegion("PX3");
	}
	else
if(getType()==CellType.GCRYSTAL2){
PR=JR.res.getAtlas("pack").findRegion("PR3");
PC=JR.res.getAtlas("pack").findRegion("PC3");
PX=JR.res.getAtlas("pack").findRegion("PX3");
}
else
if(getType()==CellType.GCRYSTAL3){
PR=JR.res.getAtlas("pack").findRegion("PR3");
PC=JR.res.getAtlas("pack").findRegion("PC3");
PX=JR.res.getAtlas("pack").findRegion("PX3");
}
else
if(getType()==CellType.GCRYSTAL4){
PR=JR.res.getAtlas("pack").findRegion("PR3");
PC=JR.res.getAtlas("pack").findRegion("PC3");
PX=JR.res.getAtlas("pack").findRegion("PX3");
}
else
if(getType()==CellType.GCRYSTAL5){
PR=JR.res.getAtlas("pack").findRegion("PR3");
PC=JR.res.getAtlas("pack").findRegion("PC3");
PX=JR.res.getAtlas("pack").findRegion("PX3");
}
	else
		if(getType()==CellType.BRUBY){
			PR=JR.res.getAtlas("pack").findRegion("PR1");
			PC=JR.res.getAtlas("pack").findRegion("PC1");
			PX=JR.res.getAtlas("pack").findRegion("PX1");
		}
	else
		if(getType()==CellType.BCRYSTAL5){
			PR=JR.res.getAtlas("pack").findRegion("PR1");
			PC=JR.res.getAtlas("pack").findRegion("PC1");
			PX=JR.res.getAtlas("pack").findRegion("PX1");
		}
}
	public boolean getDestroyed() {
		return destroyed;
	}
	public void setSelected(boolean selected) {
		Selected = selected;
	}
	public boolean getSelected() {
		return Selected;
	}
	public void setMoveState(int moveState) {
		if(this.moveState!=8){
			this.moveState = moveState;	
		}
		
		//timer=maxTime;
		if(moveState==0){
			UpdateTextures();;
			DoneAnimating=true;
			timer=maxTime;
		}
		if(moveState==8){
			if(PowerUpType>0)
			PUTypeMove=PowerUpType;
		}
		
		
		
	}
	public void setDestroyed(boolean destroyed) {
		if(destroyed){
			IsDestroying=true;
		}
		else{
			IsDestroying=false;
		}
		this.destroyed = destroyed;
	}
	public boolean CheckTimer() {
		return timer==maxTime;
		
	}
	public int getMoveState() {
		return moveState;
	}
	public boolean getDoneAnimating(){
		return DoneAnimating;
	}
	public float getTimer() {
		return timer;
	}
	public float getMaxTime() {
		return maxTime;
	}
	public int getPowerUpType() {
		return PowerUpType;
	}
	
	public void BreakPlate(){
		if(PlateState==3){
			PlateState=0;
			Broken=true;
		}
		if(PlateState!=0){
			PlateState++;
			Plate=JR.res.getAtlas("pack").findRegion("Plate"+PlateState);
		
		}
	}
	public void setPowerUpType(int powerUpType) {
	
		PowerUpType = powerUpType;
		UpdatePUTextures();
	}
	public void setImg(TextureRegion img) {
		this.img = img;
	}
	public boolean getBroken(){
		return Broken;
	}
	public void setBroken(boolean Br){
		Broken=Br;
	}
	public void setPlateState(int i) {
		PlateState=i;
		if(i==5){
			destroyed=true;
		}
		if(i==6){
			destroyed=false;
		}
	}
	public void setIsDestroying(boolean isDestroying) {
		IsDestroying = isDestroying;
	}
	public boolean getIsDestroying() {
		return IsDestroying;
	}
	public TextureRegion getPlate() {
		return Plate;
	}
	public int getPlateState() {
		return PlateState;
	}
	public void setForbidden(boolean forbidden) {
		Forbidden = forbidden;
	}
	public boolean getForbidden() {
		return Forbidden;
	}
	public void setMaxTime(float maxTime) {
		this.maxTime = maxTime;
	}
	public void setTimer(float timer) {
		this.timer = timer;
	}
	public CellType getType() {
		return type;
	}
	public void setType(CellType type) {
		this.type = type;
	}
	public void setPUTypeMove(int pUTypeMove) {
		PUTypeMove = pUTypeMove;
	}
	public void setForb(TextureRegion forb) {
		Forb = forb;
	}
	public TextureRegion getCurPU() {
		return CurPU;
	}
	public void setTipShowing(boolean tipShowing) {
		TipShowing = tipShowing;
		tiptimer=0;
		scale=1;
	}
	public boolean getTipShowing() {
		return TipShowing;
	}
}
