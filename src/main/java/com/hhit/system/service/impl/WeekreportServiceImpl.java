package com.hhit.system.service.impl;

import com.hhit.oa.dao.NotifyDao;
import com.hhit.oa.dao.NotifyRecordDao;
import com.hhit.oa.domain.NotifyDO;
import com.hhit.oa.domain.NotifyRecordDO;
import com.hhit.system.dao.ReplyRecordDao;
import com.hhit.system.dao.UserDao;
import com.hhit.system.dao.WeekreportDao;
import com.hhit.system.domain.ReplyRecordDO;
import com.hhit.system.domain.UserDO;
import com.hhit.system.domain.WeekreportDO;
import com.hhit.system.service.SessionService;
import com.hhit.system.service.WeekreportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@Service
public class WeekreportServiceImpl implements WeekreportService {

	@Autowired
	private WeekreportDao weekreportMapper;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ReplyRecordDao replyRecordDao;
    @Autowired
    private NotifyDao notifyDao;
    @Autowired
    private NotifyRecordDao notifyRecordDao;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private SimpMessagingTemplate template;
	
	@Override
	public WeekreportDO get(Long cid){
		return weekreportMapper.get(cid);
	}
	
	@Override
	public List<WeekreportDO> list(Map<String, Object> map){
		return weekreportMapper.list(map);
	}

	@Override
	public List<WeekreportDO> findAll(Map<String, Object> map) {
		return weekreportMapper.findAll(map);
	}

	@Override
	public Integer count(Map<String, Object> map){
		return weekreportMapper.count(map);
	}
	
	@Override
	public Integer save(WeekreportDO newDO){
		return weekreportMapper.save(newDO);
	}
	
	@Override
	public Integer update(WeekreportDO newDO){
		return weekreportMapper.update(newDO);
	}
	
	@Override
	public Integer remove(Long cid){
		return weekreportMapper.remove(cid);
	}
	
	@Override
	public Integer batchRemove(Long[] cids){
		return weekreportMapper.batchRemove(cids);
	}

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer reply(WeekreportDO notify) {
	    Date date = new Date();
        notify.setGtmModified(date);
        int r = weekreportMapper.update(notify);
        // 保存通知
        NotifyDO notifyPush = new NotifyDO();
        notifyPush.setUpdateDate(date);
        notifyPush.setCreateBy(notify.getPushBy());
        notifyPush.setType(notify.getType()+"");
        if (notify.getCreateBy().contains(",")){
            notifyPush.setTitle(notify.getCreated()+":"+ notify.getCreateBy().substring(0,notify.getCreateBy().indexOf(","))+"回复了最新的周报");
        }else {
            notifyPush.setTitle(notify.getCreated()+":"+ notify.getCreateBy()+"回复了最新的周报");
        }

        notifyPush.setRemarks(notify.getRemarks());
        notifyPush.setCreateDate(notify.getGtmCreate());
        notifyPush.setUpdateDate(notify.getGtmModified());
        notifyPush.setContent("批复内容：<br/>"+date+"<br/>"+notify.getWreply()+"<br/><br/>周报内容：<br/>"+notify.getwContent());
        notifyPush.setStatus(1+"");
        // 保存到接受者列表中
        Long[] userIds = notify.getUserIds();

        Integer notifyId = notify.getWid();
        List<ReplyRecordDO> records = new ArrayList<>();
        List<NotifyRecordDO> recordDOS = new ArrayList<>();
        if(userIds.length<=1){
            Long[]  ids = new Long[]{notify.getUid()};
            notifyPush.setUserIds(ids);
            int np = notifyDao.save(notifyPush);
            ReplyRecordDO  record = new ReplyRecordDO();record.setReportId(notifyId);record.setUserId(notify.getUid());record.setIsRead(0);records.add(record);
            NotifyRecordDO recordDO = new NotifyRecordDO();
            recordDO.setUserId(record.getUserId());
            recordDO.setNotifyId(notifyPush.getId());
            recordDO.setReadDate(date);
            recordDO.setIsRead(0);
            replyRecordDao.save(record);
            notifyRecordDao.save(recordDO);
        }else {
            notifyPush.setUserIds(userIds);
            int np = notifyDao.save(notifyPush);
            for (Long userId : userIds) {
                ReplyRecordDO record = new ReplyRecordDO();
                record.setReportId(notifyId);
                record.setUserId(userId);
                record.setIsRead(0);
                records.add(record);
                NotifyRecordDO recordDO = new NotifyRecordDO();
                recordDO.setUserId(record.getUserId());
                recordDO.setNotifyId(notifyPush.getId());
                recordDO.setIsRead(0);
                recordDO.setReadDate(date);
                recordDOS.add(recordDO);
            }
            replyRecordDao.batchSave(records);
            notifyRecordDao.batchSave(recordDOS);
        }
//        replyRecordDao.batchSave(records);
        //给应该要发送的用户（在线）发送通知
        ThreadPoolExecutor executor = new ThreadPoolExecutor(20,20,10, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                if (userIds.length<1) {
                    template.convertAndSendToUser((userDao.get(notify.getUid())).toString(), "/queue/notifications", "新消息：周报回复");
                } else {
                    template.convertAndSendToUser((userDao.get(notify.getUid())).toString(), "/queue/notifications", "新消息：周报回复");
                    for (UserDO userDO : sessionService.listOnlineUser()) {
                        for (Long userId : userIds) {
                            if (notify.getUid().equals(userId)) {
                                template.convertAndSendToUser(userDO.toString(), "/queue/notifications", "新消息："+notify.getCreated()+"的周报新回复");
                            }
                        }
                    }
                }
            }
        });
        executor.shutdown();
        return r;
    }


}
