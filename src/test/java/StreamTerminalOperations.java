import core.Alumno;
import core.Materia;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summarizingInt;

@SuppressWarnings("all")
public class StreamTerminalOperations {

  Materia m1;

  Materia m2;

  Materia m3;

  Materia m4;

  Materia m5;

  Alumno a1;

  Alumno a2;

  Alumno a3;

  Stream<Alumno> stream;

  @BeforeEach
  void before() {
    m1 = new Materia("Matemática", "Ciencias Exactas");
    m2 = new Materia("Literatura", "Ciencias Sociales");
    m3 = new Materia("Filosofía", "Ciencias Sociales");
    m4 = new Materia("Química", "Ciencias Exactas");
    m5 = new Materia("Ed. Física", "Deportes");

    a1 = new Alumno("Joaco", 29, List.of(m1, m4));
    a2 = new Alumno("Agus", 25, List.of(m2, m3));
    a3 = new Alumno("Marce", 29, List.of(m1, m3, m5));

    stream = Stream.<Alumno>builder()
        .add(a1)
        .add(a2)
        .add(a3)
        .build();
  }

  @Test
  public void findFirstOperation() {

    Optional<Alumno> alumno = stream
        .filter(a -> a.getEdad() == 29)
        .findFirst();

    System.out.println(alumno
        .get()
        .getNombre());
  }

  @Test
  public void findAnyOperation() {

    Optional<Alumno> alumno = stream
        .filter(a -> a.getEdad() == 29)
        .findAny();

    System.out.println(alumno
        .get()
        .getNombre());
  }


  @Test
  public void anyMatchAndAllMatchOperation() {

    Boolean anyMatch = stream.anyMatch(new Predicate<Alumno>() {
      @Override
      public boolean test(Alumno alumno) {
        return alumno.getEdad() > 25;
      }
    });

    Stream<Alumno> elMismoStream = Stream.of(a1, a2, a3);

    Boolean allMatch = elMismoStream.allMatch(a -> a.getEdad() > 25);

    System.out.println("Any: " + anyMatch);
    System.out.println("All: " + allMatch);
  }

  @Test
  public void minMaxAndCountOperations() {
    Stream<Alumno> minStream = Stream.of(a1, a2, a3);
    Stream<Alumno> maxStream = Stream.of(a1, a2, a3);
    Stream<Alumno> countStream = Stream.of(a1, a2, a3);

    Integer min = minStream
        .min(new Comparator<Alumno>() {
          @Override
          public int compare(Alumno o1, Alumno o2) {
            return o1.getEdad() - o2.getEdad();
          }
        })
        .get()
        .getEdad();

    System.out.println("La edad minima es: " + min);

    Integer max = maxStream
        .map(value -> value.getEdad())
        .max(Integer::compare)
        .get();

    System.out.println("La edad máxima es: " + max);

    Long count = countStream
        .filter(alumno -> alumno.getEdad() > 25)
        .count();

    System.out.println("El total de alumnos mayores a 25 es: " + count);
  }

  @Test
  public void forEachOperation() {
    stream.forEach(alumno -> {
      System.out.println(alumno.getNombre());
      alumno
          .getMaterias()
          .stream()
          .filter(materia -> materia
              .getCarrera()
              .contains("Ciencias"))
          .forEach(materia -> System.out.println(materia.getNombre()));
    });
  }

  @Test
  public void reduceOperation() {
    //Reduce, se usa para "reducir" el stream a que retorne un valor.
    // Min, max, count, por ej son operaciones de reducción.

    Integer suma = stream
        .map(alumno -> alumno.getEdad())
        .reduce(0, (x, y) -> x + y);
    System.out.println(suma);
    /*
    Para ilustrar este ejemplo un poco mejor. En el caso nuestro tenemos:
    Joaco 29
    Agus 25
    Marce 29

    Cuando ejecutamos reduce() lo que hace es:
    suma = (29 + 25)
    suma = (54 + 29)
    suma = (83)

    Si tuvieramos un alumno más, Leo 37 por ejemplo, y aplicaramos la operación ParallelStream:
    suma = (29 + 25) + (29 + 37)  No es determinado el orden, solo un ejemplo.
    suma =      (54 + 66)
    suma =        (120)
     */

  }

  @Test
  public void collectOperation() {
    List<Integer> edades = stream
        .map(alumno -> alumno.getEdad())
        .collect(Collectors.toList());

    edades
        .stream()
        .forEach(integer -> System.out.println(integer));

    IntSummaryStatistics estadisticas = edades
        .stream()
        .collect(summarizingInt(Integer::intValue));

    System.out.println("Min: " + estadisticas.getMax());
    System.out.println("Max: " + estadisticas.getMin());
    System.out.println("Count: " + estadisticas.getCount());
    System.out.println("Average: " + estadisticas.getAverage());
    System.out.println("Suma: " + estadisticas.getSum());
  }

  @Test
  public void mapGroupingByOperation() {
    Map<String, List<Materia>> porCarrera = stream
        .flatMap(alumno -> alumno
            .getMaterias()
            .stream())
        .distinct()
        .collect(groupingBy(Materia::getCarrera));

    porCarrera
        .entrySet()
        .stream()
        .forEach(System.out::println);
  }

  @Test
  public void consumedStream() {
    try {
      Optional<Alumno> anyAlumno = stream.findAny();
      Optional<Alumno> firstAlumno = stream.findFirst();
    }catch (Exception e){
      System.out.println("YA ESTABA CERRADOOO!");
    }
  }

  @Test
  public void solvingConsumedStream() {
    List<Alumno> list = stream
        .filter(a -> a.getEdad() == 29)
        .collect(Collectors.toList());

    Optional<Alumno> anyAlumno = list
        .stream()
        .findAny();
    Optional<Alumno> firstAlumno = list
        .stream()
        .findFirst();
  }

}
