package modelo;

public class Partida {
    private Jugador j1;
    private Jugador j2;
    private Tablero tablero;

    public Partida(Jugador j1, Jugador j2, Tablero tablero){
        this.setJugador1(j1);
        this.setJugador2(j2);
        this.setTablero(tablero);
    }
    
    public void empezar(){
        
    }
    
    public void terminar(){
        
    }
    
    public Jugador getJugador1() {
        return this.j1;
    }

    public void setJugador1(Jugador jugador1) {
        this.j1 = jugador1;
    }

    public Jugador getJugador2() {
        return this.j2;
    }

    public void setJugador2(Jugador jugador2) {
        this.j2 = jugador2;
    }

    public Tablero getTablero() {
        return this.tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    } 
}
