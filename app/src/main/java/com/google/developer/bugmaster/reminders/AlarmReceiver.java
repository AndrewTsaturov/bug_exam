package com.google.developer.bugmaster.reminders;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.developer.bugmaster.MainActivity;
import com.google.developer.bugmaster.R;
import com.google.developer.bugmaster.data.Insect;
import com.google.developer.bugmaster.ui.FragmentInterface;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class AlarmReceiver extends BroadcastReceiver {
    private static final String TAG = AlarmReceiver.class.getSimpleName();

    private static String TIME_FORMAT = "HH:mm";


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "alarm sheduled");
        //Schedule alarm on BOOT_COMLETED
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            scheduleAlarm(context);
        }
        else {
            scheduleAlarm(context);
        }
    }

    /* Schedule the alarm based on user preferences */
    public void scheduleAlarm(Context context) {
        AlarmManager manager = AlarmManagerProvider.getAlarmManager(context);

        String keyReminder = context.getString(R.string.pref_key_reminder);
        String keyAlarm = context.getString(R.string.pref_key_alarm);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        boolean enabled = preferences.getBoolean(keyReminder, false);

        //Intent to trigger
        Intent intent = new Intent(context, ReminderService.class);
        PendingIntent serviceSender = PendingIntent
                .getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (enabled) {
            Log.d(TAG, "enabled");
            Calendar startTime = Calendar.getInstance();
            Date bufferDate;

            SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT);
            try {
                String alarmPref = preferences.getString(keyAlarm, "12:00");

                bufferDate = format.parse(alarmPref);
                Log.d(TAG, "time" + bufferDate.getHours());
                Log.d(TAG, "time" + bufferDate.getMinutes());

            } catch (ParseException e) {
                Log.w(TAG, "Unable to determine alarm start time", e);
                return;
            }
  
            startTime.set(Calendar.HOUR_OF_DAY, bufferDate.getHours());
            Log.d(TAG, "time" + bufferDate.getHours());
            startTime.set(Calendar.MINUTE, bufferDate.getMinutes());
            Log.d(TAG, "time" + bufferDate.getMinutes());
            //Start at the preferred time
            //If that time has passed today, set for tomorrow
            if (Calendar.getInstance().after(startTime)) {
                startTime.add(Calendar.DATE, 1);
            }

            Log.d(TAG, "Scheduling quiz reminder alarm");

            manager.setExact(AlarmManager.RTC, startTime.getTimeInMillis(), serviceSender);
        } else {
            Log.d(TAG, "Disabling quiz reminder alarm");
            manager.cancel(serviceSender);
        }
    }

}
