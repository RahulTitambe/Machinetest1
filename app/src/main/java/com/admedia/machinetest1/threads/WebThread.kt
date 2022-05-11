package com.admedia.machinetest1.threads

import android.os.AsyncTask
import android.os.Handler
import android.os.Message
import com.admedia.machinetest1.data.Employee
import com.admedia.machinetest1.webutil.WebUtil

class WebThread(
    var handler: Handler? = null
) : AsyncTask<Any?, Any?, ArrayList<Employee>?>() {
    override fun doInBackground(vararg params: Any?): ArrayList<Employee>? {
        return WebUtil.getUserList(1)
    }

    override fun onPostExecute(result: ArrayList<Employee>?) {
        super.onPostExecute(result)
        if (handler != null) {
            var message = Message()
            message.obj = result
            handler!!.sendMessage(message)
        }
    }
}