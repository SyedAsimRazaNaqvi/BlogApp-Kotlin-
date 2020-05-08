package pk.edu.iqra.blogapp.contract

import com.google.gson.annotations.SerializedName

data class Blog (
    @SerializedName(value = "blogId") var blogId:String="",
    @SerializedName(value = "bloggerName") var bloggerName:String="",
    @SerializedName(value = "title") var title:String="",
    @SerializedName(value = "description") var description:String="",
    @SerializedName(value = "dateTime") var dateTime:String=""

)