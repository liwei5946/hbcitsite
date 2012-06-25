package cn.edu.hbcit.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.edu.hbcit.dao.LmDAO;
import cn.edu.hbcit.dao.NewsDAO;
import cn.edu.hbcit.dao.UserDAO;

import cn.edu.hbcit.pojo.Lm;
import cn.edu.hbcit.util.UserRight;

public class LmService {
	LmDAO lmDAO=new LmDAO();
	NewsDAO newsDAO=new NewsDAO();
	UserDAO userDAO=new UserDAO();
	
	/*
	 * ���ܣ�������Ŀ,
	 * ��д��ʯ��� 2011-9-28
	 * �޸ģ�
	 */
	public Lm selectLmObject(int lmid) {
		return lmDAO.selectLmObject(lmid);
	}// ������������ҳ����û���Ȩ������Ŀ���������ŷ�����ֱ��ʹ�ã�������Ŀ���ݴΣ�
	/*parames:adminRight  �û���Ȩ��id����
	 * ���ܣ��ɹ����,
	 * ��д��ʯ��� 2011-9-28
	 * �޸ģ�
	 */
	public List<UserRight> getUserRightLm(String adminRight) {
		//��ȡ�û�Ȩ��
		List<UserRight> rightList=new ArrayList<UserRight>();
		try
		{
			String[] dbRight=adminRight.split(",");
			for(int i=0;i<dbRight.length;i++)
			{
				UserRight userRight=new UserRight();
				String rightName=lmDAO.selectLmNameById(Integer.parseInt(dbRight[i]));
				int level=lmDAO.selectCurLmLevel(Integer.parseInt(dbRight[i]));
				int pid=lmDAO.selectCurLmPId(Integer.parseInt(dbRight[i]));
				//
				userRight.setRightName(rightName+"("+String.valueOf(level)+"��)");
				userRight.setRightID(Integer.parseInt(dbRight[i]));//����id
				userRight.setLmLevel(level);//����
				userRight.setpRightID(pid);//����id
				//
				rightList.add(userRight);
			}
		}catch(Exception e)
		{
			rightList=null;
			e.printStackTrace();
		}
		//
		return rightList;
	}
	
