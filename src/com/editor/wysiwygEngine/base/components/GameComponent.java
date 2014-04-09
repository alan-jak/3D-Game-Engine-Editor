package com.editor.wysiwygEngine.base.components;

import com.editor.wysiwygEngine.base.core.CoreEngine;
import com.editor.wysiwygEngine.base.core.GameObject;
import com.editor.wysiwygEngine.base.core.Transform;
import com.editor.wysiwygEngine.base.rendering.RenderingEngine;
import com.editor.wysiwygEngine.base.rendering.Shader;

public abstract class GameComponent
{
	protected GameObject parent;
	
	public void input(float delta) {}
	public void update(float delta) {}
	public void render(Shader shader, RenderingEngine renderingEngine) {}
	
	public void setParent(GameObject object)
	{
		this.parent = object;
	}
	
	public Transform getTransform()
	{
		return parent.getTransform();
	}
	
	public void addToEngine(CoreEngine engine){}
	}
