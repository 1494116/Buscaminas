
package mvc.view;

public class MainAuxMock implements MainAux{

    @Override
    public int pasarNivel(int p) {
        int []nivel= {1,1,1,1,1,1};
        return nivel[p];
    }

    @Override
    public int[][] pasarJugada(int p) {
        int[][]jugadas = null;
        switch(p) {
        case 0:
            jugadas= new int[][] {{3,5,1},{3,1,5},{3,1,1}};
            break;
        case 1:
            jugadas= new int[][] {{3,1,1}};
            break;
        case 2:
            jugadas= new int[][] {{3,5,1},{3,1,5},{3,1,1}};
            break;
        case 3:
            jugadas= new int[][] {{3,1,1}};
            break;
        case 4:
            jugadas= new int[][] {{3,5,1},{3,1,5},{3,1,1}};
            break;
        case 5:
            jugadas= new int[][] {{3,1,1}};
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
        String [] nombres= {"Prueba1","Prueba2","Prueba3","Prueba4","Prueba5","Prueba6"};
        return nombres[p];
    }

}