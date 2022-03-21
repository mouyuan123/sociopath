/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 *
 * @author MC
 */
public class Event_6 {

    /**
     * @param args the command line arguments
     */
    private int num;
    private ArrayList<String> list;
    private ArrayList<String> print = new ArrayList<String>();
    
    
    public static void main(String[] args) {
        ArrayList<String> a = new ArrayList<>();
        Event_6 event6 = new Event_6(2,a);
        event6.go();
        
    }

    public Event_6(int num, ArrayList<String> list) {
        this.num = num;
        this.list = list;
        go();
    }
    
    public void go(){
        // TODO code application logic here
        Graph<Integer> graph = new Graph<>();
        try {
            for (int i = 1; i <= num; i++) {
                graph.addVertice(i);
            }
            for (int i = 0; i < list.size(); i++) {
                String edge = list.get(i);
                if (edge.isBlank()) {
                    break;
                } 
                else {
                    String[] temp = edge.split(" ");
                    try {
                        int a = Integer.parseInt(temp[0]);
                        int b = Integer.parseInt(temp[1]);
                        if ((a > num || a <= 0) || (b > num || b <= 0)) {
                            continue;
                        }
                        graph.addEdge(a, b);
                    } catch (NumberFormatException e) {
                    }
                }
            }
            print.add("The graph is connected is " + graph.isConnectedGraph(graph.getVertex(0)));
            print.add("The size of the graph is " + graph.getSize());
            graph.printEdges();
            if (graph.isConnectedGraph(graph.getVertex(0))) {
               print.add("\nYou can form the following friendship(s):");
                for (int i = 1; i < graph.getSize(); i++) {
                    for (int j = i + 1; j <= graph.getSize(); j++) {
                        graph.getAllPaths(i, j);
                    }
                }
                graph.sortByLength();
                for (int i = 0; i < graph.getVertexPrint().size(); i++) {
                    print.add(graph.getVertexPrint().get(i));
                }
                for (int i = 1; i < graph.getPaths().size()+1; i++) {
                    print.add( i + ". " + graph.getPaths().get(i-1));
                }
                print.add("\nOutput: " + graph.getNumofpaths());
            }
        } catch (InputMismatchException e) {
            print.add("Invalid Input");
        }
    }

    public int getNum() {
        return num;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public ArrayList<String> getPrint() {
        return print;
    }
}
