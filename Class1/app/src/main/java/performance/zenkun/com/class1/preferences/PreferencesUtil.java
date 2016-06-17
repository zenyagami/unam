package performance.zenkun.com.class1.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import performance.zenkun.com.class1.model.ModelUser;

/**
 * Created by zenkun on 6/17/16. zenyagami@gmail.com
 */

public class PreferencesUtil {
  private static final String PREFERENCE_NAME ="preference_data";
  private final SharedPreferences sp;

  public PreferencesUtil(Context context) {
    sp = context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE);
  }
  public void saveUser(ModelUser user)
  {
    //todo validate if is empty
    sp.edit().putString("user",user.username).apply();
    sp.edit().putString("password",user.password).apply();
  }
  public ModelUser getUser()
  {
    String user = sp.getString("user",null);
    String password = sp.getString("password",null);
    if(TextUtils.isEmpty(user) || TextUtils.isEmpty(password))
      return null;

    return new ModelUser(user,password);
  }

}
