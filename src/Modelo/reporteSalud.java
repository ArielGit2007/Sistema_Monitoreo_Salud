package Modelo;

import java.time.LocalDate;

public class reporteSalud {

    private Persona persona;
    private LocalDate fechaReporte;
    private Recomendacion recomendaciones;
    private int puntajeSalud=0;
    private String estadoGeneral;

    public reporteSalud(Persona persona) {
        this.persona = persona;
        this.fechaReporte = LocalDate.now();
        this.recomendaciones = new Recomendacion();
        calcularEstadoGeneral(persona);
    }
    public void calcularEstadoGeneral(Persona persona) {
        //Factor 1: IMC
        if (persona.med.calcularIMC(persona)<18.5){
            puntajeSalud+=8;
        }
        else if (persona.med.calcularIMC(persona)>=18.5&&persona.med.calcularIMC(persona)<=25){
            puntajeSalud+=16;
        }
        else if (persona.med.calcularIMC(persona)>=25&&persona.med.calcularIMC(persona)<=30){
            puntajeSalud+=8;
        }
       // Factor 2: estrés
        if (persona.med.getNivelEstres() <= 4) {
            puntajeSalud += 17;
        } else if (persona.med.getNivelEstres() <= 7) {
            puntajeSalud += 8;
        }
        // Factor 3: Sueño
        if(persona.med.getHorasSueño() >=7 && persona.med.getHorasSueño() <=9){
            puntajeSalud += 17;
        } else if (persona.med.getHorasSueño()>=5 && persona.med.getHorasSueño()<7) {
            puntajeSalud += 8;
        }
        //Factor 4: Actividad física
        if(persona.actF.duracionMinutos>=150){
            puntajeSalud += 17;
        }
        else if(persona.actF.duracionMinutos>=75){
            puntajeSalud += 8;
        }
        // Factor 5: Agua
        if(persona.med.consumoAguaAdecuado(persona.actF,persona)){
            puntajeSalud += 17;
        }
        else{
            puntajeSalud += 8;
        }
        // Factor 6: Frecuencia cardíaca
        if(persona.med.getFrecuenciaCardiaca()>=persona.med.frecuenciaMinima && persona.med.getFrecuenciaCardiaca()<=persona.med.frecuenciaMaxima){
            puntajeSalud += 17;
        }
        else if(persona.med.getFrecuenciaCardiaca()>=50 && persona.med.getFrecuenciaCardiaca()<=120){
            puntajeSalud += 8;
        }
        //Estado general
        if(puntajeSalud>=85){
            estadoGeneral="Excelente";
        }
        else if(puntajeSalud>=65){
            estadoGeneral="Bueno";
        }
        else if(puntajeSalud>=45){
        estadoGeneral="Regular";
        }
        else{
            estadoGeneral="Necesita mejorar";
        }
    }

    public String generarReporte(){
        StringBuilder reporte= new StringBuilder();

        //Datos personales
        reporte.append("DATOS PERSONALES:\n");
        reporte.append("Nombre: ").append(persona.getNombre()).append(" ").append(persona.getApellido()).append("\n");
        reporte.append("Edad: ").append(persona.getEdad()).append(" años\n");
        reporte.append("Género: ").append(persona.getGenero()).append("\n");
        reporte.append("Altura: ").append(persona.getAltura()).append(" m\n");
        reporte.append("Peso: ").append(persona.getPeso()).append(" kg\n");
        reporte.append("Fecha del Reporte: ").append(fechaReporte).append("\n\n");
        //Datos de salud
        reporte.append("MÉTRICAS DE SALUD:\n");
        double imc = persona.med.calcularIMC(persona);
        reporte.append("IMC: ").append(String.format("%.2f", imc)).append("\n");
        reporte.append("Frecuencia Cardíaca: ").append(persona.med.getFrecuenciaCardiaca()).append(" lpm\n");
        reporte.append("Nivel de Estrés: ").append(persona.med.getNivelEstres()).append("/10\n");
        reporte.append("Horas de Sueño: ").append(persona.med.getHorasSueño()).append(" horas\n");
        reporte.append("Consumo de Agua: ").append(persona.med.getConsumoAgua()).append(" L\n\n");
        //Actividad fisica
        reporte.append("ACTIVIDAD FÍSICA:\n");
        reporte.append("Tipo de Actividad: ").append(persona.actF.tipoActividad).append("\n");
        reporte.append("Duración: ").append(persona.actF.duracionMinutos).append(" minutos\n");
        reporte.append("Calorías Quemadas: ").append(persona.actF.caloriasQuemadas).append(" kcal\n\n");
        //Recomendaciones
        reporte.append("RECOMENDACIONES:\n");
        reporte.append("\n• IMC: ").append(recomendaciones.RcIMC(imc));
        reporte.append("\n• Sueño: ").append(recomendaciones.RcHorasSueño(persona.med.getHorasSueño(), persona.getEdad()));
        reporte.append("\n• Estrés: ").append(recomendaciones.RcNivelEstres(persona.med.getNivelEstres()));
        reporte.append("\n• Agua: ").append(recomendaciones.RcConsumoAgua(persona.med.consumoAguaAdecuado(persona.actF, persona), persona));
        reporte.append("\n• Actividad: ").append(recomendaciones.RcActividadFisica(persona.getEdad(), persona.actF.duracionMinutos));
        reporte.append("\n• Frecuencia Cardíaca: ").append(recomendaciones.RcFrecuenciaCardiaca(persona.med.getFrecuenciaCardiaca(), persona.med.frecuenciaMinima, persona.med.frecuenciaMaxima));
        //Estado general
        reporte.append("\n\nESTADO GENERAL: ").append(estadoGeneral);
        reporte.append("\nPUNTAJE DE SALUD: ").append(puntajeSalud).append("/100");

        return reporte.toString();
    }

}
