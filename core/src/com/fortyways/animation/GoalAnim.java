package com.fortyways.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.fortyways.jewel.JR;
import com.fortyways.jewel.game.Level;
import com.fortyways.jewel.game.Cell.CellType;
import com.fortyways.ui.Graphic;
import com.fortyways.ui.NumberImage;
import com.fortyways.ui.TextImage;

public class GoalAnim extends Animation{
	private Level level;
	private Graphic Mode;
	private TextureRegion[] imgs;
	private NumberImage[] goal;
	private Graphic Desc;
	private Graphic OkButton;
	private Vector3 mouse;
	private OrthographicCamera cam;
	private Graphic Menu;
	
	private boolean clicked=false;
	
	///////MO
	private Graphic Door1;
	private Graphic Door2;
	public GoalAnim(Level level,OrthographicCamera cam,Vector3 mouse) {
		super();
		this.x=JR.WIDTH/2;
		this.y=JR.HEIGHT;
		this.level=level;
		this.cam=cam;
		this.mouse=mouse;
		MaxTime=4.5f;
		timer=0f;
		Menu=new Graphic(JR.res.getAtlas("pack").findRegion("Holo1"),JR.WIDTH/2,JR.HEIGHT+150);
		Door1=new Graphic(JR.res.getAtlas("pack").findRegion("Door1"), -300, JR.HEIGHT/2+56);
		Door2=new Graphic(JR.res.getAtlas("pack").findRegion("Door2"), JR.WIDTH+300, JR.HEIGHT/2);
		Door1.setDimensions(140*4, 86*4);
		Door2.setDimensions(140*4, 86*4);
		Menu.setDimensions(Menu.getWidth()*5,Menu.getHeight()*5);
	//	ModeButton=new Graphic(JR.res.getAtlas("pack").findRegion("Button5"),  JR.WIDTH/2-10, JR.HEIGHT+280);
	//	ModeButton.setDimensions(200, 75);
		OkButton=new Graphic(JR.res.getAtlas("pack").findRegion("OkButton1"),JR.WIDTH/2-10, JR.HEIGHT+50);
		OkButton.setDimensions(100, 100);
		if(level.getMode()==0){
			Mode=new Graphic(JR.res.getAtlas("pack").findRegion("NormalMode"), JR.WIDTH/2, JR.HEIGHT+270);
			Desc=new Graphic(JR.res.getAtlas("pack").findRegion("DescNormal"), JR.WIDTH/2+40, JR.HEIGHT+170);
			goal=new NumberImage[level.getRequiredCells().length];
			imgs=new TextureRegion[level.getRequiredCells().length];
			for(int i=0;i<level.getRequiredCells().length;i++){
				
				goal[i]=new NumberImage("0/"+level.getAmount()[i], JR.WIDTH/3, JR.HEIGHT+200-30*i-20);
				goal[i].setSize(9);
				goal[i].Dist=2;
				imgs[i]=CellType.getTexture(level.getRequiredCells()[i]);}
				
			
		}
		else if(level.getMode()==1){
			Mode=new Graphic(JR.res.getAtlas("pack").findRegion("ReverseMode"), JR.WIDTH/2, JR.HEIGHT+270);
			Desc=new Graphic(JR.res.getAtlas("pack").findRegion("DescReverse"), JR.WIDTH/2+40, JR.HEIGHT+170);
			goal=new NumberImage[level.getRequiredCells().length];
			imgs=new TextureRegion[level.getRequiredCells().length];
			
			for(int i=0;i<level.getRequiredCells().length;i++){
			
				goal[i]=new NumberImage("0/"+level.getAmount()[i], JR.WIDTH/3, JR.HEIGHT+200-30*i-20);
				goal[i].setSize(9);
				goal[i].Dist=2;
				imgs[i]=CellType.getTexture(level.getRequiredCells()[i]);
			
				
			}
		}
		else if(level.getMode()==3){
			Mode=new Graphic(JR.res.getAtlas("pack").findRegion("CollectMode"), JR.WIDTH/2, JR.HEIGHT+270);
			Desc=new Graphic(JR.res.getAtlas("pack").findRegion("DescCollect"), JR.WIDTH/2+40, JR.HEIGHT+170);
			goal=new NumberImage[level.getRequiredCells().length];
			imgs=new TextureRegion[level.getRequiredCells().length];
			
			for(int i=0;i<level.getRequiredCells().length;i++){
			
				goal[i]=new NumberImage("0/"+level.getAmount()[i], JR.WIDTH/3, JR.HEIGHT+200-30*i-20);
				goal[i].setSize(9);
				goal[i].Dist=2;
				imgs[i]=CellType.getTexture(level.getRequiredCells()[i]);
			
				
			}
		}
		else if(level.getMode()==2){
			Mode=new Graphic(JR.res.getAtlas("pack").findRegion("PlateMode"), JR.WIDTH/2, JR.HEIGHT+270);
			Desc=new Graphic(JR.res.getAtlas("pack").findRegion("DescPlate"), JR.WIDTH/2+40, JR.HEIGHT+170);
			goal=new NumberImage[1];
			goal[0]=new NumberImage("0/"+level.getAmount()[0],30, JR.HEIGHT+200);
			goal[0].setSize(9);
			goal[0].Dist=2;
			imgs=new TextureRegion[1];
			imgs[0]=JR.res.getAtlas("pack").findRegion("Plate1");
		}
		
		Mode.setDimensions(Mode.getWidth()*4, Mode.getHeight()*4);
		//Desc.setDimensions(Desc.getWidth()*0.5f, Desc.getHeight()*0.5f);
	}
	public void handleInput(){
		if(Gdx.input.justTouched()){
			mouse.x=Gdx.input.getX();
			mouse.y=Gdx.input.getY();
			cam.unproject(mouse);
			if(OkButton.contains(mouse.x, mouse.y)){
				clicked=true;
				timer=3f;
			}
		}
	}
	@Override
	public void update(float dt) {
		if(timer<1.2f){
			timer+=1.2f/70.001f;
		}
		else if(!clicked){
			handleInput();
				
		}else if(timer<MaxTime){
		timer+=1.2f/10f;
		}
		else
		{
			isFinished=true;
		}
			
			
		}
	
		
	

