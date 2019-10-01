package core;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {
	public static void renderTexturedQuad(float xSize, float ySize, float texXPos, float texYPos, float texW, float texH) {
		glBegin(GL_QUADS);
			glTexCoord2d(texXPos, texYPos);
			glVertex2f(0, 0);
			glTexCoord2d(texXPos, texYPos + texH);
			glVertex2f(0,  xSize);
			glTexCoord2d(texXPos + texW, texYPos + texH);
			glVertex2f( xSize,  ySize);
			glTexCoord2d(texXPos + texW, texYPos);
			glVertex2f( xSize, 0);
		glEnd();
	}
}
