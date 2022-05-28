package cl.falabella.code.challengefreddyparedes.repository;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.falabella.code.challengefreddyparedes.entity.Product;

/**
 * This Interface handle all CRUD sentences of TB_PRODUCTS model
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{
   Optional<Product> findBySku(String sku);

}//interface closure
