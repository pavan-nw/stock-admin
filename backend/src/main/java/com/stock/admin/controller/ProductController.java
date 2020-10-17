package com.stock.admin.controller;

import static com.stock.admin.utils.Helper.pageRequestFor;
import static com.stock.admin.utils.StockAdminConstants.PRODUCT_DOES_NOT_EXISTS;

import com.stock.admin.exception.StockAdminApplicationException;
import com.stock.admin.model.entity.Product;
import com.stock.admin.model.request.ProductRequest;
import com.stock.admin.model.response.PagedResponse;
import com.stock.admin.model.response.Response;
import com.stock.admin.service.ProductService;
import java.util.Optional;
import javax.validation.Valid;
import com.stock.admin.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
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
     * @param pageNum  the page num
     * @param size     the size
     * @param sortBy   the sort by
     * @param sortType the sort type
     * @return the all products
     */
    @GetMapping
    @ResponseBody
    public Response getAllProducts(@RequestParam(name = "shopCode") Optional<String> shopCode,
                                   @RequestParam(name = "page", defaultValue = "1") int pageNum,
                                   @RequestParam(name = "size", defaultValue = "500") int size,
                                   @RequestParam(name = "sortBy", defaultValue = "code") String sortBy,
                                   @RequestParam(name = "sortType", defaultValue = "ASC") String sortType) {
        Pageable pageRequest = pageRequestFor(pageNum, size, sortType, sortBy);
        return shopCode
                .map(code -> {
                    Page<Product> page = productService.getAllProductsByShopId(code, pageRequest);
                    return PagedResponse.buildPagedResponse(Product.type, page);
                })
                .orElseGet(() -> {
                    Page<Product> page = productService.getAll(pageRequest);
                    return PagedResponse.buildPagedResponse(Product.type, page);
                });
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
