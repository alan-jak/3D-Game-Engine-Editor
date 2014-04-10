package com.editor.wysiwygEngine.base.core;

import java.awt.Canvas;

import com.editor.gui.main.HierarchyFrame;
import com.editor.wysiwygEngine.base.rendering.RenderingEngine;
import com.editor.wysiwygEngine.base.rendering.Window;

public class CoreEngine
{	
	private boolean isRunning;
	public static Game game;
	private RenderingEngine renderingEngine;
	private int width;
	private int height;
	private double frameTime;
	private boolean fullscreen;
	
	public CoreEngine(int width, int height, double framerate, Game game)
	{
		this.isRunning = false;
		CoreEngine.game = game;
		this.width = width;
		this.height = height;
		this.frameTime = 1 / framerate;
		this.fullscreen = false;
		game.setEngine(this);
	}
	
	public CoreEngine(int width, int height, double framerate, boolean fullscreen, Game game)
	{
		this.isRunning = false;
		CoreEngine.game = game;
		this.width = width;
		this.height = height;
		this.frameTime = 1 / framerate;
		this.fullscreen = fullscreen;
		game.setEngine(this);
	}
	
	public void createWindow(String title, Canvas canvas)
	{
		Window.createWindow(width, height, title, fullscreen, canvas);
		this.renderingEngine = new RenderingEngine();
		System.out.println("Using OpenGL Version: " + RenderingEngine.getOpenGLVersion());
	}
	
	public void start()
	{
		if(isRunning)
			return;
		
		run();
	}
	
	public void stop()
	{
		if(!isRunning)
			return;
		
		isRunning = false;
	}
	
	private void run()
	{
		isRunning = true;
		
		int frames = 0;
		long frameCounter = 0;
		
		game.init();
		
		double lastTime = Time.getTime();
		double unprocessedTime = 0;
		
		HierarchyFrame.updateHeirarchyTree(game.getRootObject());
		
		while(isRunning)
		{
			Window.update(renderingEngine);
			boolean render = false;
			
			double startTime = Time.getTime();
			double passedTime = startTime - lastTime;
			lastTime = startTime;
			
			unprocessedTime += passedTime;
			frameCounter += passedTime;
			
			while(unprocessedTime > frameTime)
			{
				render = true;
				
				unprocessedTime -= frameTime;
				
				if(Window.isCloseRequested())
					stop();
				
				Input.update();
				
				game.input((float)frameTime);
				game.update((float)frameTime);
				
				if(frameCounter >= 1.0)
				{
					System.out.println(frames);
					frames = 0;
					frameCounter = 0;
				}
			}
			
			if(render)
			{
                game.Render(renderingEngine);
				Window.render();
				frames++;
			}
			else
			{
				try
				{
					Thread.sleep(1);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
		
		cleanUp();
	}
	
	private void cleanUp()
	{
		Window.dispose();
	}
	
	public RenderingEngine getRenderingEngine()
	{
		return renderingEngine;
	}
}
