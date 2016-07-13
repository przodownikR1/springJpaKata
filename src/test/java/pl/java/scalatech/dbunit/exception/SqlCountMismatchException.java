package pl.java.scalatech.dbunit.exception;
public class SqlCountMismatchException extends RuntimeException {
 
    private static final long serialVersionUID = 1654965535520724561L;

    public SqlCountMismatchException(String statement, int expectedCount, int actualCount) {
        super("Expected " + statement + " query count: " + expectedCount + ", actual: " + actualCount);
    }
}