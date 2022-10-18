package comp1110.ass2.gui;

import comp1110.ass2.Player;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static comp1110.ass2.CatanDice.diceState;

public class Game extends Application {

    private final Group root = new Group();
    private static final int WINDOW_WIDTH = 1200;
    private static final int WINDOW_HEIGHT = 700;


    public void importImage(Group node, Image image, double height, double width) {
        //导入图片并使其居中 import and center the image.
        ImageView Img = new ImageView(image);
        Img.setFitHeight(height);
        Img.setFitWidth(width);
//        (1200-600)/2=300
        Img.setLayoutX((WINDOW_WIDTH - width) / 2.0);
//        (700-600)/2=50
        Img.setLayoutY((WINDOW_HEIGHT - height) / 2.0);
        node.getChildren().add(Img);
    }

    void displayBoard() {
        Group boardGroup = new Group();

        Image boardImage = new Image(Objects.requireNonNull(Viewer.class.getResource("island-one-with-numbering.png")).toString());
        importImage(boardGroup, boardImage, 600.0, 600.0);

        //put on buttons for every structure in the board
        makeRoadButton(boardGroup);

        boardGroup.setLayoutX(-300);        //the distance from board image to the right border is 300
        if (!(root.getChildren().contains(boardGroup)))
            root.getChildren().add(boardGroup);

    }

    public void makeRoadButton(Group node) {
//        Rectangle road0 = new Rectangle(20, 60, Color.BLACK);
        List<Button> roadButton = new ArrayList<>();
        for (int i = 0; i <= 15; i++) {
            roadButton.add(new Button("1"));
            Button bRoad = roadButton.get(i);
            bRoad.setPrefHeight(20);
            bRoad.setPrefWidth(60);
            bRoad.setOnAction(actionEvent -> {
                bRoad.setStyle("-fx-background-color:#696969;" +   //设置背景颜色
                        "-fx-text-fill:#FFF;");                 //设置字体颜色
            });
        }
        Button bR0 = roadButton.get(0);
        bR0.setLayoutX(470);
        bR0.setLayoutY(294);
        bR0.setRotate(-60);
        Button bR1 = roadButton.get(1);
        bR1.setLayoutX(393);
        bR1.setLayoutY(330);
        Button bR2 = roadButton.get(2);
        bR2.setLayoutX(470);
        bR2.setLayoutY(376);
        bR2.setRotate(60);
        Button bR3 = roadButton.get(3);
        bR3.setLayoutX(470);
        bR3.setLayoutY(489);
        bR3.setRotate(-60);
        Button bR4 = roadButton.get(4);
        bR4.setLayoutX(393);
        bR4.setLayoutY(522);
        Button bR5 = roadButton.get(5);
        bR5.setLayoutX(471);
        bR5.setLayoutY(563);
        bR5.setRotate(60);
        Button bR6 = roadButton.get(6);
        bR6.setLayoutX(570);
        bR6.setLayoutY(617);
        Button bR7 = roadButton.get(7);
        bR7.setLayoutX(645);
        bR7.setLayoutY(577);
        bR7.setRotate(-60);
        Button bR8 = roadButton.get(8);
        bR8.setLayoutX(645);
        bR8.setLayoutY(468);
        bR8.setRotate(60);
        Button bR9 = roadButton.get(9);
        bR9.setLayoutX(645);
        bR9.setLayoutY(386);
        bR9.setRotate(-60);
        Button bR10 = roadButton.get(10);
        bR10.setLayoutX(645);
        bR10.setLayoutY(273);
        bR10.setRotate(60);
        Button bR11 = roadButton.get(11);
        bR11.setLayoutX(647);
        bR11.setLayoutY(189);
        bR11.setRotate(-60);
        Button bR12 = roadButton.get(12);
        bR12.setLayoutX(744);
        bR12.setLayoutY(520);
        Button bR13 = roadButton.get(13);
        bR13.setLayoutX(812);
        bR13.setLayoutY(482);
        bR13.setRotate(-60);
        Button bR14 = roadButton.get(14);
        bR14.setLayoutX(814);
        bR14.setLayoutY(373);
        bR14.setRotate(60);
        Button bR15 = roadButton.get(15);
        bR15.setLayoutX(816);
        bR15.setLayoutY(288);
        bR15.setRotate(-60);

        node.getChildren().addAll(roadButton);
    }

