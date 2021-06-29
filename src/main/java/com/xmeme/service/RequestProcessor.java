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

import javax.persistence.NonUniqueResultException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RequestProcessor {

    @Autowired
    private MemeRepository memeRepository;

    private final AtomicInteger counter = new AtomicInteger(-1);

    Optional<MemeType> getById(long Id) throws Exception {
        Optional<MemeType> meme;
        if (memeRepository.existsById(Id)) {
            meme = memeRepository.findById(Id);
            System.out.println(meme.toString());
            return meme;
        }
        else throw new Exception("Meme Not Found");
    }

    long saveMeme(MemeType meme){
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
        String url = meme.getUrl().toString();
        System.out.println("before"+meme.toString());
        if (memeRepository.existsById(meme.getId())) {

            if (!(url.isEmpty()) && !(meme.getCaption().isEmpty())) memeRepository.updateUrlCaption(meme.getId(), meme.getUrl(), meme.getCaption());
            else if (url.isEmpty()) memeRepository.updateCaption(meme.getId(), meme.getCaption());
            else memeRepository.updateUrl(meme.getId(), meme.getUrl());
            System.out.println(meme.toString());
        }
        else throw new MemeNotFound();
    }


    void ifMemeExists(String submittedBy, URL url, String caption){
        MemeType meme;
            try {
                meme = memeRepository.ifMemeExists(submittedBy, url, caption);
                if (!(meme.getSubmittedBy().isEmpty())) throw new MemeAlreadyExists();
            }
            catch (NonUniqueResultException e){
                throw new MemeAlreadyExists();
            }
            catch (NullPointerException e){

            }

    }

    void checkConstraints(MemeType meme){
        String url = meme.getUrl().toString();
        if (meme.getSubmittedBy().isEmpty() || meme.getCaption().isEmpty() || url.isEmpty()) throw new ImproperConstraintException();
    }

}
