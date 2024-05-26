package com.vianny.dverivariant.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ProductsRepository<T> extends MongoRepository<T, String> {
}
