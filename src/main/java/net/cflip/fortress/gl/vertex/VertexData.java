package net.cflip.fortress.gl.vertex;

import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class VertexData {
	public final ByteBuffer buffer;
	public final VertexFormat format;
	public final int vertexCount;

	private VertexData(ByteBuffer buffer, VertexFormat format, int vertexCount) {
		this.buffer = buffer;
		this.format = format;
		this.vertexCount = vertexCount;
	}

	public static final class Builder {
		private final ByteBuffer buffer;
		private final VertexFormat vertexFormat;
		private final int vertexCount;

		public Builder(VertexFormat vertexFormat, int vertexCount) {
			this.vertexFormat = vertexFormat;
			this.vertexCount = vertexCount;

			buffer = BufferUtils.createByteBuffer(vertexFormat.getTotalBytes() * vertexCount);
		}

		public <T> Builder addData(T[] data, int attributeIndex) {
			VertexAttribute attribute = vertexFormat.getAttribute(attributeIndex);
			int expectedSize = attribute.elementCount * vertexCount;

			if (data.length != expectedSize)
				throw new IllegalArgumentException("Data array length does not match expected size");

			for (int i = 0; i < vertexCount; i++) {
				int bufferIndex = i * vertexFormat.getTotalBytes() + attribute.stride;
				int copyStart = i * attribute.elementCount;

				T[] section = Arrays.copyOfRange(data, copyStart, copyStart + attribute.elementCount);
				attribute.format.bufferWriter.writeToBuffer(section, buffer, bufferIndex);
			}

			return this;
		}

		public VertexData build() {
			buffer.flip();
			return new VertexData(buffer, vertexFormat, vertexCount);
		}
	}
}
