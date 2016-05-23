package pl.java.scalatech.domain.generator;

import java.io.Serializable;
@FunctionalInterface
public interface Identifiable<T extends Serializable> {
    T getId();
}