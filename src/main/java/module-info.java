module com.example.employeemanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
            
                requires net.synedra.validatorfx;
                requires org.kordamp.bootstrapfx.core;
            
    opens com.example.employeemanagementsystem to javafx.fxml;
    exports com.example.employeemanagementsystem;
}