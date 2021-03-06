varying vec3 N;
varying vec3 L;

//vertex position, just for fun color output
varying vec4 P;

void main()
{
    gl_Position = gl_ModelViewProjectionMatrix*gl_Vertex;
    N = normalize(gl_NormalMatrix*gl_Normal);
	L = vec3(gl_ModelViewMatrix*(vec4(gl_LightSource[0].position)-gl_Vertex));
	P = gl_Vertex;
}