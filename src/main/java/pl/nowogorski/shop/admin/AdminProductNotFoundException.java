package pl.nowogorski.shop.admin;

import lombok.extern.log4j.Log4j2;

@Log4j2
class AdminProductNotFoundException extends Exception {
    AdminProductNotFoundException(){
        log.info("Admin product not found");
    }

    AdminProductNotFoundException(Long id) {
        log.info("Admin with id: " + id + " not found");
    }
}
