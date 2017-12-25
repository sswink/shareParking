package com.front.dao;

import com.front.dto.GenerateAllDetail;
import com.front.dto.GenerateOrder;
import com.front.dto.MyShareList;
import com.front.dto.ShareProfit;
import com.front.entity.Generate;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface GenerateMapper {
    int deleteByPrimaryKey(Long generateId);

    int insert(Generate record);

    int insertSelective(Generate record);

    Generate selectByPrimaryKey(Long generateId);

    int updateByPrimaryKeySelective(Generate record);



    List<GenerateAllDetail> getGenerateAllDetail (@Param("parkingId") long parkingId,
                                                  @Param("generateStatus")String generateStatus);


    int setStatusByOrderEnd(@Param("generateId") long generateId);


    //修改状态
    int updateByPrimaryKey(@Param("generate_id") long generate_id,@Param("status") String status);
    //共享订单ByUserId
    List<MyShareList> queryById(@Param("user_id") long user_id);
    //共享收益
    ShareProfit generateProfit(@Param("generate_id") long generate_id);
    //添加共享订单表（停车位ID 开始（结束）时间和价格）
    int insertGenerate(@Param("parkinglot_id") long parkinglot_id,
                       @Param("share_begin") long share_begin,
                       @Param("share_end") long share_end,
                       @Param("price") int price,
                       @Param("status") String status
    );
}