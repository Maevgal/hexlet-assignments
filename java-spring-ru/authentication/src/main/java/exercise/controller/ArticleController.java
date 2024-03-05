package exercise.controller;

import exercise.dto.ArticleCreateDTO;
import exercise.dto.ArticleDTO;
import exercise.dto.ArticleUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.ArticleMapper;
import exercise.model.Article;
import exercise.model.User;
import exercise.repository.ArticleRepository;
import exercise.repository.UserRepository;
import exercise.utils.UserUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private UserUtils userUtils;


    // BEGIN
    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    ArticleDTO create(@Valid @RequestBody ArticleCreateDTO articleCreateDTO) {
        Article article = articleMapper.map(articleCreateDTO);
        User currentUser = userUtils.getCurrentUser();
        article.setAuthor(currentUser);
        articleRepository.save(article);
        return articleMapper.map(article);
    }
    // END

    @GetMapping("")
    List<ArticleDTO> index() {
        var tasks = articleRepository.findAll();

        return tasks.stream()
                .map(t -> articleMapper.map(t))
                .toList();
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ArticleDTO show(@PathVariable Long id) {
        var article = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found: " + id));
        return articleMapper.map(article);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ArticleDTO update(@RequestBody @Valid ArticleUpdateDTO articleData, @PathVariable Long id) {
        var article = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found"));

        articleMapper.update(articleData, article);
        articleRepository.save(article);
        return articleMapper.map(article);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void destroy(@PathVariable Long id) {
        articleRepository.deleteById(id);
    }
}
