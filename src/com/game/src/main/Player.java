package com.game.src.main;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player {
	
	private double x;
	private double y;
	private double velX = 0;
	private double velY = 0;
	
	private BufferedImage player;
	
	
	public Player(double x, double y, Game game)
	{
		this.x = x;
		this.y = y;
		
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		player = ss.grabImage(1, 1, 32, 32);
	}

	public void tick()
	{
		x+= velX;
		y+= velY;
		
		if (x < 0)
			x = 0;
		
		if (x > 625)
			x = 625;
		
		
		if (y > 470)
			y = 470;
		
		if(y < 0)
			y = 0;
			
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
		g.drawImage(player, (int)x, (int)y, null);
	}
}
