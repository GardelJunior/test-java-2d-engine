package core.camera;

import static org.lwjgl.opengl.GL11.*;

import core.Mathf;

public class Camera {
	
	private float x,y,w,h;
	private float toX,toY;
	private float xOffset,yOffset;
	private float smoothFactor;
	
	private float zoom = 2f;
	private float toZoom = 1;
	private float zoomFactor = 0.04f;
	private boolean isZooming = true;
	
	private float shake = 0;
	
	public Camera(float x, float y,float xOffset, float yOffset, float w, float h, float smoothFactor) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.toX = x;
		this.toY = y;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.smoothFactor = Mathf.clamp(smoothFactor, 0.1f, 1);
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(x, w, h, y, -1, 1);
		glMatrixMode(GL_MODELVIEW);
	}
	
	public void startScene() {
		glPushMatrix();
		glScalef(zoom, zoom, 1);
		glTranslatef(-x, -y, 0);
	}
	
	public void update() {
		
		if(isZooming) {
			x = toX - xOffset/zoom;
			y = toY - yOffset/zoom;
		}else {
			x = Mathf.lerp(x, toX - xOffset/zoom, smoothFactor);
			y = Mathf.lerp(y, toY - yOffset/zoom, smoothFactor);
		}
		
		zoom = Mathf.lerp(zoom, toZoom, zoomFactor);
		
		if(shake > 0) {
			shake *= 0.8;
			int randomAngle = Mathf.randomInRangei(0, 360);
			x += Mathf.lengthdir_x(shake, randomAngle);
			y += Mathf.lengthdir_y(shake, randomAngle);
		}
		
		if(isZooming && toZoom-zoom <= 0.01f) {
			isZooming = false;
		}
	}
	
	public void shake(float magnitude) {
		this.shake = magnitude;
	}
	
	public void endScene() {
		glPopMatrix();
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getW() {
		return w;
	}

	public void setW(float w) {
		this.w = w;
	}

	public float getH() {
		return h;
	}

	public void setH(float h) {
		this.h = h;
	}

	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}

	public float getSmoothFactor() {
		return smoothFactor;
	}

	public void setSmoothFactor(float smoothFactor) {
		this.smoothFactor = Mathf.clamp(smoothFactor, 0.1f, 1);;
	}

	public float getToX() {
		return toX;
	}

	public void setToX(float toX) {
		this.toX = toX;
	}

	public float getToY() {
		return toY;
	}

	public void setToY(float toY) {
		this.toY = toY;
	}

	public float getZoom() {
		return zoom;
	}

	public void setZoom(float zoom, float zoomspeed) {
		this.toZoom = zoom;
		this.zoomFactor = zoomspeed;
		this.isZooming = true;
	}
}
