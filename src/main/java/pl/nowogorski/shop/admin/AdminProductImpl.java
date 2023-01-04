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
                .map(AdminProductMapper::mapAdminProductDto)
                .collect(Collectors.toList());
    }
    AdminProductDto readProducts(Long id) throws AdminProductNotFoundException {
        return adminProductRepository.findById(id)
                .map(AdminProductMapper::mapAdminProductDto)
                .orElseThrow(() -> new AdminProductNotFoundException(id));
    }

    Page<AdminProduct> readProducts(PageRequest pageRequest){
        return adminProductRepository.findAll(pageRequest);
    }

    AdminProduct addProduct(AdminProduct adminProduct) {
        return adminProductRepository.save(adminProduct);
    }

    AdminProductDto actualizeProduct(Long id, ProductDto productDto) {

        AdminProduct product = new AdminProduct(
                id,
                productDto.name(),
                productDto.category(),
                productDto.description(),
                productDto.fullDescription(),
                productDto.price(),
                productDto.currency(),
                productDto.image(),
                productDto.slug());

        AdminProduct productBuild = adminProductRepository.save(product);

        return Stream.of(productBuild)
                .map(AdminProductMapper::mapAdminProductDto)
                .findAny()
                .get();
    }

    void clearProduct(){
        adminProductRepository.deleteAll();
    }

    public void clearProduct(Long id) {
        adminProductRepository.deleteById(id);
    }
}
