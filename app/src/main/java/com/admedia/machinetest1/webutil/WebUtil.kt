package com.admedia.machinetest1.webutil

import android.media.Image
import com.admedia.machinetest1.R
import com.admedia.machinetest1.data.Employee
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class WebUtil {

    companion object{

        var image : Int = R.mipmap.ic_launcher

        fun getUserList(pageNumber : Int) : ArrayList<Employee>? {
            var url = URL("https://reqres.in/api/users?page=$pageNumber")
            var httpURLConnection = url.openConnection() as HttpURLConnection

            httpURLConnection.connect()

            if(httpURLConnection.responseCode != 200){
                return null
            }

            var responseBuffer = StringBuffer()
            var data = ByteArray(1024 * 1)
            var count = 0

            while (true) {
                count = httpURLConnection.inputStream.read(data)
                if (count == -1) {
                    break
                }
                responseBuffer.append(String(data, 0, count))
            }

            httpURLConnection.inputStream.close()

            var employeeList = ArrayList<Employee>()

            var jRoot = JSONObject(responseBuffer.toString())
            var jEmployee = jRoot.getJSONArray("data")

            for (i in 0..jEmployee.length() - 1) {
                var jEmployee = jEmployee.getJSONObject(i)

                employeeList.add(
                    Employee(
                        image,
                        jEmployee.getString("first_name") + " " + jEmployee.getString("last_name"),
                        jEmployee.getInt("id"),
                        jEmployee.getString("email")
                    )
                )
            }

            return employeeList

        }
    }
}