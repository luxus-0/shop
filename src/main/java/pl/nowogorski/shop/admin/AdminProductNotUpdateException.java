package pl.nowogorski.shop.admin;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class AdminProductNotUpdateException extends Exception {
    public AdminProductNotUpdateException(Long id) {
        log.info("Product id: " + id + " not update");
    }
}
