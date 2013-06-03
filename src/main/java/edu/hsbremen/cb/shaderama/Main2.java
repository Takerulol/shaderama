package edu.hsbremen.cb.shaderama;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Main2 {
public static void main(String[] args) {
	
	try {
		Display.setDisplayMode(new DisplayMode(800, 600));
		Display.create();
	} catch (LWJGLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	while(true);
}
}
