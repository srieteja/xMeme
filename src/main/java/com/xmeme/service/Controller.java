package com.xmeme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
public class Controller {

    @Autowired private RequestProcessor requestProcessor;

    @PostMapping("/memes")
    public UUID submitMeme(@RequestBody MemeType meme){
        System.out.println("weeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        return requestProcessor.saveMeme(meme);
    }

    @GetMapping("/memes/{id}")
    public @ResponseBody
    Optional<MemeType> getById(@PathVariable UUID id) throws Exception {
        System.out.println(id);
        return requestProcessor.getById(id);
    }

    @GetMapping("/memes")
    public @ResponseBody Iterable<MemeType> getMemes(){
        return requestProcessor.getLatestHundred();
    }

    @PatchMapping("/memes/{id}")
    public void editMeme(@PathVariable UUID id, @RequestBody URL url, String caption ) throws Exception {
        requestProcessor.editMeme(id, url, caption);
    }

    /*@PatchMapping("memes/{id}")
    public void editUrl(@PathVariable UUID id, @RequestBody URL url) throws Exception {
        requestProcessor.editUrl(id, url);
    }

    @PatchMapping("memes/{id}")
    public void editCaption(@PathVariable UUID id, @RequestBody String caption) throws Exception {
        requestProcessor.editCaption(id, caption);
    }*/


}
