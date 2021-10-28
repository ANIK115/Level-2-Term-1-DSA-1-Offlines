package graph;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Graph {

    private ArrayList<ArrayList<Integer>> roadLists;
    private int cities;
    private int roads;
    private int locations;
    private int friends;
    private int[] piecesInLocations;
    private int[] friendsLocations;
    private int totalPieces;
    private int totalCollectedPieces;
    private int[] friendsPieces;

    public Graph(int cities, int roads, int locations, int friends) {
        this.cities = cities;
        this.roads = roads;
        this.locations = locations;
        this.friends = friends;
        this.roadLists = new ArrayList<ArrayList<Integer>>(cities);
        this.totalPieces = 0;
        this.totalCollectedPieces = 0;
        this.piecesInLocations = new int[cities];
        this.friendsLocations = new int[friends];
        this.friendsPieces = new int[friends];

        for(int i=0; i<cities; i++)
        {
            roadLists.add(new ArrayList<Integer>());
        }
    }

    public Graph clone()
    {
        Graph cloneGraph = new Graph(this.cities, this.roads, this.locations, this.friends);
        cloneGraph.roadLists = (ArrayList<ArrayList<Integer>>) this.roadLists.clone();
        cloneGraph.piecesInLocations = this.piecesInLocations.clone();
        cloneGraph.friendsLocations = this.friendsLocations.clone();
        cloneGraph.totalPieces = this.totalPieces;
        return cloneGraph;
    }

    public void addRoads(int city1, int city2)
    {
        roadLists.get(city1).add(city2);
        roadLists.get(city2).add(city1);
    }

    public void addPieces(int city, int pieces)
    {
        this.piecesInLocations[city] = pieces;
        totalPieces += pieces;
    }
    public void addFriendsLocations(int city, int friend)
    {
        friendsLocations[friend] = city;
    }

    private boolean findPieces(String traversal)
    {
        if(traversal.equals("bfs"))
        {
            for(int i=0; i<friends; i++)
            {
                friendsPieces[i] = bfs(friendsLocations[i]);
                totalCollectedPieces += friendsPieces[i];
            }
        }else if(traversal.equals("dfs"))
        {
            for(int i=0; i<friends; i++)
            {
                friendsPieces[i] = dfs(friendsLocations[i]);
                totalCollectedPieces += friendsPieces[i];
            }
        }

        if(totalCollectedPieces == totalPieces)
            return true;
        else
            return false;
    }

    public void result(String traversal)
    {
        try{
            String fileName = traversal + "Output.txt";
            FileWriter fileWriter = new FileWriter(fileName);
            if(findPieces(traversal) == true)
            {
                fileWriter.write("Mission Accomplished\n");
            }else
            {
                fileWriter.write("Mission Impossible\n");
            }
            fileWriter.write(totalCollectedPieces + " out of " + totalPieces + " pieces are collected\n");
            for(int i=0; i<friends; i++)
            {
                fileWriter.write(i + " collected " + friendsPieces[i] + " pieces\n");
            }
            fileWriter.close();
        }catch (IOException e)
        {
            System.out.println(e);
        }
    }

    private int bfs(int start)
    {
        int collected = 0;
        boolean[] visited = new boolean[cities];
        for(int i=0; i<cities; i++)
        {
            visited[i] = false;
        }
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(start);
        visited[start] = true;
        collected += piecesInLocations[start];
        piecesInLocations[start] = 0;
        while(!queue.isEmpty())
        {
            int current = queue.front();
            queue.dequeue();
            for(int i = 0; i< roadLists.get(current).size(); i++)
            {
                int x = roadLists.get(current).get(i);
                if(visited[x] == false)
                {
                    visited[x] = true;
                    collected += piecesInLocations[x];
                    piecesInLocations[x] = 0;
                    queue.enqueue(x);
                }
            }
        }
        return collected;
    }
    private int dfs(int start)
    {
        int collected = 0;
        boolean[] visited = new boolean[cities];
        for(int i=0; i<cities; i++)
        {
            visited[i] = false;
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(start);

        while(!stack.isEmpty())
        {
            int current = stack.top();
            stack.pop();
            if(visited[current] == false)
            {
                visited[current] = true;
                collected += piecesInLocations[current];
                piecesInLocations[current] = 0;
            }
            for(int i = 0; i< roadLists.get(current).size(); i++)
            {
                int x = roadLists.get(current).get(i);
                if(visited[x] == false)
                {
                    stack.push(x);
                }
            }
        }
        return collected;
    }
}
