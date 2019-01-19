package com.lenistech.wordlist.service;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class WordListService {
    private ArrayList<String> fullWordList = new ArrayList<>();
    public boolean wordListImported=false;
    public WordListService(){};

    public boolean isWordListImported(){
        return wordListImported;
    }

    public void importFullWordList(String fileName){
        try {
            InputStream inputStream = ClassLoader.getSystemResourceAsStream(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String nextWord;
            ArrayList<String> wordSet = new ArrayList<>();
            while((nextWord = reader.readLine()) != null){
                wordSet.add(nextWord);
            }
            fullWordList.addAll(wordSet);
            wordListImported=true;
        } catch(IOException e){
            throw new IllegalStateException("File:" + fileName+" is not found",e);
        }
    }

    public List<String> getPartialWordSet(int numWords){
        if(numWords > fullWordList.size()){
            String errorMessage = "Unable to return partial words list:"+
                "\nfullWordList.size()="+fullWordList.size()+
                "\nnumWords="+numWords;
            throw new IllegalStateException(errorMessage);
        }
        Collections.shuffle(fullWordList);
        ArrayList<String> partialWordList = new ArrayList<>();
        for(int i=0; i<numWords; i++){
            partialWordList.add(fullWordList.get(i));
        }
        return partialWordList;
    }
}
