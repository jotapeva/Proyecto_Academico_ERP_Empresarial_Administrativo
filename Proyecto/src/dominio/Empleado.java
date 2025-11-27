package dominio;

public class Empleado {

    private String nombre;
    private String ci;
    private double salario_mensual;
    private Manager manager;
    private String curriculum; // ruta al archivo CV
    private Area area;
    private String celular;

    // Constructor mínimo
    public Empleado(String ci) {
        this.ci = ci;
    }

    // Constructor completo
    public Empleado(String nombre, String ci, double salario_mensual, Manager manager,
            String curriculum, Area area, String celular) {
        this.nombre = nombre;
        this.ci = ci;
        this.salario_mensual = salario_mensual;
        this.manager = manager;
        this.curriculum = curriculum;
        this.area = area;
        this.celular = celular;
    }

    public Empleado(String nombre, String ci, String celular) {
        this.nombre = nombre;
        this.ci = ci;
        this.celular = celular;
    }

    // Getters / setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public double getSalario_mensual() {
        return salario_mensual;
    }

    public void setSalario_mensual(double salario_mensual) {
        this.salario_mensual = salario_mensual;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public String getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    @Override
    public String toString() {
        return nombre + " - $" + salario_mensual
                + " - Área: " + (area != null ? area.getNombre() : "Sin área");
    }

    public double calcularSalarioAnual() {
        return salario_mensual * 12;
    }
}
