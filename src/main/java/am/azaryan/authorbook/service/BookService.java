package am.azaryan.authorbook.service;

import am.azaryan.authorbook.entity.Book;
import am.azaryan.authorbook.exception.ModelNotFoundException;
import am.azaryan.authorbook.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public void deleteById(int id) throws ModelNotFoundException {
        Optional<Book> byId = bookRepository.findById(id);
        if (byId.isPresent()){
            bookRepository.deleteById(id);
        } else {
            throw new ModelNotFoundException("Book does not exist.");
        }
    }
}
