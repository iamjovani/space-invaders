package com.game.src.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Enemy 
{
	private double x;
	private double y;
	private double velX = 0;
	private double velY = 0;
	
	private boolean goingUp; 
	
	private BufferedImage enemy;
	
	
	public Enemy(double x, double y, Game game)
	{
		this.x = x;
		this.y = y;
		
		SpriteSheet ss = new SpriteSheet(game.getEnemySprite());
		enemy = ss.grabImage(1, 1, 32, 32);
	}
	
	public void Controller(int screenwidth) 
	{ 
		if (this.x >= (screenwidth * 2)) {
	        goingUp = true;
	    }
	    else if (this.x <= 0) {
	        goingUp = false;
	    }
	     
	    if (goingUp) {
	        this.x -= 0.5;
	    } else {
	        this.x += 0.5;
	    }
	    System.out.println(screenwidth);
	}
	
	public void setVelX(double velX)
	{
		this.velX = velX;	
	}
	
	public void setVelY(double velY)
	{
		this.velY = velY;
	}
	
	public void setX(double x)
	{
		this.x = x;
	}
	
	public void setY(double y)
	{
		this.y = y;
	}
	
	public double getX()
	{
		return this.x;
	}
	
	public double getY()
	{
		return this.y;
	}
	
	
	public void render(Graphics g)
	{
		g.drawImage(enemy, (int)x, (int)y, null);
	}
}

