package cl.falabella.code.challengefreddyparedes.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * This class envolves data model of Products
 * @author Freddy Paredes
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "TB_PRODUCTS")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

	@Id
    @Column(name ="SKU", nullable = false, unique = true)
	private String sku;
    @Column(name ="NAMEPRODUCT" , nullable = false)
    private String name;
    @Column(name ="BRAND", nullable = false)
    private String brand;
    @Column(name ="SIZE", nullable = false)
    private Float size;
    @Column(name ="PRICE", nullable = false, precision = 8, scale = 2)
    private Float price;
    @Column(name ="PRINCIPAL_IMAGE", nullable = false)
    private String principal_image;
    @Column(name ="OTHERS_IMAGE")
    private String others_image;

}//Class closure