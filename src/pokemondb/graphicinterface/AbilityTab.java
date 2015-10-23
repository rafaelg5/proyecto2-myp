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
import pokemondb.tables.Ability;
import static pokemondb.tables.ConnectionDB.MSG;


public class AbilityTab {
    @FXML
    private final TableView<Ability> abilityTable;
    @FXML
    private final TableColumn<Ability, String> sName;
    @FXML
    private final TableColumn<Ability, String> name;
    @FXML
    private final TableColumn<Ability, String> desc;
    
    public AbilityTab(TableView<Ability> abilityTable){
        this.abilityTable = abilityTable;
        
        fillAbilityTable();
        
        sName = (TableColumn<Ability, String>) abilityTable.getColumns().get(0);
        name = (TableColumn<Ability, String>) abilityTable.getColumns().get(1);
        desc = (TableColumn<Ability, String>) abilityTable.getColumns().get(2);
        
        sName.setCellValueFactory(cellData
                -> cellData.getValue().spanishNameProperty());
        name.setCellValueFactory(cellData
                -> cellData.getValue().abilityNameProperty());
        desc.setCellValueFactory(cellData
                -> cellData.getValue().abilityDescriptionProperty());
    }
    
    /**
     * Fills the table Abilities with database values
     * @throws SQLException if a database access error occurs
     */
     private void fillAbilityTable() {

        try {

            ObservableList<Ability> data
                    = FXCollections.observableArrayList();
            ResultSet rs = new Ability().selectAll("");            

            while (rs.next()) {

                String s1, s2, s3;
                int n;
                
                s1 = rs.getString("Nombre");
                s2 = rs.getString("Nombre (Inglés)");
                s3 = rs.getString("Descripción");
                n = rs.getInt("ID");
                
                Ability tmp = new Ability(n, s2, s1, s3);

                data.add(tmp);
            }
            abilityTable.setItems(data);

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