    void displayScore() {
        Group scoreGroup = new Group();
        Image scoreImage = new Image(Objects.requireNonNull(Viewer.class.getResource("island-one-score.png")).toString());
        importImage(scoreGroup, scoreImage, 300.0, 600.0);
        scoreGroup.setLayoutX(300);        //the distance from score image to the right border is 300
        scoreGroup.setLayoutY(-200);           //the distance from score image to the upper border is 200
        if (!(root.getChildren().contains(scoreGroup)))
            root.getChildren().add(scoreGroup);

    }

    public void displayResource(Player player) {
        Group resourceGroup = new Group();

        Button bSwapTrade = new Button("Would like Swap or Trade?");
        bSwapTrade.setOnMouseClicked(event -> {
            ChangeResources.display("Please change your resources", "Please choose your operation", player);
        });
        resourceGroup.getChildren().add(bSwapTrade);
        resourceGroup.setLayoutX(WINDOW_WIDTH / 2.0 + 10);
        resourceGroup.setLayoutY(WINDOW_HEIGHT / 2.0 + 200);
        if (!(root.getChildren().contains(resourceGroup)))
            root.getChildren().add(resourceGroup);

    }

    public Button makeDicesButton(Double X, Double Y) {
        Group dice = new Group();
        Button d = new Button(" ");
        dice.getChildren().add(d);
        dice.setLayoutX(X);
        dice.setLayoutY(Y);
        d.setOnAction(event -> {
            d.setDisable(true);
        });
        if (!(root.getChildren().contains(dice)))
            root.getChildren().add(dice);
        return d;
    }

    public String makeDiceResource(int resourceID) {
        String s = null;
        switch (resourceID) {
            case 0 -> s = "Ore";
            case 1 -> s = "Grain";
            case 2 -> s = "Wool";
            case 3 -> s = "Timber";
            case 4 -> s = "Brick";
            case 5 -> s = "Gold";
        }
        return s;
    }

    public void displayDices(Player player) {
        Group DiceGroup = new Group();
        Button roll1 = new Button("Roll dices");
        DiceGroup.getChildren().add(roll1);
        DiceGroup.setLayoutX(WINDOW_WIDTH / 2.0 + 50);
        DiceGroup.setLayoutY(WINDOW_HEIGHT / 2.0 - 20);
        int[] res_s = new int[6];
        Button[] resourcesButtons = new Button[6];
        for (int i = 0; i <= 5; i++) {
            resourcesButtons[i] = makeDicesButton(WINDOW_WIDTH / 2.0 + 200 + 50 * i, WINDOW_HEIGHT / 2.0 - 20);
            resourcesButtons[i].setDisable(true);
        }
        if (!(root.getChildren().contains(DiceGroup)))
            root.getChildren().add(DiceGroup);

        Group DiceGroup2 = new Group();
        Button roll2 = new Button("Roll dices");
        DiceGroup2.getChildren().add(roll2);
        DiceGroup2.setLayoutX(WINDOW_WIDTH / 2.0 + 50);
        DiceGroup2.setLayoutY(WINDOW_HEIGHT / 2.0 + 30);
        if (!(root.getChildren().contains(DiceGroup2)))
            root.getChildren().add(DiceGroup2);
        roll2.setDisable(true);

        Group DiceGroup3 = new Group();
        Button roll3 = new Button("Roll dices");
        DiceGroup3.getChildren().add(roll3);
        DiceGroup3.setLayoutX(WINDOW_WIDTH / 2.0 + 50);
        DiceGroup3.setLayoutY(WINDOW_HEIGHT / 2.0 + 80);
        if (!(root.getChildren().contains(DiceGroup3)))
            root.getChildren().add(DiceGroup3);
        roll3.setDisable(true);

        Text[] t = new Text[6];
        for (int i = 0; i <= 5; i++) {
            Group resource_type = new Group();
            t[i] = new Text();
            resource_type.getChildren().add(t[i]);
            resource_type.setLayoutX(WINDOW_WIDTH / 2.0 + 200 + 50 * i);
            resource_type.setLayoutY(WINDOW_HEIGHT / 2.0 + 30);
            if (!(root.getChildren().contains(resource_type)))
                root.getChildren().add(resource_type);
        }


        roll1.setOnAction(event -> {

            for (int i = 0; i <= 5; i++) {
                t[i].setText(makeDiceResource(diceState(6)[i]));
            }
            roll1.setDisable(true);
            for (Button b : resourcesButtons) {
                b.setDisable(false);
            }
            roll2.setDisable(false);
        });


        roll2.setOnAction(event -> {
            for (int i = 0; i <= 5; i++) {
                if (resourcesButtons[i].isDisabled()) {
                    t[i].setText(makeDiceResource(diceState(6)[i]));
                }
            }
            roll2.setDisable(true);
            roll3.setDisable(false);
            for (Button b : resourcesButtons) {
                b.setDisable(false);
            }
        });

        roll3.setOnAction(event -> {
            for (int i = 0; i <= 5; i++) {
                if (resourcesButtons[i].isDisabled()) {
                    t[i].setText(makeDiceResource(diceState(6)[i]));
                }
            }
            roll3.setDisable(true);
            for (Button b : resourcesButtons) {
                b.setDisable(true);
            }
        });

        Group Ready = new Group();
        Button ready = new Button("Ready");
        Ready.getChildren().add(ready);
        Ready.setLayoutX(WINDOW_WIDTH / 2.0 + 500);
        Ready.setLayoutY(WINDOW_HEIGHT / 2.0 + 30);
        if (!(root.getChildren().contains(Ready)))
            root.getChildren().add(Ready);

        Group reset = new Group();
        Button r = new Button("Reset");
        reset.getChildren().add(r);
        reset.setLayoutX(WINDOW_WIDTH / 2.0 + 500);
        reset.setLayoutY(WINDOW_HEIGHT / 2.0 - 20);
        if (!(root.getChildren().contains(reset)))
            root.getChildren().add(reset);

        ready.setOnAction(event -> {
            roll1.setDisable(true);
            roll2.setDisable(true);
            roll3.setDisable(true);
            r.setDisable(true);
            for (Button b : resourcesButtons) {
                b.setDisable(true);
            }
            ready.setDisable(true);
            int[] states = new int[6];
            for (int i = 0; i <= 5; i++) {
                states[i] = Integer.parseInt(ChangeResources.resourceToNum(t[i].getText()));
            }
            for (int i = 0; i <= 5; i++) {
                res_s[states[i]]++;
            }
            for (int i = 0; i <= 5; i++) {
                System.out.print(res_s[i]);
            }
            player.setResource_state(res_s);

        });


        r.setOnAction(event -> {
            for (Button b : resourcesButtons) {
                b.setDisable(false);
            }
        });
    }

