package com.widget_demo;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.Random;

/**
 * Created by sonu on 10/04/17.
 */

public class MyWidgetProvider extends AppWidgetProvider {
    // String array to show text at textview on refresh
    private static final String[] refreshStrings = {"Refreshed successfully.", "Data Loaded.", "Done loading.", "Refreshed.", "Great Job."};

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int widgetId : appWidgetIds) {
            //get random text from refreshStrings
            String stringText = refreshStrings[new Random().nextInt(refreshStrings.length)];

            // Create an Intent to launch MainActivity
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,
                    0, intent, 0);

            // Create an Intent to Refresh MyWidgetProvider
            Intent intent1 = new Intent(context, MyWidgetProvider.class);
            intent1.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent1.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
            PendingIntent pendingIntent1 = PendingIntent.getBroadcast(context,
                    0, intent1, 0);

            // Get the layout for the App Widget and attach an on-click listener
            // to the button and imageview
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.app_widget_layout);

            //set the random text
            remoteViews.setTextViewText(R.id.app_widget_textView, stringText);

            //set pending intent1 to action button to refresh Widget
            remoteViews.setOnClickPendingIntent(R.id.app_widget_actionButton, pendingIntent1);

            //set the pending intent to open MainActivity
            remoteViews.setOnClickPendingIntent(R.id.app_widget_imageView, pendingIntent);

            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
    }

}
