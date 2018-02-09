// Copyright (c) 2018 Electronic Theatre Controls, Inc., http://www.etcconnect.com
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.
//
package com.etcconnect.baseosc;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class Settings extends Fragment {

    //UI Elements
    View myView;
    Activity myActivity;

    // Create and setup view
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.settings, container, false);
        myActivity = (Activity) myView.getContext();
        myActivity.setTitle("Settings");

        // Setup Ip Address Text Field
        // TODO: If the user just clicks elsewhere, the value isn't saved. Also catch onFocusChanged!!!!
        final EditText ipAddressEditText = myView.findViewById(R.id.ipAddress);
        ipAddressEditText.setText(MainActivity.OSCAddress);
        ipAddressEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i== EditorInfo.IME_ACTION_DONE) {
                    MainActivity.OSCAddress = ipAddressEditText.getText().toString();
                    MainActivity.oscOutFragment = new OSCSender();
                }
                return false;
            }
        });

        // Setup Out Port Text Field
        // TODO: If the user just clicks elsewhere, the value isn't saved. Also catch onFocusChanged!!!!
        final EditText outPortEditText = myView.findViewById(R.id.outPort);
        outPortEditText.setText(Integer.toString(MainActivity.outPort));
        outPortEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i== EditorInfo.IME_ACTION_DONE) {
                    try {
                        MainActivity.outPort = Integer.parseInt(outPortEditText.getText().toString());
                        MainActivity.oscOutFragment = new OSCSender();
                    }
                    catch (NumberFormatException nfe) {
                        //Todo: add message to user here saying it must be a number
                    }
                }
                return false;
            }
        });

        // Setup In Port Text Field
        // TODO: If the user just clicks elsewhere, the value isn't saved. Also catch onFocusChanged!!!!
        final EditText inPortEditText = myView.findViewById(R.id.inPort);
        inPortEditText.setText(Integer.toString(MainActivity.inPort));
        inPortEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i== EditorInfo.IME_ACTION_DONE) {
                    try {
                        MainActivity.inPort = Integer.parseInt(inPortEditText.getText().toString());
                        MainActivity.oscInFragment = new OSCReceiver();
                    }
                    catch (NumberFormatException nfe) {
                        //Todo: add message to user here saying it must be a number
                    }
                }
                return false;
            }
        });

        return myView;
    }
}
