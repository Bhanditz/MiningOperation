package com.fortyways.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Timer;
import com.fortyways.animation.FillScreenAnim;
import com.fortyways.animation.GoalAnim;
import com.fortyways.animation.NumberSpanAnim;
import com.fortyways.jewel.JR;
import com.fortyways.jewel.game.Cell;
import com.fortyways.jewel.game.Grid;
import com.fortyways.jewel.game.Level;
import com.fortyways.jewel.game.PlayerSettings;
import com.fortyways.jewel.game.Stage;
import com.fortyways.jewel.game.Cell.CellType;
import com.fortyways.ui.Graphic;
import com.fortyways.ui.NumberImage;
import com.fortyways.util.SimpleDirectionGestureDetector;

public class PlayState extends State{
	//////////////GAMEPLAY/////////////////////////
	private Grid grid;
	private boolean chosen=false;
	private Cell chosencell=null;
	private Level level;
	private int[] collected;
	private int moves=0;
	private boolean failed=false;
	private boolean completed=false;
	private int score;
	private int stars=0;
	private boolean processing=false;
	private float selecttimer=0.7f;
	private boolean paused=false;
	private GoalAnim GA;
	private boolean NoMovesCheck;
	private FillScreenAnim StageClearAnim;
	private boolean lastlevel=false;
	///////////////////////////////////
	
	//////////LAYOUT/////////
	private Graphic LevelClear;
	private NumberImage LevelNumber;

	
	private NumberImage FinalScore;
	private Graphic MenuButton1;
	private Graphic MenuButtonf;
	private Graphic NextButton;
	private Graphic RetryButton;
	private Graphic[] StarsAnim;
	private Graphic Menu;
	private TextureRegion[] imgs;
	private NumberImage[] goal;
	private TextureRegion[] xes;
	private TextureRegion MenuBg;
	private NumberImage MovesScore;
	private TextureRegion BG;
	private TextureRegion MenuBg2;
	private NumberImage TotScore;
	Graphic ProgressFrame;
	TextureRegion tr;
	 TextureRegion pb ;
	Graphic ProgressBar;
	Graphic[] ProgressPoints;
	private Graphic Stars;
	
