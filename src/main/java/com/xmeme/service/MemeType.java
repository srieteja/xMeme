package com.xmeme.service;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.net.URL;
import java.time.LocalDateTime;

@Entity
@Component
@Table(name = "meme_table")
public class MemeType implements java.io.Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "submittedby", nullable = false)
    private String submittedBy;

    @Column(name = "url", nullable = false)
    private URL url;

    @Column(name = "caption", nullable = false)
    private String caption;

    @Column(name = "datetime", nullable = false)
    private LocalDateTime submitDateTime;

    public MemeType() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(String submittedBy) {
        this.submittedBy = submittedBy;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public LocalDateTime getSubmitDateTime() {
        return submitDateTime;
    }

    public void setSubmitDateTime(LocalDateTime submitDateTime) {
        this.submitDateTime = submitDateTime;
    }

    @Override
    public String toString() {
        return "Meme " + id + ' ' + submittedBy + ' ' + caption + ' ' + submitDateTime;
    }

}