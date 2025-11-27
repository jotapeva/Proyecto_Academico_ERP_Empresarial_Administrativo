package dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Area {

    private String nombre;
    private double presupuesto_anual;
    private String descripcion;
    private ArrayList<Empleado> lst_empleados;

    public Area(String nombre) {
        this.nombre = nombre;
        this.lst_empleados = new ArrayList<>();
    }

    public Area(String nombre, String descripcion, double presupuesto_anual) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.presupuesto_anual = presupuesto_anual;
        this.lst_empleados = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPresupuesto_anual() {
        return presupuesto_anual;
    }

    public void setPresupuesto_anual(double presupuesto_anual) {
        this.presupuesto_anual = presupuesto_anual;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<Empleado> getLst_empleados() {
        return lst_empleados;
    }

    public void setLst_empleados(ArrayList<Empleado> lst_empleados) {
        this.lst_empleados = lst_empleados;
    }

    //Alta de empleado (agregar a la lista y actualizar presupuesto)
    public void agregarEmpleado(Empleado e) {
        lst_empleados.add(e);
    }

    //Baja de empleado (remover de la lista)
    public void quitarEmpleado(Empleado e) {
        if (lst_empleados != null) {
            lst_empleados.remove(e);
        }
    }

    //Saber si el área tiene empleados (para opción Baja de áreas)
    public boolean tieneEmpleados() {
        return lst_empleados != null && !lst_empleados.isEmpty();
    }

    //Calcular el total del presupuesto ya asignado a salarios anuales
    public double getPresupuestoAsignado() {
        double total = 0;
        if (lst_empleados != null) {
            for (Empleado e : lst_empleados) {
                total += e.getSalario_mensual() * 12;
            }
        }
        return total;
    }

    //Obtener cuánto presupuesto queda disponible
    public double getPresupuestoDisponible() {
        return presupuesto_anual - getPresupuestoAsignado();
    }

    public boolean hayPresupuestoSuficiente(double salarioMensual) {
        double costoAnualNuevo = salarioMensual * 12;
        return getPresupuestoAsignado() + costoAnualNuevo <= presupuesto_anual;
    }

    //ordenar empleados por nombre
    public void ordenarEmpleadosPorNombre() {
        if (lst_empleados != null) {
            Collections.sort(lst_empleados, Comparator.comparing(Empleado::getNombre));
        }
    }

    public boolean moverEmpleadoA(Empleado e, Area destino, int mesMovimiento) {
        boolean resultado = false;
        int mesesRestantes = 12 - mesMovimiento + 1;
        double montoRestante = e.getSalario_mensual() * mesesRestantes;
        if (destino.getPresupuestoDisponible() >= montoRestante) {
            this.quitarEmpleado(e);
            destino.agregarEmpleado(e);
            e.setArea(destino);
            resultado = true;
        }
        return resultado;
    }

    @Override
    public String toString() {
        return nombre + " - " + descripcion + " ($" + presupuesto_anual + ")";
    }

    public double porcentajePresupuestoUsado() {
        double resultado = 0;
        if (presupuesto_anual != 0) {
            resultado = (getPresupuestoAsignado() / presupuesto_anual) * 100;
        }
        return resultado;
    }

    // Getter usado por todas las pantallas y el renderer
    public double getPorcentajeAsignado() {
        return porcentajePresupuestoUsado();
    }

}
