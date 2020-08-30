package net.cflip.fortress.gl;

import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class VertexArray extends GLObject {
	public VertexArray() {
		super(glGenVertexArrays());
	}

	@Override
	public void bind() {
		checkValidity();
		glBindVertexArray(id);
	}

	@Override
	public void unbind() {
		glBindVertexArray(0);
	}

	@Override
	public void delete() {
		glDeleteVertexArrays(id);
		invalidate();
	}
}
