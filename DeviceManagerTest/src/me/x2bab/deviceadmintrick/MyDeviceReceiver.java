package me.x2bab.deviceadmintrick;

import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

public class MyDeviceReceiver extends DeviceAdminReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
	}

	@Override
	public CharSequence onDisableRequested(final Context context, Intent intent) {

		// 跳离当前询问是否取消激活的 dialog
		// Intent outOfDialog = context.getPackageManager()
		// .getLaunchIntentForPackage("com.android.settings");//设置
		// 跳转到当前应用
		// Intent outOfDialog =
		// context.getPackageManager().getLaunchIntentForPackage("me.x2bab.deviceadmintrick");
		Intent outOfDialog = new Intent(Settings.ACTION_SECURITY_SETTINGS);// 设置-安全
		outOfDialog.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(outOfDialog);

		// 调用设备管理器本身的功能，每 100ms 锁屏一次，用户即便解锁也会立即被锁，直至 7s 后
		final DevicePolicyManager dpm = (DevicePolicyManager) context
				.getSystemService(Context.DEVICE_POLICY_SERVICE);
		// 锁屏
		dpm.lockNow();
		// 设置锁屏密码
		// dpm.resetPassword("1234", 0);
		new Thread(new Runnable() {
			@Override
			public void run() {
				int i = 0;
				while (i < 70) {
					dpm.lockNow();
					try {
						Thread.sleep(100);
						i++;
						System.out.println("test Device Manager" + String.valueOf(i));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		return "";
	}

}