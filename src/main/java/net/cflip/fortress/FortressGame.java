package net.cflip.fortress;

import net.cflip.fortress.gl.Model;
import net.cflip.fortress.gl.shader.ShaderProgram;
import net.cflip.fortress.gl.vertex.VertexAttributeFormat;
import net.cflip.fortress.gl.vertex.VertexData;
import net.cflip.fortress.gl.vertex.VertexFormat;
import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;

public class FortressGame {
	public static void main(String[] args) {
		Window window = new Window(1280, 720, "Fortress Game");

		Float[] vertices = {
			-0.5f,  0.5f, 0f,
			-0.5f, -0.5f, 0f,
			0.5f, -0.5f, 0f,
			0.5f,  0.5f, 0f,
		};

		byte[] indices = { 0, 1, 3, 3, 1, 2 };

		VertexFormat vertexFormat = new VertexFormat.Builder()
			.addAttribute(3, VertexAttributeFormat.FLOAT, false)
			.build();

		VertexData vertexData = new VertexData.Builder(vertexFormat, 4)
			.addData(vertices, 0)
			.build();

		ByteBuffer indexData = BufferUtils.createByteBuffer(indices.length);
		indexData.put(indices);
		indexData.flip();

		ShaderProgram shader = new ShaderProgram("/glsl/basic");
		shader.bind();

		Model model = new Model(vertexData, indexData);

		do {
			glClear(GL_COLOR_BUFFER_BIT);
			glClearColor(0.1f, 0.1f, 0.2f, 1.0f);

			model.render(shader);
		} while (window.update());

		model.delete();
		shader.delete();
	}
}
