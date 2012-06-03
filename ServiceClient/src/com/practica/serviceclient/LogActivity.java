package com.practica.serviceclient;

import java.util.Calendar;
import com.practica.ServiceBroadcastClient.FriendMessageParcelable;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;


public class LogActivity extends Activity implements OnClickListener
{
	
	private Button retb;
	private static TextView tlog;
	private static ScrollView scroll;
	

	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log);
        
        retb = (Button)findViewById(R.id.button_ret);
        
        tlog = (TextView)findViewById(R.id.textlog);
 
        scroll = (ScrollView) findViewById(R.id.scrollog);

        
        retb.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	
            	ServiceClientActivity.log_active = false;
            	finish();
            }
          });
        
        ServiceClientActivity.log_active = true;
    }
    
    
	@Override
	public void onPause() {
		super.onPause();
		
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
	
	public static void writeLog(FriendMessageParcelable fmsg)
	{
			
		Calendar c = Calendar.getInstance(); 
		String date = getDate(c);
		String loc = new String ("["+fmsg.getLocation().getLatitude()+","+fmsg.getLocation().getLatitude()+"]");
		//Log.e("ServiceBroadcastClientActivity", "handleMessage::Message received from service ("+fmsg.getType()+","+fmsg.getFrom()+","+fmsg.getLocation().toString()+")");
	
		String text = new String(date+">> Msg Type:"+fmsg.getType()+", From: "+fmsg.getFrom()+", Loc: "+loc+"\n");   

		
		tlog.append(text);
		
		int height = tlog.getHeight();
		if(height > scroll.getMeasuredHeight())
		{
			scroll.scrollTo(0, height-scroll.getMeasuredHeight());
		}
		
	}


	/**
	 * @param c Calendar
	 * @return String
	 */
	private static String getDate(Calendar c) {
		String date = new String(c.get(Calendar.DAY_OF_MONTH)+"-"+c.get(Calendar.MONTH)+"-"+c.get(Calendar.YEAR)+"||"+c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE)+":"+c.get(Calendar.SECOND));
		return date;
	}
	
}