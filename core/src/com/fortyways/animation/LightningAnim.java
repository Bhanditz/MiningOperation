package com.fortyways.animation;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.fortyways.jewel.JR;
import com.fortyways.jewel.game.Cell;
import com.fortyways.util.GFX;

public class LightningAnim extends Animation{
	Color color;
	int puType;
	
	public LightningAnim(Cell c) {
		this.x=c.getX();
		this.y=c.getY();
		this.puType=c.getPowerUpType();

		
		if(JR.getCT1().contains(c.getType(), true))
		{
			color=Color.CYAN;
		}
		else if(JR.getCT3().contains(c.getType(), true)){
			color=Color.GREEN;
		}
		else{
			color=Color.YELLOW;
		}
		MaxTime=1.2f;
		timer=0f;
	}
	@Override
	public void update(float dt) {
		if(timer<=MaxTime){
			timer+=dt;
		}
		else{
			isFinished=true;
		}
		
	}

	@Override
	public void render(SpriteBatch sb) {
		GFX.setColour(color, color);
		GFX.setThickness(5);
		if(puType==1){
			GFX.drawChainLightning(sb, new Vector2[]{new Vector2(0,y),new Vector2(JR.WIDTH,y)});
			
			}
		else if(puType==2){
			GFX.drawChainLightning(sb, new Vector2[]{new Vector2(x,120),new Vector2(x,JR.HEIGHT-100)});
		}
		else if(puType==3){
			GFX.drawChainLightning(sb, new Vector2[]{new Vector2(0,y),new Vector2(JR.WIDTH,y)});
			GFX.drawChainLightning(sb, new Vector2[]{new Vector2(x,120),new Vector2(x,JR.HEIGHT-100)});}
	
		sb.setColor(1, 1, 1, 1);
		
	}

	
	
	
}
