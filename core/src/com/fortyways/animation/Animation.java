package com.fortyways.animation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Animation {
	protected float timer;
	protected float MaxTime;
	protected boolean isFinished;
	protected float x;
	protected float y;
	protected TextureRegion texture; 
	public Animation(float MaxTime,float x,float y,TextureRegion texture){
		this.MaxTime=MaxTime;
		this.x=x;
		this.y=y;
		this.texture=texture;
		timer=0;
		isFinished=false;
	}
	
	public Animation() {
		x=0;
		y=0;
		texture=null;
		isFinished=false;
		timer=0f;
	}

	public abstract void update(float dt);
	public abstract void render(SpriteBatch sb);
	public boolean isFinished(){
		return isFinished;
	}
	public void setFinished(boolean isFinished){
		this.isFinished=isFinished;
	}
}
