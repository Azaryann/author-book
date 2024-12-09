package am.azaryan.authorbook.repository;

import am.azaryan.authorbook.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    Optional<Author> findById(int id);

}
