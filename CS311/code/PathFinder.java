
// Written by Jacob Boicken 

import java.io.IOException;    
import java.io.File; 

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

// import directives
public class PathFinder {

    Graph g;

    public PathFinder() {
    
    }
    
    public void print() {
        if(g != null) {
            
            System.out.print("Node array: "); 
            for(Node n : g.nodes) {
                System.out.print("[" + n.x +", " + n.y + "], ");
            }
            System.out.println();
            System.out.println("Edges hashmap: \n" + g.edges);
        }
    }

    public void readInput(String filename) throws IOException {    
        File f = new File(filename);    
        Scanner s = new Scanner(f);    
  
        int numNodes = s.nextInt();
        int numEdges = s.nextInt();

        g = new Graph(numNodes);    // Allows same PathFinder to be used for different graph 
        
        for(int i = 0; i < numNodes; i++) {
            int index = s.nextInt();
            int x = s.nextInt();
            int y = s.nextInt();
                
            g.addNode(index, x, y);
        }

        for(int i = 0; i < numEdges; i++) {
            int u = s.nextInt();
            int v = s.nextInt();
            
            g.addEdge(u, v);
        }

        s.close();
    }    

    public double[] shortestPathDistances(int s) {
        boolean[] assimilated = new boolean[g.nodes.length];
        double[] distances = new double[g.nodes.length];
        MinHeap h = new MinHeap();
        for(int i = 0; i < g.nodes.length; i++) {
            assimilated[i] = false;
            distances[i] = (s == i) ? 0 : -1;
            h.add(i, (distances[i] < 0) ? Double.MAX_VALUE : distances[i]);
        }

        while(!h.isEmpty()) {
            int v = h.extract();
            if(distances[v] < 0) {
                break;
            }
            assimilated[v] = true;
            for(Integer u : g.getNeighbors(v)) {
                if(!assimilated[u]) {
                    if(distances[u] < 0 || distances[u] > distances[v] + g.distance(v,u)) {
                        distances[u] = distances[v] + g.distance(v,u);
                        h.update(u, distances[u]);
                    }
                }
            }
        }
       return distances; 
    }
    
    // Dijkstra's but if equals minimum then we add # of paths instead of overwrite when less than
    public int noOfShortestPaths(int s, int d) {
        boolean[] assimilated = new boolean[g.nodes.length];
        int[] numPaths = new int[g.nodes.length];
        double[] distances = new double[g.nodes.length];
        
        MinHeap h = new MinHeap();
        for(int i = 0; i < g.nodes.length; i++) {
            assimilated[i] = false;
            numPaths[i] = (s == i) ? 1 : 0;
            distances[i] = (s == i) ? 0 : -1;
            h.add(i, (distances[i] < 0) ? Double.MAX_VALUE : distances[i]);
        }

        while(!h.isEmpty()) {
            int v = h.extract();
            if(distances[v] < 0) {
                break;
            }
            assimilated[v] = true;
            for(Integer u : g.getNeighbors(v)) {
                double dist = g.distance(v, u);
                if(!assimilated[u]) {
                    if(distances[u] < 0 || distances[u] > distances[v] + dist) {
                        distances[u] = distances[v] + dist;
                        numPaths[u] = numPaths[v];
                        h.update(u, distances[u]);
                    }
                    else if(distances[u] == distances[v] + dist) {
                        numPaths[u] = numPaths[u] + numPaths[v];
                    }
                }
            }
        }
       return numPaths[d];         
    }


    public ArrayList<Integer> fromSrcToDest(int s, int d, int a, int b) {
        boolean[] assimilated = new boolean[g.nodes.length];
        int[] parents = new int[g.nodes.length];
        double[] distances = new double[g.nodes.length];
        MinHeap h = new MinHeap();
        for(int i = 0; i < g.nodes.length; i++) {
            assimilated[i] = false;
            parents[i] = -1;
            distances[i] = (s == i) ? 0 : Double.MAX_VALUE;
            h.add(i, distances[i]);
        }

        while(!h.isEmpty()) {
            
            int v = h.extract();
            if(distances[v] == Double.MAX_VALUE) {
                break;
            }
            assimilated[v] = true;

            for(Integer u : g.getNeighbors(v)) {
                double dVU = g.distance(v, u);
                double dVD = g.distance(v, d);
                double dUD = g.distance(u, d);

                double relaxation = a * (distances[v] + dVU) + b * (dUD - dVD);

                if(!assimilated[u]) {
                    if(distances[u] > relaxation) {
                        distances[u] = relaxation;
                        parents[u] = v;                   
                        h.update(u, distances[u]);
                    }
                }
            }
        }
       

        if(parents[d] == -1) 
            return null;

        ArrayList<Integer> path = new ArrayList<Integer>();
    
        getPath(d, parents, path);

        return path;
    }

