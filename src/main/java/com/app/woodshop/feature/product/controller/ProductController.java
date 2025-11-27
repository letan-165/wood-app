package com.app.woodshop.feature.product.controller;

import com.app.woodshop.common.ApiResponse;
import com.app.woodshop.feature.product.entity.Product;
import com.app.woodshop.feature.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    ProductService productService;

    @GetMapping
    ApiResponse<List<Product>> findAll() {
        return ApiResponse.<List<Product>>builder()
                .message("Lấy danh sách sản phẩm")
                .result(productService.findAll())
                .build();
    }

    @PostMapping
    ApiResponse<Product> create(@RequestBody Product product) {
        product.setProductID(null);
        return ApiResponse.<Product>builder()
                .message("Tạo sản phẩm: " + product.getName())
                .result(productService.create(product))
                .build();
    }

    @GetMapping("/{productID}")
    ApiResponse<Product> findById(@PathVariable Long productID) {
        return ApiResponse.<Product>builder()
                .message("Lấy sản phẩm:" + productID)
                .result(productService.findById(productID))
                .build();
    }

    @PutMapping
    ApiResponse<Product> update(@RequestBody Product product) {
        return ApiResponse.<Product>builder()
                .message("Cập nhật sản phẩm: " + product.getProductID())
                .result(productService.update(product))
                .build();
    }

    @DeleteMapping("/{productID}")
    ApiResponse<String> delete(@PathVariable Long productID) {
        productService.delete(productID);
        return ApiResponse.<String>builder()
                .message("Xóa sản phẩm: " + productID)
                .build();
    }
}
