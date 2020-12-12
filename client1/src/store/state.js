//获取当前时间
const now = new Date();
const state = {
  // 设置选项列表
  setList: [{ id: 1, name: '意见反馈' }, { id: 2, name: '恢复和备份' }, { id: 3, name: '设置' }],
  // 输入的搜索值
  searchText: '',
  // 用户状态
  onlineStatusList: [
    { id: 1, name: '在线' }, { id: 2, name: '离开' }, { id: 3, name: '忙碌' }, { id: 4, name: '隐身' }
  ],
  // 聊天背景图
  backImg: require('@/static/images/bg/bg.png'),
  backImg2: require('@/static/images/bg/bg3.jpg'),
  // 当前登录用户
  user: {
    id:'',
    name: '',
    img: require('@/static/images/UserAvatar.jpg'),
    onLineStatus: 1, //用户当前状态
    socketid: '',
    email: '',
    logintime: Date.now(),
    msgCount: 18
  },
  // 新的朋友
  newFriendList: [
    {
      id: 100,
      wxid: "刘德华", //微信号
      initial: 'L', //姓名首字母
      img: require('@/static/images/UserAvatar.jpg'), //头像
      signature: "你好呀，我是刘德华！", //介绍文字
      nickname: "liudehua", //昵称
      sex: 1, //性别 1为男，0为女
      remark: "LDH", //备注
      area: "香港 九龙", //地区
      status: 1,   // 1：显示 同意  拒绝 2 已添加 3 已拒绝
    },
    {
      id: 101,
      wxid: "周星驰", //微信号
      initial: 'Z', //姓名首字母
      img: require('@/static/images/p.png'), //头像
      signature: "你好呀，我是喜剧之王！", //介绍文字
      nickname: "zxc-9527", //昵称
      sex: 1, //性别 1为男，0为女
      remark: "喜剧之王-周星驰", //备注
      area: "香港 九龙", //地区
      status: 2,   // 1：显示 同意  拒绝 2 已添加 3 已拒绝
    },
    {
      id: 102,
      wxid: "王祖贤", //微信号
      initial: 'W', //姓名首字母
      img: require('@/static/images/l.png'), //头像
      signature: "你好呀，我是王祖贤", //介绍文字
      nickname: "wzx", //昵称
      sex: 0, //性别 1为男，0为女
      remark: "倩女幽魂", //备注
      area: "香港 旺角", //地区
      status: 1,   // 1：显示 同意  拒绝 2 已添加 3 已拒绝
    },
    {
      id: 103,
      wxid: "梁朝伟", //微信号
      initial: 'L', //姓名首字母
      img: require('@/static/images/m.png'), //头像
      signature: "你好呀，我是梁朝伟", //介绍文字
      nickname: "lcw", //昵称
      sex: 1, //性别 1为男，0为女
      remark: "忧郁男神", //备注
      area: "香港 旺角", //地区
      status: 1,   // 1：显示 同意  拒绝 2 已添加 3 已拒绝
    },
    {
      id: 104,
      wxid: "张学友", //微信号
      initial: 'Z', //姓名首字母
      img: require('@/static/images/h.png'), //头像
      signature: "你好呀，我是张学友", //介绍文字
      nickname: "zxy", //昵称
      sex: 1, //性别 1为男，0为女
      remark: "四大天王", //备注
      area: "香港 旺角", //地区
      status: 1,   // 1：显示 同意  拒绝 2 已添加 3 已拒绝
    },
    {
      id: 105,
      wxid: "周慧敏", //微信号
      initial: 'Z', //姓名首字母
      img: require('@/static/images/q.png'), //头像
      signature: "你好呀，我是周慧敏", //介绍文字
      nickname: "zhm", //昵称
      sex: 1, //性别 1为男，0为女
      remark: "玉女掌门人", //备注
      area: "香港 旺角", //地区
      status: 1,   // 1：显示 同意  拒绝 2 已添加 3 已拒绝
    },
  ],
  // 对话好友列表
  chatlist: [{
    id: 1,
    user: {
      name: '小可爱',
      img: require('@/static/images/mother.jpg')
    },
    messages: [{
      content: '么么哒，妈咪爱你', //聊天内容
      date: now //时间
    },
    {
      content: '按回车可以发送信息，还可以给我发送表情哟',
      date: now
    }
    ],
    index: 1 // 当前在聊天列表中的位置,从1开始
  },
  {
    id: 2,
    user: {
      name: '彭于晏',
      img: require('@/static/images/father.jpg')
    },
    messages: [{
      content: 'Are you kidding me?',
      date: now
    }],
    index: 2
  },
  {
    id: 3,
    user: {
      name: '机器人',
      img: require('@/static/images/vue.jpg')
    },
    messages: [{
      content: '我会跟你聊聊天的哟',
      date: now
    }],
    index: 3
  }
  ],
  // 好友列表
  friendlist: [{
    id: 0,
    wxid: "", //微信号
    initial: '新的朋友', //姓名首字母
    img: require('@/static/images/newfriend.jpg'), //头像
    signature: "", //个性签名
    nickname: "新的朋友", //昵称
    sex: 0, //性别 1为男，0为女
    remark: "新的朋友", //备注
    area: "", //地区
  },
  {
    id: 1,
    wxid: "AmorAres-1", //微信号
    initial: 'A', //姓名首字母
    img: require('@/static/images/小姨妈.jpg'), //头像
    signature: "每天我就萌萌哒", //个性签名
    nickname: "Amor", //昵称
    sex: 0, //性别 1为男，0为女
    remark: "Amor", //备注
    area: "浙江 宁波", //地区
  },
  {
    id: 2,
    wxid: "Big-fly",
    initial: 'B',
    img: require('@/static/images/大飞哥.jpg'),
    signature: "你不知道的js",
    nickname: "fly",
    sex: 1,
    remark: "海绵宝宝",
    area: "奥地利 布尔根兰",
  },
  {
    id: 3,
    wxid: "microzz",
    initial: 'D',
    img: require('@/static/images/microzz.jpg'),
    signature: "学习让我快乐让我成长",
    nickname: "microzz",
    sex: 1,
    remark: "李一桐",
    area: "江西 赣州",
  },
  {
    id: 4,
    wxid: "hwn0366",
    initial: 'F',
    img: require('@/static/images/father.jpg'),
    signature: "学习让我快乐让我成长",
    nickname: "丢",
    sex: 1,
    remark: "彭于晏",
    area: "江西 抚州",
  },
  {
    id: 5,
    wxid: "orange66",
    initial: 'J',
    img: require('@/static/images/orange.jpg'),
    signature: "你可以笑的很阳光！",
    nickname: "橘子君",
    sex: 1,
    remark: "橘子君",
    area: "江西 赣州",
  },
  {
    id: 6,
    wxid: "Seto_L",
    img: require('@/static/images/加菲猫.jpg'),
    signature: "自强不息",
    nickname: "21",
    sex: 1,
    remark: "加菲猫",
    area: "北京 海淀",
  },
  {
    id: 7,
    wxid: "wxid_itjz73t1ajt722",
    initial: 'M',
    img: require('@/static/images/mother.jpg'),
    signature: "开开心心就好",
    nickname: "娄娄",
    sex: 0,
    remark: "小可爱~",
    area: "江西 抚州",
  },
  {
    id: 8,
    wxid: "hj960503",
    img: require('@/static/images/萌萌俊.jpg'),
    signature: "原谅我有点蠢。。",
    nickname: "。。。。。",
    sex: 1,
    remark: "萌萌咪",
    area: "江西 萍乡",
  }

  ],
  //emoji表情
  emojis: [
    { file: '100.gif', code: '/::)', title: '微笑', reg: /\/::\)/g },
    { file: '101.gif', code: '/::~', title: '伤心', reg: /\/::~/g },
    { file: '102.gif', code: '/::B', title: '美女', reg: /\/::B/g },
    { file: '103.gif', code: '/::|', title: '发呆', reg: /\/::\|/g },
    { file: '104.gif', code: '/:8-)', title: '墨镜', reg: /\/:8-\)/g },
    { file: '105.gif', code: '/::<', title: '哭', reg: /\/::</g },
    { file: '106.gif', code: '/::$', title: '羞', reg: /\/::\$/g },
    { file: '107.gif', code: '/::X', title: '哑', reg: /\/::X/g },
    { file: '108.gif', code: '/::Z', title: '睡', reg: /\/::Z/g },
    { file: '109.gif', code: '/::\'(', title: '哭', reg: /\/::'\(/g },
    { file: '110.gif', code: '/::-|', title: '囧', reg: /\/::-\|/g },
    { file: '111.gif', code: '/::@', title: '怒', reg: /\/::@/g },
    { file: '112.gif', code: '/::P', title: '调皮', reg: /\/::P/g },
    { file: '113.gif', code: '/::D', title: '笑', reg: /\/::D/g },
    { file: '114.gif', code: '/::O', title: '惊讶', reg: /\/::O/g },
    { file: '115.gif', code: '/::(', title: '难过', reg: /\/::\(/g },
    { file: '116.gif', code: '/::+', title: '酷', reg: /\/::\+/g },
    { file: '117.gif', code: '/:--b', title: '汗', reg: /\/:--b/g },
    { file: '118.gif', code: '/::Q', title: '抓狂', reg: /\/::Q/g },
    { file: '119.gif', code: '/::T', title: '吐', reg: /\/::T/g },
    { file: '120.gif', code: '/:,@P', title: '笑', reg: /\/:,@P/g },
    { file: '121.gif', code: '/:,@-D', title: '快乐', reg: /\/:,@-D/g },
    { file: '122.gif', code: '/::d', title: '奇', reg: /\/::d/g },
    { file: '123.gif', code: '/:,@o', title: '傲', reg: /\/:,@o/g },
    { file: '124.gif', code: '/::g', title: '饿', reg: /\/::g/g },
    { file: '125.gif', code: '/:|-)', title: '累', reg: /\/:\|-\)/g },
    { file: '126.gif', code: '/::!', title: '吓', reg: /\/::!/g },
    { file: '127.gif', code: '/::L', title: '汗', reg: /\/::L/g },
    { file: '128.gif', code: '/::>', title: '高兴', reg: /\/::>/g },
    { file: '129.gif', code: '/::,@', title: '闲', reg: /\/::,@/g },
    { file: '130.gif', code: '/:,@f', title: '努力', reg: /\/:,@f/g },
    { file: '131.gif', code: '/::-S', title: '骂', reg: /\/::-S/g },
    { file: '133.gif', code: '/:,@x', title: '秘密', reg: /\/:,@x/g },
    { file: '134.gif', code: '/:,@@', title: '乱', reg: /\/:,@@/g },
    { file: '135.gif', code: '/::8', title: '疯', reg: /\/::8/g },
    { file: '136.gif', code: '/:,@!', title: '哀', reg: /\/:,@!/g },
    { file: '137.gif', code: '/:!!!', title: '鬼', reg: /\/:!!!/g },
    { file: '138.gif', code: '/:xx', title: '打击', reg: /\/:xx/g },
    { file: '139.gif', code: '/:bye', title: 'bye', reg: /\/:bye/g },
    { file: '142.gif', code: '/:handclap', title: '鼓掌', reg: /\/:handclap/g },
    { file: '145.gif', code: '/:<@', title: '什么', reg: /\/:<@/g },
    { file: '147.gif', code: '/::-O', title: '累', reg: /\/::-O/g },
    { file: '153.gif', code: '/:@x', title: '吓', reg: /\/:@x/g },
    { file: '155.gif', code: '/:pd', title: '刀', reg: /\/:pd/g },
    { file: '156.gif', code: '/:<W>', title: '水果', reg: /\/:<W>/g },
    { file: '157.gif', code: '/:beer', title: '酒', reg: /\/:beer/g },
    { file: '158.gif', code: '/:basketb', title: '篮球', reg: /\/:basketb/g },
    { file: '159.gif', code: '/:oo', title: '乒乓', reg: /\/:oo/g },
    { file: '195.gif', code: '/:circle', title: '跳舞', reg: /\/:circle/g },
    { file: '160.gif', code: '/:coffee', title: '咖啡', reg: /\/:coffee/g }
  ],
  // 得知当前选择的是哪个对话
  selectId: 1,
  // 得知当前选择的是哪个好友
  selectFriendId: 0,
  // 聊天列表 信息索引
  chatDelIndex: 0,
}

export default state;