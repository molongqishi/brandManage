package proj;

import java.util.List;

// 分页查询
public class PageBean<T> {
    // 总条数
    private int totalCount;
    // 自定义一个List，实现代码的通用
    private List<T> rows;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
