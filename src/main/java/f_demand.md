# 需求f：投诉系统
***

## /UploadReport 提交投诉
### 概述
    用户提交投诉
### 接口
    传入：
        canteenId(必需) 
        title(必需)
        body(必需)
        {
            "canteenId": 2,
            "title": "五食堂真jb贵",
            "body": "20一份饭，你咋不去抢呢，这么着急攒钱给你妈买古灰河吗"
        }
    无有效传出
***

## /UploadReportReply 提交投诉回复
### 概述
    管理员提交投诉回复
### 接口
    传入：
        reportId(必需)
        body(必需)
        {
            "reportId": 2,
            "body": "一份饭20哪里贵了？这么多年都是这个价，不要睁着眼睛乱说。再瞎bb把你马鲨了"
        }
    无有效传出
***

## /GetReportResult 查看投诉结果
### 概述
    用户查看投诉结果
### 接口
    传入：
        reportId()
        {
            "reportId": 2
        }
    传出：
        "投诉暂时没有回应" String型，表示没有回复
        
        ReportReply结构体，表示有回复
        {
            "body": "一份饭20哪里贵了？这么多年都是这个价，不要睁着眼睛乱说。再瞎bb把你马鲨了",
            "canteenAdminId": 111111,
            "reportId": 2,
            "reportReplyId": 4
        }
***