package br.com.veiga.productms.service;

import br.com.veiga.productms.dto.ProductDTO;
import br.com.veiga.productms.model.Product;
import br.com.veiga.productms.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ModelMapper mapper;

    @Override
    public Optional<ProductDTO> create(ProductDTO request) {
        request.setAvailable(true);

        Product product = repository.save(mapper.map(request, Product.class));

        ProductDTO response = mapper.map(product, ProductDTO.class);

        return Optional.of(response);
    }

    @Override
    public List<ProductDTO> getAll() {
        List<Product> products = repository.findAll();

        return products.stream()
                .map(product -> mapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDTO> getById(Long id) {
        Optional<Product> product = repository.findById(id);
        return product.map(value -> mapper.map(value, ProductDTO.class));
    }

    @Override
    public Optional<ProductDTO> update(Long id, ProductDTO request) {
        Optional<Product> product = repository.findById(id);

        if (product.isPresent()) {
            product.get().setName(request.getName());
            product.get().setDescription(request.getDescription());
            product.get().setPrice(request.getPrice());
            repository.save(product.get());
            return Optional.of(mapper.map(product.get(), ProductDTO.class));
        }

        return Optional.empty();
    }

    @Override
    public boolean inactive(Long id) {
        Optional<Product> product = repository.findById(id);

        if (product.isPresent()) {
            product.get().setAvailable(false);
            repository.save(product.get());
            return true;
        }

        return false;
    }

}
