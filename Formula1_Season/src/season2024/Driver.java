package season2024;

import java.util.Random;

/**
 * @author <<Andrew Frizzell>>
 * 
 * 		    The Driver class holds information about the individual drivers of each team. 
 * 			In the constructor, the driver is randomly assigned a "skill level" that affects 
 * 			their race performance
 */
public class Driver {
	
	private int driverPoints;
	private double driverSkill;
	private String driverName;
	private String driverTeam;
	private int pointsInRace;
	
	public Driver (String driverName, String driverTeam) {
		Random rand = new Random();
		
		this.driverTeam = driverTeam;
		pointsInRace = 0;
		driverPoints = 0;
		this.driverName = driverName;
		driverSkill = ((rand.nextDouble() % 35) / 130.0);
	}
	
	public void updatePoints(int numPoints) {
		driverPoints += numPoints;
	}
	
	public int getDriverPoints() {
		return driverPoints;
	}
	
	public double getDriverSkill() {
		return driverSkill;
	}
	
	public String getDriverTeam() {
		return driverTeam;
	}
	
	public String getDriverName() {
		return driverName;
	}
	
	/**
	 * This information helps track the data of individual races, allowing for more information
	 * to be displayed when showing race results
	 * @return
	 */
	public int getPointsInRace() {
		return pointsInRace;
	}
	
	public void setPointsInRace(int points) {
		pointsInRace = points;
	}
}