package com.fortyways.animation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fortyways.jewel.JR;
import com.fortyways.jewel.game.Cell;

public class CellGenerationAnim extends Animation{
	public float StartPos=JR.HEIGHT;
	public Cell c;
	public  CellGenerationAnim(Cell c) {
		this.c=c;
		MaxTime=c.getMaxTime();
		timer=0f;
	}
	@Override
	public void update(float dt) {
	timer+=MaxTime/100f;
	c.setY(StartPos-(timer/MaxTime)*StartPos+c.getY());
		
	}

	@Override
	public void render(SpriteBatch sb) {
		c.render(sb);
		
	}

}
