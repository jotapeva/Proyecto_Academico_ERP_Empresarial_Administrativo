# ERP de Gestión de Áreas, Empleados y Movimientos (Con reportes) 
### Proyecto académico – Facultad de Ingeniería – Universidad ORT Uruguay

Este proyecto implementa un sistema ERP simplificado para la gestión de **áreas**, **managers**, **empleados** y **movimientos internos** dentro de una empresa.  
Fue desarrollado en **Java (Swing + Ant)** como parte del obligatorio del curso, siguiendo los requerimientos funcionales y técnicos establecidos por la cátedra.

---

## 📌 Funcionalidades principales

### 🟦 Pantalla inicial
- Muestra nombres y números de estudiante.
- Opción para iniciar:
  - Sistema vacío  
  - Sistema cargado desde archivo  
  - Sistema con datos ficticios  
- Se muestra durante 3–5 segundos.

---

## 📁 Datos ficticios incluidos

### Áreas predeterminadas
| Área | Presupuesto | Descripción |
|------|-------------|-------------|
| Personal | 100.000 USD | Reclutamiento, promociones, gestión de cargos |
| RRHH | 80.000 USD | Organigrama, equipos, relacionamiento interno |
| Seguridad | 120.000 USD | Seguridad física y digital |
| Comunicaciones | 20.000 USD | Comunicaciones internas y externas |
| Marketing | 95.000 USD | Publicidad, redes y campañas |

### Managers predeterminados
- Ana Martínez – CI 4.568.369-1 – Antigüedad: 10  
- Ricardo Morales – CI 3.214.589-3 – Antigüedad: 4  
- Laura Torales – CI 3.589.257-5 – Antigüedad: 1  
- Juan Pablo Zapata – CI 4.555.197-7 – Antigüedad: 5  

---

## 🟦 Gestión de Áreas
- **Alta:** nombre único, descripción, presupuesto.  
- **Baja:** solo si no tiene empleados.  
- **Modificación:** únicamente la descripción.  
- **Movimiento de empleados:**  
  - Selección del mes.  
  - Control de presupuesto disponible.  
  - Muestra cuánto cuesta transferir al empleado:  
    ```
    costo = salario * meses_restantes_incluyendo_actual
    ```
  - Ajusta los presupuestos al confirmar.

---

## 🟩 Gestión de Managers
- **Alta:** nombre, cédula única (validación global), celular, antigüedad.  
- **Baja:** solo sin empleados.  
- **Modificación:** cambiar celular.  
- Muestra cantidad de empleados asociados.

---

## 🟨 Gestión de Empleados
- **Alta:**  
  - Nombre  
  - Cédula única  
  - Celular  
  - CV (texto)  
  - Salario mensual  
  - Manager  
  - Área  
- Verificación de presupuesto antes de aceptar el alta.  
- **CV** guardado en carpeta `cvs/` con formato:  CVxxxxxxxx.txt

- ---

## 🟪 Reportes

### 📘 Reporte Inteligente (IA – Gemini)
- Se usa **gemini-2.5-flash** de Google AI.  
- Prompt incluye:
- Descripción del área origen  
- Descripción del área destino  
- CV del empleado  
- La IA genera un análisis de ventajas/desventajas del movimiento.  
- Indicador visual: icono reloj → icono check.  
- API KEY requerida: ERP_API_KEY

---

### 📊 Reporte de Estado de Áreas
- Lista de todas las áreas ordenadas por **porcentaje de presupuesto asignado** (descendente).  
- Colores:
- > 90% → rojo  
- 70–90% → amarillo  
- < 70% → gris  
- Seleccionando un área:
- Se muestran empleados en 3 columnas.  
- Colores de botones según salario (escala azul).  
- Al hacer clic aparece toda la información del empleado.

---

### 📄 Reporte de Movimientos
- Tabla con: mes, origen, destino, empleado.  
- Ordenado por mes.  
- Filtros por mes, área, empleado.  
- Exportación a CSV:
- Usuario elige nombre y ubicación del archivo.

---

## 💾 Persistencia
- El sistema guarda automáticamente el estado completo al cerrarse.  
- Los datos se guardan en JSON mediante **Gson**.  

---

## ▶ Ejecución

Este proyecto se entrega con **dos carpetas separadas**:

- **/Proyecto/** → Código fuente completo (NetBeans + Ant)  
- **/Ejecutable/** → JAR listo para usar + librerías necesarias  

### ✔ Requisitos
- **Java 17 o superior**  
- *Opcional, solo para el reporte inteligente con IA:*  
  Definir la variable de entorno:
ERP_API_KEY=tu_api_key_de_gemini

markdown
Copiar código
> Si esta variable no está configurada, la aplicación funciona igual;  
> simplemente el reporte basado en IA permanecerá deshabilitado.

### ✔ Modo de uso (carpeta Ejecutable)
1. Abrir la carpeta **/Ejecutable/**  
2. Ejecutar el archivo:
java -jar Obligatorio2_Juan_Pablo_Curbelo_-_Juan_Ignacio_Podesta.jar
(o doble clic si el sistema lo permite)

Todas las dependencias necesarias ya están incluidas en esta carpeta.

### Codigo Fuente
En la carpeta Proyecto se ve el codigo fuente separado por cada carpeta (paquete) creada en el src
Se puede consultar y ver su funcionamiento interno

### UML
![Diagrama UML](/Proyecto/uml.png)

## 🧩 Tecnologías utilizadas
- **Java 17**  
- **Swing (GUI estilo Windows clásico)**  
- **Gson**  
- **Google AI Gemini**  
- **Apache Ant**  
- **Fat JAR** con todas las dependencias embebidas

- ---

## 👥 Autores
**Juan Pablo Curbelo – Estudiante ORT**  

---

## 📄 Licencia
Uso estrictamente educativo (proyecto académico).
