package com.editor.core;

public class StringPhaser
{
    // variables
    private static String line;
    private static String number;

    private static int    t;
    private static char   c;

    public static void init()
    {
        // initialize variables
        line = "<GO> x=100 y=20 z=09";
        number = "";

        t = 0;
        c = ' ';

        Main.editor = true;

    }

    //Don't know why this is called update, the parser should only run once, if it breaks all of your code, sorry!
    //I renamed it to a more suitable name.
    public static void parseGameObject()
    {

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
            System.out.println(t);
            //Read until whitespace is reached, and append to 'number' string
            while (!Character.isWhitespace(c) && t < line.length())
            {
                c = line.charAt(t);
                number = number + line.charAt(t);
                t++;
            }
        }
        // Deal with number produced here
        System.out.println(number);

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
        System.out.println(number);

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
        System.out.println(number);
        
        //This runs the test function parseGameComponent, eventually the parser will first detect if the line is a GameObject or GameComponent
        parseGameComponent();

    }

    public static void parseGameComponent()
    {
        String line = "<GC> type=camera parent=0 fov=80 near=0.01 far=1000";
        //Check if this component is of type camera
        int intIndex = line.indexOf("type=camera");
        if (intIndex != -1)
        {
            //Self explanatory
            line = line + "  ";
            System.out.println("Camera Type GameComponent Detected, parsing");
            parseCamera(line);
        }

    }

    public static void parseCamera(String line)
    {
        int intIndex;
        //Searches for the FOV field
        
        //To save many comments, I will explain how this works here
        //This is basically identical to the gameObject parser above
        //but instead of searching for values like 'x', 'y', and 'z', it searches for camera specific ones
        //such as the FOV, NEAR and FAR components needev to initialize the camera
        
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

                    System.out.println("Camera FAR: " + number);
                }
            }
        }
    }

    //Ignore this, I left it after starting it a long, long time ago, and decided to wait for the
    //resource management segment to finish
    public static void parseMeshRenderer(String line)
    {
        int intIndex;
        intIndex = line.indexOf("path");

        if (intIndex == -1)
            System.out.println("MeshRenderers require a path!");
        else
        {
            int t = intIndex + 4;
            char c = "a".charAt(0);
            String number = "";

            while (c != "\"".charAt(0))
            {
                c = line.charAt(t);
                number = number + line.charAt(t);
                if (t < line.length() - 1) t++;
            }
            number.replace(" ", "");

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

                    System.out.println("Camera FAR: " + number);
                }
            }
        }
    }
}
