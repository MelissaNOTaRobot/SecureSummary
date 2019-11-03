/*
1. Answer queries about the state of network connectivity. It also notifies applications when network connectivity changes.
2. Describes the status of a network interface of a given type (currently either Mobile or Wi-Fi)
*/

private static final String DEBUG_TAG = "NetworkStatusExample";
...
ConnectivityManager connMgr =
        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
boolean isWifiConn = false;
boolean isMobileConn = false;
for (Network network : connMgr.getAllNetworks()) {
    NetworkInfo networkInfo = connMgr.getNetworkInfo(network);
    if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
        isWifiConn |= networkInfo.isConnected();
    }
    if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
        isMobileConn |= networkInfo.isConnected();
    }
}
Log.d(DEBUG_TAG, "Wifi connected: " + isWifiConn);
Log.d(DEBUG_TAG, "Mobile connected: " + isMobileConn);

/*
3. Get network info
*/

public boolean isOnline() {
    ConnectivityManager connMgr = (ConnectivityManager)
            getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
    return (networkInfo != null && networkInfo.isConnected());
}

<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.android.networkusage"
    ...>

    <uses-sdk android:minSdkVersion="4"
           android:targetSdkVersion="14" />

    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

    <application
        ...>
        ...
        <activity android:label="SettingsActivity" android:name=".SettingsActivity">
             <intent-filter>
                <action android:name="android.intent.action.MANAGE_NETWORK_USAGE" />
                <category android:name="android.intent.category.DEFAULT" />
          </intent-filter>
        </activity>
    </application>
</manifest>


/*4. Allow applications to access information about networks.*/

<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.android.networkusage"
    ...>

    <uses-sdk android:minSdkVersion="4"
           android:targetSdkVersion="14" />

    /* We don't need to allow this application to open network sockets.<uses-permission android:name="android.permission.INTERNET" />*/
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

    <application
        ...>
        ...
        <activity android:label="SettingsActivity" android:name=".SettingsActivity">
             <intent-filter>
                <action android:name="android.intent.action.MANAGE_NETWORK_USAGE" />
                <category android:name="android.intent.category.DEFAULT" />
          </intent-filter>
        </activity>
    </application>
</manifest>


/*5. Detecting connection changes for network*/

public class NetworkReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager conn =  (ConnectivityManager)
            context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conn.getActiveNetworkInfo();

        // Checks the user prefs and the network connection. Based on the result, decides whether
        // to refresh the display or keep the current display.
        // If the userpref is Wi-Fi only, checks to see if the device has a Wi-Fi connection.
        if (WIFI.equals(sPref) && networkInfo != null
            && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            // If device has its Wi-Fi connection, sets refreshDisplay
            // to true. This causes the display to be refreshed when the user
            // returns to the app.
            refreshDisplay = true;
            Toast.makeText(context, R.string.wifi_connected, Toast.LENGTH_SHORT).show();

        // If the setting is ANY network and there is a network connection
        // (which by process of elimination would be mobile), sets refreshDisplay to true.
        } else if (ANY.equals(sPref) && networkInfo != null) {
            refreshDisplay = true;

        // Otherwise, the app can't download content--either because there is no network
        // connection (mobile or Wi-Fi), or because the pref setting is WIFI, and there
        // is no Wi-Fi connection.
        // Sets refreshDisplay to false.
        } else {
            refreshDisplay = false;
            Toast.makeText(context, R.string.lost_connection, Toast.LENGTH_SHORT).show();
        }
    }
}



/*6. Detecting bluetooth status*/


var bluetoothHeadset: BluetoothHeadset? = null

// Get the default adapter
val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()

private val profileListener = object : BluetoothProfile.ServiceListener {

    override fun onServiceConnected(profile: Int, proxy: BluetoothProfile) {
        if (profile == BluetoothProfile.HEADSET) {
            bluetoothHeadset = proxy as BluetoothHeadset
        }
    }

    override fun onServiceDisconnected(profile: Int) {
        if (profile == BluetoothProfile.HEADSET) {
            bluetoothHeadset = null
        }
    }
}

// Establish connection to the proxy.
bluetoothAdapter?.getProfileProxy(context, profileListener, BluetoothProfile.HEADSET)

// ... call functions on bluetoothHeadset

// Close proxy connection after use.
bluetoothAdapter?.closeProfileProxy(BluetoothProfile.HEADSET, bluetoothHeadset)


/* controlling headsets, audio distribution, and health devices*/

var bluetoothHeadset: BluetoothHeadset? = null

// Get the default adapter
val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()

private val profileListener = object : BluetoothProfile.ServiceListener {

    override fun onServiceConnected(profile: Int, proxy: BluetoothProfile) {
        if (profile == BluetoothProfile.HEADSET) {
            bluetoothHeadset = proxy as BluetoothHeadset
        }
    }

    override fun onServiceDisconnected(profile: Int) {
        if (profile == BluetoothProfile.HEADSET) {
            bluetoothHeadset = null
        }
    }
}

// Establish connection to the proxy.
bluetoothAdapter?.getProfileProxy(context, profileListener, BluetoothProfile.HEADSET)

// ... call functions on bluetoothHeadset

// Close proxy connection after use.
bluetoothAdapter?.closeProfileProxy(BluetoothProfile.HEADSET, bluetoothHeadset)
