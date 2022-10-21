package comp1110.ass2.gui;

import comp1110.ass2.Board;
import comp1110.ass2.CatanDice;
import comp1110.ass2.Player;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static comp1110.ass2.CatanDice.diceState;

public class Game extends Application {

    private final Group root = new Group();
    private static final int WINDOW_WIDTH = 1200;
    private static final int WINDOW_HEIGHT = 700;

    /**
     * import the image to the window.
     *
     * @param node: The group contains the image.
     * @param image : The image needed to import.
     * @param height :Image height.
     * @param width :Image width.
     * @auther Zetian Chen (uid: u7564812)
     */
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

    /**
     * make the resource labels on the window.
     *
     * @param node: The group contains the image.
     * @auther Zetian Chen (uid: u7564812)
     */
    public void makeResourceLabel(Group node) {
        Label label1 = new Label("Ore");
        label1.setLayoutX(411);
        label1.setLayoutY(216);
        node.getChildren().add(label1);
        Label label2 = new Label("Grain");
        label2.setLayoutX(410);
        label2.setLayoutY(435);
        node.getChildren().add(label2);
        Label label3 = new Label("Wool");
        label3.setLayoutX(578);
        label3.setLayoutY(510);
        node.getChildren().add(label3);
        Label label4 = new Label("Timber");
        label4.setLayoutX(752);
        label4.setLayoutY(415);
        node.getChildren().add(label4);
        Label label5 = new Label("Brick");
        label5.setLayoutX(754);
        label5.setLayoutY(219);
        node.getChildren().add(label5);
    }

    Group boardGroup = new Group();
    /**
     * display the board of the game.
     * also display the buttons on it.
     *
     * @param player: The game player
     * @auther Zetian Chen (uid: u7564812)
     */
    void displayBoard(Player player) {
        Image boardImage = new Image(Objects.requireNonNull(Viewer.class.getResource("island-one-with-numbering.png")).toString());
        importImage(boardGroup, boardImage, 600.0, 600.0);

        makeResourceLabel(boardGroup);
        //put on buttons for every structure in the board
        makeRoadButton(boardGroup, player);
        makeSettleButton(boardGroup, player);
        makeCityButton(boardGroup, player);
        makeJokerButton(boardGroup, player);

        boardGroup.setLayoutX(-300);        //the distance from board image to the right border is 300
        if (!(root.getChildren().contains(boardGroup)))
            root.getChildren().add(boardGroup);

    }

