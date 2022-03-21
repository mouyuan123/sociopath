/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Stack;

/**
 *
 * @author MC
 */
public class Graph<T>{
    // Instant Variables
    // To retrieve the elements in order of insertion
   private LinkedHashMap<T,LinkedHashSet<T>> vertices = new LinkedHashMap<>(); // The key = vertices & the set = edge formed between 2 vertices
   private int numofpaths = 0;
   private ArrayList<String> paths = new ArrayList<>();
   private ArrayList<String> vertexPrint= new ArrayList<>();
   
   //Constructor
   public Graph(){
       
   }
   
   public boolean addVertice(T vertexName){
       if(hasVertex(vertexName)){
           return false;
       }
       else{
           vertices.put(vertexName, new LinkedHashSet<>()); // The new vertex has no any edge
           return true;
       }
   }
   
   public boolean addEdge(T source, T destination){
        if (vertices.isEmpty()) {
            return false;
        } 
        else if (!hasVertex(source) || !hasVertex(destination)) {
            return false;
        } 
        else {
            if (!hasUniEdge(source, destination)) {
                if (!hasEdge(source, destination)) {
                    vertices.get(source).add(destination);
                }
                if (!hasEdge(destination, source)) {
                    vertices.get(destination).add(source);
                }
                return true;
            }
            return false; // undirected edge exists between this two vertices
        }
    }
   
   public boolean hasVertex(T vertexName){
       return vertices.containsKey(vertexName);
   }
   
   // true only if there is undirected edge exists between both source & destination
   public boolean hasUniEdge(T source, T destination){
       return vertices.get(source).contains(destination) && vertices.get(destination).contains(source);
   }
   
   public boolean hasEdge(T source, T destination){
       return vertices.get(source).contains(destination);
   }
   
   public T getVertex(int index){
       if(vertices.isEmpty()){
           return null;
       }
       else if(index < 0 || index >= vertices.size()){
           throw  new IndexOutOfBoundsException();
       }
       else{
           ArrayList<T> temp = new ArrayList<>(); // To store the vertices
           temp.addAll(vertices.keySet());
           return temp.get(index);
       }
   }
   
   public boolean isConnectedGraph(T vertex){
       if(!hasVertex(vertex)){
           return false;
       }
       Stack<T> stack = new Stack<>();
       stack.push(vertex);
       ArrayList<T> isVisited = new ArrayList<>();
       isVisited.add(stack.peek()); 
       ArrayList<T> adjacentNode = new ArrayList<>();
       while(!stack.isEmpty()){
          adjacentNode.addAll(vertices.get(stack.pop()));
           for(int i = 0; i < adjacentNode.size(); i++){
               if(!isVisited.contains(adjacentNode.get(i))){
                   stack.push(adjacentNode.get(i));
                   isVisited.add(adjacentNode.get(i));
               }
           }
           adjacentNode.clear();
           }
           return isVisited.size() == vertices.size();
       }

    public void getAllPaths(T from, T reach) {
        if (!hasVertex(from) || !hasVertex(reach)) {
            return;
        } 
        else {
            ArrayList<T> isVisited = new ArrayList<>();
            ArrayList<T> path = new ArrayList<>();
            path.add(from);
            getAllPaths(from, isVisited, path, reach);
        }
    }

    public void getAllPaths(T adjacentNode, ArrayList<T> isVisited, ArrayList<T> path, T reach) {
        if (adjacentNode.equals(reach)) {
            paths.add(path.toString());
            return;
        }
        isVisited.add(adjacentNode); // After checking adjacentNode != reach, only put inside isVisited (X add "reach" into the lsit)
        for (T edgeNode : vertices.get(adjacentNode)) {
            if (!isVisited.contains(edgeNode)) {
                path.add(edgeNode);
                getAllPaths(edgeNode, isVisited, path, reach);
                path.remove(edgeNode); // Remove the vertex after finding the "reach' in backtracking
            }
        }
    }

    public ArrayList<String> getVertexPrint() {
        return vertexPrint;
    }
   
   
   public String printVertices(){
       System.out.print("Vertices : ");
       return vertices.keySet().toString();
   }
   
   public void printEdges(){
       for(T vertex : vertices.keySet()){
           vertexPrint.add("Vertex : "+vertex +", Edges: "+getEdge(vertex));
       }
   }
   
   public String getEdge(T vertexName){
       String str = "";
       ArrayList<T> temp = new ArrayList<>();
       temp.addAll(vertices.get(vertexName));
       for(int i = 0; i < temp.size(); i++){
           str+="["+vertexName+","+temp.get(i)+"]";
           if(i != temp.size()-1){
               str+=",";
       }
   }
       return str;
   }
   
   
   public int getSize(){
       return vertices.size();
   }

    public int getNumofpaths() {
        return numofpaths;
    }

    public void printAllpaths() {
        for(int i = 0; i < paths.size(); i++){
           System.out.println(++numofpaths+". "+ paths.get(i));
       }
    }
    
    @SuppressWarnings("unchecked")
    public void sortByLength(){
        Collections.sort(this.paths, Comparator.comparing(String::length));
        for (int i = 0; i < paths.size() - 1; i++) {
            String min = paths.get(i);
            int indexCurrent = i;
            for (int j = i + 1; j < paths.size(); j++) {
                String temp = paths.get(j);
                if (min.length() == temp.length()) {
                    if (min.charAt(1) == temp.charAt(1)) {
                        for (int k = 2; k <= min.length() - 1; k++) {
                            if (Character.isDigit(min.charAt(k))) {
                                int a = min.charAt(k);
                                int b = temp.charAt(k);
                                if (a > b) {
                                    min = temp;
                                    indexCurrent = j;
                                    break;
                                }
                            }
                        }
                        if (indexCurrent != i) {
                            swap(i, j, this.paths);
                        }
                    }
                }
            }
        }
        printAllpaths();
    }
    
    public void swap(int from, int to, ArrayList<String> a){
        String temp = a.get(from);
        a.set(from,a.get(to));
        a.set(to,temp);
        
    }

    public ArrayList<String> getPaths() {
        return paths;
    }

   
}
