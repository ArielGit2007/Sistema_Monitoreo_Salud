package Negocio;

import Modelo.Persona;
import Modelo.historial;
import Modelo.reporteSalud;

public class sistemaSalud {
    private Persona personaActual = new Persona();
    private historial hist = new historial();

    public void cargarDatosGuardados() {
        try {
            hist.cargarEnArchivo("historial.dat"); // sistema carga todo

            if (hist.Personas != null && !hist.Personas.isEmpty()) {
                personaActual = hist.Personas.get(hist.Personas.size() - 1);
            } else {
                personaActual = null;
            }

        } catch (Exception e) {
            System.out.println("Error cargando datos: " + e.getMessage());
            personaActual = null;
        }
    }
    public void registrarPersona(String nombre, String apellido, int edad,
                                 double altura, double peso, String genero,
                                 int nivelEstres, double horasSueño, int frecuenciaCardiaca,
                                 double consumoAgua, String tipoActividad, int duracionMinutos) {
        //Registro de datos personales
        personaActual = new Persona();
        personaActual.setNombre(nombre);
        personaActual.setApellido(apellido);
        personaActual.setEdad(edad);
        personaActual.setAltura(altura);
        personaActual.setPeso(peso);
        personaActual.setGenero(genero);

        //Registro de datos medicos
        personaActual.med.setNivelEstres(nivelEstres);
        personaActual.med.setHorasSueño(horasSueño);
        personaActual.med.setFrecuenciaCardiaca(frecuenciaCardiaca);
        personaActual.med.setConsumoAgua(consumoAgua);

        //Registro de actividad física
        personaActual.actF.tipoActividad = tipoActividad;
        personaActual.actF.duracionMinutos = duracionMinutos;
        personaActual.actF.CaloriasQuemadas(personaActual.getPeso());

        //Agregar al historial
        hist.agregarDatos(personaActual);

    }

    public Persona getPersonaActual() {
        return personaActual;
    }

    public void actualizarPersona(double altura, double peso, int nivelEstres,
                                  double horasSueño, int frecuenciaCardiaca,
                                  double consumoAgua, String tipoActividad, int duracionMinutos) {
        if (personaActual == null) {
            return;
        }

        personaActual.setAltura(altura);
        personaActual.setPeso(peso);

        personaActual.med.setNivelEstres(nivelEstres);
        personaActual.med.setHorasSueño(horasSueño);
        personaActual.med.setFrecuenciaCardiaca(frecuenciaCardiaca);
        personaActual.med.setConsumoAgua(consumoAgua);

        personaActual.actF.tipoActividad = tipoActividad;
        personaActual.actF.duracionMinutos = duracionMinutos;
        personaActual.actF.CaloriasQuemadas(personaActual.getPeso());

        // Agregar al historial como nuevo registro actualizado
        hist.agregarDatos(personaActual);
    }

    public String generarReporte() {
        if (personaActual == null) {
            return null;
        }

        reporteSalud reporte = new reporteSalud(personaActual);
        return reporte.generarReporte();
    }

    public historial obtenerHistorial() {
        if (personaActual == null) {
            return null;
        }
        return hist;
    }



}
