package com.google.engedu.anagrams;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();

    Set<String> wordSet=new HashSet<String>();
    HashMap<String,ArrayList> lettersToWord=new HashMap<String,ArrayList>();
    public ArrayList<String> wordList=new ArrayList<String>();

    public AnagramDictionary(InputStream wordListStream) throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            wordList.add(word);
            wordSet.add(word);
        }
        for(int i=0;i<wordList.size();i++)
        {
            String word=wordList.get(i).toString();
            String sortedWord=sortWord(word);
            ArrayList<String> value = new ArrayList<String>();
            if(lettersToWord.containsKey(sortedWord)) {
                value=lettersToWord.get(sortedWord);
                value.add(word);
            }
            else
            {
                value.add(word);
                lettersToWord.put(sortedWord,value);
            }
        }
    }
    public String sortWord(String s)
    {
        String x="";
        int a[]=new int[s.length()];
        for(int i=0;i<s.length();i++)
            a[i]=(int)s.charAt(i);
        Arrays.sort(a);
        for(int i=0;i<s.length();i++)
            x=x+(char)(a[i]);
        return x;
    }
    public boolean isGoodWord(String word, String base)
    {
        if(wordSet.contains(word)&&base.indexOf(word)==-1)
            return true;
        else
            return false;
    }
    public ArrayList<String> getAnagramsWithOneMoreLetter(String word)
    {
        ArrayList<String> value = new ArrayList<String>();
        for(char ch='a';ch<='z';ch++)
        {
            String sortedWord=sortWord(word+ch);
            if(lettersToWord.containsKey(sortedWord))
            {
                value=lettersToWord.get(sortedWord);
                value.add(word);
            }
            else
            {
                value.add(word);
                lettersToWord.put(sortedWord,value);
            }
        }
        return value;
    }
    public String pickGoodStarterWord()
    {
        Random r=new Random();
        int n=r.nextInt(wordList.size());
        return wordList.get(n);
    }

}
