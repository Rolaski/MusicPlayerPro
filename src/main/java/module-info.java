module com.example.musicplayerpro
{
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.base;
    requires java.naming;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires javafx.media;
    requires jbcrypt;

    opens com.example.musicplayerpro to javafx.fxml;
    opens com.example.musicplayerpro.files to javafx.fxml, org.hibernate.orm.core, jbcrypto;
    exports com.example.musicplayerpro.files;

}