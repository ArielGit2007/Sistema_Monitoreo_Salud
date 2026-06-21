package Modelo;

import java.io.Serializable;

public class Persona implements Serializable {
    private String Nombre,Apellido;
    private int Edad;
    private double altura, peso;
    private String genero;

    //Clases añadidas
    public actividadFisica actF = new actividadFisica();
   public datosMedicos med = new datosMedicos();

    //Setters y getters

    public String getNombre() {return Nombre;}
    public void setNombre(String Nombre) {this.Nombre = Nombre;}
    public String getApellido() {return Apellido;}
    public void setApellido(String Apellido) {this.Apellido = Apellido;}
    public int getEdad() {return Edad;}
    public void setEdad(int Edad) {this.Edad = Edad;}
    public double getAltura() {return altura;}
    public void setAltura(double altura) {this.altura = altura;}
    public double getPeso() {return peso;}
    public void setPeso(double peso) {this.peso = peso;}
    public String getGenero() {return genero;}
    public void setGenero(String genero) {this.genero = genero;}

}
