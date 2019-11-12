package com.ssm.promotion.core.service;


import com.ssm.promotion.core.entity.Upfilecall;

import java.util.List;

public interface UpfilecallService  {
   
   
   int deleteByPrimaryKey(Integer id);
   
   
   int insert(Upfilecall record);
   
   
   int insertSelective(Upfilecall record);
   
   
   Upfilecall selectByPrimaryKey(Integer id);

   List<Upfilecall> selectByType(String bType);

   Upfilecall sUpfilecallByTypeFileName(String btype,String bfrealname);

   
   int updateByPrimaryKeySelective(Upfilecall record);
   
   
   int updateByPrimaryKey(Upfilecall record);

}
