package br.com.fiap.lanchonete;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ProducaoServiceFase4ApplicationTests {

	@Test
    void contextLoads() {
        ProducaoServiceFase4Application.main(new String[] {});
    }

    @Test
    void applicationContextNotNull() {
        assertNotNull(ProducaoServiceFase4Application.class);
    }

}
