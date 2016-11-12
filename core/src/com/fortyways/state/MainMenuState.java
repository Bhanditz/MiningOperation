package com.fortyways.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fortyways.animation.MainMenuAnim;
import com.fortyways.animation.PUAnim;
import com.fortyways.jewel.JR;
import com.fortyways.ui.Graphic;
import com.sun.scenario.effect.impl.prism.ps.PPSBlend_BLUEPeer;

public class MainMenuState extends State {

	Graphic PlayButton;
	Graphic OptionsButton;
	Graphic ShopButton;
	
	Graphic Title;
	Graphic Set;

	MainMenuAnim mma;
	
	///tst

	///
	public MainMenuState(GSM gsm) {
		super(gsm);

////////////////?????????????
Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));
///////??????????????????
		
		mma=new MainMenuAnim();
		Title=new Graphic(JR.res.getAtlas("pack").findRegion("logo2"),JR.WIDTH/2,JR.HEIGHT-69);
		Title.setDimensions(3*Title.getWidth(), 3*Title.getHeight());
		Set=new Graphic(JR.res.getAtlas("pack").findRegion("set2"),JR.WIDTH/2,75);
		Set.setDimensions(3*Set.getWidth(), 3*Set.getHeight());
		TextureRegion tr=JR.res.getAtlas("pack").findRegion("Play2");
		PlayButton=new Graphic(tr,JR.WIDTH/2,JR.HEIGHT/2);
		PlayButton.setDimensions(63*4, 58*4);
		OptionsButton=new Graphic(JR.res.getAtlas("pack").findRegion("Settings1"),JR.WIDTH/2,70);
		OptionsButton.setDimensions(122, 116);
		
	}

	@Override
	public void handleInput() {
		if(Gdx.input.justTouched()){
			mouse.x=Gdx.input.getX();
			mouse.y=Gdx.input.getY();
			cam.unproject(mouse);
			if(PlayButton.contains(mouse.x, mouse.y)){
				gsm.set(new TransDoorState(gsm, this,new  StageSelectState(gsm)));
			}
			if(OptionsButton.contains(mouse.x, mouse.y)){
				gsm.set(new TransFadeState(gsm, this,new OptionsState(gsm)));
			}
			
		}
		
	}
	@Override
	public void update(float dt) {
	mma.update(dt);
	handleInput();
	}

	@Override
	public void render(SpriteBatch sb) {
	sb.setProjectionMatrix(cam.combined);
	sb.begin();
	mma.render(sb);
	PlayButton.render(sb);
	Title.render(sb);
	Set.render(sb);
	OptionsButton.render(sb);
	
	sb.end();
		
	}
	
	
}

