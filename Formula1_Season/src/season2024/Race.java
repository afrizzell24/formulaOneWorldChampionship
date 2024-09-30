package season2024;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author <<Andrew Frizzell>>
 * 
 *         This class tracks and displays data for each individual race, and
 *         returns info about the updated Championships.
 */
public class Race {
	private Driver fastestDriver;
	private ArrayList<Team> teamList;
	private ArrayList<Driver> driverList;
	private ArrayList<Double> raceTimes;
	private int[] points;
	private int numLaps;
	private int averagePace;
	private double fastestLap;
	private String trackName;

	public Race(int whichRace, String[][] track, ArrayList<Team> teamList) {
		this.teamList = teamList;
		driverList = new ArrayList<Driver>();
		raceTimes = new ArrayList<Double>();
		numLaps = Integer.parseInt(track[whichRace][3]);
		averagePace = Integer.parseInt(track[whichRace][2]);
		trackName = track[whichRace][0];
		fastestDriver = null;
		fastestLap = 9999;
		points = new int[10];
		setPoints();
	}

	/**
	 * Begins the race and calls methods required to sort and display race data.
	 */
	public void beginRace() {
		System.out.println("Welcome to the " + trackName + "! Here are your race results: \n");
		getRaceResults();
		printRaceResults();
	}

	private void getRaceResults() {

		for (int i = 0; i < teamList.size(); ++i) {
			getAndSortFinalTime(i, teamList.get(i).driver1);
			getAndSortFinalTime(i, teamList.get(i).driver2);

		}
	}

	/**
	 * A randomizer determines each lap time. There is also a reduction in each lap
	 * time based on the average of each driver's "skill" variable and their
	 * vehicle's "performance" variable. Also records which driver has the fastest
	 * lap
	 * 
	 * @param index
	 * @param racer
	 */
	private void getAndSortFinalTime(int index, Driver racer) {
		Random rand = new Random();
		double time = 0;
		double lapTime;
		
		int totalPitTime = ((rand.nextInt() % 10) + 25) * 2;

		for (int j = 0; j < numLaps; ++j) {
			lapTime = (rand.nextDouble() % 17) + averagePace
					- averagePace * (teamList.get(index).getVehiclePerformance() + racer.getDriverSkill() / 2.0);

			time += lapTime;
			if (lapTime < fastestLap) {
				fastestLap = lapTime;
				fastestDriver = racer;
			}
		}

		time += totalPitTime;
		sortTime(time, racer);
	}

	/**
	 * Sorts the order of the drivers based on their final race time.
	 * 
	 * @param driveTime
	 * @param racer
	 */
	private void sortTime(double driveTime, Driver racer) {

		if (raceTimes.size() == 0) {
			raceTimes.add(driveTime);
			driverList.add(racer);
		} else {
			boolean set = false;

			for (int i = 0; i < raceTimes.size(); ++i) {
				if (driveTime < raceTimes.get(i)) {
					raceTimes.add(i, driveTime);
					driverList.add(i, racer);

					set = true;
					break;
				}
			}

			if (!set) {
				raceTimes.add(driveTime);
				driverList.add(racer);
			}
		}
	}
	
	/**
	 * Updates, sorts, and displays data for the driver's championship. This method also updates driver
	 * info for the most current race to display in the updated championship results. In Formula One,
	 * if the driver with the fastest lap finishes in the top 10 then they get one more championship
	 * point. This rule is implemented in the method.
	 * @return
	 */
	public ArrayList<Driver> getDriversChampResults() {
		System.out.println("\nHere are your current Driver's Championship standings: \n");

		for (int i = 0; i < driverList.size(); ++i) {
			if (driverList.get(i).equals(fastestDriver) && i < 10) {
				++points[i];
			}

			if (i < 10) {
				driverList.get(i).updatePoints(points[i]);
				driverList.get(i).setPointsInRace(points[i]);
			} else {
				driverList.get(i).setPointsInRace(0);
			}
		}

		driverSort(driverList);
		
		// This is mostly just a ton of formatting stuff
		
		System.out.printf("%1$-10s %2$12s %3$22s", "Team:", "Driver:", "Points:" + "\n\n");
		for (int i = 0; i < driverList.size(); ++i) {
			System.out.printf("%1$-15s %2$-20s %3$3s", driverList.get(i).getDriverTeam(),
							  driverList.get(i).getDriverName(), 
							  String.valueOf(driverList.get(i).getDriverPoints()));

			if (driverList.get(i).getPointsInRace() != 0) {
				System.out.printf("%1$7s", "+" + String.valueOf(driverList.get(i).getPointsInRace()));
			}

			if (driverList.get(i).equals(fastestDriver) && (driverList.get(i).getPointsInRace() != 0)) {
				System.out.print(" (Fastest Lap)");
			}
			System.out.println();
		}

		// End of formatting

		return driverList;
	}

