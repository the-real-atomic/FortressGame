package net.cflip.fortress.gl.shader;

import net.cflip.fortress.gl.GLObject;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_LINK_STATUS;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glDeleteProgram;
import static org.lwjgl.opengl.GL20.glGetProgramInfoLog;
import static org.lwjgl.opengl.GL20.glGetProgrami;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glUseProgram;

public class ShaderProgram extends GLObject {
	public static final int LOG_SIZE = 512;
	public static final String VERT_EXTENSION = ".vert";
	public static final String FRAG_EXTENSION = ".frag";

	private final Shader vertexShader;
	private final Shader fragmentShader;

	public ShaderProgram(String path) {
		super(glCreateProgram());

		vertexShader = new Shader(path + VERT_EXTENSION, GL_VERTEX_SHADER);
		fragmentShader = new Shader(path + FRAG_EXTENSION, GL_FRAGMENT_SHADER);

		vertexShader.attachToProgram(id);
		fragmentShader.attachToProgram(id);
		glLinkProgram(id);

		if (glGetProgrami(id, GL_LINK_STATUS) == GL_FALSE)
			throw new RuntimeException("Failed to create shader program\n" + glGetProgramInfoLog(id, LOG_SIZE));
	}

	@Override
	public void bind() {
		checkValidity();
		glUseProgram(id);
	}

	@Override
	public void unbind() {
		glUseProgram(0);
	}

	@Override
	public void delete() {
		vertexShader.delete();
		fragmentShader.delete();

		glDeleteProgram(id);
		invalidate();
	}
}
