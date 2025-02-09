package app.tracker.repository;

import app.tracker.entity.Transaction; // Ensure this import is present
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}