package edu.hsbremen.cb.shaderama.scene;

import java.util.ArrayList;

import edu.hsbremen.cb.shaderama.renderer.Renderer;

public class Node extends ArrayList<Node> {
	
	private static final long serialVersionUID = 1485034502091708049L;

	public void render(Renderer renderer) {
		for(Node n : this) {
			n.render(renderer);
		}
	};
}