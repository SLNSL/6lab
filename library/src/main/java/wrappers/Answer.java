package wrappers;

public interface Answer {

    Object getResult();

    void setResult(Object result);

    String getError();

    void setError(String error);

    int getErrorType();

    void setErrorType(int errorType);

    boolean hasError();
}
