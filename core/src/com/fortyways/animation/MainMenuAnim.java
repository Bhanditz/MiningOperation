package com.fortyways.animation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.fortyways.jewel.JR;
import com.fortyways.jewel.game.Cell.CellType;

public class MainMenuAnim extends Animation{

	private TextureRegion[] SomeGems;
	private float[]StartPos;
	private float[]Ypos;
	private float[]Rotation;
	private float[]Speeds;
	private int GemAmount =100;
	public MainMenuAnim() {
		SomeGems=new TextureRegion[GemAmount];
		StartPos=new float[GemAmount];
		Rotation=new float[GemAmount];
		Ypos=new float[GemAmount];
		Speeds=new float[GemAmount];
		for(int i=0;i<GemAmount;i++){
			SomeGems[i]=new TextureRegion(CellType.getTexture(CellType.getRandomType()));
			StartPos[i]=MathUtils.random(0, JR.WIDTH);
			Speeds[i]=MathUtils.random(1f, 10f);
			Ypos[i]=JR.HEIGHT;
			Rotation[i]=0f;
			//Rotation[i]=MathUtils.random(-180f, 180f);
		}
	}
	public MainMenuAnim(Array<CellType>CT){
		SomeGems=new TextureRegion[GemAmount];
		StartPos=new float[GemAmount];
		Rotation=new float[GemAmount];
		Ypos=new float[GemAmount];
		Speeds=new float[GemAmount];
		for(int i=0;i<GemAmount;i++){
			SomeGems[i]=new TextureRegion(CellType.getTexture(CT.random()));
			StartPos[i]=MathUtils.random(0, JR.WIDTH);
			Speeds[i]=MathUtils.random(1f, 10f);
			Ypos[i]=JR.HEIGHT;
			Rotation[i]=0f;
			//Rotation[i]=MathUtils.random(-180f, 180f);
		}
	}
	public void ChangeGems(Array<CellType>CT){
		for(int i=0;i<GemAmount;i++){
			SomeGems[i]=new TextureRegion(CellType.getTexture(CT.random()));
			}
	}
	
	@Override
	public void update(float dt) {

		for(int i=0;i<GemAmount;i++){
			Ypos[i]-=Speeds[i];
			
			if(Ypos[i]<=-100){
				Ypos[i]=JR.HEIGHT;
				Rotation[i]=MathUtils.random(-180f, 180f);
			}
		}
		
	}

	@Override
	public void render(SpriteBatch sb) {
		for(int i=0;i<GemAmount;i++){
			sb.draw(SomeGems[i], StartPos[i], Ypos[i], StartPos[i], Ypos[i], 50, 50, 1, 1,Rotation[i] );
		}
		
	}

}
