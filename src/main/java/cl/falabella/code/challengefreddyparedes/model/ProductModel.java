package cl.falabella.code.challengefreddyparedes.model;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * PRODUCT DTO
 * 
 * @author Freddy Paredes
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductModel {
 
    // Mandatory
    @Pattern(regexp = "FAL-[0-9]{6,7}")
    @NotNull
    private String sku;
    // Mandatory
    @Size(min=3 ,max = 50)
    @NotNull
    private String name;
    // Mandatory
    @NotNull
    private String brand;
    // Not mandatory  
    @DecimalMin("1") 
    @NotNull
    private Float size;
    // Mandatory
    @DecimalMin("1")
    @DecimalMax("99999999")
    @NotNull
    private Float price;
    // Mandatory
    @Size
    @NotNull
    //URL FORMAT
    @Pattern(regexp = "((http|https)://)(www.)?[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)")
    private String principal_image;
    // Not mandatory
    private String others_image;

}// class closure
