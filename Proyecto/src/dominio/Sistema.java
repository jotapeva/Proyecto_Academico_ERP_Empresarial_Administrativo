package dominio;

import com.google.gson.JsonArray;
import java.util.ArrayList;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import io.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;

public class Sistema {

    private ArrayList<Empleado> lstEmpleados = new ArrayList<>();
    private ArrayList<Area> lstAreas = new ArrayList<>();
    private ArrayList<Manager> lstManagers = new ArrayList<>();
    private ArrayList<String> lstMovimientos = new ArrayList<>();
    // de esta manera(private static String archivo = "sistema.txt";) el .jar no funciona.
    // lo hacemos asi el sistema porque si no el .jar no funcina
    private static String archivo = System.getProperty("user.home") + File.separator + "sistema.txt";
    private PropertyChangeSupport escuchar = new PropertyChangeSupport(this);

    public void agregarListener(PropertyChangeListener l) {
        escuchar.addPropertyChangeListener(l);
    }

    public void quitarListener(PropertyChangeListener l) {
        escuchar.removePropertyChangeListener(l);
    }

    public ArrayList<Area> getAreas() {
        return lstAreas;
    }

    public ArrayList<Empleado> getEmpleados() {
        return lstEmpleados;
    }

    public ArrayList<Manager> getManagers() {
        return lstManagers;
    }

    public ArrayList<String> getLstMovimientos() {
        return lstMovimientos;
    }

    public ArrayList<Empleado> getEmpleadosPorSalario() {
        ArrayList<Empleado> lista = new ArrayList<>(lstEmpleados);
        lista.sort((e1, e2) -> Double.compare(e2.getSalario_mensual(), e1.getSalario_mensual()));
        return lista;
    }

    public Area buscarAreaPorNombre(String nombre) {
        Area encontrada = null;
        for (Area a : lstAreas) {
            if (a.getNombre().equalsIgnoreCase(nombre)) {
                encontrada = a;
            }
        }
        return encontrada;
    }

    public Empleado buscarEmpleadoPorNombre(String nombre) {
        Empleado encontrado = null;
        for (Empleado e : lstEmpleados) {
            if (e.getNombre().equalsIgnoreCase(nombre)) {
                encontrado = e;
            }
        }
        return encontrado;
    }

    public Empleado buscarEmpleadoPorCI(String ci) {
        Empleado encontrado = null;
        for (Empleado e : lstEmpleados) {
            if (e.getCi().equals(ci)) {
                encontrado = e;
            }
        }
        return encontrado;
    }

    public Manager buscarManagerPorCI(String ci) {
        Manager encontrado = null;
        for (Manager m : lstManagers) {
            if (m.getCi().equals(ci)) {
                encontrado = m;
            }
        }
        return encontrado;
    }

    public boolean agregarArea(Area a) {
        boolean agregado = false;
        if (a != null && buscarAreaPorNombre(a.getNombre()) == null) {
            ArrayList<Area> viejo = new ArrayList<>(lstAreas);
            lstAreas.add(a);
            escuchar.firePropertyChange("areas", viejo, lstAreas);
            agregado = true;
        }
        return agregado;
    }

    public boolean eliminarArea(Area a) {
        boolean eliminado = false;
        if (a != null && !a.tieneEmpleados()) {
            ArrayList<Area> viejo = new ArrayList<>(lstAreas);
            lstAreas.remove(a);
            escuchar.firePropertyChange("areas", viejo, lstAreas);
            eliminado = true;
        }
        return eliminado;
    }

    public boolean agregarEmpleado(Empleado e) {
        boolean agregado = false;
        if (e != null) {
            boolean existeEmpleado = buscarEmpleadoPorCI(e.getCi()) != null;
            boolean existeManager = buscarManagerPorCI(e.getCi()) != null;

            if (!existeEmpleado && !existeManager) {
                ArrayList<Empleado> viejo = new ArrayList<>(lstEmpleados);
                lstEmpleados.add(e);
                escuchar.firePropertyChange("empleados", viejo, lstEmpleados);
                agregado = true;
            }
        }
        return agregado;
    }

