package pl.nowogorski.shop.product;

import lombok.extern.log4j.Log4j2;

@Log4j2
class ProductNotFoundException extends Exception {
    ProductNotFoundException(){
        log.info("Product not found");
    }
    ProductNotFoundException(Long id){
        log.info("Product with id: "+id+" not found");
    }
}
