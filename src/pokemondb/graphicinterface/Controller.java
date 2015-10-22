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

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import pokemondb.tables.*;

public class Controller implements Initializable {
    
    @FXML
    private TableView<ObservableList<Pokemon>> pokemonTable;
    @FXML
    private TableColumn<Pokemon, Integer> DexNumber;
    @FXML
    private TableColumn<Pokemon, String> PokemonName;
    @FXML
    private TableColumn<Pokemon, Double> Height;
    @FXML
    private TableColumn<Pokemon, Double> Weight;
    @FXML
    private TableColumn<Pokemon, Integer> BaseHP;
    @FXML
    private TableColumn<Pokemon, Integer> BaseAtk;
    @FXML
    private TableColumn<Pokemon, Integer> BaseDef;
    @FXML
    private TableColumn<Pokemon, Integer> BaseSpAtk;
    @FXML
    private TableColumn<Pokemon, Integer> BaseSpDef;
    @FXML
    private TableColumn<Pokemon, Integer> BaseSpd;
    @FXML
    private TableColumn<Pokemon, String> PokemonDescription;
    @FXML
    private TableView<ObservableList<Type>> typeTable;
    @FXML
    private TableView<ObservableList<Attack>> attackTable;
    @FXML
    private TableView<ObservableList<Ability>> abilityTable;
    @FXML
    private ImageView cover;
    @FXML
    private Pane mainPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cover.fitWidthProperty().bind(mainPane.widthProperty());
        cover.fitHeightProperty().bind(mainPane.heightProperty());
        fillPokemonTable();
    }

    private void fillPokemonTable() {

        try {

            ObservableList<ObservableList<Pokemon>> data
                    = FXCollections.observableArrayList();
            ResultSet rs = new Pokemon().selectAll("");

            while (rs.next()) {

                ObservableList<Pokemon> row
                        = FXCollections.observableArrayList();

                String name, sprite, desc;
                int id, num, hp, atk, def, spatk, spdef, spd;
                double height, weight;

                name = rs.getString("Nombre");
                sprite = rs.getString("Sprite");
                desc = rs.getString("Descripción");
                id = rs.getInt("ID");
                num = rs.getInt("#");
                hp = rs.getInt("PS");
                atk = rs.getInt("Ataque");
                def = rs.getInt("Defensa");
                spatk = rs.getInt("At. Especial");
                spdef = rs.getInt("Def. Especial");
                spd = rs.getInt("Velocidad");
                height = rs.getDouble("Altura");
                weight = rs.getDouble("Peso");

                Pokemon tmp = new Pokemon(name, sprite, desc, id, num, hp, atk, def,
                        spatk, spdef, spd, height, weight);
                /*DexNumber.setCellValueFactory(new Callback<CellDataFeatures<Pokemon, Integer>, ObservableValue<Integer>>() {
                    @Override
                    public ObservableValue<Integer> call(CellDataFeatures<Pokemon, Integer> p) {
                        
                        p.getValue();
                        Integer i = p.getValue().getDexNumber();
                        return null;
                    }
                });*/

                row.add(tmp);
                data.add(row);
                System.out.println(pokemonTable.getColumns().get(0).getCellData(row));
            }
            pokemonTable.setItems(data);

        } catch (SQLException e) {

        }

    }
}
