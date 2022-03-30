package fr.vertours.translator.ServiceHistony.repository;

import fr.vertours.translator.ServiceHistony.model.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {
}
