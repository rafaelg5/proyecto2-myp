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

import java.net.URL;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AttackCategory {

    private final String TABLE = "AtkCategory";

    private SimpleIntegerProperty AtkCategoryID;
    private SimpleStringProperty CategoryName;
    private SimpleObjectProperty<ImageView> CategorySprite;

    public AttackCategory() {
        this(1, "", "");
    }

    public AttackCategory(int id, String name, String sprite) {

        AtkCategoryID = new SimpleIntegerProperty(id);
        CategoryName = new SimpleStringProperty(name);

        Image img;
        ImageView i;

        if (sprite.trim().equals("")) {
            i = new ImageView();
        } else {
            URL url = getClass()
                    .getResource("/pokemondb/graphicinterface/img/" + sprite);
            img = new Image(url.toString());
            i = new ImageView(img);
        }
        
        CategorySprite = new SimpleObjectProperty<>(i);
    }

    /**
     * @return the AtkCategoryID
     */
    public int getAtkCategoryID() {
        return AtkCategoryID.get();
    }

    /**
     * @return the AtkCategoryID property
     */
    public SimpleIntegerProperty atkCategoryIDProperty() {
        return AtkCategoryID;
    }

    /**
     * @param id the id to set
     */
    public void setAtkCategoryID(int id) {
        AtkCategoryID.set(id);
    }

    /**
     * @return the CategoryName
     */
    public String getCategoryName() {
        return CategoryName.get();
    }

    /**
     * @return the CategoryName property
     */
    public SimpleStringProperty categoryNameProperty() {
        return CategoryName;
    }

    /**
     * @param name the name to set
     */
    public void setCategoryName(String name) {
        CategoryName.set(name);
    }

    /**
     * @return the CategorySprite
     */
    public ImageView getCategorySprite() {
        return CategorySprite.get();
    }

    /**
     * @return the CategorySprite property
     */
    public SimpleObjectProperty<ImageView> categorySpriteProperty() {
        return CategorySprite;
    }

    /**
     * @param sprite the sprite to set
     */
    public void setCategorySprite(String sprite) {
        Image img;
        if (sprite.trim().equals("")) {
            img = null;
        } else {

            img = new Image(getClass()
                    .getResource("/pokemondb/graphicinterface/img/" + sprite).
                    toString());
            System.out.println(img.getHeight());
        }
        CategorySprite.set(new ImageView(img));
    }
}
