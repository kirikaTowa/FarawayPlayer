package com.ywjh.farawayplayer.model

class HomeItemBeanSuccess {

    private var code: Int = 0
    private var message: String? = null
    private var result: List<ResultBean>? = null  //result 结果集



    fun getResult(): List<ResultBean>? {
        return result
    }

    fun setResult(result: List<ResultBean>) {
        this.result = result
    }

    data class ResultBean (
        var country: String,
        var piao_id: String,
        var res_encryption_flag: String,
        var mv_provider: String,
        var biaoshi: String,
        var artist_name: String,
        var is_first_publish:Int,
        var language: String,
        var album_1000_1000: String,
        var korean_bb_song: String,
        var pic_huge: String,
        var all_rate: String,
        var song_source: String,
        var song_id: String,
        var album_500_500: String,
        var rank: String,
        var pic_premium: String,
        var album_800_800: String,
        var info: String,
        var area: String,
        var si_proxycompany: String,
        var has_mv_mobile: Int ,
        var is_new: String,
        var author: String,
        var resource_type: String,
        var has_filmtv: String,
        var all_artist_ting_uid: String,
        var artist_id: String,
        var versions: String,
        var publishtime: String,
        var style: String,
        var album_id: String,
        var album_no: String,
        var resource_type_ext: String,
        var del_status: String,
        var hot: String,
        var toneid: String,
        var title: String,
        var pic_big: String,
        var relate_status: String,
        var rank_change: String,
        var lrclink: String,
        var file_duration: Int ,
        var havehigh: Int ,
        var charge: Int ,
        var pic_radio: String,
        var learn: Int ,
        var pic_s500: String,
        var all_artist_id: String,
        var pic_small: String,
        var bitrate_fee: String,
        var has_mv: Int ,
        var copy_type: String,
        var ting_uid: String,
        var album_title: String
    )
    /**
     * country : 内地
     * piao_id : 0
     * res_encryption_flag : 0
     * mv_provider : 0000000000
     * biaoshi : lossless,perm-1
     * artist_name : 冷凡
     * is_first_publish : 0
     * language : 国语
     * album_1000_1000 : http://qukufile2.qianqian.com/data2/pic/55f472ef374d4e3af0b7935a9df9437f/672600403/672600403.jpg
     * korean_bb_song : 0
     * pic_huge : http://qukufile2.qianqian.com/data2/pic/55f472ef374d4e3af0b7935a9df9437f/672600403/672600403.jpg
     * all_rate : 96,128,224,320,flac
     * song_source : web
     * song_id : 672600384
     * album_500_500 : http://qukufile2.qianqian.com/data2/pic/55f472ef374d4e3af0b7935a9df9437f/672600403/672600403.jpg@s_2,w_500,h_500
     * rank : 1
     * pic_premium : http://qukufile2.qianqian.com/data2/pic/55f472ef374d4e3af0b7935a9df9437f/672600403/672600403.jpg@s_2,w_500,h_500
     * album_800_800 :
     * info :
     * area : 0
     * si_proxycompany : 北京万上文化传媒有限公司
     * has_mv_mobile : 0
     * is_new : 1
     * author : 冷凡
     * resource_type : 0
     * has_filmtv : 0
     * all_artist_ting_uid : 340249647
     * artist_id : 559712946
     * versions :
     * publishtime : 2019-12-02
     * style :
     * album_id : 672600382
     * album_no : 1
     * resource_type_ext : 0
     * del_status : 0
     * hot : 18730
     * toneid : 0
     * title : 酒醉的蝴蝶
     * pic_big : http://qukufile2.qianqian.com/data2/pic/55f472ef374d4e3af0b7935a9df9437f/672600403/672600403.jpg@s_2,w_150,h_150
     * relate_status : 0
     * rank_change : 3
     * lrclink : http://qukufile2.qianqian.com/data2/lrc/62e555298f8d641824a7e58f9991f0aa/672600419/672600419.txt
     * file_duration : 205
     * havehigh : 2
     * charge : 0
     * pic_radio : http://qukufile2.qianqian.com/data2/pic/55f472ef374d4e3af0b7935a9df9437f/672600403/672600403.jpg@s_2,w_300,h_300
     * learn : 0
     * pic_s500 : http://qukufile2.qianqian.com/data2/pic/55f472ef374d4e3af0b7935a9df9437f/672600403/672600403.jpg@s_2,w_500,h_500
     * all_artist_id : 559712946
     * pic_small : http://qukufile2.qianqian.com/data2/pic/55f472ef374d4e3af0b7935a9df9437f/672600403/672600403.jpg@s_2,w_90,h_90
     * bitrate_fee : {"0":"0|0","1":"0|0"}
     * has_mv : 0
     * copy_type : 1
     * ting_uid : 340249647
     * album_title : 酒醉的蝴蝶
     */

}

