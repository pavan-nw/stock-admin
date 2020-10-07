package com.stock.admin.controller;

import com.stock.admin.exception.StockAdminApplicationException;
import com.stock.admin.model.entity.Product;
import com.stock.admin.model.request.ProductRequest;
import com.stock.admin.model.response.Response;
import com.stock.admin.service.ProductService;
import com.stock.admin.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * The type Product controller.
 */
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final SequenceGeneratorService sequenceGeneratorService;


    /**
     * Instantiates a new Product controller.
     *
     * @param productService           the product service
     * @param sequenceGeneratorService the sequence generator service
     */
    @Autowired
    public ProductController(ProductService productService,SequenceGeneratorService sequenceGeneratorService) {
        this.productService = productService;
        this.sequenceGeneratorService=sequenceGeneratorService;
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
                .orElseThrow(() -> new StockAdminApplicationException("Product does not exists", HttpStatus.NOT_FOUND));
    }

    /**
     * Add product product response.
     *
     * @param productRequest the create product request
     * @return the product response
     */
    @PostMapping
    @ResponseBody
    public Response addProduct(@Valid @RequestBody ProductRequest productRequest) {
        productRequest.getProduct().setCode(sequenceGeneratorService.generateSequence(Product.SEQUENCE_NAME));
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
