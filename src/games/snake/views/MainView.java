package games.snake.views;

import javax.swing.*;
import java.awt.*;

public class MainView {

    private int screenHeight, screenWidth;

    private JFrame frame;
    private JPanel mainPanel, gameFieldPanel;

    private GameFieldView gameFieldView;

    public MainView() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        screenHeight = (int) screenSize.getHeight();
        screenWidth = (int) screenSize.getWidth();

//      Creating frame
        frame = new JFrame();
        addComponentsToPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Snake game");
        frame.setLocationRelativeTo(null);
        frame.setLocation(((screenWidth / 2) - (screenWidth / 4)), ((screenHeight / 2) - (screenHeight / 4)));


        //      Display frame
        frame.pack();
        frame.setVisible(true);

    }

    private void addComponentsToPane() {
        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        gameFieldView = new GameFieldView();
        gameFieldView.setPreferredSize(new Dimension(screenWidth / 3, screenHeight / 2));
        gameFieldView.setBorder(BorderFactory.createLineBorder(Color.black));


        gameFieldPanel = new JPanel();
        LayoutManager overlay = new OverlayLayout(gameFieldPanel);
        gameFieldPanel.setLayout(overlay);
        gameFieldPanel.setBackground(Color.WHITE);
        gameFieldPanel.add(gameFieldView);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 0;
        mainPanel.add(gameFieldPanel, c);


        frame.add(mainPanel);
    }
}
