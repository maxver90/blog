package maxver90.blog.repository;

import maxver90.blog.entity.Article;
import maxver90.blog.entity.Rating;
import maxver90.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    boolean existsByUserAndArticle(User user, Article article);
}
