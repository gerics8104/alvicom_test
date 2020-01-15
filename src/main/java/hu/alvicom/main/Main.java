package hu.alvicom.main;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import hu.alvicom.bevitel.Controller;
import hu.alvicom.szamla.Penznemek;
import hu.alvicom.szamla.Szamla;

public class Main {

	public static List<Szamla> szamlak;

	public static void main(String[] args) {
		szamlak = new ArrayList<>();
		szamlak.add(new Szamla("11111111-22222222", Penznemek.HUF, new BigDecimal(150000)));
		szamlak.add(new Szamla("22222222-33333333", Penznemek.USD, new BigDecimal(1230)));
		Controller controller = new Controller();
		controller.start();
	}
}
