package salary.domain;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Salary {	
		@Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
	    private Long id;
		private String name;
		private String username;
		private String account;
		private String city;
		private int phone;
		private int amount;
		
	    @JsonIgnore
		
		public Salary() {}
		
		public Salary(String name, String username, String account, String city, int phone, int amount) {
			super();
			this.name = name;
			this.username = username;
			this.account = account;
			this.city = city;
			this.phone = phone;		
			this.amount = amount;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getAccount() {
			return account;
		}

		public void setAccount(String account) {
			this.account = account;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public int getPhone() {
			return phone;
		}

		public void setPhone(int phone) {
			this.phone = phone;
		}

		public int getAmount() {
			return amount;
		}

		public void setAmount(int amount) {
			this.amount = amount;
		}

		@Override
		public String toString() {
			return "Salary [id=" + id + ", name=" + name + ", username=" + username + ", account=" + account + ", city="
					+ city + ", phone=" + phone + ", amount=" + amount + "]";
		}		
	}

