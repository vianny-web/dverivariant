package com.vianny.dverivariant.repositories.products.doors;

import com.vianny.dverivariant.enums.TypeProducts;
import com.vianny.dverivariant.models.products.doors.InteriorDoor;
import com.vianny.dverivariant.repositories.products.ProductsRepository;

import java.util.List;

public interface InteriorDoorRepository<T> extends ProductsRepository<InteriorDoor> {
    List<T> findByType(TypeProducts type);
}
