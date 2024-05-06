package modelo;

public class Color {

    private String color;

    public Color(int opcion) {
        this.setColor(opcion);
    }

    public String[] getColoresPosibles() {
        String[] colores = {
            "\u001B[30m", //0-NEGRO
            "\u001B[31m", //1-ROJO
            "\u001B[32m", //2-VERDE
            "\u001B[33m", //3-AMARILLO
            "\u001B[34m", //4-AZUL
            "\u001B[35m", //5-MAGENTA
            "\u001B[36m", //6-CYAN
            "\u001B[37m", //7-BLANCO
            "\033[1;34m", //8-LILA
        };
        return colores;
    }

    public void setColor(int opcion) {
        this.color = this.getColoresPosibles()[opcion];
    }

    public String getColor() {
        return this.color;
    }

    @Override
    public String toString() {
        return this.getColor();
    }
}
