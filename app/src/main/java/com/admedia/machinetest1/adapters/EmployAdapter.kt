package com.admedia.machinetest1.adapters

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.admedia.machinetest1.R
import com.admedia.machinetest1.data.Employee
import com.admedia.machinetest1.databinding.ImageViewBinding
import com.admedia.machinetest1.databinding.RecyclerMainContainerBinding

class EmployAdapter(var EmployeeList : ArrayList<Employee>) : RecyclerView.Adapter<EmployAdapter.EmployeeViewHolder>() {

    interface OnDetailClickListener {
        fun OnDetailClick(entry : Employee, position: Int)
    }

    interface  OnImageClickListener {
        fun OnImageClick(imageId : Int, position: Int)
    }

    var onDetailClickListener : OnDetailClickListener? = null
    var onImageClickListener : OnImageClickListener? = null

    var img : ImageView? = null

    inner class EmployeeViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        var imgEmpImage = itemView.findViewById<ImageView>(R.id.imgImage)
        var txtEmpName = itemView.findViewById<TextView>(R.id.txtName)
        var txtEmpId = itemView.findViewById<TextView>(R.id.txtID)
        var txtEmpEmail = itemView.findViewById<TextView>(R.id.txtEmail)
        var mainContainer = itemView.findViewById<ConstraintLayout>(R.id.mainContainer)
        var imgImageViewImage = itemView.findViewById<ImageView>(R.id.imgImageViewImage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        var binding = RecyclerMainContainerBinding.inflate(LayoutInflater.from(parent.context))
        return  EmployeeViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
       holder.imgEmpImage.setImageResource(EmployeeList[position].Image)
        holder.txtEmpName.text = "${EmployeeList[position].Name}"
        holder.txtEmpId.text = "${EmployeeList[position].Id}"
        holder.txtEmpEmail.text = "${EmployeeList[position].Email}"

        holder.mainContainer.setOnClickListener {
            onDetailClickListener?.OnDetailClick(EmployeeList.get(position), position)
        }

        holder.imgImageViewImage.setOnClickListener {
            onImageClickListener?.OnImageClick(EmployeeList.get(position).Image, position)
        }
    }

    override fun getItemCount(): Int = EmployeeList.size

}

