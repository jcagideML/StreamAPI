package core;

public class Materia {

  private String nombre;

  private String carrera;

  public Materia(String nombre, String carrera) {
    this.nombre = nombre;
    this.carrera = carrera;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getCarrera() {
    return carrera;
  }

  public void setCarrera(String carrera) {
    this.carrera = carrera;
  }

  @Override
  public String toString() {
    return this.nombre;
  }

}