	/**
	 * Updates, sorts, and displays info for the constructor's championship. The method also
	 * updates data for the previous race to display with the updated championship results.
	 * @return
	 */
	public ArrayList<Team> getConstructorsChampResults() {
		System.out.println("\nHere are your Constructor's Champoinship Standings: \n");

		for (int i = 0; i < teamList.size(); ++i) {
			teamList.get(i).updateConstructorPoints(
					teamList.get(i).driver1.getPointsInRace() + teamList.get(i).driver2.getPointsInRace());
		}

		teamSort(teamList);
		
		// This is mostly formatting stuff for displaying to the console

		System.out.printf("%1$-10s %2$12s", "Team:", "Points:" + "\n\n");
		for (int i = 0; i < teamList.size(); ++i) {
			System.out.printf("%1$-15s %2$-20s", teamList.get(i).getTeamName(), teamList.get(i).getConstructorPoints());

			if (teamList.get(i).getRacePoints() != 0) {
				System.out.printf("%1$7s", "+" + String.valueOf(teamList.get(i).getRacePoints()));
			}
			System.out.println();
		}
		
		// End of formatting

		return teamList;
	}
	
	/**
	 * Sorts the list of driver list in descending order based on their driverPoints value
	 * @param driveList
	 */
	private static void driverSort(ArrayList<Driver> driveList) {
		
		for (int i = driveList.size() - 1; i > 0; --i) {
			int maxIndex = i;
			
			for (int j = i - 1; j >= 0; --j) {
				if (driveList.get(j).getDriverPoints() < driveList.get(maxIndex).getDriverPoints()) {
					maxIndex = j;
				}
			}
			
			Driver temp = driveList.get(i);
			driveList.set(i, driveList.get(maxIndex));
			driveList.set(maxIndex, temp);
		}
	}
	
	/**
	 * Sorts the list of teams in descending order based on their constructorPoints value
	 * @param teamsList
	 */
	private static void teamSort(ArrayList<Team> teamsList) {
		
		for (int i = teamsList.size() - 1; i > 0; --i) {
			int maxIndex = i;
			
			for (int j = i - 1; j >= 0; --j) {
				if (teamsList.get(j).getConstructorPoints() < teamsList.get(maxIndex).getConstructorPoints()) {
					maxIndex = j;
				}
			}
			
			Team temp = teamsList.get(i);
			teamsList.set(i, teamsList.get(maxIndex));
			teamsList.set(maxIndex, temp);
		}
	}
	

	/**
	 * The championship points distribution for the final positions of a Formula One race.
	 */
	private void setPoints() {
		points[0] = 25;
		points[1] = 18;
		points[2] = 15;
		points[3] = 12;
		points[4] = 10;
		points[5] = 8;
		points[6] = 6;
		points[7] = 4;
		points[8] = 2;
		points[9] = 1;
	}

	/**
	 * Prints driver times at the end of a race.
	 */
	private void printRaceResults() {
		System.out.printf("%1$-10s %2$12s %3$22s", "Team:", "Driver:", "Time:" + "\n\n");

		for (int i = 0; i < driverList.size(); ++i) {
			System.out.printf("%1$-15s %2$-23s", driverList.get(i).getDriverTeam(), driverList.get(i).getDriverName());

			if (i == 0) {
				System.out.printf(raceTimes.get(i).intValue() / 3600 + ":" + "%.0f:%02.3f\n",
						(raceTimes.get(i) - 3600.0) / 60, (raceTimes.get(i) % 60.0));
			} else {
				System.out.printf("+%.3f\n", raceTimes.get(i) - raceTimes.get(i - 1));
			}
		}

	}
}
