import controller.DataController;

public class Main {

	public static void main(String[] args) {
		//DataController.getInstance().inserisciCliente("Gigi", "Testino", "gigi@gmail.com", "passwords", "parippall");
		DataController.getInstance().inserisciAeroporto("Napoli", "Italia", 1);
		DataController.getInstance().trovaTuttiAeroporti();
	}
}
