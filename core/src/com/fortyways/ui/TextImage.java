package com.fortyways.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fortyways.jewel.JR;
import com.fortyways.util.Box;

public class TextImage extends Box{
	private TextureRegion[][] fontSheet;
	private String text;
	private int size=30;
	public TextImage(String text,float x,float y) {
		//super(x,y,)
		this.x=x;
		this.y=y;
		this.text=text;
		TextureRegion sheet =JR.res.getAtlas("pack").findRegion("Font");
		fontSheet=sheet.split(8, 8);
	}
	public TextImage(String text,float x,float y,int size) {
		//super(x,y,)
		this.x=x;
		this.y=y;
		this.text=text;
		this.size=size;
		TextureRegion sheet =JR.res.getAtlas("pack").findRegion("Font");
		fontSheet=sheet.split(8, 8);
	}
	public void setSize(int size) {
		this.size = size;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setDimensions(int size){
		this.size=size;
	//	this.height=height;
	}
	public void setColor(float r,float g, float b,float a){
		if(r>=0&&r<=1){	
		}
		if(g>=0&&g<=1){
		}
		if(b>=0&&b<=1){
		}
		if(a>=0&&a<=1){
		}
	}
	public void render(SpriteBatch sb){
		for(int i=0;i<text.length();i++){
			char c=text.charAt(i);
			//int index;
			if(c>='a'&&c<='z'){
				c-='a';
				
				}
				int index=(int)c;
				int row=index/fontSheet[0].length;
				int col=index%fontSheet[0].length;
			
				if(c==' '){
					
				}
				else{
			//	sb.setColor(r, g, b, a);
				sb.draw(fontSheet[row][col],x-width/2+size*i,y-height/2+25,size,size);
			//	sb.setColor(1,1,1,1);
				}
		}
		
		
		
	}
	
	
	
}
