package dugeongame;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.io.IOException;

import core.Mathf;
import core.Texture;
import core.Window;
import core.camera.Camera;
import dugeongame.entity.OPlayer;
import dugeongame.level.Level;
import dugeongame.level.LevelLoader;

public class Game {
	
	public static void main(String[] args) throws IOException {
		Window window = Window.createWindow(800, 600, "Joguinho");
		
		glClearColor(0f, 0f, 0f, 1);
		
		glEnable(GL_BLEND);
		glEnable(GL_TEXTURE_2D);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		Texture textura = new Texture("/dugeon.png");
		
		LevelLoader mapLoader = new LevelLoader();
		
		Camera camera = new Camera(0, 0, window.getWidth()/2, window.getHeight()/2, window.getWidth(), window.getHeight(), .3f);
		Level map = mapLoader.loadLevel("/map1.level", textura);
		OPlayer player = new OPlayer(textura);
		
		glBindTexture(GL_TEXTURE_2D, textura.getTexture());
		while(!window.windowShouldClose()) {
			glfwPollEvents();
			glClear(GL_COLOR_BUFFER_BIT);
			glColor3f(1, 1, 1);
			
			boolean 	A = window.getKey( GLFW_KEY_A) > 0,
					 	D = window.getKey( GLFW_KEY_D) > 0,
					 	S = window.getKey( GLFW_KEY_S) > 0,
					 	W = window.getKey( GLFW_KEY_W) > 0;
					 	
			if(A || D || S || W) {
				if(A) {
					player.setX(player.getX()-4);
					player.setxScale(-Mathf.abs(player.getxScale()));
				}
				if(D) {
					player.setX(player.getX()+4);
					player.setxScale(Mathf.abs(player.getxScale()));
				}
				if(W) {
					player.setY(player.getY()-4);
				}
				if(S) {
					player.setY(player.getY()+4);
				}
				player.setCurrentAnimation("run");
			}else {
				player.setCurrentAnimation("idle");
			}
			
			if(window.getKey( GLFW_KEY_SPACE) > 0) {
				if(!player.jumping) {
					player.jumping = true;
				}
			}
			
			camera.setToX(player.getX());
			camera.setToY(player.getY());
			
			player.update();
			camera.update();
			
			camera.startScene();
			map.render();
			player.render();

			camera.endScene();
			window.swapBuffers();
		}
		window.terminate();
	}
}
