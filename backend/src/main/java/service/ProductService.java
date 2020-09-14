package service;

import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ProductsRepository;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductsRepository productsRepository;

    //create operation
    public Product create(String prodCode,String name, String shopCode){
        return productsRepository.save(new Product(prodCode,name,shopCode));
    }

    //Retrieve Operation
    public List<Product> getAll(){
        return productsRepository.findAll();
    }

    public Product getByName(String productName){
        return productsRepository.findByName(productName);
    }

    //Update operation
    public Product update(String prodCode,String prodName, String shopCode){
        Product prod=productsRepository.findByCode(prodCode);
        prod.setName(prodName);
        prod.setShopCode(shopCode);
        return productsRepository.save(prod);
    }

    //Delete operation
    public void delete(String prodName){
        Product prod=productsRepository.findByName(prodName);
        productsRepository.delete(prod);

    }
}
