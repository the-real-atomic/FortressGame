package net.cflip.fortress.gl;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;

public class GLBuffer {
	private int id;
	private int bindTarget;

	public GLBuffer(int bindTarget, ByteBuffer data, int usage) {
		this.bindTarget = bindTarget;
		id = glGenBuffers();
		bind();
		glBufferData(bindTarget, data, usage);
	}

	public void bind() {
		glBindBuffer(bindTarget, id);
	}

	public void delete() {
		glDeleteBuffers(id);
	}
}