	private Stage stage;
	float pbwidth;
	float totalam=0;
	private NumberSpanAnim nsa;
	private int LvlNum;final int numRows=9;final int numCols=8;
	//////////////////////////////
	public PlayState(GSM gsm, Stage st,int num) {
		super(gsm);
		
		//Sound sound = Gdx.audio.newSound(Gdx.files.internal("Pickup_Coin2.wav"));
		
		//////////LEVEL AND GRID/////////////
		//System.out.println(num);
		this.stage=st;
		this.LvlNum=num;
		this.level=stage.getLvl(LvlNum);
		//TODO PB SET UP
		ProgressFrame=new Graphic(JR.res.getAtlas("pack").findRegion("ProgressFrame"),108,55);
		ProgressFrame.setDimensions(ProgressFrame.getWidth()*2, ProgressFrame.getHeight()*3);
		pb=JR.res.getAtlas("pack").findRegion("ProgressBar");
		tr=JR.res.getAtlas("pack").findRegion("ProgressBar");
		//tr.setRegionWidth(0);
		ProgressBar=new Graphic(tr, 27,40);
	//	ProgressBar.setDimensions(ProgressBar.getWidth(), ProgressBar.getHeight());
		pbwidth=ProgressBar.getWidth();
		
		for(int i=0;i<level.getAmount().length;i++){
			totalam+=level.getAmount()[i];
		}
		
		ProgressBar.setDimensions(0, ProgressBar.getHeight()*3);
		
		ProgressPoints=new Graphic[3];
		for(int i=0;i<3;i++){
			ProgressPoints[i]=new Graphic(JR.res.getAtlas("pack").findRegion("ProgressPoint"), 27,50);
			
			ProgressPoints[i].setDimensions(ProgressPoints[i].getWidth()*3, ProgressPoints[i].getHeight()*3);
			
		}
		if(totalam<level.getMoves())
		{
		ProgressPoints[0].setX(27+(level.getScore3star()*pbwidth)/(20*2*level.getMoves()));
		ProgressPoints[1].setX(27+(level.getScore2star()*pbwidth)/(20*2*level.getMoves()));
		ProgressPoints[2].setX(27+(level.getScore1star()*pbwidth)/(20*2*level.getMoves()));
		}
		else
		{
			ProgressPoints[0].setX(27+(level.getScore3star()*pbwidth)/(20*3*level.getMoves()));
			ProgressPoints[1].setX(27+(level.getScore2star()*pbwidth)/(20*3*level.getMoves()));
			ProgressPoints[2].setX(27+(level.getScore1star()*pbwidth)/(20*3*level.getMoves()));
			
		}
		
		if(level.getNumber()==
				stage.getLevelNumToStartFrom()
				+stage.getLevelAmount()-1){
			lastlevel=true;
		}
		GA=new GoalAnim(level, cam, mouse);
		grid=new Grid(9, 8,level);
		grid.CheckBeforeStart();
		if(level.getMode()==0|| level.getMode()==3){
		collected=new int[level.getAmount().length];
		for(int i=0;i<collected.length;i++){
		collected[i]=0;
		}
		
		goal=new NumberImage[level.getRequiredCells().length];
		imgs=new TextureRegion[level.getRequiredCells().length];
		
		for(int i=0;i<level.getRequiredCells().length;i++){
			if(i==0||i==1||i==2){
			goal[i]=new NumberImage("0/"+level.getAmount()[i], 45, JR.HEIGHT-60-i*30);
			goal[i].setSize(10);
			goal[i].Dist=2;
			imgs[i]=CellType.getTexture(level.getRequiredCells()[i]);}
			else if(i==3||i==4||i==5){
			goal[i]=new NumberImage("0/"+level.getAmount()[i], 115, JR.HEIGHT-60-(i-3)*30);
			goal[i].setSize(10);
			goal[i].Dist=2;
			imgs[i]=CellType.getTexture(level.getRequiredCells()[i]);
			}
		}
		}
		else if(level.getMode()==1){
			collected=new int[level.getAmount().length];
			for(int i=0;i<collected.length;i++){
			collected[i]=0;
			}
			goal=new NumberImage[level.getRequiredCells().length];
			imgs=new TextureRegion[level.getRequiredCells().length];
			xes=new TextureRegion[level.getRequiredCells().length];
			for(int i=0;i<level.getRequiredCells().length;i++){
				if(i==0||i==1||i==2){
				goal[i]=new NumberImage("0/"+level.getAmount()[i], 45, JR.HEIGHT-60-i*30);
				goal[i].setSize(10);
				goal[i].Dist=2;
				imgs[i]=CellType.getTexture(level.getRequiredCells()[i]);
				xes[i]=JR.res.getAtlas("pack").findRegion("Skull");
				}
				else if(i==3||i==4||i==5){
				goal[i]=new NumberImage("0/"+level.getAmount()[i], 115, JR.HEIGHT-60-(i-3)*30);
				goal[i].setSize(10);
				goal[i].Dist=2;
				imgs[i]=CellType.getTexture(level.getRequiredCells()[i]);
				xes[i]=JR.res.getAtlas("pack").findRegion("Skull");
				}
			}
		}
		else if(level.getMode()==2){
		collected=new int[1];
		collected[0]=0;
		goal=new NumberImage[1];
		goal[0]=new NumberImage("0/"+level.getAmount()[0], 45, JR.HEIGHT-60);
		goal[0].setSize(10);
		goal[0].Dist=2;
		imgs=new TextureRegion[1];
		imgs[0]=JR.res.getAtlas("pack").findRegion("Plate1");
		}
		if(JR.ps.getSwipe()){
		setSwipes();}
		//////////////////////////////////
		//completed=true;///////////////REMOVE!!!!
		///////////LAYOUT///////////////
		StarsAnim=new Graphic[3];
		StarsAnim[0]=new Graphic(JR.res.getAtlas("pack").findRegion("Star"),JR.WIDTH/2-52+10,JR.HEIGHT/2-5-10);
		StarsAnim[1]=new Graphic(JR.res.getAtlas("pack").findRegion("Star"),JR.WIDTH/2-10+10,JR.HEIGHT/2+5-10);
		StarsAnim[2]=new Graphic(JR.res.getAtlas("pack").findRegion("Star"),JR.WIDTH/2+32+10,JR.HEIGHT/2-5-10);
		
		Stars=new Graphic(JR.res.getAtlas("pack").findRegion("0Stars"),JR.WIDTH/2-10,JR.HEIGHT/2);
		Stars.setDimensions(125, 50);
		
		LevelNumber=new NumberImage(Integer.toString(level.getNumber()+1), JR.WIDTH/2-10, 20);
		TotScore=new NumberImage("0", 100, 45);
		LevelNumber.setColor(Color.BLACK);
		
		LevelNumber.setSize(20);
		BG=stage.getBg();
		//BG=JR.res.getAtlas("pack").findRegion("tstbg");
		FinalScore=new NumberImage("0", JR.WIDTH/2, JR.HEIGHT/2+60);
		MenuButton1=new Graphic(JR.res.getAtlas("pack").findRegion("MenuButton3"),JR.WIDTH-90,JR.HEIGHT-70);
		MenuButton1.setDimensions(32*4, 15*4);
		MenuButtonf=new Graphic(JR.res.getAtlas("pack").findRegion("MenuButton2"),JR.WIDTH/2-70,JR.HEIGHT/2-75);
		MenuButtonf.setDimensions(100, 100);
		NextButton=new Graphic(JR.res.getAtlas("pack").findRegion("NextButton"),JR.WIDTH/2+50,JR.HEIGHT/2-75);
		NextButton.setDimensions(100, 100);
		RetryButton=new Graphic(JR.res.getAtlas("pack").findRegion("RetryButton1"),JR.WIDTH/2+50,JR.HEIGHT/2-75);
		RetryButton.setDimensions(100, 100);
		Menu=new Graphic(JR.res.getAtlas("pack").findRegion("Menu5"),JR.WIDTH/2,JR.HEIGHT/2);
	
		//MenuText=new TextImage("menu", JR.WIDTH-110, JR.HEIGHT-85,16);
		

		Menu.setDimensions(300, 300);
		
	
		MovesScore=new NumberImage("0", JR.WIDTH/2-17, JR.HEIGHT-100);
		MovesScore.setColor(Color.BLACK);
		
		
		MenuBg=JR.res.getAtlas("pack").findRegion("MOMenu1");
		MenuBg2=JR.res.getAtlas("pack").findRegion("MOMenu2");
		
		
		////////////////////////////
		
		}

