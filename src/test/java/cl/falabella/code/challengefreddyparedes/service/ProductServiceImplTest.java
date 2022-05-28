package cl.falabella.code.challengefreddyparedes.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cl.falabella.code.challengefreddyparedes.entity.Product;
import cl.falabella.code.challengefreddyparedes.exception.ServiceException;
import cl.falabella.code.challengefreddyparedes.model.ProductModel;
import cl.falabella.code.challengefreddyparedes.repository.ProductRepository;

public class ProductServiceImplTest {

    private static final int RESPONSE_BAD_REQUEST = 400;
    private static final String ERROR_MESSAGE = "SKU FAL-9999999 not exist";

    @InjectMocks
    ProductServiceImpl controller;

    @Mock
    ProductRepository dao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testDeleteProduct() {
        doNothing()
                .when(dao)
                .delete(any(Product.class));
        ServiceException response = assertThrows(ServiceException.class,
                () -> controller.deleteProduct("FAL-9999999"));
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(RESPONSE_BAD_REQUEST, Integer.valueOf(response.getCode())),
                () -> assertEquals(ERROR_MESSAGE, response.getMessage()));
    }

    @Test
    void testFindAll() {
        doReturn(new ArrayList<Product>())
                .when(dao)
                .findAll();
        List<ProductModel> response = controller.findAll();
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(0, response.size()));
    }

    @Test
    void testFindProduct() {
        ServiceException response = assertThrows(ServiceException.class,
                () -> controller.deleteProduct("FAL-9999999"));
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(RESPONSE_BAD_REQUEST, Integer.valueOf(response.getCode())),
                () -> assertEquals(ERROR_MESSAGE, response.getMessage()));
    }


    @Test
    void testUpdateProduct() {
        doReturn(new Product())
                .when(dao)
                .save(any(Product.class));
        ServiceException response = assertThrows(ServiceException.class,
                () -> controller.deleteProduct("FAL-9999999"));
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(RESPONSE_BAD_REQUEST, Integer.valueOf(response.getCode())),
                () -> assertEquals(ERROR_MESSAGE, response.getMessage()));

    }



}// class closure
