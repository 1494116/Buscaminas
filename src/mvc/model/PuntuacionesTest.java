package mvc.model;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;


class PuntuacionesTest {
	
	//Clase per testejar les puntuacions mitjançant el Mock 
	//ja que hem de veure si es capaç  de llegir un archiu encara que no existexi i fer el que pertoca
    @Test
    void testEscrivirPuntuaciones() {
    	
        String ruta = "puntuacionsMock.txt";
        String nombre = "nombre";
        int puntuacio = 100;
        int nivel = 1;
        //PuntuacionesMock mockObject = Mockito.mock(PuntuacionesMock.class);
        PuntuacionesMock mockObject = new PuntuacionesMock(nombre, puntuacio, nivel);
        mockObject.setPuntuacionesMock(nombre, puntuacio, nivel);
        mockObject.escribirPuntuaciones(puntuacio, nivel, nombre);
        try {
	        // L'arxiu ha d'existir
        	File file = new File(ruta);
	        assertTrue(file.exists());
	        
	        BufferedReader br = new BufferedReader(new FileReader(file));
	        // Agafem l'ultima linia afegida
	        String line = br.readLine();
	        String bLine;
	        while ((bLine = br.readLine()) != null) {
	            line = bLine;
	        }
	        br.close();
	        // La separem en cada espai
	        String[] separacions = line.split(" ");
	        
	        // Comprovem que s'ha escrit el que toca
	        assertEquals(nombre, separacions[0]);
	        assertEquals(String.valueOf(puntuacio), separacions[1]);
	        assertEquals(String.valueOf(nivel), separacions[2]);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        mockObject.esborrarMock();
    }
    //Test llegir_puntuacions
    //Per comprovar que les puntuacions son llegides correctament
    @Test
    void testLeerPuntuaciones() {
        String nombre = "nombre";
        int puntuacio = 100;
        int nivel = 1;
        //PuntuacionesMock mockObject = Mockito.mock(PuntuacionesMock.class);
        PuntuacionesMock mockObject = new PuntuacionesMock(nombre, puntuacio, nivel);
        mockObject.setPuntuacionesMock(nombre, puntuacio, nivel);
        mockObject.escribirPuntuaciones(puntuacio, nivel, nombre);
        try {
        	mockObject.leer_Puntuaciones();
			assertEquals(1, mockObject.getPuntuacionesTamano());
		} catch (IOException e) {
			//assertThrows(FileNotFoundException.class, () -> {e.printStackTrace();});
		}
        mockObject.escribirPuntuaciones(puntuacio, nivel, nombre);
        mockObject.escribirPuntuaciones(puntuacio, nivel, nombre);
        try {
        	mockObject.leer_Puntuaciones();
        	System.out.println(mockObject.getPuntuacionesTamano());
			assertEquals(3, mockObject.getPuntuacionesTamano());
		} catch (IOException e) {
			e.printStackTrace();
		}
        //mockObject.esborrarMock();
    }
    //Test mostrar_puntuacions
    //Per comprovar que les puntuacions es mostren correctament (nivel i puntuacio)
    @Test
    void testMostrarPuntuaciones() {
    	String nombre = "nombre1";
        int puntuacion = 100;
        int nivel = 1;
        PuntuacionesMock mockObject = new PuntuacionesMock(nombre, puntuacion, nivel);
        mockObject.setPuntuacionesMock(nombre, puntuacion, nivel);
        mockObject.escribirPuntuaciones(puntuacion, nivel, nombre);
        nombre = "nombre2";
        puntuacion = 200;
        nivel = 2;
        mockObject.escribirPuntuaciones(puntuacion, nivel, nombre);
        try {
        	mockObject.mostrar_Puntuaciones(1);
        	mockObject.mostrar_Puntuaciones(2);
			assertEquals(nombre, mockObject.getPuntuaciones().get(0).getNombre());
			assertEquals(puntuacion, mockObject.getPuntuaciones().get(0).getPuntuacion());
			assertEquals(nivel, mockObject.getPuntuaciones().get(0).getnivel());
		} catch (IOException e) {
			e.printStackTrace();
		}
        mockObject.esborrarMock();
    }
}