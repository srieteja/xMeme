package com.xmeme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class Controller {

    @Autowired private RequestProcessor requestProcessor;


    @PostMapping("/memes")
    public long submitMeme (@RequestBody MemeType meme){
        System.out.println("weeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        return requestProcessor.saveMeme(meme);
    }

    @GetMapping("/memes/{id}")
    public @ResponseBody
        Optional<MemeType> getById (@PathVariable long id) throws Exception {
        System.out.println(id);
        return requestProcessor.getById(id);
    }

    @GetMapping("/memes")
    public @ResponseBody Iterable<MemeType> getMemes (@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "100") int pageSize) {
        return requestProcessor.getLatestHundred(pageNo, pageSize);
    }

    @PatchMapping("/memes/{id}")
    public void editMeme (@PathVariable long id, @RequestBody MemeType meme ) throws Exception {
        meme.setId(id);
        requestProcessor.editMeme(meme);
    }
}


