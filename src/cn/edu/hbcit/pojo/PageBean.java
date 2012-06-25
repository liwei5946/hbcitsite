package cn.edu.hbcit.pojo;

public class PageBean {
	private static final int DEFAULT_PAGE_SIZE = 20;  
    private int pageSize = DEFAULT_PAGE_SIZE;  // ÿҳ�ļ�¼��  
    private int start=0;  // ��ǰҳ��һ��������List�е�λ��,��0��ʼ  
    private int page=1;  //��ǰҳ  
    private int totalPage=0;  //�ܼ��ж���ҳ  
    private int totalCount=0;  // �ܼ�¼��  
//  ���캯��  
    public PageBean() {  
    }
  
    public PageBean(int page) {  
        this.page=page;  
    }
  
/////////////////  
  
    public void setPage(int page) {  
        if(page>0) {  
            start=(page-1)*pageSize;  
            this.page = page;  
        }
    }
      
    public int getPage() {  
        return page;  
    }
  
    public int getPageSize() {  
        return pageSize;  
    }
  
    public PageBean setPageSize(int pageSize) {  
        this.pageSize = pageSize;  
        return this;  
    }
    /** 
     * @return the start 
     */  
    public int getStart() {  
        return start;  
    }
  
    //  ��λ�ø��ݼ���õ�  
    protected void setStart() {  
    }
      
/** 
     * @return the totalCount 
     */  
    public int getTotalCount() {  
        return totalCount;  
    }
      
    public void setTotalCount(int totalCount) {
        this.totalCount=totalCount;  
        totalPage = (int) Math.ceil((totalCount + pageSize - 1) / pageSize);  
        start=(page-1)*pageSize;
    }
      
    //  ��ҳ����������������õ�  
    protected void setTotalPage() {  
          
    }  
      
    public int getTotalPage() {  
        return totalPage;  
    }  
      
      
///////////////  
    //��ȡ��һҳҳ��  
    public int getLastPage() {  
        if(hasLastPage()) {  
            return page-1;  
        }
        return page;  
    }
    public int getNextPage() {  
        if(hasNextPage()) {  
            return page+1;  
        }
        return page;  
    }
    /** 
     * ��ҳ�Ƿ�����һҳ. 
     */  
    public boolean hasNextPage() {  
        return page < totalPage;  
    }
  
    /** 
     * ��ҳ�Ƿ�����һҳ. 
     */  
    public boolean hasLastPage() {  
        return page > 1;  
    }

}
