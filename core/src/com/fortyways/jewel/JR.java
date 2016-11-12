package com.fortyways.jewel;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fortyways.jewel.game.Cell.CellType;
import com.fortyways.jewel.game.LevelStorage;
import com.fortyways.jewel.game.PlayerSettings;
import com.fortyways.state.GSM;
import com.fortyways.state.MainMenuState;
import com.fortyways.util.Content;
import com.fortyways.util.GFX;

public class JR extends ApplicationAdapter {
	SpriteBatch sb;

	public static final String TITLE = "Jewel Rush";
	public static int WIDTH = 480;
	public static int HEIGHT = 800;
	private GSM gsm;
	public static Content res;
	private static LevelStorage ls;
	private static Array<CellType>CT1=new Array<CellType>();
	private static	Array<CellType> CT2=new Array<CellType>();
	private static Array<CellType> CT3=new Array<CellType>();
	private static Array<CellType> CT4=new Array<CellType>();
	private static Array<CellType> ColCells=new Array<CellType>();
	public static PlayerSettings ps;


	

	
	
	
	
	@Override
	public void pause() {
		
		super.pause();
	}
	@Override
	public void resume() {
		
		super.resume();
	}
	@Override
	public void create () {
		ls=new LevelStorage();
		ps=new PlayerSettings();
		sb = new SpriteBatch();
		res=new Content();
		res.loadAtlas("pack.pack", "pack");
		GFX.setTexture(JR.res.getAtlas("pack").findRegion("light").getTexture());

		CT1.add(CellType.CRYSTAL1);
		CT1.add(CellType.CRYSTAL2);
		CT1.add(CellType.CRYSTAL3);
		CT1.add(CellType.CRYSTAL4);
		CT1.add(CellType.CRYSTAL5);
		CT1.add(CellType.CRYSTAL6);
		CT1.add(CellType.CRYSTAL7);
		
		CT2.add(CellType.REDCRYSTAL1);
		CT2.add(CellType.REDCRYSTAL2);
		CT2.add(CellType.REDCRYSTAL3);
		CT2.add(CellType.REDCRYSTAL4);
		CT2.add(CellType.REDCRYSTAL5);
		CT2.add(CellType.REDCRYSTAL6);
		
		CT3.add(CellType.GCRYSTAL1);
		CT3.add(CellType.GCRYSTAL2);
		CT3.add(CellType.GCRYSTAL3);
		CT3.add(CellType.GCRYSTAL4);
		CT3.add(CellType.GCRYSTAL5);
		
		CT4.add(CellType.CRYSTAL1);
		CT4.add(CellType.CRYSTAL2);
		CT4.add(CellType.CRYSTAL3);
		CT4.add(CellType.CRYSTAL4);
		CT4.add(CellType.CRYSTAL5);
		CT4.add(CellType.CRYSTAL6);
		CT4.add(CellType.CRYSTAL7);
		CT4.add(CellType.REDCRYSTAL1);
		CT4.add(CellType.REDCRYSTAL2);
		CT4.add(CellType.REDCRYSTAL3);
		CT4.add(CellType.REDCRYSTAL4);
		CT4.add(CellType.REDCRYSTAL5);
		CT4.add(CellType.REDCRYSTAL6);
		CT4.add(CellType.GCRYSTAL1);
		CT4.add(CellType.GCRYSTAL2);
		CT4.add(CellType.GCRYSTAL3);
		CT4.add(CellType.GCRYSTAL4);
		CT4.add(CellType.GCRYSTAL5);
		
		ColCells.add(CellType.CRATE1);
		gsm=new GSM();
		gsm.set(new MainMenuState(gsm));
	}
	
	@Override
	public void render () {
		gsm.update(Gdx.graphics.getDeltaTime());
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		
		gsm.render(sb);
	}
public static LevelStorage getLs() {
	return ls;
}
public static Array<CellType> getCT1() {
	return CT1;
}
public static Array<CellType> getCT2() {
	return CT2;
}
public static Array<CellType> getCT3() {
	return CT3;
}
public static Array<CellType> getCT4() {
	return CT4;
}
	public static Array<CellType> getColCells() {
		return ColCells;
	}
}
