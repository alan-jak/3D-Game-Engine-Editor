package com.editor.wysiwygEngine.base.core;

import com.editor.wysiwygEngine.base.rendering.RenderingEngine;

public abstract class Game
{
	private GameObject root = new GameObject("ROOT_OBJECT");
	
	public void init()
	{}
	
	public void input(float delta)
	{
		getRootObject().inputAll(delta);
	}
	
	public void update(float delta)
	{
		getRootObject().updateAll(delta);
	}

    public void Render(RenderingEngine renderingEngine)
    {
        renderingEngine.render(getRootObject());
    }

    public void addObject(GameObject object)
    {
        getRootObject().addChild(object);
    }
	
	public GameObject getRootObject()
	{
		return root;
	}
	
	public void setEngine(CoreEngine engine){getRootObject().setEngine(engine);}
}
