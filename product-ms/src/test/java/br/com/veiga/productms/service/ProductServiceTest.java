package br.com.veiga.productms.service;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.veiga.productms.dto.ProductDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:clear-database.sql"})
class ProductServiceTest {

    @Autowired
    private ProductService service;

    @BeforeAll
    public static void setUp() {
        FixtureFactoryLoader.loadTemplates("br.com.veiga.productms.fixture");
    }

    @Test
    public void shouldCreateProduct() {
        ProductDTO request = Fixture.from(ProductDTO.class).gimme("valid");

        Optional<ProductDTO> response = service.create(request);

        assertNotNull(response);
        assertEquals(request.getName(), response.get().getName());
        assertEquals(request.getDescription(), response.get().getDescription());
        assertEquals(request.getPrice(), response.get().getPrice());
        assertTrue(response.get().isAvailable());
    }

    @Test
    public void shouldGetAllProducts() {
        ProductDTO request = Fixture.from(ProductDTO.class).gimme("valid");
        Optional<ProductDTO> productDTO = service.create(request);
        List<ProductDTO> responses = service.getAll();

        assertNotNull(responses);
        assertEquals(responses.get(0).getName(), productDTO.get().getName());
        assertEquals(responses.get(0).getDescription(), productDTO.get().getDescription());
        assertEquals(responses.get(0).getPrice(), productDTO.get().getPrice());
    }

    @Test
    public void shouldGetById() {
        ProductDTO request = Fixture.from(ProductDTO.class).gimme("valid");
        Optional<ProductDTO> response = service.create(request);

        Long id = response.get().getId();

        Optional<ProductDTO> responseById = service.getById(id);

        assertNotNull(response);
        assertEquals(request.getName(), responseById.get().getName());
        assertEquals(request.getDescription(), responseById.get().getDescription());
        assertEquals(request.getPrice(), responseById.get().getPrice());
        assertTrue(responseById.get().isAvailable());
    }

    @Test
    public void shouldUpdateProduct() {
        ProductDTO request = Fixture.from(ProductDTO.class).gimme("valid");
        Optional<ProductDTO> response = service.create(request);
        Long id = response.get().getId();

        String newDescription = "Esse com certeza é o melhor aparelho de telefone móvel já criado na história";
        request.setDescription(newDescription);

        double newPrice = 789.32;
        request.setPrice(newPrice);

        Optional<ProductDTO> updatedProductDTO = service.update(id, request);

        assertNotNull(updatedProductDTO);
        assertEquals(newDescription, updatedProductDTO.get().getDescription());
        assertEquals(newPrice, updatedProductDTO.get().getPrice());
    }
}
