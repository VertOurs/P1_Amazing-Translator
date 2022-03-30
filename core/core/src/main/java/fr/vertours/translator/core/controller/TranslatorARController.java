package fr.vertours.translator.core.controller;

import fr.vertours.translator.core.service.NumberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("asynR")
public class TranslatorARController {

    private final NumberService service;
    private final RestTemplate restTemplate;

    public TranslatorARController(NumberService service, RestTemplate restTemplate) {
        this.service = service;
        this.restTemplate = restTemplate;
    }

    /**
     * L'appel asynchrone utilisant restTemplate
     */

    //http://localhost:8080/asynR/FR?nombre=8
    @GetMapping("{language}")
    public ResponseEntity<String> getTranslation(
            @PathVariable("language") String lang,
            @RequestParam("number") int num) {

        return ResponseEntity.ok().body(service.aSynRestTemplate(lang, num).getTranslation());

    }
}
