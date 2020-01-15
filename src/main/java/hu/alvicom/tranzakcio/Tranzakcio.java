package hu.alvicom.tranzakcio;

public class Tranzakcio {
	private String szamlaszam;
	private String penznem;
	private Integer osszeg;
	private Double valutaArfolyam;
	
	public String getSzamlaszam() {
		return szamlaszam;
	}
	public void setSzamlaszam(String szamlaszam) {
		this.szamlaszam = szamlaszam;
	}
	public String getPenznem() {
		return penznem;
	}
	public void setPenznem(String penznem) {
		this.penznem = penznem;
	}
	public Integer getOsszeg() {
		return osszeg;
	}
	public void setOsszeg(Integer osszeg) {
		this.osszeg = osszeg;
	}
	public Double getValutaArfolyam() {
		return valutaArfolyam;
	}
	public void setValutaArfolyam(Double valutaArfolyam) {
		this.valutaArfolyam = valutaArfolyam;
	}
	
	
}
