package cl.falabella.code.challengefreddyparedes.service;

import java.util.List;

import cl.falabella.code.challengefreddyparedes.model.ProductModel;

public interface IProductService {
    public List<ProductModel> findAll();    
    public ProductModel findProduct(String sku);    
    public ProductModel saveProduct(ProductModel data);    
    public ProductModel updateProduct(ProductModel data);    
    public ProductModel deleteProduct(String data);    
}//interface Closure
