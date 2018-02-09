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
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.illposed.osc.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class OSCSender extends Fragment {

    //UI Elements
    View myView;
    Activity myActivity;

    public OSCSender() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.osc_out, container, false);
        myActivity = (Activity) myView.getContext();
        myActivity.setTitle("OSC Out");

        //Setup Button 1
        final Button button = myView.findViewById(R.id.button);
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                OSCMessage buttonClick = new OSCMessage("/button/1");
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    buttonClick.addArgument(1.0);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    v.performClick();
                    buttonClick.addArgument(0.0);
                }
                else {
                    return false;
                }

                new OSCSendMessage().execute(MainActivity.outPort, MainActivity.OSCAddress, buttonClick);
                return true;
            }
        });

        //Setup Button 2
        final Button button2 = myView.findViewById(R.id.button2);
        button2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                OSCMessage buttonClick = new OSCMessage("/button/2");
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    buttonClick.addArgument(1.0);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    v.performClick();
                    buttonClick.addArgument(0.0);
                }
                else {
                    return false;
                }
                new OSCSendMessage().execute(MainActivity.outPort, MainActivity.OSCAddress, buttonClick);
                return true;
            }
        });

        //Setup Button 3
        final Button button3 = myView.findViewById(R.id.button3);
        button3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                OSCMessage buttonClick = new OSCMessage("/button/3");
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    buttonClick.addArgument(1.0);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    v.performClick();
                    buttonClick.addArgument(0.0);
                }
                else {
                    return false;
                }
                new OSCSendMessage().execute(MainActivity.outPort, MainActivity.OSCAddress, buttonClick);
                return true;
            }
        });

        //Setup custom send button
        final EditText customEditText = myView.findViewById(R.id.customText);
        Button customSendButton = myView.findViewById(R.id.sendButton);
        customSendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO: make this more robust: crashes if not the right format and always sends strings as arguments.
                String customText = customEditText.getText().toString();
                OSCMessage sendButtonClick = new OSCMessage("/" + customText.substring(0, customText.indexOf('=', 0)));
                sendButtonClick.addArgument(customText.substring(customText.indexOf('=', 0), customText.length()));
                new OSCSendMessage().execute(MainActivity.outPort, MainActivity.OSCAddress, sendButtonClick);
            }
        });

        return myView;
    }
}
