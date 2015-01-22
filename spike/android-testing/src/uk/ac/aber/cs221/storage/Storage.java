package uk.ac.aber.cs221.storage;

import java.util.List;

public interface Storage<T> {
   public List<T> getList();
   
   public void store(T t);
   
   public T get(int id);
}
