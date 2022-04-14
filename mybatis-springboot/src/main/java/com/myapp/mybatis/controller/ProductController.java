package com.myapp.mybatis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.mybatis.mapper.ProductMapper;
import com.myapp.mybatis.mapper.UserMapper;
import com.myapp.mybatis.model.Product;
import com.myapp.mybatis.model.User;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	
	private ProductMapper productMapper;
	
	//생성자 주입 (객체를 생성자 주입으로 입력시 @Autowired 필요없음, 권장 )
	public ProductController(ProductMapper productMapper) {
		this.productMapper = productMapper;
	}
	
	@GetMapping("/{id}")
	public Product getProduct(@PathVariable("id") Long id) {
		Product product = productMapper.selectProductById(id);
		return product;
	}
	
	@GetMapping
	public List<Product> getProductList(){
		List<Product> productList = productMapper.selectAllProducts();
		return productList;
	}
	
	@PostMapping			//파라메터 prodName, prodPrice
	public void creatProduct(@RequestParam("name") String prodName, 
							 @RequestParam("price") int prodPrice	) {	
		
		productMapper.insertProduct(new Product(prodName, prodPrice));
	}
	
	@PutMapping("/{id}")
	public void editProduct(@PathVariable("id") Long id,
						@RequestParam("name") String prodName,
						@RequestParam("price") int prodPrice ) {
		
		productMapper.updateProduct(new Product(id, prodName, prodPrice));
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") Long id) {
		productMapper.deleteProductById(id);
	}
}





