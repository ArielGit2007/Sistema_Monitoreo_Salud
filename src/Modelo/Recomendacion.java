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
            "de la actividad física para mantener una buena hidratación. Tu ingesta de agua debe como minimo ser de: "+ String.format("%.2f", per.med.consumoObjetivoAgua)+" litros diarios.";
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
    public String RcNivelEstres(int nivelEstres) {
        if (nivelEstres <= 2) {
            return "Estrés bajo: Tu nivel de estrés es muy bajo. Mantén tu estilo de vida relajado y " +
                    "continúa con actividades que te ayuden a mantenerte tranquilo.";
        } else if (nivelEstres >= 3 && nivelEstres <= 4) {
            return "Estrés moderado: Tu nivel de estrés es manejable. Se recomienda practicar técnicas " +
                    "de relajación como meditación, yoga o respiración profunda durante 10-15 minutos diarios.";
        } else if (nivelEstres >= 5 && nivelEstres <= 7) {
            return "Estrés elevado: Tu estrés está en niveles altos. Es importante tomar acciones: " +
                    "practica ejercicio regular (30 min/día), meditación, mantén un sueño adecuado y considera " +
                    "hablar con amigos o familia sobre lo que te preocupa.";
        } else if (nivelEstres >= 8 && nivelEstres <= 10) {
            return "Estrés muy elevado: Tu nivel de estrés es crítico. Se recomienda fuertemente que consultes " +
                    "con un profesional de salud mental o psicólogo. Mientras tanto, busca actividades relajantes, " +
                    "evita cafeína, aumenta tu actividad física y asegura dormir 7-9 horas diarias.";
        } else {
            return "Valor inválido: El nivel de estrés debe estar entre 0 y 10.";
        }
    }
    public String RcFrecuenciaCardiaca(int frecuenciaCardiaca, int frecuenciaMinima, int frecuenciaMaxima) {
        if (frecuenciaCardiaca < frecuenciaMinima) {
            return "Tu frecuencia cardíaca durante el ejercicio es baja. Esto puede indicar que tu nivel de esfuerzo es insuficiente para obtener beneficios cardiovasculares. " +
                    "Intenta aumentar la intensidad de tu actividad física para alcanzar la zona de frecuencia cardíaca recomendada.";
        } else if (frecuenciaCardiaca >= frecuenciaMinima && frecuenciaCardiaca <= frecuenciaMaxima) {
            return "Tu frecuencia cardíaca durante el ejercicio está en el rango recomendado. Esto indica que estás trabajando a una intensidad adecuada para mejorar tu salud cardiovascular. " +
                    "Sigue manteniendo esta intensidad para obtener los mejores resultados.";
        } else {
            return "Tu frecuencia cardíaca durante el ejercicio es alta. Esto puede ser un signo de que estás trabajando demasiado duro, lo cual puede ser peligroso, especialmente si tienes condiciones médicas preexistentes. " +
                    "Reduce la intensidad de tu actividad física y consulta a un profesional de la salud si esto persiste.";
        }
    }
    public String RcActividadFisica(int edad, int minutos) {
        if (edad>=18 && edad<65){
            if (minutos<150){
                return "Se recomienda aumentar tu actividad física a al menos 150 minutos de ejercicio moderado o 75 minutos de ejercicio intenso por semana " +
                        "para mejorar tu salud cardiovascular y bienestar general."+"\nLa actividad aeróbica se practicará en sesiones de 10 minutos de duración, " +
                        "como mínimo";
            }
            else{
                return "Tu nivel de actividad física es adecuado. Mantén esta rutina para seguir disfrutando de los beneficios para la salud.";
            }
        }
        else if(edad>=65) {
            if (minutos<150){
                return "Se recomienda aumentar tu actividad física a al menos 150 minutos de ejercicio moderado por semana, adaptando las actividades a tus capacidades y condiciones de salud. " +
                    "Incorpora ejercicios de equilibrio y fuerza para mejorar tu movilidad y prevenir caídas.";
            }
            else {
                return "Tu nivel de actividad física es adecuado. Mantén esta rutina para seguir disfrutando de los beneficios para la salud, asegurándote de incluir ejercicios que mejoren tu equilibrio y fuerza.";
            }
        }
        return "";
    }
}

