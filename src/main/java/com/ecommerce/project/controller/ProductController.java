package com.ecommerce.project.controller;


import com.ecommerce.project.payload.ProductDTO;
import com.ecommerce.project.payload.ProductResponse;
import com.ecommerce.project.service.ProductService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/admin/categories/{categoryId}/product")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody  ProductDTO productDTO, @PathVariable Long categoryId)
    {

        ProductDTO savedProductDTO = productService.addProuct(categoryId, productDTO);
        return new ResponseEntity<>(savedProductDTO, HttpStatus.CREATED);
    }

    @GetMapping("/public/product")
    public ResponseEntity<ProductResponse> getAllProducts()
    {
        ProductResponse productResponse=  productService.getAllProducts();
        return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }




    @PutMapping("/admin/products/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long productId,@RequestBody ProductDTO productDTO
                                                    )
    {
        ProductDTO updateproductDTO = productService.updateProduct(productId,productDTO);

        return new ResponseEntity<>(updateproductDTO,HttpStatus.OK);
    }






    @GetMapping("/public/categories/{categoryId}/products")
    public ResponseEntity<ProductResponse> getProductsByCategory(@PathVariable Long categoryId)
    {
        ProductResponse productResponse = productService.searchByCategory(categoryId);
        return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }

    @GetMapping("public/products/keyword/{keyword}")
    public ResponseEntity<ProductResponse> getProductsByKeyword(@PathVariable String keyword)
    {
        ProductResponse productResponse = productService.searchProductByKeyword('%' + keyword + '%' );//% used in pttrn matching
        return new ResponseEntity<>(productResponse, HttpStatus.FOUND);
    }

    @DeleteMapping("/admin/products/{productId}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long productId){
        ProductDTO deleteProduct = productService.deleteProduct(productId);
        return new ResponseEntity<>(deleteProduct, HttpStatus.OK);
    }


}
