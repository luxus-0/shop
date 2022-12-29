package pl.nowogorski.shop.product;

import org.springframework.stereotype.Service;
import pl.nowogorski.shop.product.dto.ProductDto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public ProductDto readProducts(Long id) throws ProductNotFoundException {
        return productRepository.findById(id)
                .map(this::toProductDto)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    ProductDto addProduct(Product product) throws ProductNotFoundException {
        Product productSaved = productRepository.save(product);
        return Stream.of(productSaved)
                .map(this::toProductDto)
                .findAny()
                .orElseThrow(ProductNotFoundException::new);
    }

    public ProductDto actualizeProduct(Long id, ProductDto productDto) {

                Product product = new Product(
                        id,
                        productDto.name(),
                        productDto.category(),
                        productDto.description(),
                        productDto.price(),
                        productDto.currency());

                Product productBuild = productRepository.save(product);

                return Stream.of(productBuild)
                        .map(this::toProductDto)
                        .findAny()
                        .orElseThrow();
    }

    void clearProduct(){
        productRepository.deleteAll();
    }

    ProductDto toProductDto(Product product){
        return new ProductDto(
                product.name(),
                product.category(),
                product.description(),
                product.price(),
                product.currency());
    }
}
