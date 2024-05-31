package com.vianny.dverivariant.controllers.user;

import com.vianny.dverivariant.dto.response.product.ProductDetailsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface ProductRetrievalController {
    ResponseEntity<ProductDetailsDTO> getProduct(@RequestParam String id);
}
