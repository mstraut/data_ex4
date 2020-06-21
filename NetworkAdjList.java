package a4;

import static org.junit.Assert.assertEquals;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.LinkedList;


public class NetworkAdjList {
	// variables
	private static int max_num_vertices = 7960;
	private static LinkedList<Integer>[] adjacencyList;

	@SuppressWarnings("unchecked")
	// uses some assignment 2 file reader code modified a little
	// reads each line and adds them to the adjacency list, undirected edges
	public static void createAdjacencyList() {

		adjacencyList = new LinkedList[max_num_vertices];
		// creates adjacencyList, each position has its own linked list
		for (int i = 0; i < max_num_vertices; i++) {
			adjacencyList[i] = new LinkedList<Integer>();
		}
		try {
			// reads file
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader("3980.edges"));
			while (br.ready()) {
				// string variables are split into array and then parsed as integers
				String temp = br.readLine();
				String[] tempArr = temp.split("\\s+");
				int i = Integer.parseInt(tempArr[0]);
				int j = Integer.parseInt(tempArr[1]);
				// if adj list position is empty vertex is added
				if (adjacencyList[i].isEmpty()) {
					adjacencyList[i].add(i);
				}
				// if adj list position is empty vertex is added
				if (adjacencyList[j].isEmpty()) {
					adjacencyList[j].add(j);
				}
				// links vertices
				adjacencyList[i].add(j);
				adjacencyList[j].add(i);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// checks if two numbers "are friends", have a shared edge
	public static boolean areFriends(int A, int B) {
		// if adjacencyList position A contains B returns true
		if (adjacencyList[A].contains(B)) {
			return true;
		}
		return false;
	}

	// breadth first search of facebook users friends with 3980
	public static void BFStraversal(int start) {
		int distance = 0;
		int vNum = max_num_vertices;
		boolean userTest[] = new boolean[vNum];
		LinkedList<Integer> usersChecked = new LinkedList<Integer>();
		// sets value for current user as true for being the user focused on
		userTest[start] = true;
		usersChecked.add(start);
		// while users is not 0 runs
		while (usersChecked.size() != 0) {
			// next user becomes the center of friend search
			start = usersChecked.poll();
			// distance increases
			distance = distance + 1;
			Iterator<Integer> i = adjacencyList[start].listIterator();
			// while iterates till user's friend list depletes
			while (i.hasNext()) {
				int nextUser = i.next();
				/*
				 * if checks if user's connection has been checked already prints message first
				 * connection found.
				 */
				if (!userTest[nextUser]) {
					System.out.println(nextUser + " is at a distance of " + distance + " from 3980.");
					userTest[nextUser] = true;
					usersChecked.add(nextUser);
				}
			}
		}
	}

	public static void main(String[] args) {

		/**
		 * These test cases assume the file 3980.edges was used
		 */

		createAdjacencyList();

		System.out.println("Testing...");
		assertEquals(areFriends(4038, 4014), true);
		System.out.println("1 of 7");

		System.out.println("Testing...");
		assertEquals(areFriends(3982, 4037), true);
		System.out.println("2 of 7");

		System.out.println("Testing...");
		assertEquals(areFriends(4030, 4017), true);
		System.out.println("3 of 7");

		System.out.println("Testing...");
		assertEquals(areFriends(4030, 1), false);
		System.out.println("4 of 7");

		System.out.println("Testing...");
		assertEquals(areFriends(1, 4030), false);
		System.out.println("5 of 7");

		System.out.println("Testing...");
		assertEquals(areFriends(4003, 3980), true);
		System.out.println("6 of 7");

		System.out.println("Testing...");
		assertEquals(areFriends(3985, 4038), false);
		System.out.println("7 of 7");
		System.out.println("Success!");

		BFStraversal(3980);

	}

}

