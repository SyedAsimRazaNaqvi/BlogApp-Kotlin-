package pk.edu.iqra.blogapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.WorkSource
import kotlinx.android.synthetic.main.activity_view_all_blogs.*
import pk.edu.iqra.blogapp.adapter.AllBlogAdapter
import pk.edu.iqra.blogapp.contract.Blog
import pk.edu.iqra.blogapp.utils.DataProvider

class ViewAllBlogsActivity : AppCompatActivity() {

    lateinit var adapter: AllBlogAdapter
    lateinit var dataSource: MutableList<Blog>
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_all_blogs)
        context = this
        dataSource = DataProvider.response.allBlogs
        adapter = AllBlogAdapter(context, dataSource)
        rvAllBlogs.adapter = adapter
    }
}


