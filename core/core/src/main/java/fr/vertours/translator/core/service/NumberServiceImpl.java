package fr.vertours.translator.core.service;

import fr.vertours.translator.core.exception.InaccurateNumberOrLangException;
import fr.vertours.translator.core.model.Num;
import fr.vertours.translator.core.repository.NumRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;

import javax.transaction.Transactional;
import java.util.Optional;

import static fr.vertours.translator.core.util.CaseConverter.verifyUpperCase;

@Service
public class NumberServiceImpl implements NumberService {

    private final NumRepository repository;
    private final RestTemplate restTemplate;
    private final WebClient.Builder webBuilderBuilder;

    public NumberServiceImpl(NumRepository repository, RestTemplate restTemplate, WebClient.Builder webBuilderBuilder) {
        this.repository = repository;
        this.restTemplate = restTemplate;
        this.webBuilderBuilder = webBuilderBuilder;
    }

    @Override
    @Transactional
    public Num getTranslationSynchroneRestTemplate(String lang, int num) {
        String lang1 = verifyUpperCase(lang);
        Num number = Optional.ofNullable(repository.findByLanguageAndNum(lang1, num))
                .orElseThrow(() -> new InaccurateNumberOrLangException());
        restTemplate.postForObject("http://histony/historique",number, Num.class);
        return number;
    }

    @Override
    @Transactional
    public Num aSynRestTemplate(String lang, int num) {
        String lang1 = verifyUpperCase(lang);
        Num number = Optional.ofNullable(repository.findByLanguageAndNum(lang1, num))
                .orElseThrow(() -> new InaccurateNumberOrLangException());

        Thread thread1 = new Thread((Runnable) restTemplate.postForObject("http://histony/historique",number, Num.class));
        thread1.start();
        return number;
    }
    @Override
    @Transactional
    public ParallelFlux<Num> synWebClient(String lang, int num) {
        String lang1 = verifyUpperCase(lang);
        Num number = Optional.ofNullable(repository.findByLanguageAndNum(lang1, num))
                .orElseThrow(() -> new InaccurateNumberOrLangException());

        final WebClient webClient = webBuilderBuilder.baseUrl("http://histony").build();

        Mono<Num> numMono = webClient.post()
                .uri("/historique")
                .body(Mono.just(number), Num.class)
                .retrieve()
                .bodyToMono(Num.class);

        final Flux<Num> numFlux = Flux.concat(numMono);
        ParallelFlux<Num> numParallelFlux = numFlux.parallel().runOn(Schedulers.boundedElastic());

        return numParallelFlux;
    }
}

