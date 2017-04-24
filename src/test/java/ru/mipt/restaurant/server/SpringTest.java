package ru.mipt.restaurant.server;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class SpringTest {

    protected MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    @Before
    public void initMockMvc() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();

    }
}
