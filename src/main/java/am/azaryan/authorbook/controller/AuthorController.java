package am.azaryan.authorbook.controller;

import am.azaryan.authorbook.entity.Author;
import am.azaryan.authorbook.repository.AuthorRepository;

import org.springframework.context.annotation.Bean;
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

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping("/authors")
    public String authorPage(ModelMap modelMap) {
        List<Author> allAuthors = authorRepository.findAll();
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
    public String deleteAuthor(@RequestParam("id") int id) {
        authorRepository.deleteById(id);
        return "redirect:/authors";
    }

}
