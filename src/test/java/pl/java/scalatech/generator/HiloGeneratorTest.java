package pl.java.scalatech.generator;

import pl.java.scalatech.domain.generator.HiloEntity;

public class HiloGeneratorTest  extends AbstractGeneratorTest{

    @Override
    String getEntityName() {
        return HiloEntity.class.getSimpleName();
    }

    @Override
    Object createEntity(int i) {
        return new HiloEntity("hilo_"+i);
    }

}
