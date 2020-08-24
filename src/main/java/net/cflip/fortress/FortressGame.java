package net.cflip.fortress;

import static org.lwjgl.opengl.GL11.glClearColor;

public class FortressGame {
	public static void main(String[] args) {
		Window window = new Window(1280, 720, "Fortress Game");

		while (window.update()) {
			glClearColor(0.1f, 0.1f, 0.2f, 1.0f);
		}
	}
}
