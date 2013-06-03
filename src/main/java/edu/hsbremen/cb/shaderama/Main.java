package edu.hsbremen.cb.shaderama;

import edu.hsbremen.cb.shaderama.core.Core;

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
