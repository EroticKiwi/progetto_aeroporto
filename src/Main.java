import controller.DataController;

public class Main {

	public static void main(String[] args) {
		DataController.getInstance().inserisciAeroporto("Napoli", "Italia", 1);
		DataController.getInstance().trovaTuttiAeroporti();
	}
}
