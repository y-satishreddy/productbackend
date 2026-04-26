package com.skylimit.Skylimit.service;

import com.skylimit.Skylimit.dto.product.*;
import com.skylimit.Skylimit.entity.Product;
import com.skylimit.Skylimit.exception.ProductNotFoundException;
import com.skylimit.Skylimit.mapper.ProductMapper;
import com.skylimit.Skylimit.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.util.Map;

@Service

public class ProductServiceImpl implements ProductService {
    private final RestClient restClient;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    public ProductServiceImpl(RestClient.Builder builder,ProductRepository productRepository,ProductMapper productMapper) {
        this.restClient=builder.build();
        this.productRepository=productRepository;
        this.productMapper=productMapper;
    }

    public ProductAddProductResponseDTO saveProduct(ProductAddProductRequestDTO productDTO) {

        Product repoProduct = productRepository.save(productMapper.toEntity(productDTO));
        return productMapper.toAddResponse(repoProduct);
    }

    public ProductGetProductResponseDTO getProduct(Long id) {
        Product repoProduct = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with id : " + id));

        return productMapper.toGetDetailsResponse(repoProduct);
    }
    public ProductGetProductResponseDTO partialUpdateProduct(
            Long id,
            ProductPartialUpdate dto
    ) {
        Product repoProduct = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found with id : " + id));

        if (dto.getName() != null) {
            repoProduct.setName(dto.getName());
        }

        if (dto.getSlug() != null) {
            repoProduct.setSlug(dto.getSlug());
        }

        if (dto.getCategoryId() != null) {
            repoProduct.setCategoryId(dto.getCategoryId());
        }

        if (dto.getBrandName() != null) {
            repoProduct.setBrandName(dto.getBrandName());
        }

        if (dto.getPrice() != null) {
            repoProduct.setPrice(dto.getPrice());
        }

        if (dto.getMrp() != null) {
            repoProduct.setMrp(dto.getMrp());
        }

        if (dto.getShortDescription() != null) {
            repoProduct.setShortDescription(dto.getShortDescription());
        }

        if (dto.getLongDescription() != null) {
            repoProduct.setLongDescription(dto.getLongDescription());
        }

        if (dto.getActive() != null) {
            repoProduct.setActive(dto.getActive());
        }

        if (dto.getFeatured() != null) {
            repoProduct.setFeatured(dto.getFeatured());
        }

        Product updatedProduct = productRepository.save(repoProduct);

        return productMapper.toGetDetailsResponse(updatedProduct);
    }
    public ProductGetProductResponseDTO updateProduct(Long id, ProductUpdateRequest productUpdateRequest){
        Product repoProduct=productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with id : " + id));
        repoProduct.setName(productUpdateRequest.getName());
        repoProduct.setSlug(productUpdateRequest.getSlug());
        repoProduct.setCategoryId(productUpdateRequest.getCategoryId());
        repoProduct.setBrandName(productUpdateRequest.getBrandName());
        repoProduct.setPrice(productUpdateRequest.getPrice());
        repoProduct.setMrp(productUpdateRequest.getMrp());
        repoProduct.setShortDescription(productUpdateRequest.getShortDescription());
        repoProduct.setLongDescription(productUpdateRequest.getLongDescription());
        repoProduct.setActive(productUpdateRequest.getActive());
        repoProduct.setFeatured(productUpdateRequest.getFeatured());
        Product response=productRepository.save(repoProduct);
        Map<String, Object> body = Map.of(
                "productId", 1,
                "eventType", "PRODUCT_UPDATED",
                "message", "product is updated successfully"
        );
        String result = restClient.post()
                .uri("http://localhost:8081/api/external/products")
                .header("correlationId", "my-secret-key")
                .body(body)
                .retrieve()
                .body(String.class);
        System.out.println("✅ Response: " + response);

        return productMapper.toGetDetailsResponse(response);
    }

}