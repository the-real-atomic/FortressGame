package net.cflip.fortress.gl.vertex;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

public class VertexFormat {
	public final List<VertexAttribute> attributes;

	public VertexFormat(List<VertexAttribute> attributes) {
		this.attributes = attributes;

		int bytesSoFar = 0;
		for (VertexAttribute attribute : attributes) {
			attribute.stride = bytesSoFar;
			bytesSoFar += attribute.byteCount;
		}
	}

	public void bindAttribs() {
		for (int i = 0; i < attributes.size(); i++) {
			VertexAttribute attribute = attributes.get(i);
			glVertexAttribPointer(i, attribute.elementCount, attribute.format.glIdentifier, attribute.normalized, 0, attribute.stride);
		}
	}

	public void enableAttribs() {
		for (int i = 0; i < attributes.size(); i++) {
			glEnableVertexAttribArray(i);
		}
	}

	public void disableAttribs() {
		for (int i = 0; i < attributes.size(); i++) {
			glDisableVertexAttribArray(i);
		}
	}

	public VertexAttribute getAttribute(int index) {
		return attributes.get(index);
	}

	public int getTotalBytes() {
		int totalBytes = 0;

		for (VertexAttribute attribute : attributes) {
			totalBytes += attribute.byteCount;
		}

		return totalBytes;
	}

	public static final class Builder {
		private final List<VertexAttribute> attributeList;

		public Builder() {
			attributeList = new ArrayList<>();
		}

		public Builder addAttribute(VertexAttribute attribute) {
			attributeList.add(attribute);
			return this;
		}

		public Builder addAttribute(int elementCount, VertexAttributeFormat format, boolean normalized) {
			return addAttribute(new VertexAttribute(elementCount, format, normalized));
		}

		public VertexFormat build() {
			return new VertexFormat(attributeList);
		}
	}
}
