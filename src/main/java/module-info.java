module ca.bcit.comp2522.termproject.oppaigames {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens ca.bcit.comp2522.termproject.oppaigames to javafx.fxml;
    exports ca.bcit.comp2522.termproject.oppaigames;
}