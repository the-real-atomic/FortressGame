package net.cflip.fortress.gl;

public class VertexAttribute {
	public int byteCount;
	public int elementCount;
	public int format;
	public boolean normalized;

	public VertexAttribute(int elementCount, VertexAttributeFormat format, boolean normalized) {
		this.byteCount = elementCount * format.bytes;
		this.elementCount = elementCount;
		this.format = format.glIdentifier;
		this.normalized = normalized;
	}
}
