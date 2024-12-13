package am.azaryan.authorbook.controller;

import am.azaryan.authorbook.entity.Author;
import am.azaryan.authorbook.entity.Book;
import am.azaryan.authorbook.exception.ModelNotFoundException;
import am.azaryan.authorbook.repository.BookRepository;
import am.azaryan.authorbook.service.AuthorService;
import am.azaryan.authorbook.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;

    private final AuthorService authorService;

    private final BookService bookService;

    public BookController(BookRepository bookRepository,AuthorService authorService, BookService bookService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @GetMapping
    public String bookPage(ModelMap modelMap) {
        List<Book> books = bookRepository.findAll();
        modelMap.put("books", books);
        return "book/books";
    }

    @GetMapping("/add")
    public String addBookPage(ModelMap modelMap) {
        List<Author> authors = authorService.findAll();
        modelMap.put("authors", authors);
        return "book/addBook";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book) {
        book.setCreatedAt(new Date());
        bookRepository.save(book);
        return "redirect:/books";
    }


    @GetMapping("/delete")
    public String deleteAuthor(@RequestParam("id") int id) throws ModelNotFoundException {
        bookService.deleteById(id);
        return "redirect:/books";
    }

}