//    /**
//     * code : 0
//     * msg : SUCCESS
//     * now : 1575866247681
//     * data : [{"type":"VIDEO","id":3395154,"title":"夜的尽头 官方版","description":"鹿晗","posterPic":"http://img3.c.yinyuetai.com/others/mobile_front_page/190903/0/-M-3786ad47009baa9a37142b8e0ede531a_0x0.jpg","thumbnailPic":"http://img4.c.yinyuetai.com/video/mv/190903/0/-M-a81ae33b8e6a026706ff5ce51b0a6315_240x135.jpg","url":"http://hc.yinyuetai.com/uploads/videos/common/FF0E016CF4EEF23105AAF686F7CF7A87.mp4?sc=c7fca97c22eec76b&br=783&rd=Android","hdUrl":"http://hc.yinyuetai.com/uploads/videos/common/FF0E016CF4EEF23105AAF686F7CF7A87.mp4?sc=c7fca97c22eec76b&br=783&rd=Android","uhdUrl":"http://hd.yinyuetai.com/uploads/videos/common/9916016CF4F5ECD8529154BC758F24EC.mp4?sc=40bae07535e4be72&br=1109&rd=Android","videoSize":21910587,"hdVideoSize":21910587,"uhdVideoSize":31003345,"status":200,"clickUrl":"https://mapiv2.yinyuetai.com/statistics/click.json?id=6582"},{"type":"VIDEO","id":3393912,"title":"你好吗我很好谢谢你呢 官方版","description":"李宇春","posterPic":"http://img1.c.yinyuetai.com/others/mobile_front_page/190823/0/-M-80db853bf9ccfd8c3a992dccc5281da0_0x0.png","thumbnailPic":"http://img1.c.yinyuetai.com/video/mv/190823/0/-M-ade44f0d8992cf14ffaf067485845401_240x135.png","url":"http://hc.yinyuetai.com/uploads/videos/common/682F016CBD95F23BF909B698F7CADB8B.mp4?sc=e8c2ad35d2b3ccaa&br=781&rd=Android","hdUrl":"http://hc.yinyuetai.com/uploads/videos/common/682F016CBD95F23BF909B698F7CADB8B.mp4?sc=e8c2ad35d2b3ccaa&br=781&rd=Android","uhdUrl":"http://hd.yinyuetai.com/uploads/videos/common/9F33016CBDAF74D39722C9FD9CCF4672.mp4?sc=a1b0c48fc10bb36e&br=1102&rd=Android","videoSize":25819131,"hdVideoSize":25819131,"uhdVideoSize":36415355,"status":200,"clickUrl":"https://mapiv2.yinyuetai.com/statistics/click.json?id=6581"},{"type":"VIDEO","id":3390796,"title":"这么久没见 官方版","description":"薛之谦","posterPic":"http://img3.c.yinyuetai.com/others/mobile_front_page/190806/0/-M-adf004698be56c3a54a59a6f49dc057d_0x0.jpg","thumbnailPic":"http://img4.c.yinyuetai.com/video/mv/190806/0/f7b2ed98bacade981ef78cc5dbbd78e5_240x135.jpg","url":"http://hc.yinyuetai.com/uploads/videos/common/0073016C64EFD8A984A340ABC3B32EE8.mp4?sc=f54ce951b67de964&br=777&rd=Android","hdUrl":"http://hc.yinyuetai.com/uploads/videos/common/0073016C64EFD8A984A340ABC3B32EE8.mp4?sc=f54ce951b67de964&br=777&rd=Android","uhdUrl":"http://hd.yinyuetai.com/uploads/videos/common/EAF4016C6511D08F8FD0F40900B15F1E.mp4?sc=f4024d3086c23a4e&br=1095&rd=Android","videoSize":28896393,"hdVideoSize":28896393,"uhdVideoSize":40708708,"status":200,"clickUrl":"https://mapiv2.yinyuetai.com/statistics/click.json?id=6580"},{"type":"VIDEO","id":3389598,"title":"YOUNG 官方版","description":"蔡徐坤","posterPic":"http://img2.c.yinyuetai.com/others/mobile_front_page/190726/0/-M-8d221c596a827770b9067a1c0638dfc7_0x0.jpg","thumbnailPic":"http://img4.c.yinyuetai.com/video/mv/190726/0/-M-a0ef3450c3d842add27d5b6c060c8615_240x135.jpg","url":"http://hc.yinyuetai.com/uploads/videos/common/AA13016C284D8957D34E83D629162E22.mp4?sc=2f492da8667f5604&br=782&rd=Android","hdUrl":"http://hc.yinyuetai.com/uploads/videos/common/AA13016C284D8957D34E83D629162E22.mp4?sc=2f492da8667f5604&br=782&rd=Android","uhdUrl":"http://hd.yinyuetai.com/uploads/videos/common/DC96016C2BFCBF39B62D9536F061237D.mp4?sc=ac8c9d6024c6ae53&br=1101&rd=Android","videoSize":23282312,"hdVideoSize":23282312,"uhdVideoSize":32789703,"status":200,"clickUrl":"https://mapiv2.yinyuetai.com/statistics/click.json?id=6579"},{"type":"VIDEO","id":3389109,"title":"慢半拍 官方版","description":"薛之谦","posterPic":"http://img1.c.yinyuetai.com/others/mobile_front_page/190719/0/-M-0d7b3644a3d19dab4641d77ff443ab54_0x0.jpg","thumbnailPic":"http://img1.c.yinyuetai.com/video/mv/190719/0/-M-a447b724054a1aa1da88b90f25e3a67d_240x135.jpg","url":"http://hc.yinyuetai.com/uploads/videos/common/E063016C084095C4383D3C62027F7E18.mp4?sc=57943ef7974fbe17&br=785&rd=Android","hdUrl":"http://hc.yinyuetai.com/uploads/videos/common/E063016C084095C4383D3C62027F7E18.mp4?sc=57943ef7974fbe17&br=785&rd=Android","uhdUrl":"http://hd.yinyuetai.com/uploads/videos/common/FB24016C086394B949600A4B5EEBD1C3.mp4?sc=18a47fecb406e80c&br=1110&rd=Android","videoSize":26949148,"hdVideoSize":26949148,"uhdVideoSize":38085415,"status":200,"clickUrl":"https://mapiv2.yinyuetai.com/statistics/click.json?id=6578"},{"type":"VIDEO","id":3386048,"title":"我们很好  电影《少年的你》主题曲","description":"林俊杰","posterPic":"http://img2.c.yinyuetai.com/others/mobile_front_page/190614/0/-M-a3ccf9462e5c3bbcc0875d555fc8ef65_0x0.jpg","thumbnailPic":"http://img0.c.yinyuetai.com/video/mv/190613/0/-M-dc4ba4f0e70f153f2975b6e575f91289_240x135.jpg","url":"http://hc.yinyuetai.com/uploads/videos/common/77D0016B51007FAA1A54BAAF486A9E9E.mp4?sc=20069a2efedee274&br=793&rd=Android","hdUrl":"http://hc.yinyuetai.com/uploads/videos/common/77D0016B51007FAA1A54BAAF486A9E9E.mp4?sc=20069a2efedee274&br=793&rd=Android","uhdUrl":"http://hd.yinyuetai.com/uploads/videos/common/84C8016B51223CCDDB6B65E864619698.mp4?sc=445975b41322da94&br=1120&rd=Android","videoSize":28933353,"hdVideoSize":28933353,"uhdVideoSize":40868808,"status":200,"clickUrl":"https://mapiv2.yinyuetai.com/statistics/click.json?id=6575"},{"type":"PROGRAM","subType":"VIDEO","id":3385413,"title":"爱豆企划社EP44 AKO王喆面试记","description":"爱豆企划社","posterPic":"http://img1.c.yinyuetai.com/others/mobile_front_page/190605/0/-M-09fee739304c615f72fb01ddcd938e88_0x0.jpg","thumbnailPic":"http://img2.c.yinyuetai.com/video/mv/190605/0/-M-73fbf8626235034991f7298748ce1848_240x135.jpg","url":"http://hc.yinyuetai.com/uploads/videos/common/CC1B016B26ED3E42358A01D01C4353A6.mp4?sc=ea481f8d36680a66&br=779&rd=Android","hdUrl":"http://hc.yinyuetai.com/uploads/videos/common/CC1B016B26ED3E42358A01D01C4353A6.mp4?sc=ea481f8d36680a66&br=779&rd=Android","uhdUrl":"http://hd.yinyuetai.com/uploads/videos/common/6C9F016B26F9875D2E092AC209D0B334.mp4?sc=7f966a88b925d0bb&br=1100&rd=Android","videoSize":76274200,"hdVideoSize":76274200,"uhdVideoSize":107657901,"status":200,"clickUrl":"https://mapiv2.yinyuetai.com/statistics/click.json?id=6574"},{"type":"PROGRAM","subType":"VIDEO","id":3385300,"title":"品▪Rapper▪冠使用手册-品冠专访","description":"品冠 & STAR!调查团","posterPic":"http://img2.c.yinyuetai.com/others/mobile_front_page/190604/0/-M-4ad8a89199a3b4777a68f4c6ecbfc27b_0x0.jpg","thumbnailPic":"http://img2.c.yinyuetai.com/video/mv/190604/0/-M-ea163ed793671dc18e317f65b6479502_240x135.jpg","url":"http://hc.yinyuetai.com/uploads/videos/common/E8C7016B20956742AD91F7D3D06B5CEE.mp4?sc=942f9f8decaec17e&br=783&rd=Android","hdUrl":"http://hc.yinyuetai.com/uploads/videos/common/E8C7016B20956742AD91F7D3D06B5CEE.mp4?sc=942f9f8decaec17e&br=783&rd=Android","uhdUrl":"http://hd.yinyuetai.com/uploads/videos/common/0537016B21EFC918B99F35C711330E58.mp4?sc=d123a6537be78c3d&br=1104&rd=Android","videoSize":42817323,"hdVideoSize":42817323,"uhdVideoSize":60330228,"status":200,"clickUrl":"https://mapiv2.yinyuetai.com/statistics/click.json?id=6573"},{"type":"PROGRAM","subType":"VIDEO","id":3385103,"title":"V榜TOP10 2019 第二十二期 蔡徐坤＆飞儿乐团＆Justin Bieber＆咸慇晶＆早安少女组","description":"音悦V榜 & 蔡徐坤 & 飞儿乐团 & Justin Bieber & 咸慇晶(T-ara)","posterPic":"http://img2.c.yinyuetai.com/others/mobile_front_page/190603/0/-M-9e2151c172a5557c5840e9d566c4be01_0x0.jpg","thumbnailPic":"http://img1.c.yinyuetai.com/video/mv/190603/0/-M-3f0166c468c0a3364a1d2d6de5ea113c_240x135.jpg","url":"http://hc.yinyuetai.com/uploads/videos/common/A693016B1CCEA37CCBAB1D9CF473CD61.mp4?sc=65bcbda6c9a80120&br=785&rd=Android","hdUrl":"http://hc.yinyuetai.com/uploads/videos/common/A693016B1CCEA37CCBAB1D9CF473CD61.mp4?sc=65bcbda6c9a80120&br=785&rd=Android","uhdUrl":"http://hd.yinyuetai.com/uploads/videos/common/752B016B1CD522DD06F1243EA0F0485E.mp4?sc=0afa1296be129987&br=1106&rd=Android","videoSize":88406651,"hdVideoSize":88406651,"uhdVideoSize":124547467,"status":200,"clickUrl":"https://mapiv2.yinyuetai.com/statistics/click.json?id=6572"},{"type":"PROGRAM","subType":"VIDEO","id":3384804,"title":"爱豆企划社EP43 Black ACE超FREE面试记","description":"爱豆企划社","posterPic":"http://img3.c.yinyuetai.com/others/mobile_front_page/190531/0/-M-b88a1b92b29c9b81a24f8a7683c2287e_0x0.jpg","thumbnailPic":"http://img0.c.yinyuetai.com/video/mv/190531/0/-M-c5ac1d0c1863966a561957e484b05272_240x135.jpg","url":"http://hc.yinyuetai.com/uploads/videos/common/0E4D016B1BC63CBC13E534E70B185CA2.mp4?sc=2b03903817c7c28a&br=785&rd=Android","hdUrl":"http://hc.yinyuetai.com/uploads/videos/common/0E4D016B1BC63CBC13E534E70B185CA2.mp4?sc=2b03903817c7c28a&br=785&rd=Android","uhdUrl":"http://hd.yinyuetai.com/uploads/videos/common/0E8A016B1BD9511523D175E5DD062961.mp4?sc=d04f10110f9a3376&br=1101&rd=Android","videoSize":157616516,"hdVideoSize":157616516,"uhdVideoSize":221105658,"status":200,"clickUrl":"https://mapiv2.yinyuetai.com/statistics/click.json?id=6571"},{"type":"PROGRAM","subType":"VIDEO","id":3384235,"title":"V榜TOP10 2019 第二十一期 蔡徐坤＆蔡依林＆TaylorSwift＆赤西仁＆咸慇晶","description":"音悦V榜 & 蔡徐坤 & Taylor Swift & 蔡依林 & 咸慇晶(T-ara) & 赤西仁","posterPic":"http://img3.c.yinyuetai.com/others/mobile_front_page/190527/0/-M-2b4ff686a5a7edddfae52d575f770961_0x0.jpg","thumbnailPic":"http://img4.c.yinyuetai.com/video/mv/190527/0/-M-69071f70fa7374c06593786c6e51c5d7_240x135.jpg","url":"http://hc.yinyuetai.com/uploads/videos/common/7242016AF8A4F48652A9ADE414962F34.mp4?sc=0c008e5b84155a89&br=785&rd=Android","hdUrl":"http://hc.yinyuetai.com/uploads/videos/common/7242016AF8A4F48652A9ADE414962F34.mp4?sc=0c008e5b84155a89&br=785&rd=Android","uhdUrl":"http://hd.yinyuetai.com/uploads/videos/common/FC69016AF8AECB9D72458964910F92B2.mp4?sc=e0d2be416ba55b70&br=1108&rd=Android","videoSize":88355978,"hdVideoSize":88355978,"uhdVideoSize":124681342,"status":200,"clickUrl":"https://mapiv2.yinyuetai.com/statistics/click.json?id=6570"},{"type":"VIDEO","id":3384215,"title":"造梦者","description":"飞儿乐团","posterPic":"http://img4.c.yinyuetai.com/others/mobile_front_page/190527/0/-M-49cb8e75549a2ff1980e787ee4d32128_0x0.jpg","thumbnailPic":"http://img2.c.yinyuetai.com/video/mv/190527/0/-M-244345846f90ba49d748d6172e07b2f1_240x135.jpg","url":"http://hc.yinyuetai.com/uploads/videos/common/B0E0016AF7F774A01341401EA4E7EFA0.mp4?sc=494cdc5f86998e27&br=778&rd=Android","hdUrl":"http://hc.yinyuetai.com/uploads/videos/common/B0E0016AF7F774A01341401EA4E7EFA0.mp4?sc=494cdc5f86998e27&br=778&rd=Android","uhdUrl":"http://hd.yinyuetai.com/uploads/videos/common/D1C6016AF801FC17611CA853D9BEC885.mp4?sc=6f427634580e253c&br=1096&rd=Android","videoSize":20700370,"hdVideoSize":20700370,"uhdVideoSize":29156246,"status":200,"clickUrl":"https://mapiv2.yinyuetai.com/statistics/click.json?id=6569"},{"type":"PROGRAM","subType":"VIDEO","id":3383936,"title":"郁可唯:一个被歌唱事业耽误的\u201c电竞女神\u201d","description":"郁可唯 & STAR!调查团","posterPic":"http://img4.c.yinyuetai.com/others/mobile_front_page/190524/0/-M-558f0cdb32824d5bd8863fb738c6ea89_0x0.jpg","thumbnailPic":"http://img0.c.yinyuetai.com/video/mv/190524/0/-M-0121e681bc04ecd4f4a21c5996157fcc_240x135.jpg","url":"http://hc.yinyuetai.com/uploads/videos/common/296F016AE45FBCAD75FA14F0562A7499.mp4?sc=1858d9ea7db545b6&br=784&rd=Android","hdUrl":"http://hc.yinyuetai.com/uploads/videos/common/296F016AE45FBCAD75FA14F0562A7499.mp4?sc=1858d9ea7db545b6&br=784&rd=Android","uhdUrl":"http://hd.yinyuetai.com/uploads/videos/common/2952016AE94451AF8DD7AE2812F1FCB7.mp4?sc=5e441ed3be393513&br=1106&rd=Android","videoSize":49240682,"hdVideoSize":49240682,"uhdVideoSize":69462112,"status":200,"clickUrl":"https://mapiv2.yinyuetai.com/statistics/click.json?id=6568"},{"type":"PROGRAM","subType":"VIDEO","id":3383373,"title":"V榜TOP10 2019 第二十期 蔡徐坤＆蔡依林＆TaylorSwift＆赤西仁＆EXID","description":"音悦V榜 & 蔡徐坤 & Taylor Swift & 蔡依林 & 赤西仁 & EXID","posterPic":"http://img4.c.yinyuetai.com/others/mobile_front_page/190520/0/-M-dbef248addca01fbbce26c9ffc8cf047_0x0.jpg","thumbnailPic":"http://img4.c.yinyuetai.com/video/mv/190520/0/-M-47379c263d99e729cf65d20182a1648a_240x135.jpg","url":"http://hc.yinyuetai.com/uploads/videos/common/FE14016AD4BA8903F4F0CD8469A062A8.mp4?sc=53ec8cbc1dded22a&br=784&rd=Android","hdUrl":"http://hc.yinyuetai.com/uploads/videos/common/FE14016AD4BA8903F4F0CD8469A062A8.mp4?sc=53ec8cbc1dded22a&br=784&rd=Android","uhdUrl":"http://hd.yinyuetai.com/uploads/videos/common/C3D4016AD4D3255D15A1A7889E1902A4.mp4?sc=98f9061e67236b1f&br=1105&rd=Android","videoSize":88213806,"hdVideoSize":88213806,"uhdVideoSize":124396468,"status":200,"clickUrl":"https://mapiv2.yinyuetai.com/statistics/click.json?id=6567"},{"type":"VIDEO","id":3383354,"title":"ECLIPSE","description":"GOT7","posterPic":"http://img3.c.yinyuetai.com/others/mobile_front_page/190520/0/-M-f1b0a2ab3910a5b39905f9925475f466_0x0.jpg","thumbnailPic":"http://img2.c.yinyuetai.com/video/mv/190520/0/64c8b95dd8aee4db1f92ab1dca039aa3_240x135.jpg","url":"http://hc.yinyuetai.com/uploads/videos/common/DD0E016AD4818999253E52B8C0C37314.mp4?sc=cc51db167d895afd&br=786&rd=Android","hdUrl":"http://hc.yinyuetai.com/uploads/videos/common/DD0E016AD4818999253E52B8C0C37314.mp4?sc=cc51db167d895afd&br=786&rd=Android","uhdUrl":"http://hd.yinyuetai.com/uploads/videos/common/9E5E016AD48DD2C90346295E48527505.mp4?sc=118a4bc98ca206bf&br=1098&rd=Android","videoSize":22302529,"hdVideoSize":22302529,"uhdVideoSize":31123899,"status":200,"clickUrl":"https://mapiv2.yinyuetai.com/statistics/click.json?id=6566"},{"type":"PROGRAM","subType":"VIDEO","id":3383122,"title":"爱豆企划社EP42 沙漠五子D5面试记 上","description":"爱豆企划社","posterPic":"http://img1.c.yinyuetai.com/others/mobile_front_page/190517/0/-M-b51484e89b8480ab6806ab9ead59d2c3_0x0.jpg","thumbnailPic":"http://img4.c.yinyuetai.com/video/mv/190517/0/-M-46a5e8f7039e98ec626c5689123aff31_240x135.jpg","url":"http://hc.yinyuetai.com/uploads/videos/common/6E26016AC53894CDEA8D3383B54D1E6D.mp4?sc=6608b9fcdeb676f9&br=773&rd=Android","hdUrl":"http://hc.yinyuetai.com/uploads/videos/common/6E26016AC53894CDEA8D3383B54D1E6D.mp4?sc=6608b9fcdeb676f9&br=773&rd=Android","uhdUrl":"http://hd.yinyuetai.com/uploads/videos/common/1965016AC53D49DC12A02BD06621AC04.mp4?sc=9ffaff7786100165&br=1099&rd=Android","videoSize":121626430,"hdVideoSize":121626430,"uhdVideoSize":172885408,"status":200,"clickUrl":"https://mapiv2.yinyuetai.com/statistics/click.json?id=6565"},{"type":"VIDEO","id":3382803,"title":"顽固 LIVE版 MV","description":"五月天 & 梁家辉","posterPic":"http://img1.c.yinyuetai.com/others/mobile_front_page/190517/0/-M-d802fcedec543e37c126947e024eb22d_0x0.jpg","thumbnailPic":"http://img0.c.yinyuetai.com/video/mv/190515/0/-M-5805fbe865bd9f4e78e521641127b718_240x135.jpg","url":"http://hc.yinyuetai.com/uploads/videos/common/9465016ABAC2FA238FE32A84B0C319F3.mp4?sc=1ec62a8ea85d95a1&br=780&rd=Android","hdUrl":"http://hc.yinyuetai.com/uploads/videos/common/9465016ABAC2FA238FE32A84B0C319F3.mp4?sc=1ec62a8ea85d95a1&br=780&rd=Android","uhdUrl":"http://hd.yinyuetai.com/uploads/videos/common/81D5016ABAD40FAF686D175C05150DC4.mp4?sc=5c0657a84532b90a&br=1101&rd=Android","videoSize":27706735,"hdVideoSize":27706735,"uhdVideoSize":39137649,"status":200,"clickUrl":"https://mapiv2.yinyuetai.com/statistics/click.json?id=6564"},{"type":"VIDEO","id":3382957,"title":"\u201c火箭少女101\u201d 孟美岐 使用手册","description":"STAR!调查团 & 孟美岐(火箭少女101) & 火箭少女101","posterPic":"http://img1.c.yinyuetai.com/others/mobile_front_page/190516/0/-M-dab6c1d89a942ba5eddc4c6d803f8d8c_0x0.jpg","thumbnailPic":"http://img3.c.yinyuetai.com/video/mv/190516/0/-M-6e846bb4350d360b06bab58e69e46e47_240x135.jpg","url":"http://hc.yinyuetai.com/uploads/videos/common/CC81016ABFC550EB2B48A257EA6592F8.mp4?sc=1dd7eb246fb5add3&br=778&rd=Android","hdUrl":"http://hc.yinyuetai.com/uploads/videos/common/CC81016ABFC550EB2B48A257EA6592F8.mp4?sc=1dd7eb246fb5add3&br=778&rd=Android","uhdUrl":"http://hd.yinyuetai.com/uploads/videos/common/7DB0016AC00B8C35F61142EFAA9A3B0A.mp4?sc=877c8c34bc7169c6&br=1096&rd=Android","videoSize":48092325,"hdVideoSize":48092325,"uhdVideoSize":67766960,"status":200,"clickUrl":"https://mapiv2.yinyuetai.com/statistics/click.json?id=6563"},{"type":"VIDEO","id":3382595,"title":"体会(Nature)","description":"鹿晗","posterPic":"http://img4.c.yinyuetai.com/others/mobile_front_page/190515/0/-M-a49f97410d4f2cbf0e3d78fe9dae10af_0x0.jpg","thumbnailPic":"http://img3.c.yinyuetai.com/video/mv/190514/0/-M-c0da6ff8e48c66cff004afe5e366ea09_240x135.jpg","url":"http://hc.yinyuetai.com/uploads/videos/common/AB77016AB4E200F9D8FA7F334B07CC14.mp4?sc=1402ac86a49e0ef5&br=777&rd=Android","hdUrl":"http://hc.yinyuetai.com/uploads/videos/common/AB77016AB4E200F9D8FA7F334B07CC14.mp4?sc=1402ac86a49e0ef5&br=777&rd=Android","uhdUrl":"http://hd.yinyuetai.com/uploads/videos/common/7A13016AB4E638F88F4A0D23618F56F1.mp4?sc=896eaa7d474f0fe0&br=1095&rd=Android","videoSize":21978316,"hdVideoSize":21978316,"uhdVideoSize":30966074,"status":200,"clickUrl":"https://mapiv2.yinyuetai.com/statistics/click.json?id=6562"},{"type":"PROGRAM","subType":"VIDEO","id":3382637,"title":"跟着威神V一起冲上云霄吧 - 威神V专访","description":"STAR!调查团 & 威神V","posterPic":"http://img0.c.yinyuetai.com/others/mobile_front_page/190514/0/-M-d5217dc8cd63033d8595548bbd367296_0x0.jpg","thumbnailPic":"http://img2.c.yinyuetai.com/video/mv/190514/0/-M-ab519f582cd48b78902c0585dd3ac4b8_240x135.jpg","url":"http://hc.yinyuetai.com/uploads/videos/common/746A016AB4D2ACC9A8A882D0EB0DD7D6.mp4?sc=df9a82e049a5112f&br=779&rd=Android","hdUrl":"http://hc.yinyuetai.com/uploads/videos/common/746A016AB4D2ACC9A8A882D0EB0DD7D6.mp4?sc=df9a82e049a5112f&br=779&rd=Android","uhdUrl":"http://hd.yinyuetai.com/uploads/videos/common/331F016AB5CB83280C479EAC4B48BCD9.mp4?sc=f4cd085daaee47e6&br=1099&rd=Android","videoSize":74924958,"hdVideoSize":74924958,"uhdVideoSize":105642177,"status":200,"clickUrl":"https://mapiv2.yinyuetai.com/statistics/click.json?id=6561"}]
//     * cost : 1487
//     */
//
//    var code: String? = null
//    var msg: String? = null
//    var now: Long = 0
//    var cost: Int = 0
//    var data: List<DataBean>? = null
//
//    class DataBean {
//        /**
//         * type : VIDEO
//         * id : 3395154
//         * title : 夜的尽头 官方版
//         * description : 鹿晗
//         * posterPic : http://img3.c.yinyuetai.com/others/mobile_front_page/190903/0/-M-3786ad47009baa9a37142b8e0ede531a_0x0.jpg
//         * thumbnailPic : http://img4.c.yinyuetai.com/video/mv/190903/0/-M-a81ae33b8e6a026706ff5ce51b0a6315_240x135.jpg
//         * url : http://hc.yinyuetai.com/uploads/videos/common/FF0E016CF4EEF23105AAF686F7CF7A87.mp4?sc=c7fca97c22eec76b&br=783&rd=Android
//         * hdUrl : http://hc.yinyuetai.com/uploads/videos/common/FF0E016CF4EEF23105AAF686F7CF7A87.mp4?sc=c7fca97c22eec76b&br=783&rd=Android
//         * uhdUrl : http://hd.yinyuetai.com/uploads/videos/common/9916016CF4F5ECD8529154BC758F24EC.mp4?sc=40bae07535e4be72&br=1109&rd=Android
//         * videoSize : 21910587
//         * hdVideoSize : 21910587
//         * uhdVideoSize : 31003345
//         * status : 200
//         * clickUrl : https://mapiv2.yinyuetai.com/statistics/click.json?id=6582
//         * subType : VIDEO
//         */
//
//        var type: String? = null
//        var id: Int = 0
//        var title: String? = null
//        var description: String? = null
//        var posterPic: String? = null
//        var thumbnailPic: String? = null
//        var url: String? = null
//        var hdUrl: String? = null
//        var uhdUrl: String? = null
//        var videoSize: Int = 0
//        var hdVideoSize: Int = 0
//        var uhdVideoSize: Int = 0
//        var status: Int = 0
//        var clickUrl: String? = null
//        var subType: String? = null
//    }
//}
