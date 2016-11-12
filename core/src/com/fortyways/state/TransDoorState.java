package com.fortyways.state;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fortyways.jewel.JR;
import com.fortyways.ui.Graphic;

public class TransDoorState extends State{

	private Graphic Door1,Door2,Door3,Door4;
	
	
	State s1,s2;
	float timer=3f;
	float mtime=3f;
	protected TransDoorState(GSM gsm,State s1,State s2) {
		super(gsm);
		this.s1=s1;
		this.s2=s2;
		Door1=new Graphic(JR.res.getAtlas("pack").findRegion("Door1"), -300, JR.HEIGHT/2+56);
		Door2=new Graphic(JR.res.getAtlas("pack").findRegion("Door2"), JR.WIDTH+300, JR.HEIGHT/2);
		Door1.setDimensions(140*4, 86*4);
		Door2.setDimensions(140*4, 86*4);
		Door3=new Graphic(JR.res.getAtlas("pack").findRegion("Gate"),JR.WIDTH/2,JR.HEIGHT+400);
		Door3.setDimensions(Door3.getWidth()*8, Door3.getHeight()*8);
		Door4=new Graphic(JR.res.getAtlas("pack").findRegion("Gate"),JR.WIDTH/2,-400);
		Door4.setDimensions(Door4.getWidth()*8, Door4.getHeight()*8);
	}

	@Override
	public void handleInput() {
		
	}

	@Override
	public void update(float dt) {
		if(timer>0){
			timer-=mtime/100;
		}
		else{
			gsm.set(s2);
		}
		if(timer>=2*mtime/3){
			//s1.update(dt);
		}
		else if(timer<mtime/3){
			//s2.update(dt);
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		if(timer>=2*mtime/3){
			Door1.setX((float) (Door1.getX()+37*0.5*(timer/4f)));
			Door2.setX((float) (Door2.getX()-38*0.5*(timer/4f)));
			Door3.setY((float) (Door3.getY()-50*0.5*(timer/5)));
			Door4.setY((float) (Door4.getY()+50*0.5*(timer/5)));
			s1.render(sb);
			}
			else if(timer>=mtime/3){
			
				s2.render(sb);
			}
			else{
				Door2.setX((float) (Door2.getX()+36*0.5*(timer*1.2)));
				Door1.setX((float) (Door1.getX()-37*0.5*(timer*1.2)));
				Door3.setY((float) (Door3.getY()+50*0.5*(timer*1.2)));
				Door4.setY((float) (Door4.getY()-50*0.5*(timer*1.2)));
			s2.render(sb);
			}
		sb.begin();
		Door3.render(sb);
		Door4.render(sb);
		Door1.render(sb);
		Door2.render(sb);
		sb.end();
	}

}
