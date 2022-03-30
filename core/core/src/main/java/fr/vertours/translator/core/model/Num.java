package fr.vertours.translator.core.model;

import javax.persistence.*;

@Entity
@Table(name = "num")
public class Num {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer num;
    private String language;
    private String translation;

    public Num(String translation, String language, Integer num ) {
        this.num = num;
        this.language = language;
        this.translation = translation;
    }

    public Num() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNum() {
        return num;
    }
    public void setNum(Integer number) {
        this.num = number;
    }

    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTranslation() {
        return translation;
    }
    public void setTranslation(String translation) {
        this.translation = translation;
    }
}
