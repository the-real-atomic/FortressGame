package net.cflip.fortress;

import net.cflip.fortress.gl.Model;
import net.cflip.fortress.gl.shader.ShaderProgram;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;

public class FortressGame {
	public static void main(String[] args) {
		Window window = new Window(1280, 720, "Fortress Game");

		float[] vertices = {
			-0.5f,  0.5f, 0,
			-0.5f, -0.5f, 0,
			0.5f, -0.5f, 0,
			0.5f,  0.5f, 0,
		};

		byte[] indices = { 0, 1, 3, 3, 1, 2 };

		ShaderProgram shader = new ShaderProgram("/glsl/basic");
		shader.bind();

		Model model = new Model(vertices, indices);

		while (window.update()) {
			glClear(GL_COLOR_BUFFER_BIT);
			glClearColor(0.1f, 0.1f, 0.2f, 1.0f);

			model.render(shader);
		}

		model.delete();
		shader.delete();
	}
}
