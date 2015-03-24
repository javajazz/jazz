package jazz;

public class HsvColor implements Color {

    final private float h;
    final private float s;
    final private float v;

    public HsvColor(final float h, final float s, final float v) {
        this.h = h;
        this.s = s;
        this.v = v;
    }

    public float getH() {
        return h;
    }

    public float getS() {
        return s;
    }

    public float getV() {
        return v;
    }
}
