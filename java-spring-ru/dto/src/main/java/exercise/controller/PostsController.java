package exercise.controller;

import exercise.dto.CommentDTO;
import exercise.dto.PostDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.model.Comment;
import exercise.model.Post;
import exercise.repository.CommentRepository;
import exercise.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// BEGIN
@RestController
@RequestMapping
@AllArgsConstructor
public class PostsController {

    private PostRepository postRepository;
    private CommentRepository commentRepository;

    @GetMapping("/posts")
    public List<PostDTO> index() {
        List<Post> posts = postRepository.findAll();
        var result = posts.stream()
                .map(this::postToDTO)
                .toList();
        return result;
    }

    @GetMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDTO show(@PathVariable long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id %d not found".formatted(id)));
        PostDTO postDTO = postToDTO(post);
        return postDTO;
    }

    private PostDTO postToDTO(Post post) {
        PostDTO dto = new PostDTO();

        dto.setId(post.getId());
        dto.setBody(post.getBody());
        dto.setTitle(post.getTitle());
        dto.setComments(commentRepository.findByPostId(post.getId())
                .stream()
                .map(this::comToDTO)
                .toList());
        return dto;
    }

    private CommentDTO comToDTO(Comment comment) {
        CommentDTO dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setBody(comment.getBody());
        return dto;
    }


}
// END
