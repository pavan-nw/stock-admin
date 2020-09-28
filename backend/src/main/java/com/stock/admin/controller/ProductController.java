package com.stock.admin.controller;

import com.stock.admin.model.entity.Product;
import com.stock.admin.model.request.ProductRequest;
import com.stock.admin.model.response.*;
import com.stock.admin.model.response.ResponseStatus;
import com.stock.admin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.WebApplicationException;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;


/**
 * The type Product controller.
 */
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * Gets all products.
     *
     * @param shopCode the shop code
     * @return the all products
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Response getAllProducts(@RequestParam(required = false, name = "shopCode") String shopCode) {
        if (shopCode != null) {
            return Response.buildResponse(Product.type, productService.getAllProductsByShopId(shopCode), true);
        }
            return Response.buildResponse(Product.type, productService.getAll(), true);
    }

    /**
     * Gets products details.
     *
     * @param productCode the product code
     * @return the products details
     */
    @RequestMapping(value = "/{productCode}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Response getProductsDetails(@PathVariable("productCode") String productCode) throws Exception {
        Optional<Product> product = productService.getByProductCode(productCode);
        return product.map(p -> Response.buildResponse(Product.type, p, true))
                .orElseThrow(() -> new Exception(HttpStatus.NOT_FOUND.getReasonPhrase()));

    }


    /**
     * Add product product response.
     *
     * @param productRequest the create product request
     * @return the product response
     */
    @RequestMapping(method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Response addProduct(@RequestBody ProductRequest productRequest) {
        Product addedProduct = productService.create(productRequest.getProduct());
        Response productResponseBody = new Response();
        productResponseBody.setPayload(addedProduct);
        productResponseBody.setStatus(ResponseStatus.SUCCESS);
        return productResponseBody;
    }


    /**
     * Modify by product by id product response.
     *
     * @param productCode    the product code
     * @param productRequest the product request
     * @return the product response
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Response modifyByProductById(@PathVariable("id") String productCode, @Valid @RequestBody ProductRequest productRequest) {
        Product updatedProduct = productService.updateByProductCode(productCode, productRequest.getProduct());
        Response productResponseBody = new Response();
        productResponseBody.setPayload(updatedProduct);
        productResponseBody.setStatus(ResponseStatus.SUCCESS);
        return productResponseBody;
    }


    /**
     * Delete by product by id.
     *
     * @param productCode the product code
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteByProductByCode(@PathVariable("id") String productCode) {
        productService.deleteByProductCode(productCode);
    }

}