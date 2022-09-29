module bomberman {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.media;
    requires java.desktop;
    requires transitive javafx.graphics;
    
    opens uet.oop.bomberman;
    exports uet.oop.bomberman;
}