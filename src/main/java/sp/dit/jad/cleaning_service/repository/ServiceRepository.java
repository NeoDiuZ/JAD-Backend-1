package sp.dit.jad.cleaning_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import sp.dit.jad.cleaning_service.model.ServiceEntity;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Integer> {

    // Find all services by category ID
    List<ServiceEntity> findByCategoryId(Integer categoryId);

    // Custom query to fetch all services with their categories
    @Query("SELECT s FROM ServiceEntity s JOIN FETCH s.category")
    List<ServiceEntity> findAllWithCategory();
}
