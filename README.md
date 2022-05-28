# Challenge-Freddy-Paredes

Challenge
mail: alexsantiago77@gmail.com

## DEPENDENCIAS

JPA, H2

## Tecnologías involucradas

* Java 11
* Spring Boot 2.2.6.RELEASE

## Ambientes y prerrequisitos

### Prerrequisitos

* **Java JDK 11**. 

Además, es necesario tener acceso a internet para que se puedan descargar las dependencias.
port default : 8080
### Maven

Para generar el artefacto y ejecutar las pruebas unitarias/integración, basta con ejecutar:

```text
./mvnw clean package
```
#### Endpoints

GET     /v1/products  -> Listar todos los products
GET     /v1/products/{SKU}  -> Encontrar producto por SKU
POST    /v1/products        -> Crear producto
PUT     /v1/products        -> Actualizar producto "unique sku id by payload"
DELETE  /v1/products/{SKU}  -> Eliminar producto



### PAYLOAD Example

```sh
{
    "sku": "FAL-9999999",
    "name": "ZAPATO",
    "brand": "NIKE",
    "size": 45.5,
    "price": 500.0,
    "principal_image": "https://example.com",
    "others_image": ""
}


