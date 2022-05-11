package com.admedia.machinetest1.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.admedia.machinetest1.MainActivity
import com.admedia.machinetest1.adapters.EmployAdapter
import com.admedia.machinetest1.data.Employee
import com.admedia.machinetest1.databinding.RecyclerViewBinding
import com.admedia.machinetest1.threads.WebThread

class HomeFragment : Fragment() {

    lateinit var binding: RecyclerViewBinding
    var employeeList = ArrayList<Employee>()
    var employeeAdapter = EmployAdapter(employeeList)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = RecyclerViewBinding.inflate(layoutInflater)

        WebThread(UsersHandler()).execute(null)

        binding.recyclerView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )

        binding.recyclerView.adapter = employeeAdapter

        return binding.root
    }

    inner class UsersHandler : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
             employeeList.addAll(msg.obj as ArrayList<Employee>)
            /*for(employee in msg.obj as ArrayList<Employee>) {
                Log.e("user", employee.toString())
            }*/
        }
    }
}