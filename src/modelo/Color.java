package modelo;

public class Color {

    private String color;

    public Color(int opcion) {
        this.setColor(opcion);
    }

    public String[] getColoresPosibles() {
        String[] colores = {
            "\u001B[30m", //NEGRO
            "\u001B[31m", //ROJO
            "\u001B[32m", //VERDE
            "\u001B[33m", //AMARILLO
            "\u001B[34m", //AZUL
            "\u001B[35m", //VIOLETA
            "\u001B[36m", //CYAN
            "\u001B[37m" //BLANCO
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
