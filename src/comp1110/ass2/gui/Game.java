package comp1110.ass2.gui;

import comp1110.ass2.CatanDice;
import comp1110.ass2.Player;
import gittest.B;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
        Button bR1 = new Button("1");
        bR1.setPrefHeight(20);
        bR1.setPrefWidth(60);
        bR1.setLayoutX(470);
        bR1.setLayoutY(294);
        bR1.setRotate(-60);

        bR1.setOnAction(actionEvent -> {
            bR1.setStyle("-fx-background-color:#696969;" +   //设置背景颜色
                    "-fx-text-fill:#FFF;");                 //设置字体颜色
        });

        node.getChildren().add(bR1);

//        switch (indexString) {
//            case "0" -> {
//                Rectangle road0 = new Rectangle(20, 60, Color.BLACK);
//                road0.setX(490); //LayoutX
//                road0.setY(274); //LayoutY
//                road0.setRotate(30);
//                node.getChildren().add(road0);
//            }
//            case "1" -> {
//                Rectangle road1 = new Rectangle(20, 60, Color.BLACK);
//                road1.setX(413);
//                road1.setY(315);
//                road1.setRotate(90);
//                node.getChildren().add(road1);
//            }
//            case "2" -> {
//                Rectangle road2 = new Rectangle(20, 60, Color.BLACK);
//                road2.setX(490);
//                road2.setY(356);
//                road2.setRotate(-30);
//                node.getChildren().add(road2);
//            }
//            case "3" -> {
//                Rectangle road3 = new Rectangle(20, 60, Color.BLACK);
//                road3.setX(490);
//                road3.setY(469);
//                road3.setRotate(30);
//                node.getChildren().add(road3);
//            }
//            case "4" -> {
//                Rectangle road4 = new Rectangle(20, 60, Color.BLACK);
//                road4.setX(413);
//                road4.setY(505);
//                road4.setRotate(90);
//                node.getChildren().add(road4);
//            }
//            case "5" -> {
//                Rectangle road5 = new Rectangle(20, 60, Color.BLACK);
//                road5.setX(491);
//                road5.setY(546);
//                road5.setRotate(-30);
//                node.getChildren().add(road5);
//            }
//            case "6" -> {
//                Rectangle road6 = new Rectangle(20, 60, Color.BLACK);
//                road6.setX(590);
//                road6.setY(600);
//                road6.setRotate(90);
//                node.getChildren().add(road6);
//            }
//            case "7" -> {
//                Rectangle road7 = new Rectangle(20, 60, Color.BLACK);
//                road7.setX(665);
//                road7.setY(560);
//                road7.setRotate(30);
//                node.getChildren().add(road7);
//            }
//            case "8" -> {
//                Rectangle road8 = new Rectangle(20, 60, Color.BLACK);
//                road8.setX(666);
//                road8.setY(451);
//                road8.setRotate(-30);
//                node.getChildren().add(road8);
//            }
//            case "9" -> {
//                Rectangle road9 = new Rectangle(20, 60, Color.BLACK);
//                road9.setX(665);
//                road9.setY(367);
//                road9.setRotate(30);
//                node.getChildren().add(road9);
//            }
//            case "10" -> {
//                Rectangle road10 = new Rectangle(20, 60, Color.BLACK);
//                road10.setX(665);
//                road10.setY(254);
//                road10.setRotate(-30);
//                node.getChildren().add(road10);
//            }
//            case "11" -> {
//                Rectangle road11 = new Rectangle(20, 60, Color.BLACK);
//                road11.setX(667);
//                road11.setY(169);
//                road11.setRotate(30);
//                node.getChildren().add(road11);
//            }
//            case "12" -> {
//                Rectangle road12 = new Rectangle(20, 60, Color.BLACK);
//                road12.setX(764);
//                road12.setY(500);
//                road12.setRotate(90);
//                node.getChildren().add(road12);
//            }
//            case "13" -> {
//                Rectangle road13 = new Rectangle(20, 60, Color.BLACK);
//                road13.setX(832);
//                road13.setY(463);
//                road13.setRotate(30);
//                node.getChildren().add(road13);
//            }
//            case "14" -> {
//                Rectangle road14 = new Rectangle(20, 60, Color.BLACK);
//                road14.setX(835);
//                road14.setY(354);
//                road14.setRotate(-30);
//                node.getChildren().add(road14);
//            }
//            case "15" -> {
//                Rectangle road15 = new Rectangle(20, 60, Color.BLACK);
//                road15.setX(837);
//                road15.setY(269);
//                road15.setRotate(30);
//                node.getChildren().add(road15);
//            }
//        }
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
            ChangeResources.display("Please change your resources", "Please choose your operation",player);
        });
        resourceGroup.getChildren().add(bSwapTrade);
        resourceGroup.setLayoutX(WINDOW_WIDTH / 2.0 + 10);
        resourceGroup.setLayoutY(WINDOW_HEIGHT / 2.0 + 200);
        if (!(root.getChildren().contains(resourceGroup)))
            root.getChildren().add(resourceGroup);

    }

    public Button makeDicesButton(Double X,Double Y){
        Group dice = new Group();
        Button d = new Button(" ");
        dice.getChildren().add(d);
        dice.setLayoutX(X);
        dice.setLayoutY(Y);
        d.setOnAction(event->{
            d.setDisable(true);
        });
        if (!(root.getChildren().contains(dice)))
            root.getChildren().add(dice);
        return d;
    }

    public String makeDiceResource(int resourceID){
        String s = null;
        switch (resourceID){
            case 0->s="Ore";
            case 1->s="Grain";
            case 2->s="Wool";
            case 3->s="Timber";
            case 4->s="Brick";
            case 5->s="Gold";
        }
        return s;
    }

    public void displayDices(Player player){
        Group DiceGroup = new Group();
        Button roll1 = new Button("Roll dices");
        DiceGroup.getChildren().add(roll1);
        DiceGroup.setLayoutX(WINDOW_WIDTH / 2.0 + 50 );
        DiceGroup.setLayoutY(WINDOW_HEIGHT / 2.0 -20);
        int[] res_s = new int[6];
        Button[] resourcesButtons = new Button[6];
        for(int i=0;i<=5;i++) {
            resourcesButtons[i] = makeDicesButton(WINDOW_WIDTH / 2.0 + 200+50*i, WINDOW_HEIGHT / 2.0 -20);
            resourcesButtons[i].setDisable(true);
        }
        if (!(root.getChildren().contains(DiceGroup)))
            root.getChildren().add(DiceGroup);

        Group DiceGroup2 = new Group();
        Button roll2 = new Button("Roll dices");
        DiceGroup2.getChildren().add(roll2);
        DiceGroup2.setLayoutX(WINDOW_WIDTH / 2.0 + 50 );
        DiceGroup2.setLayoutY(WINDOW_HEIGHT / 2.0 + 30);
        if (!(root.getChildren().contains(DiceGroup2)))
            root.getChildren().add(DiceGroup2);
        roll2.setDisable(true);

        Group DiceGroup3 = new Group();
        Button roll3 = new Button("Roll dices");
        DiceGroup3.getChildren().add(roll3);
        DiceGroup3.setLayoutX(WINDOW_WIDTH / 2.0 + 50 );
        DiceGroup3.setLayoutY(WINDOW_HEIGHT / 2.0 + 80);
        if (!(root.getChildren().contains(DiceGroup3)))
            root.getChildren().add(DiceGroup3);
        roll3.setDisable(true);

        Text[] t = new Text[6];
        for(int i  = 0;i<=5;i++){
            Group resource_type = new Group();
            t[i] = new Text();
            resource_type.getChildren().add(t[i]);
            resource_type.setLayoutX(WINDOW_WIDTH / 2.0 + 200+50*i);
            resource_type.setLayoutY(WINDOW_HEIGHT / 2.0 + 30);
            if (!(root.getChildren().contains(resource_type)))
                root.getChildren().add(resource_type);
        }


        roll1.setOnAction(event->{

            for(int i  = 0;i<=5;i++){
                t[i].setText(makeDiceResource(diceState(6)[i]));
            }
            roll1.setDisable(true);
            for(Button b :resourcesButtons){
                b.setDisable(false);
            }
            roll2.setDisable(false);
        });



        roll2.setOnAction(event->{
            for(int i = 0;i<=5;i++){
                if(resourcesButtons[i].isDisabled()){
                    t[i].setText(makeDiceResource(diceState(6)[i]));
                }
            }
            roll2.setDisable(true);
            roll3.setDisable(false);
            for(Button b :resourcesButtons){
                b.setDisable(false);
            }
        });

        roll3.setOnAction(event->{
            for(int i = 0;i<=5;i++){
                if(resourcesButtons[i].isDisabled()){
                    t[i].setText(makeDiceResource(diceState(6)[i]));
                }
            }
            roll3.setDisable(true);
            for(Button b :resourcesButtons){
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
        reset.setLayoutX(WINDOW_WIDTH / 2.0 + 500 );
        reset.setLayoutY(WINDOW_HEIGHT / 2.0 -20);
        if (!(root.getChildren().contains(reset)))
            root.getChildren().add(reset);

        ready.setOnAction(event->{
            roll1.setDisable(true);
            roll2.setDisable(true);
            roll3.setDisable(true);
            r.setDisable(true);
            for (Button b:resourcesButtons){
                b.setDisable(true);
            }
            ready.setDisable(true);
            int[] states = new int[6];
            for(int i =0;i<=5;i++){
                states[i] = Integer.parseInt(ChangeResources.resourceToNum(t[i].getText()));
            }
            for(int i =0;i<=5;i++){
                res_s[states[i]]++;
            }
            for(int i = 0;i<=5;i++){
                System.out.print(res_s[i]);
            }
            player.setResource_state(res_s);

        });


        r.setOnAction(event->{
            for(Button b:resourcesButtons){
                b.setDisable(false);
            }
        });
    }

    public void show(Player player){
        Group showButton = new Group();
        Button sb = new Button("Show resource");
        showButton.getChildren().add(sb);
        showButton.setLayoutX(WINDOW_WIDTH / 2.0 + 200 );
        showButton.setLayoutY(WINDOW_HEIGHT / 2.0 +40);
        if (!(root.getChildren().contains(showButton)))
            root.getChildren().add(showButton);

        Group showResources = new Group();
        Text show = new Text();
        showResources.getChildren().add(show);
        showResources.setLayoutX(WINDOW_WIDTH / 2.0 + 400 );
        showResources.setLayoutY(WINDOW_HEIGHT / 2.0 +50);
        if (!(root.getChildren().contains(showResources)))
            root.getChildren().add(showResources);
        sb.setOnAction(event->{
            String resource_state = ChangeResources.resourceStateToString(player.resource_state);
            show.setText(resource_state);
        });
    }

    public void endTurn(Player player){
        Group endturn = new Group();
        Button end = new Button("End this turn and calculate score");
        endturn.getChildren().add(end);
        endturn.setLayoutX(WINDOW_WIDTH / 2.0 + 350);
        endturn.setLayoutY(WINDOW_HEIGHT / 2.0 +280);
        if (!(root.getChildren().contains(endturn)))
            root.getChildren().add(endturn);

        Group showturn = new Group();
        Text turn = new Text();
        turn.setText("Turn 1");
        showturn.getChildren().add(turn);
        showturn.setLayoutX(WINDOW_WIDTH / 2.0 + 430);
        showturn.setLayoutY(WINDOW_HEIGHT / 2.0 +260);
        if (!(root.getChildren().contains(showturn)))
            root.getChildren().add(showturn);

        end.setOnAction(event->{
            player.setResource_state(new int[]{0, 0, 0, 0, 0, 0});
            player.setTurn(player.getTurn()+1);
            turn.setText("Turn "+player.getTurn());

        });




    }


    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Catan Dice");
        Scene scene = new Scene(this.root, WINDOW_WIDTH, WINDOW_HEIGHT);
        Player player = new Player();

        player.resource_state = new int[]{0, 0, 0, 0, 0, 0};
        player.board_state = "";
        player.turn=1;

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
