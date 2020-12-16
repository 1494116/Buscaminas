package mvc.view;

public interface MainAux {
	
	//Esta interfaz la creamos para poder hacer un mock object del Main
	int pasarPartida(int partidaID);
	
	int pasarNivel(int partida);
	
	int[][] pasarBombas(int partida);
	
	int[][] pasarJugada(int partida);
	
	String pasarNombre(int partida);
}
