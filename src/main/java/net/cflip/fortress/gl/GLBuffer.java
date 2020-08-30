package net.cflip.fortress.gl;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;

public class GLBuffer extends GLObject {
	private final int bindTarget;

	public GLBuffer(int bindTarget, ByteBuffer data, int usage) {
		super(glGenBuffers());
		this.bindTarget = bindTarget;
		bind();
		glBufferData(bindTarget, data, usage);
		unbind();
	}

	@Override
	public void bind() {
		checkValidity();
		glBindBuffer(bindTarget, id);
	}

	@Override
	public void unbind() {
		glBindBuffer(bindTarget, 0);
	}

	@Override
	public void delete() {
		glDeleteBuffers(id);
		invalidate();
	}
}
