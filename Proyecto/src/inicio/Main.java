package inicio;

import dominio.*;
import interfaz.*;

public class Main {
    //public static Sistema s =new Sistema();
    public static void main(String[] args) {
       Sistema s =new Sistema();
       VentanaLogo v =new VentanaLogo(s);
       v.setVisible(true);

    }
    
}