    /**
     * make jokers' buttons
     *
     * @param node :The group contains the buttons.
     * @param player: The game player
     * @auther Zetian Chen (uid: u7564812)
     */
    public void makeJokerButton(Group node, Player player) {
        List<Button> jokerButton = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            jokerButton.add(new Button("Joker"));
            Button bJoker = jokerButton.get(i);

            String joker = "J" + (i + 1);
            bJoker.setOnAction(actionEvent -> {
                int[] resourceState = player.getResource_state();
                String boardState = player.getBoard_state();
                if (!(CatanDice.checkResources("J", resourceState))) {
                    AlertWindow.display("Resource Constrain", "Not enough resources to build a joker!");
                } else if (!(CatanDice.checkBuildConstraints(joker, boardState))) {
                    AlertWindow.display("Build Constrain", "Cannot build this joker!");
                } else {
                    bJoker.setStyle("-fx-background-color:#0000CD;" +   //设置背景颜色
                            "-fx-text-fill:#FFF;");                 //设置字体颜色
                    if (boardState.equals("")) {
                        player.setBoard_state(joker);
                    } else {
                        player.setBoard_state(boardState + "," + joker);
                    }
                    System.out.println("\njokerButton boardState: " + player.getBoard_state());
                    resourceState[0] -= 1;
                    resourceState[1] -= 1;
                    resourceState[2] -= 1;
                    player.setResource_state(resourceState);
                    bJoker.setDisable(true);
                    // show resource
                    showResource(player);
                }
            });
        }
        Button bJ0 = jokerButton.get(0);
        bJ0.setLayoutX(360);
        bJ0.setLayoutY(165);
        Button bJ1 = jokerButton.get(1);
        bJ1.setLayoutX(360);
        bJ1.setLayoutY(363);
        Button bJ2 = jokerButton.get(2);
        bJ2.setLayoutX(574);
        bJ2.setLayoutY(450);
        Button bJ3 = jokerButton.get(3);
        bJ3.setLayoutX(747);
        bJ3.setLayoutY(354);
        Button bJ4 = jokerButton.get(4);
        bJ4.setLayoutX(786);
        bJ4.setLayoutY(171);
        Button bJ5 = jokerButton.get(5);
        bJ5.setLayoutX(614);
        bJ5.setLayoutY(62);
        node.getChildren().addAll(jokerButton);
    }

    /**
     * make cities' buttons
     *
     * @param node :The group contains the buttons.
     * @param player: The game player
     * @auther Zetian Chen (uid: u7564812)
     */
    public void makeCityButton(Group node, Player player) {
        List<Button> cityButton = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            cityButton.add(new Button());
            Button bCity = cityButton.get(i);
            bCity.setPrefHeight(20);
            bCity.setPrefWidth(44);
            String city = "C";
            switch (i) {
                case 0 -> {
                    city += "7";
                    bCity.setText("7");
                }
                case 1 -> {
                    city += "12";
                    bCity.setText("12");
                }
                case 2 -> {
                    city += "20";
                    bCity.setText("20");
                }
                case 3 -> {
                    city += "30";
                    bCity.setText("30");
                }
            }
            String finalCity = city;
            bCity.setOnAction(actionEvent -> {
                int[] resourceState = player.getResource_state();
                String boardState = player.getBoard_state();
                if (!(CatanDice.checkResources("C", resourceState))) {
                    AlertWindow.display("Resource Constrain", "Not enough resources to build a city!");
                } else if (!(CatanDice.checkBuildConstraints(finalCity, boardState))) {
                    AlertWindow.display("Build Constrain", "Cannot build this city!");
                } else {
                    bCity.setStyle("-fx-background-color:#006400;" +   //设置背景颜色
                            "-fx-text-fill:#000000;");                 //设置字体颜色
                    if (boardState.equals("")) {
                        player.setBoard_state(finalCity);
                    } else {
                        player.setBoard_state(boardState + "," + finalCity);
                    }
                    System.out.println("\ncityButton boardState: " + player.getBoard_state());
                    resourceState[0] -= 3;
                    resourceState[1] -= 2;
                    player.setResource_state(resourceState);
                    bCity.setDisable(true);
                    // show resource
                    showResource(player);
                }
            });
        }
        Button bC7 = cityButton.get(0);
        bC7.setLayoutX(330);
        bC7.setLayoutY(332);
        Button bC12 = cityButton.get(1);
        bC12.setLayoutX(330);
        bC12.setLayoutY(524);
        Button bC20 = cityButton.get(2);
        bC20.setLayoutX(849);
        bC20.setLayoutY(432);
        Button bC30 = cityButton.get(3);
        bC30.setLayoutX(849);
        bC30.setLayoutY(239);
        node.getChildren().addAll(cityButton);
    }

    /**
     * make settlements' buttons
     *
     * @param node :The group contains the buttons.
     * @param player: The game player
     * @auther Zetian Chen (uid: u7564812)
     */
    public void makeSettleButton(Group node, Player player) {
        List<Button> settleButton = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            settleButton.add(new Button());
            Button bSettle = settleButton.get(i);

            String settlement = "S";
            switch (i) {
                case 0 -> {
                    settlement += "3";
                    bSettle.setText("3");
                }
                case 1 -> {
                    settlement += "4";
                    bSettle.setText("4");
                }
                case 2 -> {
                    settlement += "5";
                    bSettle.setText("5");
                }
                case 3 -> {
                    settlement += "7";
                    bSettle.setText("7");
                }
                case 4 -> {
                    settlement += "9";
                    bSettle.setText("9");
                }
                case 5 -> {
                    settlement += "11";
                    bSettle.setText("11");
                }
            }
            String finalSettlement = settlement;
            bSettle.setOnAction(actionEvent -> {
                int[] resourceState = player.getResource_state();
                String boardState = player.getBoard_state();
                if (!(CatanDice.checkResources("S", resourceState))) {
                    AlertWindow.display("Resource Constrain", "Not enough resources to build a settlement!");
                } else if (!(CatanDice.checkBuildConstraints(finalSettlement, boardState))) {
                    System.out.print("\nsettlement Build Constrain: " + boardState + "\n");
                    System.out.println(finalSettlement);
                    AlertWindow.display("Build Constrain", "Cannot build this settlement!");
                } else {
                    bSettle.setStyle("-fx-background-color:#FF0000;" +   //设置背景颜色 #FFCC00橘色
                            "-fx-text-fill:#000000;");                 //设置字体颜色
                    if (boardState.equals("")) {
                        player.setBoard_state(finalSettlement);
                    } else {
                        player.setBoard_state(boardState + "," + finalSettlement);
                    }
                    System.out.println("\nsettleButton boardState: " + player.getBoard_state());
                    resourceState[4] -= 1;
                    resourceState[3] -= 1;
                    resourceState[2] -= 1;
                    resourceState[1] -= 1;
                    player.setResource_state(resourceState);
                    bSettle.setDisable(true);
                    // show resource
                    showResource(player);
                }
            });
        }
        Button bS3 = settleButton.get(0);
        bS3.setLayoutX(519);
        bS3.setLayoutY(237);
        Button bS4 = settleButton.get(1);
        bS4.setLayoutX(519);
        bS4.setLayoutY(436);
        Button bS5 = settleButton.get(2);
        bS5.setLayoutX(526);
        bS5.setLayoutY(622);
        Button bS7 = settleButton.get(3);
        bS7.setLayoutX(699);
        bS7.setLayoutY(529);
        Button bS9 = settleButton.get(4);
        bS9.setLayoutX(691);
        bS9.setLayoutY(337);
        Button bS11 = settleButton.get(5);
        bS11.setLayoutX(695);
        bS11.setLayoutY(141);
        node.getChildren().addAll(settleButton);
    }

    /**
     * make roads' buttons
     *
     * @param node :The group contains the buttons.
     * @param player: The game player
     * @auther Zetian Chen (uid: u7564812)
     */
    public void makeRoadButton(Group node, Player player) {
        List<Button> roadButton = new ArrayList<>();
        for (int i = 0; i <= 15; i++) {
            roadButton.add(new Button("1"));
            Button bRoad = roadButton.get(i);
            bRoad.setPrefHeight(20);
            bRoad.setPrefWidth(60);

            String road = "R" + i;
            bRoad.setOnAction(actionEvent -> {
                int[] resourceState = player.getResource_state();
                String boardState = player.getBoard_state();
                if (!(CatanDice.checkResources("R", resourceState))) {
                    AlertWindow.display("Resource Constrain", "Not enough resources to build a road!");
                } else if (!(CatanDice.checkBuildConstraints(road, boardState))) {
//                    System.out.print("\nroad Build Constrain: "+boardState+"\n");
                    AlertWindow.display("Build Constrain", "Cannot build this road!");
                } else {
                    bRoad.setStyle("-fx-background-color:#696969;" +   //设置背景颜色
                            "-fx-text-fill:#FFF;");                 //设置字体颜色
                    if (boardState.equals("")) {
                        player.setBoard_state(road);
                    } else {
                        player.setBoard_state(boardState + "," + road);
                    }
                    System.out.println("\nroadButton boardState: " + player.getBoard_state());
                    resourceState[4] -= 1;
                    resourceState[3] -= 1;
                    player.setResource_state(resourceState);
                    bRoad.setDisable(true);
                    // show resource
                    showResource(player);
                }
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

    /**
     * The point display board.
     *
     * @auther Zetian Chen (uid: u7564812)
     */
    void displayScore() {
        Group scoreGroup = new Group();
        Image scoreImage = new Image(Objects.requireNonNull(Viewer.class.getResource("island-one-score.png")).toString());
        importImage(scoreGroup, scoreImage, 300.0, 600.0);
        scoreGroup.setLayoutX(300);        //the distance from score image to the right border is 300
        scoreGroup.setLayoutY(-200);           //the distance from score image to the upper border is 200
        if (!(root.getChildren().contains(scoreGroup)))
            root.getChildren().add(scoreGroup);

    }

    Button bSwapTrade = new Button("Would like Swap or Trade?");

    /**
     * display the button of changing resource
     *
     * @param player: The game player
     * @auther Zetian Chen (uid: u7564812)
     */
    public void displayResource(Player player) {
        Group resourceGroup = new Group();
        bSwapTrade.setOnMouseClicked(event -> ChangeResources.display("Please change your resources", "Please choose your operation", player));
        resourceGroup.getChildren().add(bSwapTrade);
        resourceGroup.setLayoutX(WINDOW_WIDTH / 2.0 + 10);
        resourceGroup.setLayoutY(WINDOW_HEIGHT / 2.0 + 200);
        if (!(root.getChildren().contains(resourceGroup)))
            root.getChildren().add(resourceGroup);

    }

    /**
     * make dices' buttons
     *
     * @param X the layout position x direction.
     * @param Y the layout position y direction.
     * @auther Zihan Ai(uid: u7528678), Zetian Chen (uid: u7564812)
     */

    public Button makeDicesButton(Double X, Double Y) {
        Group dice = new Group();
        Button d = new Button(" ");
        dice.getChildren().add(d);
        dice.setLayoutX(X);
        dice.setLayoutY(Y);
        d.setOnAction(event -> d.setDisable(true));
        if (!(root.getChildren().contains(dice)))
            root.getChildren().add(dice);
        return d;
    }

    /**
     * covert the index of resource to name.
     *
     * @param resourceID :The index of resource.
     * @auther Zihan Ai(uid: u7528678), Zetian Chen (uid: u7564812)
     */


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

    Button roll1 = new Button("Roll dices");
    Button roll2 = new Button("Roll dices");
    Button roll3 = new Button("Roll dices");
    Button ready = new Button("Ready");
    Button r = new Button("Reset");
    Text[] t = new Text[6];

    /**
     * roll dices and display the result.
     * After rolling dices, update the resource state.
     *
     * @param player: The game player.
     * @auther Zihan Ai(uid: u7528678)
     */

    public void displayDices(Player player) {
        Group DiceGroup = new Group();
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

        DiceGroup2.getChildren().add(roll2);
        DiceGroup2.setLayoutX(WINDOW_WIDTH / 2.0 + 50);
        DiceGroup2.setLayoutY(WINDOW_HEIGHT / 2.0 + 30);
        if (!(root.getChildren().contains(DiceGroup2)))
            root.getChildren().add(DiceGroup2);
        roll2.setDisable(true);

        Group DiceGroup3 = new Group();
        DiceGroup3.getChildren().add(roll3);
        DiceGroup3.setLayoutX(WINDOW_WIDTH / 2.0 + 50);
        DiceGroup3.setLayoutY(WINDOW_HEIGHT / 2.0 + 80);
        if (!(root.getChildren().contains(DiceGroup3)))
            root.getChildren().add(DiceGroup3);
        roll3.setDisable(true);


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
            ready.setDisable(false);
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
        Ready.getChildren().add(ready);
        Ready.setLayoutX(WINDOW_WIDTH / 2.0 + 500);
        Ready.setLayoutY(WINDOW_HEIGHT / 2.0 + 30);
        if (!(root.getChildren().contains(Ready)))
            root.getChildren().add(Ready);

        Group reset = new Group();

        reset.getChildren().add(r);
        reset.setLayoutX(WINDOW_WIDTH / 2.0 + 500);
        reset.setLayoutY(WINDOW_HEIGHT / 2.0 - 20);
        if (!(root.getChildren().contains(reset)))
            root.getChildren().add(reset);

        ready.setDisable(true);
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
            int[] r_s = new int[6];
            System.arraycopy(res_s, 0, r_s, 0, 6);

            player.setResource_state(r_s);
            showResource(player);
            for (int i = 0; i <= 5; i++) {
                res_s[i] = 0;
            }

        });


        r.setOnAction(event -> {
            for (Button b : resourcesButtons) {
                b.setDisable(false);
            }
        });
    }

    Group showResources = new Group();

    /**
     * If the resource state doesn't update on time, this button will display the latest state.
     *
     * @param player: The game player
     * @auther Zihan Ai(uid: u7528678)
     */

    public void showResource(Player player) {
        Text show = new Text();
        showResources.getChildren().clear();
        showResources.getChildren().add(show);
        showResources.setLayoutX(WINDOW_WIDTH / 2.0 + 400);
        showResources.setLayoutY(WINDOW_HEIGHT / 2.0 + 50);
        String resource_state = ChangeResources.resourceStateToString(player.resource_state);
        show.setText(resource_state);
        if (!(root.getChildren().contains(showResources)))
            root.getChildren().add(showResources);
    }

    Button sb = new Button("Show resource");

    /**
     * show the resource state of player.
     *
     * @param player: the game player.
     * @auther Zihan Ai(uid: u7528678), Zetian Chen (uid: u7564812)
     */


    public void show(Player player) {
        Group showButton = new Group();

        showButton.getChildren().add(sb);
        showButton.setLayoutX(WINDOW_WIDTH / 2.0 + 200);
        showButton.setLayoutY(WINDOW_HEIGHT / 2.0 + 40);
        if (!(root.getChildren().contains(showButton)))
            root.getChildren().add(showButton);

//        Group showResources = new Group();
        Text show = new Text();
        showResources.getChildren().add(show);
        showResources.setLayoutX(WINDOW_WIDTH / 2.0 + 400);
        showResources.setLayoutY(WINDOW_HEIGHT / 2.0 + 50);
        if (!(root.getChildren().contains(showResources)))
            root.getChildren().add(showResources);
        sb.setOnAction(event -> showResource(player));
    }


    /**
     * After using the joker, change the appearance of them.
     *
     * @param knight: the index of knight.
     * @param node :the group contain the kinghts.
     * @auther Zetian Chen (uid: u7564812)
     */

    public void changeKnightButton(String knight, Group node) {
        switch (knight) {
            case "K1" -> {
                Button k = new Button("Knight");
                k.setStyle("-fx-background-color:#FFF;" +   //设置背景颜色
                        "-fx-text-fill:#0000CD;");                 //设置字体颜色
                k.setLayoutX(360);
                k.setLayoutY(165);
                node.getChildren().add(k);
            }
            case "K2" -> {
                Button k = new Button("Knight");
                k.setStyle("-fx-background-color:#FFF;" +   //设置背景颜色
                        "-fx-text-fill:#0000CD;");                 //设置字体颜色
                k.setLayoutX(360);
                k.setLayoutY(363);
                node.getChildren().add(k);
            }
            case "K3" -> {
                Button k = new Button("Knight");
                k.setStyle("-fx-background-color:#FFF;" +   //设置背景颜色
                        "-fx-text-fill:#0000CD;");                 //设置字体颜色
                k.setLayoutX(574);
                k.setLayoutY(450);
                node.getChildren().add(k);
            }
            case "K4" -> {
                Button k = new Button("Knight");
                k.setStyle("-fx-background-color:#FFF;" +   //设置背景颜色
                        "-fx-text-fill:#0000CD;");                 //设置字体颜色
                k.setLayoutX(747);
                k.setLayoutY(354);
                node.getChildren().add(k);
            }
            case "K5" -> {
                Button k = new Button("Knight");
                k.setStyle("-fx-background-color:#FFF;" +   //设置背景颜色
                        "-fx-text-fill:#0000CD;");                 //设置字体颜色
                k.setLayoutX(786);
                k.setLayoutY(171);
                node.getChildren().add(k);
            }
            case "K6" -> {
                Button k = new Button("Knight");
                k.setStyle("-fx-background-color:#FFF;" +   //设置背景颜色
                        "-fx-text-fill:#0000CD;");                 //设置字体颜色
                k.setLayoutX(614);
                k.setLayoutY(62);
                node.getChildren().add(k);
            }
        }
    }

    /**
     * make knights' buttons
     *
     * @param player: The game player.
     * @param node : the group contain the knights.
     * @auther Zetian Chen (uid: u7564812)
     */

    public void makeKnight(Player player, Group node) {
        String board_state = player.getBoard_state();
        List<String> myBoardState = Arrays.asList(new Board(board_state).getStructure());
        for (int i = 1; i <= 6; i++) {
            String knight = "K" + i;
            if (myBoardState.contains(knight)) {
                changeKnightButton(knight, node);
            }
        }
    }


    /**
     * The operation of end a turn and begin next turn.
     *
     * @param player: The game player.
     * @auther Zihan Ai (uid:u7528678)
     */
    public void endTurn(Player player) {
        Group endturn = new Group();
        Button end = new Button("End this turn and calculate score");
        endturn.getChildren().add(end);
        endturn.setLayoutX(WINDOW_WIDTH / 2.0 + 350);
        endturn.setLayoutY(WINDOW_HEIGHT / 2.0 + 280);
        if (!(root.getChildren().contains(endturn)))
            root.getChildren().add(endturn);

        end.setOnAction(event -> {
            makeKnight(player, boardGroup); // change Joker to Knight if there is
            //reset all buttons, properties of player
            player.resource_state = new int[6];
            showResource(player);
            player.setTurn(player.getTurn() + 1);
            roll1.setDisable(false);
            roll2.setDisable(true);
            roll3.setDisable(true);
            r.setDisable(false);
            ready.setDisable(false);
            for (int i = 0; i <= 5; i++) {
                t[i].setText("");
            }
            ready.setDisable(true);
            System.out.println(player.getBoard_state());
            showPoints(player);
            displayTotalPoint(player);
            if (player.turn == 15) {
                roll1.setDisable(true);
                roll2.setDisable(true);
                roll3.setDisable(true);
                r.setDisable(true);
                ready.setDisable(true);
                sb.setDisable(true);
                bSwapTrade.setDisable(true);
                end.setDisable(true);
                EndWindow.display(player);
            }

        });
    }

    public int all_point = 0;
    int punish = 0;

    /**
     * calculate the point of each turn.
     *
     * @param player: The game player.
     * @auther Zihan Ai (uid: u7528678)
     */
    public void getPoint(Player player) {
        int point = 0;
        String[] state = player.getBoard_state().split(",");
        if (!state[0].equals("")) {
            for (String str : state) {
                if (str.charAt(0) == 'R') {
                    point++;//roads point
                } else {
                    int p = Integer.parseInt(str.substring(1));//settlement,city,joker points
                    point += p;
                }
            }
        }
        for (int i = 0; i <= player.getTurn() - 1; i++) {
            all_point += player.getPoint()[i];
        }
        if (point == all_point) {
            player.setPoint(player.getTurn() - 1, -2);
            punish++;
        } else {
            player.setPoint(player.getTurn() - 1, point - all_point);
        }
        all_point = punish * 2;

    }

    Group points = new Group();
    List<Text> point = new ArrayList<>();
    Text[] p = new Text[15];
    int a = 1;

    /**
     * display the point on the point board.
     *
     * @param player: The game player.
     * @auther Zihan Ai (uid: u7528678)
     */
    public void showPoints(Player player) {
        getPoint(player);
        for (int i = 0; i <= 14; i++) {
            p[i] = new Text();
        }
        for (int i = 0; i <= 4; i++) {
            point.add(p[i]);
            p[i].setLayoutX(970 + 47 * i);
            p[i].setLayoutY(45);
        }
        point.add(p[5]);

        p[5].setLayoutX(970 + 47 * 4);
        p[5].setLayoutY(45 + 58);
        for (int i = 6; i <= 10; i++) {
            point.add(p[i]);
            p[i].setLayoutX(970 + 47 * (4 - i + 6));
            p[i].setLayoutY(45 + 58 * 2);
        }
        point.add(p[11]);
        p[11].setLayoutX(970);
        p[11].setLayoutY(45 + 58 * 3);
        for (int i = 12; i <= 14; i++) {
            point.add(p[i]);
            p[i].setLayoutX(970 + 47 * (i - 12));
            p[i].setLayoutY(45 + 58 * 4);
        }

        for (int i = 0; i <= 14; i++) {
            point.get(i).setText(String.valueOf(player.getPoint()[i]));
            p[i].setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 30));
        }


        if (a == 1) {
            points.getChildren().addAll(point);
            a++;
        }
        if (!(root.getChildren().contains(points)))
            root.getChildren().add(points);

    }

    public Group allp = new Group();
    public Text allPoint = new Text();
    int b = 0;

    /**
     * display the total point on the point board.
     *
     * @param player: The game player.
     * @auther Zihan Ai (uid: u7528678)
     */
    public void displayTotalPoint(Player player) {
        if (b == 0) {
            allp.getChildren().add(allPoint);
            b++;
        }
        allPoint.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 30));
        allPoint.setLayoutX(970 + 47 * 2 + 82);
        allPoint.setLayoutY(45 + 58 * 4);
        if (!(root.getChildren().contains(allp)))
            root.getChildren().add(allp);
        int total = 0;
        for (int point : player.getPoint()) {
            total += point;
        }
        allPoint.setText(String.valueOf(total));
    }

    /**
     * The main part of the game.
     * start the game
     *
     * @param stage : the stage to display the game.
     * @auther Zihan Ai (uid: u7528678), Zetian Chen(uid: u7654812)
     */
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Catan Dice");
        Scene scene = new Scene(this.root, WINDOW_WIDTH, WINDOW_HEIGHT);
        Player player = new Player();
        player.resource_state = new int[]{0, 0, 0, 0, 0, 0};
        player.board_state = "";
        player.turn = 0;
        displayBoard(player);
        displayScore();
        displayDices(player);
        displayResource(player);
        show(player);
        endTurn(player);
        stage.setScene(scene);
        stage.show();
    }


}
