package am.azaryan.authorbook.repository;

import am.azaryan.authorbook.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {



}
