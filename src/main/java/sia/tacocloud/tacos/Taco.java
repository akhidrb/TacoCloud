package sia.tacocloud.tacos;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Taco {

	private Long id;
	
	private Date createdAt;
	
	private String name;
	
	private List<Ingredient> ingredients;
	
}
