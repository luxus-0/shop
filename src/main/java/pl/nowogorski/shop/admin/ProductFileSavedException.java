package pl.nowogorski.shop.admin;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ProductFileSavedException extends RuntimeException {
    public ProductFileSavedException() {
        log.info("File couldn't be save");
    }
}
