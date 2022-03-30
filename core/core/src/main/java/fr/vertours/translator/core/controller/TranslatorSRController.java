package fr.vertours.translator.core.controller;

import fr.vertours.translator.core.service.NumberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("api")
public class TranslatorSRController {

    private final NumberService service;
    private final RestTemplate restTemplate;

    public TranslatorSRController(NumberService service, RestTemplate restTemplate) {
        this.service = service;
        this.restTemplate = restTemplate;
    }
//    localhost:8080/api/1


    //http://localhost:8080/api/FR?nombre=8
    @GetMapping("{language}")
    public ResponseEntity<String> getTranslation(
            @PathVariable("language") String lang,
            @RequestParam("number") int num) {
        System.out.println("je suis la");
        return ResponseEntity.ok().body(service.getTranslationSynchroneRestTemplate(lang, num).getTranslation());
    }


}
