package org.apertium;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Lttoolbox extends Activity implements OnClickListener {
	

	private EditText _inputText;
	private Button _submitButton;
	private TextView _outputText;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        _submitButton.setOnClickListener(this);
    }
    
    private void initView(){
    	setContentView(R.layout.main);
    	_inputText = (EditText)findViewById(R.id.inputtext);
    	_submitButton = (Button) findViewById(R.id.submit);
    	_outputText = (TextView) findViewById(R.id.outputtext);
    }

	@Override
	public void onClick(View v) {
		String inputText = _inputText.getText().toString();
		if(!TextUtils.isEmpty(inputText)){
			try {
				_outputText.setText(org.apertium.embed.ApertiumMain.run(_inputText.getText().toString(), "eo-en"));
			} catch (Exception e) {
				Log.e("error", e.getMessage());
			}
		}
	}
}