package id.ac.its.Ricky;

public class Asteroid extends Sprite {

	public Asteroid(int x, int y) {
		super(x,y);
		initAsteroid();
	}
	private void initAsteroid() {
		loadImage("src/resources/asteroid.png");
		getImageDimensions();
	}
}
