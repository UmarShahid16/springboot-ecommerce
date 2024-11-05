package com.evantagesoft.ecommerce.service.product;

import com.evantagesoft.ecommerce.dto.ProductDto;
import com.evantagesoft.ecommerce.entity.Category;
import com.evantagesoft.ecommerce.entity.Product;
import com.evantagesoft.ecommerce.repository.CategoryRepository;
import com.evantagesoft.ecommerce.repository.ProductRepository;
import com.evantagesoft.ecommerce.response.Response;
import com.evantagesoft.ecommerce.service.category.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Response addProduct(ProductDto productDto) {
        if (productDto.getCategoryId() == null) {
            return new Response("400", "Category ID must not be null");
        }


        return categoryRepository.findById(productDto.getCategoryId())
                .map(category -> {
                    Product product = toEntity(productDto);
                    product.setCategory(category);
                    productRepository.save(product);
                    return new Response("200", "Product Added Successfully");
                })
                .orElse(new Response("400", "Category Not Found with Id: " + productDto.getCategoryId()));

//        Optional<Category> categoryOptional = categoryRepository.findById(productDto.getCategoryId());
//        if (categoryOptional.isPresent()){
//            Category category = categoryOptional.get();
//
//            Product product = toEntity(productDto);
//            product.setCategory(category);
//
//            productRepository.save(product);
//
//            return new Response("200","Product Added Succesfully");
//
//        }else {
//            return new Response("400","Category Not Found with Id: " + productDto.getCategoryId());
//        }
    }

    @Override
    public List<Product> getProductsByCategory(Long categoryId) {

        return productRepository.findByCategoryId(categoryId);
    }


    public Product toEntity(ProductDto productDto){
        return modelMapper.map(productDto, Product.class);
    }

    public ProductDto toDto(Product product){
        return modelMapper.map(product, ProductDto.class);
    }
}
