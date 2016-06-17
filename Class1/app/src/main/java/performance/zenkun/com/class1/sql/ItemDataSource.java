package performance.zenkun.com.class1.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import performance.zenkun.com.class1.model.ModelItem;

/**
 * Created by zenkun on 6/17/16. zenyagami@gmail.com
 */

public class ItemDataSource {
  private MysqliteOpenHelper openHelper;
  private SQLiteDatabase sqLiteDatabase;

  public ItemDataSource(Context context) {
    openHelper= new MysqliteOpenHelper(context);
    sqLiteDatabase = openHelper.getWritableDatabase();
  }

  public void saveItem(ModelItem modelItem)
  {
    ContentValues contentValues = new ContentValues();
    contentValues.put(MysqliteOpenHelper.ColumnItem.ITEM_NAME,modelItem.name);
    sqLiteDatabase.insert(MysqliteOpenHelper.ITEM_TABLE_NAME,null,contentValues);

  }
  public List<ModelItem> getAllItems() {
    List<ModelItem> comments = new ArrayList<>();

    Cursor cursor = sqLiteDatabase.query(MysqliteOpenHelper.ITEM_TABLE_NAME,
        null, null, null, null, null, null);

    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      ModelItem item= cursorToItem(cursor);
      comments.add(item);
      cursor.moveToNext();
    }
    // make sure to close the cursor
    cursor.close();
    return comments;
  }
  private ModelItem cursorToItem(Cursor cursor) {
    ModelItem comment = new ModelItem();
    comment.id= cursor.getInt(cursor.getColumnIndex(MysqliteOpenHelper.ColumnItem.ID_ITEM));
    comment.name=cursor.getString(cursor.getColumnIndex(MysqliteOpenHelper.ColumnItem.ITEM_NAME));
    return comment;
  }


}
