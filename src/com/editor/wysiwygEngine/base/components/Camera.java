package com.editor.wysiwygEngine.base.components;

import com.editor.wysiwygEngine.base.core.CoreEngine;
import com.editor.wysiwygEngine.base.core.GameObject;
import com.editor.wysiwygEngine.base.core.Matrix4f;
import com.editor.wysiwygEngine.base.core.Quaternion;
import com.editor.wysiwygEngine.base.core.Vector3f;

public class Camera extends GameComponent
{
	private Matrix4f projection;

	public Camera(float fov, float aspect, float zNear, float zFar)
	{
		this.projection = new Matrix4f().initPerspective(fov, aspect, zNear, zFar);
	}
	
	public Camera(Matrix4f projection, Vector3f pos, Quaternion rotation, GameObject obj)
	{
		obj.addComponent(this);
		this.projection = projection;
		getTransform().setPosition(pos);
		getTransform().setRotation(rotation);
	}

	public Matrix4f getViewProjection()
	{
		Matrix4f cameraRotation = getTransform().getTransformedRot().conjugate().toRotationMatrix();
		Vector3f cameraPos = getTransform().getTransformedPos().mul(-1);
		
		Matrix4f cameraTranslation = new Matrix4f().initTranslation(cameraPos.getX(), cameraPos.getY(), cameraPos.getZ());

		return projection.mul(cameraRotation.mul(cameraTranslation));
	}
	
	public void setProjection(Matrix4f projection)
	{
		this.projection = projection;
	}
	
	@Override
	public void addToEngine(CoreEngine engine)
	{
		engine.getRenderingEngine().addCamera(this);
	}
	
	public GameObject getParent()
	{
		return parent;
	}
}