package com.fortyways.jewel.game;

import com.fortyways.jewel.game.Cell.CellType;

public class Level {
	private  CellType[] AllowedCells;
	private CellType[] RequiredCells;
	private int [] Amount;
	private int Moves;
	private int Number;
	private int score1star=0,score2star=0,score3star=0;
	private int CellWorth=20;
	private int mode=0;
	private int[] platespos;
	private boolean hasFrosen=false;
	private boolean hasForbiddenCells=false;
	private int[] colpos;
	private int[]forbpos;
	/*
	 * 0-normal
	 * 1-reversed
	 * 2-plates
	 * 3-collect
	 */
	public Level() {
		
		
	}
	public Level(CellType[] AllowedCells,CellType[] RequiredCells,int[] Amount,int Moves,int Number) {
		this.AllowedCells=AllowedCells;
		this.RequiredCells=RequiredCells;
		this.Amount=Amount;
		this.Moves=Moves;
		this.Number=Number;
		int totam=0;
		for(int i=0;i<Amount.length;i++){
			totam+=Amount[i];
		}
		if(mode==1){
			for(int i=0;i<Amount.length;i++){
				score1star+=Amount[i]*CellWorth;
				score2star+=Amount[i]*CellWorth*1.5;
				score3star+=Amount[i]*CellWorth*2;
			}
		}
		else if(totam>40){
			for(int i=0;i<Amount.length;i++){
				score1star+=Amount[i]*CellWorth;
				score2star+=Amount[i]*CellWorth*1.5;
				score3star+=Amount[i]*CellWorth*2;
			}
		}
		else if(mode==3){
			for(int i=0;i<Amount.length;i++){
				score1star+=Amount[i]*CellWorth*2;
				score2star+=Amount[i]*CellWorth*4;
				score3star+=Amount[i]*CellWorth*6;
			}
		}
		else{
		for(int i=0;i<Amount.length;i++){
			score1star+=Amount[i]*CellWorth;
			score2star+=Amount[i]*CellWorth*3;
			score3star+=Amount[i]*CellWorth*5;
		}
		}
		
	}
	
	public CellType[] getAllowedCells() {
		return AllowedCells;
	}
	public int[] getAmount() {
		return Amount;
	}
	public CellType[] getRequiredCells() {
		return RequiredCells;
	}
	public int getMoves() {
		return Moves;
	}
	public int getNumber() {
		return Number;
	}
	public int getScore1star() {
		return score1star;
	}
	public int getScore2star() {
		return score2star;
	}
	public int getScore3star() {
		return score3star;
	}
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	public void setPlatespos(int[] platespos) {
		this.platespos = platespos;
	}
	public int[] getPlatespos() {
		return platespos;
	}
	public int[] getForbpos() {
		return forbpos;
	}
	public void setForbpos(int[] forbpos) {
		this.forbpos = forbpos;
	}
	public void setHasForbiddenCells(boolean hasForbiddenCells) {
		this.hasForbiddenCells = hasForbiddenCells;
	}
	public void setHasFrosen(boolean hasFrosen) {
		this.hasFrosen = hasFrosen;
	}
	public boolean HasFrosen(){
		return hasFrosen;
	}
	public boolean HasForbiddenCells(){
		return hasForbiddenCells;
	}
	public int[] getColpos() {
		return colpos;
	}
	public void setColpos(int[] colpos) {
		this.colpos = colpos;
	}
}