	@Override
	public void handleInput() {
		if(Gdx.input.justTouched()){
			
			mouse.x=Gdx.input.getX();
			mouse.y=Gdx.input.getY();
			
			cam.unproject(mouse);
			
			if((completed||paused)&&GA.isFinished()){
				 MenuInput();
				
			}
			if(MenuButton1.contains(mouse.x, mouse.y)&&GA.isFinished()){
				if(!completed)
				paused=!paused;
			}
			if(!completed&&!grid.getProcessing()&&GA.isFinished()){
			//	grid.ShiftColumn(0, 1, numRows);
		
			
			//if(!JR.ps.getSwipe()){here
				int row=(int)((mouse.y-grid.getBoardOffset())/grid.getCellSize());
				int col=(int)(mouse.x/grid.getCellSize());
			if(row>=0&&row<numRows&&col>=0&&col<numCols)
			if(!chosen){
				if(!processing&&!grid.getNoMoves()){
				chosencell=grid.getCell(row, col);
				chosencell.setSelected(true);
				chosen=true;}
			}
			else{
				if(!processing&&!grid.getNoMoves()){
				chosencell.setSelected(false);
				MoveCells(row, col);
				chosen=false;}
			}
			//}here
			
			
			
			
		}
			 
		}
	}
	public void MenuInput(){
		if(paused){
			if(MenuButtonf.contains(mouse.x, mouse.y)){
				
				gsm.set(new TransDoorState(gsm, this,new LevelSelectState(gsm,stage,JR.ps.getLastLevelScreen())));
			}
		else if(RetryButton.contains(mouse.x, mouse.y)){
			
					if(level.getNumber()<=JR.getLs().getLevelAmount()){
					gsm.set(new PlayState(gsm,stage,LvlNum));}
			
			}
			
			
		}
	
			
					
					
			
			if(MenuButtonf.contains(mouse.x, mouse.y)){
				gsm.set(new TransDoorState(gsm, this,new LevelSelectState(gsm,stage,JR.ps.getLastLevelScreen())));
			}
		else if(RetryButton.contains(mouse.x, mouse.y)&&( failed)){
				//if(failed){
					
					if(level.getNumber()<=JR.getLs().getLevelAmount()){
					gsm.set(new PlayState(gsm,stage,LvlNum));}
				//}
			}
			else if(NextButton.contains(mouse.x, mouse.y)){
				if(!failed	&& completed && !lastlevel){
					
					if(level.getNumber()+1<=JR.getLs().getLevelAmount()){
						if(LvlNum<stage.getLevelNumToStartFrom()+stage.getLevelAmount())
					gsm.set(new PlayState(gsm,stage,LvlNum+1));}
				}
			}
			//}
		
	}
	public boolean CheckWin()
	{
		boolean check=true;
		
		for(int i=0;i<collected.length;i++){
			if(collected[i]<level.getAmount()[i]){
				check=false;
				
			}
		}
		if(level.getMode()==1)
		{
			if(moves>=level.getMoves()){
				check=true;
			}
			else
				check=false;
		}
		
		return (check==true);
			
		
	}
	public void CheckFail(){
		if(level.getMode()!=1){
		if(moves>=level.getMoves()){
			if(!CheckWin()){
			completed=true;
			paused=false;
			SetWinLayout();
		
			failed=true;
			
				}
			else{
				
			failed=false;
			}
			}}
		else{
			boolean check=true;
			
			for(int i=0;i<collected.length;i++){
				if(collected[i]>=level.getAmount()[i]){
					check=false;
					break;
				}}
			if(check==true){
				if(CheckWin())
				failed=false;
			}
			else{
				//ChangeScore(score+grid.getCellWorth()*(level.getMoves()-moves));
				completed=true;
				SetWinLayout();
				failed=true;
			}
		}
	}
	private void SetWinLayout() {
		
		if(level.getNumber()==stage.getLevelNumToStartFrom()+stage.getLevelAmount()-1){
		LevelClear=new Graphic(JR.res.getAtlas("pack").findRegion("StageClear"), JR.WIDTH/2, JR.HEIGHT/2+145);
			
		}
		else if(failed){
		LevelClear=new Graphic(JR.res.getAtlas("pack").findRegion("LevelFailed"), JR.WIDTH/2, JR.HEIGHT/2+145);
		}
		else{
			LevelClear=new Graphic(JR.res.getAtlas("pack").findRegion("LevelClear"), JR.WIDTH/2, JR.HEIGHT/2+145);
				
		}
		LevelClear.setDimensions(90, 50);
		Stars=new Graphic(JR.res.getAtlas("pack").findRegion("0Stars"),JR.WIDTH/2,JR.HEIGHT/2-10);
		Stars.setDimensions(125, 50);
		FinalScore=new NumberImage(Integer.toString(score), JR.WIDTH/2, JR.HEIGHT/2+20);
		//System.out.println("START WITH "+FinalScore.getNumber());
		nsa=new NumberSpanAnim(FinalScore);
		MenuButtonf=new Graphic(JR.res.getAtlas("pack").findRegion("MenuButton2"),JR.WIDTH/2-70,JR.HEIGHT/2-120);
		if(level.getNumber()==stage.getLevelNumToStartFrom()+stage.getLevelAmount()-1){
		MenuButtonf.setX(JR.WIDTH/2);
		}
		MenuButtonf.setDimensions(100, 100);
		RetryButton=new Graphic(JR.res.getAtlas("pack").findRegion("RetryButton1"),JR.WIDTH/2+70,JR.HEIGHT/2-120);
		RetryButton.setDimensions(100, 100);
		NextButton=new Graphic(JR.res.getAtlas("pack").findRegion("NextButton"),JR.WIDTH/2+70,JR.HEIGHT/2-120);
		NextButton.setDimensions(100, 100);
		Menu=new Graphic(JR.res.getAtlas("pack").findRegion("Menu3"),JR.WIDTH/2,JR.HEIGHT/2);
		Menu.setDimensions(400, 400);
		
	}
	float AnimTimer=1f;
	public void ChangeScore(int am){
		score=am;
		//TODO CHANGE SCORE
		
		float len=0;
		if(totalam<level.getMoves())
		{
			len=score*pbwidth/(20*2*level.getMoves());
			
			}
			else{
			len=score*pbwidth/(20*3*level.getMoves());	
			}
		if(len>pbwidth*2){
			len=pbwidth*2f;
		}
		
		
		if(len<pbwidth*2){
			//System.out.println("less");
		ProgressBar.setWidth(len);
		ProgressBar.setX(28+len/2);}
	//	System.out.println("wtf??? "+(level.getMoves()-moves));
		TotScore.SetNumber(Integer.toString(am));
		FinalScore.SetNumber(Integer.toString(am));
		
	}
	@Override
	public void update(float dt) {
		if(lastlevel && completed && !failed){
		StageClearAnim.update(dt);
	}
	collected=grid.getCollected();
	if(!GA.isFinished()){
		GA.update(dt);
	}
	if(level.getMode()==1){
		if(grid.getNoMoves()&&NoMovesCheck==false){
			NoMovesCheck=true;
			Timer.schedule(new Timer.Task() {
				
				@Override
				public void run() {
					moves++;
				}
			}, 1f);
			
			
		}
		else if(!grid.getNoMoves() &&NoMovesCheck==true){
			NoMovesCheck=false;
		}
	}
	for(int i=0;i<collected.length;i++){
		goal[i].update(dt);
		goal[i].SetNumber(collected[i]+"/"+level.getAmount()[i]);
		if(collected[i]>=level.getAmount()[i]&&!goal[i].getFlickering()){
			if(!goal[i].getColor()){
			goal[i].setColor(Color.GREEN);
			goal[i].setFlickering(true);}
		}
	}
	if(level.getMoves()-moves>=0){
	MovesScore.update(dt);
	MovesScore.SetNumber(Integer.toString(level.getMoves()-moves));
	if(level.getMoves()-moves<5&&!MovesScore.getFlickering()){
		MovesScore.setColor(Color.RED);
		MovesScore.setFlickering(true);
	}
	}
	else{if(MovesScore.getNumber()!="0")
	MovesScore.SetNumber(Integer.toString(0));
	}
	
	if(processing){
		if(selecttimer>=0&&processing){
		selecttimer-=dt;
		}
		else{
			processing=false;
			selecttimer=0.6f;
		}
	}
	if(completed&&AnimTimer>=0){
		AnimTimer-=dt;
		for(int i=0;i<3;i++){
			StarsAnim[i].setDimensions(AnimTimer*100+50, AnimTimer*100+50);
		}
	}
	else if(AnimTimer<0){
		
	}
	
	
	//Score.SetNumber(Integer.toString(grid.getScore()));
	if(score!=grid.getScore()&&!completed){
	ChangeScore(grid.getScore());
	//TotScore.SetNumber(Integer.toString(score));
	
	}
	handleInput();
	if(!paused){
	grid.update(dt);
	}
	if(!completed){
	CheckFail();
	if(CheckWin()){
		//won=!won;
		ChangeScore((score+5*grid.getCellWorth()*(level.getMoves()-moves)));
		
		if(score>=level.getScore3star()){
			stars=3;
			Stars.setImage(JR.res.getAtlas("pack").findRegion("3Stars"));
		}
		else if(score>=level.getScore2star()){
			stars=2;
			Stars.setImage(JR.res.getAtlas("pack").findRegion("2Stars"));
			
		}
		else{
			stars=1;
			Stars.setImage(JR.res.getAtlas("pack").findRegion("1Star"));
		}

		PlayerSettings.setUnlockedLevels(level.getNumber()+1);
		if(stars==3){
		//System.out.println("kkkk");
		PlayerSettings.setStars(level.getNumber(), 3);
		}
		else if(stars==2){
		PlayerSettings.setStars(level.getNumber(), 2);
		}
		else{
		PlayerSettings.setStars(level.getNumber(), 1);
		}
		//score+=grid.getCellWorth()*(level.getMoves()-moves);
		
		Timer.schedule(new Timer.Task() {
			
			@Override
			public void run() {
				if(!grid.getProcessing()&&!grid.isFilling()&&!grid.HasUnprocessedMatches()&&!processing&&!completed){
			completed=true;
			SetWinLayout();
			if(lastlevel){
				StageClearAnim=new FillScreenAnim();
			}
		}
			}
		}, 1f);
		
		
		

	}}
	else{
		nsa.update(dt);
	}
	
	}
	public void MoveCells(int row, int col){
		
			if(row>=0&& row<grid.getNumRows() && col>=0 && col<grid.getNumCols()){
			if(!grid.getCell(row, col).getDestroyed() && !grid.getCell(row, col).getForbidden()&&!chosencell.getForbidden()){
			if((row-1==grid.getRowOf(chosencell)&&col==grid.getColOf(chosencell))){
				if(grid.MakesMatch(chosencell, row, col,false)
						||grid.MakesMatch(grid.getCell(row, col),grid.getRowOf(chosencell), grid.getColOf(chosencell),false)){
				chosencell.setSelected(false);
				grid.SwapCells(chosencell,2,grid.getCell(row, col),1);
				chosen=false;
				grid.setMoved(true);
				moves++;
				//grid.CheckMatch();
				}
				else{
					final int rowf=row,colf=col;
					if(!processing){
					Timer.schedule(new Timer.Task() {
						
						@Override
						public void run() {
							grid.SwapCells(chosencell,2,grid.getCell(rowf, colf),1);
							//processing=false;
						}
					}, chosencell.getMaxTime()+0.1f);
					processing=true;
					grid.SwapCells(chosencell,2,grid.getCell(row, col),1);
				}}
				//grid.CheckMatch();
			}
					
			else if(row+1==grid.getRowOf(chosencell)&&col==grid.getColOf(chosencell)){
				if(grid.MakesMatch(chosencell, row, col,false)
						||grid.MakesMatch(grid.getCell(row, col),grid.getRowOf(chosencell), grid.getColOf(chosencell),false)){
						
				chosencell.setSelected(false);
				grid.SwapCells(chosencell,1,grid.getCell(row, col),2);
				chosen=false;
				grid.setMoved(true);
				moves++;
				//grid.CheckMatch();
				}
				else{
					final int rowf=row,colf=col;
				if(!processing){
					Timer.schedule(new Timer.Task() {
						
						@Override
						public void run() {
					
							grid.SwapCells(chosencell,1,grid.getCell(rowf, colf),2);
							//while(!chosencell.getDoneAnimating()){}
							//processing=false;
						}
					}, chosencell.getMaxTime()+0.1f);
					processing=true;
					grid.SwapCells(chosencell,1,grid.getCell(row, col),2);
				}}
				//grid.CheckMatch();
			}
				
			else if(col-1==grid.getColOf(chosencell)&&row==grid.getRowOf(chosencell)){
				if(grid.MakesMatch(chosencell, row, col,false)
						||grid.MakesMatch(grid.getCell(row, col),grid.getRowOf(chosencell), grid.getColOf(chosencell),false)){
				chosencell.setSelected(false);
				grid.SwapCells(chosencell,4,grid.getCell(row, col),3);
				chosen=false;
				grid.setMoved(true);
				moves++;
				//grid.CheckMatch();
				}
				else{
					final int rowf=row,colf=col;
					if(!processing){
					Timer.schedule(new Timer.Task() {
						
						@Override
						public void run() {
							
							grid.SwapCells(chosencell,4,grid.getCell(rowf, colf),3);
							//processing=false;
						}
					}, chosencell.getMaxTime()+0.1f);
					processing=true;
					grid.SwapCells(chosencell,4,grid.getCell(row, col),3);
				}}
				//grid.CheckMatch();
			}
				
			else if(col+1==grid.getColOf(chosencell)&&row==grid.getRowOf(chosencell)){
				if(grid.MakesMatch(chosencell, row, col,false)
						||grid.MakesMatch(grid.getCell(row, col),grid.getRowOf(chosencell), grid.getColOf(chosencell),false)){
				chosencell.setSelected(false);
				grid.SwapCells(chosencell,3,grid.getCell(row, col),4);
				chosen=false;
				grid.setMoved(true);
				moves++;

				//grid.CheckMatch();
				}
				else{
					final int rowf=row,colf=col;
					if(!processing){
					Timer.schedule(new Timer.Task() {
						
						@Override
						public void run() {
						
							grid.SwapCells(chosencell,3,grid.getCell(rowf, colf),4);
							//processing=false;
						}
					}, chosencell.getMaxTime()+0.1f);
					processing=true;
					grid.SwapCells(chosencell,3,grid.getCell(row, col),4);
				}}
				//grid.CheckMatch();
				
			}
			else{
				//chosencell.setSelected(false);
				//chosen=false;
				
			}
			}
			
		}
	}

