/*
 * Copyright (C) 2015 Rafael de Jesús García García
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package pokemondb.tables;

import java.sql.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class ConnectionDB {

    private static Connection conn = null;
    
    private final static String msg = "Hubo un problema al conectar a la base "
            + "de datos. Revise que el archivo Pokemon.db está en: "
            + "proyecto2/lib/Pokemon.db.\n"
            + "Si el problema persiste, mandar correo a la "
            + "dirección: rafaelg5@ciencias.unam.mx";

    public static Connection open() {
        try {
            if (conn == null || conn.isClosed()) {
                getConn();
            }

        } catch (SQLException e) {
            Alert alert = new Alert(AlertType.ERROR, msg);
            alert.showAndWait()
                    .filter(response -> response == ButtonType.OK);
        }
        return conn;
    }

    public static void close() {

        try {
            if (conn == null || conn.isClosed()) {
                Alert alert = new Alert(AlertType.WARNING, "No existe conexión "
                        + "a la base de datos. Debe conectarse primero.");
                alert.showAndWait()
                    .filter(response -> response == ButtonType.OK);
                return;
            }
            conn.commit();
            conn.close();

        } catch (SQLException e) {
            Alert alert = new Alert(AlertType.ERROR, e.getMessage());
            alert.showAndWait()
                    .filter(response -> response == ButtonType.OK);
        }
    }

    private static Connection getConn() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");

            conn = DriverManager.getConnection("jdbc:sqlite:./lib/Pokemon.db");
            conn.setAutoCommit(false);
        } catch (ClassNotFoundException e) {

            Alert alert = new Alert(AlertType.ERROR, e.getMessage());
            alert.showAndWait()
                    .filter(response -> response == ButtonType.OK);
        } catch (SQLException e) {
            conn.close();
            Alert alert = new Alert(AlertType.ERROR, e.getMessage() + "\n"
                    + msg);
            alert.showAndWait()
                    .filter(response -> response == ButtonType.OK);
        }
        return conn;
    }
}
