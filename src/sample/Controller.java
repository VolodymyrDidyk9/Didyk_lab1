package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class Controller {

    @FXML
    private Label l1, l2, l3, l4, l5, l6, l7, l8, l9, check, res1, res2, res3;

    @FXML
    private Button start, next;

    @FXML
    private TextField t1, t2, t4, t3, t5, t6, t7, t8, t9;

    static int[][] val = new int[3][3];

    public void initialize() {
        start.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                if(res1.isVisible()) {
                    res1.setVisible(false);
                    res2.setVisible(false);
                    res3.setVisible(false);
                }

                if(check.isVisible())
                    check.setVisible(false);

                val[0][0] = Integer.parseInt(t1.getText());
                val[0][1] = Integer.parseInt(t2.getText());
                val[0][2] = Integer.parseInt(t3.getText());
                val[1][0] = Integer.parseInt(t4.getText());
                val[1][1] = Integer.parseInt(t5.getText());
                val[1][2] = Integer.parseInt(t6.getText());
                val[2][0] = Integer.parseInt(t7.getText());
                val[2][1] = Integer.parseInt(t8.getText());
                val[2][2] = Integer.parseInt(t9.getText());

                if(Main.check(new Node(val))) {
                    l1.setText(t1.getText());
                    l2.setText(t2.getText());
                    l3.setText(t3.getText());
                    l4.setText(t4.getText());
                    l5.setText(t5.getText());
                    l6.setText(t6.getText());
                    l7.setText(t7.getText());
                    l8.setText(t8.getText());
                    l9.setText(t9.getText());

                    Astar.start(new Node(val));
                    if(!res1.isVisible()) {
                        res1.setVisible(true);
                        res2.setVisible(true);
                        res3.setVisible(true);
                    }
                    res1.setText("Результат було знайдено");
                    res2.setText("за " + (double)Astar.time/1000 + " секунд,");
                    res3.setText("на " + Astar.h + " кроці.");
                }
                else{
                    check.setVisible(true);
                }
            }
        });

        next.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    val = Astar.getNext();
                    l1.setText(Integer.toString(val[0][0]));
                    l2.setText(Integer.toString(val[0][1]));
                    l3.setText(Integer.toString(val[0][2]));
                    l4.setText(Integer.toString(val[1][0]));
                    l5.setText(Integer.toString(val[1][1]));
                    l6.setText(Integer.toString(val[1][2]));
                    l7.setText(Integer.toString(val[2][0]));
                    l8.setText(Integer.toString(val[2][1]));
                    l9.setText(Integer.toString(val[2][2]));
                }catch (Exception e){}
            }
        });
    }
}
