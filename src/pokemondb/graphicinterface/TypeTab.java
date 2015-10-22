package pokemondb.graphicinterface;


import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import static pokemondb.tables.ConnectionDB.MSG;
import pokemondb.tables.Type;

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
public class TypeTab {

    @FXML
    private final TableView<Type> typeTable;
    @FXML
    private final TableColumn<Type, String> name;
    @FXML
    private final TableColumn<Type, String> sName;
    @FXML
    private final TableColumn<Type, String> sprite;

    TypeTab(TableView<Type> typeTable) {
        this.typeTable = typeTable;
        
        fillTypeTable();

        name = (TableColumn<Type, String>) typeTable.getColumns().get(0);
        sName = (TableColumn<Type, String>) typeTable.getColumns().get(1);
        sprite = (TableColumn<Type, String>) typeTable.getColumns().get(2);
        
        name.setCellValueFactory(cellData
                -> cellData.getValue().spanishNameProperty());
        sName.setCellValueFactory(cellData
                -> cellData.getValue().typeNameProperty());
        sprite.setCellValueFactory(cellData
                -> cellData.getValue().typeSpriteProperty());
    }

    private void fillTypeTable() {

        try {

            ObservableList<Type> data
                    = FXCollections.observableArrayList();
            ResultSet rs = new Type().selectAll("");            

            while (rs.next()) {

                String s1, s2, s3;
                int n;
                
                s1 = rs.getString("Nombre");
                s2 = rs.getString("Nombre (Inglés)");
                s3 = rs.getString("Ícono");
                n = rs.getInt("ID");
                
                Type tmp = new Type(n, s2, s1, s3);

                data.add(tmp);
            }
            typeTable.setItems(data);

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error en la conexión");
            alert.setContentText(MSG);
            alert.getDialogPane().setPrefSize(480, 250);
            alert.showAndWait()
                    .filter(response -> response == ButtonType.OK);

        }
    }

}
