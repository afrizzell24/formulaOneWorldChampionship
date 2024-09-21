package season2024;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author <<Andrew Frizzell>>
 * 
 * 			This class iterates through a Formula One season, while keeping track of team
 * 			and driver data.
 */
public class Season {
	private String[][] trackInfo;
	private static ArrayList<Team> teamList;
	private static ArrayList<Driver> driverList;

	public Season() throws FileNotFoundException {
		driverList = new ArrayList<Driver>();
		teamList = new ArrayList<Team>();
		
		trackInfo = new String[8][4];
		setSchedule();
	}
	
	/**
	 * Adds a Team class and its drivers to their corresponding ArrayLists.
	 * @param teamName
	 * @param driver1
	 * @param driver2
	 */
	public void registerTeam(String teamName, String driver1, String driver2) {
		Team newTeam = new Team(teamName, driver1, driver2);

		teamList.add(newTeam);
		driverList.add(newTeam.driver1);
		driverList.add(newTeam.driver2);
	}

	/**
	 * Iterates through the season and holds updated championship data to 
	 * feed into the next race.
	 */
	public void startSeason(){
		Scanner scnr = new Scanner(System.in);
		int raceNum = 0;
		
		beginSeason();
		while(raceNum < trackInfo.length) {
			System.out.print("\nPress any key to advance to the " + trackInfo[raceNum][0] + " ");
			scnr.next();
			
			Race nextRace = new Race(raceNum, trackInfo, teamList);
			nextRace.beginRace();
			driverList = nextRace.getDriversChampResults();
			teamList = nextRace.getConstructorsChampResults();
			++raceNum;
		}
		scnr.close();
		
		System.out.println("\nThe winner of the Driver's Championship is " + driverList.get(0).getDriverName() + "!");
		System.out.println("The winner of the Constructor's Championship is " + teamList.get(0).getTeamName() + "!");
	}
	
	/**
	 * Prints out the opening message at the beginning of a season.
	 */
	private void beginSeason() {
		System.out.println("Welcome to the 2024 Formula One World Championship! Here is the " +
							"season schedule: \n");
		printSchedule();
		System.out.println("\nAnd here are your teams and drivers:\n");
		printDrivers();		
	}

	/**
	 * Prints relevant schedule data.
	 */
	private void printSchedule() {

		System.out.printf("Track:%30s", " " + "Date:\n\n");
		for (int i = 0; i < trackInfo.length; ++i) {
			System.out.printf("%1$-28s %2$10s", trackInfo[i][0], trackInfo[i][1] + "\n");
		}
	}

	/**
	 * Prints the driver info at the beginning of the season.
	 */
	private void printDrivers() {
		System.out.printf("%1$-10s %2$12s %3$22s", "Team:", "Driver:", "Points:" + "\n\n");
		for (int i = 0; i < driverList.size(); ++i) {
			System.out.printf("%1$-15s %2$-23s %3$s", driverList.get(i).getDriverTeam(),
					driverList.get(i).getDriverName(),
					String.valueOf(driverList.get(i).getDriverPoints()) + "\n");
		}
	}

	/**
	 * Sets the schedule and its relevant data for the season.
	 * @throws FileNotFoundException
	 */
	private void setSchedule() throws FileNotFoundException{
		File file = new File("trackInfo.txt");
		Scanner scnr = new Scanner(file);
		
		for (int i = 0; i < trackInfo.length; ++i) {
			for (int j = 0; j < trackInfo[0].length; ++j) {
				trackInfo[i][j] = scnr.nextLine();
			}
		}	
		scnr.close();
	}
}