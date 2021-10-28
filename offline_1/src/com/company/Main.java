package com.company;

import java.util.ArrayList;
import java.util.List;

class Array
{
    private String[] item;
    private int len;
    private int count = 0;

    public Array()
    {
        this.len = 20;
        item = new String[len];
    }

    public Array(int len) {
        if(len<=0)
        {
            System.out.println("Length should be a positive value!");
            return;
        }
        this.len = len;
        item = new String[len];
    }

    public Array(Object o)
    {
        if(!(o instanceof String[]))
        {
            System.out.println("Invalid Input! Input Should be String Array only!");
            return;
        }
        String[] A = (String[]) o;
        this.len = A.length;
        item = new String[len];
        for(int i=0; i<len; i++)
        {
            item[i] = A[i];
        }
        count = len;
    }


    public String[] getArray()
    {
        return item;
    }
    public String getAnElement(int i)
    {
        if(i<0 || i>=len)
        {
            System.out.println("Invalid index!");
            return "Invalid index";
        }
        return item[i];
    }
    public void add(String element)
    {
        if(count+1 <len)
        {
            item[count++] = element;
        }else
        {
            len = len * 2;
            String[] temp = new String[len];
            for(int i=0; i<count; i++)
            {
                temp[i] = item[i];
            }
            temp[count++] = element;
            item = temp;
        }
    }
    public void add(int i, String element)
    {
        if(i<0 || i>count)
        {
            System.out.println("Invalid index!");
            System.exit(1);
        }else if(count+1 > len)
        {
            count++;
            len = len * 2;
            String[] temp = new String[len];
            for(int j=0,k=0; j<count; j++,k++)
            {
                if(j==i)
                {
                    temp[j] = element;
                    k--;
                    continue;
                }
                temp[j] = item[k];
            }
            item = temp;
        }else if(count+1 <= len)
        {
            count++;
            for(int j=count-1; j>i; j--)
            {
                item[j]=item[j-1];
            }
            item[i] = element;
        }
    }
    public void remove(String element)
    {
        for(int i=0; i<count; i++)
        {
            if(element.equals(item[i]))
            {
                for(int j=i; j<count-1; j++)
                {
                    item[j] = item[j+1];
                }
                i--;
                count--;
            }
        }
    }
    public ArrayList findIndex(String element)
    {
        List<Integer> indexes = new ArrayList<>();
        for(int i = 0; i<len; i++)
        {
            if(item[i].equals(element))
            {
                indexes.add(i);
            }
        }
        return (ArrayList) indexes;
    }
    public String[] subArray(int start, int end)
    {
        if(start < 0 || end >= count || start > end)
        {
            System.out.println("Invalid range!");
            System.exit(1);
        }
        int size = end - start + 1;
        String[] subArray = new String[size];
        for(int i = 0; i<size; i++)
        {
            subArray[i] = item[i+start];
        }
        return subArray;
    }
    public static Array merge(String[]A1, String[]A2)
    {
        int size = A1.length+A2.length;
        String[] mergedItems = new String[size];
        for(int i=0,one=0,two=0; i<size; i++)
        {
            if(one == A1.length)
            {
                mergedItems[i] = A2[two++];
            }else if(two == A2.length)
            {
                mergedItems[i] = A1[one++];
            }else if(A1[one].compareToIgnoreCase(A2[two]) > 0)
            {
                mergedItems[i] = A2[two++];
            }else
            {
                mergedItems[i] = A1[one++];
            }
        }
        return new Array(mergedItems);
    }
    public int length()
    {
        return count;
    }
    public boolean isEmpty()
    {
        if(count == 0)
            return true;
        else return false;
    }
    public void print()
    {
        for(int i=0; i<count; i++)
        {
            System.out.print(item[i]+" ");
        }
        System.out.println();
    }
}

public class Main {

    public static void main(String[] args) {
        String[] s1 = {"Anik","Fahim","Udoy"};
        String[] s2 = {"Adib","Asif","Joy","Saad"};
        Array a1 = new Array();
        System.out.println("a1 default length = "+a1.length());
        Array a2 = new Array(s1);
        System.out.println("Printing a2 array : ");
        a2.print();
        a2.add("cse");
        System.out.println("After a2.add(cse)");
        a2.print();
        a2.add(4,"names");
        System.out.println("After a2.add(4,names)");
        a2.print();
//        a2.add(6,"invalid example");
//        a2.print();
        a2.add(2,"cse");
        System.out.println("After a2.add(2,cse)");
        List<Integer> ind = new ArrayList<>();
        ind = a2.findIndex("cse");
        System.out.println("Indexes of cse in a2: ");
        for(int x : ind)
            System.out.println(x);
        a2.remove("cse");
        System.out.println("After a2.remove(cse)");
        a2.print();
        String[] sub = a2.subArray(1,3);
        System.out.println("after a2.subArray(1,3)");
        for(String s : sub)
            System.out.println(s);
	    Array array = Array.merge(s1,s2);
        System.out.println("After merging s1 and s2: ");
	    array.print();
    }
}
