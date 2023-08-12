package br.com.veiga.productms.fixture;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.veiga.productms.dto.ProductDTO;

public class ProductTemplateLoader implements TemplateLoader {

    @Override
    public void load() {
        Fixture.of(ProductDTO.class).addTemplate("valid", new Rule() {{
            add("name", random("Iphone 13 pro max", "Iphone 14 pro max", "Iphone 15 pro max", "Iphone 16 pro max"));
            add("description", "iPhone 13 Pro Max. O maior upgrade do sistema de câmera Pro até hoje. Tela Super Retina XDR com ProMotion para uma experiência mais rápida e responsiva.");
            add("price", random(Double.class, range(0.01, 99999.99)));
        }});
    }
}
