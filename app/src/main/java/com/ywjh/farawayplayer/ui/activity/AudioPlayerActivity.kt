package com.ywjh.farawayplayer.ui.activity
import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.drawable.AnimationDrawable
import android.media.MediaPlayer
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.view.View
import android.widget.AdapterView
import android.widget.SeekBar
import com.ywjh.farawayplayer.R
import com.ywjh.farawayplayer.adapter.PopAdapter
import com.ywjh.farawayplayer.base.BaseActivity
import com.ywjh.farawayplayer.model.AudioBean
import com.ywjh.farawayplayer.service.AudioService
import com.ywjh.farawayplayer.service.Iservice
import com.ywjh.farawayplayer.utils.StringUtil
import com.ywjh.farawayplayer.widget.PlayListPopWindow
import de.greenrobot.event.EventBus
import kotlinx.android.synthetic.main.activity_music_player_bottom.*
import kotlinx.android.synthetic.main.activity_music_player_middle.*
import kotlinx.android.synthetic.main.activity_music_player_top.*

class AudioPlayerActivity:BaseActivity(), View.OnClickListener, SeekBar.OnSeekBarChangeListener,
    AdapterView.OnItemClickListener {
    //弹出的播放列表点击事件
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //播放当前歌曲
        iService?.playPosition(position)
    }

    //进度一改变就回调  改变后进度和ture//用户手指改变 false代码式改变
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        //侦测用户操作侦测
        if(!fromUser)return
        //更新播放进度
        iService?.seekTo(progress)
        //更新界面进度显示
        updateProgress(progress)

    }
    //触摸seekbar的回调
    override fun onStartTrackingTouch(seekBar: SeekBar?) {
    }
    //手指离开seekbar回调
    override fun onStopTrackingTouch(seekBar: SeekBar?) {
    }

    var audioBean: AudioBean? =null
    //drawable:定义一次即可
    var drawable:AnimationDrawable?=null
    var duration:Int=0
    //不仅能定义handler还能接收
    val handler = @SuppressLint("HandlerLeak")
    object:Handler(){
        override fun handleMessage(msg: Message) {
            when(msg?.what){
                MSG_PROGRESS->startUpadateProgress()
            }
        }
    }
    val MSG_PROGRESS=0
    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                R.id.state->updatePlayState()//更新播放状态
                R.id.mode->updatePlayMode()
                R.id.pre->iService?.playPre()
                R.id.next->iService?.playNext()
                R.id.playlist->showPlayList()
            }
        }
    }
    //显示界面的代码
    private fun showPlayList() {

        val list=iService?.getPlayList()
        list?.let {
            val adapter=PopAdapter(list)

            //获取底部高度
            val bottomH=audio_player_bottom.height
            //println("111111111111111111111+"+bottomH)
            val popWindow=PlayListPopWindow(this,adapter,this,window)
            popWindow.showAsDropDown(audio_player_bottom,0,bottomH)//相对位置 x和y值
        }

    }

    //更新播放模式
    private fun updatePlayMode() {
        //修改service中的mode
        iService?.updatePlayMode()//功能性操作
        //修改界面模式的图标
        updatePlayModeBtn()//界面性操作
    }
    //据当前播放模式修改图标
    private fun updatePlayModeBtn() {
        iService?.let {
            //获取播放模式
            val modeI:Int=it.getPlayMode()
            //设置图标
            when(modeI){
                AudioService.MODE_ALL->mode.setImageResource(R.drawable.selector_btn_playmode_order)
                AudioService.MODE_SINGLE->mode.setImageResource(R.drawable.selector_btn_playmode_single)
                AudioService.MODE_RANDOM->mode.setImageResource(R.drawable.selector_btn_playmode_random)
            }
        }

    }

    //ThreadMd4种  postMod啥线程中发送，啥线程中执行发送  接收端
    /*接受eventbus方法，可能更新界面，放主线程中执行*/
    fun onEventMainThread(itembean:AudioBean){
        //记录正在播放的歌曲
        this.audioBean=itembean
        //歌曲名
        audio_title.text=itembean.display_name
        artist.text=itembean.artist
        //更新播放状态按钮
        updatePlayStateBtn()
        //开始放动画
        //拿到对应drawble
        //ps：若非src而是background方式设置背景 则拿到应该：audio_anim.getbackground
         drawable = audio_anim.drawable as AnimationDrawable
        drawable?.start()
        //drawable?.stop()
        //更新播放进度 总进度与计时，进度条  后面会用到，提出去
        duration=iService?.getDuration()?:0
        lyricView.setSongDuration(duration)
        //进度条设置进度最大值
        progress_sk.max=duration
        startUpadateProgress()
        //更新播放模式图标
        updatePlayModeBtn()
    }
