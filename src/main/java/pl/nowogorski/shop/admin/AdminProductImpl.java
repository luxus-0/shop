package pl.nowogorski.shop.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.nowogorski.shop.admin.dto.AdminProductDto;
import pl.nowogorski.shop.product.dto.ProductDto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
class AdminProductImpl {
    private final AdminProductRepository adminProductRepository;

    AdminProductImpl(AdminProductRepository adminProductRepository) {
        this.adminProductRepository = adminProductRepository;
    }

    List<AdminProductDto> readProducts() {
        return adminProductRepository.findAll()
                .stream()
                .map(this::toAdminProductDto)
                .collect(Collectors.toList());
    }
    AdminProductDto readProducts(Long id) throws AdminProductNotFoundException {
        return adminProductRepository.findById(id)
                .map(this::toAdminProductDto)
                .orElseThrow(() -> new AdminProductNotFoundException(id));
    }

    Page<AdminProduct> readProducts(PageRequest pageRequest){
        return adminProductRepository.findAll(pageRequest);
    }

    AdminProduct addProduct(AdminProduct adminProduct) {
        return adminProductRepository.save(adminProduct);
    }

    AdminProductDto actualizeProduct(Long id, ProductDto productDto) throws ProductNotUpdateException {

        AdminProduct product = new AdminProduct(
                id,
                productDto.name(),
                productDto.category(),
                productDto.description(),
                productDto.price(),
                productDto.currency());

        AdminProduct productBuild = adminProductRepository.save(product);

        return Stream.of(productBuild)
                .map(this::toAdminProductDto)
                .findAny()
                .orElseThrow(() -> new ProductNotUpdateException(id));
    }

    void clearProduct(){
        adminProductRepository.deleteAll();
    }

    public void clearProduct(Long id) {
        adminProductRepository.deleteById(id);
    }

    AdminProductDto toAdminProductDto(AdminProduct adminProduct){
        return new AdminProductDto(
                adminProduct.getName(),
                adminProduct.getCategory(),
                adminProduct.getDescription(),
                adminProduct.getPrice(),
                adminProduct.getCurrency());
    }
}
