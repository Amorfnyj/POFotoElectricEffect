import controller.Controller;
import view.MainFrame;

/**
 * Created by Maksim Andrejewski on 18.06.2017.
 */
public class MainMVC {

    public static void main(String[] args) {

        MainFrame mainFrame = new MainFrame();
        Controller controller = new Controller(mainFrame);

        mainFrame.setVisible(true);
    }
}