	@Override
	public void render(SpriteBatch sb) {
		if(timer<1.2f){
			Menu.setY((float) (Menu.getY()-50*0.5*(timer*0.5f)));
			
			Door1.setX((float) (Door1.getX()+37*0.5*(timer*0.5f)));
			Door2.setX((float) (Door2.getX()-38*0.5*(timer*0.5f)));
			//sb.setColor(1, 1, 1, 0.5f);
			Menu.render(sb);
			//sb.setColor(1, 1, 1, 1);
			Door1.render(sb);
			Door2.render(sb);
			Mode.setY((float) (Mode.getY()-50*0.5*(timer*0.5f)));
			Desc.setY((float) (Desc.getY()-50*0.5*(timer*0.5f)));
			Desc.render(sb);
			OkButton.setY((float) (OkButton.getY()-50*0.5*(timer*0.5f)));
			OkButton.render(sb);
			
			for(int i=0;i<goal.length;i++){
				
				goal[i].setY((float) (goal[i].getY()-50*0.5*((timer)*0.5f)));
				goal[i].render(sb);
				
				
				
				sb.draw(imgs[i],goal[i].getX()-40,goal[i].getY(),40,40);
			}
			
		}
		if(timer>3f){
			Door2.setX((float) (Door2.getX()+36*0.5*(timer*0.5f)));
			Door1.setX((float) (Door1.getX()-37*0.5*(timer*0.5f)));
			Door1.render(sb);
			Door2.render(sb);
			Menu.setY((float) (Menu.getY()+50*0.5*((timer*0.8f)*0.5f)));
			//sb.setColor(1, 1, 1, 0.5f);
			Menu.render(sb);
			//sb.setColor(1, 1, 1, 1);
			Desc.setY((float) (Desc.getY()+50*0.5*(timer*0.8f)));
			Desc.render(sb);
			
			for(int i=0;i<goal.length;i++){
				
				goal[i].setY((float) (goal[i].getY()+50*0.5*((timer*0.8f)*0.5f)));
				goal[i].render(sb);
				
				
				
				sb.draw(imgs[i],goal[i].getX()-40,goal[i].getY(),40,40);
			}
		}
		else{
			
			Door1.render(sb);
			Door2.render(sb);
			
			//sb.setColor(1, 1, 1, 0.5f);
			Menu.render(sb);
			//sb.setColor(1, 1, 1, 1);
			OkButton.render(sb);
			Mode.render(sb);
			
			Desc.render(sb);
			for(int i=0;i<goal.length;i++){
				goal[i].render(sb);
				
				sb.draw(imgs[i],goal[i].getX()-40,goal[i].getY(),40,40);
			}
			
		}
		
	}

}
