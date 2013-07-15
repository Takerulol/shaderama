package edu.hsbremen.cb.shaderama.shader;


public class Shader {
	private String vertexShaderSource;
	private String fragmentShaderSource;
	
	private int vertexShaderProgram = -1;
	private int fragmentShaderProgram = -1;
	private int shaderProgram = -1;
	
	public String getVertexShaderSource() {
		return vertexShaderSource;
	}
	public void setVertexShaderSource(String vertexShaderSource) {
		this.vertexShaderSource = vertexShaderSource;
	}
	public String getFragmentShaderSource() {
		return fragmentShaderSource;
	}
	public void setFragmentShaderSource(String fragmentShaderSource) {
		this.fragmentShaderSource = fragmentShaderSource;
	}
	public int getVertexShaderProgram() {
		return vertexShaderProgram;
	}
	public void setVertexShaderProgram(int vertexShaderProgram) {
		this.vertexShaderProgram = vertexShaderProgram;
	}
	public int getFragmentShaderProgram() {
		return fragmentShaderProgram;
	}
	public void setFragmentShaderProgram(int fragmentShaderProgram) {
		this.fragmentShaderProgram = fragmentShaderProgram;
	}
	public int getShaderProgram() {
		return shaderProgram;
	}
	public void setShaderProgram(int shaderProgram) {
		this.shaderProgram = shaderProgram;
	}
	
}
