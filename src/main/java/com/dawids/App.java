package com.dawids;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        var trianglePane = new Pane();
        var orderLabel = new Label("Enter an order:");
        var orderTextField = new TextField();
        var bottomBox = new HBox(orderLabel, orderTextField);
        var borderPane = new BorderPane();

        orderTextField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                drawTriangle(trianglePane, Integer.parseInt(orderTextField.getText()));
            }
        });
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setSpacing(5);

        borderPane.setPadding(new Insets(10));
        borderPane.setCenter(trianglePane);
        borderPane.setBottom(bottomBox);

        var scene = new Scene(borderPane, 700, 700);
        stage.setScene(scene);
        stage.show();
    }

    private void drawTriangle(Pane trianglePane, int order) {
        if (order <= 0 || trianglePane == null) {
            throw new IllegalArgumentException();
        }
        trianglePane.getChildren().clear();
        drawTriangle(trianglePane, 100.0, 450.0, 600.0, 450.0, 350.0, 17.0, order);
    }

    private void drawTriangle(Pane trianglePane,
                              double x1,
                              double y1,
                              double x2,
                              double y2,
                              double x3,
                              double y3,
                              int order) {
        if (order == 0) {
            return;
        }
        Line[] lines = {
                new Line(x1, y1, x2, y2),
                new Line(x2, y2, x3, y3),
                new Line(x3, y3, x1, y1),
                };
        trianglePane.getChildren().addAll(lines);
        double midX1 = (x1 + x2) / 2.0;
        double midY1 = (y1 + y2) / 2.0;
        double midX2 = (x2 + x3) / 2.0;
        double midY2 = (y2 + y3) / 2.0;
        double midX3 = (x3 + x1) / 2.0;
        double midY3 = (y3 + y1) / 2.0;
        drawTriangle(trianglePane, x1, y1, midX1, midY1, midX3, midY3, order - 1);
        drawTriangle(trianglePane, midX1, midY1, x2, y2, midX2, midY2, order - 1);
        drawTriangle(trianglePane, midX3, midY3, midX2, midY2, x3, y3, order - 1);
    }

    public static void main(String[] args) {
        launch();
    }

}