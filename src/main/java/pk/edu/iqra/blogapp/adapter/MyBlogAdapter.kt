package pk.edu.iqra.blogapp.adapter

import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.it_all_blog.view.*
import kotlinx.android.synthetic.main.it_all_blog.view.dateTime
import kotlinx.android.synthetic.main.it_all_blog.view.description
import kotlinx.android.synthetic.main.it_all_blog.view.title
import kotlinx.android.synthetic.main.it_my_blog.view.*
import pk.edu.iqra.blogapp.AddOrUpdateBlogActivity
import pk.edu.iqra.blogapp.R
import pk.edu.iqra.blogapp.contract.Blog
import pk.edu.iqra.blogapp.contract.Request
import pk.edu.iqra.blogapp.contract.Response
import pk.edu.iqra.blogapp.network.IRequestContract
import pk.edu.iqra.blogapp.network.NetworkClient
import pk.edu.iqra.blogapp.utils.Constant
import pk.edu.iqra.blogapp.utils.DataProvider
import pk.edu.iqra.blogapp.utils.showToast
import retrofit2.Call
import retrofit2.Callback

class MyBlogAdapter(
    var activity: Activity,
    var context: Context,
    var dataSource: MutableList<Blog>
) :
    RecyclerView.Adapter<MyBlogAdapter.MyBlogViewHolder>(), Callback<Response> {

    private var progressDialog: ProgressDialog = ProgressDialog(context)
    private val retrofitClient = NetworkClient.getNetworkClient()
    private val requestContract = retrofitClient.create(IRequestContract::class.java)
    private lateinit var deletedBlog: Blog
    private var deletedPosition: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyBlogViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.it_my_blog, parent, false)
        return MyBlogViewHolder((view))
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    override fun onBindViewHolder(holder: MyBlogViewHolder, position: Int) {
        val blog = dataSource[position]
        holder.title.text = blog.title
        holder.description.text = blog.description
        holder.blogger.text = blog.bloggerName
        holder.dateTime.text = blog.dateTime

        holder.btnedit.setOnClickListener {

            Intent(context, AddOrUpdateBlogActivity::class.java).apply {
                DataProvider.blog = blog
                putExtra(Constant.KEY_REASON,2) //Edit
                activity.startActivity(this)
            }

        }
        holder.btndelete.setOnClickListener {

            AlertDialog.Builder(context).setTitle("Blog App Alert")
                .setMessage("Are you sure? You want to delete this Blog")
                .setPositiveButton("Yes") { dialog, which ->
                    progressDialog.setMessage("Please wait...");
                    progressDialog.setCancelable(false)
                    deletedBlog = blog
                    deletedPosition = position
                    val request = Request(
                        action = Constant.DELETE_BLOG,
                        userId = DataProvider.userId,
                        blogId = blog.blogId
                    )
                    progressDialog.show();
                    val callResponse = requestContract.makeApiCall(request)
                    callResponse.enqueue(this)
                }
                .setNegativeButton("No") { dialog, which ->
                    dialog?.dismiss()
                }
        }
    }

    class MyBlogViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title = view.title
        var description = view.description
        var blogger = view.blogger
        var dateTime = view.dateTime
        var btnedit = view.edit
        var btndelete = view.delete
    }

    override fun onFailure(call: Call<Response>, t: Throwable) {
        TODO("Not yet implemented")
    }

    override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
        if (progressDialog.isShowing)
            progressDialog.dismiss()
        if (response.body() != null) {
            val serverResponse = response.body()
            if (serverResponse!!.status) {
                dataSource.remove(deletedBlog)
                notifyItemRemoved(deletedPosition)
                notifyItemRangeChanged(deletedPosition, dataSource.size)
                context.showToast(serverResponse.message)
            } else {
                context.showToast(serverResponse.message)
            }
        }
    }


}