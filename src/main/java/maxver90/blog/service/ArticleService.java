package maxver90.blog.service;

import maxver90.blog.entity.Article;
import maxver90.blog.entity.Rating;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {
    // Service - компонент приложения Spring, который отвечает за логику работы отдельного элемента прилдожения.
    // Сервис можно внедрять в любой точке программы через @Autowired.
    public double getAverageRating(Article article) {
        List<Rating> ratingList = article.getRatings();
        double count = 0;
        for (Rating rating : ratingList) {
            count += rating.getRating();
        }
        if (ratingList.size() != 0) {
            return count / ratingList.size();
        } else {
            return 0;
        }
    }
}
