package edu.uwb.css143b2020fall.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IndexerImpl implements Indexer {
    public Map<String, List<List<Integer>>> index(List<String> docs) {
        Map<String, List<List<Integer>>> indexes = new HashMap<>();

        for (int docIndex = 0; docIndex < docs.size(); docIndex++)  // loop through every document
        {
            String[] words = docs.get(docIndex).trim().split("\\s+");   // add trim

            for (int wordIndex = 0; wordIndex < words.length; wordIndex++)  //loop through every word in that document
            {
                if( words[wordIndex].length() == 0) continue;

                List<List<Integer>> docIndexes = indexes.get(words[wordIndex]); //get the frequency for word in docs
                if(docIndexes == null ) // first time encountering this word, no docs listed
                {
                    docIndexes =new ArrayList<>();
                    for(int i = 0; i < docs.size(); i++)
                    {
                        docIndexes.add(new ArrayList<>());
                    }
                    indexes.put(words[wordIndex],docIndexes);   //insert doc list
                }

                List<Integer> encounter = docIndexes.get(docIndex); //get the current list of encounters in this doc
                encounter.add(wordIndex);
            }
        }

     return indexes;
    }
}