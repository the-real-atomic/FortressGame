package net.cflip.fortress.gl.shader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glDeleteShader;
import static org.lwjgl.opengl.GL20.glDetachShader;
import static org.lwjgl.opengl.GL20.glShaderSource;

public class Shader {
	private int id;
	private int programId;

	public Shader(String sourcePath, int type) {
		String source;
		try {
			source = readSource(sourcePath);
		} catch (IOException e) {
			throw new RuntimeException("Failed to read shader source from file at " + e.getMessage());
		}

		id = glCreateShader(type);
		glShaderSource(id, source);
		glCompileShader(id);

		// I don't check for errors here because they will be reported in the program's link error log
	}

	public void attachToProgram(int programId) {
		this.programId = programId;
		glAttachShader(programId, id);
	}

	public void delete() {
		glDetachShader(programId, id);
		glDeleteShader(id);
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
