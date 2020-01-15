package hu.alvicom.tranzakcio;

import java.math.BigDecimal;

public class TransactionLog {
	private Tranzakcio tranzakcio;
	private String tranzakcioJson;
	private BigDecimal aktualisEgyenleg;

	public TransactionLog(Tranzakcio tranzakcio, String tranzakcioJson, BigDecimal egyenleg) {
		this.tranzakcio = tranzakcio;
		this.tranzakcioJson = tranzakcioJson;
		this.aktualisEgyenleg = egyenleg;
	}

	public Tranzakcio getTranzakcio() {
		return tranzakcio;
	}

	public void setTranzakcio(Tranzakcio tranzakcio) {
		this.tranzakcio = tranzakcio;
	}

	public String getTranzakcioJson() {
		return tranzakcioJson;
	}

	public void setTranzakcioJson(String tranzakcioJson) {
		this.tranzakcioJson = tranzakcioJson;
	}

	public BigDecimal getAktualisEgyenleg() {
		return aktualisEgyenleg;
	}

	public void setAktualisEgyenleg(BigDecimal aktualisEgyenleg) {
		this.aktualisEgyenleg = aktualisEgyenleg;
	}
	
	

}
