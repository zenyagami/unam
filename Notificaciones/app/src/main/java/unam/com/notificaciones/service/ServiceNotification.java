package unam.com.notificaciones.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import unam.com.notificaciones.Main2Activity;
import unam.com.notificaciones.R;

/**
 * Created by hacke on 24/06/2016.
 */
public class ServiceNotification extends Service {
    private MyAsyncTask myAsyncTask;
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
      if(intent.getExtras()!=null && intent.getExtras().containsKey("key_id"))
      {
          id=intent.getExtras().getInt("key_id");
      }

      if(myAsyncTask==null)
      {
          myAsyncTask= new MyAsyncTask();
          myAsyncTask.execute();
      }

        return START_STICKY;
    }

    private class MyAsyncTask extends AsyncTask<Integer,Integer,Boolean>
    {
        private NotificationCompat.Builder mNotif;

        @Override
        protected void onPreExecute() {

            mNotif = new NotificationCompat
                    .Builder(getApplicationContext())
                    .setContentTitle("Descargando video")
                    .setContentText("Descargando pumas vs jaguares")
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_stat_action_perm_identity))
                    .setSmallIcon(android.R.drawable.ic_dialog_email);
        }

        @Override
        protected Boolean doInBackground(Integer... params) {
            for (int i=0;i<6;i++)
            {
                publishProgress(i);
                try {
                    Thread.sleep(1000*1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mNotif.setProgress(6,values[0],false);
            NotificationManager manager  = (NotificationManager)
                    getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(id,mNotif.build());
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result)
            {
                //elimina progres cuando lo seteamos a 0
                mNotif.setProgress(0,0,false);
                mNotif.setContentTitle("Descarga completa ;)");
                mNotif.setContentText("se ha completado la descarga de pumas");
                mNotif.setContentInfo("Descargado");
                mNotif.setAutoCancel(true);
                mNotif.setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("se ha completado la descarga de pumas\nGracias por usar \nMi App"));
                PendingIntent pendingIntent =PendingIntent.
                        getActivity(getApplicationContext(),
                                0,new Intent(getApplicationContext(),
                                        Main2Activity.class),PendingIntent.FLAG_UPDATE_CURRENT);
                mNotif.setContentIntent(pendingIntent);
                PendingIntent piService = PendingIntent.
                        getService(getApplicationContext(),1,
                                new Intent(getApplicationContext()
                                        ,ServiceNotification.class)
                                        .putExtra("key_id",id+1)
                        ,PendingIntent.FLAG_UPDATE_CURRENT);
                mNotif.addAction(android.R.drawable.ic_input_add,"Descargar de nuevo"
                ,piService);


                NotificationManager manager  = (NotificationManager)
                        getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(id,mNotif.build());
            }
            myAsyncTask=null;
            stopSelf();
        }
    }

}
