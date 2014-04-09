package com.editor.wysiwygEngine.base.components;

import com.editor.wysiwygEngine.base.core.CoreEngine;
import com.editor.wysiwygEngine.base.core.Vector3f;
import com.editor.wysiwygEngine.base.rendering.Shader;

public class DirectionalLight extends BaseLight
{
	
	public DirectionalLight(Vector3f color, float intensity)
	{
		super(color, intensity);
		
		setShader(new Shader("forward-directional"));
	}

	public Vector3f getDirection()
	{
		return getTransform().getTransformedRot().getForward();
	}
	
	@Override
	public void addToEngine(CoreEngine engine)
	{
		engine.getRenderingEngine().addLight(this);
	}
}
