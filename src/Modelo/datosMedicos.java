package Modelo;

import java.io.Serializable;

public class datosMedicos implements Serializable {


    private int nivelEstres;
    private double horasSueño;
    private int frecuenciaCardiaca;
    private double consumoAgua;


    public double calcularIMC(Persona per){
        return per.getPeso() / (per.getAltura() * per.getAltura());
    }
    public boolean consumoAguaAdecuado(actividadFisica actF, Persona per){
        double consumoObjetivo= per.getPeso() * 0.035;
        int horasAct=Math.round(actF.duracionMinutos/60);
        if ((actF.tipoActividad.equals("Ligera")|| actF.tipoActividad.equals("Moderada"))&& horasAct >=1 ){
            for (int i=0; i<horasAct; i++){
                consumoObjetivo+=0.5;
            }
        }
        else if(actF.tipoActividad.equals("Intensa")&& horasAct >=1){
            for (int i=0; i<horasAct; i++){
                consumoObjetivo+=1.0;
            }
        }
        return consumoAgua<consumoObjetivo;

    }

    public int getNivelEstres() {return nivelEstres;}

    public void setNivelEstres(int nivelEstres) {this.nivelEstres = nivelEstres;}

    // Getter y Setter para horasSueño
    public double getHorasSueño() {return horasSueño;}

    public void setHorasSueño(double horasSueño) {this.horasSueño = horasSueño;}

    // Getter y Setter para frecuenciaCardiaca
    public int getFrecuenciaCardiaca() {return frecuenciaCardiaca;}

    public void setFrecuenciaCardiaca(int frecuenciaCardiaca) {this.frecuenciaCardiaca = frecuenciaCardiaca;}

    // Getter y Setter para consumoAgua
    public double getConsumoAgua() {return consumoAgua;}

    public void setConsumoAgua(double consumoAgua) {this.consumoAgua = consumoAgua;}

}