	@Override
	public void render(final SpriteBatch sb) {
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		if(PlayerSettings.getBg()){
			sb.setColor(0.8f, 0.8f, 0.8f, 1);
		sb.draw(BG, 0, grid.getBoardOffset(),JR.WIDTH,JR.HEIGHT/2+JR.HEIGHT/5);
			sb.setColor(1, 1, 1, 1);
			}	
		sb.draw(MenuBg2,0,0,JR.WIDTH,MenuBg.getRegionHeight()+60);
		
		LevelNumber.render(sb);
		
		
		
		grid.render(sb);
		
		sb.draw(MenuBg,0,JR.HEIGHT-143,JR.WIDTH,143);
		MovesScore.render(sb);
		MenuButton1.render(sb);
		ProgressFrame.render(sb);
		ProgressBar.render(sb);
		TotScore.render(sb);
		for(int i=0;i<3;i++){
			ProgressPoints[i].render(sb);
		}
		
		if(lastlevel && completed && !failed){
			StageClearAnim.render(sb);
		}if(paused){
		Menu.render(sb);
	
		
		MenuButtonf.render(sb);
		RetryButton.render(sb);}
		else if(completed){
	
		Menu.render(sb);
		if(nsa.isFinished()){
		FinalScore.render(sb);}
		else{
			nsa.render(sb);
		}
		MenuButtonf.render(sb);
	
		LevelClear.render(sb);
		if(failed){
			RetryButton.render(sb);
		}
		else if(!lastlevel){
			
		NextButton.render(sb);
		}
		if(completed){
		Stars.render(sb);
		for(int i=0;i<stars;i++){
		StarsAnim[i].render(sb);
		}
		}
		}
		
		
		
		for(int i=0;i<level.getAmount().length;i++){
			goal[i].render(sb);
			
			if(i==0||i==1||i==2){
				if(level.getMode()==1){
					sb.draw(xes[i],70,JR.HEIGHT-60-30*(i),grid.getCellSize()/2,grid.getCellSize()/2);
				}
			sb.draw(imgs[i],10,JR.HEIGHT-60-30*(i),grid.getCellSize()/2,grid.getCellSize()/2);
			}
			if(i==3||i==4||i==5){
				if(level.getMode()==1){
					sb.draw(xes[i],70,JR.HEIGHT-60-30*(i),grid.getCellSize()/2,grid.getCellSize()/2);
				}
			sb.draw(imgs[i],70,JR.HEIGHT-60-30*(i-3),grid.getCellSize()/2,grid.getCellSize()/2);
			}
		}
		if(!GA.isFinished()){
			GA.render(sb);
		}
		sb.end();
	}
	public Level getLevel() {
		return level;
	}
	public float getSelecttimer() {
		return selecttimer;
	}
	public void setSwipes(){
		Gdx.input.setInputProcessor(new SimpleDirectionGestureDetector(new SimpleDirectionGestureDetector.DirectionListener() {

			@Override
			public void onUp() {
				if(!paused&&!completed)
				Timer.schedule(new Timer.Task() {
					
					@Override
					public void run() {
				if(!grid.getProcessing()&&!processing &&!grid.isFilling()&&!grid.getNoMoves()&&GA.isFinished()){
					if(chosen)
					chosencell.setSelected(false);
					Vector3 mos=new Vector3();
				mos.x=Gdx.input.getX();
				mos.y=Gdx.input.getY();
				cam.unproject(mos);
				int row=(int)((mouse.y-grid.getBoardOffset())/grid.getCellSize());
				int col=(int)(mouse.x/grid.getCellSize());
				if((chosen&& chosencell==grid.getCell(row, col))||!chosen){
					chosencell=grid.getCell(row, col);
				if(row!=numRows-1){
					
				MoveCells(row+1,col);}
				}
				else{
				
				}
						
			}
						
					}
				}, 0f);
				
			}

			@Override
			public void onRight() {
				if(!paused&&!completed)
				Timer.schedule(new Timer.Task() {
					
					@Override
					public void run() {
				if(!grid.getProcessing()&&!processing &&!grid.isFilling()&&!grid.getNoMoves()&&GA.isFinished()){
					if(chosen)
					chosencell.setSelected(false);
					Vector3 mos=new Vector3();
				mos.x=Gdx.input.getX();
				mos.y=Gdx.input.getY();
				cam.unproject(mos);
				int row=(int)((mouse.y-grid.getBoardOffset())/grid.getCellSize());
				int col=(int)(mouse.x/grid.getCellSize());
				if((chosen&& chosencell==grid.getCell(row, col))||!chosen){
				chosencell=grid.getCell(row, col);
				if(col!=numCols-1){
				MoveCells(row,col+1);}}}
			}},0f);
				}

			@Override
			public void onLeft() {
				if(!paused&&!completed)
				Timer.schedule(new Timer.Task() {
					
					@Override
					public void run() {
				if(!grid.getProcessing()&&!processing &&!grid.isFilling()&&!grid.getNoMoves()&&GA.isFinished()){
					if(chosen)
					chosencell.setSelected(false);
					Vector3 mos=new Vector3();
				mos.x=Gdx.input.getX();
				mos.y=Gdx.input.getY();
				cam.unproject(mos);
				int row=(int)((mouse.y-grid.getBoardOffset())/grid.getCellSize());
				int col=(int)(mouse.x/grid.getCellSize());
				if((chosen&& chosencell==grid.getCell(row, col))||!chosen){
				chosencell=grid.getCell(row, col);
				if(col!=0){
				MoveCells(row,col-1);}}}
				
			}},0f);
			}

			@Override
			public void onDown() {
				if(!paused&&!completed)
	Timer.schedule(new Timer.Task() {
					
					@Override
					public void run() {
				if(!grid.getProcessing()&&!processing &&!grid.isFilling() &&!grid.getNoMoves()&&GA.isFinished()){
					if(chosen)
					chosencell.setSelected(false);
				Vector3 mos=new Vector3();
				mos.x=Gdx.input.getX();
				mos.y=Gdx.input.getY();
				cam.unproject(mos);
				int row=(int)((mouse.y-grid.getBoardOffset())/grid.getCellSize());
				int col=(int)(mouse.x/grid.getCellSize());
				if((chosen&& chosencell==grid.getCell(row, col))||!chosen){
				chosencell=grid.getCell(row, col);
				if(row!=0){
				MoveCells(row-1,col);}}}
					}},0f);
			}
			}));
		
	}
	
}
