package com.fortyways.jewel.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class PlayerSettings {
	private static  Preferences prefs;
	private static boolean bg;
	private static int LastLevelUnlocked;		//Counting from 0!!!!
	private boolean Swipes;
	//private int Coins;
	private int lastStage;
	private int lastLevelScreen;;
	public PlayerSettings() {
	
		prefs = Gdx.app.getPreferences("My Preferences1");
		
		if(prefs.contains("background")){
			bg=prefs.getBoolean("background");
		}
		else{
			prefs.putBoolean("background", true);
			bg=true;
		}
		if(prefs.contains("swipes")){
			Swipes=prefs.getBoolean("swipes");
		}
		else{
			prefs.putBoolean("swipes", true);
			Swipes=true;
		}
		if(prefs.contains("lastlevelscreen")){
			lastLevelScreen=prefs.getInteger("lastlevelscreen");
		}
		else{
			lastLevelScreen=0;
			prefs.putInteger("lastlevelscreen", 0);
			prefs.flush();
		}
		if(prefs.contains("laststage")){
			lastLevelScreen=prefs.getInteger("laststage");
		}
		else{
			lastLevelScreen=0;
			prefs.putInteger("laststage", 0);
			prefs.flush();
		}
		
		
		
	}
	public static int getLastLevelUnlocked() {
		return LastLevelUnlocked;
	}
	public static boolean getBg(){
		return bg;
	}
	public static void setUnlockedLevels(int num){
		if(num>prefs.getInteger("level")){
			prefs.putInteger("level", num);
			prefs.flush();
		}
	}
	public static void setStars(int level,int stars){
		if(prefs.contains("levelstars"+level)){
			if(prefs.getInteger("levelstars"+level)<stars){
				prefs.putInteger("levelstars"+level, stars);
				prefs.flush();
			}
		}
		else{
			prefs.putInteger("levelstars"+level, stars);
			prefs.flush();
		}
		
	}
	public static void setBg(boolean b) {
		bg = b;
		prefs.putBoolean("background", bg);
		prefs.flush();
	}
	public static int getUnlockedLevels(){
		return prefs.getInteger("level");
	}
	public static Preferences getPrefs() {
		return prefs;
	}
	public boolean getSwipe(){
		return Swipes;
	}
	public void setSwipe(boolean sw){
		Swipes=sw;
		prefs.putBoolean("swipes", sw);
		prefs.flush();
	}
	public int getLastLevelScreen() {
		return lastLevelScreen;
	}
	public int getLastStage() {
		return lastStage;
	}
	public void setLastLevelScreen(int lastLevelScreen) {
		this.lastLevelScreen = lastLevelScreen;
		prefs.putInteger("lastlevelscreen", lastLevelScreen);
		prefs.flush();
	}
	public void setLastStage(int lastStage) {
		this.lastStage = lastStage;
		prefs.putInteger("laststage", lastStage);
		prefs.flush();
	}
	public int getStarsFromLevels(int start,int end){
		int count=0;
		for(int i=start;i<=end;i++){
			if(prefs.contains("levelstars"+i)){
				count+=prefs.getInteger("levelstars"+i);
			}
		}
		return count;
	}
}
