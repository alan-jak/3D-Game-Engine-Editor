package com.editor.wysiwygEngine.base.rendering;

import java.awt.Canvas;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.glViewport;

import com.editor.wysiwygEngine.base.core.Matrix4f;
import com.editor.wysiwygEngine.base.core.Vector2f;

public class Window
{
	public static Canvas canvass;
	public static void createWindow(int width, int height, String title)
	{
		Display.setTitle(title);
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
			Keyboard.create();
			Mouse.create();
		} catch (LWJGLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void createWindow(int width, int height, String title, boolean fullscreen, Canvas canvas)
	{
		canvass = canvas;
		Display.setTitle(title);
		try {
			Display.setParent(canvas);
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
			Keyboard.create();
			Mouse.create();
		} catch (LWJGLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void render()
	{
		Display.update();
	}
	
	public static void dispose()
	{
		Display.destroy();
		Keyboard.destroy();
		Mouse.destroy();
	}
	
	public static boolean isCloseRequested()
	{
		return Display.isCloseRequested();
	}
	
	public static int getWidth()
	{
		return Display.getDisplayMode().getWidth();
	}
	
	public static int getHeight()
	{
		return Display.getDisplayMode().getHeight();
	}
	
	public static String getTitle()
	{
		return Display.getTitle();
	}
	
	public Vector2f getCenter()
	{
		return new Vector2f(getWidth()/2, getHeight()/2);
	}
	
	public static boolean isFullscreen()
	{
		return Display.isFullscreen();
	}

	public static void update(RenderingEngine renderingEngine)
	{
		if(canvass.getParent().getHeight() != canvass.getHeight() || canvass.getParent().getWidth() != canvass.getWidth())
		{
			try {
				Display.setDisplayMode(new DisplayMode(canvass.getParent().getWidth(), canvass.getParent().getHeight()));
				glViewport(0, 0, canvass.getParent().getWidth(), canvass.getParent().getHeight());
				if(canvass.getParent().getWidth() > 800 || canvass.getParent().getHeight() > 600)
					renderingEngine.getMainCamera().setProjection(new Matrix4f().initPerspective((float)Math.toRadians(90), canvass.getParent().getWidth() / canvass.getParent().getHeight(), 0.01f, 1000));
				else
					renderingEngine.getMainCamera().setProjection(new Matrix4f().initPerspective((float)Math.toRadians(70), canvass.getParent().getWidth() / canvass.getParent().getHeight(), 0.01f, 1000));
			} catch (LWJGLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
