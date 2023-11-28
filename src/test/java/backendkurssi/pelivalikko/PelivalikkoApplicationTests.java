package backendkurssi.pelivalikko;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import backendkurssi.pelivalikko.controller.HahmoController;

@SpringBootTest
class PelivalikkoApplicationTests {

	@Autowired
    private HahmoController controller;

    @Test
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

}
