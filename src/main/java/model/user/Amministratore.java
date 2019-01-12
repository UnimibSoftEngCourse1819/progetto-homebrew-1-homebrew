package model.user;

import java.util.Date;

public class Amministratore implements Utente {

	private int id;
	private String nome;
	private String cognome;
	private Date dataDiNascita;
	private String email;
	private String password;
	private String tipo;


	public Amministratore(int id, String nome, String cognome, Date dataDiNascita, String email, String password,
			String tipo) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.email = email;
		this.password = password;
		this.tipo = tipo;
	}

	public Amministratore(String nome, String cognome) {
		super();
		this.id = -1;
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = new Date();
		this.email = "ciao";
		this.password = "miao";
		this.tipo = "Birraio";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Utente [id=" + id + ", nome=" + nome + ", cognome=" + cognome + "]";
	}



}
