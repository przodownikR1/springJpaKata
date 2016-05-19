package pl.java.scalatech.generator;

import pl.java.scalatech.domain.generator.GeneratorOne;

public class GeneratorOneTest extends AbstractGeneratorTest {

    @Override
    String getEntityName() {
        return GeneratorOne.class.getSimpleName();
    }

    @Override
    Object createEntity(int i) {
        return new GeneratorOne("go_" + i);
    }

}
