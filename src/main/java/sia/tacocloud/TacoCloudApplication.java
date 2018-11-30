package sia.tacocloud;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sia.tacocloud.tacos.Ingredient;
import sia.tacocloud.tacos.Ingredient.Type;
import sia.tacocloud.tacos.data.IngredientRepository;

@SpringBootApplication
public class TacoCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner dataLoader(IngredientRepository ingredientRepo) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
		        ingredientRepo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
		        ingredientRepo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
		        ingredientRepo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
		        ingredientRepo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
		        ingredientRepo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
		        ingredientRepo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
		        ingredientRepo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
		        ingredientRepo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
		        ingredientRepo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
		        ingredientRepo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
			}
		};
	}
}
