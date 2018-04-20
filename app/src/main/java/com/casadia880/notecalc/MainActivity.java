package com.casadia880.notecalc;

import android.os.Bundle;
import android.widget.*;
import android.app.Activity;
import android.text.Editable;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	
	Button mButton;
	EditText mEdit;
	TextView mText;
	TextView Hz;
	TextView desc;
	
	
	private final double A = Math.pow(2, 1.0/12.0);
	private final double A4 = 440;
	private final double C4 = 261.626;
	private final String[] scale = {"C","C♯/D♭","D","D♯/E♭","E","F","F♯/G♭","G","G♯/A♭","A","A♯/B♭","B"};
	
	private String note = "";
	private Boolean cont=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button)findViewById(R.id.mButton);
        mEdit = (EditText) findViewById(R.id.mEdit);
		mText = (TextView) findViewById(R.id.mText);
		Hz = (TextView) findViewById(R.id.textView1);
		desc = (TextView) findViewById(R.id.textView2);
        mButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				cont=false;
				
				double freq = 0.0;
				//do{
					try{
						String s = mEdit.getText().toString();
						if(Double.parseDouble(s)<10) throw new Exception("invalidNumber");
						freq = Double.parseDouble(s);
						cont=true;
					}
					catch (Exception e){
						Toast.makeText(getApplicationContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
						
					}
					if(cont){
						mText.setText(findNote(findSteps(freq))+findOctave(findSteps(freq)));
					}
				//}
				
			}
		});
    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
*/    
    public int findSteps(double fn){
    	double n= (Math.log(fn/C4)/Math.log(A));
    	return (int)n;
    }
    
    public String findNote(int n){
    	if(n>0){
    		return scale[(n+1)%scale.length];
    	}
    	else if(n==0) return "C";
    	else{
    		return scale[scale.length-(Math.abs(n)%scale.length)];
    	}
    }
    
    public int findOctave(int n){
    	if(n>0){
    		return 4+((n+1)/scale.length);
    	}
    	else if(n==0) return 4;
    	else{
    		return 3+((n+1)/scale.length);
    	}
    }
    
    
}
