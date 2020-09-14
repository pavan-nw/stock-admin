
package com.stock.admin;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.ProductService;

@SpringBootApplication
@RestController
public class StockAdminApplication {

	@Autowired
	private ProductService productService;

	public static void main(String[] args) {
		SpringApplication.run(StockAdminApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {

		//return String.format("Hello %s!", name);
		Product product=productService.create("prod001","productName001","shopCode");
		return product.toString();

	}

}
            