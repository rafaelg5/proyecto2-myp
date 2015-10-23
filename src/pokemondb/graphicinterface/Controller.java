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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import pokemondb.tables.*;

public class Controller implements Initializable {

    @FXML
    private TableView<Pokemon> pokemonTable;
    @FXML
    private TableView<Type> typeTable;
    @FXML
    private TableView<Attack> attackTable;
    @FXML
    private TableView<Ability> abilityTable;
    @FXML
    private TableView<AttackCategory> atkCategoryTable;
    @FXML
    private ImageView cover;
    @FXML
    private Pane mainPane;
    @FXML
    private Button button;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cover.fitWidthProperty().bind(mainPane.widthProperty());
        cover.fitHeightProperty().bind(mainPane.heightProperty());
        PokemonTab tab1 = new PokemonTab(pokemonTable);
        TypeTab tTab = new TypeTab(typeTable);
        AttackTab aTab = new AttackTab(attackTable);
        AbilityTab abTab = new AbilityTab(abilityTable);
        button.setOnAction((ActionEvent event) -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Información");
            alert.setContentText("Esta base de datos es de consulta "
                    + "únicamente. No se pueden agregar o modificar "
                    + "datos, ya que perdería su propósito.\nMás adelante "
                    + "se implementarán nuevas funciones :)");
            alert.getDialogPane().setPrefSize(480, 250);
            alert.showAndWait()
                    .filter(response -> response == ButtonType.OK);
        });
    }
}
