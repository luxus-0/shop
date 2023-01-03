package pl.nowogorski.shop.admin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UploadedFileNameUtilsTest {



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
        UploadedFileNameUtils uploadedFileNameUtils = new UploadedFileNameUtils();
        //when
        String fileName = uploadedFileNameUtils.generateSlugFromString(inputFileName);
        //then
        assertEquals(fileName, outputFileName);
    }
}