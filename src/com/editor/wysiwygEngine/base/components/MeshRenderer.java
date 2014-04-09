package com.editor.wysiwygEngine.base.components;

import com.editor.wysiwygEngine.base.rendering.Material;
import com.editor.wysiwygEngine.base.rendering.Mesh;
import com.editor.wysiwygEngine.base.rendering.RenderingEngine;
import com.editor.wysiwygEngine.base.rendering.Shader;

public class MeshRenderer extends GameComponent
{
	private Mesh mesh;
	private Material material;
	
	public MeshRenderer(Mesh mesh, Material material)
	{
		this.mesh = mesh;
		this.material = material;
	}
	
	@Override
	public void render(Shader shader, RenderingEngine renderingEngine)
	{
		shader.bind();
		shader.updateUniforms(getTransform(), material, renderingEngine);
		mesh.draw();
	}
}
