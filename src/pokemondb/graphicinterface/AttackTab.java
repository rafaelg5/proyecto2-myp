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
import java.sql.SQLException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pokemondb.tables.Attack;
import pokemondb.tables.AttackCategory;
import pokemondb.tables.ConnectionDB;
import static pokemondb.tables.ConnectionDB.MSG;
import pokemondb.tables.Type;

/**
 *
 * @author rgarcia
 */
public class AttackTab {
    
    @FXML
    private final TableView<Attack> attackTable;
    @FXML
    private final TableColumn<Attack, String> name;
    @FXML
    private final TableColumn<Attack, String> sName;
    @FXML
    private final TableColumn<Attack, Number> pow;
    @FXML
    private final TableColumn<Attack, Number> acc;
    @FXML
    private final TableColumn<Attack, Number> type;
    //@FXML
    //private final TableColumn<Type, String> type;
    //@FXML
    //private final TableColumn<AttackCategory, String> cat;
    @FXML
    private final TableColumn<Attack, Number> cat;
    @FXML
    private final TableColumn<Attack, String> desc;
    
    public AttackTab(TableView<Attack> attackTable, TableView<Type> typeTable,
            TableView<AttackCategory> atkCategoryTable) {
        
        this.attackTable = attackTable;
        
        fillAttackTable();
        
        name = (TableColumn<Attack, String>) attackTable.getColumns().get(0);
        sName = (TableColumn<Attack, String>) attackTable.getColumns().get(1);
        pow = (TableColumn<Attack, Number>) attackTable.getColumns().get(2);
        acc = (TableColumn<Attack, Number>) attackTable.getColumns().get(3);
        type = (TableColumn<Attack, Number>) attackTable.getColumns().get(4);
        //type = (TableColumn<Type, String>) typeTable.getColumns().get(0);
        cat = (TableColumn<Attack, Number>) attackTable.getColumns().get(5);
        //cat = (TableColumn<AttackCategory, String>) atkCategoryTable
        //        .getColumns().get(1);
        desc = (TableColumn<Attack, String>) attackTable.getColumns().get(6);
        
        name.setCellValueFactory(cellData
                -> cellData.getValue().attackNameProperty());
        sName.setCellValueFactory(cellData
                -> cellData.getValue().spanishNameProperty());
        pow.setCellValueFactory(cellData
                -> cellData.getValue().powerProperty());
        acc.setCellValueFactory(cellData
                -> cellData.getValue().accuracyProperty());
        type.setCellValueFactory(cellData
                -> cellData.getValue().typeIDProperty());
        //type.setCellValueFactory(cellData
        //        -> cellData.getValue().spanishNameProperty());
        //cat.setCellValueFactory(cellData
        //        -> cellData.getValue().categorySpriteProperty());
        cat.setCellValueFactory(cellData
                -> cellData.getValue().atkCategoryIDProperty());
        desc.setCellValueFactory(cellData
                -> cellData.getValue().attackDescriptionProperty());
    }
    
    private void fillAttackTable() {
        
        try {
            
            ObservableList<Attack> data
                    = FXCollections.observableArrayList();
            ResultSet rs = new Attack().selectAll("");
            
            while (rs.next()) {
                
                String s1, s2, s3;
                int n1, n2, n3, n4, n5;
                
                s1 = rs.getString("Nombre");
                s2 = rs.getString("Nombre (Inglés)");
                s3 = rs.getString("Descripción");
                n1 = rs.getInt("ID");
                n2 = rs.getInt("Poder");
                n3 = rs.getInt("Precisión");
                n4 = getTypeID(rs.getString("Tipo"));
                n5 = getCategoryID(rs.getString("Categoría"));
                
                Attack tmp = new Attack(n1, n2, n3, n4, n5, s1, s2, s3);
                
                data.add(tmp);
            }
            attackTable.setItems(data);
            
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error en la conexión");
            alert.setContentText(MSG);
            alert.getDialogPane().setPrefSize(480, 250);
            alert.showAndWait()
                    .filter(response -> response == ButtonType.OK);
            
        }
    }
    
    private int getTypeID(String s) throws SQLException {
        Connection c = ConnectionDB.open();
        String sql = "SELECT TypeID AS Tipo FROM Types WHERE SpanishName = '"
                + s + "';";
        ResultSet rs = c.createStatement().executeQuery(sql);
        int n = rs.getInt("Tipo");
        return n;
    }
    
    private int getCategoryID(String s) throws SQLException {
        Connection c = ConnectionDB.open();
        String sql = "SELECT AtkCategoryID AS Categoría FROM AtkCategory "
                + "WHERE CategoryName = '" + s + "';";
        ResultSet rs = c.createStatement().executeQuery(sql);
        int n = rs.getInt("Categoría");
        return n;
    }
}
