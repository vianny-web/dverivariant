package com.vianny.dverivariant.repositories.products;

import com.vianny.dverivariant.enums.TypeProducts;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface ProductsRepository<T> extends MongoRepository<T, String> {
    List<T> findByType(TypeProducts type);
}
