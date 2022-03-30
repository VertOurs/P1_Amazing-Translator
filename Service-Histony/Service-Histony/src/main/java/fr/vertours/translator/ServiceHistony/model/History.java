package fr.vertours.translator.ServiceHistony.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class History {

    @Id
    @GeneratedValue
    private Long id;

    private Integer num;

    private String lang;

    private LocalDateTime dateOfHistory;


    public History(Integer num, String lang, LocalDateTime dateOfHistory) {
        this.num = num;
        this.lang = lang;
        this.dateOfHistory = dateOfHistory;
    }

    public History() {
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
    public void setNum(Integer num) {
        this.num = num;
    }

    public String getLang() {
        return lang;
    }
    public void setLang(String lang) {
        this.lang = lang;
    }

    public LocalDateTime getDateOfHistory() {
        return dateOfHistory;
    }
    public void setDateOfHistory(LocalDateTime date) {
        this.dateOfHistory = date;
    }
}
