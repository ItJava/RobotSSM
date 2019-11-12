package com.ssm.promotion.core.service;

  import com.ssm.promotion.core.entity.QiniuyunAc;
public interface QiniuyunAcService{

   int deleteByPrimaryKey(Integer id);

   
   int insert(QiniuyunAc record);

   
   int insertSelective(QiniuyunAc record);

   
   QiniuyunAc selectByPrimaryKey(int id);

   
   int updateByPrimaryKeySelective(QiniuyunAc record);
   
   int updateByPrimaryKey(QiniuyunAc record);

}
