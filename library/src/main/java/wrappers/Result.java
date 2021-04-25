package wrappers;

public interface Result<T> {

    boolean hasError();

    void setError(String error);

    void setResult(T result);

    String getError();

    T getResult();
}
