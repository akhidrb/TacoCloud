package sia.tacocloud.tacos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;

import lombok.Data;


@Data
@Entity
public class Taco {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Date createdAt;
	
	private String name;
	
	@ManyToMany(targetEntity=Ingredient.class)
	private List<Ingredient> ingredients = new ArrayList<>();
	
	@PrePersist
	void createdAt() {
		this.createdAt = new Date();
	}
	
}
