module com.example.supplychainproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.supplychainproject to javafx.fxml;
    exports com.example.supplychainproject;
}