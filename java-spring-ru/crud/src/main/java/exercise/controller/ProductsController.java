package exercise.controller;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.ProductMapper;
import exercise.model.Category;
import exercise.model.Product;
import exercise.repository.CategoryRepository;
import exercise.repository.ProductRepository;
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
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    // BEGIN
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping(path = "")
    public List<ProductDTO> index() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(p -> productMapper.map(p))
                .toList();
    }

    @GetMapping(path = "/{id}")
    public ProductDTO show(@PathVariable long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id %d not found".formatted(id)));
        return productMapper.map(product);
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(@Valid @RequestBody ProductCreateDTO productCreateDTO) {
        Product product = productMapper.map(productCreateDTO);
        Long idCategory = productCreateDTO.getCategoryId();
        Category category = categoryRepository.findById(idCategory)
                .orElseThrow(() -> new ResourceNotFoundException("Not found category with id %d"
                        .formatted(idCategory)));
        product.setCategory(category);
        productRepository.save(product);
        return productMapper.map(product);
    }

    @PutMapping(path = "/{id}")
    public ProductDTO update(@PathVariable long id, @Valid @RequestBody ProductUpdateDTO productUpdateDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id %d not found".formatted(id)));
        productMapper.update(productUpdateDTO, product);
        Long idCategory = productUpdateDTO.getCategoryId().get();
        Category category = categoryRepository.findById(idCategory)
                .orElseThrow(() -> new ResourceNotFoundException("Not found category with id %d"
                        .formatted(idCategory)));
        product.setCategory(category);
        productRepository.save(product);
        ProductDTO productDTO = productMapper.map(product);
        return productDTO;
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleted(@PathVariable long id) {
        productRepository.deleteById(id);
    }
    // END
}
