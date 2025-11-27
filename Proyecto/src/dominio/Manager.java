package dominio;

import java.util.ArrayList;

public class Manager extends Empleado {

    private int antiguedad;
    private ArrayList<Empleado> empleadosACargo;

    public Manager(String nombre, String ci, String celular, int antiguedad) {
        super(nombre, ci, celular);
        this.antiguedad = antiguedad;
        this.empleadosACargo = new ArrayList<>();
    }

    //Agregar empleado a cargo
    public void agregarEmpleado(Empleado e) {
        if (e != null && !empleadosACargo.contains(e)) {
            empleadosACargo.add(e);
        }
    }

    //Cantidad de empleados supervisados
    public int cantidadEmpleadosACargo() {
        return empleadosACargo.size();
    }

    public String listarEmpleadosACargo() {
        String resultado = getNombre() + " no tiene empleados a cargo.";

        if (!empleadosACargo.isEmpty()) {
            resultado = "Empleados a cargo de " + getNombre() + ":\n";
            for (Empleado e : empleadosACargo) {
                resultado = resultado + " - " + e.getNombre() + " (CI: " + e.getCi() + ")\n";
            }
        }
        return resultado;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    public ArrayList<Empleado> getEmpleadosACargo() {
        return empleadosACargo;
    }

    public void setEmpleadosACargo(ArrayList<Empleado> empleadosACargo) {
        this.empleadosACargo = empleadosACargo;
    }

    @Override
    public String toString() {
        return getNombre() + " (antig: " + antiguedad + " años, empleados a cargo: " + empleadosACargo.size() + ")";
    }
}
