package cl.falabella.code.challengefreddyparedes.utils;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import cl.falabella.code.challengefreddyparedes.exception.ServiceException;
import cl.falabella.code.challengefreddyparedes.model.ProductModel;

/**
 * @author Freddy Paredes
 * Tests for Statics Utils
 */
public class ValidatorUtilsTest {

    private static final int RESPONSE_BAD_REQUEST = 400;
    private static final String SKU_ERROR = "SKU limit error";
    private static final String BAD_URL_ERROR = "Others_images have a bad format";

    @Nested
    class valdiateURL {

        @Test
        void testIsValidURLFalse() {
            boolean response = ValidatorUtils.isValidURL("a");
            assertAll(
                    () -> assertNotNull(response),
                    () -> assertEquals(false, response));
        }

        @Test
        void testIsValidURLTrue() {
            boolean response = ValidatorUtils.isValidURL("https://example.com");
            assertAll(
                    () -> assertNotNull(response),
                    () -> assertEquals(true, response));
        }
    }

    @Nested
    class validateSKU {

        @Test
        void testValdiateMinSKUTrue() {
            boolean response = ValidatorUtils.valdiateMinSKU("FAL-9999999");
            assertAll(
                    () -> assertNotNull(response),
                    () -> assertEquals(false, response));
        }

        @Test
        void testValdiateMinSKUFalse() {
            boolean response = ValidatorUtils.valdiateMinSKU("FAL-99999991");
            assertAll(
                    () -> assertNotNull(response),
                    () -> assertEquals(false, response));
        }
    }

    @Nested
    class validateDTOProduct {

        @Test
        void testValidateDtoBadSKU() {
            ProductModel productDto = new ProductModel();
            productDto.setSku("FAL-99");
            productDto.setOthers_image("https://example.com");            
            ServiceException response = assertThrows(ServiceException.class,() -> ValidatorUtils.validateDTO(productDto));
            assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(RESPONSE_BAD_REQUEST, Integer.valueOf(response.getCode())),
                () -> assertEquals(SKU_ERROR, response.getMessage()));
        }
        @Test
        void testValidateDtoBadURL() {
            ProductModel productDto = new ProductModel();
            productDto.setSku("FAL-9999999");
            productDto.setOthers_image("example.com");            
            ServiceException response = assertThrows(ServiceException.class,() -> ValidatorUtils.validateDTO(productDto));
            assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(RESPONSE_BAD_REQUEST, Integer.valueOf(response.getCode())),
                () -> assertEquals(BAD_URL_ERROR, response.getMessage()));
        }

        @Test
        void testValidateDtoOK() {
            ProductModel productDto = new ProductModel();
            productDto.setSku("FAL-9999999");
            productDto.setOthers_image("https://example.com");            
            boolean response = ValidatorUtils.validateDTO(productDto);
            assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(true, response));
        }
    }
}// class closure
