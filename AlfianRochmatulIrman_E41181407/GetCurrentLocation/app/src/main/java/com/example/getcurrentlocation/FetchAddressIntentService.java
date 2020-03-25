package com.example.getcurrentlocation;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.os.IResultReceiver;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class FetchAddressIntentService extends IntentService {

    private IResultReceiver resultReceiver;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     * @param resultReceiver
     */
    public FetchAddressIntentService(String name, IResultReceiver resultReceiver) {
        super("FetchAddressIntentService");
        this.resultReceiver = resultReceiver;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(intent != null){
            String errorMessage = "";
            resultReceiver = intent.getParcelableExtra(Constants.RECEIVER);
            Location location = intent.getParcelableExtra(Constants.LOCATION_DATA_EXTRA);
            if(location == null){
                return;
            }
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = null;
            try{
                addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            }catch (Exception exception){
                errorMessage = exception.getMessage();
            }
            if (addresses == null || addresses.isEmpty()){
                try {
                    deliverResultToReceiver(Constants.FAILURE_RESULT,errorMessage);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }else{
                Address address = addresses.get(0);
                ArrayList<String> addressFragment = new ArrayList<>();
                for (int i = 0; i <= address.getMaxAddressLineIndex(); i++){
                    addressFragment.add(address.getAddressLine(i));
                }
                try {
                    deliverResultToReceiver(
                                Constants.SUCCESS_RESULT,
                                TextUtils.join(
                                        Objects.requireNonNull(System.getProperty("line.separator")),
                                        addressFragment
                                )
                        );
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void deliverResultToReceiver(int resultCode, String addressMessage) throws RemoteException {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.RESULT_DATA_KEY, addressMessage);
        resultReceiver.send(resultCode, bundle);
    }
}
