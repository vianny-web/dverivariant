package com.vianny.dverivariant.services.products;

import java.util.Optional;

public interface AdminCapabilitiesService<T> {
    void addProduct(T product);
    void updateProduct(T productNew);
    void deleteProduct(String id);
    Optional<T> findProductByID(String id);
}
