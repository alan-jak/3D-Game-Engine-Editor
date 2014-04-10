Hello!
This is my project to create a Unity/UDK style editor for bennyQBD/thebennybox's 3D Game Engine tutorial series. This editor is written entirely in Java, and will require some changes to the original game engine for it to work as it uses custom file formats the engine doesn't handle.

-----THE FORMAT-----
The current file format is called a planet scene file or .planet file, made to fit with my own (Kiwuser's) version of the engine, the format is very simple and I will outline it below so it's easier to understand the compiler/parser

--Layout--
Each line should contain a tag, it does not matter where it is placed, as long as it's on the line. Valid tags are
\<GO\> - States this line should be treated as a GameObject, requires x,y and z position values on line.
\<GC\> - States this line is a component and requires type parameter

--GameComponent types--
The parser only supports the following types

type=camera - requires fov,near,far fields to parse.
----MeshRenderer is next to be added

Quick note - you may notice the camera test line contains a parent variable, this will be added later on, and will be used to choose what GameObject in the list the component will be added to.

