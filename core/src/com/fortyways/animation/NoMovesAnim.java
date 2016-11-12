package com.fortyways.animation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fortyways.jewel.JR;

public class NoMovesAnim extends Animation{
	float StopPoint;
	float height;
	float width;
	public NoMovesAnim(){
		super();
		this.MaxTime=3f;
		this.x=10;
		this.y=JR.HEIGHT/2;
		this.StopPoint=MaxTime/2;
		this.texture=JR.res.getAtlas("pack").findRegion("NoMoves1");
		this.height=70;
		this.width=460;
	}

	@Override
	public void update(float dt) {
		if(timer<MaxTime){
		timer+=dt;}
		else{
			isFinished=true;
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		if(timer<=1f){
			sb.draw(texture, x, y, width*timer, height*timer);
		}
		else if(timer>1f&&timer<=2f){
			sb.draw(texture, x, y, width, height);
		}
		else if(timer>2f){
			sb.draw(texture, x, y, width*(3-timer), height*(3-timer));
		}
		
	}

}
