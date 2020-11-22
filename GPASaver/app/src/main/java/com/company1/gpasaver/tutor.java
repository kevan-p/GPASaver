/*
package com.company1.gpasaver;

import android.os.asynctask;
import android.os.bundle;
import android.view.view;
import android.widget.arrayadapter;
import android.widget.button;
import android.widget.edittext;
import android.widget.spinner;
import android.widget.toast;

import org.json.jsonarray;
import org.json.jsonexception;
import org.json.jsonobject;

import java.io.bufferedreader;
import java.io.inputstreamreader;
import java.net.httpurlconnection;
import java.net.url;

import androidx.appcompat.app.appcompatactivity;

public class tutor extends appcompatactivity {
    edittext mname = (edittext) findviewbyid(r.id.editfirstname);
    edittext memail = (edittext) findviewbyid(r.id.editemail);
    edittext mexperience = (edittext) findviewbyid(r.id.editfeedback);
    edittext mgpa = (edittext) findviewbyid(r.id.editfeedback);
    button msendfeedback = (button) findviewbyid(r.id.btnsubmittutor);
    spinner spinnerview =(spinner)findviewbyid(r.id.spinnercourse);

    string[] spinnervalue = {"cs510", "cs641"};


    @override
    protected void oncreate(bundle savedinstancestate) {
        super.oncreate(savedinstancestate);
        setcontentview(r.layout.activity_tutor);
        downloadjson("http://coms-510-04.cs.iastate.edu/connection.php");
        msendfeedback.setonclicklistener(new view.onclicklistener() {
            @override
            public void onclick(view view) {
                if (mname.gettext().tostring().isempty() || memail.gettext().tostring().isempty()|| mexperience.gettext().tostring().isempty()||mgpa.gettext().tostring().isempty()) { //edittext is empty
                    toast.maketext(tutor.this, "please fill in mandatory text box(es)", toast.length_long).show();
                }
                else {
                    toast.maketext(tutor.this, "thank you for sharing your feedback", toast.length_short).show();
                }
            }
        });

        arrayadapter<string> adapter = new arrayadapter<>(this, android.r.layout.simple_list_item_1, spinnervalue);
        spinnerview.setadapter(adapter);
    }
    private void downloadjson(final string urlwebservice) {
        class downloadjson extends asynctask<void, void, string>
        {
            @override
            protected void onpreexecute() {
                super.onpreexecute();
            }
            @override
            protected void onpostexecute(string s) {
                super.onpostexecute(s);
                toast.maketext(getapplicationcontext(), s, toast.length_short).show();
                try {
                    loadintospinnerview(s);
                } catch (jsonexception e) {
                    e.printstacktrace();
                }
            }
            @override
            protected string doinbackground(void... voids) {
                try {
                    url url = new url(urlwebservice);
                    httpurlconnection con = (httpurlconnection) url.openconnection();
                    stringbuilder sb = new stringbuilder();
                    bufferedreader bufferedreader = new bufferedreader(new inputstreamreader(con.getinputstream()));
                    string json;
                    while ((json = bufferedreader.readline()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.tostring().trim();
                } catch (exception e) {
                    return null;
                }
            }
        }
        downloadjson getjson = new downloadjson();
        getjson.execute();
    }
    private void loadintospinnerview(string json) throws jsonexception {
        jsonarray jsonarray = new jsonarray(json);
        string[] courses = new string[jsonarray.length()];
        for (int i = 0; i < jsonarray.length(); i++) {
            jsonobject obj = jsonarray.getjsonobject(i);
            courses[i] = obj.getstring("courses_name") + " " + obj.getstring("courses_id");
        }
        arrayadapter<string> dataadapter = new arrayadapter<string>
                (this, android.r.layout.simple_spinner_item,courses );

        dataadapter.setdropdownviewresource
                (android.r.layout.simple_spinner_dropdown_item);
        spinnerview.setadapter(dataadapter);
    }

}
*/
