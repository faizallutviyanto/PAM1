package customtools;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import pertemuansatu.android.asdos.latihandesign.NotificationActivity;
import pertemuansatu.android.asdos.latihandesign.R;
import pertemuansatu.android.asdos.latihandesign.ViewNotification;

/**
 * Created by ADMIN on 6/30/2015.
 */
public class MyNotification {
    private NotificationManager notif;
    private int notifID = 100;
    private int numMsg = 0;

    public void displayNotification(Activity activity){
        Log.i("Start", "Notification");
        NotificationCompat.Builder nbuilder = new NotificationCompat.Builder(activity);
        nbuilder.setContentTitle("New Message");
        nbuilder.setContentText("Anda Mendapatkan Pesan Baru");
        nbuilder.setTicker("New Message Alert");
        nbuilder.setSmallIcon(R.drawable.coba_aja);

        nbuilder.setNumber(++numMsg);
        Intent resIntent = new Intent(activity, ViewNotification.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(activity);
        stackBuilder.addParentStack(NotificationActivity.class);

        stackBuilder.addNextIntent(resIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        nbuilder.setContentIntent(resultPendingIntent);
        notif = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
        notif.notify(notifID, nbuilder.build());
    }

    public void cancelNotification(){
        Log.i("Cancel","Notification Cancel");
        notif.cancel(notifID);
    }

    public void updateNotification(Activity activity) {
        Log.i("Update", "Notification Update");
        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(activity);
        nBuilder.setContentTitle("Update Message");
        nBuilder.setContentText("Berhasil Update New Message");
        nBuilder.setTicker("Update Message Alert");
        nBuilder.setSmallIcon(R.drawable.coba_aja);

        nBuilder.setNumber(++numMsg);
        Intent resIntent = new Intent(activity, ViewNotification.class);
        TaskStackBuilder stackBuild = TaskStackBuilder.create(activity);
        stackBuild.addParentStack(NotificationActivity.class);

        stackBuild.addNextIntent(resIntent);
        PendingIntent resultPendingIntent = stackBuild.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        nBuilder.setContentIntent(resultPendingIntent);
        notif = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
        notif.notify(notifID, nBuilder.build());
    }
}
