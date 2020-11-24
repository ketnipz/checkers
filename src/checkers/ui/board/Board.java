package checkers.ui.board;

import javafx.geometry.Insets;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Board extends GridPane {

    /**
     * Access to white chip instances.
     */
    private static Chip[] whiteChips;

    /**
     * Access to black chip instances.
     */
    private static Chip[] blackChips;

    /**
     * Access to habitable cell instances.
     */
    private static Cell[] cells;

    /**
     * Access to king crown instances.
     */
    private static Circle[] crowns;

    /**
     * The size of the outer pane container.
     */
    private final int v;

    /**
     * Board UI element for checkers game.
     *
     * @param v The size of the outer pane container.
     */
    public Board(int v) {
        super();

        blackChips = new Chip[50];
        whiteChips = new Chip[50];
        cells = new Cell[50];
        crowns = new Circle[50];

        this.v = v;
        this.setMinSize(v, v);

        this.reset();
    }

    /**
     * @return White chips array.
     */
    public static Chip[] getWhiteChips() {
        return whiteChips;
    }

    /**
     * @return Black chips array.
     */
    public static Chip[] getBlackChips() {
        return blackChips;
    }

    /**
     * @return Cells array.
     */
    public static Cell[] getCells() {
        return cells;
    }

    /**
     * @return King crowns array.
     */
    public static Circle[] getCrowns() {
        return crowns;
    }

    /**
     * @return Size of board.
     */
    public int getV() {
        return v;
    }

    /**
     * Returns a white chip by board index.
     *
     * @param i Board index of chip.
     * @return  Chip instance.
     */
    public Chip getWhiteChip(int i) {
        return whiteChips[i];
    }

    /**
     * Returns a black chip by board index.
     *
     * @param i Board index of chip.
     * @return  Chip instance.
     */
    public Chip getBlackChip(int i) {
        return blackChips[i];
    }

    /**
     * Returns a cell by board index.
     *
     * @param i Board index of cell.
     * @return  Cell instance.
     */
    public Cell getCell(int i) {
        return cells[i];
    }

    /**
     * Returns a king crown by board index.
     *
     * @param i Board index of cell.
     * @return  Crown instance.
     */
    public Circle getCrown(int i) {
        return crowns[i];
    }

    /**
     * Resets the board state.
     */
    public void reset() {
        int cellIdx = 0;
        for(int col = 0; col < 10; col++) {
            for(int row = 0; row < 10; row++) {
                boolean isHabitableCell = (row + (col % 2)) % 2 == 1;
                Pane cell;
                if(isHabitableCell) {
                    cell = new Cell();
                    cell.setBackground(new Background(new BackgroundFill(
                            Color.web("#a67d5d"),
                            CornerRadii.EMPTY,
                            Insets.EMPTY
                    )));
                    cell.setPrefSize(v/10f, v/10f);

                    Chip blackChip = new Chip(v/20f, v/20f, v*0.04,
                            Color.web("#382723"));
                    Chip whiteChip = new Chip(v/20f, v/20f, v*0.04,
                            Color.web("#e8d9b0"));

                    Circle crown = new Circle(v/20f, v/20f, v*0.04);
                    crown.setStroke(Color.GOLD);
                    crown.setFill(null);
                    crown.setStrokeWidth(5);
                    DropShadow ds = new DropShadow();
                    ds.setOffsetX(0);
                    ds.setOffsetY(0);
                    ds.setColor(Color.GOLD);
                    crown.setEffect(ds);

                    whiteChip.setVisible(false);
                    blackChip.setVisible(false);
                    crown.setVisible(false);

                    Text t = new Text(2, v/10f - 2, String.format("%d",
                            cellIdx+1));
                    t.setFont(new Font(10));

                    cell.getChildren().addAll(crown, blackChip, whiteChip, t);

                    blackChips[cellIdx] = blackChip;
                    whiteChips[cellIdx] = whiteChip;
                    cells[cellIdx] = (Cell)cell;
                    crowns[cellIdx] = crown;

                    cellIdx += 1;
                } else {
                    cell = new Pane();
                    cell.setBackground(new Background(new BackgroundFill(
                            Color.web("#e8d0aa"),
                            CornerRadii.EMPTY,
                            Insets.EMPTY
                    )));
                    cell.setPrefSize(v/10f, v/10f);
                }

                this.add(cell, row, col);
            }
        }
    }
}
