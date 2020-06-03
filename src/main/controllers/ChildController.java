package main.controllers;

import javafx.fxml.Initializable;

public abstract class ChildController implements Initializable {
	public MainController parentMainController;

	public void setParentMainController(MainController parentMainController) {
		this.parentMainController = parentMainController;
	}

	}
