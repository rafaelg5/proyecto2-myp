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
import static pokemondb.tables.ConnectionDB.MSG;
import pokemondb.tables.Pokemon;


public class PokemonTab{
    
    @FXML
    private final TableView<Pokemon> pokemonTable;
    @FXML
    private final TableColumn<Pokemon, Number> num;
    @FXML
    private final TableColumn<Pokemon, String> name;
    @FXML
    private final TableColumn<Pokemon, Number> height;
    @FXML
    private final TableColumn<Pokemon, Number> weight;
    @FXML
    private final TableColumn<Pokemon, Number> hp;
    @FXML
    private final TableColumn<Pokemon, Number> atk;
    @FXML
    private final TableColumn<Pokemon, Number> def;
    @FXML
    private final TableColumn<Pokemon, Number> spAtk;
    @FXML
    private final TableColumn<Pokemon, Number> spDef;
    @FXML
    private final TableColumn<Pokemon, Number> spd;
    @FXML
    private final TableColumn<Pokemon, String> description;
    
    public PokemonTab(TableView<Pokemon> pokemonTable){
        this.pokemonTable = pokemonTable;
        fillPokemonTable();
        
        num = 
                (TableColumn<Pokemon, Number>)pokemonTable.getColumns().get(0);
        name = 
                (TableColumn<Pokemon, String>)pokemonTable.getColumns().get(1);
        height = 
                (TableColumn<Pokemon, Number>)pokemonTable.getColumns().get(2);
        weight = 
                (TableColumn<Pokemon, Number>)pokemonTable.getColumns().get(3);
        hp = 
                (TableColumn<Pokemon, Number>)pokemonTable.getColumns().get(4);
        atk = 
                (TableColumn<Pokemon, Number>)pokemonTable.getColumns().get(5);        
        def = 
                (TableColumn<Pokemon, Number>)pokemonTable.getColumns().get(6);        
        spAtk = 
                (TableColumn<Pokemon, Number>)pokemonTable.getColumns().get(7);
        spDef = 
                (TableColumn<Pokemon, Number>)pokemonTable.getColumns().get(8);
        spd = 
                (TableColumn<Pokemon, Number>)pokemonTable.getColumns().get(9);
        description = 
                (TableColumn<Pokemon, String>)pokemonTable.getColumns().get(10);
        
        num.setCellValueFactory(cellData
                -> cellData.getValue().dexNumberProperty());
        name.setCellValueFactory(cellData
                -> cellData.getValue().pokemonNameProperty());
        height.setCellValueFactory(cellData
                -> cellData.getValue().heightProperty());
        weight.setCellValueFactory(cellData
                -> cellData.getValue().weightProperty());
        hp.setCellValueFactory(cellData
                -> cellData.getValue().baseHPProperty());
        atk.setCellValueFactory(cellData
                -> cellData.getValue().baseAtkProperty());
        def.setCellValueFactory(cellData
                -> cellData.getValue().baseDefProperty());
        spAtk.setCellValueFactory(cellData
                -> cellData.getValue().baseSpAtkProperty());
        spDef.setCellValueFactory(cellData
                -> cellData.getValue().baseSpDefProperty());
        spd.setCellValueFactory(cellData
                -> cellData.getValue().baseSpdProperty());
        description.setCellValueFactory(cellData
                -> cellData.getValue().pokemonDescriptionProperty());
    }
    
    /**
     * Fills the table Pokemon with database values
     * @throws SQLException if a database access error occurs
     */
    private void fillPokemonTable() {

        try {

            ObservableList<Pokemon> data
                    = FXCollections.observableArrayList();
            ResultSet rs = new Pokemon().selectAll("");

            while (rs.next()) {

                String s1, s2, s3;
                int n1, n2, n3, n4, n5, n6, n7, n8;
                double d1, d2;

                s1 = rs.getString("Nombre");
                s2 = rs.getString("Sprite");
                s3 = rs.getString("Descripción");
                n1 = rs.getInt("ID");
                n2 = rs.getInt("#");
                n3 = rs.getInt("PS");
                n4 = rs.getInt("Ataque");
                n5 = rs.getInt("Defensa");
                n6 = rs.getInt("At. Especial");
                n7 = rs.getInt("Def. Especial");
                n8 = rs.getInt("Velocidad");
                d1 = rs.getDouble("Altura");
                d2 = rs.getDouble("Peso");

                Pokemon tmp = new Pokemon(s1, s2, s3, n1, n2, n3, n4, n5, n6, 
                        n7, n8, d1, d2);

                data.add(tmp);
            }
            pokemonTable.setItems(data);

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
