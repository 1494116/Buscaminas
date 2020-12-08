package mvc.view;

public class MainAuxMock implements MainAux{

	@Override
	public int pasarNivel(int p) {
		int []nivel= {1,2,3,4,4,4};
		return nivel[p];
	}

}
