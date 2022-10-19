package comp1110.ass2.gui;

import comp1110.ass2.Player;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EndWindow {
    public static void display(Player player){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        Text text = new Text();
        int total = 0;
        for(int point:player.getPoint()){
            total+=point;
        }
        text.setText("Your total point is "+total+" !");
        text.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 50));


        VBox vBox = new VBox();
        vBox.getChildren().addAll(text);
        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox,1000,500);
        stage.setScene(scene);
        stage.setTitle("Congratulation!");
        stage.showAndWait();
    }
}
