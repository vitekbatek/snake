import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Java. Classic Game Snake
 * Class Cell: minimal building element
 *
 * @author Sergey Iryupin & Viktor Sidorenko
 * @version dated Jun 27, 2019
 */

public class Cell {
    protected int fig;
    protected int x, y;                       // object coordinates
    protected int size;                       // object size in px
    protected Color color;                    // object color


    public Cell(int fig, int x, int y, int size, Color color) {
        set(fig, x, y);                            // init coordinates
        this.size = size;                     // init size
        this.color = color;                   // init color
    }

    public void set(int fig, int x, int y) {           // set coordinates
        this.fig = fig;
        this.x = x;
        this.y = y;
    }

    public int getX() {                       // get the X coordinate
        return x;
    }

    public int getY() {                       // get the Y coordinate
        return y;
    }

    public void paint(Graphics2D g) {           // object rendering
        g.setColor(color);
        switch (fig) {
            case 2:
                g.fillRect(x * size, y * size,        // upper left corner
                        size, size);
                break;// width and height
            case 1:
                g.fillOval(x * size, y * size,        // upper left corner
                        size, size);
                break;// width and height
        }
    }
}