	// ��������ҳ����û����ŷ����󣬵õ�ѡ���lm��id���У�һ����Ŀ���ظ�ʽ��1����Ŀ��,0,0;   ������Ŀ���ظ�ʽ��1����Ŀ��,2����Ŀ��,0;   ������Ŀ���ظ�ʽ��1����Ŀ��,2����Ŀ��,3����Ŀ��;��
	/*parames:adminRight  �û���Ȩ��id����
	 * ���ܣ��ɹ����,
	 * ��д��ʯ��� 2011-9-28
	 * �޸ģ�
	 */
	public String getSelectLmId(String lm) {
		String retuString="";		
		try
		{
			String  lmLevel=lm.trim().substring(lm.length()-4);//��Ҫ��ȡ���Ҳ�ļ����磺(1��)
			//
			if(lmLevel.trim().equals("(1��)"))//�õ���Ŀ�ż���
			{
				String lmName=lm.trim().substring(0,lm.length()-4);
				int id=lmDAO.getLmId(lmName, 1, true);
				retuString=String.valueOf(id)+",0,0";
			}
			//
			if(lmLevel.trim().equals("(2��)"))//�õ���Ŀ�ż���
			{
				String lmName=lm.trim().substring(0,lm.length()-4);
				int id=lmDAO.getLmId(lmName, 2, true);
				int pid=lmDAO.selectCurLmPId(id);
				retuString=String.valueOf(pid)+","+String.valueOf(id)+",0";
			}
			//
			if(lmLevel.trim().equals("(3��)"))//�õ���Ŀ�ż���
			{
				String lmName=lm.trim().substring(0,lm.length()-4);
				int id=lmDAO.getLmId(lmName, 1, true);
				int pid=lmDAO.selectCurLmPId(id);
				int ppid=lmDAO.selectCurLmPId(pid);
				retuString=String.valueOf(ppid)+","+String.valueOf(pid)+","+String.valueOf(id);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		//
		return retuString;
	}
	// �����޸�ҳ����û����ŷ�����
	/*parames:lmIds  ���ŵ�id��,�ö��ŷָ�磺69,70,0
	 * ���ܣ�������Ŀ��������(1��)��(2��)��(3��)
	 * ��д��ʯ��� 2011-9-28
	 * �޸ģ�
	 */
	public String getLmName(String lmIds) {
		String retuString="";	
		if(lmIds.trim().length()==0)
			return null;
		String[] lmid=lmIds.split(",");
		try
		{
			//
			if(!lmid[2].trim().equals("0"))//�õ���Ŀ�ż���
			{
				retuString=lmDAO.selectLmNameById(Integer.parseInt(lmid[2]));
				retuString=retuString.trim()+"(3��)";
			}else
				if(!lmid[1].trim().equals("0"))//�õ���Ŀ�ż���
				{
					retuString=lmDAO.selectLmNameById(Integer.parseInt(lmid[1]));
					retuString=retuString.trim()+"(2��)";
				}
				else
				{
					retuString=lmDAO.selectLmNameById(Integer.parseInt(lmid[0]));
					retuString=retuString.trim()+"(1��)";
				}	
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		//
		return retuString;
	}
	// �ϲ���Ŀ
	/*parames:lmUnion1��lmUnion2�������ΪlmUnion1��Ŀ�ϲ������ΪlmUnion2����Ŀ��
	 * ���ܣ��ɹ����,
	 * ��д��ʯ��� 2011-9-28
	 * �޸ģ�
	 */
	public boolean lmUnion(int lmUnion1,int lmUnion2) {
		return lmDAO.lmUnion(lmUnion1, lmUnion2);
	}
	
	//���ݸ���Ŀ����������Ŀ
	/*���ܣ�������Ŀ
	 * ������lmName--���ӵ���Ŀ����,pLmName---��Ŀ�ĸ���Ŀ���ƣ����Ϊ�գ����Ǹ���Ŀ
	 * ��д��ʯ��� 2011-9-28
	 * �޸ģ�
	 */
	public boolean addLmByRootName(String lmName,String pLmName )
	{
		return lmDAO.addLmByRootName(lmName, pLmName);
	}
	// ���ݸ���Ŀid��������Ŀ
	/* 
	 * ���ܣ�������Ŀ ������lmName--���ӵ���Ŀ����;pLmId---��Ŀ�ĸ���Ŀid�����Ϊ0�����Ǹ���Ŀ;LmLevel--��Ŀ����1��root;2-child;3-leaf 
	 * ��д��ʯ��� 2011-9-28
	 * �޸ģ�
	 */
	public boolean addLmByRootId(String lmName, int pLmId,int LmLevel) {
		return lmDAO.addLmByRootId(lmName, pLmId, LmLevel);
	}
	//������Ŀ
	/*���ܣ�����������Ŀ��������Ϣ(������������ͳ��)
	 *������
	 *��д��ʯ��� 2011-9-28
	 *�޸ģ�
	 */
	public List<Lm> selectAllLm()
	{
		
		List<Lm> lmList=lmDAO.selectAllLm();
		//�������������´������ʹpojo������Ӧ������ͳ��
		List<Lm> reLmList=new ArrayList<Lm>();
		Iterator<Lm> lmIt=lmList.iterator();
		while(lmIt.hasNext())
		{
			Lm lm=(Lm)lmIt.next();
			String lmLevel="";
			String lmLevel1=lm.getLm();
			String lmLevel2=lm.getLm2();
			String lmLevel3=lm.getLm3();
			if(lmLevel1!="")
				lmLevel="1";
			else
				if(lmLevel2!="")
					lmLevel="2";
				else
					if(lmLevel3!="")
						lmLevel="3";
			//
			int documentsCount=newsDAO.countLm(lmLevel, String.valueOf(lm.getId()));
			lm.setDocumentsCount(documentsCount);//�Ѿ�����������ͳ��
			//
			reLmList.add(lm);
		}
		//����������ͳ�Ƶ�pojo����
		return reLmList;
	}
	// ������Ŀ
	/*parames:lmLevel:=0 root;lmLevel=1 lm2;lmLevel=2 lm3
	 * ���ܣ�������Ŀid������lmName--��Ŀ����,
	 * ��д��ʯ��� 2011-9-28
	 * �޸ģ�
	 */
	public String selectLmNameById(int id,int lmLevel) {
		return lmDAO.selectLmNameById(id, lmLevel);
	}
	// ������Ŀ
	/*parames:lmLevel:=0 root;lmLevel=1 lm2;lmLevel=2 lm3
	 * ���ܣ�������Ŀid������lmName--��Ŀ����,
	 * ��д��ʯ��� 2011-9-28
	 * �޸ģ�
	 */
	public String selectLmNameById(int id) {
		return lmDAO.selectLmNameById(id);
	}
	// �����ͽṹ�������Ŀid
	/*
	 * ���ܣ�������Ŀid
	 * ������lmName--��Ŀ����,optionType:true\false:connect(close)\don��t connect(close) DB
	 * ��д��ʯ��� 2011-9-28
	 * �޸ģ�
	 */
	public int getLmId(String lmName,int lmLevel,boolean optionType) {
		return lmDAO.getLmId(lmName, lmLevel, optionType);
	}
	//������Ŀ
	/*���ܣ��������и���Ŀ����
	 *������
	 *��д��ʯ��� 2011-9-28
	 *�޸ģ�
	 */
	public List<String> selectAllParentLmName()
	{
		return lmDAO.selectAllParentLmName();
	}	
	//������Ŀ
	/*���ܣ�������������Ŀ����
	 *������
	 *��д��ʯ��� 2011-9-28
	 *�޸ģ�
	 */
	public List<String> selectAllSubLmName()
	{
		return lmDAO.selectAllSubLmName();
	}	
	//������Ŀ
	/*���ܣ���������ĳһ����Ŀ���е�����Ŀ����
	 *������pLmName---��Ŀ�ĸ���Ŀ���ƣ�
	 *��д��ʯ��� 2011-9-28
	 *�޸ģ�
	 */
	public List<String> selectAllSubLmNameForPLm(String pLmName,int lmLevel)
	{ 
		return lmDAO.selectAllSubLmNameForPLm(pLmName, lmLevel);
	}	
	//������Ŀ
	/*���ܣ��������е���Ŀ���Ƽ���id
	 *������
	 *��д��ʯ��� 2011-9-28
	 *�޸ģ�
	 */
	public Map<Integer,String> selectAllParentLmNameAndKey()
	{ 
		return lmDAO.selectAllParentLmNameAndKey();
	}
	//������Ŀ
	/*���ܣ�������������Ŀ���Ƽ���id
	 *������
	 *��д��ʯ��� 2011-9-28
	 *�޸ģ�
	 */
	public Map<Integer,String> selectAllSubLmNameAndKey()
	{ 
		return lmDAO.selectAllSubLmNameAndKey();
	}
	// ɾ������Ŀ
	/*
	 * ���ܣ�ɾ��ĳһ����Ŀ��������д��ʯ��� 2011-9-28�޸ģ�
	 */
	public boolean deleteRootLmById(int id) {
		return lmDAO.deleteRootLmById(id);
	}
	//ɾ����Ŀ
	/*���ܣ�ɾ��ĳһ����Ŀ
	 *������
	 *��д��ʯ��� 2011-9-28
	 *�޸ģ�
	 */
	public boolean deleteRootLmByName(String pName)
	{ 
		return lmDAO.deleteRootLmByName(pName);
	}	
	//ɾ����Ŀ
	/*���ܣ�ɾ��ĳһ����Ŀ
	 *������
	 *��д��ʯ��� 2011-9-28
	 *�޸ģ�
	 */
	public boolean deleteCurrentSubLm(String subName)
	{ 
		return lmDAO.deleteCurrentSubLm(subName);
	}	
	//ɾ��������Ŀ
	/*���ܣ�ɾ��������Ŀ
	 *������
	 *��д��ʯ��� 2011-9-28
	 *�޸ģ�
	 */
	public boolean clear()
	{ 
		return lmDAO.clear();
	}	
	//������Ŀ
	/*���ܣ�����ĳһ��Ŀ
	 *������
	 *��д��ʯ��� 2011-9-28
	 *�޸ģ�
	 */
	public boolean updateCurrentLm(int id,String updateValue,int lmLevel)
	{ 
		return lmDAO.updateCurrentLm(id, updateValue,lmLevel);
	}	
	// ������Ŀģ��
	/*
	 * ���ܣ�����ĳһ��Ŀ��������д��ʯ��� 2011-9-28�޸ģ�
	 */
	public boolean updateLmMb(int id, String lmMb) {
		return lmDAO.updateLmMb(id, lmMb);
	}
	/*  ������Ŀ
	 * ���ܣ�����ĳһ����Ŀ���е�����Ŀ����
	 * ������id,lmLevel:��Ŀ�ź���Ŀ����
	 */
	public Map<Integer, String> selectAllSubLmNameAndKey(int id,int lmLevel) {
		return lmDAO.selectAllSubLmNameAndKey(id, lmLevel);
	}
	// ������Ŀ
	/*
	 * ���ܣ�����������Ŀid����Ϊadmin��д����ʹadmin��ȡ������Ŀ��id
	 */
	public String selectAllLmIdForAdmin() {
		return lmDAO.selectAllLmIdForAdmin();
	}
}
