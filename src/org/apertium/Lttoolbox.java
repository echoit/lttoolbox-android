package org.apertium;

import org.apertium.embed.Apertium;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Lttoolbox extends Activity implements OnClickListener {

	private static final String MODE = "eo-en";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		_submitButton.setOnClickListener(this);
	}

	private void initView() {
		setContentView(R.layout.main);
		_inputText = (EditText) findViewById(R.id.inputtext);
		_submitButton = (Button) findViewById(R.id.submit);
		_outputText = (TextView) findViewById(R.id.outputtext);
	}

	@Override
	public void onClick(View v) {
		String inputText = _inputText.getText().toString();
		if (!TextUtils.isEmpty(inputText)) {
			try {
				_outputText.setText(Apertium.exe(_inputText.getText().toString(), MODE));
			} catch (Exception e) {
				Log.e("error", e.getMessage());
			}
		}
	}

	private EditText _inputText;
	private Button _submitButton;
	private TextView _outputText;
}