package com.fortyways.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.fortyways.jewel.JR;
import com.fortyways.jewel.game.Cell.CellType;

public class FillScreenAnim extends Animation{
	
	private TextureRegion[] Gems;
	private float[]Xpos;
	private float[]Ypos;
	private float[] StartYpos;
	private float[]Rotation;
	private float[]Speeds;
	private int GemAmount;
	private int numCols;
	private int numRows;
	private int GemSize=50;
	public FillScreenAnim() {
		super(10, 0, 0, null);
		numCols=JR.WIDTH/6;
		numRows=JR.HEIGHT/6;
		GemAmount=numCols*numRows;
		
		System.out.println(GemAmount);
		Gems=new TextureRegion[GemAmount];
		Xpos=new float[GemAmount];
		Rotation=new float[GemAmount];
		Ypos=new float[GemAmount];
		StartYpos=new float[GemAmount];
		Speeds=new float[GemAmount];
		for(int i=0;i<numRows;i++){
			for(int j=0;j<numCols;j++){
				Gems[i*numCols+j]=new TextureRegion(CellType.getTexture(CellType.getRandomType()));
				Xpos[i*numCols+j]=JR.WIDTH/GemSize*j/6-60;
				Ypos[i*numCols+j]=JR.HEIGHT/GemSize*i/2+JR.HEIGHT-50;
				StartYpos[i*numCols+j]=Ypos[i*numCols+j];
				Rotation[i*numCols+j]=MathUtils.random(0f, 70f);
				Speeds[i*numCols+j]=MathUtils.random(20f, 25f);
			}
		}
		
		
	}
	private boolean Filled;
	private boolean touched;
	private int curRow=0;
	private float  timer=0f;
	private float Mtime=0.001f;
	@Override
	public void update(float dt) {
		for(int i=0;i<numRows;i++){
			if(i<=curRow)
			for(int j=0;j<numCols;j++){
				if(Ypos[i*numCols+j]>(StartYpos[i*numCols+j]-JR.HEIGHT-Rotation[i*numCols+j]/2)){
					Ypos[i*numCols+j]-=Speeds[i*numCols+j];
					//Rotation[i*numCols+j]=MathUtils.random(0f, 180f);
				}
			}
		}
		if(touched){
			for(int i=0;i<numRows;i++){
				
				for(int j=0;j<numCols;j++){
					if(Ypos[i*numCols+j]>(-300)){
						Ypos[i*numCols+j]-=Speeds[i*numCols+j];
						
					}
				}
			}
		}
		if(timer<Mtime){
			timer+=dt;
		}
		else{
			if(curRow<numRows){
				curRow++;
				timer=0f;
			}
			
		}
		if(Ypos[GemAmount-10]<=(StartYpos[GemAmount-10]-JR.HEIGHT)&&!touched&&!Filled){
			System.out.println("FINISHED");
			Filled=true;
		}
		if(Filled&&touched&&Ypos[GemAmount-10]<=-100 ){
			isFinished=true;
		}
		handleInput();
	}

	public void handleInput(){
		if(Gdx.input.justTouched()&&Filled&&!touched){
			//touched=true;
		//	System.out.println("TOUCHED");
			curRow=0;
		}
	}
	@Override
	public void render(SpriteBatch sb) {
		for(int i=0;i<numRows;i++){
			for(int j=0;j<numCols;j++){
				
			sb.draw(Gems[i*numCols+j], Xpos[i*numCols+j], Ypos[i*numCols+j],
					Xpos[i*numCols+j], StartYpos[i*numCols+j],
					GemSize, GemSize, 1, 1, Rotation[i*numCols+j]);
				}
			}
	}

}
