package com.cititmobilechallenge.citifit.application;

import android.app.Application;
import android.content.Context;

import com.cititmobilechallenge.citifit.logger.Log;
import com.cititmobilechallenge.citifit.logger.LogWrapper;
import com.cititmobilechallenge.citifit.logger.MessageOnlyLogFilter;

/**
 * Created by Mayank on 23-10-2015.
 */
public class CitiFitApplication extends Application
{
    private static Context context;

    public static Context getContext()
    {
        return context;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        context = this;
        initializeLogging();
    }

    /**
     * Initialize a custom log class that outputs both to in-app targets and logcat.
     */

    private void initializeLogging()
    {
        // Wraps Android's native log framework.
        LogWrapper logWrapper = new LogWrapper();
        // Using Log, front-end to the logging chain, emulates android.util.log method signatures.
        Log.setLogNode(logWrapper);
        // Filter strips out everything except the message text.
        MessageOnlyLogFilter msgFilter = new MessageOnlyLogFilter();
        logWrapper.setNext(msgFilter);
        // On screen logging via a customized TextView.
        // LogView logView = (LogView) findViewById(R.id.sample_logview);
        //logView.setTextAppearance(this, R.style.Log);
        //logView.setBackgroundColor(Color.WHITE);
        //msgFilter.setNext(logView);
    }
}
