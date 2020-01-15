package hu.alvicom.szamla;

import java.math.BigDecimal;

import hu.alvicom.kivetel.MyException;
import hu.alvicom.tranzakcio.TransactionLog;
import hu.alvicom.tranzakcio.Tranzakcio;

public class Szamla extends SzamlaAbstract {

	public Szamla(String szamlaszam, Penznemek penznem, BigDecimal egyenleg) {
		this.szamlaszam = szamlaszam;
		this.egyenleg = egyenleg;
		this.penznem = penznem;
	}

	@Override
	public void tranzakcioFeldolgozas(Tranzakcio tranzakcio, String tranzakcioJson) throws MyException {
		if (PenznemContains(tranzakcio.getPenznem())) {
			Penznemek penznem = Penznemek.valueOf(tranzakcio.getPenznem());
			if (penznem.equals(this.penznem)) {
				this.egyenleg = this.egyenleg.add(new BigDecimal(tranzakcio.getOsszeg()));
			} else {
				BigDecimal subOsszeg = new BigDecimal(tranzakcio.getOsszeg() * tranzakcio.getValutaArfolyam());
				this.egyenleg = this.egyenleg.add(subOsszeg);
			}
			TransactionLog transactionLog = new TransactionLog(tranzakcio, tranzakcioJson, this.egyenleg);
			this.transactions.add(transactionLog);
			System.out.println("Tranzakcio feldolgozva...");
		} else {
			throw new MyException("Ismeretlen penznem! A tratnzakcio el lett dobva!");
		}
	}
}
