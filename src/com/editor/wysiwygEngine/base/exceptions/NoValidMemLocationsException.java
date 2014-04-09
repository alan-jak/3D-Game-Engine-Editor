package com.editor.wysiwygEngine.base.exceptions;

public class NoValidMemLocationsException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	public NoValidMemLocationsException() {}

    public NoValidMemLocationsException(String message)
    {
       super(message);
       super.printStackTrace();
       System.exit(1);
    }
}
