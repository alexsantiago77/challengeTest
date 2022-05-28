package cl.falabella.code.challengefreddyparedes.presentation;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import cl.falabella.code.challengefreddyparedes.exception.ServiceException;
import cl.falabella.code.challengefreddyparedes.model.ProductModel;
import cl.falabella.code.challengefreddyparedes.service.IProductService;
import cl.falabella.code.challengefreddyparedes.utils.ValidatorUtils;

/**
 * @author Freddy Paredes
 * Tests for Controller
 */
public class ProductsControllerTest {
    private static final int RESPONSE_BAD_REQUEST = 400;
    private static final int RESPONSE_DELETE = 204;
    private static final int RESPONSE_CREATE = 201;
    private static final int RESPONSE_OK = 200;
    private static final String SKU_ERROR = "SKU limit error";
    private static final String BAD_URL_ERROR = "Others_images have a bad format";

    @InjectMocks
    ProductsController controller;

    @Mock
    IProductService productService;

    @Mock
    ValidatorUtils validator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testDeleteProduct() {
        when(productService.deleteProduct(anyString())).thenReturn(null);
        ResponseEntity<ProductModel> response = controller.deleteProduct(anyString());
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(RESPONSE_DELETE, response.getStatusCodeValue()));
    }// test closure

    @Test
    void testGetProduct() {
        when(productService.findProduct(anyString())).thenReturn(null);
        ResponseEntity<ProductModel> response = controller.getProduct(anyString());
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(RESPONSE_OK, response.getStatusCodeValue()));
    }

    @Test
    void testGetProductsList() {
        when(productService.findAll()).thenReturn(null);
        ResponseEntity<List<ProductModel>> response = controller.getProductsList();
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(RESPONSE_OK, response.getStatusCodeValue()));
    }



    @Nested
    class updateProductTest {
        
        @Test
        void testExceptionSKUSaveProduct() {
            when(productService.saveProduct(getInitNotValidProduct())).thenReturn(null);
            ServiceException response = assertThrows(ServiceException.class,
                    () -> controller.saveProduct(getInitNotValidProduct()));
            assertAll(
                    () -> assertNotNull(response),
                    () -> assertEquals(RESPONSE_BAD_REQUEST, Integer.valueOf(response.getCode())),
                    () -> assertEquals(SKU_ERROR, response.getMessage()));
        }

        @Test
        void testExceptionURLSaveProduct() {
            when(productService.saveProduct(getInitNotURLValidProduct())).thenReturn(null);
            ServiceException response = assertThrows(ServiceException.class,
                    () -> controller.saveProduct(getInitNotURLValidProduct()));
            assertAll(
                    () -> assertNotNull(response),
                    () -> assertEquals(RESPONSE_BAD_REQUEST, Integer.valueOf(response.getCode())),
                    () -> assertEquals(BAD_URL_ERROR, response.getMessage()));
        }
    }//nested class closure

    @Test
    void testUpdateProduct() {
        when(productService.updateProduct(getInitProduct())).thenReturn(null);
        ResponseEntity<ProductModel> response = controller.updateProduct(getInitProduct());
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(RESPONSE_CREATE, response.getStatusCodeValue()));
    }

    /**
     * get valid product
     * 
     * @return
     */
    private static ProductModel getInitProduct() {
        ProductModel productDto = new ProductModel();
        productDto.setSku("FAL-9999999");
        productDto.setPrincipal_image("https://example.com");
        return productDto;
    }

    /**
     * get not valid product
     * 
     * @return
     */
    private static ProductModel getInitNotValidProduct() {
        ProductModel productDto = new ProductModel();
        productDto.setSku("FAL-12");
        productDto.setPrincipal_image("https://example.com");
        return productDto;
    }

    /**
     * get not valid product
     * 
     * @return
     */
    private static ProductModel getInitNotURLValidProduct() {
        ProductModel productDto = new ProductModel();
        productDto.setSku("FAL-9999999");

        productDto.setOthers_image("bad_url");
        return productDto;
    }
}// class closure
