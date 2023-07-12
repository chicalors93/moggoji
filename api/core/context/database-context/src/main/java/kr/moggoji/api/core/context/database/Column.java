package kr.moggoji.api.core.context.database;

public abstract class Column<T> {
  protected T value;

  public T get() { return value; }
}
