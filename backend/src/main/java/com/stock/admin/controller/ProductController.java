package com.stock.admin.controller;

import com.stock.admin.model.entity.Product;
import com.stock.admin.model.request.ProductRequest;
import com.stock.admin.model.response.ProductResponse;
import com.stock.admin.model.response.ProductsResponse;
import com.stock.admin.model.response.Response;
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
     * @return the all products
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ProductsResponse getAllProducts() {
        List<Product> products = productService.getAll();
        ProductsResponse productsResponse = new ProductsResponse();
        productsResponse.setStatus(Response.ResponseStatus.SUCCESS);
        productsResponse.setPayload(products);
        return productsResponse;
    }


    /**
     * Gets all products of a specific shop.
     *
     * @param shopCode the shop code
     * @return the all products of a specific shop
     */
    @RequestMapping(value = "/shops/{shopId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ProductsResponse getAllProductsOfASpecificShop(@PathVariable("shopId") String shopCode) {
        List<Product> products = productService.getAllProductsByShopId(shopCode);
        ProductsResponse productsResponse = new ProductsResponse();
        productsResponse.setStatus(Response.ResponseStatus.SUCCESS);
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
        Product product = productService.getByProductId(productCode);
        ProductResponse productResponse = new ProductResponse();
        productResponse.setStatus(Response.ResponseStatus.SUCCESS);
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
        Product addedProduct = productService.create(productRequest);
        ProductResponse productResponseBody = new ProductResponse();
        productResponseBody.setPayload(addedProduct);
        productResponseBody.setStatus(Response.ResponseStatus.SUCCESS);
        return productResponseBody;
    }


    /**
     * Modify by product by id product response.
     *
     * @param productId      the product id
     * @param productRequest the product request
     * @return the product response
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ProductResponse modifyByProductById(@PathVariable("id") String productId, @Valid @RequestBody ProductRequest productRequest) {
        Product updatedProduct = productService.updateByProductId(productId, productRequest.getProduct());
        ProductResponse productResponseBody = new ProductResponse();
        productResponseBody.setPayload(updatedProduct);
        productResponseBody.setStatus(Response.ResponseStatus.SUCCESS);
        return productResponseBody;
    }

}