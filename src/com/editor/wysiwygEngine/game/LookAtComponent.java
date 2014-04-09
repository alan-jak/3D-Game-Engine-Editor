package com.editor.wysiwygEngine.game;

import com.editor.wysiwygEngine.base.components.GameComponent;
import com.editor.wysiwygEngine.base.core.*;
import com.editor.wysiwygEngine.base.rendering.RenderingEngine;
import com.editor.wysiwygEngine.base.rendering.Shader;


public class LookAtComponent extends GameComponent
{
	RenderingEngine renderingEngine;
	
	@Override
	public void update(float delta)
	{
		if(renderingEngine != null)
		{
			Quaternion newRot = getTransform().getLookAtRotation(renderingEngine.getMainCamera().getTransform().getTransformedPos(), new Vector3f(0, 1, 0));
			
			getTransform().setRotation(getTransform().getRotation().nlerp(newRot, delta * 5.0f, true));
		}
	}
	
	@Override
	public void render(Shader shader, RenderingEngine renderingEngine)
	{
		this.renderingEngine = renderingEngine;
	}
}
