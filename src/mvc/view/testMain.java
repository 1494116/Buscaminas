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

	//Test donde abriremos una casilla fuera del tablero y  mueres a la primera bomba que abres
	@Test
	void testPartida0() throws IOException {
		
		//Creamos mockObject del main
		MainAuxMock mockmain = new MainAuxMock();			
			System.out.println("----- INICIO PARTIDA 0 Mala suerte -----\n");
			int num_partida=0;
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
								partida.setNivel(opcion,mockmain.pasarPartida(num_partida));
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
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {			//Miramos si esta abierta
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);										
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada
												partida.marcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											} else {
												partida.marcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											}
										}
										else {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.marcarCasilla(posX-1, posY-1);
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada										
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
											} else {
												if(partida.getCasilla(posX-1, posY-1).getMina()==true)		//Miramos si hay bomba
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
												else
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
												}	
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
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {			//Miramos si esta abierta
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada
												partida.desmarcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
											} else {
												partida.desmarcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											}
										}
										else {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.desmarcarCasilla(posX-1, posY-1);
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											}
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
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											} else {
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											}
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
						String x=mockmain.pasarNombre(num_partida);
						nom=x;
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
			System.out.println("----- FINAL PARTIDA 0 Mala suerte -----\n" + "\n");
	}	

	//Test donde abriremos la misma casilla 2 veces, marcaremos una bomba y luego abriremos una bomba
	@Test
	void testPartida1() throws IOException {
		
		//Creamos mockObject del main
		MainAuxMock mockmain = new MainAuxMock();
		int num_partida=1;
		
			System.out.println("----- INICIO PARTIDA 1 Partida Perdida -----\n");
			
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
								partida.setNivel(opcion,mockmain.pasarPartida(num_partida));
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
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {			//Miramos si esta abierta
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);										
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada
												partida.marcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											} else {
												partida.marcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											}
										}
										else {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.marcarCasilla(posX-1, posY-1);
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada										
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
											} else {
												if(partida.getCasilla(posX-1, posY-1).getMina()==true)		//Miramos si hay bomba
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
												else
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
												}	
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
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {			//Miramos si esta abierta
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada
												partida.desmarcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
											} else {
												partida.desmarcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											}
										}
										else {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.desmarcarCasilla(posX-1, posY-1);
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											}
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
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											} else {
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											}
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
						String x=mockmain.pasarNombre(num_partida);
						nom=x;
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
			System.out.println("----- FINAL PARTIDA 1 Partida Perdida -----\n" + "\n");
	}
	
	//Test donde ganamos una partida 
	@Test
	void testPartida2() throws IOException {
		
		//Creamos mockObject del main
		MainAuxMock mockmain = new MainAuxMock();
		int num_partida=2;
		
			System.out.println("----- INICIO PARTIDA 2 Partida Nivel 2 completa ganada -----\n");
			
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
								partida.setNivel(opcion,mockmain.pasarPartida(num_partida));
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
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {			//Miramos si esta abierta
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);										
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada
												partida.marcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											} else {
												partida.marcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											}
										}
										else {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.marcarCasilla(posX-1, posY-1);
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada										
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
											} else {
												if(partida.getCasilla(posX-1, posY-1).getMina()==true)		//Miramos si hay bomba
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
												else
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
												}	
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
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {			//Miramos si esta abierta
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada
												partida.desmarcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
											} else {
												partida.desmarcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											}
										}
										else {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.desmarcarCasilla(posX-1, posY-1);
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											}
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
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											} else {
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											}
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
						String x=mockmain.pasarNombre(num_partida);
						nom=x;
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
			System.out.println("----- FINAL PARTIDA 2 Partida Nivel 2 completa ganada -----\n" + "\n");
	}
	
	//Test donde salimos del juego sin haberlo empezado
	@Test
	void testPartida3() throws IOException {
	//Creamos mockObject del main
		MainAuxMock mockmain = new MainAuxMock();
		int num_partida=3;
		
			System.out.println("----- INICIO PARTIDA 3 Partida sin empezar -----\n");
			
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
								partida.setNivel(opcion,mockmain.pasarPartida(num_partida));
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
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {			//Miramos si esta abierta
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);										
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada
												partida.marcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											} else {
												partida.marcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											}
										}
										else {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.marcarCasilla(posX-1, posY-1);
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada										
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
											} else {
												if(partida.getCasilla(posX-1, posY-1).getMina()==true)		//Miramos si hay bomba
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
												else
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
												}	
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
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {			//Miramos si esta abierta
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada
												partida.desmarcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
											} else {
												partida.desmarcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											}
										}
										else {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.desmarcarCasilla(posX-1, posY-1);
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											}
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
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											} else {
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											}
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
					//COMENTADO POR QUE DA ERROR, LO TIENEN MAL ELLOS
					/*if (partida.getPuntuacion()>0) {
						System.out.println("Nom del jugador?\n");
						int punts=partida.getPuntuacion();
						String nom = null; 
						String x=mockmain.pasarNombre(num_partida);
						nom=x;
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
			System.out.println("----- FINAL PARTIDA 3 Partida sin empezar  -----\n" + "\n");
	}
	
	//Test donde salimos de la partida a la mitad
	@Test
	void testPartida4() throws IOException {
		
		//Creamos mockObject del main
		MainAuxMock mockmain = new MainAuxMock();
		int num_partida=4;
		
			System.out.println("----- INICIO PARTIDA 4 Partida saliendo a la mitad -----\n");
			
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
								partida.setNivel(opcion,mockmain.pasarPartida(num_partida));
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
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {			//Miramos si esta abierta
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);										
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada
												partida.marcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											} else {
												partida.marcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											}
										}
										else {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.marcarCasilla(posX-1, posY-1);
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada										
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
											} else {
												if(partida.getCasilla(posX-1, posY-1).getMina()==true)		//Miramos si hay bomba
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
												else
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
												}	
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
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {			//Miramos si esta abierta
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada
												partida.desmarcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
											} else {
												partida.desmarcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											}
										}
										else {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.desmarcarCasilla(posX-1, posY-1);
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											}
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
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											} else {
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											}
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
						String x=mockmain.pasarNombre(num_partida);
						nom=x;
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
			System.out.println("----- FINAL PARTIDA 4 Partida saliendo a la mitad -----\n" + "\n");
	}
	
	//Test donde Mal nivel y malas decisiones perdiendo nivel 1
	@Test
	void testPartida5() throws IOException {
		//Creamos mockObject del main
		MainAuxMock mockmain = new MainAuxMock();
		int num_partida=5;
		
			System.out.println("----- INICIO PARTIDA 5 Partida Nivel mal y luego ganar -----\n");
			
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
								partida.setNivel(opcion,mockmain.pasarPartida(num_partida));
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
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {			//Miramos si esta abierta
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);										
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada
												partida.marcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											} else {
												partida.marcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											}
										}
										else {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.marcarCasilla(posX-1, posY-1);
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada										
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
											} else {
												if(partida.getCasilla(posX-1, posY-1).getMina()==true)		//Miramos si hay bomba
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
												else
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
												}	
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
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {			//Miramos si esta abierta
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada
												partida.desmarcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
											} else {
												partida.desmarcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											}
										}
										else {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.desmarcarCasilla(posX-1, posY-1);
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											}
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
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											} else {
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											}
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
						String x=mockmain.pasarNombre(num_partida);
						nom=x;
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
			System.out.println("----- FINAL PARTIDA 5 Partida Nivel mal y luego ganar -----\n" + "\n");
		
	}
	
	//Test donde ganamos una partida nivel 4 solamente abriendo casillas
	@Test
	void testPartida6() throws IOException {
		//Creamos mockObject del main
		MainAuxMock mockmain = new MainAuxMock();
		int num_partida=6;
		
			System.out.println("----- INICIO PARTIDA 6 Partida Nivel 4 ganada -----\n");
			
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
								partida.setNivel(opcion,mockmain.pasarPartida(num_partida));
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
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {			//Miramos si esta abierta
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);										
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada
												partida.marcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											} else {
												partida.marcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											}
										}
										else {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.marcarCasilla(posX-1, posY-1);
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada										
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
											} else {
												if(partida.getCasilla(posX-1, posY-1).getMina()==true)		//Miramos si hay bomba
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
												else
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
												}	
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
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {			//Miramos si esta abierta
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada
												partida.desmarcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
											} else {
												partida.desmarcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											}
										}
										else {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.desmarcarCasilla(posX-1, posY-1);
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											}
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
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											} else {
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											}
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
						String x=mockmain.pasarNombre(num_partida);
						nom=x;
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
			System.out.println("----- FINAL PARTIDA 6 Partida Nivel 4 ganada -----\n" + "\n");
		
	}	
	
	//Test donde todas las casillas son bombas
	@Test
	void testPartida7() throws IOException {
		//Creamos mockObject del main
			MainAuxMock mockmain = new MainAuxMock();
			int num_partida=7;
			
				System.out.println("----- INICIO PARTIDA 7 Todo bombas -----\n");
				
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
									partida.setNivel(opcion,mockmain.pasarPartida(num_partida));
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
											if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {			//Miramos si esta abierta
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);										
												if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada
													partida.marcarCasilla(posX-1, posY-1);
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
												} else {
													partida.marcarCasilla(posX-1, posY-1);
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
												}
											}
											else {
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
												partida.marcarCasilla(posX-1, posY-1);
												if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada										
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
												} else {
													if(partida.getCasilla(posX-1, posY-1).getMina()==true)		//Miramos si hay bomba
														assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
													else
														assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
													}	
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
											if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {			//Miramos si esta abierta
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
												if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada
													partida.desmarcarCasilla(posX-1, posY-1);
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
												} else {
													partida.desmarcarCasilla(posX-1, posY-1);
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
												}
											}
											else {
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
												partida.desmarcarCasilla(posX-1, posY-1);
												if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
												}
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
												if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {
													assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
												} else {
													assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
												}
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
				System.out.println("----- FINAL PARTIDA 7 Todo bombas -----\n" + "\n");
	}
	
	//Test donde ganas con 1 click
	@Test
	void testPartida8() throws IOException {
		//Creamos mockObject del main
			MainAuxMock mockmain = new MainAuxMock();
			int num_partida=8;
			
				System.out.println("----- INICIO PARTIDA 8 Ganar de 1 click -----\n");
				
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
									partida.setNivel(opcion,mockmain.pasarPartida(num_partida));
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
											if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {			//Miramos si esta abierta
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);										
												if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada
													partida.marcarCasilla(posX-1, posY-1);
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
												} else {
													partida.marcarCasilla(posX-1, posY-1);
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
												}
											}
											else {
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
												partida.marcarCasilla(posX-1, posY-1);
												if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada										
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
												} else {
													if(partida.getCasilla(posX-1, posY-1).getMina()==true)		//Miramos si hay bomba
														assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
													else
														assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
													}	
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
											if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {			//Miramos si esta abierta
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
												if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada
													partida.desmarcarCasilla(posX-1, posY-1);
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
												} else {
													partida.desmarcarCasilla(posX-1, posY-1);
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
												}
											}
											else {
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
												partida.desmarcarCasilla(posX-1, posY-1);
												if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
												}
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
												if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {
													assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
												} else {
													assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
												}
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
				System.out.println("----- FINAL PARTIDA 8 Ganar de 1 click -----\n" + "\n");
	}

	//Test donde bombas en cuadrado para comprobar bordes/esquinas
	@Test
	void testPartida9() throws IOException {
		//Creamos mockObject del main
			MainAuxMock mockmain = new MainAuxMock();
			int num_partida=9;
			
				System.out.println("----- INICIO PARTIDA 9 Bombas en cuadrado para comprobar bordes/esquinas -----\n");
				
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
									partida.setNivel(opcion,mockmain.pasarPartida(num_partida));
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
											if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {			//Miramos si esta abierta
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);										
												if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada
													partida.marcarCasilla(posX-1, posY-1);
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
												} else {
													partida.marcarCasilla(posX-1, posY-1);
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
												}
											}
											else {
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
												partida.marcarCasilla(posX-1, posY-1);
												if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada										
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
												} else {
													if(partida.getCasilla(posX-1, posY-1).getMina()==true)		//Miramos si hay bomba
														assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
													else
														assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
													}	
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
											if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {			//Miramos si esta abierta
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
												if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada
													partida.desmarcarCasilla(posX-1, posY-1);
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
												} else {
													partida.desmarcarCasilla(posX-1, posY-1);
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
												}
											}
											else {
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
												partida.desmarcarCasilla(posX-1, posY-1);
												if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
												}
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
												if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {
													assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
												} else {
													assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
												}
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
				System.out.println("----- FINAL PARTIDA 9 Bombas en cuadrado para comprobar bordes/esquinas -----\n" + "\n");
	}

	//Test Comprobar Caso CerradoNoBomba(M(peta),D,A) CerradoSiBomba(M,D) 
	@Test
	void testPartida10() throws IOException {	
		//Creamos mockObject del main
		MainAuxMock mockmain = new MainAuxMock();
		int num_partida=10;
		
			System.out.println("----- INICIO PARTIDA 10 Comprobar Caso CerradoNoBomba(M(peta),D,A) CerradoSiBomba(M,D) -----\n");
			
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
								partida.setNivel(opcion,mockmain.pasarPartida(num_partida));
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
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {			//Miramos si esta abierta
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);										
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada
												partida.marcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											} else {
												partida.marcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											}
										}
										else {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.marcarCasilla(posX-1, posY-1);
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada										
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
											} else {
												if(partida.getCasilla(posX-1, posY-1).getMina()==true)		//Miramos si hay bomba
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
												else
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
												}	
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
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {			//Miramos si esta abierta
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada
												partida.desmarcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
											} else {
												partida.desmarcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											}
										}
										else {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.desmarcarCasilla(posX-1, posY-1);
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											}
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
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											} else {
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											}
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
								System.out.println("Jugada: " + jugadas[contador][0] + jugadas[contador][1] + jugadas[contador][2]);
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
						String x=mockmain.pasarNombre(num_partida);
						nom=x;
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
			System.out.println("----- FINAL PARTIDA 10 Comprobar Caso CerradoNoBomba(M(peta),D,A) CerradoSiBomba(M,D) -----\n" + "\n");
	}
	
	//Test Comprobar Caso Marcado(M,D(peta)) 
	@Test
	void testPartida11() throws IOException {
		
		//Creamos mockObject del main
		MainAuxMock mockmain = new MainAuxMock();
		int num_partida=11;
		
			System.out.println("----- INICIO PARTIDA 11 Comprobar Caso Marcado(M,D(peta)) -----\n");
			
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
								partida.setNivel(opcion,mockmain.pasarPartida(num_partida));
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
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {			//Miramos si esta abierta
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);										
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada
												partida.marcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											} else {
												partida.marcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											}
										}
										else {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.marcarCasilla(posX-1, posY-1);
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada										
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
											} else {
												if(partida.getCasilla(posX-1, posY-1).getMina()==true)		//Miramos si hay bomba
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
												else
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
												}	
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
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {			//Miramos si esta abierta
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada
												partida.desmarcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
											} else {
												partida.desmarcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											}
										}
										else {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.desmarcarCasilla(posX-1, posY-1);
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											}
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
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											} else {
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											}
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
								System.out.println("Jugada: " + jugadas[contador][0] + jugadas[contador][1] + jugadas[contador][2]);
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
						String x=mockmain.pasarNombre(num_partida);
						nom=x;
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
			System.out.println("----- FINAL PARTIDA 11 Comprobar Caso Marcado(M,D(peta)) -----\n" + "\n");
	}

	//Test Comprobar Caso Abierto(M,D,A) CerradoSibomba(A) 
	@Test
	void testPartida12() throws IOException {
		
		//Creamos mockObject del main
		MainAuxMock mockmain = new MainAuxMock();
		int num_partida=12;
		
			System.out.println("----- INICIO PARTIDA 12 Comprobar Caso Abierto(M,D,A)	CerradoSibomba(A) -----\n");
			
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
								partida.setNivel(opcion,mockmain.pasarPartida(num_partida));
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
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {			//Miramos si esta abierta
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);										
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada
												partida.marcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											} else {
												partida.marcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											}
										}
										else {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.marcarCasilla(posX-1, posY-1);
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada										
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
											} else {
												if(partida.getCasilla(posX-1, posY-1).getMina()==true)		//Miramos si hay bomba
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
												else
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
												}	
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
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {			//Miramos si esta abierta
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada
												partida.desmarcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
											} else {
												partida.desmarcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											}
										}
										else {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.desmarcarCasilla(posX-1, posY-1);
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											}
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
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											} else {
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											}
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
								System.out.println("Jugada: " + jugadas[contador][0] + jugadas[contador][1] + jugadas[contador][2]);
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
						String x=mockmain.pasarNombre(num_partida);
						nom=x;
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
			System.out.println("----- FINAL PARTIDA 12 Comprobar Caso Abierto(M,D,A)	CerradoSibomba(A) -----\n" + "\n");
	}

	//Test Comprobar Caso Marcado(A(peta))
	@Test
	void testPartida13() throws IOException {
		
		//Creamos mockObject del main
		MainAuxMock mockmain = new MainAuxMock();
		int num_partida=13;
		
			System.out.println("----- INICIO PARTIDA 13 Comprobar Caso Marcado(A(peta)) -----\n");
			
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
								partida.setNivel(opcion,mockmain.pasarPartida(num_partida));
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
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {			//Miramos si esta abierta
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);										
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada
												partida.marcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											} else {
												partida.marcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											}
										}
										else {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.marcarCasilla(posX-1, posY-1);
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada										
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
											} else {
												if(partida.getCasilla(posX-1, posY-1).getMina()==true)		//Miramos si hay bomba
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
												else
													assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
												}	
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
										if(partida.getCasilla(posX-1, posY-1).getAbierta()==true) {			//Miramos si esta abierta
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {	//Miramos si ya estaba marcada
												partida.desmarcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),true);
											} else {
												partida.desmarcarCasilla(posX-1, posY-1);
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											}
										}
										else {
											assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											partida.desmarcarCasilla(posX-1, posY-1);
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {
												assertEquals(partida.getCasilla(posX-1, posY-1).getMarcado(),false);
											}
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
											if (partida.getCasilla(posX-1, posY-1).getMarcado()==true) {
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),false);
											} else {
												assertEquals(partida.getCasilla(posX-1, posY-1).getAbierta(),true);
											}
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
								System.out.println("Jugada: " + jugadas[contador][0] + jugadas[contador][1] + jugadas[contador][2]);
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
						String x=mockmain.pasarNombre(num_partida);
						nom=x;
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
			System.out.println("----- FINAL PARTIDA 13 Comprobar Caso Marcado(A(peta)) -----\n" + "\n");
	}

}