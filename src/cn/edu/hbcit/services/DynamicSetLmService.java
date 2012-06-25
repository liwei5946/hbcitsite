package cn.edu.hbcit.services;

import java.util.ArrayList;
import java.util.List;

import cn.edu.hbcit.dao.DynamicSetLmDAO;
import cn.edu.hbcit.pojo.DynamicSetLm;
public class DynamicSetLmService {

	
	DynamicSetLmDAO dynamicSetLmDAO=new DynamicSetLmDAO();


		/*
		 * 功能：添加板块设置 
		 * 参数：看数据库设置或pojo说明 
		 * 编写：石彦杰 2011-9-28
		 * 修改：
		 */
		public boolean addDynamicSetLm(String indexLmNo, String lm, String lm2,
				String lm3, String indexLmPic, String indexLmTitle,
				String indexLmTitleStyle,String notes) {
			return dynamicSetLmDAO.addDynamicSetLm(indexLmNo, lm, lm2, lm3, indexLmPic, indexLmTitle, indexLmTitleStyle, notes);
		}
		/*
		 * 功能：修改板块设置 
		 * 参数：看数据库设置或pojo说明 
		 * 编写：石彦杰 2011-9-28
		 * 修改：
		 */
		public boolean updateDynamicSetLm(int id,String indexLmNo, String lm, String lm2,
				String lm3, String indexLmPic, String indexLmTitle,
				String indexLmTitleStyle,String notes) {
			return dynamicSetLmDAO.updateDynamicSetLm(id, indexLmNo, lm, lm2, lm3, indexLmPic, indexLmTitle, indexLmTitleStyle, notes);

		}
		/*
		 * 功能：检索所有的板块设置 
		 * 参数：
		 * 编写：石彦杰 2011-9-28
		 * 修改：
		 */
		public List<DynamicSetLm> selectAllDynamicSetLm() {
			
			return dynamicSetLmDAO.selectAllDynamicSetLm();

		}
		/*
		 * 功能：检索所有的板块说明 
		 * 参数：
		 * 编写：石彦杰 2011-9-28
		 * 修改：
		 */
		public List<String> selectAllnotes() {
			
			return dynamicSetLmDAO.selectAllnotes();
		}
		/*
		 * 功能：检索某一的板块设置 
		 * 参数：
		 * 编写：石彦杰 2011-9-28
		 * 修改：
		 */
		public DynamicSetLm selectOneDynamicSetLm(int indexLmNo) {
				
			return dynamicSetLmDAO.selectOneDynamicSetLm(indexLmNo);

		}
		/*
		 * 功能：检索某一的板块存储新闻的前三个栏目
		 * 参数：
		 * 编写：石彦杰 2011-9-28
		 * 修改：
		 */
		public String selectOneDynamicLmType(int type) {
			return dynamicSetLmDAO.selectOneDynamicLmType(type);
		}
		//
		/*
		 * 功能：更改某一板块顶部图片
		 * 参数：indexLmPic:图片名称；indexLmNo：版块号
		 * 编写：石彦杰 2011-9-28
		 * 修改：
		 */
		public boolean updateOneDynamicLmPic(String indexLmPic,String indexLmNo) {
			return dynamicSetLmDAO.updateOneDynamicLmPic(indexLmPic, indexLmNo);
		}
		/*
		 * 功能：删除一个板块
		 * 参数：indexLmPic:图片名称；indexLmNo：版块号
		 * 编写：石彦杰 2011-9-28
		 * 修改：
		 */
		public boolean deleteOneDynamicLm( int id,String indexLmNo) {
			return dynamicSetLmDAO.deleteOneDynamicLm(id, indexLmNo);
		}
		/*
		 * 功能：检索某一栏目是否设置在板块中
		 * 参数：indexLmPic:图片名称；indexLmNo：版块号
		 * 编写：石彦杰 2011-9-28
		 * 修改：
		 */
		public boolean DynamicContinesLm( String lm, String lm2,String lm3) {
			return dynamicSetLmDAO.DynamicContinesLm(lm, lm2, lm3);
		}
}
