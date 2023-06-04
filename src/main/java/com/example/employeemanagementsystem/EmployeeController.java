package com.example.employeemanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class EmployeeController {
    @FXML
    private TextField fullNameField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField salaryField;

    @FXML
    private ComboBox<String> departmentComboBox;

    @FXML
    private TableView<Employee> employeeTableView;

    @FXML
    private TableColumn<Employee, String> fullNameColumn;

    @FXML
    private TableColumn<Employee, String> addressColumn;

    @FXML
    private TableColumn<Employee, String> phoneColumn;

    @FXML
    private TableColumn<Employee, Double> salaryColumn;

    @FXML
    private TableColumn<Employee, String> departmentColumn;

    private ObservableList<Employee> employeeList;

    public void initialize() {
        employeeList = FXCollections.observableArrayList();
        employeeTableView.setItems(employeeList);

        employeeTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                Employee selectedEmployee = employeeTableView.getSelectionModel().getSelectedItem();
                if (selectedEmployee != null) {
                    fullNameField.setText(selectedEmployee.getFullName());
                    addressField.setText(selectedEmployee.getAddress());
                    phoneField.setText(selectedEmployee.getPhone());
                    salaryField.setText(String.valueOf(selectedEmployee.getSalary()));
                    departmentComboBox.setValue(selectedEmployee.getDepartment());
                }
            }
        });
        phoneField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Remove any non-digit characters
            String digitsOnly = newValue.replaceAll("[^\\d]", "");

            if (digitsOnly.length() > 11) {
                digitsOnly = digitsOnly.substring(0, 11);
            }

            // Set the modified value back to the phoneField
            phoneField.setText(digitsOnly);
        });

        salaryField.textProperty().addListener((observable, oldValue, newValue) -> {

            String digitsOnly = newValue.replaceAll("[^\\d]", "");

            salaryField.setText(digitsOnly);
        });

        departmentComboBox.getItems().addAll(
                "IT",
                "Engineering",
                "Finance",
                "Management",
                "Sales",
                "Accounting",
                "Technology",
                "Security Department"
        );

        fullNameColumn.setCellValueFactory(cellData -> cellData.getValue().fullNameProperty());
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        salaryColumn.setCellValueFactory(cellData -> cellData.getValue().salaryProperty().asObject());
        departmentColumn.setCellValueFactory(cellData -> cellData.getValue().departmentProperty());
    }

    @FXML
    private void createEmployee() {
        String fullName = fullNameField.getText();
        String address = addressField.getText();
        String phone = phoneField.getText();
        String salaryText = salaryField.getText();
        String department = departmentComboBox.getValue();

        if (fullName.isEmpty() || address.isEmpty() || phone.isEmpty() || salaryText.isEmpty() || department == null) {
            showAlert("Error", "Please fill in all fields.");
        } else {
            try {
                double salary = Double.parseDouble(salaryText);
                Employee employee = new Employee(fullName, address, phone, salary, department);
                employeeList.add(employee);
                clearFields();
            } catch (NumberFormatException e) {
                showAlert("Error", "Invalid salary value.");
            }
        }
    }

    @FXML
    private void updateEmployee() {
        Employee selectedEmployee = employeeTableView.getSelectionModel().getSelectedItem();
        if (selectedEmployee == null) {
            showAlert("Error", "No employee selected for update.");
        } else {
            String fullName = fullNameField.getText();
            String address = addressField.getText();
            String phone = phoneField.getText();
            String salaryText = salaryField.getText();
            String department = departmentComboBox.getValue();

            if (fullName.isEmpty() || address.isEmpty() || phone.isEmpty() || salaryText.isEmpty() || department == null) {
                showAlert("Error", "Please fill in all fields.");
            } else {
                try {
                    double salary = Double.parseDouble(salaryText);
                    selectedEmployee.setFullName(fullName);
                    selectedEmployee.setAddress(address);
                    selectedEmployee.setPhone(phone);
                    selectedEmployee.setSalary(salary);
                    selectedEmployee.setDepartment(department);
                    employeeTableView.refresh();
                    clearFields();
                } catch (NumberFormatException e) {
                    showAlert("Error", "Invalid Salary Value.");
                }
            }
        }
    }

    @FXML
    private void deleteEmployee() {
        Employee selectedEmployee = employeeTableView.getSelectionModel().getSelectedItem();
        if (selectedEmployee == null) {
            showAlert("Error", "No employee selected for deletion.");
        } else {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("Are you sure you want to delete the selected employee?");

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                employeeList.remove(selectedEmployee);
                clearFields();
            }
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void clearFields() {
        fullNameField.clear();
        addressField.clear();
        phoneField.clear();
        salaryField.clear();
        departmentComboBox.getSelectionModel().clearSelection();
    }
}
