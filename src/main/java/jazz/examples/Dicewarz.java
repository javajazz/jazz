package jazz.examples;

import java.util.*;

import jazz.*;
import jazz.pictures.*;

public class Dicewarz {

	public static Polygon hexagon(double a) {

		double x = Math.cos(30.0 / 180.0 * Math.PI);
		double y = Math.sin(30.0 / 180.0 * Math.PI);

		return new Polygon(new Point(0, a), new Point(x * a, y * a), new Point(
				x * a, -y * a), new Point(0, -a), new Point(-x * a, -y * a),
				new Point(-x * a, y * a));
	}

	public static IntPair topLeft(int x, int y, int X, int Y) {
		return new IntPair(
				y % 2 == 1 ? Math.max(0, x - 1) : x,
				Math.min(Y - 1, y + 1));
	}

	public static IntPair topRight(int x, int y, int X, int Y) {
		return new IntPair(
				y % 2 == 1 ? x : Math.min(X - 1, x + 1),
				Math.min(Y - 1, y + 1));
	}

	public static IntPair bottomLeft(int x, int y, int X, int Y) {
		return new IntPair(
				y % 2 == 1 ? Math.max(0, x - 1) : x,
				Math.max(0, y - 1));
	}

	public static IntPair bottomRight(int x, int y, int X, int Y) {
		return new IntPair(
				y % 2 == 1 ? x : Math.min(X - 1, x + 1),
				Math.max(0, y - 1));
	}

	public static IntPair left(int x, int y, int X, int Y) {
		return new IntPair(
				Math.max(0, x - 1),
				y);
	}

	public static IntPair right(int x, int y, int X, int Y) {
		return new IntPair(
				Math.min(X - 1, x + 1),
				y);
	}

	static int height = 800;
	static int width = 1000;

	static int N = 50;

	static double a = width / N / Math.sqrt(3);
	static double dx = Math.sqrt(3) * a;

	static int M = (int) (height / (1.5 * a) - 1);

	static int W = 5;
	static int H = 4;

	static int X = N / W;
	static int Y = M / H;

	static int F = X * Y;

	static int S = 8;
	static int T = 10;

	public static void main(String... args) {

		Random r = new Random();

		Color[] playerColors = {
				Color.MAGENTA,
				Color.BLUE,
				Color.RED,
				Color.CYAN,
				Color.FOREST,
				Color.GREEN,
				Color.BROWN,
				Color.YELLOW
		};

		ArrayList<Color> cFields = new ArrayList<Color>(F);
		for (int i = 0; i < F; i++) {
			cFields.add(Color.WHITE);
		}
		int c = 0;
		for (int s = 0; s < S; s++) {
			for (int t = 0; t < T; t++) {
				cFields.set(c++, playerColors[s]);
			}
		}
		Collections.shuffle(cFields);

		Color[][] fields = new Color[N][M];

		for (int x = 0; x < X; x++) {
			for (int y = 0; y < Y; y++) {

				int a = r.nextInt(W) + x * W;
				int b = r.nextInt(H) + y * H;

				Color fc = cFields.get(x + y * X);

				fields[topLeft(a, b, N, M).a][topLeft(a, b, N, M).b] = fc;
				fields[topRight(a, b, N, M).a][topRight(a, b, N, M).b] = fc;
				fields[bottomLeft(a, b, N, M).a][bottomLeft(a, b, N, M).b] = fc;
				fields[bottomRight(a, b, N, M).a][bottomRight(a, b, N, M).b] = fc;
				fields[left(a, b, N, M).a][left(a, b, N, M).b] = fc;
				fields[right(a, b, N, M).a][right(a, b, N, M).b] = fc;

				fields[a][b] = Color.BLACK;
			}
		}

		// (x,y) -> (a,b)
		// mit
		// |x-a| <= 1
		// und |y-b| <= 1
		// und a != x
		// und b != y
		// und nicht (a - x == -1 und b - y == 1)
		// und nicht (a - x == 1 und b - y == -1)

		// Alle sechs angrenzenden Felder
		// (x-1,y) (x,y-1) (x+1,y) (x,y+1) (x+1,y+1) (x-1,y-1)

		Pictures p = new Pictures();

		for (int y = 0; y < M; y++) {

			for (int x = 0; x < N - y % 2; x++) {

				double tx = x * dx;
				if (y % 2 == 1) {
					tx += dx / 2;
				}
				double ty = y * 1.5 * a;

				p.add(hexagon(a)
						.translate(tx, ty)
						.color(fields[x + y % 2][y])
						.filled(fields[x + y % 2][y] != null));
			}
		}

		p.translate(-500 + dx / 2, -400 + a);

		Jazz.display("Dicewarz", width, height, p);
	}

}
