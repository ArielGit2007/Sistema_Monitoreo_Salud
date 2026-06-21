package Modelo;

import java.io.*;
import java.util.ArrayList;


public class historial {
   public ArrayList<Persona> Personas =new ArrayList<Persona>();
   public ArrayList<actividadFisica> act = new ArrayList<actividadFisica>();
   public ArrayList<datosMedicos> medicos = new ArrayList<>();

    public void agregarDatos(Persona per){
        act.add(per.actF);
        medicos.add(per.med);
        Personas.add(per);
        try{
            guardarEnArchivo("historial.dat");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void guardarEnArchivo(String nombre) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombre));
        oos.writeObject(Personas);
        oos.writeObject(act);
        oos.writeObject(medicos);
        oos.close();
    }
public void cargarEnArchivo(String nombre) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombre));
        Personas=(ArrayList<Persona>) ois.readObject();
        act=(ArrayList<actividadFisica>) ois.readObject();
        medicos=(ArrayList<datosMedicos>) ois.readObject();
        ois.close();
}
    public ArrayList<Persona> ultimosPersonas = new ArrayList<>();
    public ArrayList<actividadFisica> ultimosAct = new ArrayList<>();
    public ArrayList<datosMedicos> ultimosMedicos = new ArrayList<>();

    public void mostrarUltimos(int cantidad){
        ultimosPersonas.clear();  // Limpia la lista
        ultimosAct.clear();
        ultimosMedicos.clear();
        int ultimoIndice=(Personas.size())-2;//Para obviar el último registro que es el actual
        int indice=Math.max(0,Personas.size()-cantidad-1);
        for(int i=ultimoIndice;i>=indice;i--){
            ultimosPersonas.add(Personas.get(i));
            ultimosAct.add(act.get(i));
            ultimosMedicos.add(medicos.get(i));
        }
}
}
