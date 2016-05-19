package pl.java.scalatech.generator;

import pl.java.scalatech.domain.generator.Note;

public class GeneratorUUIDTest extends AbstractGeneratorTest {
    @Override
    String getEntityName() {
        return Note.class.getSimpleName();
    }

    @Override
    Object createEntity(int i) {
        return new Note("note_" + i);
    }

}
