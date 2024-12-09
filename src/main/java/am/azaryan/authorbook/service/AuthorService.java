package am.azaryan.authorbook.service;

import am.azaryan.authorbook.entity.Author;
import am.azaryan.authorbook.exception.ModelNotFoundException;
import am.azaryan.authorbook.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<Author> findAll(){
        return authorRepository.findAll();
    }

    public void deleteById(int id) throws ModelNotFoundException {
        Optional<Author> byId = authorRepository.findById(id);
        if (byId.isPresent()){
            authorRepository.deleteById(id);
        } else {
            throw new ModelNotFoundException("Author does not exist.");
        }
    }
}
