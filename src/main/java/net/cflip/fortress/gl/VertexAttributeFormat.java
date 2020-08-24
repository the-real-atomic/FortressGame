package net.cflip.fortress.gl;

import static org.lwjgl.opengl.GL11.GL_FLOAT;

public enum VertexAttributeFormat {
	FLOAT(GL_FLOAT, Float.BYTES);

	public int glIdentifier;
	public int bytes;

	VertexAttributeFormat(int glIdentifier, int bytes) {
		this.glIdentifier = glIdentifier;
		this.bytes = bytes;
	}
}
