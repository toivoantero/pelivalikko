package backendkurssi.pelivalikko.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Ammatti {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long ammattiid;
	private String nimike;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ammatti")
	private List<Hahmo> hahmot;
	
	public Ammatti() {}
	
	public Ammatti(String nimike) {
		super();
		this.nimike = nimike;
	}

	public Long getAmmattiid() {
		return ammattiid;
	}
	public void setAmmattiid(Long ammattiid) {
		this.ammattiid = ammattiid;
	}
	public String getNimike() {
		return nimike;
	}
	public void setNimike(String nimike) {
		this.nimike = nimike;
	}
	public List<Hahmo> getHahmot() {
		return hahmot;
	}
	public void setHahmot(List<Hahmo> hahmot) {
		this.hahmot = hahmot;
	}

	@Override
	public String toString() {
		return "Ammatti [ammattiid=" + ammattiid + ", nimike=" + nimike + "]";
	}
}
