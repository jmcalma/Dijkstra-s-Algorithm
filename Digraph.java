package edu.cpp.cs.cs241.Project3;

public class Digraph{
	private int[][] edges; //a matrix for the distances between two vertices
    private String[] labels; //labels[i] contains the label for vertex i
    private String[] information; //stores the demographics of each city
    private int infoCounter = 0;

    //initialize a graph with n vertices, nxn edges, and null labels
    public Digraph(int n){
        edges = new int[n][n]; // All values initially false
        labels = new String[n]; // All values initially null
        information = new String[n];
    }
    
    //inputs the distances from source to target into edges[][]
    //from the file road.dat
    public void initializeEdges(int source, int target, int distance){
    	edges[source][target] = distance;
    }
    
    //inputs the demographics of each city into information[]
    public void setInfo(String info){
    	information[infoCounter] = info;
    	infoCounter++;
    }
    
    //returns the demographics of the desired city
    public String query(String label){
    	String info = "Invalid city code.";
    	for(int i = 0; i < 20; i++){
    		if(label.equals(labels[i])){
    			info = information[i];
    		}
    	}
    	return info;
    }
    
  //removes an edge
    private void removeEdge(int source, int target){
        if(edges[source][target] == 0){
        	System.out.println("There is no road between these cities.");
        }else{
        	edges[source][target] = 0;
        	System.out.println("A road no longer exists between these cities.");
        }
    }
    
    //removes an edge
    public void remove(String cityCode1, String cityCode2){
    	int cc1 = 0; 
		int cc2 = 0;
    	for(int i = 0; i < 20; i++){
    		if(cityCode1.equals(labels[i])){
    			cc1 = i;
    		}
    		if(cityCode2.equals(labels[i])){
    			cc2 = i;
    		}
    	}
    	removeEdge(cc1, cc2);
    }
    
  //adds an edge
    private void addEdge(int source, int target, int distance){
        if(source > 20 && source < 0){
        	System.out.println("City code 1 does not exist.");
        }else if(target > 20 && target < 0){
        	System.out.println("City code 2 does not exist.");
        }else if(edges[source][target] > 0){
        	System.out.println("A road exists between these cities.");
        }else if(distance <= 0){
        	System.out.println("Invalid distance.");
        }else{
        	edges[source][target] = distance;
        	System.out.println("A road has been inserted between these cities.");
        }
    }
    
    //adds an edge between two cities
    public void insert(String cityCode1, String cityCode2, int distance){
    	int cc1 = 0; 
		int cc2 = 0;
    	for(int i = 0; i < 20; i++){
    		if(cityCode1.equals(labels[i])){
    			cc1 = i;
    		}
    		if(cityCode2.equals(labels[i])){
    			cc2 = i;
    		}
    	}
    	addEdge(cc1, cc2, distance);
    }
    
    //finds the shortest path from the source to the target vertex
    public void Dijkstra(Digraph map, String source, String target){
    	int infinity = Integer.MAX_VALUE;
    	int[] dist = new int[20];
    	String[] previous = new String[20];
    	Vertex[] Q = new Vertex[20];
    	int index = 0;
    	int alt = 0;
    	int counter = 1;
    	
    	previous[0] = source;
    	dist[0] = 0;
    	for(int i = 0; i < 20; i++){
    		dist[i] = infinity;
    	}
    	
    	for(int i = 0; i < 20; i++){
    		int smallest = edges[i][0];
    		for(int j = 0; j < 20; j++){
    			if(edges[i][j] != 0 && smallest > edges[i][j]){
    				smallest = edges[i][j];
    			}
    		}
    		Q[i] = new Vertex(labels[i], smallest);
    	}
    	
    	while(index < 20){
    		Vertex u = new Vertex();
    		int indexOfSmallest = 0;
    		for(int i = 1; i < 20; i++){
    			if(Q[0] != null){
    				u = Q[0];
    			}
    			if(u == null){
    				u = Q[i+1];
    			}
    			if(u.getDistance() > Q[i].getDistance()){
    				u = Q[i];
    				indexOfSmallest = i;
    			}
    		}
    		if(dist[indexOfSmallest] == infinity){
    			break;
    		}
    		
    		Q[indexOfSmallest] = null;
    		Q[indexOfSmallest].setVisited(true);
    		if(u.getLabel().equals(target)){
    			break;
    		}
    		int[] neighbors = neighbors(indexOfSmallest);
    		for(int v: neighbors){
    			if(Q[v].getVisited() == true){
    				break;
    			}
    			alt = dist[indexOfSmallest] + neighbors[v];
    			if(alt < dist[v]){
    				dist[v] = alt;
    				 previous[counter++]= u.getLabel();
    			}
    		}
    		index++;
    	}
    	System.out.println();
		System.out.println("The path from " + source + " to " + target + " is ");
    	for(int i = 0; i < 20; i++){
    		if(previous[i] != null){
    			System.out.print(previous[i] + " ");
    		}
    	}
    	System.out.println();
    	System.out.println("The distance between " + source + " and " + target + " is ");
    	System.out.println();
    }

    //gets the label of the vertex 
    public String getLabel(int vertex){
    	return labels[vertex];
    }
    
    //changes the label of a vertex
    public void setLabel(int vertex, String newLabel){
        labels[vertex] = newLabel;
    }

    //obtains a list of neighbors of a specified vertex
    private int[] neighbors(int vertex){
        int i;
        int count = 0;
        int[] answer;

        for(i = 0; i < labels.length; i++){
            if(edges[vertex][i] > 0)
                count++;
        }
        answer = new int[count];
        count = 0;
        for(i = 0; i < labels.length; i++){
            if(edges[vertex][i] > 0)
                answer[count++] = i;
        }
        return answer;
    }
}
