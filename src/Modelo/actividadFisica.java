package Modelo;

import java.io.Serializable;

public class actividadFisica implements Serializable {
public String tipoActividad;
public int duracionMinutos;
public int caloriasQuemadas;

public void CaloriasQuemadas(double peso){
    double metPorActividad = 0;

    switch(tipoActividad.toLowerCase()) {
        case "caminar":
            metPorActividad = 3.5;
            break;
        case "correr":
            metPorActividad = 9.8;
            break;
        case "ciclismo":
            metPorActividad = 8.0;
            break;
        case "natacion":
            metPorActividad = 8.0;
            break;
        case "gym":
            metPorActividad = 6.0;
            break;
        default:
            metPorActividad = 4.0;
    }
    this.caloriasQuemadas=(int) (metPorActividad * peso * (duracionMinutos / 60.0));
}

}

