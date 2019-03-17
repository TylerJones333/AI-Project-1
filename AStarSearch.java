
import java.util.HashSet;

public class AStarSearch{
	//diagram of object streetMap
	StreetMap graph;
	int limit = 1000;
	public String destinationloc;
	public String initailloc;
	public int expansionCount = 0; 
	
	//constructor no arguments
	public AStarSearch(){
		this.graph = null;
		this.limit = 1000;
		//depth limit to avoid infinite loop
		this.initailloc = "";
		this.destinationloc = "";	
		//this sets each argument to null 
		//this is so that the search algorithm would running
		//initially in an empty node
		this.expansionCount = 0;
	}
	//constructor four arguments
	public AStarSearch(StreetMap graph, 
			String start, 
		String destinationloc, int limit){
		//sets arguments equal to declared variables
		this();
		this.initailloc = start;
		this.graph = graph;
		this.limit = limit;
		this.destinationloc = destinationloc; 	 
	}//Last program explained how "this." were used for c
	//creating arguments in constructors
	
	//public method that implement AstarSearh
	//takes one argument of boolean type
	public Node search (boolean check){
		//Contains the variables that will be explored
		HashSet<String> checkedNode = new HashSet<>();
		Node thisNode = new Node(graph.findLocation(initailloc), null);
		expansionCount = 0;
		//checks to see if the correct destination is present
		if (thisNode.isDestination(destinationloc)){
			//returns destination
			return thisNode;
		}
		else{
			SortedFrontier eachEdge = new SortedFrontier(SortBy.f);//TA helped clarify .f
			//Sort.by() sorts the arrays
			eachEdge.addSorted(thisNode);
			Location endPoint = graph.findLocation(destinationloc);
			GoodHeuristic as = new GoodHeuristic();
			as.StartH(graph, endPoint);
			//checks if repeated state is true
			if(check){
				//checking if not empty and not greater than max value
				while(!eachEdge.isEmpty() && thisNode.depth < limit){
					thisNode = eachEdge.removeTop();
					//checks to see if the correct destination is present
					if(thisNode.isDestination(destinationloc)){
						//return destination
						return thisNode;
					}
					else{
						checkedNode.add(thisNode.loc.name);
						Node checkNode;
						thisNode.expand(as);
						expansionCount++;
			//checking for loop running to location
			//Verbally collaborated with Veer for else statement
			for (Node childNode : thisNode.children){
			if(!checkedNode.contains(childNode.loc.name)){
				if(eachEdge.contains(childNode) == true){
					checkNode = eachEdge.find(childNode);
						//checks if current nodes path is less than checked nodes
						if (childNode.partialPathCost < checkNode.partialPathCost){
						eachEdge.remove(checkNode);
						//checks if the child is node removed
						eachEdge.addSorted(childNode);
						//replace node to the sorted list
									}
								}
			else{
				//adds child node to sorted elements
				eachEdge.addSorted(childNode);
			}}}}}
				return null;
			}
			else{
				//removes from top
				thisNode = eachEdge.removeTop();
				
				//checks to see if the correct destination is present
				if (thisNode.isDestination(destinationloc)){
					//returns destination
					return thisNode;
				}
				else{
					thisNode.expand(as);
					expansionCount++;
					eachEdge.addSorted(thisNode.children);
				}
				return null; 
			}}}
	}

//worked with TA through this algorithm 
