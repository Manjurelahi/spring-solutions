package org.api.news;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsAPIServiceTest {
    @Autowired
    NewsAPIService newsAPIService;

    @Test
    public void testFilteredNewsAPIData() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyyy");
        Date fromDate = simpleDateFormat.parse("14032024");
        Date toDate = simpleDateFormat.parse("15032024");
        Object[] articles = newsAPIService.getFilteredNewsAPIData(fromDate, toDate);
        Assertions.assertTrue(articles.length > 0);
    }
}
