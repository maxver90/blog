package maxver90.blog.repository;

import maxver90.blog.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findAllByIsPublished(Boolean isPublished);
}
