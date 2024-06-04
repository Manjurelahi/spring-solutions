package org.api.news;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class NewsAPIControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testFetchNewsAPIData() throws Exception {
        this.mvc.perform(get("/news/api?from=14032024&to=15032024"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void testFetchEmptyNewsAPIData() throws Exception {
        this.mvc.perform(get("/news/api?from=10032024&to=11032024"))
                .andExpect(status().isCreated())
                .andExpect(content().string("No Data Found"));
    }

    @Test
    public void testBadRequestWithoutReqParams() throws Exception {
        this.mvc.perform(get("/news/api"))
                .andExpect(status().isBadRequest());
    }
}
