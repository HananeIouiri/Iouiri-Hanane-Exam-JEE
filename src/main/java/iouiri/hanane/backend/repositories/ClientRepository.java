package iouiri.hanane.backend.repositories;

import iouiri.hanane.backend.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}