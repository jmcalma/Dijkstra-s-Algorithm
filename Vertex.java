package edu.cpp.cs.cs241.Project3;

public class Vertex{
	String label;
	int distance;
	boolean visited;
	
	public Vertex(){
		label = "";
		distance = 0;
		visited = false;
	}
	
	public Vertex(String name, int value){
		label = name;
		distance = value;
		visited = false;
	}
	
	public String getLabel(){
		return label;
	}
	
	public int getDistance(){
		return distance;
	}
	
	public boolean getVisited(){
		return visited;
	}
	
	public void setVisited(boolean answer){
		visited = answer;
	}
}
