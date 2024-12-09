package am.azaryan.authorbook.controller;

import am.azaryan.authorbook.entity.Author;
import am.azaryan.authorbook.exception.ModelNotFoundException;
import am.azaryan.authorbook.repository.AuthorRepository;
import am.azaryan.authorbook.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AuthorController {

    private final AuthorRepository authorRepository;
    private final AuthorService authorService;

    public AuthorController(AuthorRepository authorRepository, AuthorService authorService) {
        this.authorRepository = authorRepository;
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public String authorPage(ModelMap modelMap) {
        List<Author> allAuthors = authorService.findAll();
        modelMap.put("authors", allAuthors);
        return "authors";
    }

    @GetMapping("/authors/add")
    public String addAuthorPage() {
        return "addAuthor";
    }

    @PostMapping("/authors/add")
    public String addAuthor(@ModelAttribute Author author) {
        authorRepository.save(author);
        return "redirect:/authors";
    }

    @GetMapping("/authors/delete")
    public String deleteAuthor(@RequestParam("id") int id) throws ModelNotFoundException {
        authorService.deleteById(id);
        return "redirect:/authors";
    }

}
