package net.cflip.fortress.gl;

import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

public class VertexFormat {
	public final VertexAttribute[] attributes;

	public VertexFormat(VertexAttribute[] attributes) {
		this.attributes = attributes;
	}

	public void bindAttribs() {
		int bytesSoFar = 0;

		for (int i = 0; i < attributes.length; i++) {
			VertexAttribute attribute = attributes[i];
			glVertexAttribPointer(i, attribute.elementCount, attribute.format.glIdentifier, attribute.normalized, 0, bytesSoFar);
			bytesSoFar += attribute.byteCount;
		}
	}

	public void enableAttribs() {
		for (int i = 0; i < attributes.length; i++) {
			glEnableVertexAttribArray(i);
		}
	}

	public void disableAttribs() {
		for (int i = 0; i < attributes.length; i++) {
			glDisableVertexAttribArray(i);
		}
	}
}
