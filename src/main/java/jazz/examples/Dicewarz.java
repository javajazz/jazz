package jazz.examples;

import jazz.Color;
import jazz.Jazz;
import jazz.Pictures;
import jazz.Point;
import jazz.Polygon;
import jazz.util.GridCoords;

public class Dicewarz {

	public static Polygon hexagon(double a) {

		double x = Math.cos(30.0 / 180.0 * Math.PI);
		double y = Math.sin(30.0 / 180.0 * Math.PI);

		return new Polygon(
				new Point(x * a, 2 * a),
				new Point(2 * x * a, y * a + a),
				new Point(2 * x * a, y * a),
				new Point(x * a, 0),
				new Point(0, y * a),
				new Point(0, y * a + a));
	}

	public static GridCoords topLeft(int x, int y, int X, int Y) {
		return new GridCoords(
				y % 2 == 1 ? Math.max(0, x - 1) : x,
				Math.min(Y - 1, y + 1));
	}

	public static GridCoords topRight(int x, int y, int X, int Y) {
		return new GridCoords(
				y % 2 == 1 ? x : Math.min(X - 1, x + 1),
				Math.min(Y - 1, y + 1));
	}

	public static GridCoords bottomLeft(int x, int y, int X, int Y) {
		return new GridCoords(
				y % 2 == 1 ? Math.max(0, x - 1) : x,
				Math.max(0, y - 1));
	}

	public static GridCoords bottomRight(int x, int y, int X, int Y) {
		return new GridCoords(
				y % 2 == 1 ? x : Math.min(X - 1, x + 1),
				Math.max(0, y - 1));
	}

	public static GridCoords left(int x, int y, int X, int Y) {
		return new GridCoords(
				Math.max(0, x - 1),
				y);
	}

	public static GridCoords right(int x, int y, int X, int Y) {
		return new GridCoords(
				Math.min(X - 1, x + 1),
				y);
	}

	static int width = 800;
	static int height = 600;

	static int N = 45;

	static double a = width / N / Math.sqrt(3);
	static double dx = Math.sqrt(3) * a;

	static int M = (int) (height / (1.5 * a) - 1);

	static int W = 6;
	static int H = 5;

	static int X = N / W;
	static int Y = M / H;

	static int F = X * Y;

	static int S = 8;
	static int T = 5;

	public static void main(String... args) {

		//Jazz.seed();

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

		Color[] cFields = new Color[F];
		int k = 0;
		for (int s = 0; s < S; s++) {
			for (int t = 0; t < T; t++) {
				cFields[k++] = playerColors[s];
			}
		}
		Jazz.shuffle(cFields);

		int[] cursors = new int[F];
		int[] paths = new int[F];
		Color[][] fields = new Color[N][M];

		for (int x = 0; x < X; x++) {
			for (int y = 0; y < Y; y++) {

				int a = Jazz.randomInt(W - 2) + x * W + 1;
				int b = Jazz.randomInt(H - 1) + y * H + 1;

				Color c = cFields[x + y * X];

				fields[a][b] = c;
				cursors[y + x * Y] = a + b * N;
			}
		}

		k = 1;
		while (k > 0) {
			for (int i = 0; i < F; i++) {

				k = 0;

				int x = cursors[i] % N;
				int y = cursors[i] / N;

				Color c = fields[x][y];

				int nx = x;
				int ny = y;
				GridCoords pos = topRight(x, y, N, M);
				if (fields[pos.x][pos.y] == null && paths[i] < 10) {
					nx = pos.x;
					ny = pos.y;
					k++;
					paths[i]++;
				} else {
					paths[i] = 0;
				}
				cursors[i] = nx + ny * N;
				fields[nx][ny] = c;
			}
		}

		Pictures p = new Pictures();

		for (int y = 0; y < M; y++) {
			for (int x = 0; x < N - y % 2; x++) {
				if (fields[x + y % 2][y] != null) {
					double tx = x * dx;
					if (y % 2 == 1) {
						tx += dx / 2;
					}
					double ty = y * 1.5 * a;

					p.add(hexagon(a + 0.5)
							.translate(tx, ty)
							.color(fields[x + y % 2][y])
							.filled(true));
				}
			}
		}

		p.translate(-500, -300);

		Jazz.display("Dicewarz", width + 200, height, p);
	}

}
