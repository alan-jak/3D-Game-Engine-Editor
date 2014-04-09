package com.editor.wysiwygEngine.base.rendering;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL14.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL32.*;

import java.nio.FloatBuffer;

public class DepthFBO
{
	public static final int DIFFUSE_TEXTURE = 0;
	public static final int NORMAL_TEXTURE = 1;
	public static final int HEIGHT_TEXTURE = 2;
	public static final int SHADOW_TEXTURE_0 = 4;
	public static final int SHADOW_TEXTURE_1 = 5;
	public static final int SHADOW_TEXTURE_2 = 6;
	public static final int SHADOW_TEXTURE_3 = 7;
	
	private static int lastReadBind = 0;
	private static int lastWriteBind = 0;
	private int id;
	private int frameBuffer;
	private int width;
	private int height;
	
//	public Texture(int id, int width, int height)
//	{
//		this.id = id;
//		this.width = width;
//		this.height = height;
//		this.frameBuffer = 0;
//	}
	
	public DepthFBO(int width, int height, FloatBuffer data)
	{
		this(width, height, data, GL_LINEAR, GL_REPEAT);
	}
	
	public DepthFBO(int width, int height, FloatBuffer data, int filter)
	{
		this(width, height, data, filter, GL_REPEAT);
	}
	
	public DepthFBO(int width, int height, FloatBuffer data, int filter, int wrapMode)
	{
		int textureID = glGenTextures(); // Generate texture ID
		glBindTexture(GL_TEXTURE_2D, textureID); // Bind texture ID
		
		// Setup wrap mode (GL_CLAMP | GL_REPEAT)
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, wrapMode);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, wrapMode);

		// Setup texture scaling filtering (GL_LINEAR | GL_NEAREST)
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, filter);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, filter);

		// send texture to graphicscard
		glTexImage2D(GL_TEXTURE_2D, 0, GL_DEPTH_COMPONENT16, width, height, 0, GL_DEPTH_COMPONENT, GL_FLOAT, data);

		this.id = textureID;
		this.frameBuffer = 0;
		this.width = width;
		this.height = height;
	}
	
	public void initRenderTarget(int attachment, boolean bind)
	{
		frameBuffer = glGenFramebuffers();
		glBindFramebuffer(GL_FRAMEBUFFER, frameBuffer);
		glFramebufferTexture(GL_FRAMEBUFFER, attachment, id, 0);
		
		if(attachment == GL_DEPTH_ATTACHMENT)
			glDrawBuffer(GL_NONE);
		if(glCheckFramebufferStatus(GL_FRAMEBUFFER) != GL_FRAMEBUFFER_COMPLETE)
		{
			System.err.println("Shadow framebuffer creation has failed");
			new Exception().printStackTrace();
			System.exit(1);
		}
		
		if(bind)
		{
			lastWriteBind = frameBuffer;
			glViewport(0,0,width,height);
		}
		else
			glBindFramebuffer(GL_FRAMEBUFFER, lastWriteBind);
	}
	
	public void bind()
	{
		if(lastReadBind != id)
		{
			glBindTexture(GL_TEXTURE_2D, id);
			lastReadBind = id;
		}
	}
	
	public void bindAsRenderTarget()
	{
		if(lastWriteBind != frameBuffer)
		{
			glBindFramebuffer(GL_FRAMEBUFFER, frameBuffer);
			lastWriteBind = frameBuffer;
			glViewport(0,0,width,height);
		}
	}
	
	public static void unbind()
	{
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	public static void unbindRenderTarget()
	{
		glBindFramebuffer(GL_FRAMEBUFFER, 0);
		glViewport(0,0,Window.getWidth(),Window.getHeight());
	}
	
	public static void setActiveUnit(int unit)
	{
		glActiveTexture(unit);
		
		if(unit == 0)
			glActiveTexture(GL_TEXTURE0);
		else if(unit == 1)
			glActiveTexture(GL_TEXTURE1);
		else if(unit == 2)
			glActiveTexture(GL_TEXTURE2);
		else if(unit == 3)
			glActiveTexture(GL_TEXTURE3);
		else if(unit == 4)
			glActiveTexture(GL_TEXTURE4);
		else if(unit == 5)
			glActiveTexture(GL_TEXTURE5);
		else if(unit == 6)
			glActiveTexture(GL_TEXTURE6);
		else if(unit == 7)
			glActiveTexture(GL_TEXTURE7);
		else if(unit == 8)
			glActiveTexture(GL_TEXTURE8);
		else if(unit == 9)
			glActiveTexture(GL_TEXTURE9);
		else if(unit == 10)
			glActiveTexture(GL_TEXTURE10);
		else if(unit == 11)
			glActiveTexture(GL_TEXTURE11);
		else if(unit == 12)
			glActiveTexture(GL_TEXTURE12);
		else if(unit == 13)
			glActiveTexture(GL_TEXTURE13);
		else if(unit == 14)
			glActiveTexture(GL_TEXTURE14);
		else if(unit == 15)
			glActiveTexture(GL_TEXTURE15);
		else if(unit == 16)
			glActiveTexture(GL_TEXTURE16);
		else if(unit == 17)
			glActiveTexture(GL_TEXTURE17);
		else if(unit == 18)
			glActiveTexture(GL_TEXTURE18);
		else if(unit == 19)
			glActiveTexture(GL_TEXTURE19);
		else if(unit == 20)
			glActiveTexture(GL_TEXTURE20);
		else if(unit == 21)
			glActiveTexture(GL_TEXTURE21);
		else if(unit == 22)
			glActiveTexture(GL_TEXTURE22);
		else if(unit == 23)
			glActiveTexture(GL_TEXTURE23);
		else if(unit == 24)
			glActiveTexture(GL_TEXTURE24);
		else if(unit == 25)
			glActiveTexture(GL_TEXTURE25);
		else if(unit == 26)
			glActiveTexture(GL_TEXTURE26);
		else if(unit == 27)
			glActiveTexture(GL_TEXTURE27);
		else if(unit == 28)
			glActiveTexture(GL_TEXTURE28);
		else if(unit == 29)
			glActiveTexture(GL_TEXTURE29);
		else if(unit == 30)
			glActiveTexture(GL_TEXTURE30);
		else if(unit == 31)
			glActiveTexture(GL_TEXTURE31);
		else
		{
			System.err.println("Texture Unit " + unit + " is not valid: Must be between 0 and 32");
			new Exception().printStackTrace();
			System.exit(1);
		}
	}
	
	public int getID()
	{
		return id;
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}
	
	
}
