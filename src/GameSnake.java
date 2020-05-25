import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Java. Classic Game Snake
 *
 * @author Sergey Iryupin & Viktor Sidorenko
 * @version 0.8.0 dated Jun 27, 2019
 */

public class GameSnake extends JFrame {

    final String TITLE_OF_PROGRAM = "Игра 'Змейка'";
    final String GAME_OVER_MSG = "Игра окончена. Вы проиграли";
    final String GAME_WIN_MSG = "Игра окончена. Вы выиграли";
    final int CELL_SIZE = 20;                  // size of cell in pix
    final int CANVAS_WIDTH = 30;               // width in cells
    final int CANVAS_HEIGHT = 30;              // height in cells
    final int START_SNAKE_SIZE = 6;            // initialization data
    final int START_SNAKE_X = CANVAS_WIDTH / 2;  //   for
    final int START_SNAKE_Y = CANVAS_HEIGHT / 2; //   snake
    Color snakeColor;
    Color foodColor;
    final Color POISON_COLOR = Color.red;
    static final int KEY_LEFT = 37;             // codes
    static final int KEY_UP = 38;               //   of
    static final int KEY_RIGHT = 39;            //   cursor
    static final int KEY_DOWN = 40;             //   keys
    int snakeSpeed = 1;                // snake delay in milliseconds
    int snakeDelay = 150;
    boolean gameOver = false;                   // a sign game is over or not
    boolean gameWin = false;

    Canvas canvas;                   // canvas object for rendering (drawing)
    Snake snake;                     // declare a snake object
    Food food;                       // declare a food object
    Poison poison;                   // declare a poison object

    public static void main(String[] args) {    // starting method
        new GameSnake().game();                 // create an object and call game()
    }

    public GameSnake() {
        //setTitle(TITLE_OF_PROGRAM + " : " + START_SNAKE_SIZE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        canvas = new Canvas();
        canvas.setBackground(Color.ORANGE);
        canvas.setPreferredSize(new Dimension(
                CELL_SIZE * CANVAS_WIDTH - 10,
                CELL_SIZE * CANVAS_HEIGHT - 10));

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                snake.setDirection(e.getKeyCode());
            }
        });
        add(canvas);                 // add a panel for rendering
        pack();                      // bringing the window to the required size
        setLocationRelativeTo(null); // the window will be in the center
        setResizable(false);         // prohibit window resizing
        setVisible(true);            // make the window visible
    }

    private void game() {            // method containing game cycle
        snake = new Snake(           // creating snake object
                START_SNAKE_X,
                START_SNAKE_Y,
                START_SNAKE_SIZE,
                KEY_RIGHT, this);//*/
        food = new Food(this);       // food object
        poison = new Poison(this);   // poison object

        while (!(gameOver || gameWin)) {          // game cycle while NOT gameOver
            switch (snakeSpeed) {
                case 1:
                    snakeDelay = 150;
                    snakeColor = Color.WHITE;
                    foodColor = Color.WHITE;
                    break;
                case 2:
                    snakeDelay = 140;
                    snakeColor = Color.WHITE;
                    foodColor = Color.lightGray;
                    break;
                case 3:
                    snakeDelay = 130;
                    snakeColor = Color.lightGray;
                    foodColor = Color.lightGray;
                    break;
                case 4:
                    snakeDelay = 125;
                    snakeColor = Color.lightGray;
                    foodColor = Color.GRAY;
                    break;
                case 5:
                    snakeDelay = 120;
                    snakeColor = Color.GRAY;
                    foodColor = Color.GRAY;
                    break;
                case 6:
                    snakeDelay = 115;
                    snakeColor = Color.GRAY;
                    foodColor = Color.DARK_GRAY;
                    break;
                case 7:
                    snakeDelay = 110;
                    snakeColor = Color.DARK_GRAY;
                    foodColor = Color.DARK_GRAY;
                    break;
                case 8:
                    snakeDelay = 105;
                    snakeColor = Color.DARK_GRAY;
                    foodColor = Color.BLACK;
                    break;
                case 9:
                    snakeDelay = 102;
                    snakeColor = Color.BLACK;
                    foodColor = Color.BLACK;
                    break;
                case 10:
                    snakeDelay = 100;
                    snakeColor = Color.BLACK;
                    foodColor = Color.BLACK;
                    break;
            }
            snake.move();            // snake move
            if (food.isEaten()) {    // if the snake ate the food
                food.appear();       //   show food in new place
                poison.add();        //   add new poison point
            }
            canvas.repaint();        // repaint panel/window


            sleep(snakeDelay);      // to make delay in milliseconds
        }
        if (gameOver)
            JOptionPane.showMessageDialog(GameSnake.this, GAME_OVER_MSG);
        if (gameWin)
            JOptionPane.showMessageDialog(GameSnake.this, GAME_WIN_MSG);
    }

    private void sleep(long ms) {    // method for suspending
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isCoordinatesBusy(int x, int y) {
        return snake.isInSnake(x, y);// || poison.isPoison(x, y);
    }

    class Canvas extends JPanel {    // class for rendering (drawing)
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D g2D = (Graphics2D) g;
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            snake.paint(g2D);
            food.paint(g2D);
            poison.paint(g2D);
        }
    }
}