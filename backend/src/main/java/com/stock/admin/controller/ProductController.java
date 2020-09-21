package com.stock.admin.controller;

import com.stock.admin.model.entity.Product;
import com.stock.admin.model.request.ProductRequest;
import com.stock.admin.model.response.ProductResponse;
import com.stock.admin.model.response.ProductsResponse;
import com.stock.admin.model.response.ResponseStatus;
import com.stock.admin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


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
    public ProductsResponse getAllProducts(@RequestParam(required = false, name = "shopCode") String shopCode) {
        List<Product> products;
        if (shopCode != null) {
            products = productService.getAllProductsByShopId(shopCode);
        } else {
            products = productService.getAll();
        }

        ProductsResponse productsResponse = new ProductsResponse();
        productsResponse.setStatus(ResponseStatus.SUCCESS);
        productsResponse.setPayload(products);
        return productsResponse;
    }

    /**
     * Gets products details.
     *
     * @param productCode the product code
     * @return the products details
     */
    @RequestMapping(value = "/{productCode}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ProductResponse getProductsDetails(@PathVariable("productCode") String productCode) {
        Product product = productService.getByProductCode(productCode);
        ProductResponse productResponse = new ProductResponse();
        productResponse.setStatus(ResponseStatus.SUCCESS);
        productResponse.setPayload(product);
        return productResponse;
    }


    /**
     * Add product product response.
     *
     * @param productRequest the create product request
     * @return the product response
     */
    @RequestMapping(method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ProductResponse addProduct(@RequestBody ProductRequest productRequest) {
        Product addedProduct = productService.create(productRequest.getProduct());
        ProductResponse productResponseBody = new ProductResponse();
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
    public ProductResponse modifyByProductById(@PathVariable("id") String productCode, @Valid @RequestBody ProductRequest productRequest) {
        Product updatedProduct = productService.updateByProductCode(productCode, productRequest.getProduct());
        ProductResponse productResponseBody = new ProductResponse();
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