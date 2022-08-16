package com.zay.factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: ZhouAnYan
 * @Email: yan_zay@163.com
 * @Date: 2022-07-25 10:37
 */
@Slf4j
@Component
public class LeagueRuleTemplate implements LeagueRule,InitializingBean  {

//    @Resource
//    LeagueRuleMapper leagueRuleMapper;

    @Override
    public boolean getPass(LeagueRuleDO leagueRuleDO) {
        return false;
    }

    public boolean getPass2(LeagueRuleDO leagueRuleDO) {
        if (LeagueRuleTypeEnum.NOTICE_TYPE_1.getValue() != leagueRuleDO.getType()) {
//            throw new Exception();
            log.error("公会规则类型不正确:" + leagueRuleDO.getType());
        }
//        LeagueRuleDO type1 = leagueRuleMapper.selectOne("league_id", leagueRuleDO.getLeagueId(), "type", 1);
//        if (CommonStatusEnum.YES.getStatus().equals(type1.getContent())) {
//
//        }
//        return CommonStatusEnum.YES.getStatus().equals(leagueRuleDO.getContent());
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LeagueRuleFactory.register(LeagueRuleTypeEnum.NOTICE_TYPE_1.getValue(),this);
    }
}
