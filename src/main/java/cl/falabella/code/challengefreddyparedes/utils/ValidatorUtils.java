package cl.falabella.code.challengefreddyparedes.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;

import cl.falabella.code.challengefreddyparedes.exception.ServiceException;
import cl.falabella.code.challengefreddyparedes.model.ProductModel;
import lombok.extern.slf4j.Slf4j;

/**
 * Validate URL
 */
@Slf4j
public class ValidatorUtils {

    private final static Integer SKU_MIN = 1000000;

    /**
     * Valdiate URL Format
     * 
     * @param url
     * @return
     */
    public static boolean isValidURL(String url) {
        log.info("=== Start to validate url {}", url);
        // Regex to check valid URL
        String regex = "((http|https)://)(www.)?"
                + "[a-zA-Z0-9@:%._\\+~#?&//=]"
                + "{2,256}\\.[a-z]"
                + "{2,6}\\b([-a-zA-Z0-9@:%"
                + "._\\+~#?&//=]*)";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        if (url == null) {
            return false;
        }
        Matcher m = p.matcher(url);
        Boolean response = m.matches();
        log.info("Url is {} valid", response ? "" : "not");
        return response;
    }// method closure

    /**
     * validate SKU min limits
     * 
     * @param sku
     * @return boolean
     */
    public static boolean valdiateMinSKU(String sku) {
        log.info("Start to valdiate SKU {}", sku);
        Integer isku = Integer.parseInt(sku.split("FAL-")[1]);
        if (isku < SKU_MIN) {
            return true;
        } else {
            return false;
        }
    }// method closure

    /**
     * Validate if is valid DTO
     * 
     * @param data
     * @return boolean
     */
    public static boolean validateDTO(ProductModel data) throws ServiceException {
        String url = data.getOthers_image();
        if (url != null && url.length() != 0 && !isValidURL(url)) {
            throw new ServiceException(String.valueOf(HttpStatus.BAD_REQUEST.value()),
                    "Others_images have a bad format");
        }
        if (valdiateMinSKU(data.getSku())) {
            throw new ServiceException(String.valueOf(HttpStatus.BAD_REQUEST.value()),
                    "SKU limit error");
        }
        return true;
    }// method closure
}// class closure