/*
* 开始更新进度
* */
    private fun startUpadateProgress() {
        //获取当前进度
        var progress: Int? =iService?.getProgress()
        //更新进度数据 界面型 和上面的handler回调构成递归循环
    if (progress != null) {
        updateProgress(progress)
    }
        //定时获取进度 通过handler
        handler.sendEmptyMessage(MSG_PROGRESS)//发送空消息并有一定的延时
    }

    private fun updateProgress(pro: Int) {
        //更新进度数据毫秒-》分钟+秒
        progress.text = StringUtil.parseDuration(pro)+"/"+ StringUtil.parseDuration(duration)
        //更新进度条
        progress_sk.setProgress(pro)
        //更新歌词
        lyricView.updateProgress(pro)
    }

    //更新播放状态暂停与播放转换
    private fun updatePlayState() {
        //更新状态(用到绑定服务的Iservice) 功能性操作
        iService?.updatePlayState()
        //更新图标  界面性操作要分开 这样便于复用
        //更新播放状态按钮 通过updatePlayStateButton//功能性和界面性分开的好处
        updatePlayStateBtn()



    }
/*
* 根据播放状态更新图标
* */
    private fun updatePlayStateBtn() {
        //获取当前播放状态
        val isPlaying=iService?.isPlaying()
        //根据状态更新图标
        isPlaying?.let {
            if (isPlaying){//播放
                state.setImageResource(R.drawable.selector_btn_audio_play)
               drawable?.start()
                //开始更新进度
                handler.sendEmptyMessage(MSG_PROGRESS)
            }else{

                state.setImageResource(R.drawable.selector_btn_audio_pause)
                drawable?.stop()
                //停止更新进度
                handler.removeMessages(MSG_PROGRESS)
            }

        }
    }

    override fun initListener() {
        //播放状态的切换
        state.setOnClickListener(this)//传this让所在类实现click接口
        back.setOnClickListener{finish()}
        progress_sk.setOnSeekBarChangeListener(this)
        mode.setOnClickListener(this)
        pre.setOnClickListener(this)
        next.setOnClickListener(this)
        playlist.setOnClickListener(this)
    }

    override fun getLayoutid(): Int {
        return R.layout.activity_audio_player
    }

    val conn by lazy { AudioConnection()}
    override fun initDate() {
        //注册EventBUs  主动扫描这个类 保存所有onEvent类
        EventBus.getDefault().register(this)
        /*
        * 1.和2.对应拿包裹和封装intent转发包裹，但是没必要
        * */
       /* 1.val list=intent.getParcelableArrayListExtra<AudioBean>("list")
        val position=intent.getIntExtra("position",-1)//-1代表没有获取到*/
        //println("111111111111list=$list 2222222222positin=$position") 队列与位置正确
        //-》通过AudioService播放
        var intent=intent//这是getintent 和1中一样取从VBangFragment中的数据
        //修改
        intent.setClass(this,AudioService::class.java)
        //val intent= Intent(this,AudioService::class.java)//對應發件人和收件人
      /*  2.//通过intent将list以及position传递过去
        intent.putExtra("list",list)
        intent.putExtra("position",position)*/

        //先开启

        //再绑定
        bindService(intent,conn, Context.BIND_AUTO_CREATE)//第二个参数是接口connection
        //回到AudioService进行播放
        startService(intent)

        /* //播放音乐
        //1.创建进入idle状态
        val mediaPlayer=MediaPlayer()
        //2.设置播放路径
        mediaPlayer.setDataSource(list.get(position).data)
        //3.异步准备
        mediaPlayer.prepareAsync()
        //3.监听准备
        mediaPlayer.setOnPreparedListener{
            mediaPlayer.start()
        }*/

    }
    var  iService:Iservice?=null
    inner class AudioConnection:ServiceConnection{
        /*
        * 意外断开连接时
        * */
        override fun onServiceDisconnected(name: ComponentName?) {

        }
        /*
        * service连接时
        * */
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            iService=service as Iservice//报错了 AudioService没实现接口直接莽会报错
        }

    }
//防止内存泄漏
    override fun onDestroy() {
        super.onDestroy()
        //手动解绑服务
        unbindService(conn)//手动解绑
        //反注册EventBus
        EventBus.getDefault().unregister(this)
        //清空handler发送的所有消息以及回调
        handler.removeCallbacksAndMessages(null)
    }



}