package com.ywjh.farawayplayer.ui.activity


import androidx.appcompat.widget.Toolbar
import com.ywjh.farawayplayer.base.BaseActivity
import com.ywjh.farawayplayer.utils.ToolBarManager
import com.ywjh.farawayplayer.R
import com.ywjh.farawayplayer.utils.FragmentUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find


class MainActivity :  BaseActivity(), ToolBarManager {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)


    override fun getLayoutid(): Int {
        return R.layout.activity_main
    }
    //继承与惰性加载
    override val toolbar by lazy{find<Toolbar>(R.id.tb_toolbar)}
//初始化toolbar查看效果
    override fun initDate() {
        initMainToolBar()
    }

    override fun initListener() {
        //设置tab切换监听
        bottomBar.setOnTabSelectListener{
           // it代表参数id
            val transaction = supportFragmentManager.beginTransaction()
                //将当前container替换为第二个参数由FragmentUtil获取
            transaction.replace(R.id.container, FragmentUtil.fragmentUtil.getFragment(it),it.toString())//it后需要一个tag
            transaction.commit()


        }
    }
}

