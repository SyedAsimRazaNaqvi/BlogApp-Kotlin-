package pk.edu.iqra.blogapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_view_all_blogs.*
import kotlinx.android.synthetic.main.activity_view_my_blogs.*
import pk.edu.iqra.blogapp.adapter.AllBlogAdapter
import pk.edu.iqra.blogapp.adapter.MyBlogAdapter
import pk.edu.iqra.blogapp.contract.Blog
import pk.edu.iqra.blogapp.utils.Constant
import pk.edu.iqra.blogapp.utils.DataProvider
import pk.edu.iqra.blogapp.utils.showToast
import javax.sql.DataSource

class ViewMyBlogsActivity : AppCompatActivity() {

    lateinit var adapter: MyBlogAdapter
    lateinit var dataSource: MutableList<Blog>
    private lateinit var context: Context
    private lateinit var activity: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_my_blogs)

        context = this
        activity = this
        dataSource = DataProvider.response.allBlogs
        if (dataSource.size > 0) {
            adapter = MyBlogAdapter(activity,context, dataSource)
            rvAllBlogs.visibility = View.VISIBLE
            noBlogAvailable.visibility = View.INVISIBLE
            rvAllBlogs.adapter = adapter
        } else {
            noBlogAvailable.visibility = View.VISIBLE
            rvAllBlogs.visibility = View.INVISIBLE
        }

        add.setOnClickListener {
            Intent(this, AddOrUpdateBlogActivity::class.java).apply {
                putExtra(Constant.KEY_REASON,1) //Add
                startActivity(this)

            }
        }

    }
}
