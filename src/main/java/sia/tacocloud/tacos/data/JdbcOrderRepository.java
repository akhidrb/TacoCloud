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

import sia.tacocloud.tacos.Order;
import sia.tacocloud.tacos.Taco;

@Repository
public class JdbcOrderRepository implements OrderRepository {
	
	private SimpleJdbcInsert orderInserter;
	private SimpleJdbcInsert orderTacoInserter;
	private ObjectMapper objectMapper;
	
	@Autowired
	public JdbcOrderRepository(JdbcTemplate jdbc) {
		this.orderInserter = new SimpleJdbcInsert(jdbc)
				.withTableName("Taco_Order")
				.usingGeneratedKeyColumns("id");
		
		this.orderTacoInserter = new SimpleJdbcInsert(jdbc)
				.withTableName("Taco_Order_Tacos");
		
		this.objectMapper = new ObjectMapper();
	}
	
	@Override
	public Order save(Order order) {
		order.setPlacedAt(new Date());
		long orderId = saveOrderDetails(order);
		List<Taco> tacos = order.getTacos();
		for (Taco taco: tacos) {
			saveTacoToOrder(taco.getId(), orderId);
		}
		return order;
	}

	private long saveOrderDetails(Order order) {
		@SuppressWarnings("unchecked")
		Map<String, Object> values = 
			objectMapper.convertValue(order,  Map.class);
		values.put("placedAt", order.getPlacedAt());
		return orderInserter
				.executeAndReturnKey(values)
				.longValue();
	}
	
	private void saveTacoToOrder(long tacoId, long orderId) {
		Map<String, Object> values = new HashMap<>();
		values.put("tacoOrder", orderId);
		values.put("taco", tacoId);
		orderTacoInserter.execute(values);
	}
	
}

















