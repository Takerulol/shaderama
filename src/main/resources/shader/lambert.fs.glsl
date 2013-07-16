varying vec3 N;
varying vec3 L;
varying vec4 P;

void main()
{ 
    
    //lambert with white scene
    float lambert = max(0.0,dot(normalize(L),normalize(N)));
    vec3 colorTemp = vec3(1.0,1.0,1.0) * lambert; 
    gl_FragColor = vec4(colorTemp,1.0);
    
    //Color from positions
    //gl_FragColor = (normalize(P) / 2 + 0.5) * lambert;
    //gl_FragColor = normalize(P);
    
    //Color from normals
    //gl_FragColor = vec4(N.r/2+0.5,N.g/2+0.5,N.b/2+0.5,1.0);
    
    //gl_FragColor = gl_Color;

}