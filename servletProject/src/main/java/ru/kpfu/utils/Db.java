package ru.kpfu.utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import ru.kpfu.exceptions.DbException;

/**
 *
 * @author Alexander Ferenets (Istamendil) <ist.kazan@gmail.com>
 */
public class Db {

  private final static String extension = "db";
  private final static String path = "/tmp/";

  private static void checkDb(String db) throws DbException {
    File f = new File(getDbFullPath(db));
    if (!f.exists() || !f.isFile() || !f.canRead() || !f.canWrite()) {
      throw new DbException();
    }
  }

  private static String getDbFullPath(String db) {
    String fullName = db + "." + extension;
    String fullPath = path + fullName;
    return fullPath;
  }

  public static void addEntry(String db, String[] data) throws DbException {
    checkDb(db);
    try {
      CSVWriter writer = new CSVWriter(new FileWriter(getDbFullPath(db),true));
      writer.writeNext(data);
      writer.close();
    } catch (IOException ex) {
      throw new DbException();
    }
  }

  public static List<String[]> getAllEntries(String db) throws DbException {
    checkDb(db);
    try {
      CSVReader reader = new CSVReader(new FileReader(getDbFullPath(db)));
      return reader.readAll();
    } catch (IOException ex) {
      throw new DbException();
    }
  }

}
