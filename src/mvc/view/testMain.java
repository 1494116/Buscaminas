package mvc.view;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mvc.controller.Partida;
import mvc.model.Puntuaciones;

class testMain {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	
	//Test donde abriremos una casilla fuera del tablero y luego abriremos una bomba
	@Test
	void testPartida1() throws IOException {
		
		//Creamos mockObject del main
		MainAuxMock mockmain = new MainAuxMock();
		int num_partida=0;
		
			System.out.println("----- INICIO PARTIDA 1 Mala suerte -----\n");
			
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
							System.out.println("Pulsa 5: Salir\n");
							//Le pasamos un nivel dependiendo de la partida
							opcion = mockmain.pasarNivel(num_partida);
							System.out.println("Nivel seleccionado: " + opcion + "\n");
						}		
						switch(opcion) {
							case 1:
							case 2:
							case 3:
							case 4:
								partida.setNivel(opcion);
								assertEquals(partida.getNivel(),opcion);
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
					//Creamos nuestro array de jugadas y su contador
					int [][]jugadas=mockmain.pasarJugada(num_partida);
					int contador=0;
					while (partida.getEnJuego()) {
						try {
							opcion = 0;
							while (opcion < 1 || opcion > 4){
								System.out.println("Que quieres hacer? :");
								System.out.println("Pulsa 1: Marcar casilla");
								System.out.println("Pulsa 2: Desmarcar casilla");
								System.out.println("Pulsa 3: Destapar casilla");
								System.out.println("Pulsa 4: Salir");
								//opcion = sc.nextInt();
								opcion=jugadas[contador][0];
								System.out.println(opcion+"\n");
							}
							if(opcion>0 || opcion <5) {
								switch(opcion) {
									case 1: 
										while(!posValida) {
											System.out.println("Elige una fila:");
											posX = jugadas[contador][1];
											System.out.println(posX+"\n");
											System.out.println("Elige una columna:");
											posY = jugadas[contador][2];
											System.out.println(posY+"\n");
											posValida = partida.posCorrecta(posX-1, posY-1);
											if(contador==0)		//En la primera jugada abrimos una casilla fuera de tablero
												assertEquals(posValida,false);
											else
												assertEquals(posValida,true);
											if(!posValida) {
												System.out.println("Numeros incorrectos\n");
											}
											contador++;
										}
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											partida.marcarCasilla(posX-1, posY-1);
											assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
										}
										else {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.marcarCasilla(posX-1, posY-1);
											if(partida.getCasilla(posX-1, posY-1).getMina()==true)
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
											else
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
										}
										break;
									case 2:
										while(!posValida) {
											System.out.println("Elige una fila:");
											posX = jugadas[contador][1];
											System.out.println(posX+"\n");
											System.out.println("Elige una columna:");
											posY = jugadas[contador][2];
											System.out.println(posY+"\n");
											posValida = partida.posCorrecta(posX-1, posY-1);
											if(contador==0)		//En la primera jugada abrimos una casilla fuera de tablero
												assertEquals(posValida,false);
											else
												assertEquals(posValida,true);
											if(!posValida) {
												System.out.println("Numeros incorrectos");
											}
											contador++;
										}
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											partida.desmarcarCasilla(posX-1, posY-1);
											assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
										}
										else {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.desmarcarCasilla(posX-1, posY-1);
											if(partida.getCasilla(posX-1, posY-1).getMina()==true)
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
											else
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
										}	
										break;
									case 3:
										while(!posValida) {
											System.out.println("Elige una fila:");
											posX = jugadas[contador][1];
											System.out.println(posX+"\n");
											System.out.println("Elige una columna:");
											posY = jugadas[contador][2];
											System.out.println(posY+"\n");
											posValida = partida.posCorrecta(posX-1, posY-1);
											if(contador==0)		//En la primera jugada abrimos una casilla fuera de tablero
												assertEquals(posValida,false);
											else
												assertEquals(posValida,true);
											if(!posValida) {
												System.out.println("Numeros incorrectos");
											}
											contador++;
										}
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											partida.destaparCasilla(posX-1, posY-1);
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
										}
										else {		
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.destaparCasilla(posX-1, posY-1);
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
										}
										if(!partida.getEnJuego()) {
											System.out.println("------------");
											System.out.println("Has perdido!");
											System.out.println("------------\n");
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
						//nom = sc.next();
						String x=mockmain.pasarNombre(num_partida);
						nom=x;
						//nom=mockmain.pasarNombre(num_partida);
						//nom="Otaku";
						Puntuaciones puntuacion = new Puntuaciones(nom, punts, partida.getNivel());
						//escrivim puntuacions i mostrem per nivell
						puntuacion.escribirPuntuaciones(punts,partida.getNivel(),nom);
						puntuacion.mostrar_Puntuaciones(partida.getNivel());
						entrada = false;
						System.out.println("\n");
					}
					sc.close();
				}catch(InputMismatchException ex) {
					System.out.println("Error: Introduce un caracter valido");
					System.out.println();
					sc.next();
				}
			}
			System.out.println("----- FINAL PARTIDA 1 Mala suerte -----\n" + "\n");
	}	

	//Test donde abriremos la misma casilla 2 veces, marcaremos una bomba y luego abriremos una bomba
	@Test
	void testPartida2() throws IOException {
		
		//Creamos mockObject del main
		MainAuxMock mockmain = new MainAuxMock();
		int num_partida=1;
		
			System.out.println("----- INICIO PARTIDA 2 Partida Nivel 1 Perdida -----\n");
			
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
							System.out.println("Pulsa 5: Salir\n");
							//Le pasamos un nivel dependiendo de la partida
							opcion = mockmain.pasarNivel(num_partida);
							System.out.println("Nivel seleccionado: " + opcion + "\n");
						}		
						switch(opcion) {
							case 1:
							case 2:
							case 3:
							case 4:
								partida.setNivel(opcion);
								assertEquals(partida.getNivel(),opcion);
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
					//Creamos nuestro array de jugadas y su contador
					int [][]jugadas=mockmain.pasarJugada(num_partida);
					int contador=0;
					while (partida.getEnJuego()) {
						try {
							opcion = 0;
							while (opcion < 1 || opcion > 4){
								System.out.println("Que quieres hacer? :");
								System.out.println("Pulsa 1: Marcar casilla");
								System.out.println("Pulsa 2: Desmarcar casilla");
								System.out.println("Pulsa 3: Destapar casilla");
								System.out.println("Pulsa 4: Salir");
								//opcion = sc.nextInt();
								opcion=jugadas[contador][0];
								System.out.println(opcion+"\n");
							}
							if(opcion>0 || opcion <5) {
								switch(opcion) {
									case 1: 
										while(!posValida) {
											System.out.println("Elige una fila:");
											posX = jugadas[contador][1];
											System.out.println(posX+"\n");
											System.out.println("Elige una columna:");
											posY = jugadas[contador][2];
											System.out.println(posY+"\n");
											posValida = partida.posCorrecta(posX-1, posY-1);
											if(!posValida) {
												System.out.println("Numeros incorrectos\n");
											}
										}
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											partida.marcarCasilla(posX-1, posY-1);
											assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
										}
										else {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.marcarCasilla(posX-1, posY-1);
											if(partida.getCasilla(posX-1, posY-1).getMina()==true)
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
											else
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
										}
										break;
									case 2:
										while(!posValida) {
											System.out.println("Elige una fila:");
											posX = jugadas[contador][1];
											System.out.println(posX+"\n");
											System.out.println("Elige una columna:");
											posY = jugadas[contador][2];
											System.out.println(posY+"\n");
											posValida = partida.posCorrecta(posX-1, posY-1);
											if(!posValida) {
												System.out.println("Numeros incorrectos");
											}
										}
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											partida.desmarcarCasilla(posX-1, posY-1);
											assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
										}
										else {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.desmarcarCasilla(posX-1, posY-1);
											if(partida.getCasilla(posX-1, posY-1).getMina()==true)
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
											else
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
										}
										break;
									case 3:
										while(!posValida) {
											System.out.println("Elige una fila:");
											posX = jugadas[contador][1];
											System.out.println(posX+"\n");
											System.out.println("Elige una columna:");
											posY = jugadas[contador][2];
											System.out.println(posY+"\n");
											posValida = partida.posCorrecta(posX-1, posY-1);
											if(!posValida) {
												System.out.println("Numeros incorrectos");
											}
										}
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											partida.destaparCasilla(posX-1, posY-1);
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
										}
										else {		
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.destaparCasilla(posX-1, posY-1);
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
										}
										if(!partida.getEnJuego()) {
											System.out.println("------------");
											System.out.println("Has perdido!");
											System.out.println("------------\n");
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
	
	
						contador++;
					}
					if (partida.getPuntuacion()>0) {
						System.out.println("Nom del jugador?\n");
						int punts=partida.getPuntuacion();
						String nom = null; 
						//nom = sc.next();
						String x=mockmain.pasarNombre(num_partida);
						nom=x;
						//nom=mockmain.pasarNombre(num_partida);
						//nom="Otaku";
						Puntuaciones puntuacion = new Puntuaciones(nom, punts, partida.getNivel());
						//escrivim puntuacions i mostrem per nivell
						puntuacion.escribirPuntuaciones(punts,partida.getNivel(),nom);
						puntuacion.mostrar_Puntuaciones(partida.getNivel());
						entrada = false;
						System.out.println("\n");
					}
					sc.close();
				}catch(InputMismatchException ex) {
					System.out.println("Error: Introduce un caracter valido");
					System.out.println();
					sc.next();
				}
			}
			System.out.println("----- FINAL PARTIDA 2 Partida Nivel 1 Perdida -----\n" + "\n");
	}
	
	//Test donde ganamos una partida en la que marcamos, intentamos desmarcar y abrimos lo necesario para ganar
	@Test
	void testPartida3() throws IOException {
		
		//Creamos mockObject del main
		MainAuxMock mockmain = new MainAuxMock();
		int num_partida=2;
		
			System.out.println("----- INICIO PARTIDA 3 Partida Nivel 1 completa ganada -----\n");
			
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
							System.out.println("Pulsa 5: Salir\n");
							//Le pasamos un nivel dependiendo de la partida
							opcion = mockmain.pasarNivel(num_partida);
							System.out.println("Nivel seleccionado: " + opcion + "\n");
						}		
						switch(opcion) {
							case 1:
							case 2:
							case 3:
							case 4:
								partida.setNivel(opcion);
								assertEquals(partida.getNivel(),opcion);
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
					//Creamos nuestro array de jugadas y su contador
					int [][]jugadas=mockmain.pasarJugada(num_partida);
					int contador=0;
					while (partida.getEnJuego()) {
						try {
							opcion = 0;
							while (opcion < 1 || opcion > 4){
								System.out.println("Que quieres hacer? :");
								System.out.println("Pulsa 1: Marcar casilla");
								System.out.println("Pulsa 2: Desmarcar casilla");
								System.out.println("Pulsa 3: Destapar casilla");
								System.out.println("Pulsa 4: Salir");
								//opcion = sc.nextInt();
								opcion=jugadas[contador][0];
								System.out.println(opcion+"\n");
							}
							if(opcion>0 || opcion <5) {
								switch(opcion) {
									case 1: 
										while(!posValida) {
											System.out.println("Elige una fila:");
											posX = jugadas[contador][1];
											System.out.println(posX+"\n");
											System.out.println("Elige una columna:");
											posY = jugadas[contador][2];
											System.out.println(posY+"\n");
											posValida = partida.posCorrecta(posX-1, posY-1);
											if(!posValida) {
												System.out.println("Numeros incorrectos\n");
											}
										}
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											partida.marcarCasilla(posX-1, posY-1);
											assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
										}
										else {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.marcarCasilla(posX-1, posY-1);
											if(partida.getCasilla(posX-1, posY-1).getMina()==true)
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
											else
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
										}
										break;
									case 2:
										while(!posValida) {
											System.out.println("Elige una fila:");
											posX = jugadas[contador][1];
											System.out.println(posX+"\n");
											System.out.println("Elige una columna:");
											posY = jugadas[contador][2];
											System.out.println(posY+"\n");
											posValida = partida.posCorrecta(posX-1, posY-1);
											if(!posValida) {
												System.out.println("Numeros incorrectos");
											}
										}	
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											partida.desmarcarCasilla(posX-1, posY-1);
											assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
										}
										else {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.desmarcarCasilla(posX-1, posY-1);
											if(partida.getCasilla(posX-1, posY-1).getMina()==true)
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
											else
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
										}															
										break;
									case 3:
										while(!posValida) {
											System.out.println("Elige una fila:");
											posX = jugadas[contador][1];
											System.out.println(posX+"\n");
											System.out.println("Elige una columna:");
											posY = jugadas[contador][2];
											System.out.println(posY+"\n");
											posValida = partida.posCorrecta(posX-1, posY-1);
											if(!posValida) {
												System.out.println("Numeros incorrectos");
											}
										}
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											partida.destaparCasilla(posX-1, posY-1);
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
										}
										else {		
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.destaparCasilla(posX-1, posY-1);
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
										}
										if(!partida.getEnJuego()) {
											System.out.println("------------");
											System.out.println("Has perdido!");
											System.out.println("------------\n");
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
	
	
						contador++;
					}
					if (partida.getPuntuacion()>0) {
						System.out.println("Nom del jugador?\n");
						int punts=partida.getPuntuacion();
						String nom = null; 
						//nom = sc.next();
						String x=mockmain.pasarNombre(num_partida);
						nom=x;
						//nom=mockmain.pasarNombre(num_partida);
						//nom="Otaku";
						Puntuaciones puntuacion = new Puntuaciones(nom, punts, partida.getNivel());
						//escrivim puntuacions i mostrem per nivell
						puntuacion.escribirPuntuaciones(punts,partida.getNivel(),nom);
						puntuacion.mostrar_Puntuaciones(partida.getNivel());
						entrada = false;
						System.out.println("\n");
					}
					sc.close();
				}catch(InputMismatchException ex) {
					System.out.println("Error: Introduce un caracter valido");
					System.out.println();
					sc.next();
				}
			}
			System.out.println("----- FINAL PARTIDA 3 Partida Nivel 1 completa ganada -----\n" + "\n");
	}
	
	//Test donde salimos del juego sin haberlo empezado
	@Test
	void testPartida4() throws IOException {
		
		//Creamos mockObject del main
			MainAuxMock mockmain = new MainAuxMock();
			int num_partida=3;
			
				System.out.println("----- INICIO PARTIDA 4 Partida sin empezar -----\n");
				
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
								System.out.println("Pulsa 5: Salir\n");
								//Le pasamos un nivel dependiendo de la partida
								opcion = mockmain.pasarNivel(num_partida);
								System.out.println("Nivel seleccionado: " + opcion + "\n");
							}		
							switch(opcion) {
								case 1:
								case 2:
								case 3:
								case 4:
									partida.setNivel(opcion);
									assertEquals(partida.getNivel(),opcion);
									partida.mostrar();
									seguir = 0;
									break;
								case 5:
									System.out.println("Seguro que quieres salir? (S/N)\n");
									salir = "S";
									seguir = partida.continuarJuego(salir);
									if(seguir == 0) {
										System.out.println("Vuelve pronto!");
										partida.setEnJuego(false);
										assertEquals(partida.getEnJuego(),false);
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
						//Creamos nuestro array de jugadas y su contador
						int [][]jugadas=mockmain.pasarJugada(num_partida);
						int contador=0;
						while (partida.getEnJuego()) {
							try {
								opcion = 0;
								while (opcion < 1 || opcion > 4){
									System.out.println("Que quieres hacer? :");
									System.out.println("Pulsa 1: Marcar casilla");
									System.out.println("Pulsa 2: Desmarcar casilla");
									System.out.println("Pulsa 3: Destapar casilla");
									System.out.println("Pulsa 4: Salir");
									//opcion = sc.nextInt();
									opcion=jugadas[contador][0];
									System.out.println(opcion+"\n");
								}
								if(opcion>0 || opcion <5) {
									switch(opcion) {
										case 1: 
											while(!posValida) {
												System.out.println("Elige una fila:");
												posX = jugadas[contador][1];
												System.out.println(posX+"\n");
												System.out.println("Elige una columna:");
												posY = jugadas[contador][2];
												System.out.println(posY+"\n");
												posValida = partida.posCorrecta(posX-1, posY-1);
												if(!posValida) {
													System.out.println("Numeros incorrectos\n");
												}
											}
											if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
												partida.marcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											}
											else {
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
												partida.marcarCasilla(posX-1, posY-1);
												if(partida.getCasilla(posX-1, posY-1).getMina()==true)
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
												else
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											}	
											break;
										case 2:
											while(!posValida) {
												System.out.println("Elige una fila:");
												posX = jugadas[contador][1];
												System.out.println(posX+"\n");
												System.out.println("Elige una columna:");
												posY = jugadas[contador][2];
												System.out.println(posY+"\n");
												posValida = partida.posCorrecta(posX-1, posY-1);
												if(!posValida) {
													System.out.println("Numeros incorrectos");
												}
											}
											if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
												partida.desmarcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											}
											else {
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
												partida.desmarcarCasilla(posX-1, posY-1);
												if(partida.getCasilla(posX-1, posY-1).getMina()==true)
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
												else
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											}
											break;
										case 3:
											while(!posValida) {
												System.out.println("Elige una fila:");
												posX = jugadas[contador][1];
												System.out.println(posX+"\n");
												System.out.println("Elige una columna:");
												posY = jugadas[contador][2];
												System.out.println(posY+"\n");
												posValida = partida.posCorrecta(posX-1, posY-1);
												if(!posValida) {
													System.out.println("Numeros incorrectos");
												}
											}
											if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
												partida.destaparCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											}
											else {		
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
												partida.destaparCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											}
											if(!partida.getEnJuego()) {
												System.out.println("------------");
												System.out.println("Has perdido!");
												System.out.println("------------\n");
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
		
		
							contador++;
						}
						/*if (partida.getPuntuacion()>0) {
							System.out.println("Nom del jugador?\n");
							int punts=partida.getPuntuacion();
							String nom = null; 
							//nom = sc.next();
							String x=mockmain.pasarNombre(num_partida);
							nom=x;
							//nom=mockmain.pasarNombre(num_partida);
							//nom="Otaku";
							Puntuaciones puntuacion = new Puntuaciones(nom, punts, partida.getNivel());
							//escrivim puntuacions i mostrem per nivell
							puntuacion.escribirPuntuaciones(punts,partida.getNivel(),nom);
							puntuacion.mostrar_Puntuaciones(partida.getNivel());
							entrada = false;
							System.out.println("\n");
						}*/
						sc.close();
					}catch(InputMismatchException ex) {
						System.out.println("Error: Introduce un caracter valido");
						System.out.println();
						sc.next();
					}
				}
				System.out.println("----- FINAL PARTIDA 4 Partida sin empezar  -----\n" + "\n");
	}
	
	//Test donde salimos de la partida a la mitad
	@Test
	void testPartida5() throws IOException {
		
		//Creamos mockObject del main
		MainAuxMock mockmain = new MainAuxMock();
		int num_partida=4;
		
			System.out.println("----- INICIO PARTIDA 5 Partida saliendo a la mitad -----\n");
			
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
							System.out.println("Pulsa 5: Salir\n");
							//Le pasamos un nivel dependiendo de la partida
							opcion = mockmain.pasarNivel(num_partida);
							System.out.println("Nivel seleccionado: " + opcion + "\n");
						}		
						switch(opcion) {
							case 1:
							case 2:
							case 3:
							case 4:
								partida.setNivel(opcion);
								assertEquals(partida.getNivel(),opcion);
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
					//Creamos nuestro array de jugadas y su contador
					int [][]jugadas=mockmain.pasarJugada(num_partida);
					int contador=0;
					while (partida.getEnJuego()) {
						try {
							opcion = 0;
							while (opcion < 1 || opcion > 4){
								System.out.println("Que quieres hacer? :");
								System.out.println("Pulsa 1: Marcar casilla");
								System.out.println("Pulsa 2: Desmarcar casilla");
								System.out.println("Pulsa 3: Destapar casilla");
								System.out.println("Pulsa 4: Salir");
								//opcion = sc.nextInt();
								opcion=jugadas[contador][0];
								System.out.println(opcion+"\n");
							}
							if(opcion>0 || opcion <5) {
								switch(opcion) {
									case 1: 
										while(!posValida) {
											System.out.println("Elige una fila:");
											posX = jugadas[contador][1];
											System.out.println(posX+"\n");
											System.out.println("Elige una columna:");
											posY = jugadas[contador][2];
											System.out.println(posY+"\n");
											posValida = partida.posCorrecta(posX-1, posY-1);
											if(!posValida) {
												System.out.println("Numeros incorrectos\n");
											}
										}
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											partida.marcarCasilla(posX-1, posY-1);
											assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
										}
										else {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.marcarCasilla(posX-1, posY-1);
											if(partida.getCasilla(posX-1, posY-1).getMina()==true)
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
											else
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
										}	
										break;
									case 2:
										while(!posValida) {
											System.out.println("Elige una fila:");
											posX = jugadas[contador][1];
											System.out.println(posX+"\n");
											System.out.println("Elige una columna:");
											posY = jugadas[contador][2];
											System.out.println(posY+"\n");
											posValida = partida.posCorrecta(posX-1, posY-1);
											if(!posValida) {
												System.out.println("Numeros incorrectos");
											}
										}
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											partida.desmarcarCasilla(posX-1, posY-1);
											assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
										}
										else {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.desmarcarCasilla(posX-1, posY-1);
											if(partida.getCasilla(posX-1, posY-1).getMina()==true)
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
											else
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
										}
										break;
									case 3:
										while(!posValida) {
											System.out.println("Elige una fila:");
											posX = jugadas[contador][1];
											System.out.println(posX+"\n");
											System.out.println("Elige una columna:");
											posY = jugadas[contador][2];
											System.out.println(posY+"\n");
											posValida = partida.posCorrecta(posX-1, posY-1);
											if(!posValida) {
												System.out.println("Numeros incorrectos");
											}
										}
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											partida.destaparCasilla(posX-1, posY-1);
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
										}
										else {		
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.destaparCasilla(posX-1, posY-1);
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
										}
										if(!partida.getEnJuego()) {
											System.out.println("------------");
											System.out.println("Has perdido!");
											System.out.println("------------\n");
										}
										else {
											partida.partidaGanada();
										}
										break;
									case 4:
										System.out.println("Seguro que quieres salir? (S/N)");					
										if(contador==2)											
											salir = "N";
										else
											salir="S";
										System.out.println(salir+"\n");
										seguir = partida.continuarJuego(salir);
										if(seguir == 0) {
											partida.setEnJuego(false);
											assertEquals(partida.getEnJuego(),false);
											entrada=false;
										}
										else
											assertEquals(partida.getEnJuego(),true);
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
	
	
						contador++;
					}
					if (partida.getPuntuacion()>0) {
						System.out.println("Nom del jugador?\n");
						int punts=partida.getPuntuacion();
						String nom = null; 
						//nom = sc.next();
						String x=mockmain.pasarNombre(num_partida);
						nom=x;
						//nom=mockmain.pasarNombre(num_partida);
						//nom="Otaku";
						Puntuaciones puntuacion = new Puntuaciones(nom, punts, partida.getNivel());
						//escrivim puntuacions i mostrem per nivell
						puntuacion.escribirPuntuaciones(punts,partida.getNivel(),nom);
						puntuacion.mostrar_Puntuaciones(partida.getNivel());
						entrada = false;
						System.out.println("\n");
					}
					sc.close();
				}catch(InputMismatchException ex) {
					System.out.println("Error: Introduce un caracter valido");
					System.out.println();
					sc.next();
				}
			}
			System.out.println("----- FINAL PARTIDA 5  Partida saliendo a la mitad -----\n" + "\n");
	}
	
	//Test donde ponemos el nivel y la fila/columna incorrectamente y despues ganamos
	@Test
	void testPartida6() throws IOException {
		//Creamos mockObject del main
		MainAuxMock mockmain = new MainAuxMock();
		int num_partida=5;
		
			System.out.println("----- INICIO PARTIDA 6 Partida Nivel mal y luego ganar -----\n");
			
			int opcion = 0, posX = 0, posY = 0, seguir = 1;
			boolean posValida = false, entrada = true;
			String salir;		
			Partida partida = new Partida();
			Scanner sc = new Scanner(System.in);
			int z=0;
			int y=0;
			while(entrada) {
				try {
					while(seguir == 1) {
						while (opcion < 1 || opcion > 5){
							System.out.println("Selecciona un nivel:");
							System.out.println("Pulsa 1: Nivel Facil");
							System.out.println("Pulsa 2: Nivel Medio");
							System.out.println("Pulsa 3: Nivel Dificil");
							System.out.println("Pulsa 4: Nivel Muy Dificil");
							System.out.println("Pulsa 5: Salir\n");
							//Le pasamos un nivel dependiendo de la partida
							if(y==0)
								opcion = 0;
							else
								opcion = mockmain.pasarNivel(num_partida);
							y++;
							System.out.println("Nivel seleccionado: " + opcion + "\n");
						}		
						switch(opcion) {
							case 1:
							case 2:
							case 3:
							case 4:
								partida.setNivel(opcion);
								assertEquals(partida.getNivel(),opcion);
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
					//Creamos nuestro array de jugadas y su contador
					int [][]jugadas=mockmain.pasarJugada(num_partida);
					int contador=0;
					while (partida.getEnJuego()) {
						try {
							opcion = 0;
							while (opcion < 1 || opcion > 4){
								System.out.println("Que quieres hacer? :");
								System.out.println("Pulsa 1: Marcar casilla");
								System.out.println("Pulsa 2: Desmarcar casilla");
								System.out.println("Pulsa 3: Destapar casilla");
								System.out.println("Pulsa 4: Salir");
								//opcion = sc.nextInt();
								opcion=jugadas[contador][0];
								System.out.println(opcion+"\n");
							}
							if(opcion>0 || opcion <5) {
								switch(opcion) {
									case 1: 
										while(!posValida) {
											System.out.println("Elige una fila:");
											posX = jugadas[contador][1];
											System.out.println(posX+"\n");
											System.out.println("Elige una columna:");
											posY = jugadas[contador][2];
											System.out.println(posY+"\n");
											posValida = partida.posCorrecta(posX-1, posY-1);
											if(!posValida) {
												System.out.println("Numeros incorrectos\n");
											}
										}
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											partida.marcarCasilla(posX-1, posY-1);
											assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
										}
										else {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.marcarCasilla(posX-1, posY-1);
											if(partida.getCasilla(posX-1, posY-1).getMina()==true)
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
											else
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
										}	
										break;
									case 2:
										while(!posValida) {
											System.out.println("Elige una fila:");
											posX = jugadas[contador][1];
											System.out.println(posX+"\n");
											System.out.println("Elige una columna:");
											posY = jugadas[contador][2];
											System.out.println(posY+"\n");
											posValida = partida.posCorrecta(posX-1, posY-1);
											if(!posValida) {
												System.out.println("Numeros incorrectos");
											}
										}
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											partida.desmarcarCasilla(posX-1, posY-1);
											assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
										}
										else {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.desmarcarCasilla(posX-1, posY-1);
											if(partida.getCasilla(posX-1, posY-1).getMina()==true)
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
											else
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
										}
										break;
									case 3:
										while(!posValida) {
											System.out.println("Elige una fila:");
											if(z==0)
												posX=100;
											else
												posX = jugadas[contador][1];
											System.out.println(posX+"\n");
											System.out.println("Elige una columna:");
											posY = jugadas[contador][2];
											System.out.println(posY+"\n");
											posValida = partida.posCorrecta(posX-1, posY-1);
											if(!posValida) {
												System.out.println("Numeros incorrectos");
											}
											z++;
										}
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											partida.destaparCasilla(posX-1, posY-1);
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
										}
										else {		
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.destaparCasilla(posX-1, posY-1);
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
										}
										if(!partida.getEnJuego()) {
											System.out.println("------------");
											System.out.println("Has perdido!");
											System.out.println("------------\n");
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
	
	
						contador++;
					}
					if (partida.getPuntuacion()>0) {
						System.out.println("Nom del jugador?\n");
						int punts=partida.getPuntuacion();
						String nom = null; 
						//nom = sc.next();
						String x=mockmain.pasarNombre(num_partida);
						nom=x;
						//nom=mockmain.pasarNombre(num_partida);
						//nom="Otaku";
						Puntuaciones puntuacion = new Puntuaciones(nom, punts, partida.getNivel());
						//escrivim puntuacions i mostrem per nivell
						puntuacion.escribirPuntuaciones(punts,partida.getNivel(),nom);
						puntuacion.mostrar_Puntuaciones(partida.getNivel());
						entrada = false;
						System.out.println("\n");
					}
					sc.close();
				}catch(InputMismatchException ex) {
					System.out.println("Error: Introduce un caracter valido");
					System.out.println();
					sc.next();
				}
			}
			System.out.println("----- FINAL PARTIDA 6 Partida Nivel mal y luego ganar -----\n" + "\n");
		
	}
	
	//Test donde perdemos una partida de nivel 2
	@Test
	void testPartida7() throws IOException {
		//Creamos mockObject del main
		MainAuxMock mockmain = new MainAuxMock();
		int num_partida=6;
		
			System.out.println("----- INICIO PARTIDA 7 Partida Nivel 2 perdida -----\n");
			
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
							System.out.println("Pulsa 5: Salir\n");
							//Le pasamos un nivel dependiendo de la partida
							opcion = mockmain.pasarNivel(num_partida);
							System.out.println("Nivel seleccionado: " + opcion + "\n");
						}		
						switch(opcion) {
							case 1:
							case 2:
							case 3:
							case 4:
								partida.setNivel(opcion);
								assertEquals(partida.getNivel(),opcion);
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
					//Creamos nuestro array de jugadas y su contador
					int [][]jugadas=mockmain.pasarJugada(num_partida);
					int contador=0;
					while (partida.getEnJuego()) {
						try {
							opcion = 0;
							while (opcion < 1 || opcion > 4){
								System.out.println("Que quieres hacer? :");
								System.out.println("Pulsa 1: Marcar casilla");
								System.out.println("Pulsa 2: Desmarcar casilla");
								System.out.println("Pulsa 3: Destapar casilla");
								System.out.println("Pulsa 4: Salir");
								//opcion = sc.nextInt();
								opcion=jugadas[contador][0];
								System.out.println(opcion+"\n");
							}
							if(opcion>0 || opcion <5) {
								switch(opcion) {
									case 1: 
										while(!posValida) {
											System.out.println("Elige una fila:");
											posX = jugadas[contador][1];
											System.out.println(posX+"\n");
											System.out.println("Elige una columna:");
											posY = jugadas[contador][2];
											System.out.println(posY+"\n");
											posValida = partida.posCorrecta(posX-1, posY-1);
											if(!posValida) {
												System.out.println("Numeros incorrectos\n");
											}
										}
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											partida.marcarCasilla(posX-1, posY-1);
											assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
										}
										else {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.marcarCasilla(posX-1, posY-1);
											if(partida.getCasilla(posX-1, posY-1).getMina()==true)
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
											else
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
										}	
										break;
									case 2:
										while(!posValida) {
											System.out.println("Elige una fila:");
											posX = jugadas[contador][1];
											System.out.println(posX+"\n");
											System.out.println("Elige una columna:");
											posY = jugadas[contador][2];
											System.out.println(posY+"\n");
											posValida = partida.posCorrecta(posX-1, posY-1);
											if(!posValida) {
												System.out.println("Numeros incorrectos");
											}
										}
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											partida.desmarcarCasilla(posX-1, posY-1);
											assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
										}
										else {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.desmarcarCasilla(posX-1, posY-1);
											if(partida.getCasilla(posX-1, posY-1).getMina()==true)
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
											else
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
										}
										break;
									case 3:
										while(!posValida) {
											System.out.println("Elige una fila:");
											posX = jugadas[contador][1];
											System.out.println(posX+"\n");
											System.out.println("Elige una columna:");
											posY = jugadas[contador][2];
											System.out.println(posY+"\n");
											posValida = partida.posCorrecta(posX-1, posY-1);
											if(!posValida) {
												System.out.println("Numeros incorrectos");
											}
										}
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											partida.destaparCasilla(posX-1, posY-1);
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
										}
										else {		
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.destaparCasilla(posX-1, posY-1);
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
										}
										if(!partida.getEnJuego()) {
											System.out.println("------------");
											System.out.println("Has perdido!");
											System.out.println("------------\n");
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
	
	
						contador++;
					}
					if (partida.getPuntuacion()>0) {
						System.out.println("Nom del jugador?\n");
						int punts=partida.getPuntuacion();
						String nom = null; 
						//nom = sc.next();
						String x=mockmain.pasarNombre(num_partida);
						nom=x;
						//nom=mockmain.pasarNombre(num_partida);
						//nom="Otaku";
						Puntuaciones puntuacion = new Puntuaciones(nom, punts, partida.getNivel());
						//escrivim puntuacions i mostrem per nivell
						puntuacion.escribirPuntuaciones(punts,partida.getNivel(),nom);
						puntuacion.mostrar_Puntuaciones(partida.getNivel());
						entrada = false;
						System.out.println("\n");
					}
					sc.close();
				}catch(InputMismatchException ex) {
					System.out.println("Error: Introduce un caracter valido");
					System.out.println();
					sc.next();
				}
			}
			System.out.println("----- FINAL PARTIDA 7 Partida Nivel 2 perdida -----\n" + "\n");
		
	}
	
	//Test donde perdemos una partida de nivel 3
	@Test
	void testPartida8() throws IOException {
		//Creamos mockObject del main
		MainAuxMock mockmain = new MainAuxMock();
		int num_partida=7;
		
			System.out.println("----- INICIO PARTIDA 8 Partida Nivel 3 perdida -----\n");
			
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
							System.out.println("Pulsa 5: Salir\n");
							//Le pasamos un nivel dependiendo de la partida
							opcion = mockmain.pasarNivel(num_partida);
							System.out.println("Nivel seleccionado: " + opcion + "\n");
						}		
						switch(opcion) {
							case 1:
							case 2:
							case 3:
							case 4:
								partida.setNivel(opcion);
								assertEquals(partida.getNivel(),opcion);
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
					//Creamos nuestro array de jugadas y su contador
					int [][]jugadas=mockmain.pasarJugada(num_partida);
					int contador=0;
					while (partida.getEnJuego()) {
						try {
							opcion = 0;
							while (opcion < 1 || opcion > 4){
								System.out.println("Que quieres hacer? :");
								System.out.println("Pulsa 1: Marcar casilla");
								System.out.println("Pulsa 2: Desmarcar casilla");
								System.out.println("Pulsa 3: Destapar casilla");
								System.out.println("Pulsa 4: Salir");
								//opcion = sc.nextInt();
								opcion=jugadas[contador][0];
								System.out.println(opcion+"\n");
							}
							if(opcion>0 || opcion <5) {
								switch(opcion) {
									case 1: 
										while(!posValida) {
											System.out.println("Elige una fila:");
											posX = jugadas[contador][1];
											System.out.println(posX+"\n");
											System.out.println("Elige una columna:");
											posY = jugadas[contador][2];
											System.out.println(posY+"\n");
											posValida = partida.posCorrecta(posX-1, posY-1);
											if(!posValida) {
												System.out.println("Numeros incorrectos\n");
											}
										}
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											partida.marcarCasilla(posX-1, posY-1);
											assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
										}
										else {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.marcarCasilla(posX-1, posY-1);
											if(partida.getCasilla(posX-1, posY-1).getMina()==true)
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
											else
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
										}	
										break;
									case 2:
										while(!posValida) {
											System.out.println("Elige una fila:");
											posX = jugadas[contador][1];
											System.out.println(posX+"\n");
											System.out.println("Elige una columna:");
											posY = jugadas[contador][2];
											System.out.println(posY+"\n");
											posValida = partida.posCorrecta(posX-1, posY-1);
											if(!posValida) {
												System.out.println("Numeros incorrectos");
											}
										}
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											partida.desmarcarCasilla(posX-1, posY-1);
											assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
										}
										else {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.desmarcarCasilla(posX-1, posY-1);
											if(partida.getCasilla(posX-1, posY-1).getMina()==true)
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
											else
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
										}
										break;
									case 3:
										while(!posValida) {
											System.out.println("Elige una fila:");
											posX = jugadas[contador][1];
											System.out.println(posX+"\n");
											System.out.println("Elige una columna:");
											posY = jugadas[contador][2];
											System.out.println(posY+"\n");
											posValida = partida.posCorrecta(posX-1, posY-1);
											if(!posValida) {
												System.out.println("Numeros incorrectos");
											}
										}
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											partida.destaparCasilla(posX-1, posY-1);
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
										}
										else {		
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.destaparCasilla(posX-1, posY-1);
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
										}
										if(!partida.getEnJuego()) {
											System.out.println("------------");
											System.out.println("Has perdido!");
											System.out.println("------------\n");
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
	
	
						contador++;
					}
					if (partida.getPuntuacion()>0) {
						System.out.println("Nom del jugador?\n");
						int punts=partida.getPuntuacion();
						String nom = null; 
						//nom = sc.next();
						String x=mockmain.pasarNombre(num_partida);
						nom=x;
						//nom=mockmain.pasarNombre(num_partida);
						//nom="Otaku";
						Puntuaciones puntuacion = new Puntuaciones(nom, punts, partida.getNivel());
						//escrivim puntuacions i mostrem per nivell
						puntuacion.escribirPuntuaciones(punts,partida.getNivel(),nom);
						puntuacion.mostrar_Puntuaciones(partida.getNivel());
						entrada = false;
						System.out.println("\n");
					}
					sc.close();
				}catch(InputMismatchException ex) {
					System.out.println("Error: Introduce un caracter valido");
					System.out.println();
					sc.next();
				}
			}
			System.out.println("----- FINAL PARTIDA 8 Partida Nivel 3 perdida -----\n" + "\n");
		
	}
	
	//Test donde ganamos una partida nivel 4 solamente abriendo casillas
	@Test
	void testPartida9() throws IOException {
		//Creamos mockObject del main
		MainAuxMock mockmain = new MainAuxMock();
		int num_partida=8;
		
			System.out.println("----- INICIO PARTIDA 9 Partida Nivel 4 ganada -----\n");
			
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
							System.out.println("Pulsa 5: Salir\n");
							//Le pasamos un nivel dependiendo de la partida
							opcion = mockmain.pasarNivel(num_partida);
							System.out.println("Nivel seleccionado: " + opcion + "\n");
						}		
						switch(opcion) {
							case 1:
							case 2:
							case 3:
							case 4:
								partida.setNivel(opcion);
								assertEquals(partida.getNivel(),opcion);
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
					//Creamos nuestro array de jugadas y su contador
					int [][]jugadas=mockmain.pasarJugada(num_partida);
					int contador=0;
					while (partida.getEnJuego()) {
						try {
							opcion = 0;
							while (opcion < 1 || opcion > 4){
								System.out.println("Que quieres hacer? :");
								System.out.println("Pulsa 1: Marcar casilla");
								System.out.println("Pulsa 2: Desmarcar casilla");
								System.out.println("Pulsa 3: Destapar casilla");
								System.out.println("Pulsa 4: Salir");
								//opcion = sc.nextInt();
								opcion=jugadas[contador][0];
								System.out.println(opcion+"\n");
							}
							if(opcion>0 || opcion <5) {
								switch(opcion) {
									case 1: 
										while(!posValida) {
											System.out.println("Elige una fila:");
											posX = jugadas[contador][1];
											System.out.println(posX+"\n");
											System.out.println("Elige una columna:");
											posY = jugadas[contador][2];
											System.out.println(posY+"\n");
											posValida = partida.posCorrecta(posX-1, posY-1);
											if(!posValida) {
												System.out.println("Numeros incorrectos\n");
											}
										}
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											partida.marcarCasilla(posX-1, posY-1);
											assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
										}
										else {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.marcarCasilla(posX-1, posY-1);
											if(partida.getCasilla(posX-1, posY-1).getMina()==true)
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
											else
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
										}	
										break;
									case 2:
										while(!posValida) {
											System.out.println("Elige una fila:");
											posX = jugadas[contador][1];
											System.out.println(posX+"\n");
											System.out.println("Elige una columna:");
											posY = jugadas[contador][2];
											System.out.println(posY+"\n");
											posValida = partida.posCorrecta(posX-1, posY-1);
											if(!posValida) {
												System.out.println("Numeros incorrectos");
											}
										}
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											partida.desmarcarCasilla(posX-1, posY-1);
											assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
										}
										else {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.desmarcarCasilla(posX-1, posY-1);
											if(partida.getCasilla(posX-1, posY-1).getMina()==true)
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
											else
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
										}
										break;
									case 3:
										while(!posValida) {
											System.out.println("Elige una fila:");
											posX = jugadas[contador][1];
											System.out.println(posX+"\n");
											System.out.println("Elige una columna:");
											posY = jugadas[contador][2];
											System.out.println(posY+"\n");
											posValida = partida.posCorrecta(posX-1, posY-1);
											if(!posValida) {
												System.out.println("Numeros incorrectos");
											}
										}
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											partida.destaparCasilla(posX-1, posY-1);
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
										}
										else {		
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.destaparCasilla(posX-1, posY-1);
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
										}
										if(!partida.getEnJuego()) {
											System.out.println("------------");
											System.out.println("Has perdido!");
											System.out.println("------------\n");
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
	
	
						contador++;
					}
					if (partida.getPuntuacion()>0) {
						System.out.println("Nom del jugador?\n");
						int punts=partida.getPuntuacion();
						String nom = null; 
						//nom = sc.next();
						String x=mockmain.pasarNombre(num_partida);
						nom=x;
						//nom=mockmain.pasarNombre(num_partida);
						//nom="Otaku";
						Puntuaciones puntuacion = new Puntuaciones(nom, punts, partida.getNivel());
						//escrivim puntuacions i mostrem per nivell
						puntuacion.escribirPuntuaciones(punts,partida.getNivel(),nom);
						puntuacion.mostrar_Puntuaciones(partida.getNivel());
						entrada = false;
						System.out.println("\n");
					}
					sc.close();
				}catch(InputMismatchException ex) {
					System.out.println("Error: Introduce un caracter valido");
					System.out.println();
					sc.next();
				}
			}
			System.out.println("----- FINAL PARTIDA 9 Partida Nivel 4 ganada -----\n" + "\n");
		
	}	
	
	
	
}
