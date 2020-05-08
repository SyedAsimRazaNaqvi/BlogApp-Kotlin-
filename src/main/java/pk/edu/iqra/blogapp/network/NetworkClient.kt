package pk.edu.iqra.blogapp.network

import pk.edu.iqra.blogapp.utils.Configuration
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkClient {

    companion object{
        private var networkClient:Retrofit? =null

        fun getNetworkClient():Retrofit{
            var tempInstance = networkClient;
            return if(tempInstance!=null){
                tempInstance
            }
            else{
                val instance = Retrofit.Builder()
                    .baseUrl(Configuration.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

                networkClient = instance
                instance
            }
        }

    }
}