module avocatg1.avocat {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.web;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    opens classes to javafx.base;
    opens avocatg1.avocat to javafx.fxml;
    exports avocatg1.avocat;
    exports controllers;
    opens controllers to javafx.fxml;
    exports DBModels;
    opens DBModels to javafx.fxml;
}