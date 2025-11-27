package com.app.woodshop.feature.product.service;

import com.app.woodshop.common.exception.AppException;
import com.app.woodshop.common.exception.ErrorCode;
import com.app.woodshop.feature.product.entity.Product;
import com.app.woodshop.feature.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {

    ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product create(Product product) {
        if (productRepository.existsByName(product.getName()))
            throw new AppException(ErrorCode.PRODUCT_EXISTS);

        return productRepository.save(product);
    }

    public Product findById(Long productID) {
        return productRepository.findById(productID)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NO_EXISTS));
    }

    public Product update(Product request) {
        if (!productRepository.existsById(request.getProductID()))
           throw new AppException(ErrorCode.PRODUCT_NO_EXISTS);

        return productRepository.save(request);
    }

    public void delete(Long productID) {
        Product product = productRepository.findById(productID)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NO_EXISTS));
        productRepository.delete(product);
    }
}
