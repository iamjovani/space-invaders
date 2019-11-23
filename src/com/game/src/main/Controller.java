package com.game.src.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Controller {
	
	private LinkedList<Bullet> b = new LinkedList<Bullet>();
	private Bullet tempBullet;
	private Game game;
	
	
	public Controller(Game game)
	{
		this.game = game;
	}
	
	public void tick()
	{
		for(int x = 0; x < b.size(); x++)
		{
			tempBullet = b.get(x);
			
			if(tempBullet.getY() < 0)
				b.remove(tempBullet);
			
			tempBullet.tick();
		}
		
	}
	
	public void render(Graphics g)
	{
		for(int x = 0; x < b.size(); x++)
		{
			tempBullet = b.get(x);
			tempBullet.render(g);
		}
		
	}
	
	public void addBullet(Bullet bullet)
	{
		b.add(bullet);
	}
	
	public void removeBullet(Bullet bullet)
	{
		b.remove(bullet);
	}

}
