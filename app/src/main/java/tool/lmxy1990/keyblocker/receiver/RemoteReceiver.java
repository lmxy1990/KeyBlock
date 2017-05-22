package tool.lmxy1990.keyblocker.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;
import tool.lmxy1990.keyblocker.R;
import tool.lmxy1990.keyblocker.base.BaseMethod;
import tool.lmxy1990.keyblocker.config.Config;

public class RemoteReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(Config.REMOTE_CONTROL_ACTION)) {
			boolean displayToast = intent.getBooleanExtra("RESPOND", true);
			if (BaseMethod.isAccessibilitySettingsOn(context)) {
				Intent notify_intent = new Intent();
				notify_intent.setAction(Config.NOTIFICATION_CLICK_ACTION);
				context.sendBroadcast(notify_intent);
			} else {
				if (displayToast) {
					Toast.makeText(context, R.string.start_service_first, Toast.LENGTH_SHORT).show();
					Intent access_intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
					context.startActivity(access_intent);
				}
			}
		}
	}

}
