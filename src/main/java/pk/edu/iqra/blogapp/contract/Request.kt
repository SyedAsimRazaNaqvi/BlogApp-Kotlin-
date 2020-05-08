package pk.edu.iqra.blogapp.contract

import android.widget.TextView
import com.google.gson.annotations.SerializedName

data class Request(

    @SerializedName(value = "action") var action:String="",
    @SerializedName(value = "userId") var userId:String="",
    @SerializedName(value = "userName") var userName:String="",
    @SerializedName(value = "blogId") var blogId:String="",
    @SerializedName(value = "title") var title:String="",
    @SerializedName(value = "description") var description: String = ""

)