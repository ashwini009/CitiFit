package com.cititmobilechallenge.citifit.fcm;

import android.os.Bundle;

import com.cititmobilechallenge.citifit.common.Constants;
import com.cititmobilechallenge.citifit.helper.NotificationUtils;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

/**
 * @author Ashwini Kumar.
 */

public class FcmMessagingService extends FirebaseMessagingService
{
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage)
    {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getData().size() > 0)
        {
            Bundle extras = new Bundle();
            for (Map.Entry<String, String> entry : remoteMessage.getData().entrySet())
            {
                extras.putString(entry.getKey(), entry.getValue());
            }
            if (extras.containsKey(Constants.PROVIDER))
            {
                NotificationUtils.showNotificationMessage(extras);
            } else
            {
                // do nothing
            }
        } else
        {
            // do nothing
        }
    }
}
