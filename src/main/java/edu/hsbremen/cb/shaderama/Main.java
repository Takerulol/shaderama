package edu.hsbremen.cb.shaderama;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.hsbremen.cb.shaderama.core.Core;
import edu.hsbremen.cb.shaderama.util.ObjLoader;
import edu.hsbremen.cb.shaderama.util.ResourceLoader;

public final class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("initial app!");
		Core core = new Core("lwjgl");
		core.start();

		//TODO: fill in models/shaders
	}

}
