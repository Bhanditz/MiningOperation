package com.fortyways.animation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class DestroyAnim extends Animation{
	Vector2[] CirDot;
	Vector2[] Pos;
	float rad=15;
	float size=5;
	float speed=0.3f;
	int amount=70;
	TextureRegion[][] Parts;
	public DestroyAnim(float MaxTime,float x,float y,TextureRegion texture) {
		super(MaxTime,x,y,texture);
		Parts= texture.split(1, 1);
		CirDot=new Vector2[amount];
		Pos=new Vector2[amount];
		for(int i=0;i<amount;i++)
		{
			CirDot[i]=new Vector2();
			CirDot[i].x=MathUtils.random(-rad, rad);
			CirDot[i].y=(float) Math.sqrt(rad*rad-CirDot[i].x*CirDot[i].x);
			if(MathUtils.random(1, 2)==1){
				CirDot[i].y*=(-1);
			}
			Pos[i]=new Vector2(0,0);
		}
	}

	@Override
	public void update(float dt) {
		if(timer<MaxTime){
		for(int i=0;i<amount;i++){
			if(Pos[i].x<CirDot[i].x )
			{
				if(CirDot[i].x<0){
					Pos[i].x=0;
				}
				else
				if(CirDot[i].x>=0){
					Pos[i].x+=dt*MathUtils.random(50, 100)*speed;
				}
			
			}
			else if(Pos[i].x>=CirDot[i].x){
				
				if(CirDot[i].x>=0){
					Pos[i].x=0;
				}
				else if(CirDot[i].x<0){
					Pos[i].x-=dt*MathUtils.random(50, 100)*speed;;
				}
			}
			if(Pos[i].y<CirDot[i].y )
			{
				if(CirDot[i].y<0){
					Pos[i].y=0;
				}
				else
				if(CirDot[i].y>=0){
					Pos[i].y+=MathUtils.random(50, 100)*(dt)*speed;
				}
			
			}
			else if(Pos[i].y>=CirDot[i].y){
				
				if(CirDot[i].y>=0){
					Pos[i].y=0;
				}
				else if(CirDot[i].y<0){
					Pos[i].y-=MathUtils.random(50, 100)*(dt)*speed;
				}
			}
			
			
		}
		timer+=dt;
		}
		else{
			isFinished=true;
		}
		
	}

	@Override
	public void render(SpriteBatch sb) {
		int count=0;
			if(!isFinished){
				for(int i=9;i<16;i++){
					for(int j=8;j<16;j++){
						//if(Fake!=Parts[i][j])
						{
							if(1f-(timer/MaxTime+timer%MaxTime)<0){
						sb.setColor(1.0f, 1.0f, 1.0f,0);
							}
							else{
						sb.setColor(1.0f, 1.0f, 1.0f,1f-(timer/MaxTime+timer%MaxTime));
						}
						sb.draw(Parts[i][j], 
								x+Pos[count].x*2,y+Pos[count].y*2,0,0, size, size, 1, 1, 0);
						count++;
						sb.setColor(1, 1, 1,1);
						}
					}
				}
			}
	}
	public void setRad(float rad) {
		this.rad = rad;
	}
	public void setSize(float size) {
		this.size = size;
	}

}
