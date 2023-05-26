package dsalab11;

import java.util.Arrays;

public class PathFinder {
    
    int[][] adjacencyMatrix;
    String path;
    String queue;
    String visited;
    int[] lastVertex;
    
    public PathFinder(int[][] matrix){
        adjacencyMatrix = matrix;
        lastVertex = new int[matrix.length];
        for(int i = 0; i < lastVertex.length; i++){
            lastVertex[i] = -1;
        }
        queue = "";
        visited = "";
        path = "";
    }
    
    private void enqueue(int node){
        queue = queue + node;
    }
    
    private int dequeue(){
        int node = Integer.valueOf(queue.charAt(0)+"");
        queue = queue.substring(1);
        return node;
    }
    
    private boolean isQueueEmpty(){
        return queue.isEmpty();
    }
    
    public String findPath(int start, int end){
        enqueue(start);
        visited += start;
        
        while(!isQueueEmpty()){
            int current = dequeue();
            
            for (int i = 0; i < adjacencyMatrix[current].length; i++){
                if(adjacencyMatrix[current][i] != 0 && !visited.contains(i+"")){
                    enqueue(i);
                    visited += i;
                    lastVertex[i] = current;
                    if (i == end) {
                        break;
                    }
                }
            }
        }
        
        // Backtrack from the end to the start and construct the path.
        for (int currentVertex = end; currentVertex != start; currentVertex = lastVertex[currentVertex]) {
            path = "->" + currentVertex + path;
        }

        path = start + path;
        return path;
    }
    
    public static void main(String[] args) {
        int[][] adjacencyMatrix = 
            {{0,1,0,1,0,0,0},
            {0,0,0,1,1,0,0},
            {1,0,0,0,0,1,0},
            {0,0,1,0,1,1,1},
            {0,0,0,0,0,0,1},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,1,0}};
        
        PathFinder pf = new PathFinder(adjacencyMatrix);
        String shortestPath = pf.findPath(2,6);
        System.out.println(Arrays.toString(pf.lastVertex));
        System.out.println(shortestPath);
    }
}
