package games.snake.views;

import games.snake.controllers.DrawFoodPiece;
import games.snake.controllers.DrawSnakePiece;

import javax.swing.*;
import java.awt.*;

public class GameFinishedView {


    private int screenHeight, screenWidth;

    private JFrame frame;
    private JPanel mainPanel;
    private JLabel finishText;


    public GameFinishedView() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        screenHeight = (int) screenSize.getHeight();
        screenWidth = (int) screenSize.getWidth();

//      Creating frame
        frame = new JFrame();
        addComponentsToPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Congrats !");
        frame.setLocationRelativeTo(null);
        frame.setLocation(((screenWidth / 2) - (screenWidth / 4)), ((screenHeight / 2) - (screenHeight / 4)));


        //      Display frame
        frame.pack();
        frame.setVisible(true);


    }

    private void addComponentsToPane() {
        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        finishText = new JLabel("Some finish text");
        finishText.setFont(new Font("serif", Font.BOLD, 35));

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 0;
        mainPanel.add(finishText, c);


        frame.add(mainPanel);
    }

}
