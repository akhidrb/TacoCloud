package sia.tacocloud.tacos;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import sia.tacocloud.tacos.data.IngredientRepository;
import sia.tacocloud.tacos.data.OrderRepository;
import sia.tacocloud.tacos.data.TacoRepository;
import sia.tacocloud.tacos.web.WebConfig;

@RunWith(SpringRunner.class)
@WebMvcTest(WebConfig.class)
public class HomeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IngredientRepository ingredientRepository;
	
	@MockBean
	private TacoRepository designRepository;
	
	@MockBean
	private OrderRepository orderRepository;
	
	@Test
	public void testHomePage() throws Exception {
		mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(view().name("home"))
			.andExpect(content().string(containsString("Welcome to...")));
	}

}
