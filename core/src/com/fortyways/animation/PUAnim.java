package com.fortyways.animation;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fortyways.jewel.JR;
import com.fortyways.jewel.game.Cell;
import com.fortyways.ui.Graphic;

public class PUAnim extends Animation{
	Graphic pu,pr;
	int count=0;
	float x;
	float y;
	int putype;
	int colortype;
	public PUAnim(Cell c) {
		this.x=c.getX();
		this.y=c.getY();
		this.putype=c.getPowerUpType();
		MaxTime=5f;
		timer=MaxTime;
		if(JR.getCT1().contains(c.getType(), false)){
			colortype=1;
		}
		else if(JR.getCT2().contains(c.getType(), false)){
			colortype=2;
		}
		else if(JR.getCT3().contains(c.getType(), false)){
			colortype=3;
		}
		if(colortype==1){
			pu=new Graphic(JR.res.getAtlas("pack").findRegion("putstb1"), x,JR.HEIGHT/2);			
			pr=new Graphic(JR.res.getAtlas("pack").findRegion("prtstb1"), JR.WIDTH/2,y);
			
		}
		else if(colortype==2){
			pu=new Graphic(JR.res.getAtlas("pack").findRegion("putstr1"), x,JR.HEIGHT/2);
			pr=new Graphic(JR.res.getAtlas("pack").findRegion("prtstr1"), JR.WIDTH/2,y);
			
		}
		else{
			pu=new Graphic(JR.res.getAtlas("pack").findRegion("putst1"), x,JR.HEIGHT/2);
			pr=new Graphic(JR.res.getAtlas("pack").findRegion("prtst1"), JR.WIDTH/2,y);
		}
		pu.setDimensions(pu.getWidth()*4, pu.getHeight()*4);
		pr.setDimensions(pr.getWidth()*4, pr.getHeight()*4);
	}
	@Override
	public void update(float dt) {
		
		if(timer==MaxTime){
			if(colortype==1){
				pu.setImage(JR.res.getAtlas("pack").findRegion("putstb1"));			
				pu.setImage(JR.res.getAtlas("pack").findRegion("prtstb1"));		
			}
			else if(colortype==2){
				pu.setImage(JR.res.getAtlas("pack").findRegion("putstr1"));			
				pu.setImage(JR.res.getAtlas("pack").findRegion("prtstr1"));		}
			else{
				pu.setImage(JR.res.getAtlas("pack").findRegion("putst1"));			
				pu.setImage(JR.res.getAtlas("pack").findRegion("prtst1"));		}
			}
		else if(timer==4*MaxTime/5){
			if(colortype==1){
				pu.setImage(JR.res.getAtlas("pack").findRegion("putstb2"));			
				pu.setImage(JR.res.getAtlas("pack").findRegion("prtstb2"));		
			}
			else if(colortype==2){
				pu.setImage(JR.res.getAtlas("pack").findRegion("putstr2"));			
				pu.setImage(JR.res.getAtlas("pack").findRegion("prtstr2"));		}
			else{
				pu.setImage(JR.res.getAtlas("pack").findRegion("putst2"));			
				pu.setImage(JR.res.getAtlas("pack").findRegion("prtst2"));		}
			
		}
		else if(timer==3*MaxTime/5){
			if(colortype==1){
				pu.setImage(JR.res.getAtlas("pack").findRegion("putstb3"));			
				pu.setImage(JR.res.getAtlas("pack").findRegion("prtstb3"));		
			}
			else if(colortype==2){
				pu.setImage(JR.res.getAtlas("pack").findRegion("putstr3"));			
				pu.setImage(JR.res.getAtlas("pack").findRegion("prtstr3"));		}
			else{
				pu.setImage(JR.res.getAtlas("pack").findRegion("putst3"));			
				pu.setImage(JR.res.getAtlas("pack").findRegion("prtst3"));		}
		}
		else if(timer==2*MaxTime/5){
			if(colortype==1){
				pu.setImage(JR.res.getAtlas("pack").findRegion("putstb4"));			
				pu.setImage(JR.res.getAtlas("pack").findRegion("prtstb4"));		
			}
			else if(colortype==2){
				pu.setImage(JR.res.getAtlas("pack").findRegion("putstr4"));			
				pu.setImage(JR.res.getAtlas("pack").findRegion("prtstr4"));		}
			else{
				pu.setImage(JR.res.getAtlas("pack").findRegion("putst4"));			
				pu.setImage(JR.res.getAtlas("pack").findRegion("prtst4"));		}
		}
		else if(timer==MaxTime/5){
			if(colortype==1){
				pu.setImage(JR.res.getAtlas("pack").findRegion("putstb5"));			
				pu.setImage(JR.res.getAtlas("pack").findRegion("prtstb5"));		
			}
			else if(colortype==2){
				pu.setImage(JR.res.getAtlas("pack").findRegion("putstr5"));			
				pu.setImage(JR.res.getAtlas("pack").findRegion("prtstr5"));		}
			else{
				pu.setImage(JR.res.getAtlas("pack").findRegion("putst5"));			
				pu.setImage(JR.res.getAtlas("pack").findRegion("prtst5"));		}
		}
		if(timer>=0){
			if(count==0){
				pu.setWidth((MaxTime-timer)/MaxTime*60);
				pr.setHeight((MaxTime-timer)/MaxTime*60);
			}
			else{
				pu.setWidth((timer)/MaxTime*60);
				pr.setHeight((timer)/MaxTime*60);
			}
			timer-=MaxTime/35;
			
		}
		else{
			count++;
			if(count==2){
				isFinished=true;
			}
			timer=MaxTime;
		}
	}

	@Override
	public void render(SpriteBatch sb) {
	//System.out.println(colortype);
	
		//sb.setColor(1,1,1,1);
		if(putype==2){
			pu.render(sb);
		}
		else if(putype==1){
			pr.render(sb);
		}
		else if(putype==3){
			pu.render(sb);
			pr.render(sb);
		}
		
	}

}
