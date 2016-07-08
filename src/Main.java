import controller.Controller;
import view.View;

/**
 * Created by Artem on 06.07.16.
 */
public class Main {
    public static void main(String[] args) {
        View view=new View();
        Controller controller=new Controller(view);
        controller.processUser();
    }

}
