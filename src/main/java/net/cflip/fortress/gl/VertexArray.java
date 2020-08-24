package net.cflip.fortress.gl;

import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class VertexArray {
	private int id;

	public VertexArray() {
		id = glGenVertexArrays();
		bind();
	}

	public void bind() {
		glBindVertexArray(id);
	}

	public void delete() {
		glDeleteVertexArrays(id);
	}
}
