package comp1110.ass2.gui;

import comp1110.ass2.CatanDice;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.Objects;

public class Viewer extends Application {

    private static final int VIEWER_WIDTH = 1200;
    private static final int VIEWER_HEIGHT = 700;

    private final Group root = new Group();
    private final Group controls = new Group();
    private final Group boardStateGroup = new Group();
    private TextField playerTextField;
    private TextField boardTextField;
//    private final Text boardStateErrorText = new Text("Board State incorrect!");
//    private final Text buildConstraintsText = new Text("Broke Build Constraints!");


    /**
     * Show the state of a (single player's) board in the window.
     *
     * @param board_state: The string representation of the board state.
     */
    void displayState(String board_state) {
        // FIXME Task 5: implement the state viewer
        //导入图片并使其居中 import and center the image
        importBoardImage(boardStateGroup);
//        root.getChildren().add(boardStateGroup);

        Text boardStateErrorText = new Text("Board State incorrect!");
//        Text buildConstraintsText = new Text("Broke Build Constraints!");
        if (board_state.equals("")) {
            System.out.println("No board state has been inputted.");
        } else if (!(CatanDice.isBoardStateWellFormed(board_state))) {
            makeText(boardStateErrorText, boardStateGroup);
        } else {
            String[] boardStateSplit = board_state.split(",");
            for (String move : boardStateSplit) {
//                if (!(CatanDice.checkBuildConstraints(move, board_state))) {
//                    makeText(buildConstraintsText, boardStateGroup);
//                } else {
                String substr = move.substring(1);
                if (move.charAt(0) == 'R') {
//                    System.out.println(substr);
                    makeRoad(substr, boardStateGroup);
                } else if (move.charAt(0) == 'S') {
                    makeSettlement(substr, boardStateGroup);
                } else if (move.charAt(0) == 'C') {
                    makeCity(substr, boardStateGroup);
                } else if (move.charAt(0) == 'J') {
                    makeJokerOrKnight(substr, boardStateGroup, Color.CORAL);
                } else if (move.charAt(0) == 'K') {
                    makeJokerOrKnight(substr, boardStateGroup, Color.BLACK);
                }
//                }
            }
        }

//        boardStateGroup.setLayoutX(-200);
        if (!(root.getChildren().contains(boardStateGroup)))
            root.getChildren().add(boardStateGroup);
    }

    private void importBoardImage(Group node) {
        //导入图片并使其居中 import and center the image.
//        Image boardImage = new Image(Viewer.class.getResource("island-one-with-numbering.png").toString());
        Image boardImage = new Image(Objects.requireNonNull(Viewer.class.getResource("island-one-with-numbering.png")).toString());
        ImageView boardImg = new ImageView(boardImage);
        double boardSide = 600.0;
        boardImg.setFitHeight(boardSide);
        boardImg.setFitWidth(boardSide);
//        (1200-600)/2=300
        boardImg.setLayoutX((VIEWER_WIDTH - boardSide) / 2.0);
//        (700-600)/2=50
        boardImg.setLayoutY((VIEWER_HEIGHT - boardSide) / 2.0);
        node.getChildren().add(boardImg);
    }

