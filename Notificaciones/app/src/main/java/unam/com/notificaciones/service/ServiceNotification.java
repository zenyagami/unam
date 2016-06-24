package unam.com.notificaciones.service;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import unam.com.notificaciones.R;

/**
 * Created by hacke on 24/06/2016.
 */
public class ServiceNotification extends Service {
    private int id;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        NotificationCompat.Builder mNotif = new NotificationCompat
                .Builder(getApplicationContext())
                .setContentTitle("Llamada perdida")
                .setContentText("Nuevo mensaje de voz")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_stat_action_perm_identity))
                .setSmallIcon(android.R.drawable.ic_dialog_email);

        NotificationManager manager  = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0,mNotif.build());
        id++;

        return START_STICKY;
    }

    private class MyAsyncTask extends AsyncTask<Integer,Integer,Boolean>
    {
        
    }

}
