package com.fortyways.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fortyways.animation.MainMenuAnim;
import com.fortyways.jewel.JR;
import com.fortyways.jewel.game.PlayerSettings;
import com.fortyways.jewel.game.Stage;
import com.fortyways.ui.Graphic;
import com.fortyways.ui.NumberImage;
import com.fortyways.ui.TextImage;

public class StageSelectState extends State{
	private Graphic CurStageImg;
	private Graphic LArrow;
	private Graphic RArrow;
	private Graphic Back;
	private TextImage Select;

	private Graphic StageButton;
	private int StageNum;
	private Graphic CurStageName;

	
	private Array<Stage> Stages;
	private NumberImage Levels,Stars;
	private Graphic Star;
	private Graphic lvl;
	Graphic Set;
	Graphic Set2;
	Graphic SelectButton;
	private MainMenuAnim mma;
	
	protected StageSelectState(GSM gsm) {
		super(gsm);
		
		Stages=new Array<Stage>();
		Stages.add(new Stage(
				JR.res.getAtlas("pack").findRegion("tstbg3"),
				JR.res.getAtlas("pack").findRegion("CrystalMines"),
				JR.res.getAtlas("pack").findRegion("CrystalMinesName"),
				"crystal mines", 20, 0));
		Stages.add(new Stage(
				JR.res.getAtlas("pack").findRegion("tstbg4"),
				JR.res.getAtlas("pack").findRegion("LavaPits"),
				JR.res.getAtlas("pack").findRegion("LavaPitsName"),
				"lava pits", 20, 20));
		Stages.add(new Stage(
				JR.res.getAtlas("pack").findRegion("tstbg"),
				JR.res.getAtlas("pack").findRegion("MossyCaves"),
				JR.res.getAtlas("pack").findRegion("MossyCavesName"),
				"mossy caves", 20, 40));
		StageNum=JR.ps.getLastStage();
		Set=new Graphic(JR.res.getAtlas("pack").findRegion("set2"),JR.WIDTH/2,75);
		Set.setDimensions(3*Set.getWidth(), 3*Set.getHeight());
		Set2=new Graphic(JR.res.getAtlas("pack").findRegion("set2"),JR.WIDTH/2,JR.HEIGHT-69);
		Set2.setDimensions(3*Set2.getWidth(), 3*Set2.getHeight());
		Stages.get(0).setDescription("also known as tutorialand");
		Stages.get(1).setDescription("description kinda");
		//StateName=new Graphic(JR.res.getAtlas("pack").findRegion("Button5"), JR.WIDTH/2,JR.HEIGHT-70);
	//	StateName.setDimensions(250, 100);
		StageButton=new Graphic(JR.res.getAtlas("pack").findRegion("CButton"), JR.WIDTH/2,JR.HEIGHT-70);
		Star=new Graphic(JR.res.getAtlas("pack").findRegion("Star"), 2*JR.WIDTH/3+75, JR.HEIGHT/2-220);
		lvl=new Graphic(JR.res.getAtlas("pack").findRegion("lvl"), JR.WIDTH/3+75, JR.HEIGHT/2-220);
		setStarsANDLevels(StageNum);
		SelectButton=new Graphic(JR.res.getAtlas("pack").findRegion("SelectButton"),JR.WIDTH/2, JR.HEIGHT/2-270);
		SelectButton.setDimensions(4*33, 4*15);
		if(Stages.get(StageNum).getName()=="lava pits"){
			StageButton.setImage(JR.res.getAtlas("pack").findRegion("RCButton"));
			mma=new MainMenuAnim(JR.getCT2());
		}
		else if(Stages.get(StageNum).getName()=="mossy caves"){
			StageButton.setImage(JR.res.getAtlas("pack").findRegion("GButton"));
			mma=new MainMenuAnim(JR.getCT3());
		}
		else{
			StageButton.setImage(JR.res.getAtlas("pack").findRegion("CButton"));
			mma=new MainMenuAnim(JR.getCT1());
		}
		StageButton.setDimensions(350, 100);
		Select=new TextImage("select stage", JR.WIDTH/2-90, JR.HEIGHT-80);
		Select.setSize(15);
		//Description=new TextImage(Stages.get(StageNum).getDescription(), JR.WIDTH/2-120, JR.HEIGHT/2-300);
		//Description.setSize(10);
		
		CurStageName=new Graphic( Stages.get(StageNum).getNamePic(),JR.WIDTH/2, JR.HEIGHT-70);
		CurStageName.setDimensions(CurStageName.getWidth(), CurStageName.getHeight());
		//CurStageName.setSize(15);
		Back=new Graphic(JR.res.getAtlas("pack").findRegion("Back1"), JR.WIDTH/2, 50);
		Back.setDimensions(50, 50);
		LArrow=new Graphic(JR.res.getAtlas("pack").findRegion("ArrowLeft1"), JR.WIDTH/2-200, 70);
		LArrow.setDimensions(50, 50);
		RArrow=new Graphic(JR.res.getAtlas("pack").findRegion("ArrowRight1"), JR.WIDTH/2+200, 70);
		RArrow.setDimensions(50, 50);
		
		CurStageImg=new Graphic(Stages.get(StageNum).getPreview(), JR.WIDTH/2,JR.HEIGHT/2);
		CurStageImg.setDimensions(400, 400);
		//DescriptionButton=new Graphic(JR.res.getAtlas("pack").findRegion("Button6"), JR.WIDTH/2,JR.HEIGHT/2-270);
		//DescriptionButton.setDimensions(300, 150);
		
		
	}
	public void setStarsANDLevels(int stagenum){
		if(PlayerSettings.getUnlockedLevels()>=Stages.get(stagenum).getLevelAmount()+Stages.get(stagenum).getLevelNumToStartFrom()){
			
			Levels=new NumberImage(Stages.get(stagenum).getLevelAmount()+"/"+Stages.get(stagenum).getLevelAmount(), JR.WIDTH/3, JR.HEIGHT/2-250);
			Stars=new NumberImage(JR.ps.getStarsFromLevels(
					Stages.get(stagenum).getLevelNumToStartFrom(), Stages.get(stagenum).getLevelNumToStartFrom()+Stages.get(stagenum).getLevelAmount()-1)
					+"/"+3*Stages.get(stagenum).getLevelAmount(), 2*JR.WIDTH/3, JR.HEIGHT/2-250);
			
		}
		else{
			if(PlayerSettings.getUnlockedLevels()-Stages.get(stagenum).getLevelNumToStartFrom()<0)
			{
				Levels=new NumberImage(0+"/"+Stages.get(stagenum).getLevelAmount(), JR.WIDTH/3, JR.HEIGHT/2-250);
					
			}
			else
			Levels=new NumberImage(PlayerSettings.getUnlockedLevels()-Stages.get(stagenum).getLevelNumToStartFrom()+"/"+Stages.get(stagenum).getLevelAmount(), JR.WIDTH/3, JR.HEIGHT/2-250);
			Stars=new NumberImage(JR.ps.getStarsFromLevels(
					Stages.get(stagenum).getLevelNumToStartFrom(), Stages.get(stagenum).getLevelNumToStartFrom()+Stages.get(stagenum).getLevelAmount()-1)
					+"/"+3*Stages.get(stagenum).getLevelAmount(), 2*JR.WIDTH/3, JR.HEIGHT/2-250);
			
		}
	}
	@Override
	public void handleInput() {
		if(Gdx.input.justTouched()){
			mouse.x=Gdx.input.getX();
			mouse.y=Gdx.input.getY();	
			cam.unproject(mouse);
			if(Back.contains(mouse.x, mouse.y)){
				gsm.set(new TransDoorState(gsm, this,new  MainMenuState(gsm)));
			}
			if(SelectButton.contains(mouse.x, mouse.y)){
		
				
						if(SelectButton.contains(mouse.x, mouse.y)&&Gdx.input.isTouched()){
							if(StageNum<Stages.size){
								if(JR.ps.getLastStage()==StageNum
										&&JR.ps.getLastLevelScreen()>=Stages.get(StageNum).getLevelNumToStartFrom()
										&&JR.ps.getLastLevelScreen()+12-Stages.get(StageNum).getLevelNumToStartFrom()%12==0){
						gsm.set(new TransDoorState(gsm, this, new LevelSelectState(gsm,Stages.get(StageNum),JR.ps.getLastLevelScreen())));
								}
								else{
								JR.ps.setLastStage(StageNum);
								gsm.set(new TransDoorState(gsm, this, new LevelSelectState(gsm,Stages.get(StageNum),Stages.get(StageNum).getLevelNumToStartFrom())));				
								}
									
							}
						}
					
			
			}
			if(LArrow.contains(mouse.x, mouse.y)){
				if(StageNum!=0){
					StageNum--;
					
					if(StageNum<Stages.size){
						CurStageImg=new Graphic(Stages.get(StageNum).getPreview(), JR.WIDTH/2,JR.HEIGHT/2);
						CurStageImg.setDimensions(400, 400);
						CurStageName.setImage(Stages.get(StageNum).getNamePic());
						//CurStageName.setDimensions(CurStageName.getWidth()*2, CurStageName.getHeight()*2);
						
						setStarsANDLevels(StageNum);
						if(Stages.get(StageNum).getName()=="lava pits"){
							StageButton.setImage(JR.res.getAtlas("pack").findRegion("RCButton"));
							mma.ChangeGems(JR.getCT2());
						}
						else if(Stages.get(StageNum).getName()=="mossy caves"){
							StageButton.setImage(JR.res.getAtlas("pack").findRegion("GButton"));
							mma.ChangeGems(JR.getCT3());
						}
						else{
							StageButton.setImage(JR.res.getAtlas("pack").findRegion("CButton"));
							mma.ChangeGems(JR.getCT1());
						}
						
					}
				}
			}
			if(RArrow.contains(mouse.x, mouse.y)){
				
				if(StageNum<=Stages.size){
					if(StageNum<Stages.size){
					StageNum++;
					if(StageNum<Stages.size){
					CurStageImg=new Graphic(Stages.get(StageNum).getPreview(), JR.WIDTH/2,JR.HEIGHT/2);
					CurStageImg.setDimensions(400, 400);
					CurStageName.setImage(Stages.get(StageNum).getNamePic());
					//CurStageName.setDimensions(CurStageName.getWidth()*2, CurStageName.getHeight()*2);
					setStarsANDLevels(StageNum);
					if(Stages.get(StageNum).getName()=="lava pits"){
						StageButton.setImage(JR.res.getAtlas("pack").findRegion("RCButton"));
						mma.ChangeGems(JR.getCT2());
					}
					else if(Stages.get(StageNum).getName()=="mossy caves"){
						StageButton.setImage(JR.res.getAtlas("pack").findRegion("GButton"));
						mma.ChangeGems(JR.getCT3());
					}
					else{
						StageButton.setImage(JR.res.getAtlas("pack").findRegion("CButton"));
						mma.ChangeGems(JR.getCT1());
					}
					
					}
					}
					if(StageNum==Stages.size){
					CurStageImg=new Graphic(JR.res.getAtlas("pack").findRegion("QuestionMark"), JR.WIDTH/2,JR.HEIGHT/2);
					Stars.SetNumber("0/0");
					Levels.SetNumber("0/0");
					mma.ChangeGems(JR.getCT4());
					CurStageImg.setDimensions(100, 200);
					}
				}
				
			}
			
		}
		
	}

	@Override
	public void update(float dt) {
		handleInput();
		mma.update(dt);
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.begin();
		mma.render(sb);
		Set.render(sb);
		Set2.render(sb);
		SelectButton.render(sb);
		
		if(StageNum!=Stages.size){
			StageButton.render(sb);
		CurStageName.render(sb);
		}
		Back.render(sb);
		CurStageImg.render(sb);
		Levels.render(sb);
		lvl.render(sb);
		Stars.render(sb);
		Star.render(sb);
		if(StageNum!=0){
		LArrow.render(sb);}
		if(StageNum!=Stages.size){
		RArrow.render(sb);}
		
		sb.end();
		
	}

}
