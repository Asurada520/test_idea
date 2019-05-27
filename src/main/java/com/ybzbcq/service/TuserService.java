package com.ybzbcq.service;

import com.ybzbcq.mapper.TUserMapper;
import com.ybzbcq.model.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service

public class TuserService {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired(required = false)
    private TUserMapper tUserMapper;

    public TUser selectByPrimaryKey(Integer tId) {
        TUser tUser = tUserMapper.selectByPrimaryKey(tId);
        return tUser;
    }

    public List<TUser> selectAllUser() {

        // 字符串的序列化器
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        // 查询缓存
        List<TUser> allUserList = (List<TUser>) redisTemplate.opsForValue().get("allUser");
        if(CollectionUtils.isEmpty(allUserList)){
            // json 对象序列化器
            redisSerializer = new Jackson2JsonRedisSerializer(Object.class);
            redisTemplate.setValueSerializer(redisSerializer);

            allUserList = tUserMapper.selectAllUser();
            redisTemplate.opsForValue().set("allUser", allUserList);
        }

        return allUserList;
    }

    /**
     * 根据tId 更新对象
     * @param user 待更新对象
     * @return 0 - 更新失败 1 - 更新成功
     */
    @Transactional
    public int updateUser(TUser user){
        int i = tUserMapper.updateByPrimaryKeySelective(user);
        if(i > 0){
            return 1;
        }else{
            return 0;
        }
    }
}
