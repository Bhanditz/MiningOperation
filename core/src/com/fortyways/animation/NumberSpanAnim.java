package com.fortyways.animation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fortyways.ui.NumberImage;

public class NumberSpanAnim extends Animation{

	NumberImage ni;
	int totalnum;
	int cur=0;
	
	float timer=0.3f;
	public NumberSpanAnim(NumberImage ni) {
		this.ni=ni;
		totalnum=Integer.parseInt(ni.getNumber());
		System.out.println(totalnum);
		MaxTime=0.3f;	
	}
	@Override
	public void update(float dt) {
		if(timer>=0){
			timer=MaxTime;
			if(cur<totalnum){
			cur+=10;
			ni.SetNumber(Integer.toString(cur));}
			else{
				isFinished=true;
			}
			if(MaxTime>=0.02f){
			MaxTime-=0.01f;}
		}
		else{
			timer-=0.3f/10;
			
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		ni.render(sb);
	}

}
