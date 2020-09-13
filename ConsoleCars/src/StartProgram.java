

import java.util.List;
import java.util.Scanner;

import Controller.LotCarHelper;
import Model.LotCar;

public class StartProgram {
	
	static Scanner in = new Scanner(System.in);
	static LotCarHelper lch = new LotCarHelper();
	
	private static void addACar() {
		// TODO Auto-generated method stub
		System.out.print("Enter a make: ");
		String make = in.nextLine();
		System.out.print("Enter a model: ");
		String model = in.nextLine();
		System.out.print("Enter a year: ");
		int year = in.nextInt();
		
		LotCar toAdd = new LotCar(make, model, year);
		lch.insertCar(toAdd);
	}
	
	private static void deleteACar() {
		// TODO Auto-generated method stub
		System.out.print("Enter the make to delete: ");
		String make = in.nextLine();
		System.out.print("Enter the model to delete: ");
		String model = in.nextLine();
		System.out.print("Enter the year to delete: ");
		int year = in.nextInt();
		
		LotCar toDelete = new LotCar(make, model, year);
		lch.deleteCar(toDelete);
		
	}
	
	private static void editACar() {
		// TODO Auto-generated method stub
		System.out.println("How would yuou like to search? ");
		System.out.println("1 : Search by Make");
		System.out.println("2 : Search by Model");
		System.out.println("3 : Search by Year");
		int searchBy = in.nextInt();
		in.nextLine();
		List<LotCar> foundCar;
		if (searchBy == 1) {
			System.out.print("Enter the Make: ");
			String makeName = in.nextLine();
			foundCar = lch.searchForCarByMake(makeName);
			
		} else if (searchBy == 2) {
			System.out.print("Enter the Model: ");
			String modelName = in.nextLine();
			foundCar = lch.searchForCarByModel(modelName);
		} else {
			System.out.print("Enter the Year: ");
			int yearName = in.nextInt();
			foundCar = lch.searchForCarByYear(yearName);
		}
		
		if (!foundCar.isEmpty()) {
			System.out.println("Found Results.");
			for (LotCar l : foundCar) {
				System.out.println(l.getId() + " : " + l.toString());
			}
			System.out.print("Which ID to edit: ");
			int idToEdit = in.nextInt();
			
			LotCar toEdit = lch.searchForCarById(idToEdit);
			System.out.println("Retrieved " + toEdit.getModel() + " from " + toEdit.getMake() + " year " + toEdit.getYear());
			System.out.println("1 : Update Make");
			System.out.println("2 : Update Model");
			System.out.println("3 : Update Year");
			int update = in.nextInt();
			in.nextLine();
			
			if (update == 1) {
				System.out.print("New Make: ");
				String newMake = in.nextLine();
				toEdit.setMake(newMake);
			} else if (update == 2) {
				System.out.print("New Model: ");
				String newModel = in.nextLine();
				toEdit.setModel(newModel);
			} else if (update == 3) {
				System.out.print("New Year: ");
				int newYear = in.nextInt();
				toEdit.setYear(newYear);
			}
			
			lch.updateCar(toEdit);
		} else {
			System.out.println("---- No results found");
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runMenu();

	}
	
	public static void runMenu() {
		boolean goAgain = true;
		System.out.println("--- Welcome to our awesome car lot! ---");
		while (goAgain) {
			System.out.println("* Select an item:");
			System.out.println("* 1 -- Add a car");
			System.out.println("* 2 -- Edit a car");
			System.out.println("* 3 -- Delete a car");
			System.out.println("* 4 -- View the lot");
			System.out.println("* 5 -- Exit the awesome program");
			System.out.print("* Your selection: ");
			int selection = in.nextInt();
			in.nextLine();
			
			if (selection == 1) {
				addACar();
			} else if (selection == 2) {
				editACar();
			} else if (selection == 3) {
				deleteACar();
			} else if (selection == 4) {
				viewTheLot();
			} else {
				lch.cleanUp();
				System.out.println("  Goodbye!  ");
				goAgain = false;
			}
		}
	}
	
	private static void viewTheLot() {
		// TODO Auto-generated method stub
		List<LotCar> allCars = lch.showAllCars();
		for(LotCar singleCar : allCars) {
			System.out.println(singleCar.returnCarDetails());
		}
		
	}
}
