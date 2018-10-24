package challenge.deckcards.init;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import challenge.deckcards.business.IDealDeckSvc;
import challenge.deckcards.dao.IDeckDao;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=ServletInitializer.class)
@AutoConfigureMockMvc
public class DeckcardsApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IDeckDao deckDao;

	@MockBean
	private IDealDeckSvc dealDeckSvc;
	
	@Test
	public void testShuffle() {
		// TODO make test using mockMvc 
		/*
		 mockMvc.perform(get("/users"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].id", is(1)))
            .andExpect(jsonPath("$[0].username", is("Daenerys Targaryen")))
            .andExpect(jsonPath("$[1].id", is(2)))
            .andExpect(jsonPath("$[1].username", is("John Snow")));
		 */
	}

}
