package com.game.src.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Bullet {
	
	private double x;
	private double y;
	
	BufferedImage image;
	
	public Bullet(int x, int y, Game game)
	{
		this.x = x;
		this.y = y;
		
		SpriteSheet ss = new SpriteSheet(game.getSpriteBullet());
		image = ss.grabImage(1, 1, 32, 32);// change when bullet sprite arrives.
	}
	
	public void tick()
	{
		this.y -= 10;
	}
	
	public void render(Graphics g)
	{
		g.drawImage(image, (int)x, (int)y, null);
	}
	
	public double getY()
	{
		return y;
	}

}
