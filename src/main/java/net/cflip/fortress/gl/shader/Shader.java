package net.cflip.fortress.gl.shader;

import net.cflip.fortress.gl.GLObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

public class Shader extends GLObject {
	private int programId;

	public Shader(String sourcePath, int type) {
		super(glCreateShader(type));

		String source;
		try {
			source = readSource(sourcePath);
		} catch (IOException e) {
			throw new RuntimeException("Failed to read shader source from file at " + e.getMessage());
		}

		glShaderSource(id, source);
		glCompileShader(id);

		if (glGetShaderi(id, GL_COMPILE_STATUS) == GL_FALSE)
			throw new RuntimeException("Failed to compile shader\n" + glGetShaderInfoLog(id, ShaderProgram.LOG_SIZE));
	}

	@Override
	public void bind() {
		// Shaders do not have a bind function
	}

	@Override
	public void unbind() {}

	public void attachToProgram(int programId) {
		this.programId = programId;
		glAttachShader(programId, id);
	}

	@Override
	public void delete() {
		glDetachShader(programId, id);
		glDeleteShader(id);
		invalidate();
	}

	// TODO: This is just a generic file reading function that can go somewhere else
	public String readSource(String path) throws IOException {
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(Shader.class.getResourceAsStream(path)));

		String line;
		while ((line = reader.readLine()) != null) {
			builder.append(line).append("\n");
		}

		reader.close();

		return builder.toString();
	}
}
