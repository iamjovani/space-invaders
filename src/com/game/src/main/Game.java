package com.game.src.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 *9;
	public static final int SCALE = 2;
	public final String TITLE = "2D Space Game";
	
	private boolean running = false;
	private Thread thread;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage background = null;
	private BufferedImage bullet = null;
	private BufferedImage enemySheet = null;
	
	private Player player;
	private Enemy enemy;
	private Controller c;
	
	private boolean isShooting;
	
	public void init()
	{
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		try
		{
			spriteSheet     =  loader.loadImage("/spaceship3.png");
			background      =  loader.loadImage("/Background.png");
			bullet          =  loader.loadImage("/bullet.png"); 
			enemySheet 	    =  loader.loadImage("/enemy.png");
			
			
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
		addKeyListener(new KeyInput(this));
		//SpriteSheet ss = new SpriteSheet(spriteSheet);
		player = new Player(200, 200, this);
		enemy  = new Enemy(10, 10, this);
		c =  new Controller(this);
		
	}
	
	private synchronized void start()
	{
		if (running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop()
	{
		if (!running)
			return;
		
		running = false;
		
		try {
			thread.join();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		System.exit(1);
	}
	
	public void run()
	{
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		while(running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime)/ ns;
			lastTime = now;
			
			if(delta >= 1 )
			{
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000)
			{
				
				timer += 1000;
				System.out.println(updates + " Ticks, Fps "  + frames);
				updates = 0;
				frames = 0;
			}
		}
		
		stop();
	}
	
	
	private void tick()
	{
		player.tick();
		c.tick();
	
	}
	
	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null)
		{
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		/////////////////////////////////
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		g.drawImage(background, 0, 0, null);
		player.render(g);
		enemy.render(g);
		c.render(g);
		//g.drawImage(player, 100, 100, this);
		////////////////////////////////
	
		
		g.dispose();
		bs.show();
		enemy.Controller(WIDTH);
	}
	
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_RIGHT)
		{
			player.setVelX(5);
		}
		
		if (key == KeyEvent.VK_LEFT)
		{
			player.setVelX(-5);
		}
		
		if (key == KeyEvent.VK_DOWN)
		{
			player.setVelY(5);
		}
		
		if (key == KeyEvent.VK_UP)
		{
			player.setVelY(-5);
		}
		
		if(key == KeyEvent.VK_SPACE && !isShooting)
		{
			c.addBullet(new Bullet((int)player.getX(), (int)player.getY(), this));
			isShooting = true;
		}
			
	}
	
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_RIGHT)
			player.setVelX(0);
		
		if (key == KeyEvent.VK_LEFT)
			player.setVelX(0);
		
		if (key == KeyEvent.VK_DOWN)
			player.setVelY(0);
		
		if (key == KeyEvent.VK_UP)
			player.setVelY(0);
		
		if (key == KeyEvent.VK_SPACE)
			isShooting = false;
	}

	
	public static void main(String args[])
	{
		Game game = new Game();
		
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}


	public BufferedImage getSpriteSheet()
	{
		return spriteSheet;
	}
	
	public BufferedImage getSpriteBullet() 
	{
		return bullet;
	}
	
	public BufferedImage getEnemySprite() 
	{
		return enemySheet;
	}
}
