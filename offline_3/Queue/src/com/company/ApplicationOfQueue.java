package com.company;

import java.util.Scanner;

public class ApplicationOfQueue {
    public static void stringManipulation(String stream)
    {
        Queue<Character> firstNonRepeated = new Queue<>(); //queue for storing non repeated characters serially
        int[] frequency = new int[26]; //array for tracking occurrence of stream characters
        for(int i=0; i<26; i++)
            frequency[i] = 0;
        StringBuffer newString = new StringBuffer();
        for(int i=0; i<stream.length(); i++)
        {
            char c = stream.charAt(i);
            int ind = c-'a';
            frequency[ind]++; //storing the frequency of occurrence
            if(frequency[ind]<2)
                firstNonRepeated.enqueue(c);
            if(firstNonRepeated.isEmpty())
            {
                newString.append('#');
            }else
            {
                if(firstNonRepeated.front()!=c || frequency[firstNonRepeated.front()-'a']<2) // corner case for first stream character
                {
                    newString.append(firstNonRepeated.front());
                }else
                {
                    while(!firstNonRepeated.isEmpty())
                    {
                        if(firstNonRepeated.front()==c || (frequency[firstNonRepeated.front()-'a']>=2)) //either front matches with current character or the char is repeated
                            firstNonRepeated.dequeue();
                        else
                            break;
                    }
                    if(firstNonRepeated.isEmpty())
                    {
                        newString.append('#');
                    }else
                    {
                        newString.append(firstNonRepeated.front());
                    }
                }
            }
        }
        System.out.println(newString);
    }
}
