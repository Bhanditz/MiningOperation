package com.fortyways.animation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fortyways.ui.NumberImage;

public class PlusNumAnim extends Animation{

	int amount;
	boolean sign;
	String text;
	int size=10;
	NumberImage ni;
	public PlusNumAnim(float x,float y,int num) {
		super(4f,x,y,null);
		this.amount=num;
		if(num<0){
			sign=false;
			text=""+amount;
		}
		else{
			sign=true;
			text="+"+amount;
		}
		ni=new NumberImage(text, x, y);
		ni.setSize(size);
		
	}
	@Override
	public void update(float dt) {
		if(timer<MaxTime){
			timer+=size/2*MaxTime/250;
			
		}
		else{
			isFinished=true;
		}
		
	}

	@Override
	public void render(SpriteBatch sb) {
		if(timer<=MaxTime/2){
			ni.setSize((int) (size+size/2*timer));
			if(timer<=1){
			sb.setColor(1, 1, 1,timer);}
			else{
			//sb.setColor(1, 1, 1,0.1f);
			}
		}
		else {
			ni.setSize((int) (size+size/2*timer));
		sb.setColor(1,1,1,1-timer/MaxTime);	
		if(1-timer/MaxTime<0){
			sb.setColor(1,1,1,0);	
		}
		}
		
		ni.render(sb);
		sb.setColor(1, 1, 1,1);
		
	}
	public void SetSize(int size){
		this.size=size;
	}

}
