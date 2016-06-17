package performance.zenkun.com.class1.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by zenkun on 6/17/16. zenyagami@gmail.com
 */

public class MysqliteOpenHelper extends SQLiteOpenHelper {

  private static final String DATABASE_NAME = "unam";
  private static final int DATABASE_VERSION = 1;
  public static final String ITEM_TABLE_NAME = "items";

  //Campos de la tabla
  public static class ColumnItem{
    public static final String ID_ITEM = BaseColumns._ID;
    public static final String ITEM_NAME = "name";
  }


  //Script de Creación de la tabla Quotes
  public static final String CREATE_TABLE_ITEM =
      "create table "+ITEM_TABLE_NAME+"(" +
          ColumnItem.ID_ITEM+" integer primary key autoincrement," +
          ColumnItem.ITEM_NAME+" text not null)";

  public MysqliteOpenHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override public void onCreate(SQLiteDatabase db) {
    //creamos BD
    db.execSQL(CREATE_TABLE_ITEM);

  }

  @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

  }
}
