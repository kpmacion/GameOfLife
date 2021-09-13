import javax.swing.*;

public class Main implements Runnable
{
    Window window;
    GameOfLife game;

    public Main(Window window, GameOfLife game)
    {
        this.window = window;
        this.game = game;
    }

    public static void main(String[] args)
    {
        try
        {
            GameOfLife game = new GameOfLife(1000);
            Window window = new Window(game);

            new Thread(new Main(window, game)).start();
            SwingUtilities.invokeLater(window::createGUI);
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void run()
    {
        while (!Thread.currentThread().isInterrupted())
        {
            Window.contentPane.revalidate();
            Window.contentPane.repaint();

            if(game.isRunning)
            {
                game.rebuild();
                try
                {
                    Thread.sleep(game.speed);
                }
                catch (InterruptedException e)
                {
                    break;
                }
            }
        }
    }
}
