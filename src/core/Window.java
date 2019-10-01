package core;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.opengl.GL;

public class Window {
	
	private static Window instance;
	
	public static Window getInstance() {
		if(instance == null) {
			throw new RuntimeException("Window has not been started!");
		}
		return instance;
	}
	
	private static void init() {
		glfwInit();
	}
	
	public static Window createWindow(int width, int height, String title) {
		if(instance == null) {
			init();
			instance = new Window(width,height,title);
		}
		return instance;
	}
	
	private int width, height;
	private String title;
	private float aspectRatio;
	
	private float mouseX,mouseY;
	
	private long window;

	private Window(int width, int height, String title) {
		this.width = width;
		this.height = height;
		this.title = title;
		this.aspectRatio = (float) width / (float) height;
		
		window = glfwCreateWindow(width, height, title, 0, 0);
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		
		glfwSetCursorPosCallback(window, (long window, double xpos, double ypos) -> {
			this.mouseX = (float) xpos;
			this.mouseY = (float) ypos;
		});
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getAspectRatio() {
		return aspectRatio;
	}

	public float getMouseX() {
		return mouseX;
	}

	public void setMouseX(float mouseX) {
		this.mouseX = mouseX;
	}

	public float getMouseY() {
		return mouseY;
	}

	public void setMouseY(float mouseY) {
		this.mouseY = mouseY;
	}

	public long getWindow() {
		return window;
	}
	
	public boolean windowShouldClose() {
		return glfwWindowShouldClose(window);
	}
	
	public int getKey(int key) {
		return glfwGetKey(window, key);
	}
	
	public void swapBuffers() {
		glfwSwapBuffers(window);
	}
	
	public void terminate() {
		glfwDestroyWindow(window);
		glfwTerminate();
	}
}
