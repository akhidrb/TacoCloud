package sia.tacocloud.tacos.data;

import sia.tacocloud.tacos.Order;

public interface OrderRepository {

	Order save(Order order);
}
