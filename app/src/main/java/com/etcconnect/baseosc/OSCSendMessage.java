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

import android.os.AsyncTask;
import android.util.Log;

import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCPortOut;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import static android.content.ContentValues.TAG;

/**
 * Created by csanders on 2/4/2018.
 */

public class OSCSendMessage extends AsyncTask<Object, Void, Boolean> {
    //parm 0 = port (int)
    //parm 1 = address (String)
    //parm 2 = message (OSCMessage)
    protected Boolean doInBackground(Object... parms){
        try {
            InetAddress addr = InetAddress.getByName((String)parms[1]);
            OSCPortOut sender = new OSCPortOut(addr, (int)parms[0]);
            sender.send((OSCMessage)parms[2]);
        }
        catch (SocketException e) {
            Log.d(TAG, "doInBackground: socket exception");
            //TODO: tell user osc send port failed
        }
        catch (UnknownHostException e)
        {
            Log.d(TAG, "doInBackground: unknown host exception");
            //TODO: tell user osc send port failed
        }
        catch (IOException e)
        {
            Log.d(TAG, "doInBackground: IO exception");
            //TODO: tell user osc send port failed
        }
        return true;
    }
}
