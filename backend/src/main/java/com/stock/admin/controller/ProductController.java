package com.stock.admin.controller;

import com.stock.admin.exception.StockAdminApplicationException;
import com.stock.admin.model.entity.Product;
import com.stock.admin.model.request.ProductRequest;
import com.stock.admin.model.response.ErrorResponse;
import com.stock.admin.model.response.Response;
import com.stock.admin.model.response.ResponseStatus;
import com.stock.admin.service.ProductService;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

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
     * @throws Exception the exception
     */
    @RequestMapping(value = "/{productCode}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Response getProductsDetails(@PathVariable("productCode") String productCode) throws Exception {
        Optional<Product> product = productService.getByProductCode(productCode);
        return product.map(p -> Response.buildResponse(Product.type, p, true))
                .orElseThrow(() -> new StockAdminApplicationException(new
                        ErrorResponse(404, "Product does not exists"), HttpStatus.NOT_FOUND));
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
        return Response.buildResponse(Product.type, addedProduct, true);
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
        return Response.buildResponse(Product.type, updatedProduct, true);
    }

    /**
     * Delete by product by id.
     *
     * @param productCode the product code
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Response deleteByProductByCode(@PathVariable("id") String productCode) {
        return Response.buildResponse(Product.type, productService.deleteByProductCode(productCode), true);
    }
}
