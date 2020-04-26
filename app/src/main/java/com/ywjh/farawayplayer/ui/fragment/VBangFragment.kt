package com.ywjh.farawayplayer.ui.fragment
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.AsyncQueryHandler
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.database.Cursor
import com.ywjh.farawayplayer.R
import android.graphics.Color
import android.os.AsyncTask
import android.os.Handler
import android.os.Message
import android.provider.MediaStore
import android.view.Gravity
import android.view.View
import android.widget.Adapter
import android.widget.ListView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.ywjh.farawayplayer.adapter.VbangAdapter
import com.ywjh.farawayplayer.base.BaseFragment
import com.ywjh.farawayplayer.model.AudioBean
import com.ywjh.farawayplayer.ui.activity.AudioPlayerActivity
import com.ywjh.farawayplayer.utils.CursorUtil
import kotlinx.android.synthetic.main.fragment_vbang.*
import org.jetbrains.anko.noButton
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.yesButton
import java.util.jar.Manifest


class VBangFragment: BaseFragment() {
//异步 1.新建线程  2.继承AsyncTask类  3.对数据库还有一个
/*    val handler = @SuppressLint("HandlerLeak") object : Handler(){//os包
        //new Handler要复写其方法，所以用匿名内部类
        override fun handleMessage(msg: Message) {
            msg.let {
                val cursor=msg.obj as Cursor
                CursorUtil.logCursor(cursor)
            }
        }
    }*/

    override fun initView(): View? {

        return View.inflate(context,R.layout.fragment_vbang,null)
    }

    override fun initData() {
        loadSongs()
        //动态权限申请 处理权限问题
        handlePermission()

    }
    /*
        * 处理权限问题
        * */
    private fun handlePermission() {
        val permission=android.Manifest.permission.READ_EXTERNAL_STORAGE
        val checkSelfPermission=context?.let { ActivityCompat.checkSelfPermission(it,permission) }
        if (checkSelfPermission==PackageManager.PERMISSION_GRANTED){
            //已经获取
            loadSongs()
        }else{
            //没有获取权限
            if(activity?.let { ActivityCompat.shouldShowRequestPermissionRationale(it,permission) }!!){
                //结果为true则需要弹出对话框进行再次请求
                //AlertDialog  anko封装
                alert("我们只会访问音乐文件，不会访问隐私照片","温馨提示") {
                    yesButton { myRequsetPerssion() }
                    noButton {  }
                }.show()
            }else{
                //不需要弹出
                myRequsetPerssion()
            }
        }
    }

    private fun myRequsetPerssion() {
        //真正申请权限
        val permission= arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        requestPermissions(permission,1)
    }
/*
* 接收权限授权结果
* requestCode请求码
* permission权限申请数组
* grantResules申请之后结果
* */
    //结果回调
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
        loadSongs()
    }
    }

    //因为查询使用的是ContentProvider(内容提供者对应resolver)

    private fun loadSongs() {
        //加载音乐数据 MediaPlayer媒体数据库/音乐/视频/图片
        //从数据库访问数据 需要内容解析者
        val resolver = context?.contentResolver
        //cursor返回类型 前车之鉴 放到主线程卡死 加到子线程中 完成再更新UI适配
        /*       val cursor=resolver?.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            arrayOf(MediaStore.Audio.Media.DATA,
                    MediaStore.Audio.Media.SIZE,
                    MediaStore.Audio.Media.DISPLAY_NAME,
                    MediaStore.Audio.Media.ARTIST),
            null,null,null)//查询感兴趣的字段 数组封装 后三个参数排序什么的
            //打印所有数据
            CursorUtil.logCursor(cursor)*/
        //开启一个单独的线程查询音乐数据
        /*        Thread(object :Runnable{
                    override fun run() {
                        val cursor=resolver?.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                            arrayOf(MediaStore.Audio.Media.DATA,
                                MediaStore.Audio.Media.SIZE,
                                MediaStore.Audio.Media.DISPLAY_NAME,
                                MediaStore.Audio.Media.ARTIST),
                            null,null,null)
                        //这里通过handler的方法将查询到的cursor传入主线程
                        val msg =Message.obtain()//消息池中拿消息
                        msg.obj=cursor
                        handler.sendMessage(msg)
                    }

                }).start()
                //asynctask处理异步任务*/
        //AudioTask().execute(resolver)//第二种异步传递的参数params可变参数后面取也取第一个就好了()只一个参数

        val handler = @SuppressLint("HandlerLeak")
        object : AsyncQueryHandler(resolver) {
            //token是用于区分的 调用查询可以加入token 同flag
            override fun onQueryComplete(token: Int, cookie: Any?, cursor: Cursor?) {
                //查询完成的回调  只有回调是在主线程中的
                //打印数据
                //结果是Cursor对象 和bean类似 用一个cursoradapter进行适配
                //CursorUtil.logCursor(cursor)
                /*  when(token){条目或者非常多 内嵌语句非常多 就可以考虑用cookie传递adapter
                0->adapter1
                1->adapter2

            }*/
                //有了adapter后刷新列表 cookie传daapter
    //            (cookie as VbangAdapter).notifyDataSetChanged()//数据源变化时刷新，但adapter创建时给的是null传递
                //1.先设置数据源

                //2.再刷新
                (cookie as VbangAdapter).swapCursor(cursor)//点开发现 就是前面两步
            }
        }
        //开始查询
        handler.startQuery(
            0, adapter, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            arrayOf(
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.SIZE,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.ARTIST
            ),
            null, null, null
        )
    }

    var adapter:VbangAdapter?=null
        override fun initListener() {
           adapter=VbangAdapter(context,null)//cursor对象
            //再通过listview显示出来
            mclistview.adapter=adapter//注意，这边的在vbangfragment中
            //设置条目点击事件
            mclistview.setOnItemClickListener{
                adapterView,view,i,l->//四个参数 int是position long就是id
                //获取数据集合

                val cursor=adapter?.getItem(i) as Cursor //Cursor adapter返回的item
                //通过当前位置cursor获取整个播放列表
                val list: ArrayList<AudioBean> = AudioBean.getAudioBeans(cursor)//写一个方法拿到
                //位置position
                //跳转到音乐播放界面 又是熟悉的操作 传这个要序列化接口
                startActivity<AudioPlayerActivity>("list" to list,"position" to i)//还需要对应参数，播放列表集合用于上下首切换

            }
        }
    /*
    //音乐查询异步任务
    class AudioTask:AsyncTask<ContentResolver,Void,Cursor>(){
        //1.后台开启新线程执行任务 相当于new一个新handler
        override fun doInBackground(vararg params: ContentResolver?): Cursor ?{//返回结果是Cursor
            val cursor=params[0]?.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                arrayOf(MediaStore.Audio.Media.DATA,
                    MediaStore.Audio.Media.SIZE,
                    MediaStore.Audio.Media.DISPLAY_NAME,
                    MediaStore.Audio.Media.ARTIST),
                null,null,null)
            return cursor
        }//三个泛型 参数 进度 返回的Result
        //2.后台任务结果回调到主线程
        override fun onPostExecute(result: Cursor?) {
            super.onPostExecute(result)
            //打印Cursor
            CursorUtil.logCursor(result)
        }
    }*/

    //界面销毁 关闭cursor
    //1.获取adapter中的cursor 关闭
    //2.将adapter中的cursor设置为null

    override fun onDestroy() {
        super.onDestroy()
        adapter?.changeCursor(null)//将传入的null替换原cursor 然后将其关闭
    }

}
