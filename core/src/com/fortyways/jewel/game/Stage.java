package com.fortyways.jewel.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Stage {
	private TextureRegion Preview;
	private TextureRegion Bg;
	private TextureRegion NamePic;
	private String Name;
	private int LevelAmount;
	private int LevelNumToStartFrom;
	private LevelStorage ls;
	private String Description;
	public Stage(TextureRegion Bg,TextureRegion preview,TextureRegion np,String Name,int lvlam,int lvlnum) {
		this.Bg=Bg;
		this.Name=Name;
		this.NamePic=np;
		this.LevelAmount=lvlam;
		this.Preview=preview;
		this.LevelNumToStartFrom=lvlnum;
		ls=new LevelStorage();
	}
	public Level getLvl(int lvlNum){
		if((lvlNum)<(LevelNumToStartFrom+LevelAmount)){
		return ls.getLevel(lvlNum);
		}
		return null;
	}
	public TextureRegion getBg() {
		return Bg;
	}
	public TextureRegion getPreview() {
		return Preview;
	}
	public String getName() {
		return Name;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public int getLevelNumToStartFrom() {
		return LevelNumToStartFrom;
	}
	public int getLevelAmount() {
		return LevelAmount;
	}
	public TextureRegion getNamePic() {
		return NamePic;
	}
}
