package com.tanjarine.eventlogging.plugin;

import org.apache.cordova.api.CordovaPlugin;
import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.PluginResult;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class Echo extends CordovaPlugin {

    public static final String EVENT_EXTRA = "event";
    public static final String ACTION_EXT_APP_LOGGING = "com.tanjarine.actions.EXT_APP_LOGGING";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("createEvent")) {
            Map<String, String> map = new HashMap<String, String>();
            map.put(Fields.CONTENT_ID, args.getString(0));
            map.put(Fields.USER_EMAIL, args.getString(1));
            map.put(Fields.OPT_OUT, args.getString(2));
            map.put(Fields.USER_COMMENT, args.getString(3));
            map.put(Fields.SUBSCRIPTION, args.getString(4));
            map.put(Fields.RESULTS, args.getString(5));

            sendEvent(map);
            return true;
        }
        return false;
    }

    private void sendEvent(Map<String, String> map) {
            Bundle data = new Bundle();
            data.putSerializable(EVENT_EXTRA, (Serializable) map);

            Intent postEventIntent = new Intent(ACTION_EXT_APP_LOGGING);
            postEventIntent.putExtra(EVENT_EXTRA, data);

            cordova.getActivity().startService(postEventIntent);
        }

    public static interface Fields {
            String CONTENT_ID = "contentId";
            String USER_EMAIL = "userEmail";
            String OPT_OUT = "optOut";
            String USER_COMMENT = "userComment";
            String SUBSCRIPTION = "subscription";
            String RESULTS = "results";
        }
}