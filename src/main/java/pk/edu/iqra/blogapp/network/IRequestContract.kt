package pk.edu.iqra.blogapp.network

import pk.edu.iqra.blogapp.contract.Request
import pk.edu.iqra.blogapp.contract.Response
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface IRequestContract {
    @POST(value = "service.php")
    fun makeApiCall(@Body request: Request):Call<Response>

}