    public void show(Player player) {
        Group showButton = new Group();
        Button sb = new Button("Show resource");
        showButton.getChildren().add(sb);
        showButton.setLayoutX(WINDOW_WIDTH / 2.0 + 200);
        showButton.setLayoutY(WINDOW_HEIGHT / 2.0 + 40);
        if (!(root.getChildren().contains(showButton)))
            root.getChildren().add(showButton);

        Group showResources = new Group();
        Text show = new Text();
        showResources.getChildren().add(show);
        showResources.setLayoutX(WINDOW_WIDTH / 2.0 + 400);
        showResources.setLayoutY(WINDOW_HEIGHT / 2.0 + 50);
        if (!(root.getChildren().contains(showResources)))
            root.getChildren().add(showResources);
        sb.setOnAction(event -> {
            String resource_state = ChangeResources.resourceStateToString(player.resource_state);
            show.setText(resource_state);
        });
    }

    public void endTurn(Player player) {
        Group endturn = new Group();
        Button end = new Button("End this turn and calculate score");
        endturn.getChildren().add(end);
        endturn.setLayoutX(WINDOW_WIDTH / 2.0 + 350);
        endturn.setLayoutY(WINDOW_HEIGHT / 2.0 + 280);
        if (!(root.getChildren().contains(endturn)))
            root.getChildren().add(endturn);

        Group showturn = new Group();
        Text turn = new Text();
        turn.setText("Turn 1");
        showturn.getChildren().add(turn);
        showturn.setLayoutX(WINDOW_WIDTH / 2.0 + 430);
        showturn.setLayoutY(WINDOW_HEIGHT / 2.0 + 260);
        if (!(root.getChildren().contains(showturn)))
            root.getChildren().add(showturn);

        end.setOnAction(event -> {
            player.setResource_state(new int[]{0, 0, 0, 0, 0, 0});
            player.setTurn(player.getTurn() + 1);
            turn.setText("Turn " + player.getTurn());

        });


    }


    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Catan Dice");
        Scene scene = new Scene(this.root, WINDOW_WIDTH, WINDOW_HEIGHT);
        Player player = new Player();

        player.resource_state = new int[]{0, 0, 0, 0, 0, 0};
        player.board_state = "";
        player.turn = 1;

        displayBoard();
        displayScore();
        displayDices(player);
        displayResource(player);
        show(player);
        endTurn(player);


        stage.setScene(scene);
        stage.show();
    }


}
