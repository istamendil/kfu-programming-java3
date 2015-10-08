package ru.kpfu.repositories;

import java.util.List;
import ru.kpfu.entities.User;
import ru.kpfu.exceptions.DbException;
import ru.kpfu.exceptions.DuplicateEntryException;
import ru.kpfu.utils.Db;

/**
 *
 * @author Alexander Ferenets (Istamendil) <ist.kazan@gmail.com>
 */
public class UserRepository {
  public static void add(User user) throws DbException, DuplicateEntryException{
    // Find duplicates and throw DuplicateEntryException if found
    // Add new entry otherwise
    // throw new DuplicateEntryException();
    Db.addEntry("users", new String[]{user.getEmail(), user.getPassword(), user.isSex()?"1":"0", user.isSubsription()?"1":"0"});
  }
  public static List<User> getAll(){
    // Use Db.getAllEntries and make List<User> from List<String[]> - Repository works with entities
    // Return real List here
    return null;
  }
}
