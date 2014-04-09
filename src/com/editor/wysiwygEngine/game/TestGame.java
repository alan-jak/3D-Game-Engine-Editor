package com.editor.wysiwygEngine.game;

import com.editor.wysiwygEngine.base.components.Camera;
import com.editor.wysiwygEngine.base.components.DirectionalLight;
import com.editor.wysiwygEngine.base.components.FreeLook;
import com.editor.wysiwygEngine.base.components.FreeMove;
import com.editor.wysiwygEngine.base.components.MeshRenderer;
import com.editor.wysiwygEngine.base.components.PointLight;
import com.editor.wysiwygEngine.base.components.SpotLight;
import com.editor.wysiwygEngine.base.core.Game;
import com.editor.wysiwygEngine.base.core.GameObject;
import com.editor.wysiwygEngine.base.core.Quaternion;
import com.editor.wysiwygEngine.base.core.Vector2f;
import com.editor.wysiwygEngine.base.core.Vector3f;
import com.editor.wysiwygEngine.base.rendering.Attenuation;
import com.editor.wysiwygEngine.base.rendering.Material;
import com.editor.wysiwygEngine.base.rendering.Mesh;
import com.editor.wysiwygEngine.base.rendering.Texture;
import com.editor.wysiwygEngine.base.rendering.Vertex;
import com.editor.wysiwygEngine.base.rendering.Window;

public class TestGame extends Game
{

	public void init()
	{
        
		Mesh tempMesh = new Mesh("monkey3.obj");
		
        Vertex[] vertices = new Vertex[] {
        									new Vertex(new Vector3f(0, 0, 0), new Vector2f(0, 0)),
        									new Vertex(new Vector3f(1, 0, 0), new Vector2f(1, 0)),
        									new Vertex(new Vector3f(0, 0, 1), new Vector2f(1, 0)),
        									new Vertex(new Vector3f(1, 0, 1), new Vector2f(0, 0)),
        									new Vertex(new Vector3f(0.5f, 1, 0.5f), new Vector2f(0.5f, 1))
        								 };
        int indices[] = {0, 4, 1,
        				 1, 4, 3,
        				 3, 4, 2,
        				 2, 4, 0
        				};
        
        Mesh mesh = new Mesh(vertices, indices, true);
        Material brickMaterial = new Material();//(new Texture("brick.png"), new Vector3f(1,1,1), 0, 0);
        brickMaterial.addTexture("diffuse", new Texture("brick.png"));
        brickMaterial.addFloat("specularIntensity", 1);
        brickMaterial.addFloat("specularPower", 8);
        
        MeshRenderer pyramidRenderer = new MeshRenderer(mesh, brickMaterial);
        
        GameObject pyramidObject = new GameObject("Big Pyramid");
        pyramidObject.addComponent(pyramidRenderer);
        pyramidObject.getTransform().getPosition().set(0, -1, 5);
        pyramidObject.getTransform().getScale().set(5, 5, 5);
        
        GameObject pyramidMini1 = new GameObject("Little Pyramid 1").addComponent(new MeshRenderer(tempMesh, brickMaterial));
        GameObject pyramidMini2 = new GameObject("Little Pyramid 2").addComponent(new MeshRenderer(tempMesh, brickMaterial));
        
        pyramidMini1.getTransform().getPosition().set(-3, 0, 4);
        pyramidMini1.getTransform().setRotation(new Quaternion(new Vector3f(0,1,0), 0.4f));
        
        pyramidMini2.getTransform().getPosition().set(0, 0, 5);
        
        pyramidMini1.addComponent(new LookAtComponent());
        
        pyramidMini1.addChild(pyramidMini2);

        addObject(pyramidMini1);

        addObject(pyramidObject);
        
		float fieldDepth = 10.0f;
      float fieldWidth = 10.0f;
      
      Vertex[] vertices2 = new Vertex[] {         new Vertex( new Vector3f(-fieldWidth, 0.0f, -fieldDepth), new Vector2f(0.0f, 0.0f)),
                                                 new Vertex( new Vector3f(-fieldWidth, 0.0f, fieldDepth * 3), new Vector2f(0.0f, 1.0f)),
                                                 new Vertex( new Vector3f(fieldWidth * 3, 0.0f, -fieldDepth), new Vector2f(1.0f, 0.0f)),
                                                 new Vertex( new Vector3f(fieldWidth * 3, 0.0f, fieldDepth * 3), new Vector2f(1.0f, 1.0f))};
      
      	int indices2[] = { 0, 1, 2,
                        2, 1, 3};
        
        mesh = new Mesh(vertices2, indices2, true);
        Material sandMaterial = new Material();//(new Texture("test.png"), new Vector3f(1,1,1), 0, 0);
        sandMaterial.addTexture("diffuse", new Texture("test.png"));
        sandMaterial.addFloat("specularIntensity", 1);
        sandMaterial.addFloat("specularPower", 8);

        MeshRenderer planeRenderer = new MeshRenderer(mesh, sandMaterial);
        
        GameObject planeObject = new GameObject("Plane");
        planeObject.addComponent(planeRenderer);
        planeObject.getTransform().getPosition().set(0, -1, 5);

        addObject(planeObject);
        
        GameObject directionalLightObject = new GameObject("Dir Light");
        directionalLightObject.addComponent(new DirectionalLight(new Vector3f(1.0f, 1.0f, 1.0f), 0.5f));
        directionalLightObject.getTransform().setRotation(new Quaternion(new Vector3f(1, 1, 0), (float)Math.toRadians(-45)));
        
        GameObject pointLightObject = new GameObject("Point Light");
        PointLight pointLight = new PointLight(new Vector3f(1,0,0), 0.9f, new Attenuation(0, 1, 0.1f));
        directionalLightObject.addComponent(pointLight);
        
        GameObject spotLightObject = new GameObject("Spot Light");
        SpotLight spotLight = new SpotLight(new Vector3f(0,1,0), 0.9f, new Attenuation(0, 0, 0.1f), 0.7f);
        spotLightObject.addComponent(spotLight);
        
        pointLight.getTransform().setPosition( new Vector3f(3, 0, 3));
        spotLight.getTransform().setPosition(new Vector3f(1f, 0, 1f));
        spotLightObject.getTransform().setRotation(new Quaternion(new Vector3f(0, 1, 0), (float)Math.toRadians(45)));

        addObject(directionalLightObject);
        addObject(pointLightObject);
        addObject(spotLightObject);
        addObject(new GameObject("Cam Object").addComponent(new Camera((float)Math.toRadians(80), (float)Window.getWidth()/(float)Window.getHeight(), 0.01f, 1000.0f)).addComponent(new FreeLook(1)).addComponent(new FreeMove(8)));
	}
	
}
