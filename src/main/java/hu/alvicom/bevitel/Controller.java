package hu.alvicom.bevitel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import hu.alvicom.kivetel.MyException;
import hu.alvicom.main.Main;
import hu.alvicom.szamla.Szamla;
import hu.alvicom.tranzakcio.Tranzakcio;

public class Controller extends Thread {

	private ObjectMapper mapper;
	private final char EXIT = 'e';
	private final char INPUT = 't';
	private int countrLogLimit = 10;
	private int counter = 0;

	public void run() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		char key = ' ';
		do {
			try {
				System.out.println("parancsok: (t {..}  = tranzakció végrehajtása, e = kilepes)");
				line = reader.readLine();
				key = processInput(line);
			} catch (MyException m) {
				System.out.println(m.getMessage());
			} catch (IOException e) {
				System.out.println("Beviteli hiba tortent!");
			}
		} while (EXIT != key);
	}

	private char processInput(String input) throws JsonParseException, JsonMappingException, IOException, MyException {
		char key = ' ';
		if (!input.isEmpty()) {
			key = input.charAt(0);
			switch (key) {
			case INPUT:
				String tranzakcioJson = input.substring(2, input.length());
				Tranzakcio tr = mapper.readValue(tranzakcioJson, Tranzakcio.class);
				findSzamlaBySzamlaszam(tr, tranzakcioJson);
				manageRiportok();
				break;
			case EXIT:
				System.out.println("Kilepes...");
				break;

			default:
				throw new MyException("nem imsert parancs!");
			}
		}
		return key;
	}

	private void findSzamlaBySzamlaszam(Tranzakcio tranzakcio, String tranzakcioJson) throws MyException {
		List<Szamla> szamlak = Main.szamlak;
		Szamla szamla = szamlak.stream().filter(sz -> tranzakcio.getSzamlaszam().equals(sz.getSzamlaszam())).findAny()
				.orElse(null);
		if (null != szamla) {
			szamla.tranzakcioFeldolgozas(tranzakcio, tranzakcioJson);
		} else {
			throw new MyException("Nem letezik ilyen szmalaszamu szamla! ( \" + tranzakcio.getSzamlaszam() + \" ) ");
		}
	}

	private void manageRiportok() {
		if (++counter == countrLogLimit) {
			counter = 0;
			System.out.println("Szamla report: ");
			List<Szamla> szamlak = Main.szamlak;
			szamlak.forEach(szamla -> {
				System.out.println(szamla);
			});
			System.out.println("---------------------------Riport Vege----------------------------------");
		}
	}

	public Controller() {
		mapper = new ObjectMapper();
	}
}
