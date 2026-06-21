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


}