    public boolean altaEmpleado(Empleado e) {
        boolean alta = false;
        if (e != null) {
            boolean nombreValido = !e.getNombre().trim().isEmpty();
            boolean ciValida = !e.getCi().trim().isEmpty();
            boolean salarioValido = e.getSalario_mensual() > 0;
            boolean existeEmpleado = buscarEmpleadoPorCI(e.getCi()) != null;
            boolean existeManager = buscarManagerPorCI(e.getCi()) != null;
            if (nombreValido && ciValida && salarioValido && !existeEmpleado && !existeManager) {
                ArrayList<Empleado> viejo = new ArrayList<>(lstEmpleados);
                lstEmpleados.add(e);
                escuchar.firePropertyChange("empleados", viejo, lstEmpleados);
                alta = true;
            }
        }
        return alta;
    }

    public boolean agregarEmpleadoArea(Empleado e, Area a) {
        boolean agregado = false;
        if (e != null && a != null) {
            boolean hayPresupuesto = a.hayPresupuestoSuficiente(e.getSalario_mensual());
            if (hayPresupuesto) {
                ArrayList<Area> viejoAreas = new ArrayList<>(lstAreas);
                ArrayList<Empleado> viejoEmp = new ArrayList<>(lstEmpleados);
                a.agregarEmpleado(e);
                e.setArea(a);
                escuchar.firePropertyChange("empleados", viejoEmp, lstEmpleados);
                escuchar.firePropertyChange("areas", viejoAreas, lstAreas);
                agregado = true;
            }
        }
        return agregado;
    }

    public boolean altaManager(Manager m) {
        boolean exito = false;

        if (m != null
                && m.getNombre() != null && !m.getNombre().trim().isEmpty()
                && m.getCi() != null && !m.getCi().trim().isEmpty()
                && m.getAntiguedad() >= 0
                && buscarEmpleadoPorCI(m.getCi()) == null
                && buscarManagerPorCI(m.getCi()) == null) {

            ArrayList<Manager> viejoMgr = new ArrayList<>(lstManagers);
            ArrayList<Empleado> viejoEmp = new ArrayList<>(lstEmpleados);

            lstManagers.add(m);
            lstEmpleados.add(m);
            exito = true;

            escuchar.firePropertyChange("managers", viejoMgr, lstManagers);
            escuchar.firePropertyChange("empleados", viejoEmp, lstEmpleados);
        }

        return exito;
    }

    public boolean agregarManagerArea(Manager m, Area a) {
        boolean agregado = false;
        if (m != null && a != null) {
            boolean managerYaTieneArea = m.getArea() != null;
            boolean hayPresupuesto = a.hayPresupuestoSuficiente(m.getSalario_mensual());
            if (!managerYaTieneArea && hayPresupuesto) {
                ArrayList<Area> viejoAreas = new ArrayList<>(lstAreas);
                a.agregarEmpleado(m);
                m.setArea(a);
                escuchar.firePropertyChange("areas", viejoAreas, lstAreas);
                agregado = true;
            }
        }
        return agregado;
    }

    public boolean eliminarManager(Manager m) {
        boolean eliminado = false;
        if (m != null) {
            ArrayList<Manager> viejoMgr = new ArrayList<>(lstManagers);
            ArrayList<Empleado> viejoEmp = new ArrayList<>(lstEmpleados);
            boolean estabaEnManagers = lstManagers.remove(m);
            if (estabaEnManagers) {
                lstEmpleados.remove(m);

                if (m.getArea() != null) {
                    m.getArea().quitarEmpleado(m);
                }
                escuchar.firePropertyChange("managers", viejoMgr, lstManagers);
                escuchar.firePropertyChange("empleados", viejoEmp, lstEmpleados);
                eliminado = true;
            }
        }
        return eliminado;
    }

