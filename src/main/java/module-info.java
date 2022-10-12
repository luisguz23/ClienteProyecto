module com.example.clienteproyecto {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.clienteproyecto to javafx.fxml;
    exports com.example.clienteproyecto;
}