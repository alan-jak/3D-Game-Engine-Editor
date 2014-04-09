package com.editor.wysiwygEngine.base.rendering;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL32.GL_DEPTH_CLAMP;

import java.util.ArrayList;
import java.util.HashMap;

import com.editor.wysiwygEngine.base.components.BaseLight;
import com.editor.wysiwygEngine.base.components.Camera;
import com.editor.wysiwygEngine.base.components.PointLight;
import com.editor.wysiwygEngine.base.components.SpotLight;
import com.editor.wysiwygEngine.base.core.GameObject;
import com.editor.wysiwygEngine.base.core.Quaternion;
import com.editor.wysiwygEngine.base.core.Transform;
import com.editor.wysiwygEngine.base.core.Vector2f;
import com.editor.wysiwygEngine.base.core.Vector3f;
import com.editor.wysiwygEngine.base.exceptions.UnsupportedUniformTypeException;
import com.editor.wysiwygEngine.base.rendering.resourceManagement.MappedValues;

public class RenderingEngine extends MappedValues
{	
	//Light flares testing
	private Texture flare = new Texture("flare.png");
	private Material flareMat = new Material();
	private Mesh flareMesh;
	
	private HashMap<String, Integer> samplerMap;
	private ArrayList<BaseLight> lights;
	private BaseLight activeLight;

	private Shader forwardAmbient;
	private Camera mainCamera;
	
	public RenderingEngine()
	{	
		super();
		
		lights = new ArrayList<BaseLight>();
		samplerMap = new HashMap<String, Integer>();
		samplerMap.put("diffuse", 0);
		
		addVector3f("ambient", new Vector3f(0.1f, 0.1f, 0.1f));
		
		forwardAmbient = new Shader("forward-ambient");
		
		glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		
		glFrontFace(GL_CW);
		glCullFace(GL_BACK);
		glEnable(GL_CULL_FACE);
		glEnable(GL_DEPTH_TEST);
		
		glEnable(GL_DEPTH_CLAMP);
		
		glEnable(GL_TEXTURE_2D);
		
		flareMat.addTexture("diffuse", flare);
		flareMat.addFloat("specularIntensity", 0);
		flareMat.addFloat("specularPower", 0);
		
		Vertex[] vertices = new Vertex[] {new Vertex(new Vector3f(0, 1, 0), new Vector2f(0, 0)), new Vertex(new Vector3f(1, 1, 0), new Vector2f(1, 0)), new Vertex(new Vector3f(1, 0, 0), new Vector2f(1, 1)), new Vertex(new Vector3f(0, 0, 0), new Vector2f(0, 1))};
		
		int[] indices = new int[] {0, 3, 2, 2, 1, 0};
		
		flareMesh = new Mesh(vertices, indices);
	}
	
	public void updateUniformStruct(Transform transform, Material material, Shader shader, String uniformName, String uniformType)
	{
		throw new UnsupportedUniformTypeException(uniformType + " is not a supported uniform type for RenderingEngine");
	}
	
	public void render(GameObject object)
	{
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		forwardAmbient.bind();
		
		object.renderAll(forwardAmbient, this);
		
		glEnable(GL_BLEND);
		glBlendFunc(GL_ONE, GL_ONE);
		glDepthMask(false);
		glDepthFunc(GL_EQUAL);
		
		for(BaseLight light : lights)
		{
			
			activeLight = light;
			
			object.renderAll(light.getShader(), this);
		}
		
		glDepthFunc(GL_LESS);
		glDepthMask(true);
		
		Vector3f oldAmbient = getVector3f("ambient");
		
		forwardAmbient.bind();
		
		for(BaseLight light : lights)
		{
			if(light instanceof PointLight && !(light instanceof SpotLight))
			{
				Quaternion currentRot = light.getTransform().getRotation();
				
				light.getTransform().lookAt(mainCamera.getTransform().getPosition(), new Vector3f(0, 1, 0));
				
				addVector3f("ambient", light.getColor());
				
				forwardAmbient.updateUniforms(light.getTransform(), flareMat, this);
				flareMesh.draw();
				
				light.getTransform().setRotation(currentRot);
			}
		}
		
		addVector3f("ambient", oldAmbient);
		
		glDisable(GL_BLEND);
	}
	
	public static String getOpenGLVersion()
	{
		return glGetString(GL_VERSION);
	}
	
	public void addLight(BaseLight light)
	{
		lights.add(light);
	}
	
	public void addCamera(Camera camera)
	{
		mainCamera = camera;
	}
	
	public int getSamplerSlot(String samplerName)
	{
		return samplerMap.get(samplerName);
	}
	
	public BaseLight getActiveLight()
	{
		return activeLight;
	}

	public Camera getMainCamera()
	{
		return mainCamera;
	}

	public void setMainCamera(Camera mainCamera)
	{
		this.mainCamera = mainCamera;
	}
}
