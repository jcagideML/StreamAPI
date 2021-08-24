import core.Alumno;
import core.Materia;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("all")
public class StreamCreation {

  Materia m1;
  Materia m2;
  Materia m3;
  Materia m4;
  Materia m5;

  Alumno a1;
  Alumno a2;
  Alumno a3;

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
  }

  @Test
  public void emptyStream() {
    List<String> list = new ArrayList<>();
    Stream<String> stream = list == null || list.isEmpty() ? Stream.empty() : list.stream();
  }

  @Test
  public Stream<Alumno> collectionStream() {
    List<Alumno> alumnos = new ArrayList<>();
    alumnos.add(a1);
    alumnos.add(a2);
    alumnos.add(a3);

    return alumnos.stream();
  }

  @Test
  public Stream<Alumno> arrStream() {
    Alumno[] alumnos = new Alumno[]{a1, a2, a3};

    return Arrays.stream(alumnos);
  }

  @Test
  public Stream<Alumno> StreamBuilder() {
    return Stream.<Alumno>builder()
        .add(a1)
        .add(a2)
        .add(a3)
        .build();
  }

  @Test
  public Stream<Alumno> generateStream() {
    return Stream
        .generate(() -> new Alumno("Leo", 29, List.of(m1, m2, m3)))
        .limit(10);
  }

  @Test
  public Stream<Integer> iterateStream() {
    return Stream
        .iterate(0, n -> n + 2)
        .limit(10);
  }

  @Test
  public IntStream intStream() {
    return IntStream.range(1, 3);
  }

}
