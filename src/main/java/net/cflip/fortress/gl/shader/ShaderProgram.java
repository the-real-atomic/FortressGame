package net.cflip.fortress.gl.shader;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_LINK_STATUS;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glGetProgramInfoLog;
import static org.lwjgl.opengl.GL20.glGetProgrami;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glUseProgram;

public class ShaderProgram {
	public static final int LOG_SIZE = 512;

	private final int id;

	public ShaderProgram(String path) {
		Shader vertexShader = new Shader(path+".glv", GL_VERTEX_SHADER);
		Shader fragmentShader = new Shader(path+".glf", GL_FRAGMENT_SHADER);

		id = glCreateProgram();
		glAttachShader(id, vertexShader.id);
		glAttachShader(id, fragmentShader.id);
		glLinkProgram(id);

		if (glGetProgrami(id, GL_LINK_STATUS) == GL_FALSE)
			throw new RuntimeException("Failed to create shader program\n" + glGetProgramInfoLog(id, LOG_SIZE));
	}

	public void bind() {
		glUseProgram(id);
	}
}
