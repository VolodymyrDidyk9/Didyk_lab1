package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {


    static int[] copyar(int[] another){
        int[] arr = new int[another.length];
        for(int i=0; i<another.length; i++)
            arr[i]=another[i];
        return arr;
    }

    static int[][] copyarr(int[][] another) {
        int[][] arr = new int[another.length][];
        for (int i = 0; i<another.length; i++){
            arr[i]=copyar(another[i]);
        }
        return arr;
    }

    static boolean equals(int[][] arr1, int[][] arr2){
        if (arr1.length!=arr2.length)
            return false;
        for(int i=0; i<arr1.length; i++)
            if(arr1[i].length!=arr2[i].length)
                return false;
        for (int i = 0; i<arr1.length;i++)
            for (int j = 0; j<arr1.length;j++)
                if (arr1[i][j]!=arr2[i][j])
                    return false;
        return true;
    }

    //Перевірка, чи є поле вирішуваним
    static boolean check(Node n){
        int s=0;
        for (int i=0; i<n.value.length; i++)
            for (int j=0; j<n.value.length; j++) {
                if (n.value[i][j] != 0) {
                    for (int k = j; k < n.value.length; k++)
                        if (n.value[i][j] > n.value[i][k] && n.value[i][k] != 0) {
                            s++;
                        }
                    for (int k = i + 1; k < n.value.length; k++)
                        for (int z = 0; z < n.value.length; z++)
                            if (n.value[i][j] > n.value[k][z] && n.value[k][z] != 0)
                                s++;
                }
            }
        if(n.value.length%2==0) {
            n.searchZero();
            s += n.zi + 1;
        }
        return s % 2 != 1;
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        primaryStage.setTitle("Гра у 8");
        primaryStage.setScene(new Scene(root, 420, 235));
        primaryStage.getIcons().add(new Image("file:\\Java_project\\SSS\\src\\sample\\icon.jpg"));
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }




}

