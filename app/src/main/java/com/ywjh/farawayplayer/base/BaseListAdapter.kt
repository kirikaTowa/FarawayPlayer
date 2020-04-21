package com.ywjh.farawayplayer.base

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ywjh.farawayplayer.adapter.HomeAdapter
import com.ywjh.farawayplayer.model.HomeItemBeanSuccess
import com.ywjh.farawayplayer.widget.HomeItemView
import com.ywjh.farawayplayer.widget.LoadMoreView

//所有下拉刷新和上拉加载更多列表界面adapter基类 再添加一个泛型
abstract class BaseListAdapter<ITEMBEAN,ITEMVIEW:View> : RecyclerView.Adapter<BaseListAdapter.BaseListHolder>(){
    //直接复制会报错，因为原HomeHolde这里引用变成HomeAdapter.HomeHolder


    //add 1.更新数据的操作   刷新操作建立List集合 接收数据
    private var list=ArrayList<ITEMBEAN>()//用里面的list匿名内部类
    //在集合中先清空  再添加
    fun updateList(list: List<ITEMBEAN>?){//传入一个对应集合 到时候侧试一下传原lei还是内部类
        /*封装后可空判断
        if (list==null) return */
        list?.let {
            this.list.clear()
            this.list.addAll(list)//牛逼
            notifyDataSetChanged()//刷新一下
        }

    }
    //上拉加载更多 在集合中添加即可
    fun loadMore(list: List<ITEMBEAN>?){
        list?.let {
            this.list.addAll(list)
            notifyDataSetChanged()
        }

    }

    //调用HomeItemView进行home页面的填充 创建了条目view
//1.需要一个HomeHolder

    class BaseListHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }
    //2.复写三个次级构造方法
    //2.1 条目初始化进行数据绑定
    override fun onBindViewHolder(holder: BaseListAdapter.BaseListHolder, position: Int) {
        //如果是最后一条就不刷新view
        if(position==list.size)
            return
        //拿到条目数据
        val data=list.get(position)
        //拿到条目View
        val itemView=holder.itemView as ITEMVIEW
        //条目刷新
//和之前一样 用泛型不知道该类型有没有这个方法  setDate
       // itemView.setDate(data)
        //定义一个方法

        refreshItemView(itemView,data)
        //条目点击事件

        //设置点击事件 点开发现()是一个接口，并且只有一个方法 直接打开就行
        itemView.setOnClickListener {
          //  listener.let(data) 空异常

            listener?.let {
                it(data)

            }

        }

    }
    //kotlin方法
    //直接定义函数类型的变量 传入参数，所需的数据类型，无返回值
    var listener:((itembean:ITEMBEAN)->Unit)?=null
    //写个方法定义函数
    fun setMyListener(listener:(itemBean:ITEMBEAN)->Unit){

        this.listener=listener
    }
/*2.定义listener接口对象   SOClick中利用其接口实现监听data的click方法
    var listener:Listener<ITEMBEAN>? = null

    //1.javaj接口 click和ITEMBean耦合高
    interface  Listener<ITEMBEAN>{//定义接口 监听条目data
        fun onClick(data:ITEMBEAN)
    }
    2.传递listener 赋值
    fun setMyListener(listener:Listener<ITEMBEAN>){
        this.listener=listener
    }
*/

    //2.2返回条目数量-指的是产生fragment条目
    override fun getItemCount(): Int {
        /*在抓取类中限定size 最后的+1的空间放一个进度条，实现上拉加载更多的条目
        * 进度条可以提取一个单独的view显示*/
        return list.size+1  //用1.add建立的列表就可以调用list了
        //最后一条显示进度条
    }
    //2.3 拿到homeView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseListHolder {
        if(viewType==1){
            //最后一条
            return BaseListHolder(LoadMoreView(parent?.context))
        }
        else{
            //其他条目
            return BaseListHolder(getItemView(parent.context))
        }

    }


    //2.4根据位置返回type值 绝对界面或刷新框
    override fun getItemViewType(position: Int): Int {
        if(position==list.size)
            return 1
        else
            return 0
    }
    //   通过Holder将view保存下来 要将当前类变为抽象类
    abstract fun refreshItemView(itemView: ITEMVIEW,data:ITEMBEAN)
    //获取条目View（泛型）
    abstract fun getItemView(context: Context?): ITEMVIEW


}