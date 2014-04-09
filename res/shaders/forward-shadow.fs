#version 330

in vec4 shadowCoord0;
in vec2 texCoord0;
in vec3 normal0;
in vec3 worldPos0;

out vec4 fragColor;

struct BaseLight
{
    vec3 color;
    float intensity;
};

struct DirectionalLight
{
    BaseLight base;
    vec3 direction;
};

uniform vec3 eyePos;
uniform vec3 ambientLight;
uniform sampler2D diffuse;

uniform sampler2D shadowMap0;

uniform float specularIntensity;
uniform float specularPower;

uniform DirectionalLight directionalLight;

float calcShadowFactor()
{
    vec4 shadowCoord;

    shadowCoord = shadowCoord0;

    shadowCoord /= shadowCoord.w;
    
    float shadowFactor = 0.0;
    
    const float SHADOW_BIAS = 0.005;
            float shadowMapValue;

                shadowMapValue = texture(shadowMap0, shadowCoord.xy).r;
				
            if(shadowMapValue < (shadowCoord.z - SHADOW_BIAS))
                shadowFactor += 0.0;
            else
                shadowFactor += 1.0;
				
    return shadowFactor;
}

vec4 calcLight(BaseLight base, vec3 direction, vec3 normal)
{
    float diffuseFactor = dot(normal, -direction);
    
    vec4 diffuseColor = vec4(0,0,0,0);
    vec4 specularColor = vec4(0,0,0,0);
    
    if(diffuseFactor > 0)
    {
        diffuseColor = vec4(base.color, 1.0) * base.intensity * diffuseFactor;
        
        vec3 directionToEye = normalize(eyePos - worldPos0);
        vec3 reflectDirection = normalize(reflect(direction, normal));
        
        float specularFactor = dot(directionToEye, reflectDirection);
        specularFactor = pow(specularFactor, specularPower);
        
        if(specularFactor > 0)
        {
            specularColor = vec4(base.color, 1.0) * specularIntensity * specularFactor;
        }
    }
    
    return diffuseColor + specularColor;
}

void main()
{  
    vec4 totalLight = vec4(ambientLight,1);
    vec4 color = vec4( 1, 1, 1, 1);
    vec4 textureColor = texture(diffuse, texCoord0);
    
    if(textureColor != vec4(0,0,0,0))
        color *= textureColor;
    
    totalLight += calcLight(directionalLight.base, -directionalLight.direction, normal0) * calcShadowFactor();//calcShadowFactor(lightSpacePos0);
    
    fragColor = color * totalLight;
}