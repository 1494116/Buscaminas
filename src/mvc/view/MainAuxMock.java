package mvc.view;

public class MainAuxMock implements MainAux{

	@Override
	public int pasarNivel(int p) {
		int []nivel= {1,1,1,5,1,1,2,3,4};
		return nivel[p];
	}

	@Override
	public int[][] pasarJugada(int p) {
		int[][]jugadas = null;
		switch(p) {
		case 0:		//Partida mala suerte
			jugadas= new int[][] {{3,1,1}};
			break;
		case 1:		//Partida perdida
			jugadas= new int[][] {{3,5,1},{3,1,5},{1,3,3},{3,2,2}};
			break;
		case 2:		//Partida completa ganada
			jugadas= new int[][] {{3,5,1},{3,1,5},{1,3,3},{1,2,2},{3,2,1},{1,1,1},{2,1,1},{3,1,2},{3,5,4},{3,4,5}};
			break;
		case 3:		//Salir sin empezar
			jugadas= new int[][] {{3,1,1}};
			break;
		case 4:		//Salir saliendo a la mitad
			jugadas= new int[][] {{3,5,1},{3,1,5},{4},{3,2,1},{4}};
			break;
		case 5:		//Mal nivel y luego ganar
			jugadas= new int[][] {{3,5,1},{3,1,5},{3,2,1},{3,1,2},{3,5,4},{3,4,5}};
			break;
		case 6:		//Nivel 2 Perdida
			jugadas= new int[][] {{3,5,1},{3,1,5},{1,3,3},{3,2,1},{3,10,9},{3,10,10}};
			break;
		case 7:		//Nivel 3 Perdida
			jugadas= new int[][] {{3,5,1},{3,1,5},{1,3,3},{3,2,1},{3,14,15},{3,15,14},{3,1,1}};
			break;
		case 8:		//Nivel 4 Ganada
			jugadas= new int[][] {{3,5,1},{3,1,5},{3,1,2},{3,2,1},{3,20,19},{3,19,20}};
			break;
		default:
			jugadas= new int[][] {{3,5,1},{3,1,5},{3,1,1}};
			break;
		}
		return jugadas;
	}

	@Override
	public int[][] pasarBombas() {
		int [][]bombas = {{0,0},{1,1},{2,2},{3,3},{4,4},{5,5},{6,6},{7,7},{8,8},{9,9},{10,10},{11,11},{12,12},{13,13},{14,14},{15,15},{16,16},{17,17},{18,18},{19,19}};
		return bombas;
	}

	@Override
	public String pasarNombre(int p) {
		String [] nombres= {"Prueba1","Prueba2","Prueba3","Prueba4","Prueba5","Prueba6","Prueba7","Prueba8","Prueba9"};
		return nombres[p];
	}

}
