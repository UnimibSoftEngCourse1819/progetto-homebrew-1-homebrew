package model.user;

import java.util.Date;

public interface Utente {

		public int getId();

		public void setId(int id);
		
		public String getNome();
		
		public void setNome(String nome);

		public String getCognome();

		public void setCognome(String cognome);

		public Date getDataDiNascita();
		
		public void setDataDiNascita(Date dataDiNascita);

		public String getEmail();

		public void setEmail(String email);

		public String getPassword();

		public void setPassword(String password);

		public String getTipo();

		public void setTipo(String tipo);

		@Override
		public String toString();
		
		
}
