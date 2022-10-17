package comp1110.ass2.Main;

import comp1110.ass2.gui.ChangeResources;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Button btn = new Button("change resources");
        btn.setOnMouseClicked(event -> {
            System.out.println(ChangeResources.display("Please change your resources", "Please choose your operation"));
        });
        VBox vBox = new VBox();
        vBox.getChildren().add(btn);
        //设置居中显示
        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox, 400, 400);
        primaryStage.setTitle("弹出窗口示例");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

