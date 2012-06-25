package cn.edu.hbcit.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.hbcit.pojo.Lm;

public class LmDAO {

	BaseDAO baseDAO = new BaseDAO();
	ResultSet rs = null;
	Connection conn = null;

	// �ϲ���Ŀ
	/*parames:lmUnion1��lmUnion2�������ΪlmUnion1��Ŀ�ϲ������ΪlmUnion2����Ŀ��
	 * ���ܣ��ɹ����,
	 * ��д��ʯ��� 2011-9-28
	 * �޸ģ�
	 */
	public boolean lmUnion(int lmUnion1,int lmUnion2) {
		boolean returnVlue = false;
		try {
			conn = baseDAO.getConn();
			//�Ⱥϲ�����ɾ��
			conn.createStatement().executeUpdate("update lm set lmid='"+String.valueOf(lmUnion2).trim()+"' where lmid='"+String.valueOf(lmUnion1).trim()+"'");
			//ɾ��lmUnion1
			conn.createStatement().executeUpdate("delete from lm  where id="+String.valueOf(lmUnion1).trim());
			
			returnVlue = true;
		} catch (Exception ee) {
			ee.printStackTrace();
			returnVlue = false;
		}
		baseDAO.closeAll(conn);

		return returnVlue;
	}
	// ���ݸ���Ŀ����������Ŀ
	/* 
	 * ���ܣ�������Ŀ ������lmName--���ӵ���Ŀ����,pLmName---��Ŀ�ĸ���Ŀ���ƣ����Ϊ�գ����Ǹ���Ŀ 
	 * ��д��ʯ��� 2011-9-28
	 * �޸ģ�
	 */
	public boolean addLmByRootName(String lmName, String pLmName) {
		boolean returnVlue = false;
		if (pLmName.trim().length() == 0)// ���Ӹ���Ŀ
		{
			try {
				conn = baseDAO.getConn();
				rs = conn.createStatement().executeQuery(
						"select * from lm  where lm='" + lmName.trim() + "'");
				if (rs.next())// �����Ѵ��ڸ���Ŀ
				{
					returnVlue = false;
				} else {
					conn.createStatement().executeUpdate(
							"insert into lm(lm) values('" + lmName.trim()
									+ "')");
					returnVlue = true;
				}
			} catch (Exception ee) {
				returnVlue = false;
				ee.printStackTrace();
			}
			baseDAO.closeAll(conn);
		} else {
			try {
				conn = baseDAO.getConn();
				// ��ø���Ŀ��id
				int pid = selectLmId(pLmName,false);
				if (pid != 0) {
					rs = conn.createStatement().executeQuery(
							"select * from lm  where lm2='" + lmName.trim()
									+ "'");
					if (rs.next())// �����Ѵ��ڸ�����Ŀ
					{
						returnVlue = false;
					} else {
						conn.createStatement().executeUpdate(
								"insert into lm(lm2,lmid) values('"
										+ lmName.trim() + "','"
										+ String.valueOf(pid) + "')");
						returnVlue = true;
					}
				} else
					// ��Ŀ¼�����ڣ�û�еõ�id
					returnVlue = false;
			} catch (Exception ee) {
				returnVlue = false;
				ee.printStackTrace();
			}
			baseDAO.closeAll(conn);

		}
		return returnVlue;
	}
	// ���ݸ���Ŀid��������Ŀ
	/* 
	 * ���ܣ�������Ŀ ������lmName--���ӵ���Ŀ����;pLmId---��Ŀ�ĸ���Ŀid�����Ϊ0�����Ǹ���Ŀ;LmLevel--��Ŀ����1��root;2-child;3-leaf 
	 * ��д��ʯ��� 2011-9-28
	 * �޸ģ�
	 */
	public boolean addLmByRootId(String lmName, int pLmId,int LmLevel) {
		boolean returnVlue = false;
		if (pLmId == 0)// ���Ӹ���Ŀ
		{
			try {
				conn = baseDAO.getConn();
				rs = conn.createStatement().executeQuery(
						"select lm from lm  where lm='" + lmName.trim() + "'");
				
				if (rs.next())// �����Ѵ��ڸ���Ŀ
				{
					returnVlue = false;
				} else {
					conn.createStatement().executeUpdate("insert into lm(lm) values('" + lmName.trim()+ "')");
					returnVlue = true;
				}
			} catch (Exception ee) {
				returnVlue = false;
				ee.printStackTrace();
			}
			baseDAO.closeAll(conn);
		} else {
			try {
				conn = baseDAO.getConn();
				// 
				if(LmLevel==2)
					rs = conn.createStatement().executeQuery(
							"select * from lm  where lm2='" + lmName.trim()	+ "' and lmid='"+String.valueOf(pLmId)+"'");
				// 
				if(LmLevel==3)
					rs = conn.createStatement().executeQuery(
							"select * from lm  where lm3='" + lmName.trim()	+ "' and lmid='"+String.valueOf(pLmId)+"'");
				if (rs.next())// �����Ѵ��ڸ�����Ŀ
				{
					returnVlue = false;
				} else {
					if(LmLevel==2)
						conn.createStatement().executeUpdate(
								"insert into lm(lm2,lmid) values('"
										+ lmName.trim() + "','"
										+ String.valueOf(pLmId) + "')");
					if(LmLevel==3)
						conn.createStatement().executeUpdate(
								"insert into lm(lm3,lmid) values('"
										+ lmName.trim() + "','"
										+ String.valueOf(pLmId) + "')");
						returnVlue = true;
					}
			} catch (Exception ee) {
				returnVlue = false;
				ee.printStackTrace();
			}
			baseDAO.closeAll(conn);

		}
		return returnVlue;
	}
	//
	// ���ص�ǰ��Ŀ����
	/*
	 * ���ܣ���ǰ��Ŀ�ļ���
	 * ��д��ʯ��� 2011-9-28
	 * �޸ģ�
	 */
	public int selectCurLmLevel(int id) {
		int level = 0;
		try {
			conn = baseDAO.getConn();
			rs = conn.createStatement().executeQuery("select * from lm");
			while (rs.next()) {
				if(rs.getInt("id")==id)
				{
					String lm1=rs.getString("lm");
					String lm2=rs.getString("lm2");
					String lm3=rs.getString("lm3");
					if ( lm1!= null)
						level=1;
					if ( lm2!= null)
						level=2;
					if ( lm3!= null)
						level=3;
				
				}
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		//
		baseDAO.closeAll(conn);

		return level;
	}
	// ���ص�ǰ��Ŀ����Ŀid
	/*
	 * ���ܣ�
	 * ��д��ʯ��� 2011-9-28
	 * �޸ģ�
	 */
	public int selectCurLmPId(int id) {
		int level = 0;
		try {
			conn = baseDAO.getConn();
			rs = conn.createStatement().executeQuery("select * from lm");
			while (rs.next()) {
				if(rs.getInt("id")==id)
				{
					String lmid=rs.getString("lmid");
					if(lmid!=null)
						level=Integer.parseInt(lmid);
				}
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		//
		baseDAO.closeAll(conn);

		return level;
	}
	//
	// ������Ŀ
	/*
	 * ���ܣ�����������Ŀ��������Ϣ��������д��ʯ��� 2011-9-28�޸ģ�
	 */
	public List<Lm> selectAllLm() {
		List<Lm> lmList = new ArrayList<Lm>();
		Lm lm = null;
		try {
			conn = baseDAO.getConn();

			rs = conn.createStatement().executeQuery("select * from lm");
			while (rs.next()) {
				lm = new Lm();
				lm.setId(rs.getInt("id"));
				String lm1=rs.getString("lm");
				if ( lm1== null)
					lm.setLm("");
				else
					lm.setLm(rs.getString("lm").trim());
				//
				String lm2=rs.getString("lm2");
				if (lm2 == null)
					lm.setLm2("");
				else
					lm.setLm2(rs.getString("lm2").trim());
				//
				String lm3=rs.getString("lm3");
				if (lm3 == null)
					lm.setLm3("");
				else
					lm.setLm3(rs.getString("lm3").trim());
				//
				String lmid=rs.getString("lmid");
				if (lmid == null)
					lm.setLmid("");
				else
					lm.setLmid(rs.getString("lmid").trim());
				//
				String mb=rs.getString("mb");
				if (mb == null)
					lm.setMb("");
				else
					lm.setMb(rs.getString("mb").trim());
				//
				String pic=rs.getString("pic");
				if (pic == null)
					lm.setPic("");
				else
					lm.setPic(rs.getString("pic").trim());
				//
				lmList.add(lm);
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		//
		baseDAO.closeAll(conn);

		return lmList;
	}
	// �����ͽṹ�������Ŀid
	/*
	 * ���ܣ�������Ŀid
	 * ������lmName--��Ŀ����,optionType:true\false:connect(close)\don��t connect(close) DB
	 * ��д��ʯ��� 2011-9-28
	 * �޸ģ�
	 */
	public int getLmId(String lmName,int lmLevel,boolean optionType) {
		int returnVlue = 0;
		try {
			if(optionType)
				conn = baseDAO.getConn();
			rs = conn.createStatement().executeQuery("select id,lm,lm2,lm3 from lm");
			boolean have=false;
			//����һ����Ŀ��
			if(lmLevel==1)//һ����Ŀ
			{
				while (rs.next()) {
					returnVlue = rs.getInt("id");
					String lm=rs.getString("lm");
					if(lm==null)
						continue;
					if (lm.equals(lmName))
					{
						have=true;
						break;
					}
				}
			}
			//
			if(lmLevel==2)//������Ŀ
			{
				while (rs.next() && have==false) {
					returnVlue = rs.getInt("id");
					String lm2=rs.getString("lm2");
					if(lm2==null)
						continue;
					if (lm2.equals(lmName))
					{
						have=true;
						break;
					}
				}
			}
			//
			if(lmLevel==3)//������Ŀ
			{
				while (rs.next() && have==false) {
					returnVlue = rs.getInt("id");
					String lm3=rs.getString("lm3");
					if(lm3==null)
						continue;
					if (lm3.equals(lmName))
					{
						have=true;
						break;
					}
				}
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		if(optionType)
			baseDAO.closeAll(conn);

		return returnVlue;
	}
	// ������Ŀ
	/*
	 * ���ܣ�������Ŀ,
	 * ��д��ʯ��� 2011-9-28
	 * �޸ģ�
	 */
	public Lm selectLmObject(int lmid) {
		Lm lm=new Lm();
		try {
			conn = baseDAO.getConn();

			rs = conn.createStatement().executeQuery("select * from lm");
			while (rs.next()) {
				int id=rs.getInt("id");
				if(id==lmid)
				{
					lm.setId(lmid);
					lm.setLm(rs.getString("lm"));
					lm.setLm2(rs.getString("lm2"));
					lm.setLm3(rs.getString("lm3"));
					lm.setMb(rs.getString("mb"));
					lm.setLmid(rs.getString("lmid"));
					break;
				}
				
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		baseDAO.closeAll(conn);

		return lm;
	}
	// ������Ŀ
	/*parames:optionType:true\false:connect(close)\don��t connect(close) DB
	 * ���ܣ�������Ŀid������lmName--��Ŀ����,��д��ʯ��� 2011-9-28�޸ģ�
	 */
	public int selectLmId(String lmName,boolean optionType) {
		int returnVlue = 0;
		try {
			if(optionType)
				conn = baseDAO.getConn();

			rs = conn.createStatement().executeQuery(
					"select id,lm from lm where lm<>''");
			while (rs.next()) {
				returnVlue = rs.getInt("id");
				if (rs.getString("lm").trim().equals(lmName))
					break;
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		if(optionType)
			baseDAO.closeAll(conn);

		return returnVlue;
	}
	// ������Ŀ
	/*parames:lmLevel:=0 root;lmLevel=1 lm2;lmLevel=2 lm3
	 * ���ܣ�������Ŀid������lmName--��Ŀ����,��д��ʯ��� 2011-9-28�޸ģ�
	 */
	public String selectLmNameById(int id,int lmLevel) {
		String lmName = "";
		try {
				conn = baseDAO.getConn();
				if(lmLevel==0)//root
				{
					rs = conn.createStatement().executeQuery("select id,lm from lm where lm<>''");
					while (rs.next()) {
						lmName = rs.getString("lm");
						if (rs.getInt("id")==id)
							break;
					}
				}
				//
				if(lmLevel==1)//child
				{
					rs = conn.createStatement().executeQuery("select id,lm2 from lm where lm2<>''");
					while (rs.next()) {
						lmName = rs.getString("lm2");
						if (rs.getInt("id")==id)
							break;
					}
				}
				//
				if(lmLevel==2)//leaf
				{
					rs = conn.createStatement().executeQuery("select id,lm3 from lm where lm3<>''");
					while (rs.next()) {
						lmName = rs.getString("lm3");
						if (rs.getInt("id")==id)
							break;
					}
				}
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		baseDAO.closeAll(conn);

		return lmName;
	}
	// ������Ŀ
	/*parames:lmLevel:=0 root;lmLevel=1 lm2;lmLevel=2 lm3
	 * ���ܣ�������Ŀid������lmName--��Ŀ����,��д��ʯ��� 2011-9-28�޸ģ�
	 */
	public String selectLmNameById(int id) {
		String lmName = "";
		try {
				conn = baseDAO.getConn();
				rs = conn.createStatement().executeQuery("select id,lm,lm2,lm3 from lm ");
				while (rs.next()) {
					if (rs.getInt("id")==id)	
					{
						String lm=rs.getString("lm");
						String lm2=rs.getString("lm2");
						if( lm!=null)
							lmName = rs.getString("lm");
						else
							if(lm2!=null)
								lmName = rs.getString("lm2");
							else
								lmName = rs.getString("lm3");
						break;
					}
				}
				
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		baseDAO.closeAll(conn);

		return lmName;
	}
	// ������Ŀ
	/*
	 * ���ܣ��������и���Ŀ���Ʋ�������д��ʯ��� 2011-9-28�޸ģ�
	 */
	public List<String> selectAllParentLmName() {
		List<String> returnVlue = new ArrayList<String>();
		try {
			conn = baseDAO.getConn();
			rs = conn.createStatement().executeQuery(
					"select lm from lm where lm<>''");
			while (rs.next())
				returnVlue.add(rs.getString("lm"));
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		baseDAO.closeAll(conn);
		return returnVlue;
	}

	// ������Ŀ
	/*
	 * ���ܣ�������������Ŀ���Ʋ�������д��ʯ��� 2011-9-28�޸ģ�
	 */
	public List<String> selectAllSubLmName() {
		List<String> returnVlue = new ArrayList<String>();
		try {
			conn = baseDAO.getConn();
			rs = conn.createStatement().executeQuery(
					"select lm2 from lm where lm2<>''");
			while (rs.next())
				returnVlue.add(rs.getString("lm2"));
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		baseDAO.closeAll(conn);
		return returnVlue;
	}
	// ������Ŀ
	/*
	 * ���ܣ�����������Ŀid����Ϊadmin��д����ʹadmin��ȡ������Ŀ��id
	 */
	public String selectAllLmIdForAdmin() {
		String returnVlue = "";
		try {
			conn = baseDAO.getConn();
			rs = conn.createStatement().executeQuery(
					"select id from lm ");
			int s=1;
			while (rs.next())
			{
				if(s==1)
					returnVlue=String.valueOf(rs.getInt("id"));
				else
					returnVlue=returnVlue+","+String.valueOf(rs.getInt("id"));
				s++;
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		baseDAO.closeAll(conn);
		return returnVlue;
	}

	// ������Ŀ
	/*
	 * ���ܣ�����ĳһ����Ŀ���е�����Ŀ����
	 *������pLmName---����Ŀ���ƣ�lmLevel---��Ŀ�ļ���=2:���ص������е�lm2;=3�����ص��������е�lm3;
	 * ��д��ʯ��� 2011-9-28
	 * �޸ģ�
	 */
	public List<String> selectAllSubLmNameForPLm(String pLmName,int lmLevel) {
		List<String> returnVlue = new ArrayList<String>();
		try {
			conn = baseDAO.getConn();
			// ��ø���Ŀ��id
			int pid = getLmId(pLmName,lmLevel,false);
			if (pid != 0) {
				if(lmLevel==1)
				{
					String sql="select id,lm2 from lm where lmid='" + String.valueOf(pid)+ "'";
					rs = conn.createStatement().executeQuery(sql);
					while (rs.next())
						returnVlue.add(rs.getString("lm2"));
				}
				if(lmLevel==2)
				{
					String sql="select id,lm3 from lm where lmid='" + String.valueOf(pid)+ "'";
					rs = conn.createStatement().executeQuery(sql);
					while (rs.next())
						returnVlue.add(rs.getString("lm3"));
				}
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		baseDAO.closeAll(conn);
		return returnVlue;
	}
	/*  ������Ŀ
	 * ���ܣ�����ĳһ����Ŀ���е�����Ŀ����
	 * ������id,lmLevel:��Ŀ�ź���Ŀ����
	 */
	public Map<Integer, String> selectAllSubLmNameAndKey(int id,int lmLevel) {
		Map<Integer, String> returnVlue = new HashMap<Integer, String>();
		try {
			conn = baseDAO.getConn();
			//
			if(lmLevel==1)//����������Ŀ
				rs = conn.createStatement().executeQuery("select id,lm2 from lm where lm2<>'' and lmid='"+String.valueOf(id)+"'");
			if(lmLevel==2)//����������Ŀ
				rs = conn.createStatement().executeQuery("select id,lm3 from lm where lm3<>'' and lmid='"+String.valueOf(id)+"'");

			while (rs.next()) {
				returnVlue.put(rs.getInt("id"), rs.getString("lm2"));
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		baseDAO.closeAll(conn);
		return returnVlue;
	}
	// ������Ŀ
	/*
	 * ���ܣ��������е�һ����Ŀ���Ƽ���id��������д��ʯ��� 2011-9-28�޸ģ�
	 */
	public Map<Integer, String> selectAllParentLmNameAndKey() {
		Map<Integer, String> returnVlue = new HashMap<Integer, String>();
		try {
			conn = baseDAO.getConn();
			//
			rs = conn.createStatement().executeQuery(
					"select id,lm from lm where lm<>''");
			while (rs.next()) {
				returnVlue.put(rs.getInt("id"), rs.getString("lm"));
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		baseDAO.closeAll(conn);
		return returnVlue;
	}

	// ������Ŀ
	/*
	 * ���ܣ�������������Ŀ���Ƽ���id��������д��ʯ��� 2011-9-28�޸ģ�
	 */
	public Map<Integer, String> selectAllSubLmNameAndKey() {
		Map<Integer, String> returnVlue = new HashMap<Integer, String>();
		try {
			conn = baseDAO.getConn();
			//
			rs = conn.createStatement().executeQuery(
					"select id,lm2 from lm where lm2<>''");
			while (rs.next()) {
				returnVlue.put(rs.getInt("id"), rs.getString("lm2"));
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		baseDAO.closeAll(conn);
		return returnVlue;
	}
	// ɾ������Ŀ
	/*
	 * ���ܣ�ɾ��ĳһ����Ŀ��������д��ʯ��� 2011-9-28�޸ģ�
	 */
	public boolean deleteRootLmById(int id) {
		boolean returnVlue = false;
		try {
			conn = baseDAO.getConn();
			// ɾ������Ŀ
			conn.createStatement().executeUpdate(
					"delete from lm where id=" + String.valueOf(id));
			// ɾ������Ŀ
			conn.createStatement().executeUpdate(
					"delete from lm where lmid='" + String.valueOf(id) + "'");
			returnVlue = true;
		} catch (Exception ee) {
			ee.printStackTrace();
			returnVlue = false;
		}
		baseDAO.closeAll(conn);
		return returnVlue;
	}

	// ɾ����Ŀ
	/*
	 * ���ܣ�ɾ��ĳһ����Ŀ��������д��ʯ��� 2011-9-28�޸ģ�
	 */
	public boolean deleteRootLmByName(String pName) {
		boolean returnVlue = false;
		try {
			conn = baseDAO.getConn();
			//
			// ��ø���Ŀ��id
			int pid = selectLmId(pName,false);
			// ɾ������Ŀ
			conn.createStatement().executeUpdate(
					"delete from lm where lm='" + pName.trim() + "'");
			// ɾ������Ŀ
			conn.createStatement().executeUpdate(
					"delete from lm where lmid='" + String.valueOf(pid) + "'");
			returnVlue = true;
		} catch (Exception ee) {
			ee.printStackTrace();
			returnVlue = false;
		}
		baseDAO.closeAll(conn);
		return returnVlue;
	}

	// ɾ����Ŀ
	/*
	 * ���ܣ�ɾ��ĳһ����Ŀ��������д��ʯ��� 2011-9-28�޸ģ�
	 */
	public boolean deleteCurrentSubLm(String subName) {
		boolean returnVlue = false;
		try {
			conn = baseDAO.getConn();
			//
			// ɾ����Ŀ
			conn.createStatement().executeUpdate(
					"delete from lm where lm2='" + subName.trim() + "'");
			returnVlue = true;

		} catch (Exception ee) {
			ee.printStackTrace();
			returnVlue = false;
		}
		baseDAO.closeAll(conn);
		return returnVlue;
	}

	// ɾ��������Ŀ
	/*
	 * ���ܣ�ɾ��������Ŀ��������д��ʯ��� 2011-9-28�޸ģ�
	 */
	public boolean clear() {
		boolean returnVlue = false;
		try {
			conn = baseDAO.getConn();
			//
			// ɾ����Ŀ
			conn.createStatement().executeUpdate("delete from lm");
			returnVlue = true;

		} catch (Exception ee) {
			ee.printStackTrace();
			returnVlue = false;
		}
		baseDAO.closeAll(conn);
		return returnVlue;
	}

	// ������Ŀ
	/*
	 * ���ܣ�����ĳһ��Ŀ��������д��ʯ��� 2011-9-28�޸ģ�
	 */
	public boolean updateCurrentLm(int id, String updateValue,int lmLevel) {
		boolean returnVlue = false;
		try {
			conn = baseDAO.getConn();
			if(lmLevel==0)
				conn.createStatement().executeUpdate(
						"update lm set lm='" + updateValue.trim() + "' where id="+String.valueOf(id));
			if(lmLevel==1)
				conn.createStatement().executeUpdate(
						"update lm set lm2='" + updateValue.trim() + "' where id="+String.valueOf(id));
			if(lmLevel==2)
				conn.createStatement().executeUpdate(
						"update lm set lm3='" + updateValue.trim() + "' where id="+String.valueOf(id));

			returnVlue = true;

		} catch (Exception ee) {
			ee.printStackTrace();
			returnVlue = false;
		}
		baseDAO.closeAll(conn);
		return returnVlue;
	}
	// ������Ŀģ��
	/*
	 * ���ܣ�����ĳһ��Ŀ��������д��ʯ��� 2011-9-28�޸ģ�
	 */
	public boolean updateLmMb(int id, String lmMb) {
		boolean returnVlue = false;
		try {
			conn = baseDAO.getConn();
			conn.createStatement().executeUpdate(
						"update lm set mb='" + lmMb.trim() + "' where id="+String.valueOf(id));
			
			returnVlue = true;

		} catch (Exception ee) {
			ee.printStackTrace();
			returnVlue = false;
		}
		baseDAO.closeAll(conn);
		return returnVlue;
	}
}