    // Generated Path from parents array
    // Starts from dest going up path
    // Uses recursion to add IDs in correct order
    private void getPath(int v, int[] parents, ArrayList<Integer> path) {
        if(v < 0) 
            return;
        
        getPath(parents[v], parents, path);

        path.add(v);
    }


    public ArrayList<Integer> fromSrcToDestVia(int s, int d, ArrayList<Integer> onTheWay, int a, int b) {
        ArrayList<Integer> path = new ArrayList<Integer>();
        
        ArrayList<Integer> tempPath = fromSrcToDest(s, onTheWay.get(0), a, b);
        if(tempPath == null)
            return null;
        
        tempPath.remove(tempPath.size()-1);
        path.addAll(tempPath);

        for(int i = 1; i < onTheWay.size(); i++) {
            tempPath = fromSrcToDest(onTheWay.get(i-1), onTheWay.get(i), a, b);
            if(tempPath == null) // Returns null if not reachable so then Via isn't possible
                return null;
        
            tempPath.remove(tempPath.size()-1);
            path.addAll(tempPath);
        }

        tempPath = fromSrcToDest(onTheWay.get(onTheWay.size()-1), d, a, b);
        if(tempPath == null)
            return null;
        
        path.addAll(tempPath);    
        return path;
    }

    public int[] minCostReachabilityFromSrc(int s) {
        boolean[] assimilated = new boolean[g.nodes.length];
        int[] parents = new int[g.nodes.length];
        double[] distances = new double[g.nodes.length];
        MinHeap h = new MinHeap();
        for(int i = 0; i < g.nodes.length; i++) {
            assimilated[i] = false;
            parents[i] =  (s == i) ? s : -1;
            distances[i] = (s == i) ? 0 : -1;
            h.add(i, (distances[i] < 0) ? Double.MAX_VALUE : distances[i]);
        }

        while(!h.isEmpty()) {
            
            int v = h.extract();
            if(distances[v] < 0) {
                break;
            }
            assimilated[v] = true;

            for(Integer u : g.getNeighbors(v)) {
                if(!assimilated[u]) {
                    if(distances[u] < 0 || distances[u] > g.distance(v,u)) {
                        distances[u] = g.distance(v,u);
                        parents[u] = v;                   
                        h.update(u, distances[u]);
                    }
                }
            }
        }
 
        return parents; 
    }

    public double minCostOfReachabilityFromSrc(int s) {
        boolean[] assimilated = new boolean[g.nodes.length];
        int[] parents = new int[g.nodes.length];
        double[] distances = new double[g.nodes.length];
        MinHeap h = new MinHeap();
        for(int i = 0; i < g.nodes.length; i++) {
            assimilated[i] = false;
            parents[i] =  (s == i) ? s : -1;
            distances[i] = (s == i) ? 0 : -1;
            h.add(i, (distances[i] < 0) ? Double.MAX_VALUE : distances[i]);
        }

        while(!h.isEmpty()) {
            
            int v = h.extract();
            if(distances[v] < 0) {
                break;
            }
            assimilated[v] = true;

            for(Integer u : g.getNeighbors(v)) {
                if(!assimilated[u]) {
                    if(distances[u] < 0 || distances[u] > g.distance(v,u)) {
                        distances[u] = g.distance(v,u);
                        parents[u] = v;                   
                        h.update(u, distances[u]);
                    }
                }
            }
        }
        
        // Could just run minCostReach.. but for some reason I copied it.
        double cost = 0;
        for(int i = 0; i < parents.length; i++) {
            if(parents[i] >= 0 && parents[i] != i) {
                cost += g.distance(i, parents[i]);
            }
        }
        
        return cost;
    }

    public boolean isFullReachableFromSrc(int s) {
        boolean[] assimilated = new boolean[g.nodes.length];
        for(int i = 0; i < g.nodes.length; i++) {
            assimilated[i] = false;
        }
        DFSCount(s, assimilated);
        
        for(boolean a : assimilated) {
            if(!a) {
                return false;
            }
        }

        return true;
    }
    
    private void DFSCount(int v, boolean[] assimilated) {
        assimilated[v] = true;
        for(Integer u : g.getNeighbors(v)) {
            if(!assimilated[u]) {
                DFSCount(u, assimilated);
            }
        }
    }
}


// MinHeap with ability to update and extract (both O(log n))
class MinHeap {
    ArrayList<KeyValue> heap;           // Heap holding keys + values (sorted by values)
    HashMap<Integer, Integer> indexMap; // Key to index in heap to find value in O(1) time (used in update)

    public MinHeap() {
        heap = new ArrayList<KeyValue>();
        indexMap = new HashMap<Integer, Integer>();
    }

    public ArrayList<Integer> getHeap() {
        ArrayList<Integer> copyKeys = new ArrayList<Integer>();
        heap.forEach((k) -> copyKeys.add(k.key));
        return copyKeys; 
    }

