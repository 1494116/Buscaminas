package mvc.view;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import mvc.controller.Partida;
import mvc.model.Puntuaciones;

public class Main {
	
	//Para los mock objects
    private MainAux mockmain;
    public void setMainMock(MainAux m) {
    	this.mockmain=m;
    }

	public static void main(String[] args) throws IOException {
	    
		int opcion = 0, posX = 0, posY = 0, seguir = 1;
		boolean posValida = false, entrada = true;
		String salir;
		
		Partida partida = new Partida();
		Scanner sc = new Scanner(System.in);

		while(entrada) {
			try {
				while(seguir == 1) {
					while (opcion < 1 || opcion > 5){
						System.out.println("Selecciona un nivel:");
						System.out.println("Pulsa 1: Nivel Facil");
						System.out.println("Pulsa 2: Nivel Medio");
						System.out.println("Pulsa 3: Nivel Dificil");
						System.out.println("Pulsa 4: Nivel Muy Dificil");
						System.out.println("Pulsa 5: Salir");
						opcion = sc.nextInt();
					}
					
					switch(opcion) {
						case 1:
						case 2:
						case 3:
						case 4:
							partida.setNivel(opcion);
							partida.mostrar();
							seguir = 0;
							break;
						case 5:
							System.out.println("Seguro que quieres salir? (S/N)\n");
							salir = sc.next();
							seguir = partida.continuarJuego(salir);
							if(seguir == 0) {
								System.out.println("Vuelve pronto!");
								partida.setEnJuego(false);
								entrada=false;
								sc.close();	
							}else {
								opcion = 0;
								seguir = 1;
							}
							
							break;
						default:
							break;
					}
				}
				while (partida.getEnJuego()) {
					try {
						opcion = 0;
						while (opcion < 1 || opcion > 4){
							System.out.println("Que quieres hacer? :");
							System.out.println("Pulsa 1: Marcar casilla");
							System.out.println("Pulsa 2: Desmarcar casilla");
							System.out.println("Pulsa 3: Destapar casilla");
							System.out.println("Pulsa 4: Salir");
							opcion = sc.nextInt();
						}
						if(opcion>0 || opcion <5) {
							switch(opcion) {
								case 1: 
									while(!posValida) {
										System.out.println("Elige una fila:");
										posX = sc.nextInt();
										System.out.println("Elige una columna:");
										posY = sc.nextInt();
										posValida = partida.posCorrecta(posX-1, posY-1);
										if(!posValida) {
											System.out.println("Numeros incorrectos\n");
										}
									}
									partida.marcarCasilla(posX-1, posY-1);
									break;
								case 2:
									while(!posValida) {
										System.out.println("Elige una fila:\n");
										posX = sc.nextInt();
										System.out.println("Elige una columna:\n");
										posY = sc.nextInt();
										posValida = partida.posCorrecta(posX-1, posY-1);
										if(!posValida) {
											System.out.println("Numeros incorrectos");
										}
									}
									partida.desmarcarCasilla(posX-1, posY-1);
									break;
								case 3:
									while(!posValida) {
										System.out.println("Elige una fila:");
										posX = sc.nextInt();
										System.out.println("Elige una columna:");
										posY = sc.nextInt();
										posValida = partida.posCorrecta(posX-1, posY-1);
										if(!posValida) {
											System.out.println("Numeros incorrectos");
										}
									}
									partida.destaparCasilla(posX-1, posY-1);
									if(!partida.getEnJuego()) {
										System.out.println("------------");
										System.out.println("Has perdido!");
										System.out.println("------------");
									}
									else {
										partida.partidaGanada();
									}
									break;
								case 4:
									System.out.println("Seguro que quieres salir? (S/N)\n");
									salir = sc.next();
									seguir = partida.continuarJuego(salir);
									if(seguir == 0) {
										partida.setEnJuego(false);
										entrada=false;
									}
									break;
							}
							
							partida.mostrar();
							posValida = false;
						}
						

					}catch(InputMismatchException ex) {
						System.out.println("Error: Introduce un caracter valido");
						System.out.println();
						sc.next();
					}



				}
				if (partida.getPuntuacion()>0) {
					System.out.println("Nom del jugador?\n");
					int punts=partida.getPuntuacion();
					String nom = null; 
					nom = sc.next();
					Puntuaciones puntuacion = new Puntuaciones(nom, punts, partida.getNivel());
					//escrivim puntuacions i mostrem per nivell
					puntuacion.escribirPuntuaciones(punts,partida.getNivel(),nom);
					puntuacion.mostrar_Puntuaciones(partida.getNivel());
					entrada = false;
				}
				sc.close();
			}catch(InputMismatchException ex) {
				System.out.println("Error: Introduce un caracter valido");
				System.out.println();
				sc.next();
			}

		}

	}
	
}
