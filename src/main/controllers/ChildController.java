package main.controllers;

import javafx.fxml.Initializable;

public abstract class ChildController implements Initializable {
	public MainController parentAdminController;

	public void setParentAdminController(MainController parentAdminController) {
		this.parentAdminController = parentAdminController;
	}

	}