    private void makeText(Text text, Group node) {
        DropShadow ds = new DropShadow();
        ds.setOffsetY(4.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
        text.setFill(Color.YELLOW);
        text.setEffect(ds);
        text.setCache(true);
        text.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 50));
        text.setLayoutX(350);
//        700/2=350
        text.setLayoutY(VIEWER_HEIGHT / 2.0);
        text.setTextAlignment(TextAlignment.CENTER);
        text.toFront();
        node.getChildren().remove(text);
        node.getChildren().add(text);
//        this.boardStateErrorText.setOpacity(0.5); //设置透明度（<1）
    }

    /**
     * @param indexString : "0"-"15"
     */
    private void makeRoad(String indexString, Group node) {
        switch (indexString) {
            case "0" -> {
                Rectangle road0 = new Rectangle(20, 60, Color.BLACK);
                road0.setX(490); //LayoutX
                road0.setY(274); //LayoutY
                road0.setRotate(30);
                node.getChildren().add(road0);
            }
            case "1" -> {
                Rectangle road1 = new Rectangle(20, 60, Color.BLACK);
                road1.setX(413);
                road1.setY(315);
                road1.setRotate(90);
                node.getChildren().add(road1);
            }
            case "2" -> {
                Rectangle road2 = new Rectangle(20, 60, Color.BLACK);
                road2.setX(490);
                road2.setY(356);
                road2.setRotate(-30);
                node.getChildren().add(road2);
            }
            case "3" -> {
                Rectangle road3 = new Rectangle(20, 60, Color.BLACK);
                road3.setX(490);
                road3.setY(469);
                road3.setRotate(30);
                node.getChildren().add(road3);
            }
            case "4" -> {
                Rectangle road4 = new Rectangle(20, 60, Color.BLACK);
                road4.setX(413);
                road4.setY(505);
                road4.setRotate(90);
                node.getChildren().add(road4);
            }
            case "5" -> {
                Rectangle road5 = new Rectangle(20, 60, Color.BLACK);
                road5.setX(491);
                road5.setY(546);
                road5.setRotate(-30);
                node.getChildren().add(road5);
            }
            case "6" -> {
                Rectangle road6 = new Rectangle(20, 60, Color.BLACK);
                road6.setX(590);
                road6.setY(600);
                road6.setRotate(90);
                node.getChildren().add(road6);
            }
            case "7" -> {
                Rectangle road7 = new Rectangle(20, 60, Color.BLACK);
                road7.setX(665);
                road7.setY(560);
                road7.setRotate(30);
                node.getChildren().add(road7);
            }
            case "8" -> {
                Rectangle road8 = new Rectangle(20, 60, Color.BLACK);
                road8.setX(666);
                road8.setY(451);
                road8.setRotate(-30);
                node.getChildren().add(road8);
            }
            case "9" -> {
                Rectangle road9 = new Rectangle(20, 60, Color.BLACK);
                road9.setX(665);
                road9.setY(367);
                road9.setRotate(30);
                node.getChildren().add(road9);
            }
            case "10" -> {
                Rectangle road10 = new Rectangle(20, 60, Color.BLACK);
                road10.setX(665);
                road10.setY(254);
                road10.setRotate(-30);
                node.getChildren().add(road10);
            }
            case "11" -> {
                Rectangle road11 = new Rectangle(20, 60, Color.BLACK);
                road11.setX(667);
                road11.setY(169);
                road11.setRotate(30);
                node.getChildren().add(road11);
            }
            case "12" -> {
                Rectangle road12 = new Rectangle(20, 60, Color.BLACK);
                road12.setX(764);
                road12.setY(500);
                road12.setRotate(90);
                node.getChildren().add(road12);
            }
            case "13" -> {
                Rectangle road13 = new Rectangle(20, 60, Color.BLACK);
                road13.setX(832);
                road13.setY(463);
                road13.setRotate(30);
                node.getChildren().add(road13);
            }
            case "14" -> {
                Rectangle road14 = new Rectangle(20, 60, Color.BLACK);
                road14.setX(835);
                road14.setY(354);
                road14.setRotate(-30);
                node.getChildren().add(road14);
            }
            case "15" -> {
                Rectangle road15 = new Rectangle(20, 60, Color.BLACK);
                road15.setX(837);
                road15.setY(269);
                road15.setRotate(30);
                node.getChildren().add(road15);
            }
        }
    }

    private void makeSettlement(String indexString, Group node) {
        switch (indexString) {
            case "3" -> {
                Rectangle s3 = new Rectangle(25, 25, Color.BLACK);
                s3.setX(520);
                s3.setY(235);
                s3.setOpacity(0.6); //透明度
                node.getChildren().add(s3);
            }
            case "4" -> {
                Rectangle s4 = new Rectangle(25, 25, Color.BLACK);
                s4.setX(520);
                s4.setY(438);
                s4.setOpacity(0.6); //透明度
                node.getChildren().add(s4);
            }
            case "5" -> {
                Rectangle s5 = new Rectangle(25, 25, Color.BLACK);
                s5.setX(528);
                s5.setY(620);
                s5.setOpacity(0.6); //透明度
                node.getChildren().add(s5);
            }
            case "7" -> {
                Rectangle s7 = new Rectangle(25, 25, Color.BLACK);
                s7.setX(700);
                s7.setY(527);
                s7.setOpacity(0.6); //透明度
                node.getChildren().add(s7);
            }
            case "9" -> {
                Rectangle s9 = new Rectangle(25, 25, Color.BLACK);
                s9.setX(690);
                s9.setY(337);
                s9.setOpacity(0.6); //透明度
                node.getChildren().add(s9);
            }
            case "11" -> {
                Rectangle s11 = new Rectangle(25, 25, Color.BLACK);
                s11.setX(698);
                s11.setY(139);
                s11.setOpacity(0.6); //透明度
                node.getChildren().add(s11);
            }
        }
    }

    private void makeCity(String indexString, Group node) {
        switch (indexString) {
            case "7" -> {
                Rectangle c7 = new Rectangle(44, 20, Color.BLACK);
                c7.setX(330);
                c7.setY(337);
                c7.setOpacity(0.6);
                node.getChildren().add(c7);
            }
            case "12" -> {
                Rectangle c12 = new Rectangle(44, 20, Color.BLACK);
                c12.setX(330);
                c12.setY(530);
                c12.setOpacity(0.6);
                node.getChildren().add(c12);
            }
            case "20" -> {
                Rectangle c20 = new Rectangle(44, 20, Color.BLACK);
                c20.setX(849);
                c20.setY(432);
                c20.setOpacity(0.6);
                node.getChildren().add(c20);
            }
            case "30" -> {
                Rectangle c30 = new Rectangle(44, 20, Color.BLACK);
                c30.setX(849);
                c30.setY(239);
                c30.setOpacity(0.6);
                node.getChildren().add(c30);
            }
        }
    }

    private void makeJokerOrKnight(String indexString, Group node, Color color) {
        switch (indexString) {
            case "1" -> {
                Circle j1 = new Circle(421, 180, 12); //X,Y,Radius
                j1.setFill(color);
                node.getChildren().add(j1);
            }
            case "2" -> {
                Circle j2 = new Circle(422, 373, 12);
                j2.setFill(color);
                node.getChildren().add(j2);
            }
            case "3" -> {
                Circle j3 = new Circle(594, 468, 12);
                j3.setFill(color);
                node.getChildren().add(j3);
            }
            case "4" -> {
                Circle j4 = new Circle(767, 374, 12);
                j4.setFill(color);
                node.getChildren().add(j4);
            }
            case "5" -> {
                Circle j5 = new Circle(766, 181, 12);
                j5.setFill(color);
                node.getChildren().add(j5);
            }
            case "6" -> {
                Circle j6 = new Circle(594, 82, 12);
                j6.setFill(color);
                node.getChildren().add(j6);
            }
        }
    }

