package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//import static sample.MainController.counter;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/sample.fxml"));
        primaryStage.setTitle("Kaban Table");
        primaryStage.setScene(new Scene(root, 640, 400));
        primaryStage.setResizable(false);

//        todoList.add(0,new Task("Task1",LevelOfPriority.MEDIUM, LocalDate.now()));
//        counter++;
//        ListView<Task> todolist = new ListView<>();
//        todolist.setEditable(true);
//        todolist.setCellFactory(new Callback<ListView<Task>, ListCell<Task>>() {
//            @Override
//            public ListCell<Task> call(ListView<Task> param) {
//                return new TaskCell();
//            }
//        });
//        todolist.setItems(todoList);
//
//
//
//        todolist.setPrefWidth(100);
//        todolist.setPrefHeight(70);
//        todolist.setVisible(true);


        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
