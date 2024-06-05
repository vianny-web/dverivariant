package com.vianny.dverivariant.services.products;

import com.vianny.dverivariant.dto.response.product.ProductBriefDTO;
import com.vianny.dverivariant.dto.response.product.ProductDetailsDTO;
import com.vianny.dverivariant.enums.TypeProducts;

import java.util.List;

public interface ProductRetrievalService<T> {
    List<ProductBriefDTO> getAllProductsByType(TypeProducts type);
    ProductDetailsDTO getProductById(String id);
}