    // I don't know why I made this function 
    // I think I was using it for something then I rewrote the heap with the hashmap 
    // so it just exists now
    public ArrayList<Double> getValues() {
        ArrayList<Double> copyValues = new ArrayList<Double>();
        heap.forEach((k) -> copyValues.add(k.value));
        return copyValues; 
    }

    // Swaps two indexes in arraylist and fixes their mapping
    private void swap(int i, int j) {
        int keyI = heap.get(i).key;
        int keyJ = heap.get(j).key;
        
        int tempIndex = indexMap.get(keyI);
        indexMap.put(keyI, indexMap.get(keyJ));
        indexMap.put(keyJ, tempIndex);

        Collections.swap(heap, i, j);
    }

    // Adds value to end and heapify up and maps index
    public void add(int key, double value) {
        KeyValue k = new KeyValue(key, value);
        heap.add(k);
        indexMap.put(key, heap.size()-1);
        heapifyUp(heap.size()-1);
    }

    // Moves value up if needed then continues up
    private void heapifyUp(int i) { 
        int j = (i -1) / 2;
        
        if(i == 0) return;       
        else if(heap.get(i).value >= heap.get(j).value) return;
        else {
            swap(i, j);
            heapifyUp(j);
        }
    }

    // Swaps value with smallest child value
    // Ends if leaf node ie 2i+1 is larger than array
    // If there isn't second childs, sets the second's variables to be the same as first's
    private void heapifyDown(int i) {
        int j1 = (2*i) + 1;
        int j2 = (2*i) + 2;
        double v1, v2;

        if(j1 < heap.size()) {
            v1 = heap.get(j1).value;
        } else {
            return;
        }

        if(j2 < heap.size()) {
            v2 = heap.get(j2).value;
        } else {
            j2 = j1;
            v2 = v1;
        }

        if(v1 < v2) {
            if(v1 < heap.get(i).value) {
                swap(i, j1);
                heapifyDown(j1);
            }
        } else if(v2 < v1){
            if(v2 < heap.get(i).value) {
                swap(i, j2);
                heapifyDown(j2);
            }
        } else {
            if(j1 <= j2) {
                if(v1 < heap.get(i).value) {
                    swap(i, j1);
                    heapifyDown(j1);
                }
            } else {
                if(v2 < heap.get(i).value) {
                    swap(i, j2);
                    heapifyDown(j2);
                }
            } 
        }
    }

    public int extract() {
        int ret = heap.get(0).key;
        
        swap(0, heap.size()-1);
        
        heap.remove(heap.size()-1);
        indexMap.remove(ret);

        heapifyDown(0);
        return ret;
    }

   public void update(int key, double value) {
        Integer i = indexMap.get(key);
        if (i == null)
            return;

        KeyValue k = heap.get(i);
        double oldValue = k.value;

        k.value = value;

        if(oldValue < value) {
            heapifyDown(i);
        } else {
            heapifyUp(i);       // should always be up in this code
        }
   }

   public boolean isEmpty() {
        return heap.isEmpty();
   }
}

// Type to hold Key and Value together in hashmap
class KeyValue {
    int key;
    double value;

    public KeyValue(int k, double v) {
        key = k; 
        value = v;
    }
}

// Type to hold the Nodes and edges
class Graph {
    Node[] nodes;                           // Array of nodes index is their ID value so 0-th node is node w/ ID 0
    HashMap<Integer, HashSet<Integer>> edges;  // HashMap of edges: Node ID to set of Node IDs that are connected
    
    public Graph(int numNodes) {
        nodes = new Node[numNodes];
        edges = new HashMap<Integer, HashSet<Integer>>();
    }

    public void addNode(int i, int x, int y) {
        nodes[i] = new Node(x, y);
    }

    public void addEdge(int a, int b) {
        HashSet<Integer> aEdges = edges.get(a);
        HashSet<Integer> bEdges = edges.get(b);
        
        if(aEdges == null) {
            aEdges = new HashSet<Integer>();
            aEdges.add(b);
            edges.put(a, aEdges);
        } else {
            aEdges.add(b);
        }
    
        if(bEdges == null) {
            bEdges = new HashSet<Integer>();
            bEdges.add(a);
            edges.put(b, bEdges);
        } else {
            bEdges.add(a);
        }
    }
    
    public HashSet<Integer> getNeighbors (int a) {
        HashSet<Integer> ret = edges.get(a);
        if (ret == null) 
            return new HashSet<Integer>(); // If no neighbors needs to have empty hash set (so no nullpointerexception)
        else 
            return ret;
    }

    public double distance(int a, int b) {
        Node u = nodes[a];
        Node v = nodes[b];
        return Math.sqrt((u.x - v.x)* (u.x - v.x) + (u.y - v.y) * (u.y - v.y));
    }
}

// Type to hold cordinates together
class Node {
    int x, y;
    
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

