package cl.falabella.code.challengefreddyparedes.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import cl.falabella.code.challengefreddyparedes.entity.Product;
import cl.falabella.code.challengefreddyparedes.exception.ServiceException;
import cl.falabella.code.challengefreddyparedes.model.ProductModel;
import cl.falabella.code.challengefreddyparedes.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * This class handle interface IProductService in Product DB DAO
 * 
 * @author Freddy Paredes
 * @version 1.0
 */
@Slf4j
@Service
public class ProductServiceImpl implements IProductService {
    /**
     * Instance for DAO
     */
    @Autowired
    public ProductRepository productDAO;
    /**
     * Get mapper
     */
    private ObjectMapper mapper;

    /**
     * init() initialize the context
     */
    @PostConstruct
    public void init() {
        mapper = new ObjectMapper();
    }// Closure Method

    /**
     * @return DTO Product list
     */
    @Override
    public List<ProductModel> findAll() {
        log.info("Start method find all Products");
        // Get Entity List
        List<Product> productList = (List<Product>) productDAO.findAll();
        // Build DTO Response
        List<ProductModel> response = productList.stream().map(x -> {
            return mapper.convertValue(x, ProductModel.class);
        }).collect(Collectors.toList());

        log.info("End method find all Products with {} result(s).", response.size());
        return response;
    }// method closure

    /**
     * @return DTO Product 
     */
    @Override
    public ProductModel findProduct(String sku) {
        log.info("Start method find Product sku={}", sku);
        // Validate & Find Entity
        Product entityProduct = isPresentSKU(sku);
        // Build DTO Response
        ProductModel response = mapper.convertValue(entityProduct, ProductModel.class);
        log.info("End method find Product", response);
        return response;
    }// method closure

    /**
     * Save Product
     */
    @Override
    public ProductModel saveProduct(ProductModel data) {
        log.info("Start method save Product");
        Product entityProduct = mapper.convertValue(data, Product.class);
        entityProduct = productDAO.save(entityProduct);
        ProductModel response = mapper.convertValue(entityProduct, ProductModel.class);
        log.info("Start method save Product");
        return response;
    }// method closure

    /**
     * Update product
     */
    @Override
    public ProductModel updateProduct(ProductModel data) {
        log.info("Start method update Product");
        isPresentSKU(data.getSku());// Validate if Optional Entity Exist
        ProductModel savedProductDto = this.saveProduct(data);
        log.info("End method update Product");
        return savedProductDto;
    }// method closure

    @Override
    public ProductModel deleteProduct(String sku) {
        log.info("Start method delete Product sku {}", sku);
        productDAO.delete(isPresentSKU(sku));
        log.info("End method delete Product");
        return null;
    }// method closure

    /**
     * Validate if Optional Entity Exist
     * 
     * @param sku
     * @return Product
     */
    private Product isPresentSKU(String sku) {
        Optional<Product> findedProduct = productDAO.findBySku(sku);
        if (!findedProduct.isPresent()) {
            throw new ServiceException(String.valueOf(HttpStatus.BAD_REQUEST.value()),
                    "SKU " + sku + " not exist");
        }
        return findedProduct.get();
    }// method closure

}// class closure
