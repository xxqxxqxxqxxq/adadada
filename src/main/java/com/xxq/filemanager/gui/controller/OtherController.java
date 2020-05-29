package com.xxq.filemanager.gui.controller;

import com.xxq.FileClient;
import com.xxq.filemanager.bean.DepartInfo;
import com.xxq.filemanager.gui.view.OtherView;
import com.xxq.filemanager.service.interfaceI.DepartService;
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
import java.text.DecimalFormat;
import java.util.List;
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
    @Autowired
    DepartService departService;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public  void show(Integer a,Integer b,Integer c,Integer d,Integer e){
        Integer m= a+b+c+d+e;
        System.out.println(m);
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("党群部门", Integer.parseInt(myPercent(a, m))),
                new PieChart.Data("行政部门",  Integer.parseInt(myPercent(b, m))),
                new PieChart.Data("教学单位",  Integer.parseInt(myPercent(c, m))),
                new PieChart.Data("科研单位",  Integer.parseInt(myPercent(d, m))),
                new PieChart.Data("党基层组织",  Integer.parseInt(myPercent(e, m))));

        //Creating a Pie chart
        PieChart pieChart = new PieChart(pieChartData);

        //Setting the title of the Pie chart
        pieChart.setTitle("部门百分比");

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
    public static String myPercent(int y, int z) {
        String baifenbi = "";// 接受百分比的值
        double baiy = y * 1.0;
        double baiz = z * 1.0;
        double fen = baiy / baiz;
        // NumberFormat nf = NumberFormat.getPercentInstance(); 注释掉的也是一种方法
        // nf.setMinimumFractionDigits( 2 ); 保留到小数点后几位
        DecimalFormat df1 = new DecimalFormat("##.00%"); // ##.00%
        // 百分比格式，后面不足2位的用0补齐
        // baifenbi=nf.format(fen);
        baifenbi = df1.format(fen);
        String substring = baifenbi.substring(0, 2);
        return substring;
    }


    public void lineChart(){
        //Defining the x axis
        List<DepartInfo> departInfos = departService.selectAllDepart();
        Integer a=null;
        Integer b=null;
        Integer c=null;
        Integer d=null;
        Integer e=null;
        for (DepartInfo departInfo:departInfos ) {
            switch (departInfo.getDepartName()){
                case "党群部门" :
                    a = departInfo.getAmount();
                    break;
                case "行政部门":
                    b = departInfo.getAmount();
                    break;
                case "教学单位":
                    c = departInfo.getAmount();
                    break;
                case "科研单位":
                    d = departInfo.getAmount();
                    break;
                case "党基层组织":
                    e = departInfo.getAmount();
                    break;
            }

        }
        NumberAxis xAxis = new NumberAxis(1, 5, 1);

        xAxis.setLabel("部门编号");



        //Defining the y axis

        NumberAxis yAxis = new NumberAxis   (0, 350, 50);

        yAxis.setLabel("部门人数");



        //Creating the line chart

        LineChart linechart = new LineChart(xAxis, yAxis);



        //Prepare XYChart.Series objects by setting data

        XYChart.Series series = new XYChart.Series();

        series.setName("人数统计");




        series.getData().add(new XYChart.Data(1, a));

        series.getData().add(new XYChart.Data(2, b));

        series.getData().add(new XYChart.Data(3, c));

        series.getData().add(new XYChart.Data(4, d));

        series.getData().add(new XYChart.Data(5, e));





        //Setting the data to Line chart

        linechart.getData().add(series);



        //Creating a Group object

        Group root = new Group(linechart);

        A_Main.getChildren().add(root);

        show(a,b,c,d,e);

    }
//    @FXML
//    public void loadPdf() {
//        Button btn = new Button();
//        btn.setText("Load PDF");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println(111111);
//
//               OpenPDF openPDF = new OpenPDF();
//               openPDF.open();
//            }
//        });
//
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        Scene scene = FileClient.getScene();
//        scene.setRoot(root);
//        Stage stage = FileClient.getStage();
//        stage.setScene(scene);
//    }

}
