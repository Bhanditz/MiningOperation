package com.fortyways.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.fortyways.jewel.JR;

import com.fortyways.jewel.game.LevelStorage;
import com.fortyways.jewel.game.PlayerSettings;
import com.fortyways.jewel.game.Stage;
import com.fortyways.ui.Graphic;
import com.fortyways.ui.NumberImage;


public class LevelSelectState extends State{
	private LevelStorage ls;
	private Graphic[][] Buttons;
	private TextureRegion img;
	private NumberImage [][]Numbers;
	private Graphic Back;
	private Graphic StateName;
	private Graphic[][] Stars;
	private Graphic Select; 
	private TextureRegion Stars0,Stars1,Stars2,Stars3;
	private int numCols;
	private int numRows;
	int boardOffset;
	int cellSize;
	private Stage stage;
	private Graphic LArrow;
	private Graphic RArrow;
	private int StartingPoint=0;
	private int LvlAm;
	private Graphic BG;
	public LevelSelectState(GSM gsm, Stage stage,int sp) {
		super(gsm);
		numRows=4;
		numCols=3;
		this.stage=stage;
		this.StartingPoint=sp;
		LvlAm=numRows*numCols;
		ls=JR.getLs();
		if(stage.getName()=="lava pits"){
			img=JR.res.getAtlas("pack").findRegion("RedCrystal");
			BG=new Graphic(JR.res.getAtlas("pack").findRegion("tstbg4"), JR.WIDTH/2, JR.HEIGHT/2);
		}
		else if(stage.getName()=="mossy caves"){
			img=JR.res.getAtlas("pack").findRegion("GreenCrystal");
			BG=new Graphic(JR.res.getAtlas("pack").findRegion("tstbg5"),  JR.WIDTH/2, JR.HEIGHT/2);
			
		}
		else{
		img=JR.res.getAtlas("pack").findRegion("Crystal");
		BG=new Graphic(JR.res.getAtlas("pack").findRegion("tstbg3"),  JR.WIDTH/2, JR.HEIGHT/2);
		}
		BG.setDimensions(BG.getWidth()*20, BG.getHeight()*20);
		Stars0=JR.res.getAtlas("pack").findRegion("0Stars");
		Stars1=JR.res.getAtlas("pack").findRegion("1Star");
		Stars2=JR.res.getAtlas("pack").findRegion("2Stars");
		Stars3=JR.res.getAtlas("pack").findRegion("3Stars");
		if(stage.getName()=="lava pits"){
			LArrow=new Graphic(JR.res.getAtlas("pack").findRegion("RCArrowLeft"), JR.WIDTH/2-200, 50);
			RArrow=new Graphic(JR.res.getAtlas("pack").findRegion("RCArrowRight"), JR.WIDTH/2+200, 50);
			StateName=new Graphic(JR.res.getAtlas("pack").findRegion("RCButton"), JR.WIDTH/2,JR.HEIGHT-50);
			
		}
		else if(stage.getName()=="mossy caves"){
			LArrow=new Graphic(JR.res.getAtlas("pack").findRegion("GArrowLeft"), JR.WIDTH/2-200, 50);
			RArrow=new Graphic(JR.res.getAtlas("pack").findRegion("GArrowRight"), JR.WIDTH/2+200, 50);
			StateName=new Graphic(JR.res.getAtlas("pack").findRegion("GButton"), JR.WIDTH/2,JR.HEIGHT-50);
			
		}
		else{
		LArrow=new Graphic(JR.res.getAtlas("pack").findRegion("CArrowLeft"), JR.WIDTH/2-200, 50);
		RArrow=new Graphic(JR.res.getAtlas("pack").findRegion("CArrowRight"), JR.WIDTH/2+200, 50);
		StateName=new Graphic(JR.res.getAtlas("pack").findRegion("CButton"), JR.WIDTH/2,JR.HEIGHT-50);
		
		}LArrow.setDimensions(50, 50);
		
		RArrow.setDimensions(50, 50);
		Back=new Graphic(JR.res.getAtlas("pack").findRegion("Back1"), JR.WIDTH/2, 20);
		//StateName=new Graphic(JR.res.getAtlas("pack").findRegion("CButton"), JR.WIDTH/2,JR.HEIGHT-50);
		StateName.setDimensions(350, 100);
		Select=new Graphic( JR.res.getAtlas("pack").findRegion("SelectLevel"),JR.WIDTH/2, JR.HEIGHT-50);
		
		Back.setDimensions(50, 50);
		cellSize=JR.WIDTH/numCols;
		boardOffset=(JR.HEIGHT - (cellSize*numRows))/2;
		Numbers=new NumberImage[numRows][numCols];
		Buttons=new Graphic[numRows][numCols];
		Stars=new Graphic[numRows][numCols];
		for(int row=0;row<numRows;row++){
			for(int col=0;col<numCols;col++){
				Graphic tr=new Graphic(img, col*cellSize+cellSize/2,
						row*cellSize+boardOffset+cellSize/2
						);
				Buttons[row][col]=tr;
				Buttons[row][col].setDimensions(100, 100);
				Numbers[row][col]=new NumberImage(Integer.toString((numCols*numRows)-row*numCols+col-2+StartingPoint), col*cellSize+cellSize/2+15,
						row*cellSize+boardOffset+cellSize/2-10);
				Numbers[row][col].setType(1);
				Numbers[row][col].setWidth(50);
				Numbers[row][col].setHeight(50);
				if(PlayerSettings.getPrefs().contains("levelstars"+Integer.toString((numCols*numRows)-row*numCols+col-3+StartingPoint))){
					if(PlayerSettings.getPrefs().getInteger("levelstars"+Integer.toString((numCols*numRows)-row*numCols+col-3+StartingPoint))==1){
						Stars[row][col]=new Graphic(Stars1, col*cellSize+cellSize/2,
								row*cellSize+boardOffset+cellSize/2-70
								);
					}
					else if(PlayerSettings.getPrefs().getInteger("levelstars"+Integer.toString((numCols*numRows)-row*numCols+col-3+StartingPoint))==2){
						Stars[row][col]=new Graphic(Stars2, col*cellSize+cellSize/2,
								row*cellSize+boardOffset+cellSize/2-70
								);
					}
					else if(PlayerSettings.getPrefs().getInteger("levelstars"+Integer.toString((numCols*numRows)-row*numCols+col-3+StartingPoint))==3){	
						Stars[row][col]=new Graphic(Stars3, col*cellSize+cellSize/2,
								row*cellSize+boardOffset+cellSize/2-70
								);
					}
					else{
							System.out.println("llllll");
						Stars[row][col]=new Graphic(Stars0, col*cellSize+cellSize/2,
								row*cellSize+boardOffset+cellSize/2-70
								);
					}
				}
				else{
				//System.out.println((numCols*numRows)-row*numCols+col-3);
					Stars[row][col]=new Graphic(Stars0, col*cellSize+cellSize/2,
							row*cellSize+boardOffset+cellSize/2-70
							);
				}
			}
		}
	}

