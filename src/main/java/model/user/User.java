package model.user;

import java.util.Date;

public interface User {

		public int getId();

		public void setId(int id);
		
		public String getName();
		
		public void setName(String name);

		public String getSurname();

		public void setSurname(String surname);

		public Date getDateOfBirth();
		
		public void setDateOfBirth(Date dateOfBirth);

		public String getEmail();

		public void setEmail(String email);

		public String getPassword();

		public void setPassword(String password);

		public String getKind();

		public void setKind(String kind);

		@Override
		public String toString();
		
		
}
