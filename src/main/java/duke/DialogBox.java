package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

import java.io.IOException;


public class DialogBox extends HBox {

    private Label dialog;
    private ImageView displayPicture;

    public DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog = new Label(text);
        displayPicture = new ImageView(img);

        dialog.setWrapText(true);
        dialog.setFont(new Font(10));
        dialog.setMinHeight(Region.USE_PREF_SIZE);

        this.displayPicture.setFitWidth(90);
        this.displayPicture.setFitHeight(90);

        this.setSpacing(15);
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(dialog, displayPicture);
        this.setBackground(new Background(new BackgroundFill(Color.BEIGE, new CornerRadii(1), new Insets(5, 0, 0, 0))));
    }
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
