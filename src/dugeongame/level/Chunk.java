package dugeongame.level;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import core.Mathf;

public class Chunk {
	private float x,y;
	private int drawList = -1;
	
	public Chunk(float x, float y,int xOffset, int yOffset, int chunkW, int chunkH, Tile[][] mapping) {
		this.x = x;
		this.y = y;
		
		drawList = GL12.glGenLists(1);
		GL12.glNewList(drawList, GL12.GL_COMPILE);
			GL11.glPushMatrix();
			GL11.glTranslatef(x, y, 0);
			GL11.glBegin(GL11.GL_QUADS);
				for(int i = 0 ; i < yOffset + chunkH ; i++) {
					float yPos = i * Tile.SIZE;
					for(int j = 0 ; j < xOffset + chunkW ; j++) {
						float xPos = j * Tile.SIZE;
						
						float texXPos = mapping[i][j].getxPos();
						float texYPos = mapping[i][j].getyPos();
						float texW = mapping[i][j].getwSize();
						float texH = mapping[i][j].gethSize();
						
						glTexCoord2d(texXPos, texYPos);
						
						glVertex2f(xPos, yPos);
						
						glTexCoord2d(texXPos, texYPos + texH);
						
						glVertex2f(xPos,  yPos + Tile.SIZE);
						
						glTexCoord2d(texXPos + texW, texYPos + texH);
						
						glVertex2f(xPos + Tile.SIZE,  yPos + Tile.SIZE);
						
						glTexCoord2d(texXPos + texW, texYPos);
						
						glVertex2f(xPos + Tile.SIZE, yPos);
					}
				}
			GL11.glEnd();
			GL11.glPopMatrix();
		GL12.glEndList();
	}

	public boolean shouldRender(float camX, float camY) {
		return Mathf.distance_between_points(x, y, camX, camY) < 100;
	}
	
	public void render() {
		GL12.glCallList(drawList);
	}
}
