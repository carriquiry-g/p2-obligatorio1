package modelo;

public class Color {

    private int r;
    private int g;
    private int b;

    public Color() {
        this.setR(0);
        this.setG(0);
        this.setB(0);
    }

    public Color(int r, int g, int b) {
        this.setR(r);
        this.setG(g);
        this.setB(b);
    }

    public int getR() {
        return this.r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return this.g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return this.b;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "Color: " + this.getR() + "(R), " + this.getG() + "(G), " + this.getB() + "(B).";
    }
}
