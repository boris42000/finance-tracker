package app.tracker.repository;

import app.tracker.entity.User; // Ensure this import is present
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}