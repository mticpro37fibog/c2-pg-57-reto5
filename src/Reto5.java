import controlador.Interprete;
import vista.GUI;

public class Reto5 {
    public static void main(String[] args) {
        try {
            GUI.start(new Interprete("reto4.db"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
