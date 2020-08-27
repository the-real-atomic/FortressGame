package net.cflip.fortress.gl;

import net.cflip.fortress.gl.shader.ShaderProgram;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;

public class Model {
	private final GLBuffer vertexBuffer;
	private final GLBuffer indexBuffer;
	private final VertexArray vertexArray;

	private final int vertexCount;

	private final VertexFormat vertexFormat;

	public Model(ByteBuffer vertexData, ByteBuffer indexData) {
		vertexCount = indexData.limit();

		vertexBuffer = new GLBuffer(GL_ARRAY_BUFFER, vertexData, GL_STATIC_DRAW);
		indexBuffer = new GLBuffer(GL_ELEMENT_ARRAY_BUFFER, indexData, GL_STATIC_DRAW);

		vertexBuffer.bind();
		indexBuffer.bind();

		vertexArray = new VertexArray();
		vertexArray.bind();

		VertexAttribute[] attributeList = {
			new VertexAttribute(3, VertexAttributeFormat.FLOAT, false)
		};

		vertexFormat = new VertexFormat(attributeList);
		vertexFormat.bindAttribs();
	}

	public void delete() {
		vertexBuffer.delete();
		indexBuffer.delete();
		vertexArray.delete();
	}

	public void render(ShaderProgram shader) {
		vertexArray.bind();
		vertexFormat.enableAttribs();

		vertexBuffer.bind();
		indexBuffer.bind();

		shader.bind();

		glDrawElements(GL_TRIANGLES, vertexCount, GL_UNSIGNED_BYTE, 0);

		vertexFormat.disableAttribs();
	}
}
