package core.physics;

public interface ICollidable {
	public boolean isColliding(ICollidable collidable);
	public boolean onCollisionWith(ICollidable collidable);
}
