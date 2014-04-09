package com.editor.wysiwygEngine.base.rendering.resourceManagement;

import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL20.glCreateProgram;

import java.util.ArrayList;
import java.util.HashMap;

import com.editor.wysiwygEngine.base.exceptions.NoValidMemLocationsException;

public class ShaderResource
{
	private int program;
    private int refCount;
	private HashMap<String, Integer> uniforms;
	private ArrayList<String> uniformNames;
	private ArrayList<String> uniformTypes;

	public ShaderResource()
    {
		this.program = glCreateProgram();
    	this.refCount = 1;
    	
    	if(program == 0)
		{
			throw new NoValidMemLocationsException("Unrecoverable Error Creating Shader");
		}
    	
    	uniforms = new HashMap<String, Integer>();
		uniformNames = new ArrayList<String>();
		uniformTypes = new ArrayList<String>();
    }
    
    @Override
    protected void finalize()
    {
    	glDeleteBuffers(program);
    }
    
    public void addReference()
    {
    	refCount++;
    }
    
    public boolean removeReference()
    {
    	refCount--;
    	return refCount == 0;
    }

	public int getProgram()
	{
		return program;
	}
	
	public HashMap<String, Integer> getUniforms() {
		return uniforms;
	}

	public ArrayList<String> getUniformNames() {
		return uniformNames;
	}

	public ArrayList<String> getUniformTypes() {
		return uniformTypes;
	}
}
