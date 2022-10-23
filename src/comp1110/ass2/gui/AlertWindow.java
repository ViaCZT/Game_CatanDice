package comp1110.ass2.gui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Objects;


/**
 * pop up an alert window when the player breaks resource or build constrain.
 *
 * @author Zetian Chen
 */
public class AlertWindow {
    public static void display(String title, String msg) {
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);  //阻止此JavaFX应用程序打开的所有其他窗口（Stage）

        Image alertImg = new Image(Objects.requireNonNull(AlertWindow.class.getResourceAsStream("alert.png")));
        Label msgLabel = new Label(msg, new ImageView(alertImg));
        msgLabel.setFont(new Font("Calibri", 22));
        msgLabel.setTextFill(Color.RED);
        msgLabel.setWrapText(true);  //启用文本折叠换行
//        msgLabel.setGraphicTextGap(10);  //图片和文字之间的距离=10

        StackPane root = new StackPane(msgLabel);
        Scene scene = new Scene(root, 600, 250);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
