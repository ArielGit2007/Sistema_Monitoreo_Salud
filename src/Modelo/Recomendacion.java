package Modelo;

public class Recomendacion {
    public String RcIMC(double IMC) {

        if (IMC < 18.5) {
            return "Bajo peso: Se recomienda aumentar la ingesta calórica con alimentos nutritivos y realizar ejercicios de fuerza para ganar masa muscular.";
        } else if (IMC >= 18.5 && IMC < 25) {
            return "Peso normal: Mantén una dieta equilibrada y continúa con tu rutina de ejercicios para mantener un estilo de vida saludable.";
        } else if (IMC >= 25 && IMC < 30) {
            return "Sobrepeso: Se recomienda reducir la ingesta calórica, aumentar la actividad física y adoptar hábitos alimenticios más saludables.";
        } else {
            return "Obesidad: Es importante consultar a un profesional de la salud para desarrollar un plan de pérdida de peso que incluya cambios en la dieta, " +
                    "ejercicio regular y posiblemente tratamiento médico.";
        }
    }
public String RcConsumoAgua(boolean agua,Persona per){
if(agua){
    return "Tu consumo de agua es adecuado. Continúa hidratándote correctamente, especialmente durante y después de la actividad física.";
}
else{
    return "Tu consumo de agua es insuficiente. Asegúrate de beber suficiente agua a lo largo del día, especialmente antes, durante y después " +
            "de la actividad física para mantener una buena hidratación. Tu ingesta de agua debe como minimo ser de"+per.med.consumoObjetivoAgua;
}
}
    public String RcHorasSueño(double horasSueño, int edad) {
        if (horasSueño < 6) {
            return "Insuficiente: Duermes muy pocas horas. Se recomienda dormir entre 7-9 horas diarias. " +
                    "La falta de sueño puede afectar tu salud mental, física y tu capacidad de concentración. " +
                    "Intenta establecer una rutina de sueño regular.";
        } else if (horasSueño >= 6 && horasSueño < 7) {
            return "Poco: Estás cerca del mínimo recomendado. Se sugiere aumentar tu sueño a 7-9 horas diarias " +
                    "para optimizar tu recuperación y bienestar.";
        } else if (horasSueño >= 7 && horasSueño <= 9) {
            return "Óptimo: Tu tiempo de sueño es adecuado. Mantén esta rutina de 7-9 horas diarias para asegurar " +
                    "una buena recuperación y salud física y mental.";
        } else {
            return "Excesivo: Duermes más de lo recomendado. Aunque el descanso es importante, dormir demasiado " +
                    "puede indicar fatiga o problemas de salud. Consulta a un profesional si esto persiste.";
        }
    }

}
