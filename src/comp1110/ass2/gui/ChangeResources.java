package comp1110.ass2.gui;

import comp1110.ass2.CatanDice;
import comp1110.ass2.Player;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ChangeResources {


    public static Player player = new Player();


    static Button ore = new Button("Ore");
    static Button grain = new Button("Grain");
    static Button wool = new Button("Wool");
    static Button timber = new Button("Timber");
    static Button brick = new Button("Brick");
    static Button gold = new Button("Gold");
    static Button[] resources = {ore, grain, wool, timber, brick, gold};
    static Button swap = new Button("Swap");
    static Button trade = new Button("trade");
    static Button cancel = new Button("Cancel");
    static Button ok = new Button("OK");
    /**
     * display the window which used to changing resources
     * after the action, update the player's resource state
     *
     * @param title: The string representation the window's title.
     * @param msg: The massage to display on this window。
     * @param player: The game player.
     * @author Zihan Ai (uid: u7528678)
     */

    public static void display(String title, String msg, Player player) {
        int[] r = player.getResource_state();
        String b = player.getBoard_state();
        player.setResource_state(r);
        player.setBoard_state(b);
        int[] resource_state = player.getResource_state();
        String t1 = resourceStateToString(player.getResource_state());
        Text re = new Text();
        re.setText(t1);
//        Text bo = new Text();
        Label bo = new Label();
        bo.setText("your board state: " + player.getBoard_state());
        bo.setWrapText(true);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label();
        label.setText(msg);


        for (Button button : resources) {
            button.setDisable(true);
        }
        cancel.setDisable(true);

        trade.setDisable(true);
        if (resource_state[5] >= 2) {
            trade.setDisable(false);
        }
        ok.setDisable(true);


        Text t2 = new Text();
        Text t3 = new Text();
        Text t4 = new Text();
        swap.setOnAction(event -> {
            for (int i = 0; i <= 5; i++) {
                if (player.getResource_state()[i] > 0)
                    resources[i].setDisable(false);
            }
            cancel.setDisable(false);
            swap.setDisable(true);
            trade.setDisable(true);
            t2.setText("swap");
            for (Button bu : resources) {
                bu.setOnMouseClicked(event2 -> {
                    t3.setText(bu.getText());
                    for (Button bb : resources) {
                        bb.setDisable(true);
                    }
                    lightOnButtonsSwap(player.getBoard_state());
                    for (Button bb0 : resources) {
                        bb0.setOnMouseClicked(event0 -> {
                            t4.setText(bb0.getText());
                            ok.setDisable(false);
                        });
                    }
                });
            }
        });

        trade.setOnAction(event -> {
            lightOnButtonsTrade();
            cancel.setDisable(false);
            swap.setDisable(true);
            trade.setDisable(true);
            t2.setText("trade");
            for (Button bu : resources) {
                bu.setOnMouseClicked(event2 -> {
                    t3.setText(bu.getText());
                    ok.setDisable(false);
                });
            }
        });

        cancel.setOnMouseClicked(event -> {
            ore.setDisable(true);
            grain.setDisable(true);
            wool.setDisable(true);
            timber.setDisable(true);
            brick.setDisable(true);
            gold.setDisable(true);
            cancel.setDisable(true);
            swap.setDisable(false);
            if (resource_state[5] >= 2) {
                trade.setDisable(false);
            }
            t2.setText("");
            t3.setText("");
            t4.setText("");
            ok.setDisable(true);
        });

        ok.setOnMouseClicked(event -> {
            String s = "";
            if (t4.getText().equals("") && t2.getText().equals("trade")) {
                s += "trade ";
                s += resourceToNum(t3.getText());
            } else if (t2.getText().equals("swap") && !t4.getText().equals("")) {
                s = "swap " + resourceToNum(t3.getText()) + " " + resourceToNum(t4.getText());
            }
            System.out.println(s);
            player.setResource_state(CatanDice.updateResourceState(s, player.getResource_state()));
            String bb = CatanDice.updateBoardState(s, player.getBoard_state());
            player.setBoard_state(bb);
            re.setText(resourceStateToString(player.getResource_state()));
            bo.setText("your board state: " + bb);
            for (Button bb1 : resources) {
                bb1.setDisable(true);
            }
            ok.setDisable(true);
            if (resource_state[5] >= 2) {
                trade.setDisable(false);
            }
            swap.setDisable(false);
        });

        Button close = new Button("Close");
        close.setOnAction(event -> stage.close());

        VBox vBox = new VBox();
        vBox.getChildren().addAll(label, swap, trade, ore, grain, wool, timber, brick, gold, cancel, re, bo, ok, t2, t3, t4, close);

        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox, 200, 500);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.showAndWait();

    }

    public static void lightOnButtonsSwap(String board_state) {
        String[] bs = board_state.split(",");
        List<String> b = new ArrayList<>(Arrays.asList(bs));
        for (int i = 0; i <= 5; i++) {
            if (b.contains("J" + (i + 1)) || b.contains("J6")) {
                resources[i].setDisable(false);
            }
        }

    }

    public static void lightOnButtonsTrade() {
        for (int i = 0; i <= 4; i++) {
            resources[i].setDisable(false);
        }
    }

    public static String resourceStateToString(int[] resource_state) {
        String s = "";
        s += "Ore : " + resource_state[0] + "\n";
        s += "Grain : " + resource_state[1] + "\n";
        s += "Wool : " + resource_state[2] + "\n";
        s += "Timber : " + resource_state[3] + "\n";
        s += "Brick : " + resource_state[4] + "\n";
        s += "Gold : " + resource_state[5] + "\n";
        return s;
    }

    public static String resourceToNum(String s) {
        String i = null;
        switch (s) {
            case "Ore" -> i = "0";
            case "Grain" -> i = "1";
            case "Wool" -> i = "2";
            case "Timber" -> i = "3";
            case "Brick" -> i = "4";
            case "Gold" -> i = "5";
        }
        return i;
    }


}

