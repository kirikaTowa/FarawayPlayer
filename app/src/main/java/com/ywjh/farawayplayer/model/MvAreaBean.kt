package com.ywjh.farawayplayer.model

class MvAreaBean {
    /**
     * status : 1
     * error :
     * data : {"list":[{"is_short":0,"channel_id":96,"name":"抗击肺炎"},{"is_short":0,"channel_id":94,"name":"小姐姐"},{"is_short":0,"channel_id":91,"name":"MV"},{"is_short":0,"channel_id":100,"name":"新声请指教"},{"is_short":0,"channel_id":68,"name":"综艺"},{"is_short":0,"channel_id":48,"name":"网络"},{"is_short":0,"channel_id":40,"name":"影视"},{"is_short":0,"channel_id":54,"name":"舞蹈"},{"is_short":0,"channel_id":22,"name":"现场"},{"is_short":0,"channel_id":67,"name":"翻唱"},{"is_short":0,"channel_id":86,"name":"国风"},{"is_short":0,"channel_id":87,"name":"演奏"},{"is_short":0,"channel_id":35,"name":"二次元"},{"is_short":0,"channel_id":72,"name":"节奏控"},{"is_short":0,"channel_id":34,"name":"华语"},{"is_short":0,"channel_id":39,"name":"欧美"},{"is_short":0,"channel_id":75,"name":"韩国"},{"is_short":0,"channel_id":74,"name":"日本"},{"is_short":0,"channel_id":82,"name":"首唱会"}],"timestamp":1586532279}
     * errcode : 0
     */

    var status: Int = 0
    var error: String? = null
    var data: DataBean? = null
    var errcode: Int = 0

    class DataBean {
        /**
         * list : [{"is_short":0,"channel_id":96,"name":"抗击肺炎"},{"is_short":0,"channel_id":94,"name":"小姐姐"},{"is_short":0,"channel_id":91,"name":"MV"},{"is_short":0,"channel_id":100,"name":"新声请指教"},{"is_short":0,"channel_id":68,"name":"综艺"},{"is_short":0,"channel_id":48,"name":"网络"},{"is_short":0,"channel_id":40,"name":"影视"},{"is_short":0,"channel_id":54,"name":"舞蹈"},{"is_short":0,"channel_id":22,"name":"现场"},{"is_short":0,"channel_id":67,"name":"翻唱"},{"is_short":0,"channel_id":86,"name":"国风"},{"is_short":0,"channel_id":87,"name":"演奏"},{"is_short":0,"channel_id":35,"name":"二次元"},{"is_short":0,"channel_id":72,"name":"节奏控"},{"is_short":0,"channel_id":34,"name":"华语"},{"is_short":0,"channel_id":39,"name":"欧美"},{"is_short":0,"channel_id":75,"name":"韩国"},{"is_short":0,"channel_id":74,"name":"日本"},{"is_short":0,"channel_id":82,"name":"首唱会"}]
         * timestamp : 1586532279
         */

        var timestamp: Int = 0
        var list: List<ListBean>? = null


        class ListBean {

            /**
             * is_short : 0
             * channel_id : 96
             * name : 抗击肺炎
             */

            var is_short: Int = 0
            var channel_id: Int = 0
            var name: String? = null
            override fun toString(): String {
                return "ListBean{" +
                        "is_short=" + is_short +
                        ", channel_id=" + channel_id +
                        ", name='" + name + '\''.toString() +
                        '}'.toString()
            }
        }
    }
}
