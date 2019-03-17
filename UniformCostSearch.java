import java.util.HashSet;
import java.util.Set;


public class UniformCostSearch{
	//diagram of object streetMap
	StreetMap graph;
	int limit = 1000;
	public String destinationloc;
	public String initailloc;
	public int expansionCount = 0;
	
	//constructor no arguments
	public UniformCostSearch(){
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
	public UniformCostSearch(StreetMap graph, 
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
	public Node search (boolean child){
		Node thisEmptyNode = new Node (graph.findLocation(initailloc),null);
		expansionCount = 0;
		//checks to see if the correct destination is present
		if (thisEmptyNode.isDestination(destinationloc)){
			//returns destination
			return thisEmptyNode;
		}
		else{
			SortedFrontier eachBoarder = new SortedFrontier(SortBy.g);//TA helped clarify .g
			//Sort.by() sorts the arrays
			eachBoarder.addSorted(thisEmptyNode);
			Location endingLocation = graph.findLocation(destinationloc);
			GoodHeuristic ucs = new GoodHeuristic();
			ucs.StartH(graph, endingLocation);
			//checks if repeated state is true
			if(child){
				Set<String> explored = new HashSet<>();
				while (!eachBoarder.isEmpty() && thisEmptyNode.depth < limit){
					//checks to see if the correct destination is present
					thisEmptyNode = eachBoarder.removeTop();
					
					if (thisEmptyNode.isDestination(destinationloc)){
						return thisEmptyNode;
					}
					else{
						Node checkNode;
						explored.add(thisEmptyNode.loc.name);
						thisEmptyNode.expand(ucs);
						expansionCount++;
						
						for (Node childNode : thisEmptyNode.children){
							if(!explored.contains(childNode.loc.name)){
							if (eachBoarder.contains(childNode) == true){
								checkNode = eachBoarder.find(childNode);
								if (childNode.partialPathCost < checkNode.partialPathCost){
									eachBoarder.remove(checkNode);
									eachBoarder.addSorted(childNode);
								}
							}
							else{
								eachBoarder.addSorted(childNode);
							}
						}
					}}}
				return null;
			}
			else
			{
				while (!eachBoarder.isEmpty() && thisEmptyNode.depth < limit){
					thisEmptyNode = eachBoarder.removeTop();
					
					if (thisEmptyNode.isDestination(destinationloc)){
						return thisEmptyNode;
					}
					else
					{
						thisEmptyNode.expand(ucs);
						expansionCount++;
						eachBoarder.addSorted(thisEmptyNode.children);
					}
				}
				return null;
			}}}}
