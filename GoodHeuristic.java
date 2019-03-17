import java.io.*;
import java.util.*;

//This program will show a heuristic function
//in the domain of searching for a shortest path on a map.

public class GoodHeuristic extends Heuristic {
	public double heuristicValue(Node currNode) {
		double heuristicValue = 0.0;

		// COMPUTE A REASONABLE HEURISTIC VALUE HERE
		heuristicValue = range(currNode.loc, getDestination()) / MajorityOfSpeedSpeed;
		return (heuristicValue);
	}
	
	double MajorityOfSpeedSpeed = 0.0;
	
	public void StartH(StreetMap graph, Location endPoint){
		MRSpeed(graph);
		setDestination(endPoint);
	}
	
	public double range(Location startingPoint, Location endPoint){
		double longitude = startingPoint.longitude - endPoint.longitude;
		double latitude = startingPoint.latitude - endPoint.latitude;
		return Math.sqrt(longitude *longitude +latitude * latitude);
		//the location,longitude, and latitude values are
		//Cartesian coordinates measured in miles
	}
	
	public double MRSpeed(StreetMap graph){
		double speed;
		for(Location location : graph.locations){
			for(Road road: location.roads){
				speed = distance(road.fromLocation, road.toLocation)/road.cost;
				if(speed > MajorityOfSpeedSpeed){
					MajorityOfSpeedSpeed = speed;
		}}}
		return MajorityOfSpeedSpeed; 
	}
}

//wokred with Ta through this algorithm
