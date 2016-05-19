package pl.java.scalatech.generator;

import pl.java.scalatech.domain.generator.TableGeneratorEntity;

public class TableGeneratorTest extends AbstractGeneratorTest {

    @Override
    String getEntityName() {
        return TableGeneratorEntity.class.getSimpleName();
    }

    @Override
    Object createEntity(int i) {
        return new TableGeneratorEntity("gen_"+i);
    }

}
