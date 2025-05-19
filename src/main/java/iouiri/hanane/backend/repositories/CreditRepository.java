package iouiri.hanane.backend.repositories;

import iouiri.hanane.backend.entities.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditRepository extends JpaRepository<Credit, Long> {
}
