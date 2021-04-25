package wrappers;


public class FieldResult<T> implements Result<T> {
    private T result;
    private String error;

    public FieldResult() {
    }

    ;

    public FieldResult(T result) {
        this.result = result;
    }

    public boolean hasError() {
        if (error == null) return false;
        return true;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public T getResult() {
        return result;
    }


}
