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

import java.sql.Connection;
import java.sql.ResultSet;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import pokemondb.tables.ConnectionDB;

public class PokemonDB extends Application {

    private ObservableList<ObservableList> data;
    private TableView tableview;

    public void buildData() {

        Connection c;
        data = FXCollections.observableArrayList();

        try {
            c = ConnectionDB.open();

            String sql = "SELECT DexNumber AS '#', PokemonName AS Pokemon, "
                    + "Height AS 'Altura (m)', Weight AS 'Peso (kg)', "
                    + "BaseHP AS PS, BaseAtk AS Ataque, BaseDef AS Defensa, "
                    + "BaseSpAtk AS 'At. Esp', BaseSpDef AS 'Def. Esp', "
                    + "BaseSpd AS Velocidad, PokemonDescription AS Descripción"
                    + " FROM Pokemon ORDER BY DexNumber;";
            ResultSet rs = c.createStatement().executeQuery(sql);
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col
                        = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String>
                            call(CellDataFeatures<ObservableList, String> param) {

                        return new SimpleStringProperty(
                                param.getValue().get(j).toString());
                    }
                });

                tableview.getColumns().addAll(col);
            }

            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();

                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {

                    row.add(rs.getString(i));
                }
                data.add(row);

            }
            tableview.setItems(data);

        } catch (Exception e) {
        }
    }

    @Override
    public void start(Stage stage) throws Exception {

        tableview = new TableView();
        buildData();

        Scene scene = new Scene(tableview);
        stage.setScene(scene);
        stage.show();
        /*Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

         Scene scene = new Scene(root);

         stage.setTitle("Base de Datos Pokémon");
         stage.setScene(scene);
         stage.show();*/
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
