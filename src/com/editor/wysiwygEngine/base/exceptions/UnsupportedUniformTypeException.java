package com.editor.wysiwygEngine.base.exceptions;

public class UnsupportedUniformTypeException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

    public UnsupportedUniformTypeException() {}

    public UnsupportedUniformTypeException(String message)
    {
       super(message);
       super.printStackTrace();
    }
}