    public void moverEmpleadoArea(Empleado e, Area destino, String mes) {

        Area origen = e.getArea();
        String nombreOrigen;
        if (origen == null) {
            nombreOrigen = "-";
        } else {
            nombreOrigen = origen.getNombre();
        }
        String movimiento = LocalDate.now() + ";"
                + mes + ";"
                + nombreOrigen + ";"
                + destino.getNombre() + ";"
                + e.getNombre() + ";";
        lstMovimientos.add(movimiento);
        ArrayList<Area> viejoAreas = new ArrayList<>(lstAreas);
        ArrayList<Empleado> viejoEmp = new ArrayList<>(lstEmpleados);
        if (origen != null) {
            origen.quitarEmpleado(e);
        }
        destino.agregarEmpleado(e);
        e.setArea(destino);
        escuchar.firePropertyChange("empleados", viejoEmp, lstEmpleados);
        escuchar.firePropertyChange("areas", viejoAreas, lstAreas);
        escuchar.firePropertyChange("movimientos", null, movimiento);
    }
    
    public int totalEmpleadosEnArea(Area a) {
        return a.getLst_empleados().size();
    }

    public ArrayList<Manager> getManagersPorAntiguedad() {
        ArrayList<Manager> orden = new ArrayList<>(lstManagers);
        orden.sort((m1, m2) -> Integer.compare(m2.getAntiguedad(), m1.getAntiguedad()));
        return orden;
    }

    public ArrayList<Manager> getManagersSinEmpleados() {
        ArrayList<Manager> sin = new ArrayList<>();
        for (Manager m : lstManagers) {
            if (m.getEmpleadosACargo() == null || m.getEmpleadosACargo().isEmpty()) {
                sin.add(m);
            }
        }
        return sin;
    }

    public ArrayList<Area> getAreaSinEmpleado() {
        ArrayList<Area> sin = new ArrayList<>();
        for (Area a : lstAreas) {
            if (!a.tieneEmpleados()) {
                sin.add(a);
            }
        }
        return sin;

    }

    public void precargarDatos() {

        Area a1 = new Area("Personal", "Reclutamiento y gestión de cargos", 100000.00);
        Area a2 = new Area("RRHH", "Relaciones internas y gestión de equipos", 80000.00);
        Area a3 = new Area("Seguridad", "Seguridad informática y física", 120000.00);
        Area a4 = new Area("Comunicaciones", "Protocolos y comunicación externa", 20000.00);
        Area a5 = new Area("Marketing", "Publicidad y gestión de redes", 95000.00);
        lstAreas.add(a1);
        lstAreas.add(a2);
        lstAreas.add(a3);
        lstAreas.add(a4);
        lstAreas.add(a5);
        Manager m1 = new Manager("Ana Martínez", "4.568.369-1", "099123456", 10);
        m1.setSalario_mensual(60.00);
        Manager m2 = new Manager("Ricardo Morales", "3.214.589-3", "094121212", 4);
        m2.setSalario_mensual(50.00);
        Manager m3 = new Manager("Laura Torales", "3.589.257-5", "099654321", 1);
        m3.setSalario_mensual(450.00);
        Manager m4 = new Manager("Juan Pablo Zapata", "4.555.197-7", "099202020", 5);
        m4.setSalario_mensual(500.00);
        altaManager(m1);
        altaManager(m2);
        altaManager(m3);
        altaManager(m4);
        agregarManagerArea(m1, a1);
        agregarManagerArea(m2, a1);
        agregarManagerArea(m3, a1);
        agregarManagerArea(m4, a2);
    }

