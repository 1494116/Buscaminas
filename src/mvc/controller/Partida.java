package mvc.controller;
import mvc.model.Casilla;
import mvc.model.Tablero;

public class Partida {
	
	public Tablero tablero;
	public boolean enJuego;
	private int nivel;
	
	public Partida() {
		enJuego = true;
	}
	
	public Casilla getCasilla(int x, int y) {
		Casilla c = tablero.getCasilla(x,y);
		return c;	
	}
	
	public int continuarJuego(String input) {
		switch (input.toLowerCase()) {
			case "s": return 0;
			case "n": return 1;
			default: return -1;
		}
	}
	
	public boolean getEnJuego() {
		return enJuego;
	}
	
	public void setEnJuego(boolean enJuego) {
		this.enJuego = enJuego;
	}
	
	public void setNivel(int nivel,int partida) {
		int p = partida;
		if(nivel > 4) {
			this.nivel = 4;
		}else if(nivel<1) {
			this.nivel = 1;
		}else {
			this.nivel = nivel;
		}
		tablero = new Tablero(this.nivel,p);
	}
	
	public int getNivel() {
		return this.nivel;
	}
	

	
	public void destaparCasilla(int posX, int posY) {
		setEnJuego(tablero.destaparCasilla(posX, posY));
	}
	
	public boolean posCorrecta(int posX, int posY) {
		return tablero.posCorrecta(posX, posY);
	}
	
	public void marcarCasilla(int posX, int posY) {
		tablero.marcarCasilla(posX, posY);
	}
	
	public void desmarcarCasilla(int posX, int posY) {
		tablero.desmarcarCasilla(posX, posY);
	}
	
	public void mostrar() {
		Casilla[][] auxtablero = tablero.getTablero();
		for(int i=0;i<tablero.getMedida();i++){
    		for(int j=0;j<tablero.getMedida();j++){
    			if(auxtablero[i][j].getAbierta() && !auxtablero[i][j].getMina()){
    				System.out.print(" "+auxtablero[i][j].getValor()+" ");
    			} else {
	    			if(!auxtablero[i][j].getAbierta()){
	    				if(auxtablero[i][j].getMarcado()) {
    						System.out.print(" B ");
    					} else {
    						System.out.print("[ ]");
    					}
	    			}else{
	    				System.out.print(" "+auxtablero[i][j].getValor()+" ");
	    			}
    			}
    		}
    		System.out.println("\n");
    	}
	}
	public int getPuntuacion() {
		return tablero.getCasillasDestapadas();
	}
	public void partidaGanada() {

		if (enJuego) {
			if (tablero.getCasillasDestapadas() - tablero.getMinasDetectadas() == 
					tablero.getMedida() * tablero.getMedida() - tablero.getBombas() || 
					tablero.getMinasDetectadas() == tablero.getBombas()) {
				enJuego = false;
			}
			if (!enJuego) {
				System.out.println("Has ganado!");
			}
		}
	}
}
