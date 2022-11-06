module bomberman {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.media;
    requires java.desktop;
    requires transitive javafx.graphics;
    
    opens uet.oop.bomberman;
    exports uet.oop.bomberman.controller to BombermanGame;
    exports uet.oop.bomberman.entities.dynamic to uet.oop.bomberman.controller;
    exports uet.oop.bomberman.entities.dynamic.items to uet.oop.bomberman.controller;
    exports uet.oop.bomberman.entities to uet.oop.bomberman.entities.dynamic;
    exports uet.oop.bomberman.graphics to uet.oop.bomberman.entities;
    exports uet.oop.bomberman.entities.enemies to uet.oop.bomberman.graphics;
}