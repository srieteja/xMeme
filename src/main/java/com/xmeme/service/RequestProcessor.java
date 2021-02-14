package com.xmeme.service;

import com.xmeme.exception.ImproperConstraintException;
import com.xmeme.exception.MemeAlreadyExists;
import com.xmeme.exception.MemeNotFound;
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
        ifMemeExists(meme.getSubmittedBy(), meme.getUrl(), meme.getCaption());
        checkConstraints(meme);
        meme.setSubmitDateTime(LocalDateTime.now());
        memeRepository.save(meme);
        System.out.println(meme.toString());
        return meme.getId();
    }

    Page<MemeType> getLatestHundred(int pageNo, int pageSize){
        Pageable page = PageRequest.of(pageNo, pageSize, Sort.by("datetime").descending());
        return memeRepository.getLatestHundred(page);
    }

    void editMeme(MemeType meme) throws MemeNotFound {
        if (memeRepository.existsById(meme.getId())) {
            if (meme.getUrl() != null && meme.getCaption() != null) memeRepository.updateUrlCaption(meme.getId(), meme.getUrl(), meme.getCaption());
            else if (meme.getUrl() == null) memeRepository.updateCaption(meme.getId(), meme.getCaption());
            else memeRepository.updateUrl(meme.getId(), meme.getUrl());
        }
        else throw new MemeNotFound();
    }


    void ifMemeExists(String submittedBy, URL url, String caption){
        MemeType meme;
        try {
            meme = memeRepository.ifMemeExists(submittedBy, url, caption);
            if (meme.getId() != null) throw new MemeAlreadyExists();

        }
        catch (Exception e){
            e.getMessage();
        }
    }

    void checkConstraints(MemeType meme){
        if (meme.getSubmittedBy() != null && meme.getCaption() != null && meme.getUrl() != null);
        else throw new ImproperConstraintException();
    }

}
