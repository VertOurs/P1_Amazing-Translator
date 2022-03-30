package fr.vertours.translator.core.repository;

import fr.vertours.translator.core.model.Num;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NumRepository extends JpaRepository<Num, Long> {

    Num findByLanguageAndNum(String lang, int num);
}