package cn.edu.hbcit.services;

import java.util.ArrayList;
import java.util.List;

import cn.edu.hbcit.dao.DynamicSetLmDAO;
import cn.edu.hbcit.pojo.DynamicSetLm;
public class DynamicSetLmService {

	
	DynamicSetLmDAO dynamicSetLmDAO=new DynamicSetLmDAO();


		/*
		 * ���ܣ���Ӱ������ 
		 * �����������ݿ����û�pojo˵�� 
		 * ��д��ʯ��� 2011-9-28
		 * �޸ģ�
		 */
		public boolean addDynamicSetLm(String indexLmNo, String lm, String lm2,
				String lm3, String indexLmPic, String indexLmTitle,
				String indexLmTitleStyle,String notes) {
			return dynamicSetLmDAO.addDynamicSetLm(indexLmNo, lm, lm2, lm3, indexLmPic, indexLmTitle, indexLmTitleStyle, notes);
		}
		/*
		 * ���ܣ��޸İ������ 
		 * �����������ݿ����û�pojo˵�� 
		 * ��д��ʯ��� 2011-9-28
		 * �޸ģ�
		 */
		public boolean updateDynamicSetLm(int id,String indexLmNo, String lm, String lm2,
				String lm3, String indexLmPic, String indexLmTitle,
				String indexLmTitleStyle,String notes) {
			return dynamicSetLmDAO.updateDynamicSetLm(id, indexLmNo, lm, lm2, lm3, indexLmPic, indexLmTitle, indexLmTitleStyle, notes);

		}
		/*
		 * ���ܣ��������еİ������ 
		 * ������
		 * ��д��ʯ��� 2011-9-28
		 * �޸ģ�
		 */
		public List<DynamicSetLm> selectAllDynamicSetLm() {
			
			return dynamicSetLmDAO.selectAllDynamicSetLm();

		}
		/*
		 * ���ܣ��������еİ��˵�� 
		 * ������
		 * ��д��ʯ��� 2011-9-28
		 * �޸ģ�
		 */
		public List<String> selectAllnotes() {
			
			return dynamicSetLmDAO.selectAllnotes();
		}
		/*
		 * ���ܣ�����ĳһ�İ������ 
		 * ������
		 * ��д��ʯ��� 2011-9-28
		 * �޸ģ�
		 */
		public DynamicSetLm selectOneDynamicSetLm(int indexLmNo) {
				
			return dynamicSetLmDAO.selectOneDynamicSetLm(indexLmNo);

		}
		/*
		 * ���ܣ�����ĳһ�İ��洢���ŵ�ǰ������Ŀ
		 * ������
		 * ��д��ʯ��� 2011-9-28
		 * �޸ģ�
		 */
		public String selectOneDynamicLmType(int type) {
			return dynamicSetLmDAO.selectOneDynamicLmType(type);
		}
		//
		/*
		 * ���ܣ�����ĳһ��鶥��ͼƬ
		 * ������indexLmPic:ͼƬ���ƣ�indexLmNo������
		 * ��д��ʯ��� 2011-9-28
		 * �޸ģ�
		 */
		public boolean updateOneDynamicLmPic(String indexLmPic,String indexLmNo) {
			return dynamicSetLmDAO.updateOneDynamicLmPic(indexLmPic, indexLmNo);
		}
		/*
		 * ���ܣ�ɾ��һ�����
		 * ������indexLmPic:ͼƬ���ƣ�indexLmNo������
		 * ��д��ʯ��� 2011-9-28
		 * �޸ģ�
		 */
		public boolean deleteOneDynamicLm( int id,String indexLmNo) {
			return dynamicSetLmDAO.deleteOneDynamicLm(id, indexLmNo);
		}
		/*
		 * ���ܣ�����ĳһ��Ŀ�Ƿ������ڰ����
		 * ������indexLmPic:ͼƬ���ƣ�indexLmNo������
		 * ��д��ʯ��� 2011-9-28
		 * �޸ģ�
		 */
		public boolean DynamicContinesLm( String lm, String lm2,String lm3) {
			return dynamicSetLmDAO.DynamicContinesLm(lm, lm2, lm3);
		}
}
