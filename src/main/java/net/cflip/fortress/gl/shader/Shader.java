package net.cflip.fortress.gl.shader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glShaderSource;

public class Shader {
	public int id;

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
