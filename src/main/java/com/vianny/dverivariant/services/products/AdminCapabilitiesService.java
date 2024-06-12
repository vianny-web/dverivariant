package com.vianny.dverivariant.services.products;

import java.util.Optional;

public interface AdminCapabilitiesService<T> {
    void saveOrUpdateProduct(T product);
    void deleteProduct(String id);
    Optional<T> findProductByID(String id);
}
