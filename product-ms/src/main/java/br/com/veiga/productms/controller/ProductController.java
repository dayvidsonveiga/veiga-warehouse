package br.com.veiga.productms.controller;

import br.com.veiga.productms.dto.ProductDTO;
import br.com.veiga.productms.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody @Valid ProductDTO request) {
        Optional<ProductDTO> response = service.create(request);

        return response.map(productDTO -> new ResponseEntity<>(productDTO, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable("id") Long id) {
        Optional<ProductDTO> response = service.getById(id);

        return response.map(productDTO -> new ResponseEntity<>(productDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable("id") Long id,
                                             @RequestBody @Valid ProductDTO request) {
        Optional<ProductDTO> response = service.update(id, request);

        return response.map(productDTO -> new ResponseEntity<>(productDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        boolean inactive = service.inactive(id);

        return inactive
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
