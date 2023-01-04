package pl.nowogorski.shop.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.nowogorski.shop.product.dto.ProductDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
class ProductDatabaseImpl {

    private final ProductRepository productRepository;

    ProductDatabaseImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    List<ProductDto> readProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::toProductDto)
                .collect(Collectors.toList());
    }
    ProductDto readProducts(Long id) throws ProductNotFoundException {
        return productRepository.findById(id)
                .map(this::toProductDto)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    Page<Product> readProducts(int page, int size){
        return productRepository.findAll(PageRequest.of(page, size));
    }
    ProductDto toProductDto(Product product){
        return new ProductDto(
                product.getName(),
                product.getCategory(),
                product.getDescription(),
                product.getPrice(),
                product.getCurrency(),
                product.getImage(),
                product.getSlug());
    }

    public Product readProductBySlug(String slug) {
        return productRepository.findBySlug(slug).orElseThrow();
    }
}
