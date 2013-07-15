uniform sampler2D sampler01;

varying vec3 N;
varying vec3 L;
varying vec4 C;

void main()
{ 
    //float lambert = max(0.0, dot(normalize(L),normalize(N)));
    //gl_FragColor = texture2D(sampler01, (gl_TexCoord[0].st));
    
    float lambert = dot(normalize(L),normalize(N));
    
    vec3 colorTemp = vec3(1.0,1.0,1.0) * lambert;
    gl_FragColor = vec4(colorTemp,1.0);
    //gl_FragColor = vec4(1.0, 0.0, 0.0, 1.0);
    //gl_FragColor = gl_Color;

}