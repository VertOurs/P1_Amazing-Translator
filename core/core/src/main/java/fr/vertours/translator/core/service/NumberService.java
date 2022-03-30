package fr.vertours.translator.core.service;

import fr.vertours.translator.core.model.Num;
import reactor.core.publisher.ParallelFlux;

public interface NumberService {

    Num getTranslationSynchroneRestTemplate(String lang, int num);
    Num aSynRestTemplate(String lang, int num);
    ParallelFlux<Num> synWebClient(String lang, int num);
}
