package ru.kpfu.entities;

/**
 *
 * @author Alexander Ferenets (Istamendil) <ist.kazan@gmail.com>
 */
public class User {
  private int id;
  private String email;
  private String password;
  private boolean sex;
  private boolean subsription;

  public User(String email, String password, boolean sex, boolean subsription) {
    this(-1, email, password, sex, subsription);
  }
  
  public User(int i, String email, String password, boolean sex, boolean subsription) {
    this.id = -1;
    this.email = email;
    this.password = password;
    this.sex = sex;
    this.subsription = subsription;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isSex() {
    return sex;
  }

  public void setSex(boolean sex) {
    this.sex = sex;
  }

  public boolean isSubsription() {
    return subsription;
  }

  public void setSubsription(boolean subsription) {
    this.subsription = subsription;
  }
  
  
}
