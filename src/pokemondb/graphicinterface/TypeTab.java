package pokemondb.graphicinterface;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import pokemondb.tables.ConnectionDB;
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
    private final TableView<Damage> typeTable;
    @FXML
    private final TableColumn<Damage, ImageView> type;
    @FXML
    private final TableColumn<Damage, ImageView> aType;
    @FXML
    private final TableColumn<Damage, Number> dmg;

    TypeTab(TableView<Damage> typeTable) {
        this.typeTable = typeTable;

        fillTypeTable();

        type = (TableColumn<Damage, ImageView>) typeTable.getColumns().get(0);
        aType = (TableColumn<Damage, ImageView>) typeTable.getColumns().get(1);
        dmg = (TableColumn<Damage, Number>) typeTable.getColumns().get(2);

        type.setCellValueFactory(cellData
                -> cellData.getValue().type.typeSpriteProperty());
        type.setStyle("-fx-alignment: CENTER;");
        aType.setCellValueFactory(cellData
                -> cellData.getValue().attacking.typeSpriteProperty());
        aType.setStyle("-fx-alignment: CENTER;");
        dmg.setCellValueFactory(cellData
                -> cellData.getValue().damage);
        dmg.setStyle("-fx-alignment: CENTER;");
        
    }

    /**
     * Fills the table Types with database values
     *
     * @throws SQLException if a database access error occurs
     */
    private void fillTypeTable() {

        try {

            ObservableList<Damage> data = FXCollections.observableArrayList(),
                    data2 = FXCollections.observableArrayList();
            ResultSet rs = new Type().selectDamageTaken();

            while (rs.next()) {

                String s1, s2;
                double d;

                s1 = rs.getString("Tipo");
                s2 = rs.getString("Tipo Atacante");
                d = rs.getDouble("Daño recibido");
                Connection conn = ConnectionDB.open();
                
                String sql1 = "SELECT * FROM Types WHERE SpanishNAME = '"
                        + s1 + "';",
                sql2 = "SELECT * FROM Types WHERE SpanishNAME = '"
                        + s2 + "';";
                ResultSet rs2 = conn.createStatement().executeQuery(sql1),
                        rs3 = conn.createStatement().executeQuery(sql2);
                
                Type tmp1 = new Type(rs2.getInt(1), rs2.getString(2), 
                        rs2.getString(3), rs2.getString(4));
                Type tmp2 = new Type(rs3.getInt(1), rs3.getString(2), 
                        rs3.getString(3), rs3.getString(4));

                Damage damage = new Damage(tmp1, tmp2, d);
                data.add(damage);
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
    
    public class Damage{
        
        private final Type type;
        private final Type attacking;
        public final SimpleDoubleProperty damage;
        
        public Damage(Type t1, Type t2, double dam){
            type = t1;
            attacking = t2;
            damage = new SimpleDoubleProperty(dam);
        }       
        
    }

}
