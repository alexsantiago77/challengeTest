package cl.falabella.code.challengefreddyparedes.presentation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.falabella.code.challengefreddyparedes.model.ProductModel;
import cl.falabella.code.challengefreddyparedes.service.IProductService;
import cl.falabella.code.challengefreddyparedes.utils.ValidatorUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * This class exposes
 * 
 * @author Freddy Paredes
 * @version 1.0
 */
@Slf4j
@RequestMapping("/v1/products")
@RestController
public class ProductsController {

    /**
     * Instance of Product service
     */
    @Autowired
    IProductService productService;

    /**
     * Get all Products
     * 
     * @return List<Products>
     */
    @GetMapping
    public ResponseEntity<List<ProductModel>> getProductsList() {
        log.info("Start endpoint find all products");
        List<ProductModel> response = productService.findAll();
        log.info("End endpoint find all products");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }// method closure

    /**
     * Get by id product
     * 
     * @return Products
     */
    @GetMapping("/{sku}")
    public ResponseEntity<ProductModel> getProduct(@PathVariable String sku) {
        log.info("Start endpoint find all products");
        ProductModel response = productService.findProduct(sku);
        log.info("End endpoint find all products");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }// method closure

    /**
     * Create Product
     * 
     * @return Products
     */
    @PostMapping
    public ResponseEntity<ProductModel> saveProduct(@Valid @RequestBody ProductModel data) {
        log.info("Start endpoint create product data={}", data);
        ValidatorUtils.validateDTO(data); // Double check DTO validation
        ProductModel response = productService.saveProduct(data);
        log.info("End endpoint create product");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }// method closure

    /**
     * Update Product
     * 
     * @return ResponseEntity<ProductModel>
     */
    @PutMapping
    public ResponseEntity<ProductModel> updateProduct(@Valid @RequestBody ProductModel data) {
        log.info("Start endpoint update product data={}", data);
        ValidatorUtils.validateDTO(data); // Double check DTO validation
        ProductModel response = productService.updateProduct(data);
        log.info("End endpoint update product");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }// method closure

    /**
     * delete Product
     * 
     * @return ResponseEntity<ProductModel>
     */
    @DeleteMapping("/{sku}")
    public ResponseEntity<ProductModel> deleteProduct(@PathVariable String sku) {
        log.info("Start endpoint delete product sku={}", sku);       
        ProductModel response = productService.deleteProduct(sku);
        log.info("End endpoint delete product");
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }// method closure

}// class closure
