package edu.uwb.css143b2020fall.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearcherImpl implements Searcher
{
    public List<Integer> search(String keyPhrase, Map<String, List<List<Integer>>> index)
    {
        List<Set<Integer>> documents = new ArrayList<>();

        // Loop through words
        String[] words = keyPhrase.trim().split("\\s+");
        for (String word : words) {

            // The index did not contain the word, return an empty list (no docs have the search)
            if (!index.containsKey(word))
            {
                return new ArrayList<>();
            }

            for (int d = 0; d < index.get(word).size(); d++)
            { // for each document

                if (word == (words[0]))
                { // first word encountered!
                    documents.add(new HashSet<>());
                    documents.get(d).addAll(index.get(word).get(d)); // store all the indexes the word occurs in doc
                }
                else
                {
                    Set<Integer> keep = new HashSet<>();
                    for (int num : index.get(word).get(d))
                    {
                        if (documents.get(d).contains(num - 1))
                        {
                            keep.add(num); // store all indexes of word that occurs in doc AND follows legal index
                        }
                    }
                    documents.set(d, keep); // replace
                }

            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < documents.size(); i++) {
            if (documents.get(i).size() > 0) result.add(i); // return all docs that have valid indexes
        }

        return result;
    }
}