    public String generarReporteInteligente(Area origen, Area destino, Empleado e) {
        String resultado = "";
        try {
            String apiKey = System.getenv("ERP_API_KEY");
            if (apiKey == null || apiKey.isEmpty()) {
                resultado = "Error: No se encontró la variable de entorno ERP_API_KEY.";
            } else {
                String curriculum;
                if (e.getCurriculum() == null) {
                    curriculum = "Sin CV disponible";
                } else {
                    curriculum = e.getCurriculum();
                }
                String prompt
                        = "Genera un reporte comparando el traslado de un empleado del área '"
                        + origen.getNombre() + "' (" + origen.getDescripcion() + ") "
                        + "hacia el área '" + destino.getNombre() + "' (" + destino.getDescripcion() + ").\n"
                        + "Usa el siguiente texto del CV del empleado:\n---\n"
                        + curriculum + "\n---\n"
                        + "Analiza ventajas y desventajas del cambio considerando competencias, cultura del área y riesgos.\n"
                        + "Devuelve el texto en español.";

                JsonObject req = new JsonObject();
                JsonArray contents = new JsonArray();
                JsonObject item = new JsonObject();
                JsonArray parts = new JsonArray();
                JsonObject part = new JsonObject();
                part.addProperty("text", prompt);
                parts.add(part);
                item.add("parts", parts);
                contents.add(item);
                req.add("contents", contents);

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=" + apiKey))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(req.toString()))
                        .build();

                HttpClient client = HttpClient.newHttpClient();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();

                if (json.has("error")) {
                    resultado = "Error de la API: " + json.getAsJsonObject("error").get("message").getAsString();
                } else {
                    String texto
                            = json.getAsJsonArray("candidates")
                                    .get(0).getAsJsonObject()
                                    .getAsJsonObject("content")
                                    .getAsJsonArray("parts")
                                    .get(0).getAsJsonObject()
                                    .get("text").getAsString();

                    resultado = texto;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            resultado = "Error al generar el reporte: " + ex.getMessage();
        }
        return resultado;
    }

    public void guardarSistema() {
        ArchivoGrabacion arch = new ArchivoGrabacion(archivo);
        arch.grabarLinea("[AREAS]");
        for (Area a : lstAreas) {
            String empleados = "";
            ArrayList<Empleado> lista = a.getLst_empleados();
            int i = 0;
            while (i < lista.size()) {
                empleados += lista.get(i).getCi();
                if (i < lista.size() - 1) {
                    empleados += ",";
                }
                i++;
            }

            String linea = a.getNombre() + ";"
                    + a.getDescripcion() + ";"
                    + a.getPresupuesto_anual() + ";"
                    + empleados;

            arch.grabarLinea(linea);
        }

        arch.grabarLinea("[MANAGERS]");
        for (Manager m : lstManagers) {
            String areaNombre = "";
            if (m.getArea() != null) {
                areaNombre = m.getArea().getNombre();
            }
            String linea = m.getNombre() + ";"
                    + m.getCi() + ";"
                    + m.getCelular() + ";"
                    + m.getSalario_mensual() + ";"
                    + m.getAntiguedad() + ";"
                    + areaNombre;

            arch.grabarLinea(linea);
        }

        arch.grabarLinea("[EMPLEADOS]");
        for (Empleado e : lstEmpleados) {
            boolean esManager = false;
            if (e instanceof Manager) {
                esManager = true;
            }
            if (!esManager) {

                String areaNombre = "";
                if (e.getArea() != null) {
                    areaNombre = e.getArea().getNombre();
                }
                String managerCi = "";
                if (e.getManager() != null) {
                    managerCi = e.getManager().getCi();
                }

                String linea = e.getNombre() + ";"
                        + e.getCi() + ";"
                        + e.getCelular() + ";"
                        + e.getSalario_mensual() + ";"
                        + e.getCurriculum() + ";"
                        + areaNombre + ";"
                        + managerCi;

                arch.grabarLinea(linea);
            }
        }
        arch.grabarLinea("[MOVIMIENTOS]");
        for (String mov : lstMovimientos) {
            arch.grabarLinea(mov);
        }
        arch.cerrar();
    }

    public void cargarSistema() {

        File f = new File(archivo);
        ArchivoLectura arch = null;
        String seccion = "";
        ArrayList<Area> areasTemp = new ArrayList<>();
        ArrayList<String> empleadosTemp = new ArrayList<>();
        lstAreas.clear();
        lstManagers.clear();
        lstEmpleados.clear();
        lstMovimientos.clear();

        if (f.exists()) {
            arch = new ArchivoLectura(archivo);
            while (arch.hayMasLineas()) {
                String linea = arch.linea();
                if (linea == null) {
                    linea = "";
                }
                linea = linea.trim();
                if (linea.equals("")) {
                } else if (linea.startsWith("[")) {
                    seccion = linea;
                } else {
                    String[] p = linea.split(";", -1);
                    switch (seccion) {

                        case "[AREAS]":
                            String nombre = p[0];
                            String desc = p[1];
                            double presupuesto = Double.parseDouble(p[2]);
                            Area a = new Area(nombre, desc, presupuesto);
                            a.getLst_empleados().clear();
                            lstAreas.add(a);
                            areasTemp.add(a);
                            String listaCIs = "";
                            if (p.length > 3) {
                                listaCIs = p[3];
                            }
                            empleadosTemp.add(listaCIs);
                            break;

                        case "[MANAGERS]":
                            String nombreM = p[0];
                            String ciM = p[1];
                            String celM = p[2];
                            double salarioM = Double.parseDouble(p[3]);
                            int antig = Integer.parseInt(p[4]);
                            String nomAreaM = p[5];

                            Manager m = new Manager(nombreM, ciM, celM, antig);
                            m.setSalario_mensual(salarioM);

                            Area areaM = buscarAreaPorNombre(nomAreaM);
                            if (areaM != null) {
                                m.setArea(areaM);
                            }

                            lstManagers.add(m);

                            Empleado repetido = buscarEmpleadoPorCI(ciM);
                            if (repetido == null) {
                                lstEmpleados.add(m);
                            }
                            break;

                        case "[EMPLEADOS]":
                            String nombreE = p[0];
                            String ciE = p[1];
                            String celE = p[2];
                            double salarioE = Double.parseDouble(p[3]);
                            String cv = p[4];
                            String nomAreaE = p[5];
                            String ciManager = p[6];

                            Manager yaEsManager = buscarManagerPorCI(ciE);
                            if (yaEsManager == null) {
                                Area areaE = buscarAreaPorNombre(nomAreaE);
                                Manager man = buscarManagerPorCI(ciManager);
                                Empleado e = new Empleado(nombreE, ciE, salarioE, man, cv, areaE, celE);
                                lstEmpleados.add(e);
                            }
                            break;

                        case "[MOVIMIENTOS]":
                            lstMovimientos.add(linea);
                            break;
                    }
                }
            }
            arch.cerrar();
        }
        for (Area a : lstAreas) {
            a.getLst_empleados().clear();
        }

        for (int i = 0; i < areasTemp.size(); i++) {
            Area a = areasTemp.get(i);
            String cadena = empleadosTemp.get(i);
            if (cadena != null) {
                cadena = cadena.trim();
            }
            if (cadena != null && !cadena.equals("")) {
                String[] partes = cadena.split(",");
                for (int j = 0; j < partes.length; j++) {
                    String ci = partes[j].trim();
                    Empleado e = buscarEmpleadoPorCI(ci);
                    if (e != null) {
                        boolean yaEsta = false;
                        for (Empleado emp : a.getLst_empleados()) {
                            if (emp.getCi().equals(ci)) {
                                yaEsta = true;
                            }
                        }
                        if (!yaEsta) {
                            a.getLst_empleados().add(e);
                            e.setArea(a);
                        }
                    }
                }
            }
        }
        escuchar.firePropertyChange("empleados", null, lstEmpleados);
        escuchar.firePropertyChange("managers", null, lstManagers);
        escuchar.firePropertyChange("areas", null, lstAreas);
    }

}
