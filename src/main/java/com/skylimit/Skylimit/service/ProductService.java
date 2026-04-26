package com.skylimit.Skylimit.service;

import com.skylimit.Skylimit.dto.product.*;

public interface ProductService {
    public ProductAddProductResponseDTO saveProduct(ProductAddProductRequestDTO productDTO);
    public ProductGetProductResponseDTO getProduct(Long id);
    public ProductGetProductResponseDTO partialUpdateProduct(Long id, ProductPartialUpdate productPartialUpdateDTO);
    public ProductGetProductResponseDTO updateProduct(Long id, ProductUpdateRequest updateProductRequest);
}