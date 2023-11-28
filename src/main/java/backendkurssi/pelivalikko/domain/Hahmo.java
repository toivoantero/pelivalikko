package backendkurssi.pelivalikko.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Hahmo {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	private String nimi;
	private int ika;
	private int kokemuspisteet;
	
	@ManyToOne
    @JoinColumn(name = "ammattiid")
    private Ammatti ammatti;
	
    public Hahmo() {}
	
	public Hahmo(String nimi, int ika, int kokemuspisteet, Ammatti ammatti) {
		super();
		this.nimi = nimi;
		this.ika = ika;
		this.kokemuspisteet = kokemuspisteet;
		this.ammatti = ammatti;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNimi() {
		return nimi;
	}
	public void setNimi(String nimi) {
		this.nimi = nimi;
	}
	public int getIka() {
		return ika;
	}
	public void setIka(int ika) {
		this.ika = ika;
	}
	public int getKokemuspisteet() {
		return kokemuspisteet;
	}
	public void setKokemuspisteet(int kokemuspisteet) {
		this.kokemuspisteet = kokemuspisteet;
	}
	public Ammatti getAmmatti() {
		return ammatti;
	}
	public void setAmmatti(Ammatti ammatti) {
		this.ammatti = ammatti;
	}

	@Override
	public String toString() {
		return "Hahmo [nimi=" + nimi + ", ika=" + ika + ", kokemuspisteet=" + kokemuspisteet + "]";
	}
	
}
