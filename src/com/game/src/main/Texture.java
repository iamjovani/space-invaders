package com.game.src.main;

import java.awt.image.BufferedImage;

public class Texture {
	
	private BufferedImage player, missile, enemy;
	private SpriteSheet ss;
	
	public Texture(Game game)
	{
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		getTexture();
	}
	
	
	public void getTexture()
	{
		
	}

}
