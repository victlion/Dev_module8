package org.example.db;

import org.flywaydb.core.Flyway;

public class Flyway_migration {
    public static void main(String[] args) {

        Flyway flyway = Flyway.configure().dataSource("jdbc:h2:~/testBase",null,null).load();

        flyway.migrate();
    }
}