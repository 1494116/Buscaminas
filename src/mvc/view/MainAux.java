package mvc.view;

public interface MainAux {
    //Esta interfaz la creamos para poder hacer un mock object del Main
    int pasarNivel(int p);

    int[][] pasarBombas();

    int[][] pasarJugada(int p);

    String pasarNombre(int p);
}