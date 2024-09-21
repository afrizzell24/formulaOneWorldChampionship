package season2024;

import java.util.Random;

/**
 * @author <<Andrew Frizzell>>
 * 
 * 			This class holds information about each racing team, and it tracks which
 * 			drivers are racing for the team.
 */
public class Team {
	
	Driver driver1;
	Driver driver2;
	
	private int constructorPoints;
	private double vehiclePerformance;
	private String teamName;
	private int racePoints;
	
	public Team(String teamName, String driver1, String driver2) {
		Random rand = new Random();
		
		constructorPoints = 0;
		this.teamName = teamName;
		this.driver1 = new Driver(driver1, this.teamName);
		this.driver2 = new Driver(driver2, this.teamName);
		vehiclePerformance = ((rand.nextDouble() % 50) / 100.0);
	}
	
	public String getTeamName() {
		return teamName;
	}
	
	public double getVehiclePerformance() {
		return vehiclePerformance;
	}
	
	public int getConstructorPoints() {
		return constructorPoints;
	}
	
	public void updateConstructorPoints(int points) {
		racePoints = points;
		constructorPoints += points;
	}
	
	/**
	 * This method tracks the points a team gets in an individual race, allowing
	 * more data to be displayed when viewing race results.
	 * @return
	 */
	public int getRacePoints() {
		return racePoints;
	}
}