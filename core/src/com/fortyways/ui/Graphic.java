package com.fortyways.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fortyways.util.Box;

public class Graphic extends Box{
	private TextureRegion image;
	public Graphic(TextureRegion image,float x,float y){
		this.x=x;
		this.y=y;
		this.image=image;
		width=image.getRegionWidth();
		height=image.getRegionHeight();
		
				
	}
	public void setDimensions(float width,float height){
		this.width=width;
		this.height=height;
	}
	public void render(SpriteBatch sb){
		sb.draw(image,x-width/2,y-height/2,width,height);
	}
	public void setImage(TextureRegion image) {
		this.image = image;
	}
}
