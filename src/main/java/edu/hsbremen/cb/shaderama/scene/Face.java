package edu.hsbremen.cb.shaderama.scene;

public class Face {
	
	private int[] vertexIndices;
	private int[] normalIndices;
	private int[] textureCoords;
	
	public Face(int[] vertexIndices, int[] normalIndices, int[] textureCoords) {
		this.vertexIndices = vertexIndices;
		this.normalIndices = normalIndices;
		this.textureCoords = textureCoords;
	}

	public int[] getVertexIndices() {
		return vertexIndices;
	}

	public void setVertexIndices(int[] vertexIndices) {
		this.vertexIndices = vertexIndices;
	}

	public int[] getNormalIndices() {
		return normalIndices;
	}

	public void setNormalIndices(int[] normalIndices) {
		this.normalIndices = normalIndices;
	}

	public int[] getTextureCoords() {
		return textureCoords;
	}

	public void setTextureCoords(int[] textureCoords) {
		this.textureCoords = textureCoords;
	}

	
	
}
