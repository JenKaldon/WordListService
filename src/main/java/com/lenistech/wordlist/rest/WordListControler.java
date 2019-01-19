package com.lenistech.wordlist.rest;

import com.lenistech.wordlist.service.WordListService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class WordListControler {
    private WordListService wordListService = new WordListService();

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/api/v1/words")
    @ResponseBody
    public List<String> getWordList(@RequestParam() int numWords){
        if(!wordListService.isWordListImported()){
            wordListService.importFullWordList("Animals.txt");
        }
        return  wordListService.getPartialWordSet(numWords);
    }
}
