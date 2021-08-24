import core.Alumno;
import core.Materia;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("all")
public class StreamIntermediateOperations {

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
  public void filterOperationWithAnonymousClass() {
    stream.filter(new Predicate<Alumno>() {
      @Override
      public boolean test(Alumno alumno) {
        return alumno.getEdad() == 29;
      }
    });
  }

  @Test
  public void filterOperationWithLambdaExpression() {
    stream.filter(alumno -> alumno.getEdad() == 29);
  }

  @Test
  public void mapOperation() {
    Stream<String> stringStream = stream.map(alumno -> alumno.getNombre());
  }

  @Test
  public void flatMapOperation() {
    Stream<Materia> materiaStream = stream.flatMap(alumno -> alumno
        .getMaterias()
        .stream());

    System.out.println(materiaStream.count());
  }

  @Test
  public void flatMapOperationAndDistinct() {
    Stream<Materia> materiaStream = stream.flatMap(alumno -> alumno
        .getMaterias()
        .stream())
        .distinct();

    System.out.println(materiaStream.count());
  }
}
