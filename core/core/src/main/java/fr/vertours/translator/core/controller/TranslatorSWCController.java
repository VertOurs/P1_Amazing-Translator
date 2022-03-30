package fr.vertours.translator.core.controller;

import fr.vertours.translator.core.model.Num;
import fr.vertours.translator.core.service.NumberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;

@RestController
@RequestMapping("webSync")
public class TranslatorSWCController {

    private final NumberService service;
    private final RestTemplate restTemplate;

    public TranslatorSWCController(NumberService service, RestTemplate restTemplate) {
        this.service = service;
        this.restTemplate = restTemplate;
    }

    //http://localhost:8080/asynR/FR?nombre=8
    //http://localhost:8080/api/FR?nombre=8
    //http://localhost:8080/webSync/FR?nombre=8
    @GetMapping("{language}")
    public ResponseEntity<String> getTranslation(
            @PathVariable("language") String lang,
            @RequestParam("number") int num) {
        ParallelFlux<Num> flux = service.synWebClient(lang, num);
        return ResponseEntity.ok().body(service.synWebClient(lang, num).toString());
    }
}
