package com.editor.wysiwygEngine.planetParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.editor.wysiwygEngine.base.components.Camera;
import com.editor.wysiwygEngine.base.components.FreeLook;
import com.editor.wysiwygEngine.base.components.FreeMove;
import com.editor.wysiwygEngine.base.components.MeshRenderer;
import com.editor.wysiwygEngine.base.core.GameObject;
import com.editor.wysiwygEngine.base.core.Vector3f;
import com.editor.wysiwygEngine.base.rendering.Material;
import com.editor.wysiwygEngine.base.rendering.Mesh;
import com.editor.wysiwygEngine.base.rendering.Texture;
import com.editor.wysiwygEngine.base.rendering.Window;

public class GameCreator
{
    // variables
    private String number;
    private String path;

    private int    t;
    private char   c;

    private ArrayList<GameObject> Objects = new ArrayList<GameObject>();
    
    public GameCreator(String gamePath)
    {
    	path = gamePath;
    	t = 0;
    	c = ' ';
    	
    }
    
    public ArrayList<GameObject> createGame()
    {
    	BufferedReader br = null;
    	 
		try {
 
			String sCurrentLine;
 
			br = new BufferedReader(new FileReader(path));
			
			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
				if(sCurrentLine.indexOf("<GO>") != -1)
					parseGameObject(sCurrentLine + " ");
				else if(sCurrentLine.indexOf("<GC>") != -1)
					parseGameComponent(sCurrentLine + " ");
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
    	
    	return Objects;
    }

    //Don't know why this is called update, the parser should only run once, if it breaks all of your code, sorry!
    //I renamed it to a more suitable name.
    private void parseGameObject(String line)
    {

    	float x = 0, y = 0, z = 0;
    	
        //Search for a variable called 'x' in the string recieved
        while (c != 'x' && t < line.length())
        {
            c = line.charAt(t);
            t++;
        }
        t++;
        //Check if there was a 'x' found on the line
        if (c != 'x' && t < line.length())
            System.out.println("GameObject definition must contain an X Position!");
        else
        {
            //Read until whitespace is reached, and append to 'number' string
            while (!Character.isWhitespace(c) && t < line.length())
            {
                c = line.charAt(t);
                number = number + line.charAt(t);
                t++;
            }
        }
        // Deal with number produced here
        number = number.replace("null", "");
        x = Float.parseFloat(number);
        System.out.println("X: " + number);

        //reset variables
        t = 0;
        c = ' ';
        number = "";
        
        //Search for 'y' value
        while (c != "y".charAt(0) && t < line.length())
        {
            c = line.charAt(t);
            t++;
        }
        t++;
        //Check if 'y' was found
        if (c != "y".charAt(0) && t < line.length())
            System.out.println("GameObject definition must contain an Y Position!");
        else
        {
            //If found, read unitl whitespace
            while (!Character.isWhitespace(c) && t < line.length())
            {
                c = line.charAt(t);
                number = number + line.charAt(t);
                t++;
            }
        }
        // Deal with produced number here
        y = Float.parseFloat(number);
        System.out.println("Y: " + number);

        //Reset variables
        t = 0;
        c = " ".charAt(0);
        number = "";
        
        //Search for 'z' value
        while (c != "z".charAt(0) && t < line.length())
        {
            c = line.charAt(t);
            t++;
        }
        t++;
        
        //Check if a 'z' value was discovered
        if (c != "z".charAt(0))
            System.out.println("GameObject definition must contain an Z Position!");
        else
        {
            //Read in 'z' until whitespace is found
            while (!Character.isWhitespace(c) && t < line.length())
            {
                c = line.charAt(t);
                number = number + line.charAt(t);
                t++;
            }
        }
        // Deal with produced number here
        z = Float.parseFloat(number);
        System.out.println("Z: " + number);
        
        GameObject obj = new GameObject("Object " + Objects.size() + 1);
        obj.getTransform().setPosition(new Vector3f(x, y, z));
        Objects.add(obj);

    }

    private void parseGameComponent(String line)
    {
        //Check if this component is of type camera
        if (line.indexOf("type=camera") != -1)
        {
            //Self explanatory
            line = line + "  ";
            System.out.println("Camera Type GameComponent Detected, parsing");
            parseCamera(line);
        }
        else if(line.indexOf("type=meshRenderer") != -1)
        {
        	//Self explanatory
            line = line + "  ";
            System.out.println("MeshRenderer Type GameComponent Detected, parsing");
            parseMeshRenderer(line);
        }

    }

    private void parseCamera(String line)
    {
        int intIndex;
        int parent = 0;
        float fov = 0, near = 0, far = 0;
        //Searches for the FOV field
        
        //To save many comments, I will explain how this works here
        //This is basically identical to the gameObject parser above
        //but instead of searching for values like 'x', 'y', and 'z', it searches for camera specific ones
        //such as the FOV, NEAR and FAR components needed to initialize the camera
        
        intIndex = line.indexOf("fov=");

        if (intIndex == -1)
            System.out.println("Camera type GameComponents require a fov setting!");
        else
        {
            int t = intIndex + 4;
            char c = "a".charAt(0);
            String number = "";

            while (c != " ".charAt(0))
            {
                c = line.charAt(t);
                number = number + line.charAt(t);
                if (t < line.length() - 1) t++;
            }
            number.replace(" ", "");

            fov = Float.parseFloat(number);
            System.out.println("Camera FOV: " + number);

            intIndex = line.indexOf("near=");

            if (intIndex == -1)
                System.out.println("Camera type GameComponents require a near setting!");
            else
            {
                t = intIndex + 5;
                number = "";
                c = "a".charAt(0);
                while (c != " ".charAt(0))
                {
                    c = line.charAt(t);
                    number = number + line.charAt(t);
                    if (t < line.length() - 1) t++;
                }
                number.replace(" ", "");

                near = Float.parseFloat(number);
                System.out.println("Camera NEAR: " + number);

                intIndex = line.indexOf("far=");

                if (intIndex == -1)
                    System.out.println("Camera type GameComponents require a far setting!");
                else
                {
                    t = intIndex + 4;
                    number = "";
                    c = "a".charAt(0);
                    while (c != " ".charAt(0))
                    {
                        c = line.charAt(t);
                        number = number + line.charAt(t);
                        if (t < line.length()) t++;
                    }
                    number.replace(" ", "");

                    far = Float.parseFloat(number);
                    System.out.println("Camera FAR: " + number);
                }
            }
        }
        
        intIndex = line.indexOf("parent=");
        
        if (intIndex == -1)
            System.out.println("Camera type GameComponents require a parent setting!");
        else
        {
            t = intIndex + 7;
            number = "";
            c = "a".charAt(0);
            while (c != ' ')
            {
                c = line.charAt(t);
                number = number + line.charAt(t);
                if (t < line.length()) t++;
            }
            number = number.replaceAll("\\s+","");

            parent = Integer.parseInt(number);
            System.out.println("Camera Parent: " + number);
        }
        
        Camera cam = new Camera((float)Math.toRadians(fov), (Window.getWidth()/Window.getHeight()), near, far);
        Objects.get(parent).addComponent(cam);
        Objects.get(parent).addComponent(new FreeLook(1.0f));
        Objects.get(parent).addComponent(new FreeMove(8.0f));
    }

    private void parseMeshRenderer(String line)
    {
        int intIndex;
        intIndex = line.indexOf("mPath=\"");
        String tpath = "", mpath = "";

        if (intIndex == -1)
            System.out.println("MeshRenderers require a model path!");
        else
        {
            int t = intIndex + 7;
            char c = ' ';
            String number = "";

            while (c != "\"".charAt(0))
            {
                c = line.charAt(t);
                number = number + line.charAt(t);
                if (t < line.length() - 1) t++;
            }
            number = number.replace(" ", "");
            number = number.replace("\"", "");

            mpath = number.replace("./res/models/", "");
            mpath = mpath.replace(".\\res\\models\\", "");
            System.out.println("Model Path: " + number);

            intIndex = line.indexOf("tPath=\"");

            if (intIndex == -1)
                System.out.println("MeshRenderers require a texture path!");
            else
            {
            	t = intIndex + 7;
                c = ' ';
                number = "";

                while (c != "\"".charAt(0))
                {
                    c = line.charAt(t);
                    number = number + line.charAt(t);
                    if (t < line.length() - 1) t++;
                }
                number = number.replace(" ", "");
                number = number.replace("\"", "");

                tpath = number.replace("./res/textures/", "");
                System.out.println("Texture Path: " + number);
            }
        }
        
        intIndex = line.indexOf("parent=");
        int parent = 0;
        
        if (intIndex == -1)
            System.out.println("Camera type GameComponents require a parent setting!");
        else
        {
            t = intIndex + 7;
            number = "";
            c = "a".charAt(0);
            while (c != ' ')
            {
                c = line.charAt(t);
                number = number + line.charAt(t);
                if (t < line.length()) t++;
            }
            number = number.replaceAll("\\s+","");

            parent = Integer.parseInt(number);
            System.out.println("MeshRenderer Parent: " + number);
        }
        
        Material mat = new Material();
        mat.addTexture("diffuse", new Texture(tpath));
        MeshRenderer mr = new MeshRenderer(new Mesh(mpath), mat);
        
        Objects.get(parent).addComponent(mr);
        
    }
}
