package hu.alvicom.szamla;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import hu.alvicom.kivetel.MyException;
import hu.alvicom.tranzakcio.TransactionLog;
import hu.alvicom.tranzakcio.Tranzakcio;

public abstract class SzamlaAbstract {

	public abstract void tranzakcioFeldolgozas(Tranzakcio tranzakcio, String tranzakcioJson) throws MyException;

	public SzamlaAbstract() {
		transactions = new ArrayList<>();
		egyenleg = new BigDecimal(0);
	}

	protected String szamlaszam;
	protected Penznemek penznem;
	protected BigDecimal egyenleg;
	protected List<TransactionLog> transactions;

	public String getSzamlaszam() {
		return szamlaszam;
	}

	public Penznemek getPenznem() {
		return penznem;
	}

	public BigDecimal getEgyenleg() {
		return egyenleg;
	}

	protected boolean PenznemContains(String test) {
		for (Penznemek pn : Penznemek.values()) {
			if (pn.name().equals(test)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		String newLine = System.getProperty("line.separator");
		StringBuilder builder = new StringBuilder();
		builder.append(szamlaszam + " szamu szamla riport: " + newLine);
		transactions.forEach(trancaction -> {
			builder.append("tranzackios uzenet: " + trancaction.getTranzakcioJson() + newLine);
			builder.append("aktualis egyenleg: " + trancaction.getAktualisEgyenleg() + newLine);
			builder.append("--------------------------------------------------------------------------" + newLine);
		});
		return builder.toString();
	}
}
