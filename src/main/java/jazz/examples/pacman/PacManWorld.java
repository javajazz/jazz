package jazz.examples.pacman;

import jazz.Direction;

public class PacManWorld {

	double mouthOpening = 0;
	double posPacManX = 0;
	double posPacManY = 0;
	double pacManRotate = 0;
	double speedX = 1;
	double speedY = 0;

	public void setDirection(Direction dir) {
		switch (dir) {
		case DOWN:
			speedX = 0;
			speedY = -1;
			pacManRotate = -90;
			break;
		case UP:
			speedX = 0;
			speedY = 1;
			pacManRotate = 90;
			break;
		case LEFT:
			speedX = -1;
			speedY = 0;
			pacManRotate = 180;
			break;
		case RIGHT:
			speedX = 1;
			speedY = 0;
			pacManRotate = 0;
			break;
		}
	}
}
