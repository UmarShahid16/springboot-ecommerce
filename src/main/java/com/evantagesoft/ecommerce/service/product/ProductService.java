package com.evantagesoft.ecommerce.service.product;

import com.evantagesoft.ecommerce.dto.ProductDto;
import com.evantagesoft.ecommerce.entity.Product;
import com.evantagesoft.ecommerce.response.Response;

import java.util.List;

public interface ProductService {
    Response addProduct(ProductDto productDto);

    List<Product> getProductsByCategory(Long categoryId);
}
