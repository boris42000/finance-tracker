package app.tracker.repository;

import app.tracker.entity.Account; // Ensure this import is present
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}