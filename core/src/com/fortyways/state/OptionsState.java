package com.fortyways.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fortyways.jewel.JR;
import com.fortyways.ui.Graphic;
import com.fortyways.ui.TextImage;

public class OptionsState extends State{

	private Graphic BGButton;
	private Graphic SWButton;
	private TextImage Background;
	private TextImage Swipe;
	private Graphic ChBoxBg;
	private Graphic ChBoxSw;
	private Graphic Back;
	private TextureRegion unchecked;
	private TextureRegion checked;
	private boolean checkbg=false;
	private boolean checksw=false;
	
	private TextImage Select;
	public OptionsState(GSM gsm) {
		super(gsm);
		//StateName=new Graphic(JR.res.getAtlas("pack").findRegion("Button5"), JR.WIDTH/2,JR.HEIGHT-50);
	//	StateName.setDimensions(250, 100);
		Select=new TextImage("options", JR.WIDTH/2-60, JR.HEIGHT-80);
		Select.setSize(15);
		Back=new Graphic(JR.res.getAtlas("pack").findRegion("Back1"), JR.WIDTH/2, 20);
		Back.setDimensions(50, 50);
		unchecked=JR.res.getAtlas("pack").findRegion("CheckBox1");
		checked=JR.res.getAtlas("pack").findRegion("CheckBox1-on");
	
		ChBoxBg=new Graphic(unchecked, JR.WIDTH-100, JR.HEIGHT/2);
		
		if(!JR.ps.getSwipe()){
			ChBoxSw=new Graphic(unchecked, JR.WIDTH-100, JR.HEIGHT/2+100);
		}
		else{
			ChBoxSw=new Graphic(checked, JR.WIDTH-100, JR.HEIGHT/2+100);
		}
		ChBoxSw.setDimensions(50, 50);
		ChBoxBg.setDimensions(50, 50);
		BGButton=new Graphic(JR.res.getAtlas("pack").findRegion("Button1"), JR.WIDTH/2-90, JR.HEIGHT/2);
		BGButton.setDimensions(300, 100);
		SWButton=new Graphic(JR.res.getAtlas("pack").findRegion("Button1"), JR.WIDTH/2-90, JR.HEIGHT/2+100);
		SWButton.setDimensions(300, 100);
		Background=new TextImage("background",  10, JR.HEIGHT/2-40);
		Background.setSize(25);
		Swipe=new TextImage("swipe",  10, JR.HEIGHT/2-40+100);
		Swipe.setSize(25);
	}

	@Override
	public void handleInput() {
		if(Gdx.input.justTouched()){
			mouse.x=Gdx.input.getX();
			mouse.y=Gdx.input.getY();
			cam.unproject(mouse);
			if(ChBoxBg.contains(mouse.x, mouse.y)){
				if(checkbg){
				ChBoxBg.setImage(unchecked);
				
				checkbg=false;
				}
				else{
					checkbg=true;
				
				ChBoxBg.setImage(checked);
				
			}}
			if(ChBoxSw.contains(mouse.x, mouse.y))
			{
				if(checksw){
				ChBoxSw.setImage(unchecked);
				JR.ps.setSwipe(false);
				checksw=false;
				}
				else{
				checksw=true;
			
				JR.ps.setSwipe(true);
				ChBoxSw.setImage(checked);
				
			}}
			if(Back.contains(mouse.x, mouse.y)){
				gsm.set(new TransFadeState(gsm, this,new MainMenuState(gsm)));
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
		//StateName.render(sb);
		Select.render(sb);
		BGButton.render(sb);
		SWButton.render(sb);
		ChBoxBg.render(sb);
		ChBoxSw.render(sb);
		Background.render(sb);
		Swipe.render(sb);
		Back.render(sb);
		sb.end();
	}

}
