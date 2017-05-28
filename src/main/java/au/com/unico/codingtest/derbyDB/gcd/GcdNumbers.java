package au.com.unico.codingtest.derbyDB.gcd;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GcdNumbers {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Integer gcd;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getGcd() {
		return gcd;
	}
	public void setGcd(Integer gcd) {
		this.gcd = gcd;
	}

}
