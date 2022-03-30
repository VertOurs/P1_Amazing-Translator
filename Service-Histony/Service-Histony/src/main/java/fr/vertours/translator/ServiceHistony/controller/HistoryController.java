package fr.vertours.translator.ServiceHistony.controller;

import fr.vertours.translator.ServiceHistony.HistoryService;
import fr.vertours.translator.ServiceHistony.model.History;
import fr.vertours.translator.core.model.Num;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class HistoryController {

    private HistoryService service;

    public HistoryController(HistoryService service) {
        this.service = service;
    }




    @PostMapping("/historique")
    public void createHistory(@RequestBody Num num) {
        service.createHistory(num);
    }
}