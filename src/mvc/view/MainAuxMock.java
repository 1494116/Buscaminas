package mvc.view;

public class MainAuxMock implements MainAux{

	@Override
	public int pasarNivel(int partida) {
		int []nivel= {2,3,2,5,1,1,4,1,1,2};
		return nivel[partida];
	}

	@Override
	public int[][] pasarJugada(int partida) {
		int[][]jugadas = null;
		switch(partida) {
		case 0:		//Partida mala suerte
			jugadas= new int[][] {{3,10,11},{3,4,5}};
			break;
		case 1:		//Partida perdida nivel 3
			jugadas= new int[][] {{3,5,1},{3,5,1},{3,1,5},{1,3,3},{2,3,3},{3,2,2}};
			break;
		case 2:		//Partida completa ganada nivel 2
			jugadas= new int[][] {{3,1,1},{3,10,1},{3,1,5},{1,4,3},{1,4,8},{1,3,9},{1,7,6},{3,3,3},
				{3,2,3},{1,1,3},{3,3,2},{1,3,1},{3,4,2},{3,4,1},{3,5,1},{1,6,1},{3,7,1},{3,8,1},{3,8,2},
				{3,8,3},{1,8,4},{3,8,5},{1,8,6},{3,9,4},{3,9,3},{3,9,2},{1,9,1},{3,10,2},{3,10,3},{1,10,4},
				{3,9,5},{3,10,5},{1,9,6},{3,10,6},{3,10,7},{3,10,8},{3,9,7},{3,9,8},{3,8,7},{3,8,8},{3,7,7},
				{3,7,8},{1,9,9},{1,10,9},{1,9,10},{3,10,10},{3,5,8},{1,5,9},{3,5,10},{3,4,10},{3,4,9},{3,3,10},{3,2,10}};
			break;
		case 3:		//Salir sin empezar
			jugadas= new int[][] {{3,1,1}};
			break;
		case 4:		//Salir saliendo a la mitad
			jugadas= new int[][] {{3,4,1},{3,1,3},{4},{3,2,1},{4}};
			break;
		case 5:		//Mal nivel y malas decisiones perdiendo nivel 1
			jugadas= new int[][] {{3,1,1},{2,3,1},{1,3,1},{1,1,1},{2,2,2},{1,1,1},{2,1,1},{3,15},{1,1,5},{2,1,5},{3,1,5},{3,1,1}};
			break;
		case 6:		//Nivel 4 Ganada
			jugadas= new int[][] {{3,5,1},{3,1,5},{3,1,2},{3,2,1},{3,20,19},{3,19,20}};
			break;
		case 7:		//Todo bombas
			jugadas= new int[][] {{3,1,1}};
			break;
		case 8:	//Ganar con un click
			jugadas= new int[][] {{3,5,5}};
			break;
		case 9: 	//Bombas en cuadrado para comprobar bordes/esquinas
		default:
			jugadas= new int[][] {{3,5,5},{3,1,1},{3,1,3},{3,1,4},{3,1,5},{3,1,6},{3,1,7},{3,1,8},{3,1,10},
				{3,10,1},{3,10,3},{3,10,4},{3,10,5},{3,10,6},{3,10,7},{3,10,8},{3,10,10},
				{3,3,1},{3,4,1},{3,5,1},{3,6,1},{3,7,1},{3,8,1},
				{3,3,10},{3,4,10},{3,5,10},{3,6,10},{3,7,10},{3,8,10}};
			break;
		}
		return jugadas;
	}

	@Override
	public int[][] pasarBombas(int partida) {
		int[][]bombas = null;
		switch(partida) {
		case 0:
			bombas = new int[][] {{3,4}};
			break;
		case 1:
			bombas = new int[][] {{0,0},{1,1},{2,2},{3,3},{4,4},{5,5},{6,6},{7,7},{8,8},{9,9},{10,10},{11,11},{12,12},{13,13},{14,14}};
			break;
		case 2:
			bombas = new int[][] {{0,2},{0,9},{2,0},{2,8},{3,2},{3,7},{4,8},{5,0},{6,5},{7,3},{7,5},{8,0},{8,5},{8,8},{8,9},{9,3},{9,8}};
			break;
		case 4:
			bombas = new int[][] {{0,0},{1,1},{2,2},{3,3},{4,4}};
			break;
		case 5:
			bombas = new int[][] {{0,0},{1,1},{2,2},{3,3},{4,4}};
			break;
		case 6:
			bombas = new int[][] {{0,0},{1,1},{2,2},{3,3},{4,4},{5,5},{6,6},{7,7},{8,8},{9,9},{10,10},{11,11},{12,12},{13,13},{14,14},{15,15},{16,16},{17,17},{18,18},{19,19}};
			break;		
		case 7:
			bombas = new int[][] {{0,0},{0,1},{0,2},{0,3},{0,4},{1,0},{1,1},{1,2},{1,3},{1,4},{2,0},{2,1},{2,2},{2,3},{2,4},{3,0},{3,1},{3,2},{3,3},{3,4},{4,0},{4,1},{4,2},{4,3},{4,4}};
			break;
		case 8:
			bombas = new int[][] {{0,0}};
			break;
		case 9:
			bombas = new int[][] {{1,0},{1,1},{1,2},{1,3},{1,4},{1,5},{1,6},{1,7},{1,8},{1,9},{8,0},{8,1},{8,2},{8,3},{8,4},{8,5},{8,6},{8,7},{8,8},{8,9}
								,{0,1},{2,1},{3,1},{4,1},{5,1},{6,1},{7,1},{9,1},{0,8},{2,8},{3,8},{4,8},{5,8},{6,8},{7,8},{9,8}};
			break;
		default:
			bombas = new int[][] {{0,0},{1,1},{2,2},{3,3},{4,4},{5,5},{6,6},{7,7},{8,8},{9,9},{10,10},{11,11},{12,12},{13,13},{14,14},{15,15},{16,16},{17,17},{18,18},{19,19}};
			break;
		}	
		return bombas;
	}

	@Override
	public String pasarNombre(int partida) {
		String [] nombres= {"Prueba0","Prueba1","Prueba2","Prueba3","Prueba4","Prueba5","Prueba6","Prueba7","Prueba8","Prueba9"};
		return nombres[partida];
	}

	@Override
	public int pasarPartida(int cont) {
		int [] partidas_id = {0,1,2,3,4,5,6,7,8,9};
		return partidas_id[cont];
	}

}
