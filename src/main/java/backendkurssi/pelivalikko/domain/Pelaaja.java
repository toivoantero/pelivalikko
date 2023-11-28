package backendkurssi.pelivalikko.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="PelaajaTaulu")
public class Pelaaja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    // Pelaajanimi with unique constraint
    @Column(name = "pelaajanimi", nullable = false, unique = true)
    private String pelaajanimi;

    @Column(name = "password", nullable = false)
    private String passwordHash;

    @Column(name = "role", nullable = false)
    private String role;
    
    public Pelaaja() {
    }

	public Pelaaja(String pelaajanimi, String passwordHash, String role) {
		super();
		this.pelaajanimi = pelaajanimi;
		this.passwordHash = passwordHash;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPelaajanimi() {
		return pelaajanimi;
	}

	public void setPelaajanimi(String pelaajanimi) {
		this.pelaajanimi = pelaajanimi;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
