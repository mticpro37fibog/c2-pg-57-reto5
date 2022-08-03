import controlador.Interprete;
import vista.GUI;

public class Reto5 {
    public static void main(String[] args) {
        try {
            // GUI.start(new Interprete("reto5.db"));
            GUI.start(new Interprete("reto5"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
