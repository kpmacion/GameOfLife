import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GameField extends JScrollPane
{
    MyColorChooser colorChooser;
    int[] lastSelectedCell;

    public GameField(GameOfLife game, MyColorChooser colorChooser)
    {
        this.colorChooser = colorChooser;
        this.lastSelectedCell = new int[]{game.arr.length + 1, game.arr.length + 1};

        DefaultTableModel tableModel = new DefaultTableModel(game.arr.length - GameOfLife.borderSize * 2, game.arr.length - GameOfLife.borderSize * 2)
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };

        JTable table = new JTable(tableModel);
        table.setPreferredSize(new Dimension(game.arr.length * 10, game.arr.length * 10));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setTableHeader(null);

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer()
        {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
            {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                component.setSize(new Dimension(10, 10));
                component.setBackground(Color.lightGray);

                if(Window.draw.isSelected() && isSelected && hasFocus && !(lastSelectedCell[0] == row && lastSelectedCell[1] == column))
                {
                    game.arr[row + GameOfLife.borderSize][column + GameOfLife.borderSize] = 1;
                    lastSelectedCell = new int[]{row, column};
                }
                else if(!Window.draw.isSelected() && isSelected && hasFocus)
                {
                    game.arr[row + GameOfLife.borderSize][column + GameOfLife.borderSize] = 0;
                }

                if(game.arr[row + GameOfLife.borderSize][column +  GameOfLife.borderSize] == 1)
                {
                    component.setBackground(colorChooser.color);
                }

                return component;
            }
        });

        setViewportView(table);
    }
}
