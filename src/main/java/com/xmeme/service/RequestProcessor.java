package com.xmeme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RequestProcessor {

    @Autowired
    private MemeRepository memeRepository;

    private final AtomicInteger counter = new AtomicInteger(-1);

    Optional<MemeType> getById(UUID Id) throws Exception {
        if (memeRepository.existsById(Id)) return memeRepository.findById(Id);
        else throw new Exception("Meme Not Found");
    }

    UUID saveMeme(MemeType meme){
        meme.setSubmitDateTime(LocalDateTime.now());
        System.out.println(meme.getId());
        System.out.println(meme.toString());
        memeRepository.save(meme);
        return meme.getId();
    }

    Page<MemeType> getLatestHundred(){
        Pageable page = PageRequest.of(0, 100, Sort.by("datetime").descending());
        return memeRepository.getLatestHundred(page);
    }

    void editMeme(UUID id, URL url, String caption) throws Exception {
        if (memeRepository.existsById(id)) memeRepository.updateUrlCaption(id, url, caption);
        else throw new Exception("Meme not found");
    }

    void editCaption(UUID id, String caption) throws Exception{
        if (memeRepository.existsById(id)) memeRepository.updateCaption(id, caption);
        else throw new Exception("Meme does not exist");
    }

    void editUrl(UUID id, URL url) throws Exception {
        if (memeRepository.existsById(id)) memeRepository.updateUrl(id, url);
        else throw new Exception("Meme does not exist");
    }


}
