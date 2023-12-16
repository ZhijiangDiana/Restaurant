# 在线交流实现档案
### 底层实现是通过websocket。实现流程如下：
### ①用户登录进来页面获取其userId(这个结合到餐厅中也要做，也是从servlet拿id)
### ②选择评论区的人点击其弹出聊天，点击聊天后跳出对话框，此时相当于websocket的onopen方法执行了。这里跟传统的在线聊天不一样的是在线聊天室保证了人在线才能开始聊天，而餐厅这里可能对方不在线，如果直接套用onopen的模板会导致拿到的session为空。所以此时onopen不做事情。
### ③当输入了一段内容点击发送之后相当于执行了onMessage()方法，这时需要传参有：fromId,toId,toName,mess。首先会先将这个信息根据Message的格式存入数据库中，接着从onlineUser里尝试拿接收方，如果发现接收方的session是空的，那我就返一个封装类if (sessionTo == null){
                // 用于告知前端对方不在线 直接再调一次fetchMessage就行
                String msg2 = MessageUtils.getMessage(false, toId, 0);
                sessionFrom.getBasicRemote().sendText(msg2);
                // 这里不能调接收方session 因为是空的
            }
### 前端拿到0意味着要去刷新再调一次fetchMessages()，其实就相当于再查一次表把更新后的数据展现出来。如果对方在线的话，就相当于是在线聊天了 
                sessionFrom.getBasicRemote().sendText(msg1);
                // 不然永远都是发送方发消息
                String msg3 = MessageUtils.getMessage(false,null,mess);
                sessionTo.getBasicRemote().sendText(msg3);
### 这里就是将消息直接通过session的获取远程然后直接展现数据。注意这里向发送方的发送的数据得是msg3，不然前端那一直都是右边在说话

### 后端的基本逻辑到此结束


## 前端逻辑
### 这里的fetchAllUserList是可以删掉的，这里是为了模拟和人聊天。正常情况下直接搜索或者点击人名字就能进行聊天。多的好像也没什么好说了，前端我都写的七七八八了，到时候前端要做的就是完善这个模型，这里有个问题是聊天框没固定大小，如果消息太多就会一直无限向下延生，所以要显示聊天框的大小并配上滚动条就可以了。


# 判断狗叫还没做 因为不知道现在判断狗叫是咋做的。 