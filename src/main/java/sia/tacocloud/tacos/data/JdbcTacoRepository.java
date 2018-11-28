package sia.tacocloud.tacos.data;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import sia.tacocloud.tacos.Taco;

@Repository
public class JdbcTacoRepository implements TacoRepository {

	private SimpleJdbcInsert tacoInserter;
	private SimpleJdbcInsert tacoIngredientInserter;
	private ObjectMapper objectMapper;
	
	@Autowired
	public JdbcTacoRepository(JdbcTemplate jdbc) {
		this.tacoInserter = new SimpleJdbcInsert(jdbc).withTableName("Taco").usingGeneratedKeyColumns("id");
		this.tacoIngredientInserter = new SimpleJdbcInsert(jdbc).withTableName("Taco_Ingredients");
		this.objectMapper = new ObjectMapper();
	}
	
	@Override
	public Taco save(Taco taco) {
		taco.setCreatedAt(new Date());
		long tacoId = saveTacoDetails(taco);
		taco.setId(tacoId);
		List<String> ingredients = taco.getIngredients();
		for (String ingredient : ingredients) {
			saveIngredientsToTaco(ingredient, tacoId);
		}
		return taco;
	}
	
	private long saveTacoDetails(Taco taco) {
		@SuppressWarnings("unchecked")
		Map<String, Object> values = objectMapper.convertValue(taco, Map.class);
		values.put("createdAt", taco.getCreatedAt());
		long tacoId = tacoInserter.executeAndReturnKey(values).longValue();
		return tacoId;
	}
	
	private void saveIngredientsToTaco(String ingredient, long tacoId) {
		Map<String, Object> values = new HashMap<>();
		values.put("taco", tacoId);
		values.put("ingredient", ingredient);
		tacoIngredientInserter.execute(values);
	}
	
}
