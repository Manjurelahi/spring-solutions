package org.api.news;

import org.api.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

@Service
public class NewsAPIService {
    @Value("${news.api.url}")
    private String API_URL;

    public Object[] getFilteredNewsAPIData(Date from, Date to) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        WebClient webClient = WebClient.builder().baseUrl(API_URL).build();
        Article[] articles = webClient.get().retrieve().bodyToMono(Article[].class).block();
        return Arrays.stream(articles).filter(article -> {
            try {
				Date updateDate = simpleDateFormat.parse(article.getUpdatedAt());
				return updateDate.after(from) && updateDate.before(to);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }).toArray();
    }
}
