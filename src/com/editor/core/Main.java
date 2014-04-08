package com.editor.core;

public class Main
{
    public static boolean editor = false;

    public static void Main(String[] args)
    {

        // runs init method of StringPhaser
        StringPhaser.init();

        StringPhaser.parseGameObject();
    }

}
