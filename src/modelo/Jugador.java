package modelo;

public class Jugador {

    private String nombre;
    private int edad;
    private String alias;
    private int partidasTotales = 0;
    private int partidasGanadas = 0;
    private int partidasPerdidas = 0;
    private int partidasAbandonadas = 0;
    private int puntaje = 0;
    
    public Jugador(){
        this.setNombre("undefined");
        this.setEdad(-1);
        this.setAlias("undefined");
    }
    
    public Jugador(String nombre, int edad, String alias) {
        this.setNombre(nombre);
        this.setEdad(edad);
        this.setAlias(alias);
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return this.edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getPartidasTotales() {
        return this.partidasTotales;
    }

    public void setPartidasTotales(int partidasTotales) {
        this.partidasTotales = partidasTotales;
    }

    public int getPartidasGanadas() {
        return this.partidasGanadas;
    }

    public void setPartidasGanadas(int partidasGanadas) {
        this.partidasGanadas = partidasGanadas;
    }

    public int getPartidasPerdidas() {
        return this.partidasPerdidas;
    }

    public void setPartidasPerdidas(int partidasPerdidas) {
        this.partidasPerdidas = partidasPerdidas;
    }

    public int getPartidasAbandonadas() {
        return this.partidasAbandonadas;
    }

    public void setPartidasAbandonadas(int partidasAbandonadas) {
        this.partidasAbandonadas = partidasAbandonadas;
    }

    public int getPuntaje() {
        return this.puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    @Override
    public String toString() {
        return this.getNombre() + " (" + this.getAlias() + ")";
    }
    
    public boolean equals(Jugador otroJugador) {
        return this.getAlias().equals(otroJugador.getAlias());
    }
}
