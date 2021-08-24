package core;

import java.util.List;

public class Alumno {

  private String nombre;

  private int edad;

  private List<Materia> materias;

  public Alumno(String nombre, int edad, List<Materia> materias) {
    this.nombre = nombre;
    this.edad = edad;
    this.materias = materias;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getEdad() {
    return edad;
  }

  public void setEdad(int edad) {
    this.edad = edad;
  }

  public List<Materia> getMaterias() {
    return materias;
  }

  public void setMaterias(List<Materia> materias) {
    this.materias = materias;
  }

}