	@Override
	public void handleInput() {
		if(Gdx.input.justTouched()){
			mouse.x=Gdx.input.getX();
			mouse.y=Gdx.input.getY();
			cam.unproject(mouse);
			
			if(Back.contains(mouse.x, mouse.y)){
				gsm.set(new TransDoorState(gsm, this,new StageSelectState(gsm)));
				//gsm.set(new MainMenuState(gsm));
			}
			if(LArrow.contains(mouse.x, mouse.y)){
				if(StartingPoint-LvlAm>=stage.getLevelNumToStartFrom()){
					JR.ps.setLastLevelScreen(StartingPoint-LvlAm);
					
					gsm.set(new LevelSelectState(gsm, stage, StartingPoint-LvlAm));
				}
			}
			if(RArrow.contains(mouse.x, mouse.y)){
				if(StartingPoint+LvlAm<stage.getLevelNumToStartFrom()+stage.getLevelAmount()){
					JR.ps.setLastLevelScreen(StartingPoint+LvlAm);
					gsm.set(new LevelSelectState(gsm, stage, StartingPoint+LvlAm));
					
				}
			}
			if(mouse.y>100){
			int cellSize=JR.WIDTH/numCols;
			int row=(int)((mouse.y-boardOffset)/cellSize);
			int col=(int)(mouse.x/cellSize);
			if(row>=0&&row<numRows &&col>=0&&col<numCols){
				if((numCols*numRows)-row*numCols+col-2-1+StartingPoint<ls.getLevelAmount()){
					if((numCols*numRows)-row*numCols+StartingPoint+col-2-1<=PlayerSettings.getUnlockedLevels()
							&&(numCols*numRows)-row*numCols+col-2-1+StartingPoint<stage.getLevelAmount()+stage.getLevelNumToStartFrom()){
						JR.ps.setLastLevelScreen(StartingPoint);
						gsm.set(new PlayState(gsm,stage,(numCols*numRows)-row*numCols+col-2-1+StartingPoint));}
					}
			}
			}
			
			
		}
	}

	@Override
	public void update(float dt) {
		handleInput();
		
	}

	@Override
	public void render(SpriteBatch sb) {
	sb.begin();
	sb.setColor(0.5f, 0.5f, 0.5f, 1);
	BG.render(sb);
	sb.setColor(1, 1, 1, 1);
	StateName.render(sb);
	Select.render(sb);
	Back.render(sb);
	if(StartingPoint-LvlAm>=stage.getLevelNumToStartFrom()){
	LArrow.render(sb);}
	if(StartingPoint+LvlAm<stage.getLevelNumToStartFrom()+stage.getLevelAmount()){
	RArrow.render(sb);
	}
	
	
	for(int row=0;row<numRows;row++){
		for(int col=0;col<numCols;col++){
			if((numCols*numRows)-row*numCols+col-2-1+StartingPoint<=PlayerSettings.getUnlockedLevels()
					&&ls.hasLevel((numCols*numRows)-row*numCols+col-2-1+StartingPoint-1)
					&&(numCols*numRows)-row*numCols+col-2-1+StartingPoint<stage.getLevelAmount()+stage.getLevelNumToStartFrom()){
			Buttons[row][col].render(sb);
			Numbers[row][col].render(sb);
			Stars[row][col].render(sb);
			}
			else if(((numCols*numRows)-row*numCols+col-2-1+StartingPoint)<stage.getLevelAmount()+stage.getLevelNumToStartFrom()){
				sb.draw(JR.res.getAtlas("pack").findRegion("Lock2"),col*cellSize+cellSize/2-50,
						row*cellSize+boardOffset+cellSize/2-50,100,100);
			}
		}
	}
	
	sb.end();
	}

}
