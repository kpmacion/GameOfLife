import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Window extends JFrame
{
    static JPanel contentPane = new JPanel();
    static JRadioButton draw = new JRadioButton("Draw");

    GameField gameField;
    GameOfLife game;
    Template template;
    MyColorChooser colorChooser;
    MyFile file;
    public Window(GameOfLife game) throws Exception
    {
        this.game = game;
        this.colorChooser = new MyColorChooser();
        this.gameField = new GameField(game, colorChooser);
        this.template = new Template(game.arr.length);;

        setTitle("Game of life");
        setSize(new Dimension(730, 400));
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void createGUI()
    {
        JSlider speedSlider = new JSlider(SwingConstants.VERTICAL,0,500,300);
        speedSlider.setMajorTickSpacing(100);
        speedSlider.setPaintTicks(true);
        speedSlider.addChangeListener(e -> game.speed = Math.abs(speedSlider.getValue() - 500));

        Border buttonBorder = new CompoundBorder(new LineBorder(Color.WHITE), new EmptyBorder(5, 7, 5, 7));

        JButton simulateButton = new JButton("Simulate");
        JButton resetButton = new JButton("Reset");

        simulateButton.setForeground(Color.BLACK);
        simulateButton.setBackground(new Color(141,253,152));
        simulateButton.setBorder(buttonBorder);

        simulateButton.addActionListener(e ->
        {
            simulateButton.setEnabled(false);
            game.isRunning = true;
        });

        resetButton.setForeground(Color.BLACK);
        resetButton.setBackground(Color.pink);
        resetButton.setBorder(buttonBorder);

        resetButton.addActionListener(e ->
        {
            simulateButton.setEnabled(true);
            game.isRunning = false;
            game.clear();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2,1));
        buttonPanel.add(simulateButton);
        buttonPanel.add(resetButton);

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BorderLayout());
        menuPanel.add(speedSlider, BorderLayout.CENTER);
        menuPanel.add(buttonPanel, BorderLayout.PAGE_START);

        JMenuItem gliderTemplate = new JMenuItem("Glider");
        gliderTemplate.addActionListener(e -> game.arr = template.getGlider());

        JMenuItem sunTemplate = new JMenuItem("Sun");
        sunTemplate.addActionListener(e -> game.arr = template.getSun());

        JMenuItem gunTemplate = new JMenuItem("Gun");
        gunTemplate.addActionListener(e -> game.arr = template.getGun());

        JMenuItem fountainTemplate = new JMenuItem("Fountain");
        fountainTemplate.addActionListener(e -> game.arr = template.getFountain());

        JMenuItem randomTemplate = new JMenuItem("Random");
        randomTemplate.addActionListener(e -> game.arr = template.getRandom());

        JMenuItem linesTemplate = new JMenuItem("Lines");
        linesTemplate.addActionListener(e -> game.arr = template.getLines());

        JMenuItem squareTemplate = new JMenuItem("Square");
        squareTemplate.addActionListener(e -> game.arr = template.getSquare());

        JMenu templates = new JMenu("Templates");
        templates.add(randomTemplate);
        templates.add(squareTemplate);
        templates.add(gunTemplate);
        templates.add(sunTemplate);
        templates.add(linesTemplate);
        templates.add(fountainTemplate);
        templates.add(gliderTemplate);

        JMenuItem colors = new JMenuItem("Colors");
        colors.addActionListener(e ->
        {
            contentPane.remove(colorChooser);
            contentPane.add(colorChooser, BorderLayout.PAGE_END);
        });

        JMenuItem open = new JMenuItem("\uD83D\uDCC2" + " Open");
        open.addActionListener(e -> game.arr = MyFile.loadFromFile(game.arr));

        JMenuItem save = new JMenuItem("\uD83D\uDCBE" + " Save");
        save.addActionListener(e -> MyFile.saveToFile(game.arr));

        JMenu file = new JMenu("File");
        file.add(open);
        file.add(save);

        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton delete = new JRadioButton("Delete");
        buttonGroup.add(draw);
        buttonGroup.add(delete);
        draw.setSelected(true);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(file);
        menuBar.add(templates);
        menuBar.add(colors);
        menuBar.add(draw);
        menuBar.add(delete);
        menuBar.setBorder(new CompoundBorder(new LineBorder(Color.lightGray), new EmptyBorder(0, 0, 0, 0)));

        contentPane.setLayout(new BorderLayout());
        contentPane.add(gameField, BorderLayout.CENTER);
        contentPane.add(menuPanel, BorderLayout.LINE_END);

        setContentPane(contentPane);
        setJMenuBar(menuBar);
    }
}