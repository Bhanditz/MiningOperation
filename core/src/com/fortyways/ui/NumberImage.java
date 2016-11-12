package com.fortyways.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fortyways.jewel.JR;
import com.fortyways.util.Box;

public class NumberImage extends Box{

	private TextureRegion[][] Numbers;
	private String Number;
	private int size=20;
	private int type=0; 
	public NumberImage(String num,float x,float y) {
		//super(x,y,)
		this.x=x;
		this.y=y;
		this.Number=num;
		
		TextureRegion sheet =JR.res.getAtlas("pack").findRegion("Numbers");
		Numbers=sheet.split(8, 8);
		}
		
		
	
	public void SetNumber(String Number){
		this.Number=Number;
	}
	public void setType(int t){
		type=t;
		if(type==0){
			TextureRegion sheet =JR.res.getAtlas("pack").findRegion("Numbers");
			Numbers=sheet.split(8, 8);
		}
		else if(type==1){
			TextureRegion sheet =JR.res.getAtlas("pack").findRegion("Numbers2");
			Numbers=sheet.split(15, 23);
		}
	}
	public int Dist=5;
	public void setSize(int size) {
		this.size = size;
	}
	private Color col=Color.WHITE;
	public void setColor(Color c){
		col=c;
		colorset=true;
	}
	private boolean colorset=false;
	private boolean flick=false;
	private float flicktimer=2f;
	public void setFlickering(boolean f){
		flick=f;
	}
	public void update(float dt){
		if(flick){
			if(flicktimer>=0){
				flicktimer-=2f/180;
				//r*=flicktimer;
				//g*=flicktimer;
				//b*=flicktimer;
			}
			else{
				flicktimer=2f;
			}
		}
	}
	public void render(SpriteBatch sb){
		if(flick&& col==Color.GREEN){
				if(flicktimer>=1){
					sb.setColor(flicktimer-1,1, flicktimer-1, 1);//white2green
					
				}
				else{
					sb.setColor(1-flicktimer, 1, 1-flicktimer,1);//green2white
				}
				}
				else if(flick&& col==Color.RED){
					if(flicktimer>=1){
						sb.setColor(1,flicktimer-1, flicktimer-1, 1);//white2green
					}
					else{
						sb.setColor(1,1-flicktimer, 1-flicktimer, 1);//green2white
					}
				}
				else if(flick&&  col==Color.BLUE){
					if(flicktimer>=1){
						sb.setColor(flicktimer-1,flicktimer-1,1, 1);//white2green
					}
					else{
						sb.setColor(1-flicktimer,  1-flicktimer,1, 1);//green2white
					}	
				}
				else
				{
					if(colorset){
					sb.setColor(col);}
					//sb.setColor(r,g,b, a);
				}
			
		for(int i=0;i<Number.length();i++){
			char c=Number.charAt(i);
			int index;
			
				if(c>='0'&&c<='9'){
					index=Character.getNumericValue(c);
				}
				else if(c=='/'){
					index=10;
				}
				else if(c=='+'){
					index=11;
				}
				else{
					index=12;
				}
			//=(int)c;
			
			
				
		
			sb.draw(Numbers[0][index],x-width/2-Dist*(Number.length()-1+i)+size*i,y-height/2+size,size,size);
		
		}
			sb.setColor(1, 1, 1, 1);
		
		
	}
	public boolean getColor(){
		return colorset;
	}
	public boolean getFlickering(){
		return flick;
	}
	public String getNumber() {
		return Number;
	}
}
