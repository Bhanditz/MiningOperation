package com.fortyways.animation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.fortyways.jewel.JR;

public class PixelDustAnim extends Animation{

	public enum PixelType{
		Yellow,Red,Blue,Green;
		public static PixelType getRandomType(){
			int rp=MathUtils.random(0, 3);
			PixelType pt = null;
			if(rp==0){
				pt=PixelType.Blue;	
			}
			else if(rp==1){
				pt=PixelType.Green;	
			}
			else if(rp==2){
				pt=PixelType.Red;	
			}
			else if(rp==3){
				pt=PixelType.Yellow;	
			}
			return pt;
		}
	}
	private TextureRegion[] Pixels;
	private float[] Timer;
	private float[] Mtime;
	private float[] Xpos;
	private float[] Ypos;
	private boolean[] Showing;
	private int PixAmount;
	private PixelType[] UsedColors;
	private TextureRegion[] PixelSprites;
	private int SpriteAmount;
	private float width;
	private float height;
	private float size=5;
	public PixelDustAnim(float width,float height,float x,float y) {
		super(0,x,y,null);
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		SpriteAmount=4;
		PixelSprites =new TextureRegion[SpriteAmount];
		PixelSprites[0]=JR.res.getAtlas("pack").findRegion("GreenPixels");
		PixelSprites[1]=JR.res.getAtlas("pack").findRegion("BluePixels");
		PixelSprites[2]=JR.res.getAtlas("pack").findRegion("YellowPixels");
		PixelSprites[3]=JR.res.getAtlas("pack").findRegion("RedPixels");
		UsedColors=new PixelType[1];
		UsedColors[0]=PixelType.getRandomType();
		//UsedColors[1]=PixelType.Red;
		//UsedColors[2]=PixelType.Green;
		//UsedColors[3]=PixelType.Yellow;
		PixAmount=250;
		SetPixels();
		
	}
	private void SetPixels() {
		TextureRegion[][] Parts=null;
		TextureRegion[][] PartsRed;
		TextureRegion[][] PartsBlue;
		TextureRegion[][] PartsGreen;
		TextureRegion[][] PartsYellow;
		Pixels=new TextureRegion[PixAmount];
		Timer=new float[PixAmount];
		Mtime=new float[PixAmount];
		Xpos=new float[PixAmount];
		Ypos=new float[PixAmount];
		Showing=new boolean[PixAmount];
		PartsRed=PixelSprites[3].split(1, 1);
		PartsBlue=PixelSprites[1].split(1, 1);
		PartsGreen=PixelSprites[0].split(1, 1);
		PartsYellow=PixelSprites[2].split(1, 1);
		int CurType;
		for(int i=0;i<PixAmount;i++){
			CurType=MathUtils.random(0, UsedColors.length-1);
			PixelType pt=UsedColors[CurType];
			if(pt==PixelType.Blue){
				Parts=PartsBlue;
			}
			else
			if(pt==PixelType.Red){
				Parts=PartsRed;
			}
			else
			if(pt==PixelType.Yellow){
				Parts=PartsYellow;
			}
			else
			if(pt==PixelType.Green){
				Parts=PartsGreen;
			}
			Pixels[i]=Parts[MathUtils.random(0, Parts.length-1)][MathUtils.random(0, Parts[0].length-1)];
			Timer[i]=0;
			Mtime[i]=MathUtils.random(2f,3f);
			Xpos[i]=MathUtils.random(x, x+width);
		
			Ypos[i]=MathUtils.random(y, y+height);
			Showing[i]=MathUtils.randomBoolean();
		}
		
	}
	@Override
	public void update(float dt) {
		for(int i=0;i<PixAmount;i++){
			if(Timer[i]<=Mtime[i]){
				Timer[i]+=2*dt;
				
			}
			else{
				Timer[i]=0f;
				Xpos[i]=MathUtils.random(x,x+width);
				//System.out.println(Xpos[i]);
				Ypos[i]=MathUtils.random(y, y+height);
				Showing[i]=!Showing[i];
			}
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		for(int i=0;i<PixAmount;i++){
			if(Showing[i]){
				if(Timer[i]<=Mtime[i]/2){
					if(Timer[i]<=1){
					sb.setColor(1, 1, 1,Timer[i]);}
					else{
					sb.setColor(1, 1, 1,1);
					}
				}
				
				
			
				else
				{
					if((Mtime[i]-Timer[i])<=0){
						sb.setColor(1, 1, 1,0);
					}
					else if((Mtime[i]-Timer[i])>=1){
						sb.setColor(1, 1, 1,1);
					}
					else
					sb.setColor(1, 1, 1,(Mtime[i]-Timer[i]));
				}
				//sb.draw(Pixels[i], Xpos[i], Ypos[i], size+20*Timer[i], size+20*Timer[i]);
			
				sb.draw(Pixels[i], Xpos[i], Ypos[i], size+5*Timer[i], size+5*Timer[i]);
				sb.setColor(1, 1, 1,1);
			}
		}
	}
	

}
