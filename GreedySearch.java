
import java.util.*;
//No need for Hash sets for this class

public class GreedySearch {

	StreetMap graph;
	public String initailloc;
	public String destinationloc;
	int limit = 1000;
	public int expansionCount = 0; 
	//public constructor no arguments	
	
	public GreedySearch(){
		this.graph = null;
		this.limit = 1000;
		this.initailloc = "";
		this.destinationloc = "";
		//this sets each argument to null or to max value
		//this is so that the search algorithm would running
		//initially in an empty node
		this.expansionCount = 0;
	}
	//public constructor with four arguments
	public GreedySearch(StreetMap graph, 
			String initialloc, 
			String destinationloc, int limit){
		//sets arguments equal to declared variables
		this();
		this.initailloc = initialloc; 
		this.graph = graph;
		this.limit = limit;
		this.destinationloc = destinationloc; 	 
	}//Last program explained how "this." were used for
	//creating arguments in constructors
	
	//public method that implement GreedySearch
	//Takes one argument of boolean type
	public Node search (boolean check){
		Node thisNode = new Node(graph.findLocation(initailloc), null);
		expansionCount = 0;
		//checks to see if the correct destination is present
		if (thisNode.isDestination(destinationloc)){
			//returns destination
			return thisNode;
		}
		else
		{
			SortedFrontier eachEdge = new SortedFrontier(SortBy.h);//TA clarified the .h
			//Sort.by() sorts the arrays
			eachEdge.addSorted(thisNode);
			Location endPoint = graph.findLocation(destinationloc);
			GoodHeuristic gs = new GoodHeuristic();
			gs.StartH(graph, endPoint);
			
			//checks if repeated state is true
			if(check){
				HashSet<String> checkedNode = new HashSet<>();
			//checking if not empty and not greater than max value
			while(!eachEdge.isEmpty() && thisNode.depth < limit){
				thisNode = eachEdge.removeTop();
				//checks to see if the correct destination is present
			if(thisNode.isDestination(destinationloc)){
				//return destination
				//https://www.geeksforgeeks.org/activity-selection-problem-greedy-algo-1/
					return thisNode;
					}
					else{	
						checkedNode.add(thisNode.loc.name);
						thisNode.expand(gs);
						expansionCount++;
						//https://docs.telerik.com/devtools/aspnet-ajax/controls/treeview/application-scenarios/checkboxes/check-uncheck-all-child-nodes	
						//checking for loop running to location
						for (Node child : thisNode.children){
							if(!checkedNode.contains(child.loc.name)&&!eachEdge.contains(child)){
								eachEdge.addSorted(child);
							}}}}
				return null;
			}
			else{
				//checking if not empty and not greater than max value
				while(!eachEdge.isEmpty() && thisNode.depth < limit){
					
					thisNode = eachEdge.removeTop();
					//checks to see if the correct destination is present
					if (thisNode.isDestination(destinationloc)){
						//return destination
						return thisNode;
					}
					else{
						thisNode.expand(gs);
						expansionCount++;
						eachEdge.addSorted(thisNode.children);
					}}
				return null; 
			}}}
	}

//worked with TA through this algorithm

