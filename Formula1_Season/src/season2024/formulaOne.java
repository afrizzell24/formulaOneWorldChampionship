package season2024;

import java.io.FileNotFoundException;

public class formulaOne {
	public static void main(String[] args) throws FileNotFoundException {
		Season season24 = new Season();
		
		season24.registerTeam("Mercedes", "George Russell", "Lewis Hamilton");
		season24.registerTeam("Redbull", "Max Verstappen", "Checo Perez");
		season24.registerTeam("Mclaren", "Oscar Piastri", "Lando Norris");
		season24.registerTeam("Ferrari", "Charles Leclerc", "Carlos Sainz");
		season24.registerTeam("Aston Martin", "Fernando Alonso", "Lance Stroll");
		season24.registerTeam("RB", "Yuki Tsunoda", "Daniel Ricciardo");
		season24.registerTeam("Haas", "Nico Hulkenberg", "Kevin Magnussen");
		season24.registerTeam("Williams", "Logan Sargeant", "Alex Albon");
		season24.registerTeam("Alpine", "Esteban Ocon", "Pierre Gasly");
		season24.registerTeam("Kick Sauber", "Valtteri Bottas", "Zhou Guanyu");
		
		season24.startSeason();		
	}
}