//    private void makeKnight(String indexString) {
//        switch (indexString) {
//            case "1" -> {
//                Circle k1 = new Circle(421, 180 + 27, 15); //12+15=27
//                k1.setFill(Color.BLACK);
//                k1.setOpacity(0.6);
//                root.getChildren().add(k1);
//            }
//            case "2" -> {
//                Circle k2 = new Circle(422, 373 + 27, 15);
//                k2.setFill(Color.BLACK);
//                k2.setOpacity(0.6);
//                root.getChildren().add(k2);
//            }
//            case "3" -> {
//                Circle k3 = new Circle(594, 468 + 27, 15);
//                k3.setFill(Color.BLACK);
//                k3.setOpacity(0.6);
//                root.getChildren().add(k3);
//            }
//            case "4" -> {
//                Circle k4 = new Circle(767, 374 + 27, 15);
//                k4.setFill(Color.BLACK);
//                k4.setOpacity(0.6);
//                root.getChildren().add(k4);
//            }
//            case "5" -> {
//                Circle k5 = new Circle(766, 181 + 27, 15);
//                k5.setFill(Color.BLACK);
//                k5.setOpacity(0.6);
//                root.getChildren().add(k5);
//            }
//            case "6" -> {
//                Circle k6 = new Circle(594, 82 + 27, 15);
//                k6.setFill(Color.BLACK);
//                k6.setOpacity(0.6);
//                root.getChildren().add(k6);
//            }
//        }
//    }

    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        Label boardLabel = new Label("Board State:");
        boardTextField = new TextField();
        boardTextField.setPrefWidth(500);
        Button button = new Button("Show");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                displayState(boardTextField.getText());
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(boardLabel, boardTextField, button);
        hb.setSpacing(10);
        controls.getChildren().add(hb);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Board State Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        root.getChildren().add(controls);

        makeControls();

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
