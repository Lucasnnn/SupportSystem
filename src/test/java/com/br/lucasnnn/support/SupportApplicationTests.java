package com.br.lucasnnn.support;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class SupportApplicationTests {

    @Test
    public void contextFailsToLoad() {
        SupportApplication.main(new String[]{});
    }
}