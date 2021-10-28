package graph;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int c = scanner.nextInt();
        int r = scanner.nextInt();
        int l = scanner.nextInt();
        int f = scanner.nextInt();
        Graph graph = new Graph(c, r, l, f);

        for(int i=0; i<r; i++)
        {
            int city1 = scanner.nextInt();
            int city2 = scanner.nextInt();
            graph.addRoads(city1, city2);
        }

        for(int i=0; i<l; i++)
        {
            int city = scanner.nextInt();
            int pieces = scanner.nextInt();
            graph.addPieces(city, pieces);
        }

        for(int i=0; i<f; i++)
        {
            int city = scanner.nextInt();
            int friend = scanner.nextInt();
            graph.addFriendsLocations(city, friend);
        }
        Graph cloneGraph = graph.clone();
        graph.result("bfs");
        cloneGraph.result("dfs");
    }
}
