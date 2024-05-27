package com.vianny.dverivariant.services.products;

import java.util.Optional;

public interface AdminCapabilities<T> {
    void addProduct(T product, String urlImage);
    void updateProduct(T productNew);
    void deleteProduct(String id);
    Optional<T> findProductByID(String id);
}
