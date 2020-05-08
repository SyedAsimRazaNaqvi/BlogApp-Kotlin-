package pk.edu.iqra.blogapp.utils

import pk.edu.iqra.blogapp.contract.Blog
import pk.edu.iqra.blogapp.contract.Response

object DataProvider {

    var response:Response = Response()
    var blog:Blog = Blog()
    lateinit var userId:String
    lateinit var userName:String

}