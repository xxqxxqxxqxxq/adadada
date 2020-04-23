package com.xxq.filemanager.gui.controller;

import com.xxq.FileClient;
import com.xxq.filemanager.gui.view.OtherView;
import com.xxq.filemanager.springJavafxSupport.FXMLController;
import com.xxq.filemanager.util.OpenPDF;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static com.xxq.filemanager.springJavafxSupport.GUIState.getHostServices;

/**
 * @ClassName :OtherController
 * @Description:
 * @Author xxq
 * @Date 2020/4/13  19:20
 * @Version V1.0
 **/
@FXMLController
public class OtherController implements Initializable {
    @Autowired
    OtherView otherView;
    @FXML
    private AnchorPane A_Main;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public  void show(){
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("0-3", 13),
                new PieChart.Data("3-9", 25),
                new PieChart.Data("9-12", 10),
                new PieChart.Data("12-18", 11),
                new PieChart.Data("18-24", 11));

        //Creating a Pie chart
        PieChart pieChart = new PieChart(pieChartData);

        //Setting the title of the Pie chart
        pieChart.setTitle("访问时间段");

        //setting the direction to arrange the data
        pieChart.setClockwise(true);

        //Setting the length of the label line
        pieChart.setLabelLineLength(50);

        //Setting the labels of the pie chart visible
        pieChart.setLabelsVisible(true);

        //Setting the start angle of the pie chart
        pieChart.setStartAngle(180);

        //Creating a Group object
        Group root = new Group(pieChart);
        root.setLayoutX(500);
        //Creating a scene object
        Scene scene = new Scene(root, 600, 400);
        A_Main.getChildren().add(root);
        //Setting title to the Stage
//        stage.setTitle("Pie chart");
//
//        //Adding scene to the stage
//        stage.setScene(scene);
//
//        //Displaying the contents of the stage
//        stage.show();
    }

    public void lineChart(){
        //Defining the x axis

        NumberAxis xAxis = new NumberAxis(2, 12, 2);

        xAxis.setLabel("月份");



        //Defining the y axis

        NumberAxis yAxis = new NumberAxis   (0, 350, 50);

        yAxis.setLabel("访问量");



        //Creating the line chart

        LineChart linechart = new LineChart(xAxis, yAxis);



        //Prepare XYChart.Series objects by setting data

        XYChart.Series series = new XYChart.Series();

        series.setName("流量");



        series.getData().add(new XYChart.Data(2, 15));

        series.getData().add(new XYChart.Data(4, 30));

        series.getData().add(new XYChart.Data(6, 60));

        series.getData().add(new XYChart.Data(8, 120));

        series.getData().add(new XYChart.Data(10, 240));

        series.getData().add(new XYChart.Data(12, 300));



        //Setting the data to Line chart

        linechart.getData().add(series);



        //Creating a Group object

        Group root = new Group(linechart);

        A_Main.getChildren().add(root);



    }
    @FXML
    public void loadPdf() {
        Button btn = new Button();
        btn.setText("Load PDF");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(111111);

               OpenPDF openPDF = new OpenPDF();
               openPDF.open();
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);
        Scene scene = FileClient.getScene();
        scene.setRoot(root);
        Stage stage = FileClient.getStage();
        stage.setScene(scene);
    }

}
