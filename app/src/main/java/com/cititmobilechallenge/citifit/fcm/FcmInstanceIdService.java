package com.cititmobilechallenge.citifit.fcm;

import com.cititmobilechallenge.citifit.logger.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * @author Ashwini Kumar.
 */
public class FcmInstanceIdService extends FirebaseInstanceIdService
{
    @Override
    public void onTokenRefresh()
    {
        super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d("FCM", "Reg Id: " + token);
    }
}
