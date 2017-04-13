package edu.cpp.cs.cs241.Project3;

import java.util.Scanner;
import java.io.*;

public class Project3 {
	public static void main(String[] args) throws FileNotFoundException{
		Scanner read = new Scanner(System.in);
		Scanner file1 = new Scanner(new File("/Users/Jerahmeel/Documents/eclipse projects/Project3/src/edu/cpp/cs/cs241/Project3/city.dat"));
		Scanner file1b = new Scanner(new File("/Users/Jerahmeel/Documents/eclipse projects/Project3/src/edu/cpp/cs/cs241/Project3/city.dat"));
		Scanner file2 = new Scanner(new File("/Users/Jerahmeel/Documents/eclipse projects/Project3/src/edu/cpp/cs/cs241/Project3/road.dat"));
		Digraph map = new Digraph(20);
		boolean exit = false;
		
		for(int i = 0; i < 20; i++){
			map.setInfo(file1.nextLine());
			map.initializeEdges(file2.nextInt() - 1, file2.nextInt() - 1, file2.nextInt());
		}
		for(int i = 0; i < 20; i++){
			int vertex = file1b.nextInt();
			String label = file1b.next();
			map.setLabel(vertex - 1, label);
			file1b.nextLine();
		}
		file1.close();
		file1b.close();
		file2.close();

		while(exit == false){
			System.out.println("Here are the commands you can perform:");
			System.out.println("Q (city code) Query the city information");
			System.out.println("D (city code 1) (city code 2) Find the minimum distance between two cities");
			System.out.println("I (city code 1) (city code 2) (distance) Insert a road");
			System.out.println("R (city code 1) (city code 2) Remove an existing road");
			System.out.println("H Display the commands");
			System.out.println("E Exit the program");
			
			String input = read.next();
			if(input.equals("Q")){
				System.out.println("City code: ");
				String cityCode = read.next();
				System.out.println(map.query(cityCode));
			}else if(input.equals("D")){
				System.out.println("City codes: ");
				String cityCode1 = read.next();
				String cityCode2 = read.next();
				map.Dijkstra(map, cityCode1, cityCode2);
			}else if(input.equals("I")){
				System.out.println("City codes and distance: ");
				String cityCode1 = read.next();
				String cityCode2 = read.next();
				int distance = read.nextInt();
				map.insert(cityCode1, cityCode2, distance);
			}else if(input.equals("R")){
				System.out.println("City codes: ");
				String cityCode1 = read.next();
				String cityCode2 = read.next();
				map.remove(cityCode1, cityCode2);
			}else if(input.equals("H")){
				System.out.println("Here are the commands you can perform:");
				System.out.println("Q (city code) Query the city information");
				System.out.println("D (city code 1) (city code 2) Find the minimum distance between two cities");
				System.out.println("I (city code 1) (city code 2) Insert a road");
				System.out.println("R (city code 1) (city code 2) Remove an existing road");
				System.out.println("H Display the commands");
				System.out.println("E Exit the program");
			}else if(input.equals("E")){
				exit = true;
				System.out.println("Good bye.");
				read.close();
			}
			if(!input.equals("E")){
				System.out.println();
				System.out.print("Please enter a command. ");
			}
		}
	}
}
