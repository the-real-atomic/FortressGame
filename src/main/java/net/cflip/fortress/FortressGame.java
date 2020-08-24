package net.cflip.fortress;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;

public class FortressGame {
	public static void main(String[] args) {
		Window window = new Window(1280, 720, "Fortress Game");

		while (window.update()) {
			glClear(GL_COLOR_BUFFER_BIT);
			glClearColor(0.1f, 0.1f, 0.2f, 1.0f);
		}
	}
}
