package fr.vertours.translator.ServiceHistony;

import fr.vertours.translator.ServiceHistony.model.History;
import fr.vertours.translator.ServiceHistony.repository.HistoryRepository;
import fr.vertours.translator.core.model.Num;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistoryService {
    private HistoryRepository repository;

    public HistoryService(HistoryRepository repository) {
        this.repository = repository;
    }

    public List<History> getAllHistory() {
        return repository.findAll();
    }

    public void createHistory(Num num) {
        repository.save(new History(num.getNum(), num.getLanguage(), LocalDateTime.now()));
    }
}
