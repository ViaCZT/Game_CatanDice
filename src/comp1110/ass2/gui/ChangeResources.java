package comp1110.ass2.gui;

import comp1110.ass2.CatanDice;
import comp1110.ass2.Player;
import gittest.A;
import gittest.B;
import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ChangeResources {

    private static final int VIEWER_WIDTH = 1200;
    private static final int VIEWER_HEIGHT = 700;

    private static final Group root = new Group();
    private final Group controls = new Group();
    private static final Group boardStateGroup = new Group();

    private TextField playerTextField;
    private TextField boardTextField;
    private static boolean res;
    private static boolean b1=false;


    public static Player player=new Player();


    static Button ore = new Button("Ore");
    static Button grain = new Button("Grain");
    static Button wool = new Button("Wool");
    static Button timber = new Button("Timber");
    static Button brick = new Button("Brick");
    static Button gold = new Button("Gold");
    static Button[] resources = {ore,grain,wool,timber,brick,gold};

    public static boolean display(String title, String msg){
        int[] r = {0,1,2,1,1,2};
        String b = "J1,J2,J3";
        player.setResource_state(r);
        player.setBoard_state(b);
        int[] resource_state = player.getResource_state();
        String t1=resourceStateToString(player.getResource_state());
        Text re =new Text();
        re.setText(t1);
        Text bo = new Text();
        bo.setText("your board state: " +player.getBoard_state());


        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label();
        label.setText(msg);
        Button swap = new Button("Swap");
        Button trade = new Button("trade");
        Button cancel = new Button("Cancel");
        Button ok = new Button("OK");

        for(Button button:resources){
            button.setDisable(true);
        }
        cancel.setDisable(true);

        trade.setDisable(true);
        if(resource_state[5]>=2){
            trade.setDisable(false);
        }


        Text t2 = new Text();
        Text t3 = new Text();
        Text t4 = new Text();
        swap.setOnAction(event -> {
            res=true;
            for(int i =0;i<=5;i++){
                if(player.getResource_state()[i]>0)
                resources[i].setDisable(false);
            }
            cancel.setDisable(false);
            swap.setDisable(true);
            trade.setDisable(true);
            t2.setText("swap");
            for(Button bu:resources){
                bu.setOnMouseClicked(event2->{
                    t3.setText(bu.getText());
                    for(Button bb :resources){
                        bb.setDisable(true);
                    }
                    lightOnButtonsSwap(player.getResource_state(), player.getBoard_state());
                    for (Button bb0:resources){
                        bb0.setOnMouseClicked(event0->{
                            t4.setText(bb0.getText());
                        });
                    }
                });
            }
        });

        trade.setOnAction(event -> {
            res=false;
            lightOnButtonsTrade(resource_state);
            cancel.setDisable(false);
            swap.setDisable(true);
            trade.setDisable(true);
            t2.setText("trade");
            for(Button bu:resources){
                bu.setOnMouseClicked(event2->{
                    t3.setText(bu.getText());
                });
            }
        });

        cancel.setOnMouseClicked(event ->{
            ore.setDisable(true);
            grain.setDisable(true);
            wool.setDisable(true);
            timber.setDisable(true);
            brick.setDisable(true);
            gold.setDisable(true);
            cancel.setDisable(true);
            swap.setDisable(false);
            if(resource_state[5]>=2){
                trade.setDisable(false);
            }
            t2.setText("");
            t3.setText("");
            t4.setText("");
        });

        ok.setOnMouseClicked(event->{
            String s ="";
            if(t4.getText()==""&&t2.getText()=="trade"){
                s+="trade ";
                s+=resourceToNum(t3.getText());
            }
            else if(t2.getText()=="swap"&&t4.getText()!=""){
                s="swap "+resourceToNum(t3.getText())+" "+resourceToNum(t4.getText());
            }
            System.out.println(s);
            player.setResource_state(CatanDice.updateResourceState(s,player.getResource_state()));
            String bb = CatanDice.updateBoardState(s,player.getBoard_state());
            player.setBoard_state(bb);
            re.setText(resourceStateToString(player.getResource_state()));
            bo.setText("your board state: "+bb);
            for(Button bb1:resources){
                bb1.setDisable(true);
            }
        });

        VBox vBox = new VBox();
        vBox.getChildren().addAll(label,swap,trade,ore,grain,wool,timber,brick,gold,cancel,re,bo,ok,t2,t3,t4);

        //设置居中
        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox,200,500);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.showAndWait();

        return res;
    }

    public static void lightOnButtonsSwap(int[] rs, String board_state){
        String[] bs = board_state.split(",");
        List<String> b= new ArrayList<>();
        b.addAll(Arrays.asList(bs));
        for(int i = 0;i<=5;i++){
            if(b.contains("J"+(i+1))||b.contains("J6")){
                resources[i].setDisable(false);
            }
        }

    }

    public static void lightOnButtonsTrade(int[] rs){
        for(int i = 0;i<=4;i++){
            resources[i].setDisable(false);
        }
    }

    public static String resourceStateToString(int resource_state[]){
        String s = "";
        s+="Ore : "+resource_state[0]+"\n";
        s+="Grain : "+resource_state[1]+"\n";
        s+="Wool : "+resource_state[2]+"\n";
        s+="Timber : "+resource_state[3]+"\n";
        s+="Brick : "+resource_state[4]+"\n";
        s+="Gold : "+resource_state[5]+"\n";
        return s;
    }

    public static String resourceToNum(String s){
        String i = null;
        switch (s){
            case "Ore"-> i="0";
            case "Grain"-> i="1";
            case "Wool"-> i="2";
            case "Timber"-> i="3";
            case "Brick"-> i="4";
            case "gold"-> i="5";
        }
        return i;
    }




}

