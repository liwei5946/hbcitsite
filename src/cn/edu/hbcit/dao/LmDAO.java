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

	// 合并栏目
	/*parames:lmUnion1，lmUnion2：将编号为lmUnion1栏目合并到编号为lmUnion2的栏目中
	 * 功能：成功与否,
	 * 编写：石彦杰 2011-9-28
	 * 修改：
	 */
	public boolean lmUnion(int lmUnion1,int lmUnion2) {
		boolean returnVlue = false;
		try {
			conn = baseDAO.getConn();
			//先合并，后删除
			conn.createStatement().executeUpdate("update lm set lmid='"+String.valueOf(lmUnion2).trim()+"' where lmid='"+String.valueOf(lmUnion1).trim()+"'");
			//删除lmUnion1
			conn.createStatement().executeUpdate("delete from lm  where id="+String.valueOf(lmUnion1).trim());
			
			returnVlue = true;
		} catch (Exception ee) {
			ee.printStackTrace();
			returnVlue = false;
		}
		baseDAO.closeAll(conn);

		return returnVlue;
	}
	// 根据父栏目名保存子栏目
	/* 
	 * 功能：增加栏目 参数：lmName--增加的栏目名称,pLmName---栏目的父栏目名称，如果为空，则是根栏目 
	 * 编写：石彦杰 2011-9-28
	 * 修改：
	 */
	public boolean addLmByRootName(String lmName, String pLmName) {
		boolean returnVlue = false;
		if (pLmName.trim().length() == 0)// 增加根栏目
		{
			try {
				conn = baseDAO.getConn();
				rs = conn.createStatement().executeQuery(
						"select * from lm  where lm='" + lmName.trim() + "'");
				if (rs.next())// 库中已存在该栏目
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
				// 获得父栏目的id
				int pid = selectLmId(pLmName,false);
				if (pid != 0) {
					rs = conn.createStatement().executeQuery(
							"select * from lm  where lm2='" + lmName.trim()
									+ "'");
					if (rs.next())// 库中已存在该子栏目
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
					// 父目录不存在，没有得到id
					returnVlue = false;
			} catch (Exception ee) {
				returnVlue = false;
				ee.printStackTrace();
			}
			baseDAO.closeAll(conn);

		}
		return returnVlue;
	}
	// 根据父栏目id保存子栏目
	/* 
	 * 功能：增加栏目 参数：lmName--增加的栏目名称;pLmId---栏目的父栏目id，如果为0，则是根栏目;LmLevel--栏目级别1：root;2-child;3-leaf 
	 * 编写：石彦杰 2011-9-28
	 * 修改：
	 */
	public boolean addLmByRootId(String lmName, int pLmId,int LmLevel) {
		boolean returnVlue = false;
		if (pLmId == 0)// 增加根栏目
		{
			try {
				conn = baseDAO.getConn();
				rs = conn.createStatement().executeQuery(
						"select lm from lm  where lm='" + lmName.trim() + "'");
				
				if (rs.next())// 库中已存在该栏目
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
				if (rs.next())// 库中已存在该子栏目
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
	// 返回当前栏目级别
	/*
	 * 功能：当前栏目的级别：
	 * 编写：石彦杰 2011-9-28
	 * 修改：
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
	// 返回当前栏目父栏目id
	/*
	 * 功能：
	 * 编写：石彦杰 2011-9-28
	 * 修改：
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
	// 检索栏目
	/*
	 * 功能：返回所有栏目的所有信息参数：编写：石彦杰 2011-9-28修改：
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
	// 以名和结构层次找栏目id
	/*
	 * 功能：返回栏目id
	 * 参数：lmName--栏目名称,optionType:true\false:connect(close)\don‘t connect(close) DB
	 * 编写：石彦杰 2011-9-28
	 * 修改：
	 */
	public int getLmId(String lmName,int lmLevel,boolean optionType) {
		int returnVlue = 0;
		try {
			if(optionType)
				conn = baseDAO.getConn();
			rs = conn.createStatement().executeQuery("select id,lm,lm2,lm3 from lm");
			boolean have=false;
			//搜索一级栏目找
			if(lmLevel==1)//一级栏目
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
			if(lmLevel==2)//二级栏目
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
			if(lmLevel==3)//三级栏目
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
	// 检索栏目
	/*
	 * 功能：返回栏目,
	 * 编写：石彦杰 2011-9-28
	 * 修改：
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
	// 检索栏目
	/*parames:optionType:true\false:connect(close)\don‘t connect(close) DB
	 * 功能：返回栏目id参数：lmName--栏目名称,编写：石彦杰 2011-9-28修改：
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
	// 检索栏目
	/*parames:lmLevel:=0 root;lmLevel=1 lm2;lmLevel=2 lm3
	 * 功能：返回栏目id参数：lmName--栏目名称,编写：石彦杰 2011-9-28修改：
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
	// 检索栏目
	/*parames:lmLevel:=0 root;lmLevel=1 lm2;lmLevel=2 lm3
	 * 功能：返回栏目id参数：lmName--栏目名称,编写：石彦杰 2011-9-28修改：
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
	// 检索栏目
	/*
	 * 功能：返回所有父栏目名称参数：编写：石彦杰 2011-9-28修改：
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

	// 检索栏目
	/*
	 * 功能：返回所有子栏目名称参数：编写：石彦杰 2011-9-28修改：
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
	// 检索栏目
	/*
	 * 功能：返回所有栏目id：单为admin所写，以使admin获取所有栏目的id
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

	// 检索栏目
	/*
	 * 功能：返回某一父栏目所有的子栏目名称
	 *参数：pLmName---父栏目名称，lmLevel---栏目的级别=2:返回的是所有的lm2;=3：返回的是是所有的lm3;
	 * 编写：石彦杰 2011-9-28
	 * 修改：
	 */
	public List<String> selectAllSubLmNameForPLm(String pLmName,int lmLevel) {
		List<String> returnVlue = new ArrayList<String>();
		try {
			conn = baseDAO.getConn();
			// 获得父栏目的id
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
	/*  检索栏目
	 * 功能：返回某一父栏目所有的子栏目名称
	 * 参数：id,lmLevel:栏目号和栏目级别
	 */
	public Map<Integer, String> selectAllSubLmNameAndKey(int id,int lmLevel) {
		Map<Integer, String> returnVlue = new HashMap<Integer, String>();
		try {
			conn = baseDAO.getConn();
			//
			if(lmLevel==1)//检索二级栏目
				rs = conn.createStatement().executeQuery("select id,lm2 from lm where lm2<>'' and lmid='"+String.valueOf(id)+"'");
			if(lmLevel==2)//检索三级栏目
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
	// 检索栏目
	/*
	 * 功能：返回所有的一级栏目名称及其id参数：编写：石彦杰 2011-9-28修改：
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

	// 检索栏目
	/*
	 * 功能：返回所有子栏目名称及其id参数：编写：石彦杰 2011-9-28修改：
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
	// 删除根栏目
	/*
	 * 功能：删除某一主栏目参数：编写：石彦杰 2011-9-28修改：
	 */
	public boolean deleteRootLmById(int id) {
		boolean returnVlue = false;
		try {
			conn = baseDAO.getConn();
			// 删除父栏目
			conn.createStatement().executeUpdate(
					"delete from lm where id=" + String.valueOf(id));
			// 删除子栏目
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

	// 删除栏目
	/*
	 * 功能：删除某一主栏目参数：编写：石彦杰 2011-9-28修改：
	 */
	public boolean deleteRootLmByName(String pName) {
		boolean returnVlue = false;
		try {
			conn = baseDAO.getConn();
			//
			// 获得父栏目的id
			int pid = selectLmId(pName,false);
			// 删除父栏目
			conn.createStatement().executeUpdate(
					"delete from lm where lm='" + pName.trim() + "'");
			// 删除子栏目
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

	// 删除栏目
	/*
	 * 功能：删除某一子栏目参数：编写：石彦杰 2011-9-28修改：
	 */
	public boolean deleteCurrentSubLm(String subName) {
		boolean returnVlue = false;
		try {
			conn = baseDAO.getConn();
			//
			// 删除栏目
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

	// 删除所有栏目
	/*
	 * 功能：删除所有栏目参数：编写：石彦杰 2011-9-28修改：
	 */
	public boolean clear() {
		boolean returnVlue = false;
		try {
			conn = baseDAO.getConn();
			//
			// 删除栏目
			conn.createStatement().executeUpdate("delete from lm");
			returnVlue = true;

		} catch (Exception ee) {
			ee.printStackTrace();
			returnVlue = false;
		}
		baseDAO.closeAll(conn);
		return returnVlue;
	}

	// 更新栏目
	/*
	 * 功能：更新某一栏目参数：编写：石彦杰 2011-9-28修改：
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
	// 更新栏目模板
	/*
	 * 功能：更新某一栏目参数：编写：石彦杰 2011-9-28修改：
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
