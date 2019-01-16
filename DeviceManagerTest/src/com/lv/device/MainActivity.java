package com.lv.device;

import me.x2bab.deviceadmintrick.R;
import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
		ComponentName deviceComponentName = new ComponentName(
				"me.x2bab.deviceadmintrick",
				"me.x2bab.deviceadmintrick.MyDeviceReceiver");
		intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,
				deviceComponentName);
		this.startActivity(intent);
	}

}
