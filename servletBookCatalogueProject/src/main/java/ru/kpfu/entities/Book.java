package ru.kpfu.entities;

/**
 *
 * @author Alexander Ferenets (Istamendil) <ist.kazan@gmail.com>
 */
public class Book{
  private int id;
  private String annotation;
  private String name;
  private int year;

  public Book(String name, int year, String annotation) {
    this(-1, name, year, annotation);
  }
  
  public Book(int i, String name, int year, String annotation) {
    this.id = i;
    this.name = name;
    this.year = year;
    this.annotation = annotation;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getAnnotation() {
    return annotation;
  }

  public void setAnnotation(String annotation) {
    this.annotation = annotation;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }
  
  

}
