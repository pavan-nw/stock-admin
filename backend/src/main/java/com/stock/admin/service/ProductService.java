package com.stock.admin.service;

import static com.stock.admin.utils.StockAdminConstants.PRODUCT_DOES_NOT_EXISTS;

import com.stock.admin.exception.StockAdminApplicationException;
import com.stock.admin.model.entity.Product;
import com.stock.admin.repository.ProductsRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * The type Product service.
 */
@Service
public class ProductService {
	private final ProductsRepository productsRepository;

	/**
	 * Instantiates a new Product service.
	 *
	 * @param productsRepository the products repository
	 */
	@Autowired
	public ProductService(ProductsRepository productsRepository) {
		this.productsRepository = productsRepository;
	}

	/**
	 * Create product.
	 *
	 * @param product the create product request
	 * @return the product
	 */
	public Product create(Product product) {
		return productsRepository.save(product);
	}

	/**
	 * Gets all.
	 *
	 * @param pageable the pageable
	 * @return the all
	 */
	public Page<Product> getAll(Pageable pageable) {
		return productsRepository.findAll(pageable);
	}

	/**
	 * Gets by Product code.
	 *
	 * @param productCode the Product code
	 * @return the by Product code
	 */
	public Optional<Product> getByProductCode(String productCode) {
		return Optional.ofNullable(productsRepository.findByCode(productCode));
	}

	/**
	 * Gets by product name and packaging.
	 *
	 * @param productName the product name
	 * @param packaging   the packaging
	 * @return the by product name and packaging
	 */
	public Optional<Product> getByProductNameAndPackagingAndShopCode(String productName, String packaging,
			String shopCode) {
		return productsRepository.findByNameAndPackagingAndShopCode(productName, packaging, shopCode);
	}

	/**
	 * Gets all products by shop id.
	 *
	 * @param shopCode    the shop code
	 * @param pageRequest the page request
	 * @return the all products by shop id
	 */
	public Page<Product> getAllProductsByShopId(String shopCode, Pageable pageRequest) {
		return productsRepository.findByShopCode(shopCode, pageRequest);
	}

	/**
	 * Update by Product code product.
	 *
	 * @param productCode the prod code
	 * @param newProduct  the new product
	 * @return the product
	 */
	public Product updateByProductCode(String productCode, Product newProduct) {
		Product product = productsRepository.findByCode(productCode);
		if (product != null) {
			product.setName(newProduct.getName());
			product.setPackaging(newProduct.getPackaging());
			return productsRepository.save(product);
		}
		throw new StockAdminApplicationException(PRODUCT_DOES_NOT_EXISTS, HttpStatus.NOT_FOUND);
	}

	/**
	 * Delete.
	 *
	 * @param productCode the prod Code
	 * @return the product
	 */
	public Product deleteByProductCode(String productCode) {
		Product product = productsRepository.findByCode(productCode);
		if (product != null) {
			productsRepository.delete(product);
			return product;
		}
		throw new StockAdminApplicationException(PRODUCT_DOES_NOT_EXISTS, HttpStatus.NOT_FOUND);
	}
}
