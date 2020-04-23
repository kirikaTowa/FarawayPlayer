package com.ywjh.farawayplayer.ui.fragment
import android.annotation.SuppressLint
import android.content.AsyncQueryHandler
import android.content.ContentResolver
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
import android.widget.TextView
import com.ywjh.farawayplayer.base.BaseFragment
import com.ywjh.farawayplayer.utils.CursorUtil


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
        //加载音乐数据 MediaPlayer媒体数据库/音乐/视频/图片
        //从数据库访问数据 需要内容解析者
        val resolver= context?.contentResolver
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

        val handler= @SuppressLint("HandlerLeak")
        object:AsyncQueryHandler(resolver){//token是用于区分的 调用查询可以加入token 同flag
            override fun onQueryComplete(token: Int, cookie: Any?, cursor: Cursor?) {
                //查询完成的回调  只有回调是在主线程中的
            //打印数据
            CursorUtil.logCursor(cursor)
          /*  when(token){条目或者非常多 内嵌语句非常多 就可以考虑用cookie传递adapter
                0->adapter1
                1->adapter2

            }*/
            }
        }
        //开始查询
        handler.startQuery(0,null,MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            arrayOf(MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.SIZE,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.ARTIST),
            null,null,null)

    }//因为查询使用的是ContentProvider(内容提供者对应resolver)
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

}
