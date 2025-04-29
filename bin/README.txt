Welcome to the Food Truck Project!

This JavaFX Project Features a customer facing UI for a food truck, as well as an admin view. The customer can buy different drinks and grilled cheeses. 
The admin can manage inventory and track sales analytics in a different window. 

Java Files:
1. FoodItem.java - Represents a single food item that can be sold like sprite or grilled cheese. It has a name and a price.
2. FoodTruck.java - Handles the backend logic of selling FoodItems, tracking inventory, and restocking
3. Controller.java - The main controller for the project which handles customer UI interaction
4. AdminController.java - The admin controller which handles admin UI interaction
5. App.java - Serves as the entry point for the application

FXML Files:
1. MainView.fxml - The customer view which includes a menu and a food truck!
2. AdminView.fxml - The admin view which includes sales analytics and inventory tracking.

CSS File:
- styles.css - Needed to applying styling to the fxml files!

Resources:
- This folder holds the images used for this project!

How to Run:
1. cd into the src directory
2. compile:     javac --module-path C:\JavaFX\javafx-sdk-24.0.1\lib --add-modules javafx.controls,javafx.fxml app/*.java
3. run:         java --module-path C:\JavaFX\javafx-sdk-24.0.1\lib --add-modules javafx.controls,javafx.fxml app.App