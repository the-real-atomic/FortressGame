package net.cflip.fortress.gl.vertex;

public class VertexAttribute {
	public VertexAttributeFormat format;
	public int byteCount;
	public int elementCount;
	public boolean normalized;
	public int stride;

	public VertexAttribute(int elementCount, VertexAttributeFormat format, boolean normalized) {
		this.format = format;
		this.byteCount = elementCount * format.bytes;
		this.elementCount = elementCount;
		this.normalized = normalized;
	}
}
