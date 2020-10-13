package com.stock.admin.controller;

import static com.stock.admin.utils.StockAdminConstants.PRODUCT_DOES_NOT_EXISTS;

import com.stock.admin.exception.StockAdminApplicationException;
import com.stock.admin.model.entity.Product;
import com.stock.admin.model.request.ProductRequest;
import com.stock.admin.model.response.Response;
import com.stock.admin.service.ProductService;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Product controller.
 */
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    /**
     * Instantiates a new Product controller.
     *
     * @param productService the product service
     */
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Gets all products.
     *
     * @param shopCode the shop code
     * @return the all products
     */
    @GetMapping
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
    @GetMapping(value = "/{productCode}")
    @ResponseBody
    public Response getProductsDetails(@PathVariable("productCode") String productCode) {
        Optional<Product> product = productService.getByProductCode(productCode);
        return product.map(p -> Response.buildResponse(Product.type, p, true))
                .orElseThrow(() -> new StockAdminApplicationException(PRODUCT_DOES_NOT_EXISTS, HttpStatus.NOT_FOUND));
    }

    /**
     * Add product product response.
     *
     * @param productRequest the create product request
     * @return the product response
     */
    @PostMapping
    @ResponseBody
    public Response addProduct(@RequestBody ProductRequest productRequest) {
        Product addedProduct = productService.create(productRequest.getProduct());
        return Response.buildResponse(Product.type, addedProduct, true);
    }

    /**
     * Modify by product by id product response.
     *
     * @param productCode    the product code
     * @param productRequest the product request
     * @return the product response
     */
    @PutMapping(value = "/{id}")
    @ResponseBody
    public Response modifyByProductById(@PathVariable("id") String productCode, @Valid @RequestBody ProductRequest productRequest) {
        Product updatedProduct = productService.updateByProductCode(productCode, productRequest.getProduct());
        return Response.buildResponse(Product.type, updatedProduct, true);
    }

    /**
     * Delete by product by id.
     *
     * @param productCode the product code
     * @return the response
     */
    @DeleteMapping(value = "/{id}")
    public Response deleteByProductByCode(@PathVariable("id") String productCode) {
        return Response.buildResponse(Product.type, productService.deleteByProductCode(productCode), true);
    }
}
