import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MyColorChooser extends JPanel
{
    Color color = Color.RED;

    public MyColorChooser()
    {
        JLabel label = new JLabel();
        label.setOpaque(true);
        label.setBackground(color);
        label.setBorder(new CompoundBorder(new LineBorder(Color.lightGray), new EmptyBorder(15,15,15,15)));

        DefaultTableModel defaultTableModel = new DefaultTableModel(5,20);
        JTable colorTable = new JTable(defaultTableModel);

        colorTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer()
        {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
            {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                component.setBackground(generateColors(105)[column * 5 + row]);

                if(isSelected && hasFocus)
                {
                    label.setBackground(component.getBackground());
                }

                return component;
            }
        });

        JButton confirmButton = new JButton(" Confirm");

        confirmButton.addActionListener(e ->
        {
            color = label.getBackground();
            Window.contentPane.remove(this);
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);
        panel.add(confirmButton, BorderLayout.PAGE_END);

        JSeparator separator = new JSeparator();
        separator.setBackground(Color.lightGray);

        setLayout(new BorderLayout());

        add(panel, BorderLayout.LINE_END);
        add(colorTable, BorderLayout.CENTER);
        add(separator, BorderLayout.PAGE_START);
    }

    public Color[] generateColors(int n)
    {
        Color[] colors = new Color[n];
        for(int i = 0; i < n; i++)
        {
            colors[i] = Color.getHSBColor((float) i / (float) n, 0.85f, 1.0f);
        }
        return colors;
    }
}
