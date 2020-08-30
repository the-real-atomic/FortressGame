package net.cflip.fortress.gl.vertex;

import net.cflip.fortress.util.FloatBufferWriter;
import net.cflip.fortress.util.GenericBufferWriter;

import static org.lwjgl.opengl.GL11.GL_FLOAT;

public enum VertexAttributeFormat {
	FLOAT(GL_FLOAT, Float.BYTES, new FloatBufferWriter());

	public int glIdentifier;
	public int bytes;
	public GenericBufferWriter bufferWriter;

	VertexAttributeFormat(int glIdentifier, int bytes, GenericBufferWriter bufferWriter) {
		this.glIdentifier = glIdentifier;
		this.bytes = bytes;
		this.bufferWriter = bufferWriter;
	}
}
