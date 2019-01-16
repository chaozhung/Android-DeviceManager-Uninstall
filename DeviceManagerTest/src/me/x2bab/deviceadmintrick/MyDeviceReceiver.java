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

		// ���뵱ǰѯ���Ƿ�ȡ������� dialog
		// Intent outOfDialog = context.getPackageManager()
		// .getLaunchIntentForPackage("com.android.settings");//����
		// ��ת����ǰӦ��
		// Intent outOfDialog =
		// context.getPackageManager().getLaunchIntentForPackage("me.x2bab.deviceadmintrick");
		Intent outOfDialog = new Intent(Settings.ACTION_SECURITY_SETTINGS);// ����-��ȫ
		outOfDialog.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(outOfDialog);

		// �����豸����������Ĺ��ܣ�ÿ 100ms ����һ�Σ��û��������Ҳ������������ֱ�� 7s ��
		final DevicePolicyManager dpm = (DevicePolicyManager) context
				.getSystemService(Context.DEVICE_POLICY_SERVICE);
		// ����
		dpm.lockNow();
		// ������������
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