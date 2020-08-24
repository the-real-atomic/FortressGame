package net.cflip.fortress;

import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

public class Window {
	private long window;

	public Window(int width, int height, String title) {
		if (!glfwInit())
			throw new IllegalStateException("Could not initialize GLFW");

		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
		window = glfwCreateWindow(width, height, title, 0, 0);

		if (window == 0)
			throw new RuntimeException("Could not create a window");

		glfwMakeContextCurrent(window);
		glfwSwapInterval(1);

		GL.createCapabilities();
	}

	public boolean update() {
		glfwSwapBuffers(window);
		glfwPollEvents();

		return !glfwWindowShouldClose(window);
	}
}
