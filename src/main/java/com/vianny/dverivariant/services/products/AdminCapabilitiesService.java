package com.vianny.dverivariant.services.products;

import java.util.Optional;

public interface AdminCapabilitiesService<T> {
    void saveProduct(T product);
    void updateProduct(T product);
    void deleteProduct(String id);
    Optional<T> findProductByID(String id);
}
