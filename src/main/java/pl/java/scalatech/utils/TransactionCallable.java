package pl.java.scalatech.utils;
@FunctionalInterface
public interface TransactionCallable<T> {
  T execute();
}