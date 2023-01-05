package pl.nowogorski.shop.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class SlugifyUtilsTest {

    @ParameterizedTest
    @CsvSource({
            "product test.png, product-test.png",
            "hello file.png, hello-file.png",
            "ąęśćżźńłó.png, aesczznlo.png",
            "Product 1.png, product-1.png",
            "Product - 1.png, product-1.png",
            "Product_1.png, product-1.png",
            "Product__1.png, product-1.png"
    })
    void shouldReturnSlugifyFileNameWhenInputFileName(String inputFileName, String outputFileName){
        //given
        SlugifyUtils uploadedFileNameUtils = new SlugifyUtils();
        //when
        String fileName = uploadedFileNameUtils.generateSlugFromFileName(inputFileName);
        //then
        assertEquals(fileName, outputFileName);
    }

}