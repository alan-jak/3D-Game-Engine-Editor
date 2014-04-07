
public class GameObjectStringParser {

	public static void main(String[] args)
	{
		String line = "<GO> x=100 y=20 z=09";
		
		int t = 0;
		char c = ' ';
		String number = "";
		
		while(c != "x".charAt(0) && t < line.length())
		{
		    c = line.charAt(t);        
		    t++;
		}
		t++;
		if(c != "x".charAt(0) && t < line.length())
			System.out.println("GameObject definition must contain an Y Position!");
		else
		{
			System.out.println(t);
			while(!Character.isWhitespace(c) && t < line.length())
			{
				c = line.charAt(t);
				number = number + line.charAt(t);
				t++;
			}
		}
		//Deal with number produced here
		System.out.println(number);
		
		t = 0;
		c = ' ';
		number = "";
		
		while(c != "y".charAt(0) && t < line.length())
		{
		    c = line.charAt(t);        
		    t++;
		}
		t++;
		if(c != "y".charAt(0) && t < line.length())
			System.out.println("GameObject definition must contain an Y Position!");
		else
		{
			while(!Character.isWhitespace(c) && t < line.length())
			{
				c = line.charAt(t);
				number = number + line.charAt(t);
				t++;
			}
		}
		//Deal with produced number here
		System.out.println(number);
		
		t = 0;
		c = " ".charAt(0);
		number = "";
		
		while(c != "z".charAt(0) && t < line.length())
		{
		    c = line.charAt(t);        
		    t++;
		}
		t++;
		if(c != "z".charAt(0))
			System.out.println("GameObject definition must contain an Z Position!");
		else
		{
			while(!Character.isWhitespace(c) && t < line.length())
			{
				c = line.charAt(t);
				number = number + line.charAt(t);
				t++;
			}
		}
		//Deal with produced number here
		System.out.println(number);
		
		
		parseGameComponent();
		
	}

	
	public static void parseGameComponent()
	{
		String line = "<GC> type=camera parent=0 fov=80 near=0.01 far=1000";
	      int intIndex = line.indexOf("type=camera");
	      if(intIndex != -1)
	      {
	    	  line = line + "  ";
	         System.out.println("Camera Type GameComponent Detected, parsing");
	         parseCamera(line);
	      }
	      /*
	      String strOrig = "Hello readers";
	      int intIndex = strOrig.indexOf("Hello");
	      if(intIndex == - 1){
	         System.out.println("Hello not found");
	      }else{
	         System.out.println("Found Hello at index "
	         + intIndex);
	      }
	      */
	      
	}
	
	public static void parseCamera(String line)
	{
		int intIndex;
	         intIndex = line.indexOf("fov=");
	         
	         if(intIndex == -1)
	        	 System.out.println("Camera type GameComponents require a fov setting!");
	         else
	         {
	        	 int t = intIndex+4;
	        	 char c = "a".charAt(0);
	        	 String number = "";
	        	 
	        	 while(c != " ".charAt(0))
	 			{
	 				c = line.charAt(t);
	 				number = number + line.charAt(t);
	 				if(t<line.length()-1)
	 					t++;
	 			}
	 			number.replace(" ", "");
	        	 
	        	 System.out.println("Camera FOV: " + number);
	        	 
	        	 intIndex = line.indexOf("near=");
		         
		         if(intIndex == -1)
		        	 System.out.println("Camera type GameComponents require a near setting!");
		         else
		         {
		        	 t = intIndex+5;
		        	 number = "";
		        	 c = "a".charAt(0);
		        	 while(c != " ".charAt(0))
			 			{
			 				c = line.charAt(t);
			 				number = number + line.charAt(t);
			 				if(t<line.length()-1)
			 					t++;
			 			}
			 			number.replace(" ", "");
			        	 
			        	 System.out.println("Camera NEAR: " + number);
		        	 
		        	 intIndex = line.indexOf("far=");
			         
		        	 if(intIndex == -1)
			        	 System.out.println("Camera type GameComponents require a far setting!");
		        	 else
		        	 {
		        		 t = intIndex+4;
			        	 number = "";
			        	 c = "a".charAt(0);
			        	 while(c != " ".charAt(0))
				 			{
				 				c = line.charAt(t);
				 				number = number + line.charAt(t);
				 				if(t<line.length())
				 					t++;
				 			}
				 			number.replace(" ", "");
			        	 
				         System.out.println("Camera FAR: " + number);
		        	 }
		         }
	         }
	}
	
	public static void parseMeshRenderer(String line)
	{
		int intIndex;
        intIndex = line.indexOf("path");
        
        if(intIndex == -1)
       	 System.out.println("MeshRenderers require a path!");
        else
        {
       	 int t = intIndex+4;
       	 char c = "a".charAt(0);
       	 String number = "";
       	 
       	 while(c != "\"".charAt(0))
			{
				c = line.charAt(t);
				number = number + line.charAt(t);
				if(t<line.length()-1)
					t++;
			}
			number.replace(" ", "");
       	 
       	 System.out.println("Camera FOV: " + number);
       	 
       	 intIndex = line.indexOf("near=");
	         
	         if(intIndex == -1)
	        	 System.out.println("Camera type GameComponents require a near setting!");
	         else
	         {
	        	 t = intIndex+5;
	        	 number = "";
	        	 c = "a".charAt(0);
	        	 while(c != " ".charAt(0))
		 			{
		 				c = line.charAt(t);
		 				number = number + line.charAt(t);
		 				if(t<line.length()-1)
		 					t++;
		 			}
		 			number.replace(" ", "");
		        	 
		        	 System.out.println("Camera NEAR: " + number);
	        	 
	        	 intIndex = line.indexOf("far=");
		         
	        	 if(intIndex == -1)
		        	 System.out.println("Camera type GameComponents require a far setting!");
	        	 else
	        	 {
	        		 t = intIndex+4;
		        	 number = "";
		        	 c = "a".charAt(0);
		        	 while(c != " ".charAt(0))
			 			{
			 				c = line.charAt(t);
			 				number = number + line.charAt(t);
			 				if(t<line.length())
			 					t++;
			 			}
			 			number.replace(" ", "");
		        	 
			         System.out.println("Camera FAR: " + number);
	        	 }
	         }
        }
	}
}
