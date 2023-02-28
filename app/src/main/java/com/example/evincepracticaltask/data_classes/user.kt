package com.example.evincepracticaltask.data_classes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class DataItem(

	@ColumnInfo(name = "last_name")
	@field:SerializedName("last_name")
	var lastName: String? = "",

	@PrimaryKey
	@field:SerializedName("id")
	var id: Int? = 0,

	@ColumnInfo(name = "avatar")
	@field:SerializedName("avatar")
	var avatar: String? = "",

	@ColumnInfo(name = "first_name")
	@field:SerializedName("first_name")
	var firstName: String? = "",

	@ColumnInfo(name = "email")
	@field:SerializedName("email")
	var email: String? = "",

)

data class Support(

	@field:SerializedName("text")
	val text: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class UserResponse(

	@field:SerializedName("per_page")
	val perPage: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("support")
	val support: Support? = null
)
