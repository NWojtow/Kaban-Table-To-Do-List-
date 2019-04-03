package sample;


import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import  javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;


public class MainController implements Initializable {

    @FXML
    ListView<Task> ToDoList;
    @FXML
    ListView<Task> inProgressList;
    @FXML
    ListView<Task> doneList;


    public static ObservableList<Task> todoList = FXCollections.observableArrayList();
    public static ObservableList<Task> inProgressTasks = FXCollections.observableArrayList();
    public static ObservableList<Task> doneTasks = FXCollections.observableArrayList();


    public static int editingIndex = 0;
    public static boolean ifInProgress;

    static int inProgressCounter = 0;
    static int doneCounter = 0;


    public void quitProgram() {
        System.out.println("Quit");
        Platform.exit();
        System.exit(0);
    }


    public void about() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("So you wonder, who did this fantastic app?");
        alert.setContentText("Norbert Wójtowicz" + '\n' + "Version: 1.0.0.2513161341");
        alert.showAndWait();
    }


    public void addTask() {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("view/AddTask.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Add Task");
            stage.show();


        } catch (Exception exc) {
            System.out.println("Exception occured");
        }
    }

    public void editToDo() {
        try {


            Parent root = FXMLLoader.load(getClass().getResource("view/Edit.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Edit");
            stage.show();


        } catch (Exception exc) {
            System.out.println("Exception occured");
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        //To do context menu
        ContextMenu tasksContext = new ContextMenu();
        MenuItem addTo = new MenuItem();
        addTo.textProperty().bind(Bindings.format("Add To In Progress"));

        MenuItem makeDone = new MenuItem();
        makeDone.textProperty().bind(Bindings.format("Add to done"));

        MenuItem deleteTask = new MenuItem();
        deleteTask.textProperty().bind(Bindings.format("Delete task"));

        MenuItem show = new MenuItem();
        show.textProperty().bind(Bindings.format("Show addnotation"));

        MenuItem editingToDo = new MenuItem();
        editingToDo.textProperty().bind(Bindings.format("Edit"));

        editingToDo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ifInProgress = false;
                editingIndex = ToDoList.getSelectionModel().getSelectedIndex();
                editToDo();
                ToDoList.setItems(todoList);
                ToDoList.refresh();
            }
        });

        show.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int buff = ToDoList.getSelectionModel().getSelectedIndex();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Addnotation");
                alert.setHeaderText("Let see what you have to do");
                alert.setContentText(todoList.get(buff).toAlert());


                alert.showAndWait();
            }
        });

        MenuItem showInProgress = new MenuItem();
        showInProgress.textProperty().bind(Bindings.format("Show addnotation"));

        showInProgress.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int buff = inProgressList.getSelectionModel().getSelectedIndex();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Addnotation");
                alert.setHeaderText("Let see what you have to do");
                alert.setContentText(inProgressTasks.get(buff).toAlert());

                alert.showAndWait();
            }
        });


        MenuItem showInDone = new MenuItem();
        showInDone.textProperty().bind(Bindings.format("Show addnotation"));

        showInDone.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int buff = doneList.getSelectionModel().getSelectedIndex();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Addnotation");
                alert.setHeaderText("Let see what you have to do");
                alert.setContentText(doneTasks.get(buff).toAlert());

                alert.showAndWait();
            }
        });


        addTo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int buff1 = ToDoList.getSelectionModel().getSelectedIndex();
                Task temp = todoList.get(buff1);
                inProgressTasks.add(temp);
                todoList.remove(buff1);
                ToDoList.refresh();

            }

        });

        makeDone.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int buff2 = ToDoList.getSelectionModel().getSelectedIndex();
                doneTasks.add(todoList.get(buff2));
                todoList.remove(buff2);
                ToDoList.refresh();
            }
        });

        deleteTask.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int buff3 = ToDoList.getSelectionModel().getSelectedIndex();
                todoList.remove(buff3);
                ToDoList.refresh();
            }
        });


        //In Progress context menu


        tasksContext.getItems().addAll(addTo, makeDone, deleteTask, show, editingToDo);


        MenuItem editInProgress = new MenuItem();
        editInProgress.textProperty().bind(Bindings.format("Edit"));


        editInProgress.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ifInProgress = true;
                editingIndex = inProgressList.getSelectionModel().getSelectedIndex();
                editToDo();
                inProgressList.setItems(inProgressTasks);
                inProgressList.refresh();
            }
        });

        ContextMenu inProgressContext = new ContextMenu();
        MenuItem addToDone = new MenuItem();
        addToDone.textProperty().bind(Bindings.format("Add to done"));
        addToDone.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int buff4 = inProgressList.getSelectionModel().getSelectedIndex();
                Task temp = inProgressTasks.get(buff4);
                doneTasks.add(temp);
                inProgressTasks.remove(temp);
                inProgressList.refresh();
            }
        });

        MenuItem deleteTaskFromInProgress = new MenuItem();
        deleteTaskFromInProgress.textProperty().bind(Bindings.format("Delete"));


        deleteTaskFromInProgress.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int buff5 = inProgressList.getSelectionModel().getSelectedIndex();
                Task temp = inProgressTasks.get(buff5);
                inProgressTasks.remove(temp);
                inProgressList.refresh();
            }
        });

        inProgressContext.getItems().addAll(addToDone, deleteTaskFromInProgress, showInProgress, editInProgress);

        //Done context menu

        ContextMenu doneContext = new ContextMenu();
        MenuItem deleteFromDone = new MenuItem();

        deleteFromDone.textProperty().bind(Bindings.format("Delete"));

        deleteFromDone.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int buff6 = doneList.getSelectionModel().getSelectedIndex();
                Task temp = doneTasks.get(buff6);
                doneTasks.remove(temp);
                doneList.refresh();
            }
        });

        doneContext.getItems().addAll(deleteFromDone, showInDone);

        ToDoList.setContextMenu(tasksContext);

        inProgressList.setContextMenu(inProgressContext);
        doneList.setContextMenu(doneContext);
        ToDoList.setCellFactory(new ToDoCellFactory());
        inProgressList.setCellFactory(new ToDoCellFactory());
        doneList.setCellFactory(new ToDoCellFactory());
        ToDoList.setItems(todoList);
        inProgressList.setItems(inProgressTasks);
        doneList.setItems(doneTasks);
    }


}
