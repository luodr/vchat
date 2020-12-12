/*
 * @Description: 朋友圈数据
 * @Version: 1.0
 * @Autor: 
 * @Date: 2020-07-20 16:14:16
 * @LastEditors: 
 * @LastEditTime: 2020-07-22 12:11:49
 */
import { setStore } from "@/utils/timeFunc.js";

let wechatMoments = [
    {
        id: 1, username: '李一桐', type: 1, content: '纤云弄巧，飞星传恨，银汉迢迢暗度。金风玉露一相逢，便胜却人间无数。柔情似水，佳期如梦，忍顾鹊桥归路！两情若是久长时，又岂在朝朝暮暮。',
        face: require('@/static/images/l.png'),
        imgList: [
            { id: 100, url: require('@/static/images/h.png') },
            { id: 101, url: require('@/static/images/Guai.jpg') },
            { id: 102, url: require('@/static/images/n.png') }
        ],
        time: 1595234453000,
        active: false,
        persons: [
            { id: 1, name: "周星驰" },
            { id: 2, name: "刘德华" },
            { id: 3, name: "周润发" },
            { id: 4, name: "张学友" },
            { id: 5, name: "关之琳" },
            { id: 6, name: "李嘉欣" },
            { id: 7, name: "邱淑贞" },
            { id: 8, name: "蓝洁瑛" },
            { id: 9, name: "陈德容" },
            { id: 10, name: "李连杰" },
            { id: 11, name: "黎姿" },
            { id: 12, name: "王祖贤" },
            { id: 13, name: "成龙" },
        ]
    },
    {
        id: 4, username: '李嘉欣', type: 1, content: '去年今日此门中，人面桃花相映红。人面不知何处去，桃花依旧笑春风。',
        imgList: [
            { id: 103, url: require('@/static/images/h.png') },
            { id: 104, url: require('@/static/images/l.png') },
            { id: 105, url: require('@/static/images/q.png') },
            { id: 106, url: require('@/static/images/orange.jpg') },
        ],
        time: 1595233433000,
        face: require('@/static/images/小姨妈.jpg'),
        active: false,
        persons: [
            { id: 1, name: "周星驰" },
            { id: 9, name: "陈德容" },
            { id: 10, name: "李连杰" },
            { id: 11, name: "黎姿" },
            { id: 12, name: "王祖贤" },
            { id: 13, name: "成龙" }
        ]
    },
    {
        id: 2, username: '谢霆锋', type: 1, content: ' 重重帷幕深垂，我孤居莫愁堂；独卧不眠，更觉静夜漫漫长长。巫山神女艳遇楚王，原是梦幻；青溪小姑住所，本就独处无郎。 我是柔弱菱枝，偏遭风波摧残；我是铃芳桂叶，却无月露香。虽然深知沉溺相思，无益健康；我却痴情到底，落个终身清狂。',
        imgList: [
            { id: 109, url: require('@/static/images/h.png') },
            { id: 110, url: require('@/static/images/n.png') }
        ],
        time: 1595229233000,
        face: require('@/static/images/UserAvatar.jpg'),
        active: false,
        persons: [
            { id: 4, name: "张学友" },
            { id: 5, name: "关之琳" },
            { id: 6, name: "李嘉欣" },
            { id: 7, name: "邱淑贞" },
            { id: 8, name: "蓝洁瑛" },
            { id: 9, name: "陈德容" },
            { id: 10, name: "李连杰" },
            { id: 13, name: "成龙" }
        ]
    },
    {
        id: 3, username: '周星驰', type: 1, content: ' 君知妾有夫，赠妾双明珠。感君缠绵意，系在红罗襦。妾家高楼连苑起，良人执戟明光里。知君用心如日月，事夫誓拟同生死。 还君明珠双泪垂，何不相逢未嫁时。',
        imgList: [
            { id: 108, url: require('@/static/images/h.png') },
            { id: 107, url: require('@/static/images/o.png') }
        ],
        time: 1595147613000,
        face: require('@/static/images/新之助.jpg'),
        active: false,
        persons: [
            { id: 1, name: "周星驰" },
            { id: 10, name: "李连杰" },
            { id: 11, name: "黎姿" },
            { id: 12, name: "王祖贤" },
            { id: 13, name: "成龙" }
        ]
    },
    {
        id: 5, username: '关之琳', type: 1, content: ' 伫倚危楼风细细，望极春愁，黯黯生天际。草色烟光残照里，无言谁会凭阑意？拟把疏狂图一醉，对酒当歌，强乐还无味。衣带渐宽终不悔，为伊消得人憔悴。',
        time: 1595307209000,
        face: require('@/static/images/q.png'),
        active: false,
        persons: [
            { id: 1, name: "周星驰" },
            { id: 10, name: "李连杰" },
            { id: 11, name: "黎姿" },
            { id: 12, name: "王祖贤" },
            { id: 13, name: "成龙" }
        ]
    },
    {
        id: 6, username: '王晶', type: 1, content: '寒蝉凄切，对长亭晚，骤雨初歇。都门帐饮无绪，留恋处、兰舟催发。执手相看泪眼，竟无语凝噎。念去去、千里烟波，暮霭沉沉楚天阔。多情自古伤离别，更那堪冷落清秋节。今宵酒醒何处，杨柳岸、晓风残月。此去经年，应是良辰好景虚设。便纵有千种风情，更与何人说。',
        video: ['http://video.699pic.com/videos/75/76/40/a_U1sxjzXuyWAj1591757640.MP4', 'http://video.699pic.com/videos/23/82/11/a_5iFyQpDcP1YJ1588238211.mp4'],
        time: 1595310029000,
        face: require('@/static/images/新之助.jpg'),
        active: false,
        persons: [
            { id: 1, name: "周星驰" },
            { id: 10, name: "李连杰" },
            { id: 11, name: "黎姿" },
            { id: 12, name: "王祖贤" },
            { id: 13, name: "成龙" }
        ]
    },
    {
        id: 7, username: '张家辉', type: 1, content: '寒蝉凄切，对长亭晚，骤雨初歇。都门帐饮无绪，留恋处、兰舟催发。执手相看泪眼，竟无语凝噎。念去去、千里烟波，暮霭沉沉楚天阔。多情自古伤离别，更那堪冷落清秋节。今宵酒醒何处，杨柳岸、晓风残月。此去经年，应是良辰好景虚设。便纵有千种风情，更与何人说。',
        video: ['http://video.699pic.com/videos/19/48/12/a_xShZzsmPgRpf1584194813.mp4', 'http://video.699pic.com/videos/36/37/93/a_XgA4njhTCM0d1587363793.mp4'],
        time: 1595310800000,
        face: require('@/static/images/UserAvatar.jpg'),
        active: false,
        persons: [
            { id: 1, name: "周星驰" },
            { id: 10, name: "李连杰" },
            { id: 11, name: "黎姿" },
            { id: 6, name: "李嘉欣" },
            { id: 7, name: "邱淑贞" },
            { id: 8, name: "蓝洁瑛" },
            { id: 9, name: "陈德容" },
            { id: 12, name: "王祖贤" },
            { id: 13, name: "成龙" }
        ]
    },
    {
        id: 8, username: '刘德华', type: 1, content: '花褪残红青杏小。燕子飞时，绿水人家绕。枝上柳棉吹又少。天涯何处无芳草。墙里秋千墙外道。墙外行人，墙里佳人笑。笑渐不闻声渐悄。多情却被无情恼。',
        video: ['http://video.699pic.com/videos/00/36/26/a_WXNijvu4QS7R1593003626.mp4'],
        time: 1595310800000,
        face: require('@/static/images/father.jpg'),
        active: false,
        persons: [
            { id: 1, name: "周星驰" },
            { id: 10, name: "李连杰" },
            { id: 11, name: "黎姿" },
            { id: 6, name: "李嘉欣" },
            { id: 7, name: "邱淑贞" },
        ]
    },

]
// 设置缓存
setStore("moments", wechatMoments);
export default wechatMoments;