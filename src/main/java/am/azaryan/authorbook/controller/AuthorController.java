package am.azaryan.authorbook.controller;

import am.azaryan.authorbook.entity.Author;
import am.azaryan.authorbook.exception.ModelNotFoundException;
import am.azaryan.authorbook.repository.AuthorRepository;
import am.azaryan.authorbook.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorRepository authorRepository;
    private final AuthorService authorService;

    public AuthorController(AuthorRepository authorRepository, AuthorService authorService) {
        this.authorRepository = authorRepository;
        this.authorService = authorService;
    }

    @GetMapping
    public String authorPage(ModelMap modelMap) {
        List<Author> allAuthors = authorService.findAll();
        modelMap.put("authors", allAuthors);
        return "author/authors";
    }

    @GetMapping("add")
    public String addAuthorPage() {
        return "author/addAuthor";
    }

    @PostMapping("add")
    public String addAuthor(@ModelAttribute Author author) {
        authorRepository.save(author);
        return "redirect:/authors";
    }

    @GetMapping("/edit")
    public String editAuthorPage(@RequestParam("id") int id, ModelMap modelMap) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            modelMap.put("author", author);
            return "author/editAuthor";
        }
        return "redirect:/authors";
    }

    @PostMapping("/edit")
    public String editAuthor(@ModelAttribute Author author) {
        authorRepository.save(author);
        return "redirect:/authors";
    }

    @GetMapping("/delete")
    public String deleteAuthor(@RequestParam("id") int id) throws ModelNotFoundException {
        authorService.deleteById(id);
        return "redirect:/authors";
    }

}
