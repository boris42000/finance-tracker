package app.tracker.repository;

import app.tracker.entity.Category; // Ensure this import is present
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}